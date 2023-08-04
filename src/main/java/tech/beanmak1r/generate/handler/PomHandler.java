package tech.beanmak1r.generate.handler;

import tech.beanmak1r.generate.starter.base.dependency.DependencyItem;
import tech.beanmak1r.generate.starter.base.starter.AbstractStarter;
import tech.beanmak1r.generate.starter.base.dependency.BaseDependency;
import tech.beanmak1r.generate.utils.XmlUtils;

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
        XmlUtils.refreshAndClose();
    }
}
