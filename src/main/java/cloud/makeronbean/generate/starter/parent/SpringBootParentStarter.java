package cloud.makeronbean.generate.starter.parent;

import cloud.makeronbean.generate.starter.base.BaseStarter;
import cloud.makeronbean.generate.utils.ProjectInfoUtils;

/**
 * @author makeronbean
 * @createDate 2023-05-03  11:43
 * @description
 */
public class SpringBootParentStarter extends BaseStarter {
    static Config config = new Config();
    
    
    @Override
    public void load() {
        this.dependency = new SpringBootParentDependency();
        this.yaml = new SpringBootParentYaml();
        this.code = new SpringBootParentCode();
        this.order = 1;
    }
    
    public SpringBootParentStarter yamlAppName(String yamlAppName) {
        config.yamlAppName = yamlAppName;
        return this;
    }
    
    public SpringBootParentStarter mainBootName(String mainBootName) {
        config.mainBootName = mainBootName;
        return this;
    }
    
    public SpringBootParentStarter devtools(boolean open) {
        config.devtools = open;
        return this;
    }
    
    public SpringBootParentStarter test(boolean open) {
        config.test = open;
        return this;
    }
    
    static class Config {
        private String yamlAppName = ProjectInfoUtils.artifactId;
        
        private String mainBootName = ProjectInfoUtils.artifactId.substring(0, 1).toUpperCase() + ProjectInfoUtils.artifactId.substring(1) + "Application";
        
        private boolean devtools = true;
        
        private boolean test = true;
        
        public String getYamlAppName() {
            return yamlAppName;
        }
        
        public String getMainBootName() {
            return mainBootName;
        }
    
        public boolean isDevtools() {
            return devtools;
        }
    
        public boolean isTest() {
            return test;
        }
    }
    
}
