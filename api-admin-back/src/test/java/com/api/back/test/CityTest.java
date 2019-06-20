package com.api.back.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.module.admin.app.entity.BaseArea;
import com.module.admin.app.mapper.BaseAreaMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wangdong
 * @date: 2019/6/20 10:57
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CityTest {
    @Resource
    private BaseAreaMapper baseArea;
    @Test
    public void test(){
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("depth",1);
        List<BaseArea> baseAreas1 = baseArea.selectList(queryWrapper);
        QueryWrapper queryWrapper1=new QueryWrapper();
        queryWrapper.eq("depth",2);
        List<BaseArea> baseAreas2 = baseArea.selectList(queryWrapper1);
//        queryWrapper.eq("depth",3);
//        List<BaseArea> baseAreas3 = baseArea.selectList(queryWrapper);
        JSONArray jsonArray=new JSONArray();
        for (BaseArea area : baseAreas1) {
            JSONObject o=new JSONObject();
            o.put("code",area.getCode());
            o.put("name",area.getName());
            JSONArray jsonArray1=new JSONArray();
            for (BaseArea area1 : baseAreas2) {
                if (area.getCode().equals(area1.getParentCode())){
                    JSONObject o1=new JSONObject();
                    o1.put("code",area1.getCode());
                    o1.put("name",area1.getName());
                    jsonArray1.add(o1);
                }
            }
            o.put("children",jsonArray1);
            jsonArray.add(o);
        }

        System.out.println(jsonArray);
    }
}
