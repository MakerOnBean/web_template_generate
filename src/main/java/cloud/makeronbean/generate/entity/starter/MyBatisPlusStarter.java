package cloud.makeronbean.generate.entity.starter;

import cloud.makeronbean.generate.constant.code.CodeTemplate;
import cloud.makeronbean.generate.constant.code.MyBatisPlusCodeConst;
import cloud.makeronbean.generate.constant.dependency.DependencyConst;
import cloud.makeronbean.generate.constant.yaml.MyBatisPlusYamlConst;
import cloud.makeronbean.generate.entity.dto.MyBatisPlusDto;
import cloud.makeronbean.generate.resolver.PomResolver;

import java.util.*;

/**
 * @author makeronbean
 * @createDate 2023-05-01  16:12
 * @description
 */

public class MyBatisPlusStarter extends BaseStarterAdapter {
    
    
    public MyBatisPlusStarter setDatabaseName(String dbName) {
        MyBatisPlusDto.dbName = dbName;
        yamlMap.put(MyBatisPlusYamlConst.URL.getKey(), MyBatisPlusDto.getDatabaseUrl());
        return this;
    }
    
    public MyBatisPlusStarter setPort(Integer port) {
        MyBatisPlusDto.port = port;
        yamlMap.put(MyBatisPlusYamlConst.URL.getKey(), MyBatisPlusDto.getDatabaseUrl());
        return this;
    }
    
    public MyBatisPlusStarter setUsername(String username) {
        this.yamlMap.put(MyBatisPlusYamlConst.USERNAME.getKey(), username);
        return this;
    }
    
    public MyBatisPlusStarter setPassword(String password) {
        this.yamlMap.put(MyBatisPlusYamlConst.PASSWORD.getKey(), password);
        return this;
    }
    
    @Override
    protected List<CodeTemplate> getDefaultCodeTemplateList() {
        return new ArrayList<>(Arrays.asList(MyBatisPlusCodeConst.values()));
    }
    
    @Override
    protected Map<String, Object> getDefaultYamlMap() {
        Map<String, Object> resultMap = new HashMap<>();
    
        // mvc 默认配置
        for (MyBatisPlusYamlConst value : MyBatisPlusYamlConst.values()) {
            resultMap.put(value.getKey(), value.getDefaultValue());
        }
    
        return resultMap;
    }
    
    @Override
    protected List<Dependency> getDefaultDependencies() {
        List<Dependency> dependencies = new ArrayList<>();
        
        Dependency mybatis = new Dependency();
        mybatis.tabName = DependencyConst.DEPENDENCY.getTabName();
        mybatis.groupId = "com.baomidou";
        mybatis.artifactId = "mybatis-plus-boot-starter";
        mybatis.version = "3.4.1";
        mybatis.parentNodeName = DependencyConst.DEPENDENCIES.getTabName();
        dependencies.add(mybatis);
        
        Dependency mysql = new Dependency();
        mysql.tabName = DependencyConst.DEPENDENCY.getTabName();
        mysql.groupId = "mysql";
        mysql.artifactId = "mysql-connector-java";
        mysql.version = "8.0.30";
        mysql.parentNodeName = DependencyConst.DEPENDENCIES.getTabName();
        dependencies.add(mysql);
        return dependencies;
    }
    
    
    private MyBatisPlusStarter() {
        this.dependencyList = getDefaultDependencies();
        this.yamlMap = getDefaultYamlMap();
        this.codeTemplateList = getDefaultCodeTemplateList();
        this.order = 2;
    }
}
