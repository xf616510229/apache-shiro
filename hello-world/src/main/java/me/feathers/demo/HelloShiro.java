package me.feathers.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.rmi.runtime.Log;

/**
 * HelloShiro
 * <p>
 *
 * @author Feathers
 * @date 2018-04-30 15:18
 */
public class HelloShiro {

    private static Logger logger = LoggerFactory.getLogger(HelloShiro.class);

    public static void main(String[] args) {
        //1. 根据ini构建SecurityManager工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-hello.ini");

        //2. 构建一个SecurityManager实例
        SecurityManager securityManager = factory.getInstance();

        //3. 将securityManager实例绑定到当前运行环境中
        SecurityUtils.setSecurityManager(securityManager);

        //4. 创建一个Subject
        Subject subject = SecurityUtils.getSubject();

        //5. 准备token（这里是用户名和密码）
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123");

        //6. 尝试进行验证,如果认证失败，则会抛出AuthenticationException异常
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            logger.error("验证失败", e);
        }

        //7. 获取认证状态
        System.out.println("认证是否成功："+subject.isAuthenticated());

        //8. 退出操作
        subject.logout();

        //9. 退出后，再次认证状态
        System.out.println("操作退出，当前认证状态为" + subject.isAuthenticated());
    }
}
