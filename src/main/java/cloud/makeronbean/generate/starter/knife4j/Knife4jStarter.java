package cloud.makeronbean.generate.starter.knife4j;

import cloud.makeronbean.generate.starter.base.BaseStarter;
import cloud.makeronbean.generate.utils.ProjectInfoUtils;

/**
 * @author makeronbean
 * @createDate 2023-05-03  13:34
 * @description
 */
public class Knife4jStarter extends BaseStarter {
    
    static Config config = new Config();
    
    @Override
    public void load() {
        this.dependency = new Knife4jDependency();
        this.code = new Knife4jCode();
        this.order = 3;
    }
    
    public Knife4jStarter version(String version) {
        config.version = version;
        return this;
    }
    
    public Knife4jStarter title(String title) {
        config.title = title;
        return this;
    }
    
    public Knife4jStarter description(String description) {
        config.description = description;
        return this;
    }
    
    static class Config {
        private String version = "2.0.7";
        
        private String title = ProjectInfoUtils.artifactId;
        
        private String description = "swagger-bootstrap-ui-demo RESTful APIs";
        
        public String getVersion() {
            return version;
        }
        
        public String getTitle() {
            return title;
        }
        
        public String getDescription() {
            return description;
        }
    }
}
