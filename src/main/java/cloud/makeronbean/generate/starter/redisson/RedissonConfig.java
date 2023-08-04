package cloud.makeronbean.generate.starter.redisson;

import cloud.makeronbean.generate.project.Project;
import cloud.makeronbean.generate.project.VersionHolder;

/**
 * @author makeronbean
 * @createDate 2023-05-04  09:18
 * @description
 */
public class RedissonConfig {
    private String host = "localhost";

    private Integer port = 6379;

    private Integer database = 0;

    private String version;

    private String password;

    String getHost() {
        return host;
    }

    void setHost(String host) {
        this.host = host;
    }

    Integer getPort() {
        return port;
    }

    void setPort(Integer port) {
        this.port = port;
    }

    Integer getDatabase() {
        return database;
    }

    void setDatabase(Integer database) {
        this.database = database;
    }

    String getVersion() {
        return version;
    }

    void setVersion(String version) {
        this.version = version;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
