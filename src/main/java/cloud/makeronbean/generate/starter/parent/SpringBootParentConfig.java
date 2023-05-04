package cloud.makeronbean.generate.starter.parent;

import cloud.makeronbean.generate.utils.ProjectInfoUtils;

/**
 * @author makeronbean
 * @createDate 2023-05-04  09:13
 * @description
 */
public class SpringBootParentConfig {
    private String yamlAppName = ProjectInfoUtils.artifactId;
    
    private String mainBootName = ProjectInfoUtils.artifactId.substring(0, 1).toUpperCase() + ProjectInfoUtils.artifactId.substring(1) + "Application";
    
    private boolean devtools = true;
    
    private boolean test = true;
    
    String getYamlAppName() {
        return yamlAppName;
    }
    
    void setYamlAppName(String yamlAppName) {
        this.yamlAppName = yamlAppName;
    }
    
    String getMainBootName() {
        return mainBootName;
    }
    
    void setMainBootName(String mainBootName) {
        this.mainBootName = mainBootName;
    }
    
    boolean isDevtools() {
        return devtools;
    }
    
    void setDevtools(boolean devtools) {
        this.devtools = devtools;
    }
    
    boolean isTest() {
        return test;
    }
    
    void setTest(boolean test) {
        this.test = test;
    }
}
