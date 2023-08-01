package cloud.makeronbean.generate.starter.lombok;

/**
 * @author makeronbean
 * @createDate 2023-05-04  08:55
 * @description
 */
public class LombokConfig {
    private String version = "1.18.26";


    String getVersion() {
        return version;
    }

    void setVersion(String version) {
        this.version = version;
    }
}
