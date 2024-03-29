package tech.beanmak1r.generate.starter.redis;

import tech.beanmak1r.generate.enums.TagsEnum;
import tech.beanmak1r.generate.starter.base.code.BaseCode;
import tech.beanmak1r.generate.starter.base.code.CodeItem;
import tech.beanmak1r.generate.starter.base.dependency.BaseDependency;
import tech.beanmak1r.generate.starter.base.dependency.DependencyItem;
import tech.beanmak1r.generate.starter.base.starter.StarterAdapter;
import tech.beanmak1r.generate.starter.base.yaml.BaseYaml;
import tech.beanmak1r.generate.utils.StringUtils;

/**
 * redis starter
 *
 * @author beanMak1r
 * @since 2023-08-01 07:50
 */
public class RedisStarter extends StarterAdapter {
    private final RedisConfig config = new RedisConfig();

    private RedisStarter() {
        this.order = 3;
    }

    public RedisStarter host(String host) {
        config.setHost(host);
        return this;
    }

    public RedisStarter port(Integer port) {
        config.setPort(port);
        return this;
    }

    public RedisStarter database(Integer database) {
        if (database > 15) {
            System.out.println("The value of database ranges from 0 to 15");
            return this;
        }
        config.setDatabase(database);
        return this;
    }

    public RedisStarter password(String password) {
        config.setPassword(password);
        return this;
    }

    @Override
    protected void addDependency(BaseDependency dependency) {
        DependencyItem redis = new DependencyItem()
                .setPath(TagsEnum.DEPENDENCY)
                .addTag(TagsEnum.GROUP_ID, "org.springframework.boot")
                .addTag(TagsEnum.ARTIFACT_ID, "spring-boot-starter-data-redis");
        dependency.addDependencyItem(redis);

        DependencyItem redisPool = new DependencyItem()
                .setPath(TagsEnum.DEPENDENCY)
                .addTag(TagsEnum.GROUP_ID, "org.apache.commons")
                .addTag(TagsEnum.ARTIFACT_ID, "commons-pool2");
        dependency.addDependencyItem(redisPool);
    }

    @Override
    protected void addCode(BaseCode code) {
        CodeItem config = new CodeItem();
        config.setPath("config/redis");
        config.setFileName("RedisConfig.java");
        config.setCodeTemplate("import org.springframework.context.annotation.Bean;\n" +
                "import org.springframework.data.redis.connection.RedisConnectionFactory;\n" +
                "import org.springframework.data.redis.core.RedisTemplate;\n" +
                "import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;\n" +
                "import org.springframework.data.redis.serializer.StringRedisSerializer;\n" +
                "\n" +
                "public class RedisConfig {\n" +
                "    @Bean\n" +
                "    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {\n" +
                "        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();\n" +
                "        //  设置redis的连接池工厂。\n" +
                "        redisTemplate.setConnectionFactory(redisConnectionFactory);\n" +
                "        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer();\n" +
                "        //  将Redis 中 string ，hash 数据类型，自动序列化！\n" +
                "        redisTemplate.setKeySerializer(new StringRedisSerializer());\n" +
                "        redisTemplate.setValueSerializer(serializer);\n" +
                "        //  设置数据类型是Hash 的 序列化！\n" +
                "        redisTemplate.setHashKeySerializer(new StringRedisSerializer());\n" +
                "        redisTemplate.setHashValueSerializer(serializer);\n" +
                "        redisTemplate.afterPropertiesSet();\n" +
                "        return redisTemplate;\n" +
                "    }\n" +
                "}\n");
        code.addCodeItem(config);
    }

    @Override
    protected void addYaml(BaseYaml yaml) {
        yaml.addYamlConfig("spring.redis.database", config.getDatabase());
        yaml.addYamlConfig("spring.redis.host", config.getHost());
        yaml.addYamlConfig("spring.redis.port", config.getPort());
        if (!StringUtils.isEmpty(config.getPassword())) {
            yaml.addYamlConfig("spring.redis.password", config.getPassword());
        }
    }
}
