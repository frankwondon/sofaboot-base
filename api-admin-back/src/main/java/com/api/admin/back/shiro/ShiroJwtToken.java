package com.api.admin.back.shiro;

import org.apache.shiro.authc.AuthenticationToken;

public class ShiroJwtToken  implements AuthenticationToken {
    private String token;
    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
