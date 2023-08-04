package tech.beanmak1r.generate.project;

/**
 * jdk8 版本控制
 *
 * @author beanMak1r
 * @since 2023-08-03 16:28
 */
public class Jdk8VersionControl extends AbstractVersionControls{

    public Jdk8VersionControl() {
        versionHolder
                // boot
                .setVersion(VersionHolder.DependencyNameEnum.SPRING_BOOT, "2.3.6.RELEASE")
                // mybatis flex
                .setVersion(VersionHolder.DependencyNameEnum.MY_BATIS_FLEX, "1.5.5")
                // mybatis plus
                .setVersion(VersionHolder.DependencyNameEnum.MY_BATIS_PLUS, "3.4.1")
                // mysql
                .setVersion(VersionHolder.DependencyNameEnum.MYSQL,"8.0.32")
                // redisson
                .setVersion(VersionHolder.DependencyNameEnum.REDISSON, "3.13.6")
                // spring doc
                .setVersion(VersionHolder.DependencyNameEnum.SPRING_DOC, "1.7.0");
    }
}
