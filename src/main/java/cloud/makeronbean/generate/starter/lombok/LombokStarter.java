package cloud.makeronbean.generate.starter.lombok;

import cloud.makeronbean.generate.starter.base.BaseStarter;

/**
 * @author makeronbean
 * @createDate 2023-05-03  13:31
 * @description
 */
public class LombokStarter extends BaseStarter {
    
    static Config config = new Config();
    
    @Override
    public void load() {
        this.dependency = new LombokDependency();
        this.order = 3;
    }
    
    public LombokStarter version(String version) {
        config.version = version;
        return this;
    }
    
    static class Config {
        private String version = "1.18.26";
    
        public String getVersion() {
            return version;
        }
    }
}
