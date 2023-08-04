package cloud.makeronbean.generate.starter.knife4j;

import cloud.makeronbean.generate.project.Project;
import cloud.makeronbean.generate.project.VersionHolder;

/**
 * @author makeronbean
 * @createDate 2023-05-04  09:03
 * @description
 */
public class Knife4jConfig {
    private String version;

    private String title = Project.project().artifactId();

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
