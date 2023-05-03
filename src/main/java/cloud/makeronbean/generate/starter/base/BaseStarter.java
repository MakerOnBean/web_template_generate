package cloud.makeronbean.generate.starter.base;

import cloud.makeronbean.generate.starter.base.code.BaseCode;
import cloud.makeronbean.generate.starter.base.dependency.BaseDependency;
import cloud.makeronbean.generate.starter.base.yaml.BaseYaml;


/**
 * @author makeronbean
 * @createDate 2023-05-01  08:40
 * @description 依赖基类
 */
public abstract class BaseStarter {
    
    /**
     * 加载顺序
     */
    protected Integer order;
    
    protected BaseCode code;
    
    protected BaseDependency dependency;
    
    protected BaseYaml yaml;
    
    public abstract void load();
    
    @Override
    public boolean equals(Object o) {
        return this.getClass().equals(o.getClass());
    }
    
    public Integer getOrder() {
        return order;
    }
    
    public BaseCode getCode() {
        return code;
    }
    
    public BaseDependency getDependency() {
        return dependency;
    }
    
    public BaseYaml getYaml() {
        return yaml;
    }
}