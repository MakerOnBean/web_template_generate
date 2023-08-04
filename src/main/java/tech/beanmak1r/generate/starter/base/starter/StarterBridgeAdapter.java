package tech.beanmak1r.generate.starter.base.starter;

/**
 * @author makeronbean
 * @createDate 2023-05-04  09:35
 * @description project加载starter时的桥梁
 */
public interface StarterBridgeAdapter {
    
    
    /**
     * 加载starter
     * @param starter 被加载的starter项目
     */
    void loadStarter(AbstractStarter starter);
}
