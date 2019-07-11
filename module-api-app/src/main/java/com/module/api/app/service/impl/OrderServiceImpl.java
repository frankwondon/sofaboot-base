package com.module.api.app.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONPath;
import com.google.common.collect.Lists;
import com.lib.db.entity.AppOrder;
import com.module.api.app.dto.ExpressPriceResult;
import com.module.api.app.entity.*;
import com.module.api.app.mapper.*;
import com.module.api.app.query.CreateOrderQuery;
import com.module.api.app.query.ExpressPriceQuery;
import com.module.api.app.result.OrderResult;
import com.module.api.app.service.OrderService;
import com.module.common.ResponseCode;
import com.module.common.constant.ExpressEnum;
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
    private ProductSkuMapper skuMapper;
    @Resource
    private ExpressTemplateMapper expressTemplateMapper;
    @Resource
    private AppCartMapper appCartMapper;
    @Resource
    private AppExpressAddressMapper addressMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<OrderResult> createOrder(List<CreateOrderQuery> query,Integer userId) {
        List<OrderResult> orderResults = Lists.newArrayList();
        for (CreateOrderQuery createOrderQuery : query) {
            orderResults.add(this.createDBOrder(createOrderQuery,userId));
            //删除购物车
            if (createOrderQuery.getCartId() != null) {
                appCartMapper.deleteById(createOrderQuery.getCartId());
            }
        }
        return orderResults;
    }

    @Override
    public List<ExpressPriceResult> computeExpressPrice(ExpressPriceQuery query) {
        List<ExpressPriceResult> list=Lists.newArrayList();
        for (Integer orderId : query.getOrderIds()) {
            list.add(createDBExpressPrice(orderId, query.getAddressId()));
        }
        return list;
    }

    private ExpressPriceResult createDBExpressPrice(Integer orderId, Integer addressId) {
        AppExpressAddress appExpressAddress = addressMapper.selectById(addressId);
        if (appExpressAddress == null) {
            throw new DBException(ResponseCode.C_520015);
        }
        AppOrder order = orderMapper.selectById(orderId);
        AppProduct product = productMapper.selectById(order.getProductId());
        ExpressPriceResult priceResult = new ExpressPriceResult();

        BigDecimal expressPrice = BigDecimal.valueOf(0);
        if (product.getShipType() == ProductEnum.ShipType.NEED.key()) {
            AppExpressTemplate appExpressTemplate = expressTemplateMapper.selectById(product.getExpressTemplateId());
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
                    expressPrice = computerPrice(eArea, product, order.getProductSize(), priceRule);
                } else {
                    priceResult.setCanDelivery(false);
                }
            }
        }
        priceResult.setOrderId(orderId);
        priceResult.setProductId(order.getProductId());
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
        if (baseNum >= eArea.getFirstNum()) {
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
     * 创建订单
     *
     * @param createOrderQuery
     * @return
     */
    private OrderResult createDBOrder(CreateOrderQuery createOrderQuery,Integer userId) {
        AppProductSku sku = skuMapper.selectById(createOrderQuery.getSkuId());
        AppProduct product = productMapper.selectById(createOrderQuery.getProductId());
        if (sku != null && product != null) {
            //减库存
            if (productMapper.cutSkuReserve(createOrderQuery) <= 0) {
                throw new DBException(ResponseCode.C_520012);
            }
            if (product.getStatus() == ProductEnum.ShelfStatus.OBTAINED.key()) {
                throw new DBException(ResponseCode.C_520011);
            }
            //计算总价
            BigDecimal sumPrice = sku.getFixedPrice().multiply(new BigDecimal(createOrderQuery.getNumber()));
            //生成订单号
            String orderNum = orderMapper.callOrderNum();
            //创建订单
            AppOrder order = new AppOrder();
            order.setUserId(userId);
            order.setOrderNum(orderNum);
            order.setCreateTime(LocalDateTime.now());
            order.setProductId(createOrderQuery.getProductId());
            order.setSkuId(createOrderQuery.getSkuId());
            order.setSku(sku.getSku());
            order.setProductSize(createOrderQuery.getNumber());
            order.setProductPrice(sku.getFixedPrice());
            order.setProductSumPrice(sumPrice);
            order.setProductName(product.getName());
            orderMapper.insert(order);
            OrderResult result = new OrderResult();
            //生成返回结果
            int expressType = ExpressEnum.DisplayType.MERCHANT.key();
            if (product.getShipType() == ProductEnum.ShipType.NO.key()) {
                expressType = ExpressEnum.DisplayType.NO.key();
            } else {
                AppExpressTemplate appExpressTemplate = expressTemplateMapper.selectById(product.getExpressTemplateId());
                if (appExpressTemplate.getType() == ExpressEnum.Type.USER.key()) {
                    expressType = ExpressEnum.DisplayType.USER.key();
                }
            }

            return result.convertToResult(order, product, sku, expressType);
        } else {
            throw new DBException(ResponseCode.C_520010);
        }
    }


    public static void main(String[] args) {
        String json = "[{\"code\":120000,\"name\":\"天津\"},{\"code\":130000,\"name\":\"河北省\"},{\"code\":140000,\"name\":\"山西省\"}]";
        JSONArray array = JSONArray.parseArray(json);
        System.out.println(JSONPath.containsValue(array, "$.code", 120000));
        System.out.println(JSONPath.contains(array, "$.code"));
        ;
    }

}
