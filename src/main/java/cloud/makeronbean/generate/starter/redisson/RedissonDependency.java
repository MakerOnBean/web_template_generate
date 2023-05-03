package cloud.makeronbean.generate.starter.redisson;

import cloud.makeronbean.generate.constant.DependencyConst;
import cloud.makeronbean.generate.starter.base.dependency.BaseDependency;
import cloud.makeronbean.generate.starter.base.dependency.DependencyItem;

/**
 * @author makeronbean
 * @createDate 2023-05-03  15:20
 * @description
 */
public class RedissonDependency extends BaseDependency {
    
    @Override
    protected void getDefault() {
        DependencyItem redisson = new DependencyItem();
        redisson.setGroupId("org.redisson");
        redisson.setTabName(DependencyConst.DEPENDENCY.getTabName());
        redisson.setArtifactId("redisson-spring-boot-starter");
        redisson.setVersion(RedissonStarter.config.getVersion());
        redisson.setParentNodeName(DependencyConst.DEPENDENCIES.getTabName());
        this.addDependencyItem(redisson);
    }
}
