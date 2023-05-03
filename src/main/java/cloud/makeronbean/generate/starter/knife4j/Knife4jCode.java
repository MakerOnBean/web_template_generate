package cloud.makeronbean.generate.starter.knife4j;

import cloud.makeronbean.generate.starter.base.code.BaseCode;
import cloud.makeronbean.generate.starter.base.code.CodeItem;
import cloud.makeronbean.generate.utils.ProjectInfoUtils;

/**
 * @author makeronbean
 * @createDate 2023-05-03  13:37
 * @description
 */
public class Knife4jCode extends BaseCode {
    
    @Override
    protected void getDefault() {
        // 配置文件
        addConfig();
    }
    
    /**
     * 配置文件
     */
    private void addConfig() {
        CodeItem config = new CodeItem();
        config.setPath("config/knife4j");
        config.setFileName("Knife4jConfiguration.java");
        config.setCodeTemplate(String.format(
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
                        "}", Knife4jStarter.config.getTitle(), Knife4jStarter.config.getDescription(), ProjectInfoUtils.basePackage
        ));
        this.addCodeItem(config);
    }
}
