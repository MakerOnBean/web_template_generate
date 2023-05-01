package cloud.makeronbean.generate.entity.starter;

import cloud.makeronbean.generate.constant.code.CodeTemplate;
import cloud.makeronbean.generate.constant.code.MyBatisPlusCodeConst;
import cloud.makeronbean.generate.constant.code.SpringParentCodeConst;
import cloud.makeronbean.generate.constant.dependency.DependencyConst;
import cloud.makeronbean.generate.constant.yaml.SpringBootParentYamlConst;
import cloud.makeronbean.generate.entity.dto.SpringBootParentDto;

import java.util.*;

/**
 * @author makeronbean
 * @createDate 2023-05-01  10:52
 * @description
 */

public class SpringBootParentStarter extends BaseStarterAdapter {
    
    public SpringBootParentStarter setServerPort(Integer port) {
        this.yamlMap.put(SpringBootParentYamlConst.SERVER_PORT.getKey(), port);
        return this;
    }
    
    public SpringBootParentStarter setAppName(String name) {
        this.yamlMap.put(SpringBootParentYamlConst.APP_NAME.getKey(), name);
        return this;
    }
    
    /**
     * TODO 测试无效，枚举类中已经锁死了
     */
    public SpringBootParentStarter setMainBootName(String name) {
        SpringBootParentDto.mainBootName = name;
        return this;
    }
    
    
    @Override
    public List<CodeTemplate> getCodeTemplateList() {
        return new ArrayList<>(Arrays.asList(SpringParentCodeConst.values()));
    }
    
    @Override
    protected Map<String, Object> getDefaultYamlMap() {
        Map<String, Object> resultMap = new HashMap<>();
        
        for (SpringBootParentYamlConst value : SpringBootParentYamlConst.values()) {
            resultMap.put(value.getKey(), value.getDefaultValue());
        }
        
        return resultMap;
    }
    
    @Override
    protected List<Dependency> getDefaultDependencies() {
        List<Dependency> dependencies = new ArrayList<>();
        Dependency dependency = new Dependency();
        dependency.tabName = DependencyConst.PARENT.getTabName();
        dependency.groupId = "org.springframework.boot";
        dependency.artifactId = "spring-boot-starter-parent";
        dependency.version = "2.3.6.RELEASE";
        dependency.parentNodeName = DependencyConst.PROJECT.getTabName();
        dependencies.add(dependency);
        return dependencies;
    }
    
    
    private SpringBootParentStarter() {
        this.dependencyList = getDefaultDependencies();
        this.codeTemplateList = getCodeTemplateList();
        this.yamlMap = getDefaultYamlMap();
        this.order = 0;
    }
}
