package me.feathers.demo.provide;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

/**
 * IniRealmDemo
 * IniRealm 使用示例 编程式
 * <p>
 *
 * @author Feathers
 * @date 2018-04-30 21:47
 */
public class IniRealmDemo {

    public static void main(String[] args) {
        IniRealm realm = new IniRealm("classpath:shiro.ini");
        SecurityManager sm = new DefaultSecurityManager(realm);
        SecurityUtils.setSecurityManager(sm);
        Subject user = SecurityUtils.getSubject();
        user.login(new UsernamePasswordToken("zhangsan", "123456"));
    }
}
