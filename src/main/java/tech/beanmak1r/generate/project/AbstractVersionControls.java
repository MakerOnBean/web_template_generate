package tech.beanmak1r.generate.project;

/**
 * 版本控制基类
 *
 * @author beanMak1r
 * @since 2023-08-01 12:30
 */
public class AbstractVersionControls {

    protected final VersionHolder versionHolder = VersionHolder.versionHolder();

    public String getVersion(VersionHolder.DependencyNameEnum dependencyName) {
        return versionHolder.getVersion(dependencyName);
    }

    public String getVersion(String dependencyName) {
        return versionHolder.getVersion(dependencyName);
    }

    public void setVersion(VersionHolder.DependencyNameEnum dependencyName, String version) {
        versionHolder.setVersion(dependencyName, version);
    }

    public void setVersion(String dependencyName, String version) {
        versionHolder.setVersion(dependencyName, version);
    }
}
