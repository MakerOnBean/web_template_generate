package cloud.makeronbean.generate.constant;

/**
 * @author makeronbean
 * @createDate 2023-05-01  11:01
 * @description 依赖常量类
 */
public enum DependencyConst {

    PROJECT("project"),

    GROUP_ID("groupId"),

    ARTIFACT_ID("artifactId"),

    VERSION("version"),

    SCOPE("scope"),

    OPTIONAL("optional"),

    PARENT("parent"),

    DEPENDENCY("dependencies/dependency"),

    PLUGIN("build/plugins/plugin"),

    ;

    private final String tabName;

    DependencyConst(String tabName) {
        this.tabName = tabName;
    }
    
    public String getTabName() {
        return tabName;
    }
}
