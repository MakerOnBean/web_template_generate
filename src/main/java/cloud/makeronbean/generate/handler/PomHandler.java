package cloud.makeronbean.generate.handler;

import cloud.makeronbean.generate.starter.base.starter.AbstractStarter;
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
     * 处理方法
     */
    @Override
    public void generateHandler(AbstractStarter starter) {
        BaseDependency dependency = starter.getDependency();
        if (dependency != null) {
            for (DependencyItem dependencyItem : dependency.getDependencyItemList()) {
                XmlUtils.addNode(dependencyItem);
            }
        }
    }
    
    
    @Override
    public void afterGenerate() throws IOException {
        XmlUtils.refreshPom();
        XmlUtils.closeStream();
    }
    
    
    @Override
    public void beforeGenerate() {
        XmlUtils.initPom();
    }
}
