package com.api.admin.back.shiro;

import com.module.admin.back.service.user.BackUserService;
import com.module.common.model.BackUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class LoginRealm extends AuthorizingRealm {

    private BackUserService backUserService;

    public LoginRealm(BackUserService backUserService) {
        this.backUserService = backUserService;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        BackUser user = (BackUser) principalCollection.getPrimaryPrincipal();
        user.getId();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole("admin");
        simpleAuthorizationInfo.addRole("root");
        simpleAuthorizationInfo.addStringPermission("1:2:3");
        simpleAuthorizationInfo.addStringPermission("1:2:4");
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token= (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        BackUser user = backUserService.getByAccount(username);
//        return new SimpleAuthenticationInfo(user, user.getEncryptPwd(), ByteSource.Util.bytes(user.getSalt()), this.getName());
        return new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
    }
}
