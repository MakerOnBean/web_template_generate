package cloud.makeronbean.generate.starter.redis;

/**
 * redis 配置类
 *
 * @author beanMak1r
 * @since 2023-08-01 07:50
 */
public class RedisConfig {
    private String host = "localhost";

    private Integer port = 6379;

    private Integer database = 0;

    private String password = null;

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

    String getPassword() {
        return password;
    }

    void setPassword(String password) {
        this.password = password;
    }
}
