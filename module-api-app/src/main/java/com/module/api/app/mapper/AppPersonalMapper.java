package com.module.api.app.mapper;

import com.module.api.app.entity.AppUser;
import com.module.api.app.result.UserManagerResult;
import com.module.common.bean.AppCurrentUser;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName AppPersonalMapper
 * @Description TODO
 * @Author YJT
 * @Date 2019/7/10 0010 9:51
 * @Version 1.0
 **/
public interface AppPersonalMapper {


   /**
    * 个人信息
    * @param userId
    * @return
    */
   UserManagerResult getPersonalMsg(@Param("userId") Integer userId);

   /**
    * 修改昵称
    * @param user
    * @return
    */
   int updateUserName(@Param("user")AppCurrentUser user);

   /**
    * 修改头像
    * @param path
    * @param userId
    */
   void updateHeadImg(@Param("path") String path,@Param("userId") Integer userId);


   /**
    * 修改支付密码
    * @param cellPhoneNum
    * @param payWord
    * @return
    */
   int updateUserPayPwd(@Param("cellPhoneNum") String cellPhoneNum,@Param("payWord") String payWord);



}
