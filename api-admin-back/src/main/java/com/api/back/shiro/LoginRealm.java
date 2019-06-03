package com.api.back.shiro;

import com.module.admin.back.entity.BackUser;
import com.module.admin.back.service.BackUserService;
import com.module.common.bean.CurrentUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class LoginRealm extends AuthorizingRealm {

    private BackUserService backUserService;

    public LoginRealm(BackUserService backUserService) {
        this.backUserService = backUserService;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //todo 实现权限控制  在这里添加权限  在拦截器中实现验证功能
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token= (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        BackUser user = backUserService.getByAccount(username);
        CurrentUser currentUser=new CurrentUser();
        if (user!=null){
            if (user.getLocked()==1){
                throw new AuthenticationException("该用户已经被锁定");
            }
            currentUser.setRoleId(user.getRoleId());
            currentUser.setUsername(user.getUsername());
            currentUser.setId(user.getId());
            currentUser.setCellPhoneNum(user.getCellPhoneNum());
            currentUser.setUserType(user.getUserType());
        }else{
            throw new AuthenticationException("用户不存在");
        }
        //进行认证
        return new SimpleAuthenticationInfo(currentUser, user.getEncryptPwd(), ByteSource.Util.bytes(user.getSalt()), this.getName());
    }
}
