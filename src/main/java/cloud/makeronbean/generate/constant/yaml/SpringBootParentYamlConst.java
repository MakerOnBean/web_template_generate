package cloud.makeronbean.generate.constant.yaml;

import cloud.makeronbean.generate.resolver.PomResolver;

/**
 * @author makeronbean
 * @createDate 2023-05-01  18:21
 * @description
 */
@SuppressWarnings("all")
public enum SpringBootParentYamlConst implements YamlTemplate {
    SERVER_PORT("server.port", 8080),
    
    APP_NAME("spring.application.name", PomResolver.getInstance().getArtifactId()),
    
    ;
    
    SpringBootParentYamlConst(String key, Object defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }
    
    private final String key;
    private final Object defaultValue;
    
    @Override
    public String getKey() {
        return key;
    }
    
    @Override
    public Object getDefaultValue() {
        return defaultValue;
    }
    
}
