package cloud.makeronbean.generate.utils;

import cloud.makeronbean.generate.constant.DependencyConst;
import cloud.makeronbean.generate.starter.base.dependency.DependencyItem;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.dom4j.xpath.DefaultXPath;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private static final Map<String, String> namespaceURIs;


    static {
        namespaceURIs = new HashMap<>();
        namespaceURIs.put("a", "http://maven.apache.org/POM/4.0.0");
        namespaceURIs.put("xsi", "http://www.w3.org/2001/XMLSchema-instance");
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
     * 创建基本的pom标签
     */
    public static void initPom() {
        Element project = (Element) DOCUMENT.selectSingleNode(DependencyConst.PROJECT.getTabName());
        // 创建 dependencies
        Element dependencies = DOCUMENT.getRootElement().element(DependencyConst.DEPENDENCIES.getTabName());
        if (dependencies == null) {
            project.addElement(DependencyConst.DEPENDENCIES.getTabName(), URL);
        }

        // 创建build
        Element build = DOCUMENT.getRootElement().element(DependencyConst.BUILD.getTabName());
        if (build == null) {
            build = project.addElement(DependencyConst.BUILD.getTabName(), URL);
            // 创建plugins
            build.addElement(DependencyConst.PLUGINS.getTabName(), URL);
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
        XPath xpath = new DefaultXPath("//a:" + dependencyItem.getParentNodeName());
        xpath.setNamespaceURIs(namespaceURIs);
        Element parentElement = (Element) xpath.selectSingleNode(DOCUMENT);

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
        removeEmptyTextNodes(DOCUMENT.getRootElement());

        OutputFormat format = OutputFormat.createPrettyPrint();
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

    /**
     * 递归遍历元素，去除空行
     *
     * @param element 节点
     */
    private static void removeEmptyTextNodes(Element element) {
        List<Node> nodes = new ArrayList<>(element.content());
        for (Node node : nodes) {
            if (node.getNodeType() == Node.TEXT_NODE) {
                String text = node.getText().trim();
                if (text.isEmpty()) {
                    element.remove(node);
                }
            } else if (node.getNodeType() == Node.ELEMENT_NODE) {
                removeEmptyTextNodes((Element) node);
            }
        }
    }



}
