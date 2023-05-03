package cloud.makeronbean.generate.starter.web;

import cloud.makeronbean.generate.constant.DependencyConst;
import cloud.makeronbean.generate.starter.base.dependency.BaseDependency;
import cloud.makeronbean.generate.starter.base.dependency.DependencyItem;

/**
 * @author makeronbean
 * @createDate 2023-05-02  21:38
 * @description
 */

public class SpringBootWebDependency extends BaseDependency {
    
    @Override
    protected void getDefault() {
        // web starter
        DependencyItem web = new DependencyItem();
        web.setGroupId("org.springframework.boot");
        web.setArtifactId("spring-boot-starter-web");
        web.setTabName(DependencyConst.DEPENDENCY.getTabName());
        web.setParentNodeName(DependencyConst.DEPENDENCIES.getTabName());
        this.addDependencyItem(web);
        
        // 参数校验
        if (SpringBootWebStarter.config.isValid()) {
            DependencyItem validation = new DependencyItem();
            validation.setGroupId("org.springframework.boot");
            validation.setArtifactId("spring-boot-starter-validation");
            validation.setTabName(DependencyConst.DEPENDENCY.getTabName());
            validation.setParentNodeName(DependencyConst.DEPENDENCIES.getTabName());
            this.addDependencyItem(validation);
        }
    }
}
