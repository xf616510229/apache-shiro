package me.feathers.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;
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

    @Test
    public void hello() {
        //1.构建SecurityManager环境
        DefaultSecurityManager sm = new DefaultSecurityManager();

        //2.配置安全信息源
        SimpleAccountRealm realm = new SimpleAccountRealm();
        realm.addAccount("Feathers", "123456", "admin");
        sm.setRealm(realm);

        //3.绑定SecurityManager到当前运行环境中，在当前运行环境中，SecurityManager是单例的
        SecurityUtils.setSecurityManager(sm);

        //4.获取主体（用户），提交认证请求
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("Feathers", "123456");
        try {
            subject.login(token);
        }  catch (UnknownAccountException e) {
            logger.error("找不到账户", e);
        } catch (IncorrectCredentialsException e) {
            logger.error("密码错误", e);
        } catch (LockedAccountException e) {
            logger.error("账户已经被锁定", e);
        }

        //5.获取认证结果
        logger.info("isAuthenticated: {}", subject.isAuthenticated());

        //6.权限验证
        try {
            subject.checkPermission("admin");
        } catch (UnauthenticatedException e) {
            logger.error("对不起，请您先登陆", e);
        } catch (UnauthorizedException e) {
            logger.error("对不起，您没有该权限");
        }
    }

}
