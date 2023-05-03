package cloud.makeronbean.generate.handler;

import cloud.makeronbean.generate.starter.base.BaseStarter;
import cloud.makeronbean.generate.starter.base.code.BaseCode;
import cloud.makeronbean.generate.starter.base.code.CodeItem;
import cloud.makeronbean.generate.utils.ProjectInfoUtils;
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
    public void write(CodeItem codeItem) throws IOException {
        String path = codeItem.getPath();
        String filePath = ProjectInfoUtils.getJavaPath();
        
        if (path != null && path.length() > 0) {
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
    public void generateHandler(BaseStarter starter) throws Exception {
        BaseCode code = starter.getCode();
        if (code != null) {
            for (CodeItem codeItem : code.getCodeItemList()) {
                write(codeItem);
            }
        }
    }
}
