package cloud.makeronbean.generate.starter.knife4j;

import cloud.makeronbean.generate.constant.DependencyConst;
import cloud.makeronbean.generate.starter.base.starter.StarterAdapter;
import cloud.makeronbean.generate.starter.base.code.BaseCode;
import cloud.makeronbean.generate.starter.base.code.CodeItem;
import cloud.makeronbean.generate.starter.base.dependency.BaseDependency;
import cloud.makeronbean.generate.starter.base.dependency.DependencyItem;
import cloud.makeronbean.generate.utils.ProjectInfoUtils;

/**
 * @author makeronbean
 * @createDate 2023-05-03  13:34
 * @description
 */
public class Knife4jStarter extends StarterAdapter {
    
    private Knife4jStarter() {
        this.order = 3;
    }
    
    Knife4jConfig config = new Knife4jConfig();
    
    public Knife4jStarter version(String version) {
        config.setVersion(version);
        return this;
    }
    
    public Knife4jStarter title(String title) {
        config.setTitle(title);
        return this;
    }
    
    public Knife4jStarter description(String description) {
        config.setDescription(description);
        return this;
    }
    
    
    @Override
    protected void addDependency(BaseDependency dependency) {
        DependencyItem knife4j = new DependencyItem();
        knife4j.setGroupId("com.github.xiaoymin");
        knife4j.setTabName(DependencyConst.DEPENDENCY.getTabName());
        knife4j.setArtifactId("knife4j-spring-boot-starter");
        knife4j.setParentNodeName(DependencyConst.DEPENDENCIES.getTabName());
        knife4j.setVersion(config.getVersion());
        dependency.addDependencyItem(knife4j);
    }
    
    @Override
    protected void addCode(BaseCode code) {
        CodeItem knifeConfig = new CodeItem();
        knifeConfig.setPath("config/knife4j");
        knifeConfig.setFileName("Knife4jConfiguration.java");
        knifeConfig.setCodeTemplate(String.format(
                "import org.springframework.context.annotation.Bean;\n" +
                        "import org.springframework.context.annotation.Configuration;\n" +
                        "import springfox.documentation.builders.ApiInfoBuilder;\n" +
                        "import springfox.documentation.builders.PathSelectors;\n" +
                        "import springfox.documentation.builders.RequestHandlerSelectors;\n" +
                        "import springfox.documentation.spi.DocumentationType;\n" +
                        "import springfox.documentation.spring.web.plugins.Docket;\n" +
                        "import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;\n" +
                        "\n" +
                        "\n" +
                        "@Configuration\n" +
                        "@EnableSwagger2WebMvc\n" +
                        "public class Knife4jConfiguration {\n" +
                        "    \n" +
                        "    @Bean(value = \"defaultApi2\")\n" +
                        "    public Docket defaultApi2() {\n" +
                        "        return new Docket(DocumentationType.SWAGGER_2)\n" +
                        "                .apiInfo(new ApiInfoBuilder()\n" +
                        "                        .title(\"%s\")\n" +
                        "                        .description(\"%s\")\n" +
                        "                        .build())\n" +
                        "                //分组名称\n" +
                        "                .select()\n" +
                        "                //这里指定Controller扫描包路径\n" +
                        "                .apis(RequestHandlerSelectors.basePackage(\"%s.controller\"))\n" +
                        "                .paths(PathSelectors.any())\n" +
                        "                .build();\n" +
                        "    }\n" +
                        "}", config.getTitle(), config.getDescription(), ProjectInfoUtils.basePackage
        ));
        code.addCodeItem(knifeConfig);
    }
}
