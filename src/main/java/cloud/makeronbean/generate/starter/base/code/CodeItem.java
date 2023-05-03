package cloud.makeronbean.generate.starter.base.code;


/**
 * @author makeronbean
 * @createDate 2023-05-02  21:14
 * @description 代码片段项
 */

public class CodeItem {
    private String path;
    private String fileName;
    private String codeTemplate;
    
    public CodeItem() {
    }
    
    public CodeItem(String path, String fileName, String codeTemplate) {
        this.path = path;
        this.fileName = fileName;
        this.codeTemplate = codeTemplate;
    }
    
    public String getPath() {
        return path;
    }
    
    public void setPath(String path) {
        this.path = path;
    }
    
    public String getFileName() {
        return fileName;
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    public String getCodeTemplate() {
        return codeTemplate;
    }
    
    public void setCodeTemplate(String codeTemplate) {
        this.codeTemplate = codeTemplate;
    }
}
