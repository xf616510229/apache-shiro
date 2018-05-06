package me.feathers.demo.muti;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.AllSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.realm.text.PropertiesRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * MutiRealmTest
 * 多Realm测试
 * <p>
 *
 * @author Feathers
 * @date 2018-05-06 21:44
 */
public class MutiRealmTest {

    @Test
    public void testMutiRealm() {
        IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
        PropertiesRealm propertiesRealm = new PropertiesRealm();
        propertiesRealm.setResourcePath("classpath:shiro.properties");
        propertiesRealm.onInit();

        List<Realm> realms = new ArrayList<>();
        realms.add(iniRealm);
        realms.add(propertiesRealm);

        // 设置多Realm
        SecurityManager sm = new DefaultSecurityManager(realms);
        SecurityUtils.setSecurityManager(sm);

        // 设置多Realm策略
        ModularRealmAuthenticator authenticator = (ModularRealmAuthenticator) ((DefaultSecurityManager) sm).getAuthenticator();
        authenticator.setAuthenticationStrategy(new AllSuccessfulStrategy());

        Subject user = SecurityUtils.getSubject();
        user.login(new UsernamePasswordToken("Feathers", "123456"));
        user.checkRoles("admin", "user");
        user.checkPermission( "user:add");
    }
}
