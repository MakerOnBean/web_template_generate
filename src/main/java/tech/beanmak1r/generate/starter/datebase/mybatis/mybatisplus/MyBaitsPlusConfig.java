package tech.beanmak1r.generate.starter.datebase.mybatis.mybatisplus;

/**
 * @author makeronbean
 * @createDate 2023-05-04  09:07
 * @description
 */
public class MyBaitsPlusConfig {
    private String version;

    private boolean logicDelete = true;

    boolean isLogicDelete() {
        return logicDelete;
    }

    void setLogicDelete(boolean logicDelete) {
        this.logicDelete = logicDelete;
    }

    String getVersion() {
        return version;
    }

    void setVersion(String version) {
        this.version = version;
    }
}
