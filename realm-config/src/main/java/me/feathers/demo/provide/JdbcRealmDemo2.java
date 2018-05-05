package me.feathers.demo.provide;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;

import javax.sql.DataSource;

/**
 * JdbcRealmDemo
 * JdbcRealm 使用示例
 * <p>
 *
 * @author Feathers
 * @date 2018-04-30 21:50
 */
public class JdbcRealmDemo {

    public static void main(String[] args) {
        JdbcRealm realm = new JdbcRealm();
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/shiro");
        ds.setUsername("test");
        ds.setPassword("test");
        realm.setDataSource(ds);

        // 和PropertiesRealm一样，需要初始化，在初始化前，需要设置dataSources
        realm.init();

        SecurityManager sm = new DefaultSecurityManager(realm);
        SecurityUtils.setSecurityManager(sm);
        Subject user = SecurityUtils.getSubject();

        user.login(new UsernamePasswordToken("zhangsan", "123"));
    }

}
