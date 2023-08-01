package cloud.makeronbean.generate.starter.lombok;

import cloud.makeronbean.generate.constant.DependencyConst;
import cloud.makeronbean.generate.starter.base.dependency.DependencyItem;
import cloud.makeronbean.generate.starter.base.starter.StarterAdapter;
import cloud.makeronbean.generate.starter.base.dependency.BaseDependency;

/**
 * @author makeronbean
 * @createDate 2023-05-03  13:31
 * @description
 */
public class LombokStarter extends StarterAdapter {

    LombokConfig config = new LombokConfig();

    public LombokStarter version(String version) {
        config.setVersion(version);
        return this;
    }

    private LombokStarter() {
        this.order = 3;
    }

    @Override
    protected void addDependency(BaseDependency dependency) {
        DependencyItem lombok = new DependencyItem()
                .setPath(DependencyConst.DEPENDENCY)
                .addTag(DependencyConst.GROUP_ID, "org.projectlombok")
                .addTag(DependencyConst.ARTIFACT_ID, "lombok")
                .addTag(DependencyConst.VERSION, config.getVersion())
                .addTag(DependencyConst.SCOPE, "provided");
        dependency.addDependencyItem(lombok);
    }
}
