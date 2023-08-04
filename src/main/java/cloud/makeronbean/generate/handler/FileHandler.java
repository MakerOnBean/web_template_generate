package cloud.makeronbean.generate.handler;

import cloud.makeronbean.generate.project.Project;
import cloud.makeronbean.generate.starter.base.starter.AbstractStarter;
import cloud.makeronbean.generate.starter.base.code.BaseCode;
import cloud.makeronbean.generate.starter.base.code.CodeItem;
import cloud.makeronbean.generate.utils.FileUtils;
import cloud.makeronbean.generate.project.ProjectInfo;
import cloud.makeronbean.generate.utils.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author makeronbean
 * @createDate 2023-05-01  16:56
 * @description
 */

public class FileHandler extends GenerateHandlerAdapter {
    
    /**
     * 创建文件并写入
     *
     * @param codeItem 代码项
     * @throws IOException
     */
    private void write(CodeItem codeItem) throws IOException {
        String path = codeItem.getPath();
        String filePath = Project.project().javaPath();
        
        if (path != null && !path.isEmpty()) {
            filePath += "/" + path;
        }
        
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        
        File codeFile = new File(filePath + "/" + codeItem.getFileName());
        codeFile.createNewFile();
        
        String code = StringUtils.addPackage(codeItem.getPath().replaceAll("/", "."), codeItem.getCodeTemplate());
        
        FileOutputStream fos = new FileOutputStream(codeFile);
        fos.write(code.getBytes(StandardCharsets.UTF_8));
    }
    
    @Override
    public void generateHandler(AbstractStarter starter) throws Exception {
        BaseCode code = starter.getCode();
        if (code != null) {
            for (CodeItem codeItem : code.getCodeItemList()) {
                write(codeItem);
            }
        }
    }
    
    @Override
    public void beforeGenerate() throws Exception {
        FileUtils.initFile();
    }
}
