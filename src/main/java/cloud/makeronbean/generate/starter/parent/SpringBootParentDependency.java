package cloud.makeronbean.generate.starter.parent;

import cloud.makeronbean.generate.constant.DependencyConst;
import cloud.makeronbean.generate.starter.base.dependency.BaseDependency;
import cloud.makeronbean.generate.starter.base.dependency.DependencyItem;

/**
 * @author makeronbean
 * @createDate 2023-05-03  11:44
 * @description
 */
public class SpringBootParentDependency extends BaseDependency {
    
    @Override
    protected void getDefault() {
        DependencyItem parent = new DependencyItem();
        parent.setTabName(DependencyConst.PARENT.getTabName());
        parent.setGroupId("org.springframework.boot");
        parent.setArtifactId("spring-boot-starter-parent");
        parent.setVersion("2.3.6.RELEASE");
        parent.setParentNodeName(DependencyConst.PROJECT.getTabName());
        this.addDependencyItem(parent);
        
        // devtools
        if (SpringBootParentStarter.config.isDevtools()) {
            DependencyItem devtools = new DependencyItem();
            devtools.setGroupId("org.springframework.boot");
            devtools.setTabName(DependencyConst.DEPENDENCY.getTabName());
            devtools.setArtifactId("spring-boot-devtools");
            devtools.setScope("runtime");
            devtools.setOptional("true");
            devtools.setParentNodeName(DependencyConst.DEPENDENCIES.getTabName());
            this.addDependencyItem(devtools);
        }
        
        //test
        if (SpringBootParentStarter.config.isTest()) {
            DependencyItem test = new DependencyItem();
            test.setGroupId("org.springframework.boot");
            test.setArtifactId("spring-boot-starter-test");
            test.setScope("test");
            test.setParentNodeName(DependencyConst.DEPENDENCIES.getTabName());
            test.setTabName(DependencyConst.DEPENDENCY.getTabName());
            this.addDependencyItem(test);
        }
    }
}
