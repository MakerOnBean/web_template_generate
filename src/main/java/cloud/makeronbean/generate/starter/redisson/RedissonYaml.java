package cloud.makeronbean.generate.starter.redisson;

import cloud.makeronbean.generate.starter.base.yaml.BaseYaml;

/**
 * @author makeronbean
 * @createDate 2023-05-03  15:22
 * @description
 */
public class RedissonYaml extends BaseYaml {
    @Override
    protected void getDefault() {
        // 默认单机配置
        this.addConfig("spring.redis.redisson.config", String.format(
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
                RedissonStarter.config.getHost(),
                RedissonStarter.config.getPort(),
                RedissonStarter.config.getDatabase()
        ));
    }
}
