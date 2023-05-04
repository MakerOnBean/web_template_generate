package cloud.makeronbean.generate.starter.parent;

import cloud.makeronbean.generate.constant.DependencyConst;
import cloud.makeronbean.generate.starter.base.starter.StarterAdapter;
import cloud.makeronbean.generate.starter.base.code.BaseCode;
import cloud.makeronbean.generate.starter.base.code.CodeItem;
import cloud.makeronbean.generate.starter.base.dependency.BaseDependency;
import cloud.makeronbean.generate.starter.base.dependency.DependencyItem;
import cloud.makeronbean.generate.starter.base.yaml.BaseYaml;

/**
 * @author makeronbean
 * @createDate 2023-05-03  11:43
 * @description
 */
public class SpringBootParentStarter extends StarterAdapter {
    private final SpringBootParentConfig config = new SpringBootParentConfig();
    
    
    public SpringBootParentStarter yamlAppName(String yamlAppName) {
        config.setYamlAppName(yamlAppName);
        return this;
    }
    
    public SpringBootParentStarter mainBootName(String mainBootName) {
        config.setMainBootName(mainBootName);
        return this;
    }
    
    public SpringBootParentStarter devtools(boolean open) {
        config.setDevtools(open);
        return this;
    }
    
    public SpringBootParentStarter test(boolean open) {
        config.setTest(open);
        return this;
    }
    
    @Override
    protected void addDependency(BaseDependency dependency) {
        DependencyItem parent = new DependencyItem();
        parent.setTabName(DependencyConst.PARENT.getTabName());
        parent.setGroupId("org.springframework.boot");
        parent.setArtifactId("spring-boot-starter-parent");
        parent.setVersion("2.3.6.RELEASE");
        parent.setParentNodeName(DependencyConst.PROJECT.getTabName());
        dependency.addDependencyItem(parent);
        
        DependencyItem plugin = new DependencyItem();
        plugin.setTabName(DependencyConst.PLUGIN.getTabName());
        plugin.setGroupId("org.springframework.boot");
        plugin.setArtifactId("spring-boot-maven-plugin");
        plugin.setVersion("2.3.6.RELEASE");
        plugin.setParentNodeName(DependencyConst.PLUGINS.getTabName());
        dependency.addDependencyItem(plugin);
        
        // devtools
        if (config.isDevtools()) {
            DependencyItem devtools = new DependencyItem();
            devtools.setGroupId("org.springframework.boot");
            devtools.setTabName(DependencyConst.DEPENDENCY.getTabName());
            devtools.setArtifactId("spring-boot-devtools");
            devtools.setScope("runtime");
            devtools.setOptional("true");
            devtools.setParentNodeName(DependencyConst.DEPENDENCIES.getTabName());
            dependency.addDependencyItem(devtools);
        }
        
        //test
        if (config.isTest()) {
            DependencyItem test = new DependencyItem();
            test.setGroupId("org.springframework.boot");
            test.setArtifactId("spring-boot-starter-test");
            test.setScope("test");
            test.setParentNodeName(DependencyConst.DEPENDENCIES.getTabName());
            test.setTabName(DependencyConst.DEPENDENCY.getTabName());
            dependency.addDependencyItem(test);
        }
    }
    
    @Override
    protected void addYaml(BaseYaml yaml) {
        yaml.addYamlConfig("spring.application.name", config.getYamlAppName());
    }
    
    @Override
    protected void addCode(BaseCode code) {
        String bootName = config.getMainBootName();
        CodeItem boot = new CodeItem();
        boot.setPath("");
        boot.setFileName(bootName + ".java");
        boot.setCodeTemplate(String.format(
                "import org.springframework.boot.SpringApplication;\n" +
                        "import org.springframework.boot.autoconfigure.SpringBootApplication;\n" +
                        "\n" +
                        "@SpringBootApplication\n" +
                        "public class %s {\n" +
                        "    public static void main(String[] args) {\n" +
                        "        SpringApplication.run( %s.class, args);\n" +
                        "    }\n" +
                        "}", bootName, bootName
        ));
        code.addCodeItem(boot);
    }
}
