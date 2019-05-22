package com.api.admin.back.shiro;

import com.module.admin.back.entity.BackUser;
import com.module.admin.back.service.BackUserService;
import com.module.common.bean.CurrentUser;
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
        CurrentUser currentUser=new CurrentUser();
        if (user!=null){
            currentUser.setRole(user.getRole());
            currentUser.setId(user.getId());
            currentUser.setCellPhoneNum(user.getCellPhoneNum());
            currentUser.setUserType(user.getUserType());
        }else{
            throw new AuthenticationException("用户不存在");
        }
//        return new SimpleAuthenticationInfo(admin, admin.getEncryptPwd(), ByteSource.Util.bytes(admin.getSalt()), this.getName());
        return new SimpleAuthenticationInfo(currentUser, user.getPassword(), this.getName());
    }
}
