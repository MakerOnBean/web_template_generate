package cloud.makeronbean.generate.entity.starter;

import cloud.makeronbean.generate.constant.dependency.DependencyConst;

import java.util.ArrayList;
import java.util.List;

/**
 * @author makeronbean
 * @createDate 2023-05-01  19:16
 * @description
 */

public class LombokStarter extends BaseStarterAdapter{
    private LombokStarter() {
        this.dependencyList = getDependencyList();
        this.order = 3;
    }
    
    @Override
    protected List<Dependency> getDefaultDependencies() {
        List<Dependency> dependencies = new ArrayList<>();
        Dependency dependency = new Dependency();
        dependency.setGroupId("org.projectlombok");
        dependency.setTabName(DependencyConst.DEPENDENCY.getTabName());
        dependency.setArtifactId("lombok");
        dependency.setScope("provided");
        dependency.parentNodeName = DependencyConst.DEPENDENCIES.getTabName();
        dependencies.add(dependency);
        return dependencies;
    }
}
