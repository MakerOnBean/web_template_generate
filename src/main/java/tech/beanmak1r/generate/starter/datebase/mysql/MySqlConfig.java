package tech.beanmak1r.generate.starter.datebase.mysql;

import tech.beanmak1r.generate.project.Project;

/**
 * mysql 配置
 *
 * @author beanMak1r
 * @since 2023-08-01 11:21
 */
public class MySqlConfig {

    private String version;

    private String username = "root";

    private String password = "123456";

    private Integer port = 3306;

    private String ip = "localhost";

    private String dbName = Project.project().artifactId();

    String getVersion() {
        return version;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    String getDbName() {
        return dbName;
    }

    Integer getPort() {
        return port;
    }

    void setVersion(String mysqlVersion) {
        this.version = mysqlVersion;
    }

    void setUsername(String username) {
        this.username = username;
    }

    void setPassword(String password) {
        this.password = password;
    }

    void setPort(Integer port) {
        this.port = port;
    }

    void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
