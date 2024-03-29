package tech.beanmak1r.generate.starter.parent;

import tech.beanmak1r.generate.project.Project;
import tech.beanmak1r.generate.utils.StringUtils;

/**
 * @author makeronbean
 * @createDate 2023-05-04  09:13
 * @description
 */
public class SpringBootParentConfig {
    private String yamlAppName = Project.project().artifactId();

    private String version;

    private String mainBootName = StringUtils.bootNameFormat(Project.project().artifactId());

    private boolean devtools = false;

    private boolean test = true;

    String getYamlAppName() {
        return yamlAppName;
    }

    void setYamlAppName(String yamlAppName) {
        this.yamlAppName = yamlAppName;
    }

    String getMainBootName() {
        return mainBootName;
    }

    void setMainBootName(String mainBootName) {
        this.mainBootName = mainBootName;
    }

    boolean isDevtools() {
        return devtools;
    }

    void setDevtools(boolean devtools) {
        this.devtools = devtools;
    }

    boolean isTest() {
        return test;
    }

    void setTest(boolean test) {
        this.test = test;
    }

    String getVersion() {
        return version;
    }

    void setVersion(String version) {
        this.version = version;
    }
}
