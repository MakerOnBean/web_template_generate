package cloud.makeronbean.generate.starter.parent;

import cloud.makeronbean.generate.utils.ProjectInfoUtils;
import cloud.makeronbean.generate.utils.StringUtils;

/**
 * @author makeronbean
 * @createDate 2023-05-04  09:13
 * @description
 */
public class SpringBootParentConfig {
    private String yamlAppName = ProjectInfoUtils.artifactId;

    private String version = "2.3.6.RELEASE";

    private String mainBootName = StringUtils.bootNameFormat(ProjectInfoUtils.artifactId);

    private boolean devtools = true;

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
