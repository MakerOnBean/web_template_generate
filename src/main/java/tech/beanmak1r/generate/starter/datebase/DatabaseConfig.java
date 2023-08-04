package tech.beanmak1r.generate.starter.datebase;

import tech.beanmak1r.generate.project.Project;

/**
 * 数据库配置类
 *
 * @author beanMak1r
 * @since 2023-08-04 10:53
 */
public class DatabaseConfig {
    private String version;

    private String host = "localhost";

    private String username = "root";

    private String password = "123456";

    private Integer port = 3306;

    private String dbName = Project.project().artifactId();

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
}
