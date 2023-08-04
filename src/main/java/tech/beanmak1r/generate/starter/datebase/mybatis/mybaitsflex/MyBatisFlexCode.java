package tech.beanmak1r.generate.starter.datebase.mybatis.mybaitsflex;

import tech.beanmak1r.generate.project.Project;
import tech.beanmak1r.generate.starter.base.code.BaseCode;
import tech.beanmak1r.generate.starter.base.code.CodeItem;

/**
 * MyBatisFlex 代码模板
 *
 * @author beanMak1r
 * @since 2023-08-03 18:03
 */
public class MyBatisFlexCode {
    private final BaseCode code;

    public MyBatisFlexCode(BaseCode code) {
        this.code = code;
    }


    public void fillData() {
        addConfig();
        addIdWorker();
        addIdGenerator();
    }

    private void addIdGenerator() {
        CodeItem idGenerator = new CodeItem();
        idGenerator.setPath("config/mybatis");
        idGenerator.setFileName("SnowflakeIDKeyGenerator.java");
        idGenerator.setCodeTemplate("import com.mybatisflex.core.keygen.IKeyGenerator;\n" +
                "import org.springframework.context.annotation.Configuration;\n" +
                "\n" +
                "\n" +
                "@Configuration\n" +
                "public class SnowflakeIDKeyGenerator implements IKeyGenerator {\n" +
                "\n" +
                "    private final IdWorker idWorker = new IdWorker();\n" +
                "\n" +
                "    @Override\n" +
                "    public Object generate(Object entity, String keyColumn) {\n" +
                "        return idWorker.nextId();\n" +
                "    }\n" +
                "}");
        code.addCodeItem(idGenerator);
    }


    private void addIdWorker() {

        CodeItem idWorker = new CodeItem();
        idWorker.setPath("config/mybatis");
        idWorker.setFileName("IdWorker.java");
        idWorker.setCodeTemplate("import java.lang.management.ManagementFactory;\n" +
                "import java.net.InetAddress;\n" +
                "import java.net.NetworkInterface;\n" +
                "\n" +
                "public class IdWorker {\n" +
                "    // 时间起始标记点，作为基准，一般取系统的最近时间（一旦确定不能变动）\n" +
                "    private final static long twepoch = 1288834974657L;\n" +
                "    // 机器标识位数\n" +
                "    private final static long workerIdBits = 5L;\n" +
                "    // 数据中心标识位数\n" +
                "    private final static long datacenterIdBits = 5L;\n" +
                "    // 机器ID最大值\n" +
                "    private final static long maxWorkerId = ~(-1L << workerIdBits);\n" +
                "    // 数据中心ID最大值\n" +
                "    private final static long maxDatacenterId = ~(-1L << datacenterIdBits);\n" +
                "    // 毫秒内自增位\n" +
                "    private final static long sequenceBits = 12L;\n" +
                "    // 机器ID偏左移12位\n" +
                "    private final static long workerIdShift = sequenceBits;\n" +
                "    // 数据中心ID左移17位\n" +
                "    private final static long datacenterIdShift = sequenceBits + workerIdBits;\n" +
                "    // 时间毫秒左移22位\n" +
                "    private final static long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;\n" +
                "\n" +
                "    private final static long sequenceMask = ~(-1L << sequenceBits);\n" +
                "    /* 上次生产id时间戳 */\n" +
                "    private static long lastTimestamp = -1L;\n" +
                "    // 0，并发控制\n" +
                "    private long sequence = 0L;\n" +
                "\n" +
                "    private final long workerId;\n" +
                "    // 数据标识id部分\n" +
                "    private final long datacenterId;\n" +
                "\n" +
                "    public IdWorker(){\n" +
                "        this.datacenterId = getDatacenterId(maxDatacenterId);\n" +
                "        this.workerId = getMaxWorkerId(datacenterId, maxWorkerId);\n" +
                "    }\n" +
                "    /**\n" +
                "     * @param workerId 工作机器ID\n" +
                "     * @param datacenterId 机房ID\n" +
                "     */\n" +
                "    public IdWorker(long workerId, long datacenterId) {\n" +
                "        if (workerId > maxWorkerId || workerId < 0) {\n" +
                "            throw new IllegalArgumentException(String.format(\"worker Id can't be greater than %d or less than 0\", maxWorkerId));\n" +
                "        }\n" +
                "        if (datacenterId > maxDatacenterId || datacenterId < 0) {\n" +
                "            throw new IllegalArgumentException(String.format(\"datacenter Id can't be greater than %d or less than 0\", maxDatacenterId));\n" +
                "        }\n" +
                "        this.workerId = workerId;\n" +
                "        this.datacenterId = datacenterId;\n" +
                "    }\n" +
                "    /**\n" +
                "     * 获取下一个ID\n" +
                "     *\n" +
                "     * @return id\n" +
                "     */\n" +
                "    public synchronized long nextId() {\n" +
                "        long timestamp = timeGen();\n" +
                "        if (timestamp < lastTimestamp) {\n" +
                "            throw new RuntimeException(String.format(\"Clock moved backwards.  Refusing to generate id for %d milliseconds\", lastTimestamp - timestamp));\n" +
                "        }\n" +
                "\n" +
                "        if (lastTimestamp == timestamp) {\n" +
                "            // 当前毫秒内，则+1\n" +
                "            sequence = (sequence + 1) & sequenceMask;\n" +
                "            if (sequence == 0) {\n" +
                "                // 当前毫秒内计数满了，则等待下一秒\n" +
                "                timestamp = tilNextMillis(lastTimestamp);\n" +
                "            }\n" +
                "        } else {\n" +
                "            sequence = 0L;\n" +
                "        }\n" +
                "        lastTimestamp = timestamp;\n" +
                "        // ID偏移组合生成最终的ID，并返回ID\n" +
                "        long nextId = ((timestamp - twepoch) << timestampLeftShift)\n" +
                "                | (datacenterId << datacenterIdShift)\n" +
                "                | (workerId << workerIdShift) | sequence;\n" +
                "\n" +
                "        return nextId;\n" +
                "    }\n" +
                "\n" +
                "    private long tilNextMillis(final long lastTimestamp) {\n" +
                "        long timestamp = this.timeGen();\n" +
                "        while (timestamp <= lastTimestamp) {\n" +
                "            timestamp = this.timeGen();\n" +
                "        }\n" +
                "        return timestamp;\n" +
                "    }\n" +
                "\n" +
                "    private long timeGen() {\n" +
                "        return System.currentTimeMillis();\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * <p>\n" +
                "     * 获取 maxWorkerId\n" +
                "     * </p>\n" +
                "     */\n" +
                "    protected static long getMaxWorkerId(long datacenterId, long maxWorkerId) {\n" +
                "        StringBuffer mpid = new StringBuffer();\n" +
                "        mpid.append(datacenterId);\n" +
                "        String name = ManagementFactory.getRuntimeMXBean().getName();\n" +
                "        if (!name.isEmpty()) {\n" +
                "            /*\n" +
                "             * GET jvmPid\n" +
                "             */\n" +
                "            mpid.append(name.split(\"@\")[0]);\n" +
                "        }\n" +
                "        /*\n" +
                "         * MAC + PID 的 hashcode 获取16个低位\n" +
                "         */\n" +
                "        return (mpid.toString().hashCode() & 0xffff) % (maxWorkerId + 1);\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * <p>\n" +
                "     * 数据标识id部分\n" +
                "     * </p>\n" +
                "     */\n" +
                "    protected static long getDatacenterId(long maxDatacenterId) {\n" +
                "        long id = 0L;\n" +
                "        try {\n" +
                "            InetAddress ip = InetAddress.getLocalHost();\n" +
                "            NetworkInterface network = NetworkInterface.getByInetAddress(ip);\n" +
                "            if (network == null) {\n" +
                "                id = 1L;\n" +
                "            } else {\n" +
                "                byte[] mac = network.getHardwareAddress();\n" +
                "                id = ((0x000000FF & (long) mac[mac.length - 1])\n" +
                "                        | (0x0000FF00 & (((long) mac[mac.length - 2]) << 8))) >> 6;\n" +
                "                id = id % (maxDatacenterId + 1);\n" +
                "            }\n" +
                "        } catch (Exception e) {\n" +
                "            System.out.println(\" getDatacenterId: \" + e.getMessage());\n" +
                "        }\n" +
                "        return id;\n" +
                "    }\n" +
                "}");
        code.addCodeItem(idWorker);
    }

