package cloud.makeronbean.generate.starter.mybaitsflex;

import cloud.makeronbean.generate.enums.TagsEnum;
import cloud.makeronbean.generate.project.Project;
import cloud.makeronbean.generate.project.VersionHolder;
import cloud.makeronbean.generate.starter.base.code.BaseCode;
import cloud.makeronbean.generate.starter.base.code.CodeItem;
import cloud.makeronbean.generate.starter.base.dependency.BaseDependency;
import cloud.makeronbean.generate.starter.base.dependency.DependencyItem;
import cloud.makeronbean.generate.starter.base.starter.StarterAdapter;
import cloud.makeronbean.generate.starter.base.yaml.BaseYaml;
import cloud.makeronbean.generate.starter.mybatisplus.MyBaitsPlusConfig;
import cloud.makeronbean.generate.utils.StringUtils;

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


    @Override
    protected void addDependency(BaseDependency dependency) {
        DependencyItem mybatisPlus = new DependencyItem()
                .setPath(TagsEnum.DEPENDENCY)
                .addTag(TagsEnum.GROUP_ID, "com.mybatis-flex")
                .addTag(TagsEnum.ARTIFACT_ID, "mybatis-flex-spring-boot-starter")
                .addTag(TagsEnum.VERSION, StringUtils.isEmpty(
                        config.getVersion()) ?
                        Project.project().versionControls().getVersion(VersionHolder.DependencyNameEnum.MY_BATIS_FLEX) :
                        config.getVersion());
        dependency.addDependencyItem(mybatisPlus);
    }

    @Override
    protected void addYaml(BaseYaml yaml) {
        yaml.addYamlConfig("mybatis-flex.configuration.log-impl", "org.apache.ibatis.logging.stdout.StdOutImpl");
    }

    @Override
    protected void addCode(BaseCode code) {
        new MyBatisFlexCode(code, config).fillData();
    }


}
