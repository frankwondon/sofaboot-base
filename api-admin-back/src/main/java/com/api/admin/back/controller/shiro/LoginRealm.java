package com.api.admin.back.controller.shiro;

import com.module.admin.back.service.user.BackUserService;
import com.module.common.model.BackUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class LoginRealm extends AuthorizingRealm {
    private BackUserService backUserService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        BackUser user = (BackUser) principalCollection.getPrimaryPrincipal();
        user.getId();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token= (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        BackUser user = backUserService.getByAccount(username);
        return new SimpleAuthenticationInfo(user, user.getEncryptPwd(), ByteSource.Util.bytes(user.getSalt()), this.getName());
    }
}
