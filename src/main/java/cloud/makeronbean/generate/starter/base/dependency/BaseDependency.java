package cloud.makeronbean.generate.starter.base.dependency;

import java.util.LinkedList;
import java.util.List;

/**
 * @author makeronbean
 * @createDate 2023-05-02  21:33
 * @description
 */

public abstract class BaseDependency {
    
    public BaseDependency() {
        getDefault();
    }
    
    private final List<DependencyItem> dependencyItemList = new LinkedList<>();
    
    /**
     * 获取
     */
    public List<DependencyItem> getDependencyItemList() {
        return this.dependencyItemList;
    }
    
    
    /**
     * 添加依赖项
     * @param item 依赖项
     */
    protected void addDependencyItem(DependencyItem item) {
        dependencyItemList.add(item);
    }
    
    
    /**
     * 填充 dependencyItemList
     */
    protected abstract void getDefault();
}
