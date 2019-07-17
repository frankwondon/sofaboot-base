package com.module.api.app.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONPath;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.response.AlipayTradePayResponse;
import com.google.common.collect.Lists;
import com.lib.db.entity.AppOrder;
import com.lib.db.entity.AppOrderDetail;
import com.lib.db.entity.AppOrderRecord;
import com.module.api.app.dto.AddressDto;
import com.module.api.app.dto.ExpressPriceResult;
import com.module.api.app.dto.PayDto;
import com.module.api.app.entity.*;
import com.module.api.app.mapper.*;
import com.module.api.app.query.ComputerProductPriceQuery;
import com.module.api.app.query.CreateOrderQuery;
import com.module.api.app.query.ExpressPriceQuery;
import com.module.api.app.result.ComputerOrderResult;
import com.module.api.app.result.CreateOrderResult;
import com.module.api.app.service.OrderService;
import com.module.common.ResponseCode;
import com.module.common.ResponseException;
import com.module.common.constant.ExpressEnum;
import com.module.common.constant.OrderEnum;
import com.module.common.constant.ProductEnum;
import com.module.common.exception.DBException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author wangdong
 * @date: 2019/7/9 14:17
 */
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private ProductMapper productMapper;
    @Resource
    private AppOrderMapper orderMapper;
    @Resource
    private AppOrderDetailMapper orderDetailMapper;
    @Resource
    private AppOrderRecordMapper orderRecordMapper;
    @Resource
    private ProductSkuMapper skuMapper;
    @Resource
    private ExpressTemplateMapper expressTemplateMapper;
    @Resource
    private AppCartMapper appCartMapper;
    @Resource
    private AppExpressAddressMapper addressMapper;

    @Override
    public List<ComputerOrderResult> computerOrderResults(List<ComputerProductPriceQuery> query) {
        List<ComputerOrderResult> orderResults = Lists.newArrayList();
        for (ComputerProductPriceQuery createOrderQuery : query) {
            //创建多个订单
            orderResults.add(this.computerOrderPrice(createOrderQuery));
        }
        return orderResults;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public CreateOrderResult createOrder(CreateOrderQuery query, Integer userId) {
        //生成订单号
        String parentOrderNum = orderMapper.callOrderNum();
        int productSize = query.getProducts().size();
        List<String> childOrderNums = this.createChildOrderNums(parentOrderNum, productSize);
        List<AppOrder> childOrders = Lists.newArrayList();
        for (int i = 0; i < productSize; i++) {
            ComputerProductPriceQuery computerProductPriceQuery = query.getProducts().get(i);
            //创建多个子订单
            childOrders.add(this.createChildOrder(parentOrderNum, childOrderNums.get(i), computerProductPriceQuery, userId, query.getAddressId()));
            //删除购物车
            if (computerProductPriceQuery.getCartId() != null) {
                appCartMapper.deleteById(computerProductPriceQuery.getCartId());
            }
        }
        return createParentOrder(parentOrderNum, childOrders, userId, query.getAddressId());
    }

    /**
     * 创建子订单号
     *
     * @param parentOrderNum
     * @param childSize
     * @return
     */
    private List<String> createChildOrderNums(String parentOrderNum, int childSize) {
        List<String> childOrderNums = Lists.newArrayList();
        for (int i = 0; i < childSize; i++) {
            childOrderNums.add(String.format("%s%04d", parentOrderNum, i + 1));
        }
        return childOrderNums;
    }


    @Override
    public List<ExpressPriceResult> computeExpressPrice(ExpressPriceQuery query) {
        List<ExpressPriceResult> list = Lists.newArrayList();
        for (ComputerProductPriceQuery product : query.getProducts()) {
            list.add(createDBExpressPrice(product, query.getAddressId()));
        }
        return list;
    }

    @Override
    public void cancelOrder(Integer orderId, Integer userId) {
        if (orderMapper.cancelOrder(orderId, userId) == 0) {
            throw new DBException(ResponseCode.C_520016);
        } else {
            this.createOrderRecord();
        }
    }

    private void createOrderRecord() {
        AppOrderRecord record = new AppOrderRecord();
        record.setCreateTime(LocalDateTime.now());
        record.setOperationRemark("用户取消了订单");
        record.setOperationType(OrderEnum.RecordOperationType.CLOSE_ORDER.key());
        record.setOperationUserType(OrderEnum.RecordOperationUserType.USER.key());
        orderRecordMapper.insert(record);
    }

    private ExpressPriceResult createDBExpressPrice(ComputerProductPriceQuery query, Integer addressId) {
        AppExpressAddress appExpressAddress = addressMapper.selectById(addressId);
        if (appExpressAddress == null) {
            throw new DBException(ResponseCode.C_520015);
        }
        AppProduct product = productMapper.selectById(query.getProductId());
        ExpressPriceResult priceResult = new ExpressPriceResult();
        BigDecimal expressPrice = BigDecimal.valueOf(0);
        //如果商品定义需要物流
        if (product.getShipType() == ProductEnum.ShipType.NEED.key()) {
            AppExpressTemplate appExpressTemplate = expressTemplateMapper.selectById(product.getExpressTemplateId());
            //是用户自费物流的话
            if (appExpressTemplate.getType() == ExpressEnum.Type.USER.key()) {
                Optional<AppExpressTemplateArea> check = Optional.empty();
                //如果运费模板勾选默认运费 否则根据省市的CODE去寻找运费规则
                if (appExpressTemplate.getDefaultArea() == ExpressEnum.DefaultType.DEFAULT.key()) {
                    check = Optional.of(expressTemplateMapper.defaultTemplateAreas(appExpressTemplate.getId()));
                } else {
                    List<AppExpressTemplateArea> appExpressTemplateAreas = expressTemplateMapper.templateAreas(appExpressTemplate.getId());
                    for (AppExpressTemplateArea area : appExpressTemplateAreas) {
                        JSONArray jsonArray = JSONArray.parseArray(area.getArea());
                        if (JSONPath.containsValue(jsonArray, "$.code", appExpressAddress.getProvinceCode())) {
                            check = Optional.of(area);
                            break;
                        }
                    }
                    if (!check.isPresent()) {
                        for (AppExpressTemplateArea area : appExpressTemplateAreas) {
                            JSONArray jsonArray = JSONArray.parseArray(area.getArea());
                            if (JSONPath.containsValue(jsonArray, "$.code", appExpressAddress.getCityCode())) {
                                check = Optional.of(area);
                                break;
                            }
                        }
                    }
                }
                //如果查到运费模板 计算运费否则该商品不能进行配送
                if (check.isPresent()) {
                    ExpressEnum.PriceRule priceRule = ExpressEnum.PriceRule.byKey(appExpressTemplate.getPriceRule());
                    if (priceRule == null) {
                        log.error("计算方式为空,请检查后台运费模板设置！");
                        throw new DBException(ResponseCode.C_520013);
                    }
                    AppExpressTemplateArea eArea = check.get();
                    //得到最终价格
                    expressPrice = computerPrice(eArea, product, query.getNumber(), priceRule);
                } else {
                    priceResult.setCanDelivery(false);
                }
            }
        }
        priceResult.setProductId(query.getProductId());
        priceResult.setSkuId(query.getSkuId());
        priceResult.setExpressPrice(expressPrice.longValue());
        return priceResult;
    }

    /**
     * 计算个订单的具体运费
     *
     * @param eArea      采用的运费模板
     * @param product    商品
     * @param productNum 购买数量
     * @param priceRule  计费规则
     * @return
     */
    private BigDecimal computerPrice(AppExpressTemplateArea eArea, AppProduct product, Integer productNum, ExpressEnum.PriceRule priceRule) {
        //大于首件计费
        int baseNum = productNum;
        BigDecimal bigDecimal;
        //按KG计费
        if (priceRule == ExpressEnum.PriceRule.WEIGHT) {
            baseNum = productNum * product.getProductGram();
        }
        //大于首件计费
        if (baseNum > eArea.getFirstNum()) {
            //减去首件的价格
            baseNum = baseNum - eArea.getFirstNum();
            Integer continueNum = eArea.getContinueNum();
            int cCount;
            //除不尽则 再加一次基础计算
            if (baseNum % continueNum == 0) {
                cCount = baseNum / continueNum;
            } else {
                cCount = (baseNum / continueNum + 1);
            }
            bigDecimal = eArea.getContinuePrice().multiply(BigDecimal.valueOf(cCount));
        } else {
            bigDecimal = eArea.getFirstPrice();
        }
        return bigDecimal;
    }


    /**
     * 创建父订单
     *
     * @param parentOrderNum
     * @param childOrders
     * @param userId
     * @return
     */
    private CreateOrderResult createParentOrder(String parentOrderNum, List<AppOrder> childOrders, Integer userId, Integer addressId) {
        BigDecimal parentPrice = BigDecimal.valueOf(0);
        BigDecimal parentExpressPrice = BigDecimal.valueOf(0);
        for (AppOrder childOrder : childOrders) {
            parentPrice = parentPrice.add(childOrder.getProductSumPrice());
            parentExpressPrice = parentExpressPrice.add(childOrder.getExpressPrice());
        }
        AppOrder order = new AppOrder();
        order.setParentOrder(OrderEnum.OrderType.PARENT.key());
        order.setOrderNum(parentOrderNum);
        order.setUserId(userId);
        order.setCreateTime(LocalDateTime.now());
        order.setProductSumPrice(parentPrice);
        order.setExpressPrice(parentExpressPrice);
        orderMapper.insert(order);
        this.createOrderDetail(order, userId, addressId);
        return CreateOrderResult.builder().orderNum(parentOrderNum).orderId(order.getId()).build();
    }


    /**
     * 创建子订单
     *
     * @param createOrderQuery
     * @return
     */
    private AppOrder createChildOrder(String parentOrderNum, String childOrderNum, ComputerProductPriceQuery createOrderQuery, Integer userId, Integer addressId) {
        AppProductSku sku = skuMapper.selectById(createOrderQuery.getSkuId());
        AppProduct product = productMapper.selectById(createOrderQuery.getProductId());
        if (sku != null && product != null) {
            //下架商品
            if (product.getStatus() == ProductEnum.ShelfStatus.OBTAINED.key()) {
                throw new DBException(ResponseCode.C_520011);
            }
            //失效商品
            if (productMapper.countSkuEffective(createOrderQuery.getSkuId()) == 0) {
                throw new DBException(ResponseCode.C_520010);
            }
            //减库存
            if (productMapper.cutSkuReserve(createOrderQuery) <= 0) {
                throw new DBException(ResponseCode.C_520012);
            }
            //计算运费
            ExpressPriceResult dbExpressPrice = createDBExpressPrice(createOrderQuery, addressId);
            //无法配送报错
            if (!dbExpressPrice.getCanDelivery()){
                throw new DBException(ResponseCode.C_520014);
            }
            //计算总价
            BigDecimal sumPrice = sku.getFixedPrice().multiply(new BigDecimal(createOrderQuery.getNumber()));
            //创建订单
            AppOrder order = new AppOrder();
            order.setParentOrder(OrderEnum.OrderType.CHILD.key());
            order.setParentOrderNumber(parentOrderNum);
            order.setOrderNum(childOrderNum);
            order.setUserId(userId);
            order.setCreateTime(LocalDateTime.now());
            order.setProductId(createOrderQuery.getProductId());
            order.setSkuId(createOrderQuery.getSkuId());
            order.setSku(sku.getSku());
            order.setProductSize(createOrderQuery.getNumber());
            order.setProductPrice(sku.getFixedPrice());
            order.setProductSumPrice(sumPrice);
            order.setProductName(product.getName());
            order.setExpressPrice(BigDecimal.valueOf(dbExpressPrice.getExpressPrice()));
            orderMapper.insert(order);
            return order;
        } else {
            throw new DBException(ResponseCode.C_520010);
        }
    }


    /**
     * 计算每一个商品的价格
     *
     * @param createOrderQuery
     * @return
     */
    private ComputerOrderResult computerOrderPrice(ComputerProductPriceQuery createOrderQuery) {
        AppProductSku sku = skuMapper.selectById(createOrderQuery.getSkuId());
        AppProduct product = productMapper.selectById(createOrderQuery.getProductId());
        if (sku != null && product != null) {
            int skuReserve = productMapper.findSkuReserve(createOrderQuery.getSkuId());
            int skuEffective = productMapper.countSkuEffective(createOrderQuery.getSkuId());
            //计算总价
            BigDecimal sumPrice = sku.getFixedPrice().multiply(new BigDecimal(createOrderQuery.getNumber()));
            ComputerOrderResult result = ComputerOrderResult.builder()
                    .number(createOrderQuery.getNumber())
                    .productId(createOrderQuery.getProductId())
                    .productImg(product.getMainImg())
                    .productName(product.getName())
                    .productPrice(sku.getFixedPrice().longValue())
                    .productSumPrice(sumPrice.longValue())
                    .sku(sku.getSku())
                    .skuId(sku.getId())
                    .cartId(createOrderQuery.getCartId())
                    .build();
            //下架商品
            if (product.getStatus() == ProductEnum.ShelfStatus.OBTAINED.key()) {
                result.setProductSkuStatus(ProductEnum.AppDisplayType.PRODUCT_OBTAINED.key());
                //SKU失效
            } else if (skuEffective == 0) {
                result.setProductSkuStatus(ProductEnum.AppDisplayType.SKU_INVALID.key());
                //库存为0
            } else if (skuReserve == 0) {
                result.setProductSkuStatus(ProductEnum.AppDisplayType.NO_RESERVE.key());
                //不足抵扣
            } else if (skuReserve < createOrderQuery.getNumber()) {
                result.setProductSkuStatus(ProductEnum.AppDisplayType.SHORT_RESERVE.key());
                //正常
            } else {
                result.setProductSkuStatus(ProductEnum.AppDisplayType.OK.key());
            }
            //运费模式
            result.setExpressType(this.getExpressType(product).key());
            return result;
        } else {
            throw new DBException(ResponseCode.C_520010);
        }

    }

    /**
     * 获取邮寄类型
     *
     * @param product
     * @return 邮寄类型参考美剧
     */
    private ExpressEnum.DisplayType getExpressType(AppProduct product) {
        ExpressEnum.DisplayType expressType = ExpressEnum.DisplayType.MERCHANT;
        if (product.getShipType() == ProductEnum.ShipType.NO.key()) {
            expressType = ExpressEnum.DisplayType.NO;
        } else {
            AppExpressTemplate appExpressTemplate = expressTemplateMapper.selectById(product.getExpressTemplateId());
            if (appExpressTemplate.getType() == ExpressEnum.Type.USER.key()) {
                expressType = ExpressEnum.DisplayType.USER;
            }
        }
        return expressType;
    }


    /**
     * 创建订单详情
     *
     * @param appOrder
     * @param userId
     * @param addressId
     */
    private void createOrderDetail(AppOrder appOrder, Integer userId, Integer addressId) {
        AddressDto pointAddress = addressMapper.findPointAddress(userId, addressId);
        AppOrderDetail orderDetail = new AppOrderDetail();
        orderDetail.setOrderId(appOrder.getId());
        orderDetail.setProvince(pointAddress.getProvinceName());
        orderDetail.setCity(pointAddress.getCityName());
        orderDetail.setZone(pointAddress.getZoneName());
        orderDetail.setDetail(pointAddress.getDetail());
        orderDetail.setMobile(pointAddress.getReceiptUserMobile());
        orderDetail.setPerName(pointAddress.getReceiptUserName());
        orderDetailMapper.insert(orderDetail);
    }


    @Override
    public String ali(String orderNum)  {
        //客户端配置
        AlipayClient alipayClient = new
                DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do",
                "2016100100641010",
                "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCYAmoyS7oJCuPCU3dc2dI3BFNw/FyKQu8WxpiNe7/lFlgv+0VHsCP4ROelFpv0JG+EhjyWQ2IJ4SNep1MPlN1J+vhESUUSzb+GztaRC7tuWJN6FjpYzBFy8EzwLLiLqym7SXpvP8AALQt++gGL740X9SzaP21ZS6w8A/p591eyVWjpdmKTKFl1oRN0cxeF6ruRouRumVPT18Lah14y9p+c8JQV6Rsr4f9qkaib01U44Ikk6ifXfQJnSxSIB8cWetM6YOn1Ce3pOPYMtGbPQVAvFFjrG/kM32JmbUxn0Pg//RcpCVpHHwtZ5MUGai5h4ri/W2ufZNPR0+8f2RMIp1rRAgMBAAECggEAbr3roiK7MJVHYq4uGm3yEPTM2VZxEKU8y8n5H7IVFg+WRbZ4PYbcXaXNYN881LuqptCo2QvUI8BqZS3ffE2GfbwiHJWIhe9w5gRQGzGB5h0Pq/T/outgluzsEFl0RQR4jaI1WVEHTtmCM4ptkAYAM4EBoSpuPoy7uKlpVGvW7urcmu25aK31nN077cLw7UkLi/6OVY3BgULoMTZJtM27XbxSXvH5xlH90Pmp24yk7w9kg0oxmBKG5tG+IQX4gY7lD8T7QWED0XNdl+6B1GS0L7knuSlXGVbGBO+Mfq32SwdWI9T9t3jusLCF1yjP0VyXcgYEsGs7CVT4dlGnC6SaAQKBgQDOphRzzDo1tujVdbs7ynmmTDqiIXJc1MN82UEJLlrXUQxt1X53PtT4wI9WgDJoZIgCfGQFs7wLRWsq7lfCRMSoYyY6MPb2RIsHI/aewUPP00PwLJwCOjDFHO3OU/FbkHK8Uzln0BffXZ8g6mduSQZ8Q7TR7J4c2KLZrrE9qvvXYQKBgQC8T9d2Qz9To0B+EDmorP3lY87nZCNJS3uevLBr5Xm3xjDKabje6kT0VQxpnkx9bxYMD+FDcMPRZ5tk5ChGtS3gmPI19EZ/rOtn/7SZEy7X1Zx/vqDR6fgZ/8qamI35IPzfIxzoYHvDgHCQnvQc3GoRb16m1Ru+0JIQZn2GKjbpcQKBgQCOqJK3+kY5LMOCEVunhDkE990Gbmaf9XXbK7Q9HVYzDM2yyU8cDTFDZNPPWIL0FGrcpHj8gr8PXOYF5jKJd6X3xTLvuzvSeTEtRcOMh0lEIMnm9TTiSL/AZB87cDHQD2FV53Ja24qPdfdT5t5ugVVL1gXvYhUpPtj1xCaxtudTIQKBgDi/vujO3zivS/IxpY6lg2Yrq+CjltaxvrFZn7kEUKgHPBDjx2i2JM2VC8aQg/CRF43PA021n+1tUbEX6ONWGltC/EsyMEStscuHjb9LvqQgpacBpalc3v3GziHEs5ROUqZUCJQm72U9Qa7zmBFSg0x5yYZxVokshbgXiuBxvFahAoGAWhf466rYmleRMQ/Oz57tG6j4czBzwiW1gRmuLbADCqXSoFBaePEHgpMO+XnyRG/zbAVXYxkkGxASc6MmfF0da7MVeUoYz1vlfPkBVMXPn3KeW2GJWcumPKJmfgLKAXUkLnrVt9ZutHgJ5HXu9NY26owLQ971qNmZPwwy2+Bvf8c=",
                "json", "utf-8",
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmAJqMku6CQrjwlN3XNnSNwRTcPxcikLvFsaYjXu/5RZYL/tFR7Aj+ETnpRab9CRvhIY8lkNiCeEjXqdTD5TdSfr4RElFEs2/hs7WkQu7bliTehY6WMwRcvBM8Cy4i6spu0l6bz/AAC0LfvoBi++NF/Us2j9tWUusPAP6efdXslVo6XZikyhZdaETdHMXheq7kaLkbplT09fC2odeMvafnPCUFekbK+H/apGom9NVOOCJJOon130CZ0sUiAfHFnrTOmDp9Qnt6Tj2DLRmz0FQLxRY6xv5DN9iZm1MZ9D4P/0XKQlaRx8LWeTFBmouYeK4v1trn2TT0dPvH9kTCKda0QIDAQAB",
                "RSA2");

        //沙箱请求配置
        AlipayTradePayRequest request = new AlipayTradePayRequest();
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        PayDto payDto = orderMapper.getPayDto(orderNum);
        //描述标题
        model.setSubject(payDto.getProductTitle());
        //唯一 订单号  父订单
        model.setOutTradeNo(payDto.getOrderNum());
        //请求超时时间
        model.setTimeoutExpress("30m");
        //支付金额
        model.setTotalAmount(payDto.getTotalAmount());
        //销售产品码，商家和支付宝签约的产品码
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        AlipayTradePayResponse response = null;
        try {
            response = alipayClient.sdkExecute(request);
        } catch (AlipayApiException e) {
            throw new ResponseException("支付失败1");
        }
        if (response.isSuccess()) {
            //todo 更改数据库表 支付时间 支付金额  使用支付成功回调接口
            return response.getBody();
        } else {
            throw new ResponseException("支付失败2");
        }
    }
}
