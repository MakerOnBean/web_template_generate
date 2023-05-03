package cloud.makeronbean.generate.starter.web;

import cloud.makeronbean.generate.starter.base.BaseStarter;

/**
 * @author makeronbean
 * @createDate 2023-05-02  21:49
 * @description
 */
public class SpringBootWebStarter extends BaseStarter {
    
    static Config config = new Config();
    
    @Override
    public void load() {
        this.dependency = new SpringBootWebDependency();
        this.yaml =  new SpringBootWebYaml();
        this.code = new SpringBootWebCode();
        this.order = 2;
    }
    
    public SpringBootWebStarter serverPort(Integer port) {
        config.serverPort = port;
        return this;
    }
    
    public SpringBootWebStarter cors(boolean open) {
        config.cors = open;
        return this;
    }
    
    public SpringBootWebStarter asserts(boolean open) {
        config.asserts = open;
        return this;
    }
    
    public SpringBootWebStarter valid(boolean open) {
        config.valid = open;
        return this;
    }
    
    static class Config {
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
    
        public Integer getServerPort() {
            return serverPort;
        }
    
        public boolean isCors() {
            return cors;
        }
    
        public boolean isAssert() {
            return asserts;
        }
    
        public boolean isValid() {
            return valid;
        }
        
        
    }
}
