package cloud.makeronbean.generate.starter.mybatisplus;

import cloud.makeronbean.generate.utils.ProjectInfoUtils;

/**
 * @author makeronbean
 * @createDate 2023-05-04  09:07
 * @description
 */
public class MyBaitsPlusConfig {
    private String mysqlVersion = "8.0.30";
    
    private String username = "root";
    
    private String password = "123456";
    
    private Integer port = 3306;
    
    private String dbName = ProjectInfoUtils.artifactId;
    
    private boolean logicDelete = true;
    
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
    
    boolean isLogicDelete() {
        return logicDelete;
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
    
    void setLogicDelete(boolean logicDelete) {
        this.logicDelete = logicDelete;
    }
}
