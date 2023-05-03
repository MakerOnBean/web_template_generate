package cloud.makeronbean.generate.starter.base.dependency;

/**
 * @author makeronbean
 * @createDate 2023-05-02  21:34
 * @description
 */

public class DependencyItem {
    /**
     * groupId
     */
    protected String groupId;
    
    /**
     * artifactId
     */
    protected String artifactId;
    
    /**
     * version
     */
    protected String version;
    
    /**
     * scope
     */
    protected String scope;
    
    /**
     * optional
     */
    protected String optional;
    
    /**
     * 标签名称
     */
    protected String tabName;
    
    /**
     * 父节点名称
     */
    protected String parentNodeName;
    
    public String getGroupId() {
        return groupId;
    }
    
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    
    public String getArtifactId() {
        return artifactId;
    }
    
    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }
    
    public String getVersion() {
        return version;
    }
    
    public void setVersion(String version) {
        this.version = version;
    }
    
    public String getScope() {
        return scope;
    }
    
    public void setScope(String scope) {
        this.scope = scope;
    }
    
    public String getOptional() {
        return optional;
    }
    
    public void setOptional(String optional) {
        this.optional = optional;
    }
    
    public String getTabName() {
        return tabName;
    }
    
    public void setTabName(String tabName) {
        this.tabName = tabName;
    }
    
    public String getParentNodeName() {
        return parentNodeName;
    }
    
    public void setParentNodeName(String parentNodeName) {
        this.parentNodeName = parentNodeName;
    }
}
