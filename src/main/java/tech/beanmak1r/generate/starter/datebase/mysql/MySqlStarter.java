package tech.beanmak1r.generate.starter.datebase.mysql;

import tech.beanmak1r.generate.enums.TagsEnum;
import tech.beanmak1r.generate.project.VersionHolder;
import tech.beanmak1r.generate.starter.base.dependency.BaseDependency;
import tech.beanmak1r.generate.starter.base.dependency.DependencyItem;
import tech.beanmak1r.generate.starter.base.starter.StarterAdapter;
import tech.beanmak1r.generate.starter.base.yaml.BaseYaml;
import tech.beanmak1r.generate.starter.datebase.DatabaseConfig;
import tech.beanmak1r.generate.utils.StringUtils;

/**
 * mysql Starter
 *
 * @author beanMak1r
 * @since 2023-08-01 11:23
 */
public class MySqlStarter extends StarterAdapter {

    private final DatabaseConfig config = new DatabaseConfig();

    private MySqlStarter() {
        this.order = 3;
    }

    public MySqlStarter version(String version) {
        config.setVersion(version);
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

    public MySqlStarter ip(String ip) {
        config.setHost(ip);
        return this;
    }

    public DatabaseConfig getConfig() {
        return config;
    }

    @Override
    protected void addDependency(BaseDependency dependency) {
        DependencyItem mysql = new DependencyItem()
                .setPath(TagsEnum.DEPENDENCY)
                .addTag(TagsEnum.GROUP_ID, "mysql")
                .addTag(TagsEnum.ARTIFACT_ID, "mysql-connector-java")
                .addTag(TagsEnum.SCOPE, "runtime")
                .addTag(TagsEnum.VERSION, StringUtils.getVersion(config.getVersion(), VersionHolder.DependencyNameEnum.MYSQL));
        dependency.addDependencyItem(mysql);
    }

    @Override
    protected void addYaml(BaseYaml yaml) {
        yaml.addYamlConfig("spring.datasource.username", config.getUsername());
        yaml.addYamlConfig("spring.datasource.password", config.getPassword());
        yaml.addYamlConfig("spring.datasource.driver-class-name", "com.mysql.cj.jdbc.Driver");
        yaml.addYamlConfig("spring.datasource.url", String.format("jdbc:mysql://%s:%d/%s?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai", config.getHost(), config.getPort(), config.getDbName()));
    }
}
