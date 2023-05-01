package cloud.makeronbean.generate.entity.project;

import cloud.makeronbean.generate.entity.starter.BaseStarter;
import cloud.makeronbean.generate.resolver.FileResolver;
import cloud.makeronbean.generate.resolver.PomResolver;
import cloud.makeronbean.generate.resolver.YamlResolver;
import cloud.makeronbean.generate.utils.SystemUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author makeronbean
 * @createDate 2023-05-01  08:28
 * @description 项目类
 */

public class Project {
    private final ProjectInfo info;
    
    private final PomResolver pomResolver;
    private final YamlResolver yamlResolver;
    private final FileResolver fileResolver;
    
    private List<BaseStarter> starterList = new ArrayList<>();
    
    private static final List<String> BASE_PACKAGE = Arrays.asList("common", "config", "controller", "entity", "mapper", "model", "service", "utils");
    
    public Project() {
        pomResolver = PomResolver.getInstance();
        yamlResolver = YamlResolver.getInstance();
        fileResolver = FileResolver.getInstance();
        info = new ProjectInfo(SystemUtils.getProjectPath(), pomResolver.getGroupId());
    }
    
    public Project(List<BaseStarter> starterList) {
        this();
        this.starterList = new ArrayList<>(starterList);
    }
    
    public Project addStarter(BaseStarter starter) {
        starterList.add(starter);
        return this;
    }
    
    public void generate() {
        try {
            // 创建基本目录
            for (String s : BASE_PACKAGE) {
                fileResolver.mkdir(info.getBaseGeneratePath(), s);
            }
            
            // 生成配置文件
            fileResolver.touch(info.getYamlPath());
            
            // 排序 去重
            starterList = starterList.stream().distinct()
                    .sorted((o1, o2) -> {
                        if (o1.getOrder() == null) {
                            return -1;
                        } else if (o2.getOrder() == null){
                            return 1;
                        }
                        return o1.getOrder().compareTo(o2.getOrder());
                    })
                    .collect(Collectors.toList());
            
    
            for (BaseStarter starter : starterList) {
                if (starter.getDependencyList() != null && starter.getDependencyList().size() > 0) {
                    pomResolver.addNodes(starter.getDependencyList());
                }
                
                if (starter.getYamlMap() != null) {
                    yamlResolver.dump(starter.getYamlMap());
                }
                
                if (starter.getCodeTemplateList() != null) {
                    fileResolver.write(starter.getCodeTemplateList(), info);
                }
            }
    
            // 刷新pom文件
            pomResolver.refresh();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            pomResolver.shutdown();
        }
    }
}
