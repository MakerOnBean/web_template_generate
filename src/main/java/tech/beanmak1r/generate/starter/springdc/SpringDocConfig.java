package tech.beanmak1r.generate.starter.springdc;

/**
 * SprigDoc 配置类
 *
 * @author beanMak1r
 * @since 2023-08-04 14:53
 */
public class SpringDocConfig {
    private String version;

    private String openAPIPath = "/api-docs";

    private String swaggerUIPath = "/swagger-ui.html";

    String getVersion() {
        return version;
    }

    void setVersion(String version) {
        this.version = version;
    }

    String getOpenAPIPath() {
        return openAPIPath;
    }

    void setOpenAPIPath(String openAPIPath) {
        this.openAPIPath = openAPIPath;
    }

    String getSwaggerUIPath() {
        return swaggerUIPath;
    }

    void setSwaggerUIPath(String swaggerUIPath) {
        this.swaggerUIPath = swaggerUIPath;
    }
}
