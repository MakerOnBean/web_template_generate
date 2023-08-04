package tech.beanmak1r.generate.starter.datebase.mybatis.mybaitsflex;

import tech.beanmak1r.generate.starter.datebase.mybatis.MyBatisGenerateConfig;

/**
 * @author makeronbean
 * @createDate 2023-05-04  09:07
 * @description
 */
public class MyBaitsFlexConfig {
    private String version;

    private volatile MyBatisGenerateConfig generateConfig;

    public void enableMyBatisFlexGenerate(MyBatisGenerateConfig generateConfig) {
        this.generateConfig = generateConfig;
    }

    public MyBatisGenerateConfig getGenerateConfig() {
        return generateConfig;
    }

    String getVersion() {
        return version;
    }

    void setVersion(String version) {
        this.version = version;
    }
}
