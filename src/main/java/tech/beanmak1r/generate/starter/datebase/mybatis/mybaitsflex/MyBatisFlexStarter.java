package tech.beanmak1r.generate.starter.datebase.mybatis.mybaitsflex;

import tech.beanmak1r.generate.enums.TagsEnum;
import tech.beanmak1r.generate.project.VersionHolder;
import tech.beanmak1r.generate.starter.base.code.BaseCode;
import tech.beanmak1r.generate.starter.base.dependency.BaseDependency;
import tech.beanmak1r.generate.starter.base.dependency.DependencyItem;
import tech.beanmak1r.generate.starter.base.starter.StarterAdapter;
import tech.beanmak1r.generate.starter.base.yaml.BaseYaml;
import tech.beanmak1r.generate.starter.datebase.mybatis.MyBatisGenerateConfig;
import tech.beanmak1r.generate.utils.StringUtils;

/**
 * @author makeronbean
 * @createDate 2023-05-03  12:58
 * @description
 */
public class MyBatisFlexStarter extends StarterAdapter {

    MyBaitsFlexConfig config = new MyBaitsFlexConfig();

    private MyBatisFlexStarter() {
        this.order = 3;
    }

    public MyBatisFlexStarter version(String version) {
        config.setVersion(version);
        return this;
    }

    public MyBatisFlexStarter enableMyBatisFlexGenerate(MyBatisGenerateConfig generateConfig) {
        config.enableMyBatisFlexGenerate(generateConfig);
        return this;
    }

    @Override
    protected void addDependency(BaseDependency dependency) {
        DependencyItem mybatisPlus = new DependencyItem()
                .setPath(TagsEnum.DEPENDENCY)
                .addTag(TagsEnum.GROUP_ID, "com.mybatis-flex")
                .addTag(TagsEnum.ARTIFACT_ID, "mybatis-flex-spring-boot-starter")
                .addTag(TagsEnum.VERSION, StringUtils.getVersion(config.getVersion(), VersionHolder.DependencyNameEnum.MY_BATIS_FLEX));
        dependency.addDependencyItem(mybatisPlus);

        DependencyItem hikariCP = new DependencyItem()
                .setPath(TagsEnum.DEPENDENCY)
                .addTag(TagsEnum.GROUP_ID, "com.zaxxer")
                .addTag(TagsEnum.ARTIFACT_ID, "HikariCP");
        dependency.addDependencyItem(hikariCP);

    }

    @Override
    protected void addYaml(BaseYaml yaml) {
        yaml.addYamlConfig("mybatis-flex.configuration.log-impl", "org.apache.ibatis.logging.stdout.StdOutImpl");
    }

    @Override
    protected void addCode(BaseCode code) {
        new MyBatisFlexCode(code).fillData();
    }


}
