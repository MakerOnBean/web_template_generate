package cloud.makeronbean.generate.constant.yaml;

import cloud.makeronbean.generate.entity.dto.MyBatisPlusDto;
import cloud.makeronbean.generate.resolver.PomResolver;

/**
 * @author makeronbean
 * @createDate 2023-05-01  13:58
 * @description
 */
@SuppressWarnings("all")
public enum MyBatisPlusYamlConst implements YamlTemplate {
    USERNAME("spring.datasource.username", "root"),
    PASSWORD("spring.datasource.password", "123456"),
    DRIVER_CLASS_NAME("spring.datasource.driver-class-name", "com.mysql.cj.jdbc.Driver"),
    URL("spring.datasource.url", MyBatisPlusDto.getDatabaseUrl()),
    LOG("mybatis-plus.configuration.log-impl", "org.apache.ibatis.logging.stdout.StdOutImpl"),
    UNDERSCORE_2_CAMEL("mybatis-plus.configuration.map-underscore-to-camel-case", true),
    TYPE_ALIASES_PACKAGE("mybatis-plus.type-aliases-package", PomResolver.getInstance().getGroupId() + ".entity"),
    LOGIC_DELETE_FIELD("mybatis-plus.global-config.db-config.logic-delete-field", "isDelete"),
    LOGIC_DELETE_VALUE("mybatis-plus.global-config.db-config.logic-delete-value", 1),
    LOGIC_NOT_DELETE_VALUE("mybatis-plus.global-config.db-config.logic-not-delete-value", 0),
    ;
    
    MyBatisPlusYamlConst(String key, Object defaultValue) {
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
