package cloud.makeronbean.generate.starter.redisson;

import cloud.makeronbean.generate.starter.base.BaseStarter;

/**
 * @author makeronbean
 * @createDate 2023-05-03  15:15
 * @description
 */
public class RedissonStarter extends BaseStarter {
    
    static Config config = new Config();
    
    @Override
    public void load() {
        this.dependency = new RedissonDependency();
        this.yaml = new RedissonYaml();
    }
    
    public RedissonStarter host(String host) {
        config.host = host;
        return this;
    }
    
    public RedissonStarter port(Integer port) {
        config.port = port;
        return this;
    }
    
    public RedissonStarter database(Integer database) {
        if (database > 15) {
            System.out.println("The value of database ranges from 0 to 15");
            return this;
        }
        config.database = database;
        return this;
    }
    
    public RedissonStarter version(String version) {
        config.version = version;
        return this;
    }
    
    
    static class Config {
        private String host = "localhost";
        
        private Integer port = 6379;
        
        private Integer database = 0;
        
        private String version = "3.13.6";
    
        public String getHost() {
            return host;
        }
    
        public Integer getPort() {
            return port;
        }
    
        public Integer getDatabase() {
            return database;
        }
    
        public String getVersion() {
            return version;
        }
    
    }
}
