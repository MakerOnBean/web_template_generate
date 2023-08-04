package tech.beanmak1r.generate.starter.datebase.mybatis.mybaitsflex;

import tech.beanmak1r.generate.handler.SupportHandler;
import tech.beanmak1r.generate.project.Project;
import tech.beanmak1r.generate.starter.base.factory.StarterFactory;
import tech.beanmak1r.generate.starter.base.factory.StarterFactorySingleImpl;
import tech.beanmak1r.generate.starter.base.starter.AbstractStarter;
import tech.beanmak1r.generate.starter.datebase.DatabaseConfig;
import tech.beanmak1r.generate.starter.datebase.mybatis.MyBatisGenerateConfig;
import tech.beanmak1r.generate.starter.datebase.mysql.MySqlStarter;
import tech.beanmak1r.generate.starter.springdc.SpringDocStarter;
import tech.beanmak1r.generate.utils.StringUtils;

/**
 * MyBatisFlex 代码生成处理器
 *
 * @author beanMak1r
 * @since 2023-08-04 09:41
 */
public class MyBatisFlexGenerateHandler implements SupportHandler {
    StarterFactory starterFactory = StarterFactorySingleImpl.getStarterFactory();

    private MyBatisGenerateConfig generateConfig;

    @Override
    public void beforeGenerate() {
        MySqlStarter mySqlStarter = starterFactory.getInstance(MySqlStarter.class);
        DatabaseConfig dbConfig = mySqlStarter.getConfig();

        // 作者信息
        if (StringUtils.isEmpty(generateConfig.getAuthor())) {
            generateConfig.setAuthor(Project.project().author());
        }

        // basePackage
        if (StringUtils.isEmpty(generateConfig.getBasePackage())) {
            generateConfig.setBasePackage(Project.project().basePackage());
        }

        // jdbcUrl
        if (StringUtils.isEmpty(generateConfig.getJdbcUrl())) {
            generateConfig.setJdbcUrl(String.format("jdbc:mysql://%s:%d/%s?characterEncoding=utf-8&tinyInt1isBit=true", dbConfig.getHost(), dbConfig.getPort(), dbConfig.getDbName()));
        }

        // username
        if (StringUtils.isEmpty(generateConfig.getUsername())) {
            generateConfig.setUsername(dbConfig.getUsername());
        }

        // password
        if (StringUtils.isEmpty(generateConfig.getPassword())) {
            generateConfig.setPassword(dbConfig.getPassword());
        }

        // springdoc
        if (generateConfig.isEnableSwagger()) {
            if (Project.project().getStarterByType(SpringDocStarter.class) == null) {
                generateConfig.setEnableSwagger(false);
            }
        }
    }

    @Override
    public void generateHandler(AbstractStarter starter) throws Exception {
        if (starter instanceof MyBatisFlexStarter) {
            if (generateConfig.getGenerateTable().isEmpty()) {
                return;
            }
            MyBatisFlexGenerateAction action = MyBatisFlexGenerateAction.getInstance();
            action.generate(generateConfig);
        }
    }

    @Override
    public void afterGenerate() throws Exception {
    }

    @Override
    public boolean isSupport() {
        MyBatisFlexStarter myBatisFlexStarter = starterFactory.getInstance(MyBatisFlexStarter.class);
        this.generateConfig = myBatisFlexStarter.config.getGenerateConfig();
        return generateConfig != null && generateConfig.isEnable();
    }
}
