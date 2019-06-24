package com.module.api.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.module.api.app.dto.ExpressTemplateDto;
import com.module.api.app.entity.AppExpressTemplate;
import com.module.api.app.entity.AppExpressTemplateArea;
import com.module.api.app.mapper.AppExpressTemplateAreaMapper;
import com.module.api.app.mapper.AppExpressTemplateMapper;
import com.module.api.app.service.AppExpressTemplateService;
import com.module.common.ResponseCode;
import com.module.common.exception.DBOperationException;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author wangdong
 * @date: 2019/6/19 14:35
 */
public class AppExpressTemplateServiceImpl implements AppExpressTemplateService {

    @Resource
    private AppExpressTemplateMapper expressTemplateMapper;
    @Resource
    private AppExpressTemplateAreaMapper expressTemplateAreaMapper;


    @Override
    public List<ExpressTemplateDto> listTemplate() {
        List<AppExpressTemplate> appExpressTemplates = expressTemplateMapper.selectList(null);
        if (appExpressTemplates != null) {
            List<ExpressTemplateDto> result = new ArrayList<>();
            appExpressTemplates.forEach(t -> {
                ExpressTemplateDto dto = new ExpressTemplateDto();
                BeanUtils.copyProperties(t,dto);
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq("temp_id", t.getId());
                List<AppExpressTemplateArea> list = expressTemplateAreaMapper.selectList(queryWrapper);
                dto.setArea(list);
                result.add(dto);
            });
            return result;
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createTemplate(ExpressTemplateDto dto) {
        if (dto.getArea() == null) {
            throw new DBOperationException(ResponseCode.C_500003);
        }
        //运费模板
        expressTemplateMapper.insert(dto);
        //地区配置
        expressTemplateAreaMapper.insertList(dto.getArea(),dto.getId());
    }

    @Override
    public ExpressTemplateDto templateDetail(Integer productId){
        ExpressTemplateDto dto= (ExpressTemplateDto) expressTemplateMapper.selectById(productId);
        if (dto!=null){
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("temp_id", productId);
            List<AppExpressTemplateArea> list = expressTemplateAreaMapper.selectList(queryWrapper);
            dto.setArea(list);
            return dto;
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTemplate(ExpressTemplateDto dto) {
        if (dto.getArea() == null) {
            throw new DBOperationException(ResponseCode.C_500003);
        }
        if (expressTemplateMapper.countShelfProductUseTemplate(dto.getId())>0){
            throw new DBOperationException(ResponseCode.C_510003);
        }
        //更新运费模板
        expressTemplateMapper.updateById(dto);
        //先删除地区配置
        List<Integer> integers = expressTemplateAreaMapper.listIdsOfProduct(dto.getId());
        expressTemplateAreaMapper.deleteBatchIds(integers);
        //重新创建地区配置
        expressTemplateAreaMapper.insertList(dto.getArea(),dto.getId());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void  delTemplate(Integer tempId){
        if (expressTemplateMapper.countShelfProductUseTemplate(tempId)>0){
            throw new DBOperationException(ResponseCode.C_510004);
        }
        expressTemplateMapper.deleteById(tempId);
        //先删除地区配置
        List<Integer> integers = expressTemplateAreaMapper.listIdsOfProduct(tempId);
        expressTemplateAreaMapper.deleteBatchIds(integers);
    }
}
