package cloud.makeronbean.generate.resolver;

import cloud.makeronbean.generate.utils.SystemUtils;
import lombok.SneakyThrows;
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

public class YamlResolver {
    
    private static YamlResolver yamlResolver;
    
    public synchronized static YamlResolver getInstance() {
        if (yamlResolver == null) {
            String yamlPath = SystemUtils.getProjectPath() +  "/" + "src" + "/" + "main" + "/" + "resources" + "/" + "application.yaml";
            yamlResolver = new YamlResolver(yamlPath);
        }
        return yamlResolver;
    }
    
    private String path;
    private Yaml yaml;
    
    private YamlResolver(String yamlPath) {
        path = yamlPath;
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
    @SneakyThrows
    private Map<String, Object> mapConverter(Map<String, Object> map) {
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
}
