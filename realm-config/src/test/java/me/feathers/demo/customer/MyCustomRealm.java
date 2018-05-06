package me.feathers.demo.customer;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

import java.util.HashMap;
import java.util.Map;

/**
 * MyCustomRealm
 * 自定义Realm
 * <p>
 *
 * @author Feathers
 * @date 2018-05-06 21:22
 */
public class MyCustomRealm implements Realm {

    /**
     * 定义Realm名称
     *
     * @return name
     */
    @Override
    public String getName() {
        return "myCustomRealm";
    }

    /**
     * 是否支持此类型的Token, 只支持简单的用户名和密码验证
     *
     * @param token 令牌
     * @return true 支持 false 不支持
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    private Map<String, String> userInfo = new HashMap<>();

    {
        userInfo.put("Feathers", "123456");
    }

    /**
     * 获取认证信息
     *
     * @param token 令牌
     * @return 认证信息
     * @throws AuthenticationException 认证出错
     */
    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取 身份 用户名
        String principal = (String) token.getPrincipal();
        // 获取 凭证 密码
        String credentials = new String((char[])token.getCredentials());
        // 从数据库中查询是否有匹配，这里使用集合代替数据库查询
        String credentialsFromDb = userInfo.get(principal);
        // 如果credentialsFromDb==null 说明用户名错误
        if (credentialsFromDb == null) {
            throw new UnknownAccountException();
        }
        // 如果密码不一致，说明密码错误
        if (!credentials.equals(credentialsFromDb)) {
            throw new IncorrectCredentialsException();
        }
        return new SimpleAuthenticationInfo(principal, credentials, getName());
    }
}
