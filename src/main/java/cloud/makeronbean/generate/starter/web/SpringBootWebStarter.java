package cloud.makeronbean.generate.starter.web;

import cloud.makeronbean.generate.constant.DependencyConst;
import cloud.makeronbean.generate.starter.base.dependency.DependencyItem;
import cloud.makeronbean.generate.starter.base.starter.StarterAdapter;
import cloud.makeronbean.generate.starter.base.code.BaseCode;
import cloud.makeronbean.generate.starter.base.dependency.BaseDependency;
import cloud.makeronbean.generate.starter.base.yaml.BaseYaml;

/**
 * @author makeronbean
 * @createDate 2023-05-02  21:49
 * @description
 */
public class SpringBootWebStarter extends StarterAdapter {

    private final SpringBootWebConfig config = new SpringBootWebConfig();

    private SpringBootWebStarter() {
        this.order = 2;
    }

    public SpringBootWebStarter serverPort(Integer port) {
        config.setServerPort(port);
        return this;
    }

    public SpringBootWebStarter cors(boolean open) {
        config.setCors(open);
        return this;
    }

    public SpringBootWebStarter asserts(boolean open) {
        config.setAsserts(open);
        return this;
    }

    public SpringBootWebStarter valid(boolean open) {
        config.setValid(open);
        return this;
    }


    @Override
    protected void addDependency(BaseDependency dependency) {
        DependencyItem web = new DependencyItem()
                .setPath(DependencyConst.DEPENDENCY)
                .addTag(DependencyConst.GROUP_ID, "org.springframework.boot")
                .addTag(DependencyConst.ARTIFACT_ID, "spring-boot-starter-web");
        dependency.addDependencyItem(web);

        // 参数校验
        if (config.isValid()) {
            DependencyItem validation = new DependencyItem()
                    .setPath(DependencyConst.DEPENDENCY)
                    .addTag(DependencyConst.GROUP_ID, "org.springframework.boot")
                    .addTag(DependencyConst.ARTIFACT_ID, "spring-boot-starter-validation");
            dependency.addDependencyItem(validation);
        }
    }

    @Override
    protected void addYaml(BaseYaml yaml) {
        yaml.addYamlConfig("server.port", config.getServerPort());
        yaml.addYamlConfig("spring.mvc.format.date", "yyyy-MM-dd");
        yaml.addYamlConfig("spring.mvc.format.date-time", "yyyy-MM-dd HH:mm:ss");
        yaml.addYamlConfig("spring.jackson.date-format", "yyyy-MM-dd");
        yaml.addYamlConfig("spring.jackson.time-zone", "Asia/Shanghai");
    }


    @Override
    protected void addCode(BaseCode code) {
        new SpringBootWebCode(config, code).fillData();
    }
}
