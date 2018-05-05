package me.feathers.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.PropertiesRealm;
import org.apache.shiro.subject.Subject;

/**
 * PropertiesRealmDemo
 * PropertiesRealm 使用示例
 * <p>
 *
 * @author Feathers
 * @date 2018-04-30 21:48
 */
public class PropertiesRealmDemo {

    public static void main(String[] args) {
        PropertiesRealm realm = new PropertiesRealm();
        realm.setResourcePath("classpath:shiro.properties");
        SecurityManager sm = new DefaultSecurityManager(realm);
        SecurityUtils.setSecurityManager(sm);

        Subject user = SecurityUtils.getSubject();
        user.login(new UsernamePasswordToken("username", "password"));
        assert user.hasRole("role1");
    }
}
