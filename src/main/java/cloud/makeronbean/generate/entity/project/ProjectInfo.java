package cloud.makeronbean.generate.entity.project;

import lombok.Data;

/**
 * @author makeronbean
 * @createDate 2023-05-01  08:37
 * @description 项目基本信息
 */
@Data
public class ProjectInfo {
    /**
     * 项目磁盘路径
     */
    private String basePath;
    
    /**
     * 包路径
     */
    private String basePackagePath;
    
    
    /**
     * 基包名
     */
    private String basePackage;
    
    
    /**
     * 配置文件路径
     */
    private String yamlPath;
    
    public ProjectInfo(String basePath, String basePackagePath) {
        this.basePath = basePath + "/" + "src" + "/" + "main" + "/" + "java" + "/";
        this.yamlPath = basePath +  "/" + "src" + "/" + "main" + "/" + "resources" + "/" + "application.yaml";
        this.basePackage = basePackagePath;
        this.basePackagePath = basePackagePath.replaceAll("\\.", "/");
    }
    
    public String getBaseGeneratePath() {
        return basePath + basePackagePath;
    }
}
