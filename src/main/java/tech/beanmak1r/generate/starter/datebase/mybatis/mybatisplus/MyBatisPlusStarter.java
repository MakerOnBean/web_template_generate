package tech.beanmak1r.generate.starter.datebase.mybatis.mybatisplus;

import tech.beanmak1r.generate.enums.TagsEnum;
import tech.beanmak1r.generate.project.Project;
import tech.beanmak1r.generate.project.VersionHolder;
import tech.beanmak1r.generate.starter.base.dependency.DependencyItem;
import tech.beanmak1r.generate.starter.base.starter.StarterAdapter;
import tech.beanmak1r.generate.starter.base.code.BaseCode;
import tech.beanmak1r.generate.starter.base.code.CodeItem;
import tech.beanmak1r.generate.starter.base.dependency.BaseDependency;
import tech.beanmak1r.generate.starter.base.yaml.BaseYaml;
import tech.beanmak1r.generate.utils.StringUtils;

/**
 * @author makeronbean
 * @createDate 2023-05-03  12:58
 * @description
 */
public class MyBatisPlusStarter extends StarterAdapter {

    MyBaitsPlusConfig config = new MyBaitsPlusConfig();

    private MyBatisPlusStarter() {
        this.order = 3;
    }

    public MyBatisPlusStarter logicDelete(boolean open) {
        config.setLogicDelete(open);
        return this;
    }

    public MyBatisPlusStarter version(String version) {
        config.setVersion(version);
        return this;
    }

    @Override
    protected void addDependency(BaseDependency dependency) {
        DependencyItem mybatisPlus = new DependencyItem()
                .setPath(TagsEnum.DEPENDENCY)
                .addTag(TagsEnum.GROUP_ID, "com.baomidou")
                .addTag(TagsEnum.ARTIFACT_ID, "mybatis-plus-boot-starter")
                .addTag(TagsEnum.VERSION, StringUtils.getVersion(config.getVersion(), VersionHolder.DependencyNameEnum.MY_BATIS_PLUS));
        dependency.addDependencyItem(mybatisPlus);
    }

    @Override
    protected void addYaml(BaseYaml yaml) {
        yaml.addYamlConfig("mybatis-plus.configuration.log-impl", "org.apache.ibatis.logging.stdout.StdOutImpl");
        yaml.addYamlConfig("mybatis-plus.configuration.map-underscore-to-camel-case", true);
        yaml.addYamlConfig("mybatis-plus.type-aliases-package", Project.project().basePackage() + ".entity");
        // 是否开启逻辑删除
        if (config.isLogicDelete()) {
            yaml.addYamlConfig("mybatis-plus.global-config.db-config.logic-delete-field", "isDeleted");
            yaml.addYamlConfig("mybatis-plus.global-config.db-config.logic-delete-value", 1);
            yaml.addYamlConfig("mybatis-plus.global-config.db-config.logic-not-delete-value", 0);
        }
    }

    @Override
    protected void addCode(BaseCode code) {
        CodeItem meta = new CodeItem();
        meta.setPath("config/mybatis");
        meta.setFileName("CommonMetaObjectHandler.java");
        meta.setCodeTemplate(
                String.format("import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;\n" +
                        "import org.apache.ibatis.reflection.MetaObject;\n" +
                        "import org.mybatis.spring.annotation.MapperScan;\n" +
                        "import org.springframework.stereotype.Component;\n" +
                        "\n" +
                        "import java.util.Date;\n" +
                        "\n" +
                        "@Component\n" +
                        "@MapperScan(\"%s.mapper\")\n" +
                        "public class CommonMetaObjectHandler implements MetaObjectHandler {\n" +
                        "    /**\n" +
                        "     * 新增时自动填充\n" +
                        "     */\n" +
                        "    @Override\n" +
                        "    public void insertFill(MetaObject metaObject) {\n" +
                        "        //参数1:元数据对象\n" +
                        "        //参数2:类属性名称\n" +
                        "        //参数3:类对象\n" +
                        "        //参数4:当前系统时间\n" +
                        "        this.strictInsertFill(metaObject, \"createTime\", Date.class, new Date());\n" +
                        "        this.strictUpdateFill(metaObject, \"updateTime\", Date.class, new Date());\n" +
                        "    }\n" +
                        "    \n" +
                        "    /**\n" +
                        "     * 修改时自动填充\n" +
                        "     */\n" +
                        "    @Override\n" +
                        "    public void updateFill(MetaObject metaObject) {\n" +
                        "        this.strictUpdateFill(metaObject, \"updateTime\", Date.class, new Date());\n" +
                        "    }\n" +
                        "}", Project.project().basePackage()
                )
        );
        code.addCodeItem(meta);
    }


}
