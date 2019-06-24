package com.module.admin.app.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.admin.app.entity.AppUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.module.admin.app.result.UserManagerResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * back_user  Mapper 接口
 * </p>
 *
 * @author wangdong
 * @since 2019-06-21
 */
@Repository
public interface AppUserMapper extends BaseMapper<AppUser> {
    /**
     * 分页查询用户列表
     * @param page
     * @param keyWord
     * @return
     */
    IPage<UserManagerResult> listOfUser(Page page, @Param("keyWord") String keyWord);

    /**
     * 禁用这个用户
     * @param id
     * @return
     */
    int disable(Integer id);
}
