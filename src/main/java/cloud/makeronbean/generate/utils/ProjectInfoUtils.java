package cloud.makeronbean.generate.utils;

/**
 * @author makeronbean
 * @createDate 2023-05-02  22:42
 * @description
 */
public class ProjectInfoUtils {
    /**
     * 项目磁盘路径
     */
    public static String projectPath;
    
    
    /**
     * 基础包名
     */
    public static String basePackage;
    
    /**
     *
     * groupId
     */
    public static String groupId;
    
    /**
     * artifactId
     */
    public static String artifactId;
    
    /**
     * 获取java文件基路径
     */
    public static String getJavaPath() {
        return projectPath + "/" + "src" + "/" + "main" + "/" + "java" + "/" + packagePath();
    }
    
    /**
     * 项目生成的包路径
     */
    public static String packagePath() {
        return basePackage.replaceAll("\\.","/");
    }
    
    /**
     * yaml配置文件路径
     */
    public static String yamlFilePath() {
        return projectPath + "/" + "src" + "/" + "main" + "/" + "resources" + "/" + "application.yaml";
    }
    
    /**
     * pom文件路径
     */
    public static String pomFilePath() {
        return projectPath + "/" + "pom.xml";
    }
    
    static {
        projectPath = System.getProperty("user.dir");
        groupId = XmlUtils.getSingleValue("groupId");
        artifactId = XmlUtils.getSingleValue("artifactId");
        basePackage = groupId;
    }

}
