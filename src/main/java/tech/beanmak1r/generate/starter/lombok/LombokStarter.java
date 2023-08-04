package tech.beanmak1r.generate.starter.lombok;

import tech.beanmak1r.generate.enums.TagsEnum;
import tech.beanmak1r.generate.starter.base.dependency.DependencyItem;
import tech.beanmak1r.generate.starter.base.starter.StarterAdapter;
import tech.beanmak1r.generate.starter.base.dependency.BaseDependency;

/**
 * @author makeronbean
 * @createDate 2023-05-03  13:31
 * @description
 */
public class LombokStarter extends StarterAdapter {

    @Override
    protected void addDependency(BaseDependency dependency) {
        DependencyItem lombok = new DependencyItem()
                .setPath(TagsEnum.DEPENDENCY)
                .addTag(TagsEnum.GROUP_ID, "org.projectlombok")
                .addTag(TagsEnum.ARTIFACT_ID, "lombok")
                .addTag(TagsEnum.SCOPE, "provided");
        dependency.addDependencyItem(lombok);
    }
}
