package tech.beanmak1r.generate.handler;

import java.util.Objects;

/**
 * @author makeronbean
 * @createDate 2023-05-03  14:14
 * @description
 */
public abstract class GenerateHandlerAdapter implements GenerateHandler {
    
    @Override
    public void beforeGenerate() throws Exception {
    }
    
    @Override
    public void afterGenerate() throws Exception {
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj != null && Objects.equals(this.getClass(), obj.getClass());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getClass());
    }
}
