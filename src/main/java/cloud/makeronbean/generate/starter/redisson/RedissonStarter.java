package cloud.makeronbean.generate.starter.redisson;

import cloud.makeronbean.generate.enums.TagsEnum;
import cloud.makeronbean.generate.project.Project;
import cloud.makeronbean.generate.project.VersionHolder;
import cloud.makeronbean.generate.starter.base.dependency.DependencyItem;
import cloud.makeronbean.generate.starter.base.starter.StarterAdapter;
import cloud.makeronbean.generate.starter.base.dependency.BaseDependency;
import cloud.makeronbean.generate.starter.base.yaml.BaseYaml;
import cloud.makeronbean.generate.utils.StringUtils;

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
                .addTag(TagsEnum.VERSION, StringUtils.isEmpty(
                        config.getVersion()) ?
                        Project.project().versionControls().getVersion(VersionHolder.DependencyNameEnum.REDISSON) :
                        config.getVersion());
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
