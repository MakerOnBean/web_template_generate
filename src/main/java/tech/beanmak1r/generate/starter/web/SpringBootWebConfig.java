package tech.beanmak1r.generate.starter.web;

/**
 * @author makeronbean
 * @createDate 2023-05-04  09:22
 * @description
 */
public class SpringBootWebConfig {
    /**
     * 端口号
     */
    private Integer serverPort = 8080;
    
    /**
     * 是否配置跨域
     */
    private boolean cors = true;
    
    /**
     * 是否开启断言
     */
    private boolean asserts = true;
    
    /**
     * 是否开启参数校验
     */
    private boolean valid = true;
    
    Integer getServerPort() {
        return serverPort;
    }
    
    boolean isCors() {
        return cors;
    }
    
    boolean isAssert() {
        return asserts;
    }
    
    boolean isValid() {
        return valid;
    }
    
    void setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
    }
    
    void setCors(boolean cors) {
        this.cors = cors;
    }
    
    void setAsserts(boolean asserts) {
        this.asserts = asserts;
    }
    
    void setValid(boolean valid) {
        this.valid = valid;
    }
}
