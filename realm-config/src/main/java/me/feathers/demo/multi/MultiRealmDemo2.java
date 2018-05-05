package me.feathers.demo.multi;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.RealmFactory;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * MultiRealmDemo
 * 多Realm配置实例 编程式
 * <p>
 *
 * @author Feathers
 * @date 2018-05-01 11:12
 */
public class MultiRealmDemo {

    public static void main(String[] args) {

        //配置安全数据源 两个realm
        //1,
        IniRealm realm1 = new IniRealm("classpath:provide/shiro.ini");
        //2,
        JdbcRealm realm2 = new JdbcRealm();
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/shiro");
        ds.setUsername("test");
        ds.setPassword("test");
        realm2.setDataSource(ds);
        realm2.init();
        List<Realm> realms = new ArrayList<>();
        realms.add(realm1);
        realms.add(realm2);

        //创建SecurityManager
        DefaultSecurityManager sm = new DefaultSecurityManager(realms);

        SecurityUtils.setSecurityManager(sm);
        Subject user = SecurityUtils.getSubject();
        user.login(new UsernamePasswordToken("zhang", "123"));
    }

}
