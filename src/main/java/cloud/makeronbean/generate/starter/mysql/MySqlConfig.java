package cloud.makeronbean.generate.starter.mysql;

import cloud.makeronbean.generate.utils.ProjectInfoUtils;

/**
 * mysql 配置
 *
 * @author beanMak1r
 * @since 2023-08-01 11:21
 */
public class MySqlConfig {

    private String mysqlVersion = "8.0.30";

    private String username = "root";

    private String password = "123456";

    private Integer port = 3306;

    private String dbName = ProjectInfoUtils.artifactId;

    String getMysqlVersion() {
        return mysqlVersion;
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

    void setMysqlVersion(String mysqlVersion) {
        this.mysqlVersion = mysqlVersion;
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
}
