package me.feathers.demo.customer;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * MyCustomRealmTest
 * <p>
 *
 * @author Feathers
 * @date 2018-05-06 21:22
 */
public class MyCustomRealmTest {


    @Test
    public void testMyCustomRealm() {
        MyCustomRealm realm = new MyCustomRealm();
        SecurityManager sm = new DefaultSecurityManager(realm);
        SecurityUtils.setSecurityManager(sm);
        Subject user = SecurityUtils.getSubject();
        user.login(new UsernamePasswordToken("Feathers", "123456"));
    }
}
