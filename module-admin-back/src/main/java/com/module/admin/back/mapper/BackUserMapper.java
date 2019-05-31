package com.module.admin.back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.admin.back.result.BackUserResult;
import com.module.admin.back.entity.BackUser;
import com.module.admin.back.query.BackUserQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * back_user  Mapper 接口
 * </p>
 *
 * @author wangdong
 * @since 2019-05-20
 */
@Repository
public interface BackUserMapper extends BaseMapper<BackUser> {


    /**
     * 查询用户列表
     */
    IPage<BackUserResult> listBackUser(Page page, BackUserQuery query);

    /**
     * 根据手机号查询用户
     */
    BackUser selectByAccount(String account);

    /**
     * 分配用户角色
     * @param uid
     * @param role
     * @return
     */
    int  updateUserRole(@Param("uid") Integer uid, @Param("roleId") Integer role);

    int  countUserPhone(@Param("uid")Integer uid,@Param("phone")String phone);

    int countUserName(@Param("uid")Integer uid,@Param("username")String username);

    BackUserResult findById(Integer uid);

    int updateLockedUser(@Param("uid") Integer uid,@Param("locked") Integer locked);

    int updatePwd(BackUser user);
}
