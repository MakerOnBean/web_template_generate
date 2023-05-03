package cloud.makeronbean.generate.utils;

import cloud.makeronbean.generate.constant.DependencyConst;
import cloud.makeronbean.generate.starter.base.dependency.DependencyItem;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.nio.file.Files;

/**
 * @author makeronbean
 * @createDate 2023-05-01  09:14
 * @description
 */

public class XmlUtils {
    private static final Document DOCUMENT;
    private static final File POM_FILE;
    private static final InputStream IN;
    private static final String URL = "http://maven.apache.org/POM/4.0.0";
    
    
    static {
        String path = ProjectInfoUtils.pomFilePath();
        POM_FILE = new File(path);
        try {
            IN = new FileInputStream(POM_FILE);
            DOCUMENT = new SAXReader().read(IN);
        } catch (DocumentException | FileNotFoundException e) {
            throw new RuntimeException("pom文件操作失败", e);
        }
    }
    
    
    /**
     * 获取单个节点
     *
     * @param key 标签名称
     * @return 节点值
     */
    public static String getSingleValue(String key) {
        Element element = DOCUMENT.getRootElement();
        return element.element(key).getStringValue();
    }
    
    
    /**
     * 添加节点
     */
    public static void addNode(DependencyItem dependencyItem) {
        Element parentElement;
        if (DependencyConst.PROJECT.getTabName().equals(dependencyItem.getParentNodeName())) {
            parentElement = (Element) DOCUMENT.selectSingleNode(dependencyItem.getParentNodeName());
        } else {
            parentElement = DOCUMENT.getRootElement().element(dependencyItem.getParentNodeName());
        }
        
        Element parentTab = parentElement.addElement(dependencyItem.getTabName(), URL);
        
        parentTab.addElement(DependencyConst.GROUP_ID.getTabName(), URL).addText(dependencyItem.getGroupId());
        
        parentTab.addElement(DependencyConst.ARTIFACT_ID.getTabName(), URL).addText(dependencyItem.getArtifactId());
        
        if (dependencyItem.getVersion() != null) {
            parentTab.addElement(DependencyConst.VERSION.getTabName(), URL).addText(dependencyItem.getVersion());
        }
        
        if (dependencyItem.getOptional() != null) {
            parentTab.addElement(DependencyConst.OPTIONAL.getTabName(), URL).addText(dependencyItem.getOptional());
        }
        
        if (dependencyItem.getScope() != null) {
            parentTab.addElement(DependencyConst.SCOPE.getTabName(), URL).addText(dependencyItem.getScope());
        }
    }
    
    
    /**
     * 刷新pom
     */
    public static void refreshPom() throws IOException {
        OutputFormat format = new OutputFormat();
        format.setEncoding("UTF-8");
        format.setNewlines(true);
        format.setIndent(true);
        format.setIndentSize(4);
        XMLWriter xmlWriter = new XMLWriter(Files.newOutputStream(POM_FILE.toPath()), format);
        xmlWriter.write(DOCUMENT);
        xmlWriter.close();
    }
    
    
    /**
     * 关闭流
     */
    public static void closeStream() throws IOException {
        IN.close();
    }
}
