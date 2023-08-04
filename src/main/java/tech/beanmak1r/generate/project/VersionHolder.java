package tech.beanmak1r.generate.project;

import java.util.HashMap;
import java.util.Map;

/**
 * 版本信息缓存类
 *
 * @author beanMak1r
 * @since 2023-08-03 16:15
 */
public class VersionHolder {

    private static final VersionHolder versionHolder = new VersionHolder();

    public static VersionHolder versionHolder() {
        return versionHolder;
    }

    private final Map<String, String> PAYLOAD = new HashMap<>();

    private VersionHolder() {
    }


    public enum DependencyNameEnum {

        SPRING_BOOT("springBoot"),
        MYSQL("mySql"),
        REDISSON("redisson"),
        MY_BATIS_PLUS("myBatisPlus"),
        MY_BATIS_FLEX("mybatisFlex"),
        SPRING_DOC("springDoc"),
        ;
        private final String dependencyName;

        DependencyNameEnum(String dependencyName) {
            this.dependencyName = dependencyName;
        }

        public String getDependencyName() {
            return dependencyName;
        }
    }


    VersionHolder setVersion(DependencyNameEnum dependencyName, String version) {
        this.PAYLOAD.put(dependencyName.getDependencyName(), version);
        return this;
    }

    VersionHolder setVersion(String dependencyName, String version) {
        this.PAYLOAD.put(dependencyName, version);
        return this;
    }


    String getVersion(DependencyNameEnum dependencyName) {
        return this.PAYLOAD.get(dependencyName.dependencyName);
    }

    String getVersion(String dependencyName) {
        return this.PAYLOAD.get(dependencyName);
    }


}
