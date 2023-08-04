package tech.beanmak1r.generate.starter.datebase.mybatis.mybaitsflex;

import tech.beanmak1r.generate.starter.datebase.mybatis.MyBatisGenerateConfig;
import tech.beanmak1r.generate.utils.StringUtils;
import com.mybatisflex.codegen.Generator;
import com.mybatisflex.codegen.config.ColumnConfig;
import com.mybatisflex.codegen.config.EntityConfig;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.mybatisflex.codegen.dialect.IDialect;
import com.mybatisflex.codegen.dialect.JdbcTypeMapping;
import com.zaxxer.hikari.HikariDataSource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MyBatisFlexGenerateAction {

    private static MyBatisFlexGenerateAction action;

    public static MyBatisFlexGenerateAction getInstance() {
        if (action == null) {
            action = new MyBatisFlexGenerateAction();
        }
        return action;
    }

    public void generate(MyBatisGenerateConfig config) {
        //配置数据源
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(config.getJdbcUrl());
        dataSource.setUsername(config.getUsername());
        dataSource.setPassword(config.getPassword());

        //创建配置内容，两种风格都可以。
        GlobalConfig globalConfig = createGlobalConfig(config);

        //通过 datasource 和 globalConfig 创建代码生成器
        Generator generator = new Generator(dataSource, globalConfig, IDialect.MYSQL);

        JdbcTypeMapping.registerMapping(LocalDateTime.class, LocalDateTime.class);
        JdbcTypeMapping.registerMapping(LocalDate.class, LocalDate.class);

        //生成代码
        generator.generate();
    }

    public GlobalConfig createGlobalConfig(MyBatisGenerateConfig config) {
        //创建配置内容
        GlobalConfig globalConfig = new GlobalConfig();

        // 注释配置
        globalConfig.getJavadocConfig()
                .setAuthor(config.getAuthor())
                .setSince(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

        if (!StringUtils.isEmpty(config.getTablePrefix())) {
            globalConfig.setTablePrefix(config.getTablePrefix());
        }
        globalConfig.setGenerateTable(config.getGenerateTable().toArray(new String[0]));

        //包路径配置
        globalConfig
                .getPackageConfig()
                .setBasePackage(config.getBasePackage());

        ColumnConfig createTime = new ColumnConfig();
        createTime.setColumnName(config.getCreateTimeColumn());
        createTime.setOnInsertValue("now()");

        ColumnConfig updateTime = new ColumnConfig();
        updateTime.setColumnName(config.getUpdateTimeColumn());
        updateTime.setOnInsertValue("now()");
        updateTime.setOnUpdateValue("now()");

        // 策略配置
        globalConfig.getStrategyConfig()
                .setLogicDeleteColumn(config.getLogicDeleteColumn())
                .setColumnConfig(createTime)
                .setColumnConfig(updateTime);

        globalConfig.setControllerTemplatePath("controller.tpl");

        // Entity 配置
        globalConfig.enableEntity()
                .setWithActiveRecord(config.isEnableActiveRecord())
                .setWithSwagger(config.isEnableSwagger())
                .setSwaggerVersion(EntityConfig.SwaggerVersion.DOC)
                .setWithLombok(true)
                .setOverwriteEnable(true);

        // Mapper 配置
        globalConfig.enableMapper()
                .setMapperAnnotation(true)
                .setOverwriteEnable(true);

        // Service 配置
        globalConfig.enableService()
                .setOverwriteEnable(true);

        // ServiceImpl 配置
        globalConfig.enableServiceImpl()
                .setOverwriteEnable(true);

        // Controller 配置
        globalConfig.enableController()
                .setOverwriteEnable(true)
                .setRestStyle(true);

        return globalConfig;
    }

}