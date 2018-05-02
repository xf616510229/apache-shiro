package me.feathers.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

        //4. 获取正在执行的用户
        Subject currentUser = SecurityUtils.getSubject();

        //5. 获取用户的会话
        Session session = currentUser.getSession();
        session.setAttribute("key", "value");

        //**********************用户认证
        //6. 判断用户是否登陆，如果没有则登陆
        if (!currentUser.isAuthenticated()) {
            //准备token（这里是用户名和密码）
            UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123");
            //使用remember me功能，下次就不必登陆了
            token.setRememberMe(true);
            //登陆，如果失败会抛出异常
            try {
                currentUser.login(token);
                logger.info("用户{}登陆成功", currentUser.getPrincipal());
            } catch (UnknownAccountException e) {
                logger.error("找不到账户", e);
            } catch (IncorrectCredentialsException e) {
                logger.error("密码错误", e);
            } catch (LockedAccountException e) {
                logger.error("账户已经被锁定", e);
            }
        }

        //**********************用户授权
        //7. 判断用户是否有特定的角色
        if (currentUser.hasRole("teacher") || currentUser.hasRole("boss")) {
            logger.info("欢迎您，老师");
        } else {
            logger.info("对不起，你没有该权限");
        }

        //8. 判断用户是否有权限在一个确定的实体上操作
        if (currentUser.isPermitted("homework:add")) {
            logger.info("请您添加新的家庭作业");
        } else {
            logger.info("对不起！您没有权限添加作业");
        }

        //8. 注销登陆
        currentUser.logout();

        //9. 退出后，再次认证状态
        System.out.println("操作退出，当前认证状态为" + currentUser.isAuthenticated());
    }
}
