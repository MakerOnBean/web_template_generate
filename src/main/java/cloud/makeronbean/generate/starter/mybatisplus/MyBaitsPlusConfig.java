package cloud.makeronbean.generate.starter.mybatisplus;

import cloud.makeronbean.generate.utils.ProjectInfoUtils;

/**
 * @author makeronbean
 * @createDate 2023-05-04  09:07
 * @description
 */
public class MyBaitsPlusConfig {
    private String mybatisPlusVersion = "3.4.1";

    private boolean logicDelete = true;

    boolean isLogicDelete() {
        return logicDelete;
    }

    void setLogicDelete(boolean logicDelete) {
        this.logicDelete = logicDelete;
    }

    String getMybatisPlusVersion() {
        return mybatisPlusVersion;
    }

    void setMybatisPlusVersion(String mybatisPlusVersion) {
        this.mybatisPlusVersion = mybatisPlusVersion;
    }
}
