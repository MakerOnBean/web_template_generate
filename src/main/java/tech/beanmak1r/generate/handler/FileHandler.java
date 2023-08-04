package tech.beanmak1r.generate.handler;

import tech.beanmak1r.generate.project.Project;
import tech.beanmak1r.generate.starter.base.starter.AbstractStarter;
import tech.beanmak1r.generate.starter.base.code.BaseCode;
import tech.beanmak1r.generate.starter.base.code.CodeItem;
import tech.beanmak1r.generate.utils.StringUtils;

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
     * @throws IOException 文件创建失败时抛出
     */
    private void write(CodeItem codeItem) throws IOException {
        String path = codeItem.getPath();
        String filePath = Project.project().javaPath();

        if (path != null && !path.isEmpty()) {
            filePath += "/" + path;
        }

        File file = new File(filePath);
        if (!file.exists()) {
            boolean success = file.mkdirs();
            if (!success) {
                throw new IOException("文件创建失败");
            }
        }

        File codeFile = new File(filePath + "/" + codeItem.getFileName());
        boolean success = codeFile.createNewFile();
        if (!success) {
            throw new IOException("文件创建失败");
        }

        String code = StringUtils.addPackage(codeItem.getPath().replaceAll("/", "."), codeItem.getCodeTemplate());

        try (FileOutputStream fos = new FileOutputStream(codeFile)) {
            fos.write(code.getBytes(StandardCharsets.UTF_8));
        }
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
}
