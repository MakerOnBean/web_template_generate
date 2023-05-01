package cloud.makeronbean.generate.entity.starter;

import cloud.makeronbean.generate.constant.dependency.DependencyConst;

import java.util.ArrayList;
import java.util.List;

/**
 * @author makeronbean
 * @createDate 2023-05-01  20:50
 * @description
 */

public class SpringBootTestStarter extends BaseStarterAdapter {
    private SpringBootTestStarter() {
        this.dependencyList = getDependencyList();
        this.order = 3;
    }
    
    @Override
    protected List<BaseStarter.Dependency> getDefaultDependencies() {
        List<BaseStarter.Dependency> dependencies = new ArrayList<>();
        BaseStarter.Dependency dependency = new BaseStarter.Dependency();
        dependency.setGroupId("org.springframework.boot");
        dependency.setArtifactId("spring-boot-starter-test");
        dependency.setScope("test");
        dependency.parentNodeName = DependencyConst.DEPENDENCIES.getTabName();
        dependency.setTabName(DependencyConst.DEPENDENCY.getTabName());
        dependencies.add(dependency);
        return dependencies;
    }
}
