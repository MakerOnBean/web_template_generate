package cloud.makeronbean.generate.starter.mysql;

import cloud.makeronbean.generate.constant.DependencyConst;
import cloud.makeronbean.generate.starter.base.dependency.BaseDependency;
import cloud.makeronbean.generate.starter.base.dependency.DependencyItem;
import cloud.makeronbean.generate.starter.base.starter.StarterAdapter;
import cloud.makeronbean.generate.starter.base.yaml.BaseYaml;

/**
 * mysql Starter
 *
 * @author beanMak1r
 * @since 2023-08-01 11:23
 */
public class MySqlStarter extends StarterAdapter {

    private final MySqlConfig config = new MySqlConfig();

    private MySqlStarter() {
        this.order = 3;
    }

    public MySqlStarter mysqlVersion(String version) {
        config.setMysqlVersion(version);
        return this;
    }

    public MySqlStarter username(String username) {
        config.setUsername(username);
        return this;
    }


    public MySqlStarter password(String password) {
        config.setPassword(password);
        return this;
    }

    public MySqlStarter dbName(String dbName) {
        config.setDbName(dbName);
        return this;
    }

    public MySqlStarter port(Integer port) {
        config.setPort(port);
        return this;
    }


    @Override
    protected void addDependency(BaseDependency dependency) {
        DependencyItem mysql = new DependencyItem()
                .setPath(DependencyConst.DEPENDENCY)
                .addTag(DependencyConst.GROUP_ID, "mysql")
                .addTag(DependencyConst.ARTIFACT_ID, "mysql-connector-java")
                .addTag(DependencyConst.VERSION, config.getMysqlVersion())
                .addTag(DependencyConst.SCOPE, "runtime");
        dependency.addDependencyItem(mysql);
    }

    @Override
    protected void addYaml(BaseYaml yaml) {
        yaml.addYamlConfig("spring.datasource.username", config.getUsername());
        yaml.addYamlConfig("spring.datasource.password", config.getPassword());
        yaml.addYamlConfig("spring.datasource.driver-class-name", "com.mysql.cj.jdbc.Driver");
        yaml.addYamlConfig("spring.datasource.url", String.format("jdbc:mysql://localhost:%d/%s?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai", config.getPort(), config.getDbName()));
    }
}
