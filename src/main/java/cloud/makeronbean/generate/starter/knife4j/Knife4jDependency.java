package cloud.makeronbean.generate.starter.knife4j;

import cloud.makeronbean.generate.constant.DependencyConst;
import cloud.makeronbean.generate.starter.base.dependency.BaseDependency;
import cloud.makeronbean.generate.starter.base.dependency.DependencyItem;

/**
 * @author makeronbean
 * @createDate 2023-05-03  13:35
 * @description
 */
public class Knife4jDependency extends BaseDependency {
    
    @Override
    protected void getDefault() {
        DependencyItem knife4j = new DependencyItem();
        knife4j.setGroupId("com.github.xiaoymin");
        knife4j.setTabName(DependencyConst.DEPENDENCY.getTabName());
        knife4j.setArtifactId("knife4j-spring-boot-starter");
        knife4j.setParentNodeName(DependencyConst.DEPENDENCIES.getTabName());
        knife4j.setVersion(Knife4jStarter.config.getVersion());
        this.addDependencyItem(knife4j);
    }
}
