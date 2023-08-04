package cloud.makeronbean.generate.starter.parent;

import cloud.makeronbean.generate.enums.TagsEnum;
import cloud.makeronbean.generate.project.Project;
import cloud.makeronbean.generate.project.VersionHolder;
import cloud.makeronbean.generate.starter.base.dependency.DependencyItem;
import cloud.makeronbean.generate.starter.base.starter.StarterAdapter;
import cloud.makeronbean.generate.starter.base.code.BaseCode;
import cloud.makeronbean.generate.starter.base.code.CodeItem;
import cloud.makeronbean.generate.starter.base.dependency.BaseDependency;
import cloud.makeronbean.generate.starter.base.yaml.BaseYaml;
import cloud.makeronbean.generate.utils.StringUtils;

/**
 * @author makeronbean
 * @createDate 2023-05-03  11:43
 * @description
 */
public class SpringBootParentStarter extends StarterAdapter {
    private final SpringBootParentConfig config = new SpringBootParentConfig();


    private SpringBootParentStarter() {
        this.order = 1;
    }

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

    public SpringBootParentStarter version(String version) {
        config.setVersion(version);
        return this;
    }

    @Override
    protected void addDependency(BaseDependency dependency) {
        DependencyItem parent = new DependencyItem()
                .setPath(TagsEnum.PARENT)
                .addTag(TagsEnum.GROUP_ID, "org.springframework.boot")
                .addTag(TagsEnum.ARTIFACT_ID, "spring-boot-starter-parent")
                .addTag(TagsEnum.VERSION, StringUtils.isEmpty(
                        config.getVersion()) ?
                        Project.project().versionControls().getVersion(VersionHolder.DependencyNameEnum.SPRING_BOOT) :
                        config.getVersion());
        dependency.addDependencyItem(parent);

        DependencyItem plugin = new DependencyItem()
                .setPath(TagsEnum.PLUGIN)
                .addTag(TagsEnum.GROUP_ID, "org.springframework.boot")
                .addTag(TagsEnum.ARTIFACT_ID, "spring-boot-maven-plugin")
                .addTag(TagsEnum.VERSION, StringUtils.isEmpty(
                        config.getVersion()) ?
                        Project.project().versionControls().getVersion(VersionHolder.DependencyNameEnum.SPRING_BOOT) :
                        config.getVersion());
        dependency.addDependencyItem(plugin);

        // devtools
        if (config.isDevtools()) {
            DependencyItem devtools = new DependencyItem()
                    .setPath(TagsEnum.DEPENDENCY)
                    .addTag(TagsEnum.GROUP_ID, "org.springframework.boot")
                    .addTag(TagsEnum.ARTIFACT_ID, "spring-boot-devtools")
                    .addTag(TagsEnum.SCOPE, "runtime")
                    .addTag(TagsEnum.OPTIONAL, "true");

            dependency.addDependencyItem(devtools);
        }

        //test
        if (config.isTest()) {
            DependencyItem test = new DependencyItem()
                    .setPath(TagsEnum.DEPENDENCY)
                    .addTag(TagsEnum.GROUP_ID, "org.springframework.boot")
                    .addTag(TagsEnum.ARTIFACT_ID, "spring-boot-starter-test")
                    .addTag(TagsEnum.SCOPE, "test");
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
