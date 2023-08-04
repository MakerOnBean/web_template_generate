package tech.beanmak1r.generate.project;

import tech.beanmak1r.generate.utils.XmlUtils;

/**
 * @author makeronbean
 * @createDate 2023-05-02  22:42
 * @description
 */
public class ProjectInfo {

    /**
     * 作者
     */
    static String author;

    /**
     * 项目磁盘路径
     */
    static String projectPath;

    /**
     * 基础包名
     */
    static String basePackage;

    /**
     * groupId
     */
    static String groupId;

    /**
     * artifactId
     */
    static String artifactId;

    /**
     * 获取java文件基路径
     */
    static String javaPath() {
        return projectPath + "/" + "src" + "/" + "main" + "/" + "java" + "/" + packagePath();
    }

    /**
     * 项目生成的包路径
     */
    static String packagePath() {
        return basePackage.replaceAll("\\.", "/");
    }

    /**
     * yaml配置文件路径
     */
    static String configPath() {
        return projectPath + "/" + "src" + "/" + "main" + "/" + "resources" + "/" + "application.yaml";
    }

    /**
     * pom文件路径
     */
    static String pomPath() {
        return projectPath + "/" + "pom.xml";
    }


    /**
     * 版本控制
     */
    private static AbstractVersionControls versionControls;

    static {
        author = System.getProperty("user.name");
        projectPath = System.getProperty("user.dir");
        groupId = XmlUtils.getSingleValue("groupId");
        artifactId = XmlUtils.getSingleValue("artifactId");
        basePackage = groupId;
        versionControls = new Jdk8VersionControl();

    }


    static AbstractVersionControls getVersionControls() {
        return versionControls;
    }

    static void setVersionControls(AbstractVersionControls versionControls) {
        ProjectInfo.versionControls = versionControls;
    }
}