    private void addConfig() {
        CodeItem config = new CodeItem();
        config.setPath("config/mybatis");
        config.setFileName("MyBatisFlexConfig.java");
        config.setCodeTemplate(String.format("import com.mybatisflex.core.FlexGlobalConfig;\n" +
                "import com.mybatisflex.core.dialect.DbType;\n" +
                "import com.mybatisflex.core.mybatis.FlexConfiguration;\n" +
                "import com.mybatisflex.spring.boot.ConfigurationCustomizer;\n" +
                "import com.mybatisflex.spring.boot.MyBatisFlexCustomizer;\n" +
                "import com.mybatisflex.core.keygen.KeyGeneratorFactory;\n" +
                "import com.mybatisflex.annotation.KeyType;\n" +
                "import org.apache.ibatis.logging.stdout.StdOutImpl;\n" +
                "import org.mybatis.spring.annotation.MapperScan;\n" +
                "import org.springframework.context.annotation.Configuration;\n" +
                "\n" +
                "@Configuration\n" +
                "@MapperScan(\"%s.mapper\")\n" +
                "public class MyBatisFlexConfig implements MyBatisFlexCustomizer, ConfigurationCustomizer {\n" +
                "\n" +
                "    @Override\n" +
                "    public void customize(FlexGlobalConfig flexGlobalConfig) {\n" +
                "        flexGlobalConfig.setDbType(DbType.MYSQL);\n" +
                "        KeyGeneratorFactory.register(\"snowflake\", new SnowflakeIDKeyGenerator());\n" +
                "        FlexGlobalConfig.KeyConfig keyConfig = new FlexGlobalConfig.KeyConfig();\n" +
                "        keyConfig.setKeyType(KeyType.Generator);\n" +
                "        keyConfig.setValue(\"snowflake\");\n" +
                "        keyConfig.setBefore(true);\n" +
                "        flexGlobalConfig.setKeyConfig(keyConfig);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void customize(FlexConfiguration configuration) {\n" +
                "        configuration.setLogImpl(StdOutImpl.class);\n" +
                "    }\n" +
                "}", Project.project().basePackage()));
        code.addCodeItem(config);
    }
}
