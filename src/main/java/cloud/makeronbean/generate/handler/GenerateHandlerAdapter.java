package cloud.makeronbean.generate.handler;

import cloud.makeronbean.generate.starter.base.BaseStarter;

/**
 * @author makeronbean
 * @createDate 2023-05-03  14:14
 * @description
 */
public abstract class GenerateHandlerAdapter implements GenerateHandler {
    @Override
    public void afterGenerate() throws Exception {
    }
    
    @Override
    public boolean equals(Object obj) {
        return this.getClass().equals(obj.getClass());
    }
}
