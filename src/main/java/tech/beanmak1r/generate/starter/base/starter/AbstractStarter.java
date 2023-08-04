package tech.beanmak1r.generate.starter.base.starter;

import tech.beanmak1r.generate.starter.base.code.BaseCode;
import tech.beanmak1r.generate.starter.base.dependency.BaseDependency;
import tech.beanmak1r.generate.starter.base.yaml.BaseYaml;

import java.util.Objects;


/**
 * @author makeronbean
 * @createDate 2023-05-01  08:40
 * @description 依赖基类
 */
public abstract class AbstractStarter {
    
    /**
     * 加载顺序
     */
    protected Integer order = 10;
    
    private final BaseCode code = new BaseCode();
    
    private final BaseDependency dependency = new BaseDependency();
    
    private final BaseYaml yaml = new BaseYaml();
    
    
    /**
     * 生成时调用，加载所有配置
     */
    void load() {
        this.addCode(code);
        this.addDependency(dependency);
        this.addYaml(yaml);
    }
    
    /**
     * 代码模板配置
     * @param code 将 CodeItem 添加到 code 对象中
     */
    protected abstract void addCode(BaseCode code);
    
    
    /**
     * 依赖模板配置
     * @param dependency 将 DependencyItem 添加到 dependency 对象中
     */
    protected abstract void addDependency(BaseDependency dependency);
    
    /**
     * 添加yaml模板配置
     * @param yaml 将配置添加到 yaml 中
     */
    protected abstract void addYaml(BaseYaml yaml);
    
    
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
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractStarter that = (AbstractStarter) o;
        return Objects.equals(order, that.order) && Objects.equals(code, that.code) && Objects.equals(dependency, that.dependency) && Objects.equals(yaml, that.yaml);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(order, code, dependency, yaml);
    }
}