package cloud.makeronbean.generate.constant.yaml;

/**
 * @author makeronbean
 * @createDate 2023-05-01  14:09
 * @description
 */

@SuppressWarnings("all")
public enum WebYamlConst implements YamlTemplate {
    FORMAT_DATE("spring.mvc.format.date", "yyyy-MM-dd"),
    FORMAT_DATE_TIME("spring.mvc.format.date-time", "yyyy-MM-dd HH:mm:ss"),
    JSON_DATE_FORMAT("spring.jackson.date-format", "yyyy-MM-dd"),
    JSON_TIME_ZONE("spring.jackson.time-zone", "Asia/Shanghai"),
    
    ;
    
    WebYamlConst(String key, String defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }
    
    private final String key;
    private final String defaultValue;
    
    @Override
    public String getKey() {
        return key;
    }
    
    @Override
    public String getDefaultValue() {
        return defaultValue;
    }
}
