package cloud.makeronbean.generate.project;

import cloud.makeronbean.generate.handler.GenerateHandler;
import cloud.makeronbean.generate.starter.base.BaseStarter;
import cloud.makeronbean.generate.handler.FileHandler;
import cloud.makeronbean.generate.handler.PomHandler;
import cloud.makeronbean.generate.handler.YamlHandler;
import cloud.makeronbean.generate.utils.FileUtils;
import cloud.makeronbean.generate.utils.ProjectInfoUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author makeronbean
 * @createDate 2023-05-01  08:28
 * @description 项目类
 */

public class Project {
    
    /**
     * starter项列表
     */
    private final List<BaseStarter> starterList;
    
    /**
     * 处理器列表
     */
    private final List<GenerateHandler> generateHandlerList;
    
    Project() {
        generateHandlerList = getHandlerList();
        this.starterList = new LinkedList<>();
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
        return generateHandlerList;
    }
    
    
    /**
     * 添加启动Starter
     *
     * @param starter starter项
     * @return this
     */
    public Project addStarter(BaseStarter starter) {
        Class<? extends BaseStarter> clazz = starter.getClass();
        if (starterList.stream().anyMatch(item -> item.getClass().equals(clazz))) {
            throw new RuntimeException(String.format("%s 已存在，添加失败\n", clazz.getSimpleName()));
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
    public <T> T getStarterByType(Class<T> clazz) {
        for (BaseStarter starter : starterList) {
            if (starter.getClass().equals(clazz)) {
                return (T) starter;
            }
        }
        return null;
    }
    
    
    /**
     * 根据类型删除starter
     * @param clazz starter子类型
     * @return this
     * @param <T>
     */
    public <T> Project deleteStarterByType(Class<T> clazz) {
        starterList.removeIf(starter -> starter.getClass().equals(clazz));
        return this;
    }
    
    
    /**
     * 设置基本包路径
     *
     * @param basePackage 例:"com.xxx.xxx"
     * @return this
     */
    public Project setBasePackage(String basePackage) {
        ProjectInfoUtils.basePackage = basePackage;
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
            throw new RuntimeException(String.format("%s 已存在，添加失败\n", clazz.getSimpleName()));
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
    public <T> Project deleteGenerateHandlerByType(Class<T> clazz) {
        this.generateHandlerList.removeIf(handler -> handler.getClass().equals(clazz));
        return this;
    }
    
    
    public void generate() {
        List<BaseStarter> executeStarterList = null;
        List<GenerateHandler> executeHandlerList = null;
        try {
            // 创建项目基本文件
            FileUtils.initProject();
            
            // 排序 去重
            executeStarterList = starterList
                    .stream()
                    .distinct()
                    .sorted((o1, o2) -> {
                        if (o1.getOrder() == null) {
                            return -1;
                        } else if (o2.getOrder() == null) {
                            return 1;
                        }
                        return o1.getOrder().compareTo(o2.getOrder());
                    })
                    .collect(Collectors.toList());
            
            executeHandlerList = generateHandlerList
                    .stream()
                    .distinct()
                    .collect(Collectors.toList());
            
            // 生成
            for (BaseStarter starter : executeStarterList) {
                starter.load();
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
