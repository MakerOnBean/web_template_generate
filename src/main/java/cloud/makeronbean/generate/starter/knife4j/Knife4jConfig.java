package cloud.makeronbean.generate.starter.knife4j;

import cloud.makeronbean.generate.utils.ProjectInfoUtils;

/**
 * @author makeronbean
 * @createDate 2023-05-04  09:03
 * @description
 */
public class Knife4jConfig {
    private String version = "2.0.7";

    private String title = ProjectInfoUtils.artifactId;

    private String description = "swagger-bootstrap-ui-demo RESTful APIs";

    void setVersion(String version) {
        this.version = version;
    }

    void setTitle(String title) {
        this.title = title;
    }

    void setDescription(String description) {
        this.description = description;
    }

    String getVersion() {
        return version;
    }

    String getTitle() {
        return title;
    }

    String getDescription() {
        return description;
    }
}
