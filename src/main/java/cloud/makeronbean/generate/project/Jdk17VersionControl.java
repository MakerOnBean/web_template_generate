package cloud.makeronbean.generate.project;

/**
 * TODO
 *
 * @author beanMak1r
 * @since 2023-08-03 16:28
 */
public class Jdk17VersionControl extends AbstractVersionControls{

    public Jdk17VersionControl() {

        versionHolder
                // boot
                .setVersion(VersionHolder.DependencyNameEnum.SPRING_BOOT, "2.6.13")
                // mybatis flex
                .setVersion(VersionHolder.DependencyNameEnum.MY_BATIS_FLEX, "1.5.5")
                // mybatis plus
                .setVersion(VersionHolder.DependencyNameEnum.MY_BATIS_PLUS, "3.5.3.1")
                // mysql
                .setVersion(VersionHolder.DependencyNameEnum.MYSQL,"8.0.30")
                // knife4j TODO 修改版本
                .setVersion(VersionHolder.DependencyNameEnum.KNIFE4J, "2.0.7")
                // redisson
                .setVersion(VersionHolder.DependencyNameEnum.REDISSON, "3.17.1");
    }
}
