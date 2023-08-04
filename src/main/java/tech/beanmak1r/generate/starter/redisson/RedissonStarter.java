package tech.beanmak1r.generate.starter.redisson;

import tech.beanmak1r.generate.enums.TagsEnum;
import tech.beanmak1r.generate.project.VersionHolder;
import tech.beanmak1r.generate.starter.base.dependency.DependencyItem;
import tech.beanmak1r.generate.starter.base.starter.StarterAdapter;
import tech.beanmak1r.generate.starter.base.dependency.BaseDependency;
import tech.beanmak1r.generate.starter.base.yaml.BaseYaml;
import tech.beanmak1r.generate.utils.StringUtils;

/**
 * @author makeronbean
 * @createDate 2023-05-03  15:15
 * @description
 */
public class RedissonStarter extends StarterAdapter {

    private final RedissonConfig config = new RedissonConfig();

    private RedissonStarter() {
        this.order = 3;
    }

    public RedissonStarter host(String host) {
        config.setHost(host);
        return this;
    }

    public RedissonStarter port(Integer port) {
        config.setPort(port);
        return this;
    }

    public RedissonStarter database(Integer database) {
        if (database > 15) {
            System.out.println("The value of database ranges from 0 to 15");
            return this;
        }
        config.setDatabase(database);
        return this;
    }

    public RedissonStarter version(String version) {
        config.setVersion(version);
        return this;
    }

    public RedissonStarter password(String password) {
        config.setPassword(password);
        return this;
    }

    @Override
    protected void addDependency(BaseDependency dependency) {
        DependencyItem redisson = new DependencyItem()
                .setPath(TagsEnum.DEPENDENCY)
                .addTag(TagsEnum.GROUP_ID, "org.redisson")
                .addTag(TagsEnum.ARTIFACT_ID, "redisson-spring-boot-starter")
                .addTag(TagsEnum.VERSION, StringUtils.getVersion(config.getVersion(), VersionHolder.DependencyNameEnum.REDISSON));
        dependency.addDependencyItem(redisson);
    }

    @Override
    protected void addYaml(BaseYaml yaml) {
        yaml.addYamlConfig("spring.redis.redisson.config", String.format(
                "singleServerConfig:\n" +
                        "  idleConnectionTimeout: 10000\n" +
                        "  connectTimeout: 10000\n" +
                        "  timeout: 3000\n" +
                        "  retryAttempts: 3\n" +
                        "  retryInterval: 1500\n" +
                        "  password: %s\n" +
                        "  subscriptionsPerConnection: 5\n" +
                        "  clientName: null\n" +
                        "  address: \"redis://%s:%d\"\n" +
                        "  subscriptionConnectionMinimumIdleSize: 1\n" +
                        "  subscriptionConnectionPoolSize: 50\n" +
                        "  connectionMinimumIdleSize: 24\n" +
                        "  connectionPoolSize: 64\n" +
                        "  database: %d\n" +
                        "  dnsMonitoringInterval: 5000\n" +
                        "threads: 16\n" +
                        "nettyThreads: 32\n" +
                        "codec: !<org.redisson.codec.JsonJacksonCodec> {}\n" +
                        "transportMode: \"NIO\"",
                config.getPassword(),
                config.getHost(),
                config.getPort(),
                config.getDatabase()
        ));
    }

}
