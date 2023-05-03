package cloud.makeronbean.generate.starter.mybatisplus;

import cloud.makeronbean.generate.starter.base.code.BaseCode;
import cloud.makeronbean.generate.starter.base.code.CodeItem;
import cloud.makeronbean.generate.utils.ProjectInfoUtils;

/**
 * @author makeronbean
 * @createDate 2023-05-03  13:17
 * @description
 */
public class MybatisPlusCode extends BaseCode {
    
    @Override
    protected void getDefault() {
        // 分页插件
        setPagePlugin();
        // 设置自动填充
        setMetaHandler();
    }
    
    
    /**
     * 分页插件
     */
    private void setPagePlugin() {
        CodeItem page = new CodeItem();
        page.setPath("config/mybatis");
        page.setFileName("MybatisConfig.java");
        page.setCodeTemplate(String.format(
                "import com.baomidou.mybatisplus.annotation.DbType;\n" +
                        "import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;\n" +
                        "import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;\n" +
                        "import org.springframework.context.annotation.Bean;\n" +
                        "import org.mybatis.spring.annotation.MapperScan;\n" +
                        "import org.springframework.context.annotation.Configuration;\n" +
                        "\n" +
                        "@Configuration\n" +
                        "@MapperScan(\"%s.mapper\")\n" +
                        "public class MybatisConfig {\n" +
                        "    \n" +
                        "    /**\n" +
                        "     * 配置：分页插件\n" +
                        "     */\n" +
                        "    @Bean\n" +
                        "    public MybatisPlusInterceptor mybatisPlusInterceptor() {\n" +
                        "        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();\n" +
                        "        // 指定数据库类型\n" +
                        "        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);\n" +
                        "        // 溢出后从第1页开始\n" +
                        "        paginationInnerInterceptor.setOverflow(true);\n" +
                        "        interceptor.addInnerInterceptor(paginationInnerInterceptor);\n" +
                        "        return interceptor;\n" +
                        "    }\n" +
                        "}", ProjectInfoUtils.basePackage
        ));
        this.addCodeItem(page);
    }
    
    /**
     * 自动填充
     */
    private void setMetaHandler() {
        CodeItem meta = new CodeItem();
        meta.setPath("config/mybatis");
        meta.setFileName("CommonMetaObjectHandler.java");
        meta.setCodeTemplate(
                "import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;\n" +
                        "import org.apache.ibatis.reflection.MetaObject;\n" +
                        "import org.springframework.stereotype.Component;\n" +
                        "\n" +
                        "import java.util.Date;\n" +
                        "\n" +
                        "@Component\n" +
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
                        "}"
        );
        this.addCodeItem(meta);
    }
}
