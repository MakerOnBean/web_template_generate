package cloud.makeronbean.generate.starter.mybatisplus;

import cloud.makeronbean.generate.starter.base.BaseStarter;
import cloud.makeronbean.generate.utils.ProjectInfoUtils;

/**
 * @author makeronbean
 * @createDate 2023-05-03  12:58
 * @description
 */
public class MyBatisPlusStarter extends BaseStarter {
    
    static Config config = new Config();
    
    @Override
    public void load() {
        this.dependency = new MyBatisPlusDependency();
        this.yaml = new MyBatisPlusYaml();
        this.code = new MybatisPlusCode();
        this.order = 3;
    }
    
    public MyBatisPlusStarter mysqlVersion(String version) {
        config.mysqlVersion = version;
        return this;
    }
    
    public MyBatisPlusStarter username(String username) {
        config.username = username;
        return this;
    }
    
    
    public MyBatisPlusStarter password(String password) {
        config.password = password;
        return this;
    }
    
    public MyBatisPlusStarter dbName(String dbName) {
        config.dbName = dbName;
        return this;
    }
    
    public MyBatisPlusStarter port(Integer port) {
        config.port = port;
        return this;
    }
    
    public MyBatisPlusStarter logicDelete(boolean open) {
        config.logicDelete = open;
        return this;
    }
    
    static class Config {
        private String mysqlVersion = "8.0.30";
        
        private String username = "root";
        
        private String password = "123456";
        
        private Integer port = 3306;
        
        private String dbName = ProjectInfoUtils.artifactId;
        
        private boolean logicDelete = true;
        
        public String getMysqlVersion() {
            return mysqlVersion;
        }
        
        public String getUsername() {
            return username;
        }
        
        public String getPassword() {
            return password;
        }
        
        public String getDbName() {
            return dbName;
        }
        
        public Integer getPort() {
            return port;
        }
        
        public boolean isLogicDelete() {
            return logicDelete;
        }
    }
}
