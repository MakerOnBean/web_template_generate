package cloud.makeronbean.generate.utils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author makeronbean
 * @createDate 2023-05-03  14:07
 * @description
 */
public class FileUtils {
    
    /**
     * 基础包路径
     */
    private static final List<String> BASE_PACKAGE = Arrays.asList("common", "config", "controller", "entity", "mapper", "model/req", "model/resp", "model/dto", "service", "utils");
    
    /**
     * 创建文件
     *
     * @param basePackage 包路径
     * @param name        包名
     */
    public static void mkdir(String basePackage, String name) {
        File file = new File(basePackage + "/" + name);
        if (!file.exists()) {
            file.mkdirs();
        }
    }
    
    
    /**
     * 创建一个空文件
     *
     * @param path
     * @throws IOException
     */
    public static void touch(String path) throws IOException {
        File file = new File(path);
        file.createNewFile();
    }
    
    
    /**
     * 创建项目基本文件
     */
    public static void initProject() {
        // 生成基本文件夹
        for (String s : BASE_PACKAGE) {
            FileUtils.mkdir(ProjectInfoUtils.getJavaPath(), s);
        }
    
        // 生成配置文件
        try {
            FileUtils.touch(ProjectInfoUtils.yamlFilePath());
        } catch (IOException e) {
            throw new RuntimeException("配置文件创建失败",e);
        }
    }
}
