package cloud.makeronbean.generate.entity.starter;

import cloud.makeronbean.generate.constant.code.CodeTemplate;
import cloud.makeronbean.generate.constant.code.Knife4jCodeConst;
import cloud.makeronbean.generate.constant.dependency.DependencyConst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author makeronbean
 * @createDate 2023-05-01  20:57
 * @description
 */

public class Knife4jStarter extends BaseStarterAdapter{
    
    
    private Knife4jStarter() {
        this.order = 3;
        this.dependencyList = getDefaultDependencies();
        this.codeTemplateList = getDefaultCodeTemplateList();
    }
    
    @Override
    protected List<Dependency> getDefaultDependencies() {
        List<Dependency> dependencies = new ArrayList<>();
        Dependency dependency = new Dependency();
        dependency.setGroupId("com.github.xiaoymin");
        dependency.setTabName(DependencyConst.DEPENDENCY.getTabName());
        dependency.setArtifactId("knife4j-spring-boot-starter");
        dependency.setScope("provided");
        dependency.parentNodeName = DependencyConst.DEPENDENCIES.getTabName();
        dependency.setVersion("2.0.7");
        dependencies.add(dependency);
        return dependencies;
    }
    
    @Override
    protected List<CodeTemplate> getDefaultCodeTemplateList() {
        return new ArrayList<>(Arrays.asList(Knife4jCodeConst.values()));
    }
}
