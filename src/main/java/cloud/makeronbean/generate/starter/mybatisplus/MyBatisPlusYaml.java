package cloud.makeronbean.generate.starter.mybatisplus;

import cloud.makeronbean.generate.starter.base.yaml.BaseYaml;
import cloud.makeronbean.generate.utils.ProjectInfoUtils;

/**
 * @author makeronbean
 * @createDate 2023-05-03  13:04
 * @description
 */
public class MyBatisPlusYaml extends BaseYaml {
    
    
    @Override
    protected void getDefault() {
        this.addConfig("spring.datasource.username", MyBatisPlusStarter.config.getUsername());
        this.addConfig("spring.datasource.password", MyBatisPlusStarter.config.getPassword());
        this.addConfig("spring.datasource.driver-class-name", "com.mysql.cj.jdbc.Driver");
        this.addConfig("spring.datasource.url", String.format("jdbc:mysql://localhost:%d/%s?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai", MyBatisPlusStarter.config.getPort(), MyBatisPlusStarter.config.getDbName()));
        this.addConfig("mybatis-plus.configuration.log-impl", "org.apache.ibatis.logging.stdout.StdOutImpl");
        this.addConfig("mybatis-plus.configuration.map-underscore-to-camel-case", true);
        this.addConfig("mybatis-plus.type-aliases-package", ProjectInfoUtils.basePackage + ".entity");
        // 是否开启逻辑删除
        if (MyBatisPlusStarter.config.isLogicDelete()) {
            this.addConfig("mybatis-plus.global-config.db-config.logic-delete-field", "isDelete");
            this.addConfig("mybatis-plus.global-config.db-config.logic-delete-value", 1);
            this.addConfig("mybatis-plus.global-config.db-config.logic-not-delete-value", 0);
        }
    }
}
