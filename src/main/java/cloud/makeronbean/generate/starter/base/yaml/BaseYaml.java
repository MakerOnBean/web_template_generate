package cloud.makeronbean.generate.starter.base.yaml;

import java.util.HashMap;
import java.util.Map;

/**
 * @author makeronbean
 * @createDate 2023-05-02  21:43
 * @description
 */

public abstract class BaseYaml {
    
    public BaseYaml() {
        getDefault();
    }
    
    private final Map<String, Object> configMap = new HashMap<>();
    
    
    /**
     * 填充配置map
     */
    protected void addConfig(String key, Object value) {
        configMap.put(key, value);
    }
    
    
    /**
     * 获取配置map
     */
    public Map<String, Object> getConfigMap() {
        return this.configMap;
    }
    
    /**
     * 填充map
     */
    protected abstract void getDefault();
    
}
