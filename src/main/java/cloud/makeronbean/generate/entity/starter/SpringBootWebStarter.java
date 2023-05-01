package cloud.makeronbean.generate.entity.starter;

import cloud.makeronbean.generate.constant.code.CodeTemplate;
import cloud.makeronbean.generate.constant.code.WebCodeConst;
import cloud.makeronbean.generate.constant.dependency.DependencyConst;
import cloud.makeronbean.generate.constant.yaml.WebYamlConst;

import java.util.*;

/**
 * @author makeronbean
 * @createDate 2023-05-01  10:52
 * @description
 */

public class SpringBootWebStarter extends BaseStarterAdapter {
    
    @Override
    protected List<Dependency> getDefaultDependencies() {
        ArrayList<Dependency> dependencies = new ArrayList<>();
        
        // web依赖
        Dependency dependency = new Dependency();
        dependency.setGroupId("org.springframework.boot");
        dependency.setTabName(DependencyConst.DEPENDENCY.getTabName());
        dependency.setArtifactId("spring-boot-starter-web");
        dependency.parentNodeName = DependencyConst.DEPENDENCIES.getTabName();
        dependencies.add(dependency);
        
        // 数据校验依赖
        Dependency dependency1 = new Dependency();
        dependency1.setGroupId("org.springframework.boot");
        dependency1.setTabName(DependencyConst.DEPENDENCY.getTabName());
        dependency1.setArtifactId("spring-boot-starter-validation");
        dependency1.parentNodeName = DependencyConst.DEPENDENCIES.getTabName();
        dependencies.add(dependency1);
        return dependencies;
    }
    
    @Override
    protected List<CodeTemplate> getDefaultCodeTemplateList() {
        return new ArrayList<>(Arrays.asList(WebCodeConst.values()));
    }
    
    @Override
    public Map<String, Object> getDefaultYamlMap() {
        Map<String, Object> resultMap = new HashMap<>();
        
        // mvc 默认配置
        for (WebYamlConst value : WebYamlConst.values()) {
            resultMap.put(value.getKey(), value.getDefaultValue());
        }
        
        
        return resultMap;
    }

    
    private SpringBootWebStarter() {
        this.dependencyList = getDefaultDependencies();
        this.yamlMap = getDefaultYamlMap();
        this.codeTemplateList = getDefaultCodeTemplateList();
        this.order = 1;
    }
}
