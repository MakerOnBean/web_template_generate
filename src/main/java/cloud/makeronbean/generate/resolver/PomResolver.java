package cloud.makeronbean.generate.resolver;

import cloud.makeronbean.generate.entity.starter.BaseStarter;
import cloud.makeronbean.generate.utils.SystemUtils;
import cloud.makeronbean.generate.utils.XmlUtils;
import lombok.SneakyThrows;
import org.dom4j.DocumentException;

import java.io.*;
import java.util.List;

/**
 * @author makeronbean
 * @createDate 2023-05-01  08:34
 * @description
 */

public class PomResolver {
    
    private static PomResolver pomResolver;
    
    
    /**
     * 初始化pom解析器
     * @return pom解析器对象
     */
    @SneakyThrows
    public synchronized static PomResolver getInstance() {
        if (pomResolver == null) {
            // 获取目录对象
            File file = new File(SystemUtils.getProjectPath());
            String path = file.getPath() + "/pom.xml";
            File poxFile = new File(path);
            pomResolver = new PomResolver(poxFile);
        }
        return pomResolver;
    }

    
    /**
     * pom文件输入流
     */
    private final InputStream pomStream;
    
    /**
     * groupId
     */
    private final String groupId;
    
    /**
     * artifactId
     */
    private final String artifactId;
    
    
    public PomResolver(File pomFile) throws FileNotFoundException, DocumentException {
        this.pomStream = new FileInputStream(pomFile);
        XmlUtils.init(pomFile, this.pomStream);
        this.groupId = XmlUtils.getSingleValue("groupId");
        this.artifactId = XmlUtils.getSingleValue("artifactId");
    }
    
    
    /**
     * 添加节点
     * @param dependencyList 依赖对象列表
     */
    public void addNodes(List<BaseStarter.Dependency> dependencyList) {
        XmlUtils.addNodes(dependencyList);
    }
    
    
    public void refresh() throws IOException {
        XmlUtils.refreshPom();
    }
    
    
    /**
     * 善后处理
     * @throws IOException
     */
    public void shutdown() {
        try {
            pomStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String getGroupId() {
        return groupId;
    }
    
    public String getArtifactId() {
        return artifactId;
    }
}
