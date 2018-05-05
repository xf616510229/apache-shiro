package me.feathers.demo.custom;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

/**
 * MyRealm1
 * 自定义Realm的使用
 * <p>
 *
 * @author Feathers
 * @date 2018-05-01 10:56
 */
public class MyRealm1Demo {

    public static void main(String[] args) {
        MyRealm1 myRealm1 = new MyRealm1();
        SecurityManager sm = new DefaultSecurityManager(myRealm1);
        SecurityUtils.setSecurityManager(sm);
        Subject user = SecurityUtils.getSubject();
        user.login(new UsernamePasswordToken("zhangsan", "123"));
    }
}
