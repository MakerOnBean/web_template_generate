package tech.beanmak1r.generate.starter.springdc;

import tech.beanmak1r.generate.enums.TagsEnum;
import tech.beanmak1r.generate.project.VersionHolder;
import tech.beanmak1r.generate.starter.base.dependency.BaseDependency;
import tech.beanmak1r.generate.starter.base.dependency.DependencyItem;
import tech.beanmak1r.generate.starter.base.starter.StarterAdapter;
import tech.beanmak1r.generate.starter.base.yaml.BaseYaml;
import tech.beanmak1r.generate.utils.StringUtils;

/**
 * SpringDoc Starter
 *
 * @author beanMak1r
 * @since 2023-08-04 14:58
 */
public class SpringDocStarter extends StarterAdapter {

    private final SpringDocConfig config = new SpringDocConfig();

    private SpringDocStarter() {
        this.order = 3;
    }


    public SpringDocStarter version(String version) {
        config.setVersion(version);
        return this;
    }

    public SpringDocStarter openAPIPath(String openAPIPath) {
        config.setOpenAPIPath(openAPIPath);
        return this;
    }

    public SpringDocStarter swaggerUIPath(String swaggerUIPath) {
        config.setSwaggerUIPath(swaggerUIPath);
        return this;
    }


    @Override
    protected void addYaml(BaseYaml yaml) {
        yaml.addYamlConfig("springdoc.api-docs.path", config.getOpenAPIPath());
        yaml.addYamlConfig("springdoc.swagger-ui.path", config.getSwaggerUIPath());
        yaml.addYamlConfig("springdoc.show-actuator", "true");
    }


    @Override
    protected void addDependency(BaseDependency dependency) {
        DependencyItem springDoc = new DependencyItem()
                .setPath(TagsEnum.DEPENDENCY)
                .addTag(TagsEnum.GROUP_ID, "org.springdoc")
                .addTag(TagsEnum.ARTIFACT_ID, "springdoc-openapi-ui")
                .addTag(TagsEnum.VERSION, StringUtils.getVersion(config.getVersion(), VersionHolder.DependencyNameEnum.SPRING_DOC));
        dependency.addDependencyItem(springDoc);
    }
}
