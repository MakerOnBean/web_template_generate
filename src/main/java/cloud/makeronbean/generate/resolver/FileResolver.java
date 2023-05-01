package cloud.makeronbean.generate.resolver;

import cloud.makeronbean.generate.constant.code.CodeTemplate;
import cloud.makeronbean.generate.entity.project.ProjectInfo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author makeronbean
 * @createDate 2023-05-01  16:56
 * @description
 */

public class FileResolver {
    private static FileResolver fileResolver;
    
    public static FileResolver getInstance() {
        if (fileResolver == null) {
            fileResolver = new FileResolver();
        }
        return fileResolver;
    }
    
    private FileResolver() {
    }
    
    /**
     * 创建文件
     *
     * @param basePackage 包路径
     * @param name        包名
     */
    public void mkdir(String basePackage, String name) {
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
    public void touch(String path) throws IOException {
        File file = new File(path);
        file.createNewFile();
    }
    
    
    /**
     * 创建文件并写入
     *
     * @param codeTemplateList
     * @param info
     * @throws IOException
     */
    public void write(List<CodeTemplate> codeTemplateList, ProjectInfo info) throws IOException {
        for (CodeTemplate codeTemplate : codeTemplateList) {
            String path = codeTemplate.getPath();
            String filePath = info.getBaseGeneratePath();
            if (path != null && path.length() > 0) {
                filePath += "/" + path;
            }
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            
            File codeFile = new File(filePath + "/" + codeTemplate.getFileName());
            codeFile.createNewFile();
            
            String code = String.format(codeTemplate.getCodeTemplate(), "package " + info.getBasePackage() + (path != null && path.length() > 0 ? "." + path.replaceAll("/", ".") : "") + ";");
            
            FileOutputStream fos = new FileOutputStream(codeFile);
            fos.write(code.getBytes(StandardCharsets.UTF_8));
        }
    }
}
