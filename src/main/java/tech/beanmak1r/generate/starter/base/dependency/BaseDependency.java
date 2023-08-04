package tech.beanmak1r.generate.starter.base.dependency;

import java.util.LinkedList;
import java.util.List;

/**
 * @author makeronbean
 * @createDate 2023-05-02  21:33
 * @description
 */

public class BaseDependency {
    
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
    public void addDependencyItem(DependencyItem item) {
        dependencyItemList.add(item);
    }
}
