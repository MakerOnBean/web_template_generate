package cloud.makeronbean.generate.starter.mybatisplus;

import cloud.makeronbean.generate.constant.DependencyConst;
import cloud.makeronbean.generate.starter.base.dependency.BaseDependency;
import cloud.makeronbean.generate.starter.base.dependency.DependencyItem;

/**
 * @author makeronbean
 * @createDate 2023-05-03  12:58
 * @description
 */
public class MyBatisPlusDependency extends BaseDependency {
    
    
    @Override
    protected void getDefault() {
        DependencyItem mybatisPlus = new DependencyItem();
        mybatisPlus.setGroupId("com.baomidou");
        mybatisPlus.setTabName(DependencyConst.DEPENDENCY.getTabName());
        mybatisPlus.setArtifactId("mybatis-plus-boot-starter");
        mybatisPlus.setVersion("3.4.1");
        mybatisPlus.setParentNodeName(DependencyConst.DEPENDENCIES.getTabName());
        this.addDependencyItem(mybatisPlus);
        
        DependencyItem mysql = new DependencyItem();
        mysql.setTabName(DependencyConst.DEPENDENCY.getTabName());
        mysql.setGroupId("mysql");
        mysql.setArtifactId("mysql-connector-java");
        mysql.setVersion(MyBatisPlusStarter.config.getMysqlVersion());
        mysql.setParentNodeName(DependencyConst.DEPENDENCIES.getTabName());
        this.addDependencyItem(mysql);
    }
}
