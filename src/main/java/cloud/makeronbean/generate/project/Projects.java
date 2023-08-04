package cloud.makeronbean.generate.project;

import cloud.makeronbean.generate.starter.base.factory.StarterFactory;
import cloud.makeronbean.generate.starter.base.factory.StarterFactorySingleImpl;
import cloud.makeronbean.generate.starter.knife4j.Knife4jStarter;
import cloud.makeronbean.generate.starter.lombok.LombokStarter;
import cloud.makeronbean.generate.starter.mybatisplus.MyBatisPlusStarter;
import cloud.makeronbean.generate.starter.mysql.MySqlStarter;
import cloud.makeronbean.generate.starter.parent.SpringBootParentStarter;
import cloud.makeronbean.generate.starter.redis.RedisStarter;
import cloud.makeronbean.generate.starter.web.SpringBootWebStarter;

/**
 * @author makeronbean
 * @createDate 2023-05-03  15:47
 * @description
 */
public class Projects {
    private static Project PROJECT;
    private static final StarterFactory FACTORY = new StarterFactorySingleImpl();


    /**
     * 全部装配
     *
     * @param dbName     数据库名称
     * @param dbPassword 数据库密码
     * @return Project对象
     */
    public static Project all(String dbName, String dbPassword) {
        PROJECT = Project.project();

        SpringBootParentStarter parentStarter = FACTORY.getInstance(SpringBootParentStarter.class);
        SpringBootWebStarter webStarter = FACTORY.getInstance(SpringBootWebStarter.class);

        MyBatisPlusStarter myBatisPlusStarter = FACTORY.getInstance(MyBatisPlusStarter.class);

        MySqlStarter mySqlStarter = FACTORY.getInstance(MySqlStarter.class);
        mySqlStarter.dbName(dbName).password(dbPassword);

        LombokStarter lombokStarter = FACTORY.getInstance(LombokStarter.class);

        Knife4jStarter knife4jStarter = FACTORY.getInstance(Knife4jStarter.class);

        RedisStarter redisStarter = FACTORY.getInstance(RedisStarter.class);

        return PROJECT
                .addStarter(parentStarter)
                .addStarter(webStarter)
                .addStarter(mySqlStarter)
                .addStarter(myBatisPlusStarter)
                .addStarter(lombokStarter)
                .addStarter(knife4jStarter)
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
        PROJECT = Project.project();

        SpringBootParentStarter parentStarter = FACTORY.getInstance(SpringBootParentStarter.class);

        SpringBootWebStarter webStarter = FACTORY.getInstance(SpringBootWebStarter.class);
        webStarter.asserts(false);
        webStarter.valid(false);

        MyBatisPlusStarter myBatisPlusStarter = FACTORY.getInstance(MyBatisPlusStarter.class);

        MySqlStarter mySqlStarter = FACTORY.getInstance(MySqlStarter.class);
        mySqlStarter.dbName(dbName).password(dbPassword);

        LombokStarter lombokStarter = FACTORY.getInstance(LombokStarter.class);

        return PROJECT
                .addStarter(parentStarter)
                .addStarter(webStarter)
                .addStarter(mySqlStarter)
                .addStarter(myBatisPlusStarter)
                .addStarter(lombokStarter);
    }


    /**
     * 自定义
     *
     * @return Project对象
     */
    public static Project customer() {
        return Project.project();
    }
}
