package com.module.admin.back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.module.admin.back.BackUserResult;
import com.module.admin.back.entity.BackUser;
import com.module.admin.back.query.BackUserQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    List<BackUserResult> listBackUser(BackUserQuery query);

    /**
     * 根据手机号查询用户
     */
    BackUser selectByAccount(String account);

}
