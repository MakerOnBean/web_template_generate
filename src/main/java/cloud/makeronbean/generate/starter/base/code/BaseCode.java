package cloud.makeronbean.generate.starter.base.code;

import java.util.LinkedList;
import java.util.List;

/**
 * @author makeronbean
 * @createDate 2023-05-02  21:16
 * @description
 */

public abstract class BaseCode {
    
    public BaseCode() {
        getDefault();
    }
    
    private final List<CodeItem> codeItemList = new LinkedList<>();
    
    
    /**
     * 获取
     */
    public List<CodeItem> getCodeItemList() {
        return this.codeItemList;
    }
    
    
    /**
     * 添加代码片段
     * @param item 代码片段
     */
    protected void addCodeItem(CodeItem item) {
        codeItemList.add(item);
    }
    
    
    /**
     * 填充 codeItemList
     */
    protected abstract void getDefault();
    
}
