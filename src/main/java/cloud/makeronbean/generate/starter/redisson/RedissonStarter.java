package cloud.makeronbean.generate.starter.redisson;

import cloud.makeronbean.generate.constant.DependencyConst;
import cloud.makeronbean.generate.starter.base.starter.StarterAdapter;
import cloud.makeronbean.generate.starter.base.dependency.BaseDependency;
import cloud.makeronbean.generate.starter.base.dependency.DependencyItem;
import cloud.makeronbean.generate.starter.base.yaml.BaseYaml;

/**
 * @author makeronbean
 * @createDate 2023-05-03  15:15
 * @description
 */
public class RedissonStarter extends StarterAdapter {
    
    private final RedissonConfig config = new RedissonConfig();
    
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
    
    @Override
    protected void addDependency(BaseDependency dependency) {
        DependencyItem redisson = new DependencyItem();
        redisson.setGroupId("org.redisson");
        redisson.setTabName(DependencyConst.DEPENDENCY.getTabName());
        redisson.setArtifactId("redisson-spring-boot-starter");
        redisson.setVersion(config.getVersion());
        redisson.setParentNodeName(DependencyConst.DEPENDENCIES.getTabName());
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
                        "  password: null\n" +
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
                config.getHost(),
                config.getPort(),
                config.getDatabase()
        ));
    }
    
}
