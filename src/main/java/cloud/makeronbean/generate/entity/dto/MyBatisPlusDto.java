package cloud.makeronbean.generate.entity.dto;

import cloud.makeronbean.generate.resolver.PomResolver;

/**
 * @author makeronbean
 * @createDate 2023-05-01  20:18
 * @description
 */

public class MyBatisPlusDto {
    private static final String URL_TEMPLATE = "jdbc:mysql://localhost:%d/%s?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
    public static Integer port = 3306;
    public static String dbName = PomResolver.getInstance().getArtifactId();
    
    public static String getDatabaseUrl() {
        return String.format(URL_TEMPLATE, port, dbName);
    }
}
