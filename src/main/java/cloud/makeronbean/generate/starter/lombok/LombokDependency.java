package cloud.makeronbean.generate.starter.lombok;

import cloud.makeronbean.generate.constant.DependencyConst;
import cloud.makeronbean.generate.starter.base.dependency.BaseDependency;
import cloud.makeronbean.generate.starter.base.dependency.DependencyItem;

/**
 * @author makeronbean
 * @createDate 2023-05-03  13:32
 * @description
 */
public class LombokDependency extends BaseDependency {
    
    
    @Override
    protected void getDefault() {
        DependencyItem lombok = new DependencyItem();
        lombok.setGroupId("org.projectlombok");
        lombok.setTabName(DependencyConst.DEPENDENCY.getTabName());
        lombok.setArtifactId("lombok");
        lombok.setScope("provided");
        lombok.setVersion(LombokStarter.config.getVersion());
        lombok.setParentNodeName(DependencyConst.DEPENDENCIES.getTabName());
        this.addDependencyItem(lombok);
    }
}
