package cloud.makeronbean.generate.constant;

/**
 * @author makeronbean
 * @createDate 2023-05-01  11:01
 * @description 依赖常量类
 */
public enum DependencyConst {
    
    PROJECT("project"),
    
    PARENT("parent"),
    
    DEPENDENCIES("dependencies"),
    
    DEPENDENCY("dependency"),
    
    GROUP_ID("groupId"),
    
    ARTIFACT_ID("artifactId"),
    
    VERSION("version"),
    
    SCOPE("scope"),
    
    OPTIONAL("optional"),
    
    BUILD("build"),
    
    PLUGINS("plugins"),
    
    PLUGIN("plugin"),
    
    ;
    
    private final String tabName;
    
    DependencyConst(String tabName) {
        this.tabName = tabName;
    }
    
    public String getTabName() {
        return tabName;
    }
}
