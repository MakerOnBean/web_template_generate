package cloud.makeronbean.generate.starter.parent;

import cloud.makeronbean.generate.starter.base.yaml.BaseYaml;

/**
 * @author makeronbean
 * @createDate 2023-05-03  11:46
 * @description
 */
public class SpringBootParentYaml extends BaseYaml {
    
    
    @Override
    protected void getDefault() {
        this.addConfig("spring.application.name", SpringBootParentStarter.config.getYamlAppName());
    }
}
