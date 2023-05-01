package cloud.makeronbean.generate.entity.starter;

import cloud.makeronbean.generate.constant.dependency.DependencyConst;

import java.util.ArrayList;
import java.util.List;

/**
 * @author makeronbean
 * @createDate 2023-05-01  18:56
 * @description
 */

public class SpringBootDevtoolsStarter extends BaseStarterAdapter {
    
    private SpringBootDevtoolsStarter() {
        this.dependencyList = getDefaultDependencies();
        this.order = 3;
    }
    
    @Override
    protected List<Dependency> getDefaultDependencies() {
        List<Dependency> dependencies = new ArrayList<>();
        Dependency dependency = new Dependency();
        dependency.setGroupId("org.springframework.boot");
        dependency.setTabName(DependencyConst.DEPENDENCY.getTabName());
        dependency.setArtifactId("spring-boot-devtools");
        dependency.setScope("runtime");
        dependency.setOptional("true");
        dependency.parentNodeName = DependencyConst.DEPENDENCIES.getTabName();
        
        dependencies.add(dependency);
        return dependencies;
    }
}
