package cloud.makeronbean.generate.utils;

import cloud.makeronbean.generate.constant.dependency.DependencyConst;
import cloud.makeronbean.generate.entity.starter.BaseStarter;
import cloud.makeronbean.generate.entity.starter.factory.StarterFactory;
import cloud.makeronbean.generate.entity.starter.factory.StarterFactoryImpl;
import lombok.SneakyThrows;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.List;

/**
 * @author makeronbean
 * @createDate 2023-05-01  09:14
 * @description
 */

public class XmlUtils {
    private static Document document;
    private static File pomFile;
    
    private static final String URL = "http://maven.apache.org/POM/4.0.0";
    
    public static void init(File pomFile, InputStream in) throws DocumentException, FileNotFoundException {
        XmlUtils.pomFile = pomFile;
        document = new SAXReader().read(in);
    }
    
    
    /**
     * 获取单个节点
     * @param key 标签名称
     * @return 节点值
     */
    public static String getSingleValue(String key) {
        Element element = document.getRootElement();
        return element.element(key).getStringValue();
    }
    
    
    /**
     * 添加节点
     */
    @SneakyThrows
    public static void addNodes(List<BaseStarter.Dependency> dependencyList) {
        for (BaseStarter.Dependency dependency : dependencyList) {
            Element parentElement = null;
            if (DependencyConst.PROJECT.getTabName().equals(dependency.getParentNodeName())) {
                parentElement = (Element) document.selectSingleNode(dependency.getParentNodeName());
            } else {
                parentElement = document.getRootElement().element(dependency.getParentNodeName());
            }
    
            Element parentTab = parentElement.addElement(dependency.getTabName(), URL);
    
            parentTab.addElement(DependencyConst.GROUPID.getTabName(), URL).addText(dependency.getGroupId());
    
            parentTab.addElement(DependencyConst.ARTIFACTID.getTabName(), URL).addText(dependency.getArtifactId());
    
            if (dependency.getVersion() != null) {
                parentTab.addElement(DependencyConst.VERSION.getTabName(), URL).addText(dependency.getVersion());
            }
            
            if (dependency.getOptional() != null) {
                parentTab.addElement(DependencyConst.OPTIONAL.getTabName(), URL).addText(dependency.getOptional());
            }
    
            if (dependency.getScope() != null) {
                parentTab.addElement(DependencyConst.SCOPE.getTabName(), URL).addText(dependency.getScope());
            }
        }
    }
    
    
    /**
     * 刷新pom
     */
    public static void refreshPom() throws IOException {
        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(pomFile),format);
        xmlWriter.write(document);
        xmlWriter.close();
    }
    
    public static void main(String[] args) {
        StarterFactory factory = new StarterFactoryImpl();
    }
}
