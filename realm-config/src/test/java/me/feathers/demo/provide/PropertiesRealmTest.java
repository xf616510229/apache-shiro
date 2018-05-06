package me.feathers.demo.provide;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.text.PropertiesRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * PropertiesRealm
 * <p>
 *
 * @author Feathers
 * @date 2018-05-06 0:48
 */
public class PropertiesRealmTest {

    @Test
    public void testPropertiesRealm() {
        // 创建一个propertiesRealm
        PropertiesRealm realm = new PropertiesRealm();
        // 在初始化之前需要设置properties文件路径
        realm.setResourcePath("classpath:shiro.properties");
        // 初始化
        realm.onInit();

        SecurityManager sm = new DefaultSecurityManager(realm);
        SecurityUtils.setSecurityManager(sm);

        Subject user = SecurityUtils.getSubject();
        user.login(new UsernamePasswordToken("Feathers", "123456"));
        user.checkRoles("admin", "user");
        user.checkPermission("user:delete");
    }
}
