package cloud.makeronbean.generate.handler;

import cloud.makeronbean.generate.starter.base.starter.AbstractStarter;
import cloud.makeronbean.generate.starter.base.yaml.BaseYaml;
import cloud.makeronbean.generate.utils.ProjectInfoUtils;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * @author makeronbean
 * @createDate 2023-05-01  16:48
 * @description
 */

public class YamlHandler extends GenerateHandlerAdapter{
    
    private final String path;
    private final Yaml yaml;
    
    public YamlHandler() {
        path = ProjectInfoUtils.yamlFilePath();
        DumperOptions options = new DumperOptions();
        // 通常使用的yaml格式
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        // 标量格式
        options.setDefaultScalarStyle(DumperOptions.ScalarStyle.PLAIN);
        yaml = new Yaml(options);
    }
    
    
    /**
     * 序列化map到yaml中
     *
     * @param map 被序列化的map
     * @throws IOException
     */
    public void dump(Map<String, Object> map) throws IOException {
        
        Map<String, Object> showMap = mapConverter(map);
        
        //转储选项设置
        yaml.dump(showMap, new FileWriter(path));
    }
    
    
    /**
     * map格式转换
     */
    private Map<String, Object> mapConverter(Map<String, Object> map) {
        try {
            Map<String, Object> resultMap = yaml.loadAs(Files.newInputStream(Paths.get(path)), Map.class);
            if (resultMap == null) {
                resultMap = new HashMap<>();
            }
            for (String s : map.keySet()) {
                if (s.contains(".")) {
                    handlerConverter(resultMap, s, map.get(s));
                } else {
                    resultMap.put(s, map.get(s));
                }
            }
            return resultMap;
        } catch (IOException e) {
            throw new RuntimeException("yaml load失败",e);
        }
    }
    
    private void handlerConverter(Map<String, Object> resultMap, String oldKey, Object value) {
        String[] keys = oldKey.split("\\.");
        Map<String, Object> tempMap = resultMap;
        for (int i = 0; i < keys.length; i++) {
            String key = keys[i];
            if (i != keys.length - 1) {
                Map<String, Object> curMap = null;
                if (!tempMap.containsKey(key)) {
                    curMap = new HashMap<>();
                    tempMap.put(key, curMap);
                } else {
                    curMap = (Map<String, Object>) tempMap.get(key);
                }
                tempMap = curMap;
            } else {
                tempMap.put(key, value);
            }
        }
    }
    
    @Override
    public void generateHandler(AbstractStarter starter) throws IOException {
        BaseYaml baseYaml = starter.getYaml();
        if (baseYaml != null) {
            Map<String, Object> configMap = baseYaml.getConfigMap();
            dump(configMap);
        }
    }
}
