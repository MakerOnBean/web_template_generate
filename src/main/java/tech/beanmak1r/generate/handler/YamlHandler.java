package tech.beanmak1r.generate.handler;

import tech.beanmak1r.generate.project.Project;
import tech.beanmak1r.generate.starter.base.starter.AbstractStarter;
import tech.beanmak1r.generate.starter.base.yaml.BaseYaml;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * @author makeronbean
 * @createDate 2023-05-01  16:48
 * @description
 */

public class YamlHandler extends GenerateHandlerAdapter {

    private final String path;
    private final Yaml yaml;

    public YamlHandler() {
        path = Project.project().configPath();
        DumperOptions options = new DumperOptions();
        // 通常使用的yaml格式
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        // 标量格式
        options.setDefaultScalarStyle(DumperOptions.ScalarStyle.PLAIN);
        yaml = new Yaml(options);
    }


    /**
     * 序列化 map 到 yaml 中
     *
     * @param map 被序列化的map
     * @throws IOException 文件创建失败时抛出
     */
    public void dump(Map<String, Object> map) throws IOException {

        Map<String, Object> showMap = mapConverter(map);

        //转储选项设置
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(Paths.get(path)), StandardCharsets.UTF_8))) {
            yaml.dump(showMap, writer);
        }

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
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if (entry.getKey().contains(".")) {
                    handlerConverter(resultMap, entry.getKey(), entry.getValue());
                } else {
                    resultMap.put(entry.getKey(), entry.getValue());
                }
            }
            return resultMap;
        } catch (IOException e) {
            throw new RuntimeException("yaml load失败", e);
        }
    }

    private void handlerConverter(Map<String, Object> resultMap, String oldKey, Object value) {
        String[] keys = oldKey.split("\\.");
        Map<String, Object> tempMap = resultMap;
        for (int i = 0; i < keys.length; i++) {
            String key = keys[i];
            if (i != keys.length - 1) {
                Map<String, Object> curMap;
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

    /**
     * 创建 yaml 文件
     *
     * @throws IOException createNewFile抛出
     */
    @Override
    public void beforeGenerate() throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            boolean newFile = file.createNewFile();
            if (!newFile) {
                throw new IOException("文件创建失败");
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
