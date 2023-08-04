package tech.beanmak1r.generate.project;

import tech.beanmak1r.generate.starter.base.factory.StarterFactory;
import tech.beanmak1r.generate.starter.base.factory.StarterFactorySingleImpl;
import tech.beanmak1r.generate.starter.datebase.mybatis.MyBatisGenerateConfig;
import tech.beanmak1r.generate.starter.datebase.mybatis.mybaitsflex.MyBatisFlexStarter;
import tech.beanmak1r.generate.starter.lombok.LombokStarter;
import tech.beanmak1r.generate.starter.datebase.mysql.MySqlStarter;
import tech.beanmak1r.generate.starter.parent.SpringBootParentStarter;
import tech.beanmak1r.generate.starter.redis.RedisStarter;
import tech.beanmak1r.generate.starter.springdc.SpringDocStarter;
import tech.beanmak1r.generate.starter.web.SpringBootWebStarter;

/**
 * @author makeronbean
 * @createDate 2023-05-03  15:47
 * @description
 */
public class Projects {
    private static final Project PROJECT = Project.project();
    private static final StarterFactory FACTORY = StarterFactorySingleImpl.getStarterFactory();


    /**
     * 全部装配
     *
     * @param dbName     数据库名称
     * @param dbPassword 数据库密码
     * @return Project对象
     */
    public static Project all(String dbName, String dbPassword) {
        return all(dbName, dbPassword, null);
    }

    /**
     * 全部装配，使用 MyBatis 代码生成器
     *
     * @param dbName     数据库名称
     * @param dbPassword 数据库密码
     * @param config     MyBatis 代码生成器配置对象
     * @return Project对象
     */
    public static Project all(String dbName, String dbPassword, MyBatisGenerateConfig config) {
        SpringBootParentStarter parentStarter = FACTORY.getInstance(SpringBootParentStarter.class);
        SpringBootWebStarter webStarter = FACTORY.getInstance(SpringBootWebStarter.class);

        MyBatisFlexStarter myBatisFlexStarter = FACTORY.getInstance(MyBatisFlexStarter.class);
        if (config != null) {
            myBatisFlexStarter.enableMyBatisFlexGenerate(config);
        }

        MySqlStarter mySqlStarter = FACTORY.getInstance(MySqlStarter.class);
        mySqlStarter.dbName(dbName).password(dbPassword);

        LombokStarter lombokStarter = FACTORY.getInstance(LombokStarter.class);

        SpringDocStarter springDocStarter = FACTORY.getInstance(SpringDocStarter.class);

        RedisStarter redisStarter = FACTORY.getInstance(RedisStarter.class);

        return PROJECT
                .addStarter(parentStarter)
                .addStarter(webStarter)
                .addStarter(mySqlStarter)
                .addStarter(myBatisFlexStarter)
                .addStarter(lombokStarter)
                .addStarter(springDocStarter)
                .addStarter(redisStarter);
    }


    /**
     * 简单配置 ssm + lombok
     *
     * @param dbName     数据库名称
     * @param dbPassword 数据库密码
     * @return Project对象
     */
    public static Project simple(String dbName, String dbPassword) {
        return simple(dbName, dbPassword, null);
    }


    /**
     * 简单配置 ssm + lombok，使用 MyBatis 代码生成器
     *
     * @param dbName     数据库名称
     * @param dbPassword 数据库密码
     * @param config     MyBatis 代码生成器配置对象
     * @return Project对象
     */
    public static Project simple(String dbName, String dbPassword, MyBatisGenerateConfig config) {
        SpringBootParentStarter parentStarter = FACTORY.getInstance(SpringBootParentStarter.class);

        SpringBootWebStarter webStarter = FACTORY.getInstance(SpringBootWebStarter.class);
        webStarter.asserts(false);
        webStarter.valid(false);

        MyBatisFlexStarter myBatisFlexStarter = FACTORY.getInstance(MyBatisFlexStarter.class);
        if (config != null) {
            myBatisFlexStarter.enableMyBatisFlexGenerate(config);
        }

        MySqlStarter mySqlStarter = FACTORY.getInstance(MySqlStarter.class);
        mySqlStarter.dbName(dbName).password(dbPassword);

        LombokStarter lombokStarter = FACTORY.getInstance(LombokStarter.class);

        return PROJECT
                .addStarter(parentStarter)
                .addStarter(webStarter)
                .addStarter(mySqlStarter)
                .addStarter(myBatisFlexStarter)
                .addStarter(lombokStarter);
    }


    /**
     * 自定义
     *
     * @return Project对象
     */
    public static Project customer() {
        return PROJECT;
    }
}
