package me.feathers.demo.provide;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.io.ResourceUtils;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.text.PropertiesRealm;
import org.apache.shiro.subject.Subject;

import java.io.IOException;
import java.io.InputStream;

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
        // 创建一个propertiesRealm
        PropertiesRealm realm = new PropertiesRealm();
        // 在初始化之前需要设置properties文件路径
        realm.setResourcePath("classpath:shiro.properties");
        // 初始化
        realm.onInit();

        SecurityManager sm = new DefaultSecurityManager(realm);
        SecurityUtils.setSecurityManager(sm);

        Subject user = SecurityUtils.getSubject();
        user.login(new UsernamePasswordToken("zhangsan", "123456"));
        assert user.hasRole("role1");
    }
}
