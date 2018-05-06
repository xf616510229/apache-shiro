package me.feathers.demo.provide;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * JdbcRealmTest
 * <p>
 *
 * @author Feathers
 * @date 2018-05-06 0:53
 */
public class JdbcRealmTest {

    @Test
    public void testJdbcRealm() {
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

        user.login(new UsernamePasswordToken("Feathers", "123456"));
    }

    @Test
    public void testJdbcRealmCustomSql() {
        JdbcRealm realm = new JdbcRealm();
        // 设置查询用户语句
        String authenticationQuery = new StringBuilder()
                .append(" select password ")
                .append(" from t_user ")
                .append(" where username = ? ")
                .toString();
        realm.setAuthenticationQuery(authenticationQuery);
        // 设置查询角色语句
        String userRoleQuery = new StringBuilder()
                .append("select tr.role_name ")
                .append(" from t_role tr ")
                .append(" left join t_user_role tur on tur.role_id = tr.id ")
                .append(" left join t_user tu on tur.user_id = tu.id ")
                .append(" where tu.username = ? ")
                .toString();
        realm.setUserRolesQuery(userRoleQuery);
        // 设置权限查询语句
        String permissionQuery = new StringBuilder()
                .append(" select tp.permission_name ")
                .append(" from t_permission tp ")
                .append("   left join t_role_permission trp on trp.permission_id = tp.id ")
                .append("   left join t_role tr on trp.role_id = tr.id ")
                .append("   left join t_user_role tur on tur.role_id = tr.id ")
                .append("   left join t_user tu on tur.user_id = tu.id ")
                .append("   where tu.username = ? ")
                .toString();
        realm.setPermissionsQuery(permissionQuery);

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

        user.login(new UsernamePasswordToken("Feathers", "123456"));
    }

}
