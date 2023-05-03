package cloud.makeronbean.generate.handler;

import cloud.makeronbean.generate.starter.base.BaseStarter;
import cloud.makeronbean.generate.starter.base.dependency.BaseDependency;
import cloud.makeronbean.generate.starter.base.dependency.DependencyItem;
import cloud.makeronbean.generate.utils.XmlUtils;

import java.io.*;

/**
 * @author makeronbean
 * @createDate 2023-05-01  08:34
 * @description
 */

public class PomHandler extends GenerateHandlerAdapter {
    
    /**
     * 将内存中的xml写到文件中
     *
     * @throws IOException
     */
    public void refresh() throws IOException {
        XmlUtils.refreshPom();
    }
    
    
    /**
     * 处理方法
     */
    @Override
    public void generateHandler(BaseStarter starter) {
        BaseDependency dependency = starter.getDependency();
        if (dependency != null) {
            for (DependencyItem dependencyItem : dependency.getDependencyItemList()) {
                XmlUtils.addNode(dependencyItem);
            }
        }
    }
    
    
    @Override
    public void afterGenerate() throws IOException {
        refresh();
        XmlUtils.closeStream();
    }
    
    
    @Override
    public void beforeGenerate() {
        XmlUtils.initPom();
    }
}
