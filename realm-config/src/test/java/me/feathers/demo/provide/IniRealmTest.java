package me.feathers.demo.provide;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * IniRealmTester
 * <p>
 *
 * @author Feathers
 * @date 2018-05-06 0:37
 */
public class IniRealmTest {

    @Test
    public void testIniRealm() {
        IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
        SecurityManager sm = new DefaultSecurityManager(iniRealm);
        SecurityUtils.setSecurityManager(sm);
        Subject user = SecurityUtils.getSubject();
        user.login(new UsernamePasswordToken("Feathers", "123456"));
        user.checkRoles("admin", "user");
        user.checkPermission( "user:add");
    }

}
