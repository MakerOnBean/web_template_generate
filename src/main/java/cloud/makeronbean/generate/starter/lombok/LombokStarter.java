package cloud.makeronbean.generate.starter.lombok;

import cloud.makeronbean.generate.constant.DependencyConst;
import cloud.makeronbean.generate.starter.base.starter.StarterAdapter;
import cloud.makeronbean.generate.starter.base.dependency.BaseDependency;
import cloud.makeronbean.generate.starter.base.dependency.DependencyItem;

/**
 * @author makeronbean
 * @createDate 2023-05-03  13:31
 * @description
 */
public class LombokStarter extends StarterAdapter {
    
    LombokConfig config = new LombokConfig();
    
    public LombokStarter version(String version) {
        config.setVersion(version);
        return this;
    }
    
    @Override
    protected void addDependency(BaseDependency dependency) {
        DependencyItem lombok = new DependencyItem();
        lombok.setGroupId("org.projectlombok");
        lombok.setTabName(DependencyConst.DEPENDENCY.getTabName());
        lombok.setArtifactId("lombok");
        lombok.setScope("provided");
        lombok.setVersion(config.getVersion());
        lombok.setParentNodeName(DependencyConst.DEPENDENCIES.getTabName());
        dependency.addDependencyItem(lombok);
    }
}
