package me.feathers.demo.custom;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * MyRealm1
 * 自定义Realm
 * <p>
 *
 * @author Feathers
 * @date 2018-05-01 10:56
 */
public class MyRealm1 implements Realm {
    @Override
    public String getName() {
        return "myRealm1";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        //仅支持UsernamePasswordToken类型的Token
        return token instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取身份：用户名
        String username = (String) token.getPrincipal();
        //获取凭证：密码
        String password = new String((char[]) token.getCredentials());
        if (!"zhang".equals(username)) {
            throw new UnknownAccountException();
        }
        if (!"123".equals(password)) {
            throw new IncorrectCredentialsException();
        }
        //如果身份认证验证成功，返回一个AuthenticationInfo实现；
        return new SimpleAuthenticationInfo(username, password, getName());
    }
}
