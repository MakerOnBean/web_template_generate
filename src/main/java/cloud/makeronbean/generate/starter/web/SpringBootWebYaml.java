package cloud.makeronbean.generate.starter.web;

import cloud.makeronbean.generate.starter.base.yaml.BaseYaml;

/**
 * @author makeronbean
 * @createDate 2023-05-02  21:47
 * @description
 */

public class SpringBootWebYaml extends BaseYaml {
    
    
    @Override
    protected void getDefault() {
        this.addConfig("server.port", SpringBootWebStarter.config.getServerPort());
        this.addConfig("spring.mvc.format.date", "yyyy-MM-dd");
        this.addConfig("spring.mvc.format.date-time", "yyyy-MM-dd HH:mm:ss");
        this.addConfig("spring.jackson.date-format", "yyyy-MM-dd");
        this.addConfig("spring.jackson.time-zone", "Asia/Shanghai");
    }
}
