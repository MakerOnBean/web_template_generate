package tech.beanmak1r.generate.starter.datebase.mybatis;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * MyBatis 相关代码生成器插件配置类
 *
 * @author beanMak1r
 * @since 2023-08-04 10:08
 */
public class MyBatisGenerateConfig {

    private final boolean enable;

    private String author;

    private String basePackage;

    private String jdbcUrl;

    private String username;

    private String password;

    private String tablePrefix;

    private boolean enableSwagger = true;

    private boolean enableActiveRecord = true;

    private final List<String> generateTable = new LinkedList<>();

    private String createTimeColumn = "create_time";

    private String updateTimeColumn = "update_time";

    private String logicDeleteColumn = "is_deleted";


    public boolean isEnableSwagger() {
        return enableSwagger;
    }



    public void setEnableSwagger(boolean enableSwagger) {
        this.enableSwagger = enableSwagger;
    }

    public boolean isEnableActiveRecord() {
        return enableActiveRecord;
    }

    public void setEnableActiveRecord(boolean enableActiveRecord) {
        this.enableActiveRecord = enableActiveRecord;
    }

    public MyBatisGenerateConfig() {
        this.enable = true;
    }

    public boolean isEnable() {
        return enable;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTablePrefix() {
        return tablePrefix;
    }

    public void setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
    }

    public List<String> getGenerateTable() {
        return generateTable;
    }

    public void setGenerateTable(String... generateTable) {
        this.generateTable.addAll(Arrays.asList(generateTable));
    }

    public String getCreateTimeColumn() {
        return createTimeColumn;
    }

    public void setCreateTimeColumn(String createTimeColumn) {
        this.createTimeColumn = createTimeColumn;
    }

    public String getUpdateTimeColumn() {
        return updateTimeColumn;
    }

    public void setUpdateTimeColumn(String updateTimeColumn) {
        this.updateTimeColumn = updateTimeColumn;
    }

    public String getLogicDeleteColumn() {
        return logicDeleteColumn;
    }

    public void setLogicDeleteColumn(String logicDeleteColumn) {
        this.logicDeleteColumn = logicDeleteColumn;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }
}
