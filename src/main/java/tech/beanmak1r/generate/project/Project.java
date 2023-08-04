package tech.beanmak1r.generate.project;

import tech.beanmak1r.generate.handler.*;
import tech.beanmak1r.generate.starter.base.starter.AbstractStarter;
import tech.beanmak1r.generate.starter.base.starter.StarterBridgeAdapter;
import tech.beanmak1r.generate.starter.base.starter.StarterBridgeAdapterImpl;
import tech.beanmak1r.generate.starter.datebase.mybatis.mybaitsflex.MyBatisFlexGenerateHandler;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author makeronbean
 * @createDate 2023-05-01  08:28
 * @description 项目类
 */

public class Project {


    private static Project instance;

    public static synchronized Project project() {
        if (instance == null) {
            instance = new Project();
            instance.init();
        }
        return instance;
    }

    private void init() {
        generateHandlerList = getHandlerList();
        this.starterList = new LinkedList<>();
    }

    Project() {
    }

    /**
     * starter项列表
     */
    private List<AbstractStarter> starterList;

    /**
     * 处理器列表
     */
    private List<GenerateHandler> generateHandlerList;

    private final StarterBridgeAdapter starterBridgeAdapter = new StarterBridgeAdapterImpl();

    public String author() {
        return ProjectInfo.author;
    }

    public String projectPath() {
        return ProjectInfo.projectPath;
    }

    public String basePackage() {
        return ProjectInfo.basePackage;
    }

    public String groupId() {
        return ProjectInfo.groupId;
    }

    public String artifactId() {
        return ProjectInfo.artifactId;
    }

    public String javaPath() {
        return ProjectInfo.javaPath();
    }

    public String packagePath() {
        return ProjectInfo.packagePath();
    }

    public String configPath() {
        return ProjectInfo.configPath();
    }

    public String pomPath() {
        return ProjectInfo.pomPath();
    }

    public AbstractVersionControls versionControls() {
        return ProjectInfo.getVersionControls();
    }

    public void setVersionControls(AbstractVersionControls versionControls) {
        ProjectInfo.setVersionControls(versionControls);
    }

    /**
     * 设置基本包路径
     *
     * @param basePackage 例:"com.xxx.xxx"
     * @return this
     */
    public Project setBasePackage(String basePackage) {
        ProjectInfo.basePackage = basePackage;
        return this;
    }


    /**
     * 设置作者
     *
     * @param author 作者
     * @return this
     */
    public Project setAuthor(String author) {
        ProjectInfo.author = author;
        return this;
    }

    /**
     * 初始化处理器列表
     *
     * @return 初始化完成的处理器列表
     */
    private List<GenerateHandler> getHandlerList() {
        final List<GenerateHandler> generateHandlerList;
        generateHandlerList = new LinkedList<>();
        generateHandlerList.add(new PomHandler());
        generateHandlerList.add(new FileHandler());
        generateHandlerList.add(new YamlHandler());
        generateHandlerList.add(new MyBatisFlexGenerateHandler());
        return generateHandlerList;
    }


    /**
     * 添加启动Starter
     *
     * @param starter starter项
     * @return this
     */
    public Project addStarter(AbstractStarter starter) {
        Class<? extends AbstractStarter> clazz = starter.getClass();
        if (starterList.stream().anyMatch(item -> item.getClass().equals(clazz))) {
            throw new RuntimeException(String.format("%s 已存在，添加失败%n", clazz.getSimpleName()));
        }
        starterList.add(starter);
        return this;
    }


    /**
     * 通过类型获取starter
     *
     * @param clazz starter子类型
     * @param <T>
     * @return 类型对应的starter对象，如果不存在返回null
     */
    public <T extends AbstractStarter> T getStarterByType(Class<T> clazz) {
        for (AbstractStarter starter : starterList) {
            if (starter.getClass().equals(clazz)) {
                return (T) starter;
            }
        }
        return null;
    }


    /**
     * 根据类型删除starter
     *
     * @param clazz starter子类型
     * @param <T>
     * @return this
     */
    public <T extends AbstractStarter> Project deleteStarterByType(Class<T> clazz) {
        starterList.removeIf(starter -> starter.getClass().equals(clazz));
        return this;
    }


    /**
     * 添加 生成处理器
     *
     * @param handler 被添加的生成处理器
     * @return this
     */
    public Project addGenerateHandler(GenerateHandler handler) {
        Class<? extends GenerateHandler> clazz = handler.getClass();
        if (generateHandlerList.stream().anyMatch(item -> item.getClass().equals(clazz))) {
            throw new RuntimeException(String.format("%s 已存在，添加失败%n", clazz.getSimpleName()));
        }
        this.generateHandlerList.add(handler);
        return this;
    }


    /**
     * 根据类型删除 生成处理器
     *
     * @param clazz 被删除的子类生成处理器类型
     * @param <T>   子类处理器
     * @return this
     */
    public <T extends AbstractStarter> Project deleteGenerateHandlerByType(Class<T> clazz) {
        this.generateHandlerList.removeIf(handler -> handler.getClass().equals(clazz));
        return this;
    }


    public void generate() {
        List<AbstractStarter> executeStarterList = null;
        List<GenerateHandler> executeHandlerList = null;
        try {
            // 排序 去重
            executeStarterList = starterList
                    .stream()
                    .distinct()
                    .sorted(Comparator.comparing(AbstractStarter::getOrder))
                    .collect(Collectors.toList());

            executeHandlerList = generateHandlerList
                    .stream()
                    .filter(generateHandler -> {
                        if (generateHandler instanceof SupportHandler) {
                            SupportHandler supportHandler = (SupportHandler) generateHandler;
                            return supportHandler.isSupport();
                        }
                        return true;
                    })
                    .distinct()
                    .collect(Collectors.toList());

            // 前置工作
            for (GenerateHandler handler : executeHandlerList) {
                handler.beforeGenerate();
            }

            // 生成
            for (AbstractStarter starter : executeStarterList) {
                starterBridgeAdapter.loadStarter(starter);
                for (GenerateHandler handler : executeHandlerList) {
                    handler.generateHandler(starter);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // 善后
            if (executeHandlerList != null) {
                for (GenerateHandler handler : executeHandlerList) {
                    try {
                        handler.afterGenerate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
