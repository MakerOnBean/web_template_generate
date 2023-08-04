package cloud.makeronbean.generate.utils;

import cloud.makeronbean.generate.enums.TagsEnum;
import cloud.makeronbean.generate.project.Project;
import cloud.makeronbean.generate.project.ProjectInfo;
import cloud.makeronbean.generate.starter.base.dependency.DependencyItem;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

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
    private static final Element projectElement;

    static {
        namespaceURIs = new HashMap<>();
        namespaceURIs.put("a", "http://maven.apache.org/POM/4.0.0");
        namespaceURIs.put("xsi", "http://www.w3.org/2001/XMLSchema-instance");
        String path = Project.project().pomPath();
        POM_FILE = new File(path);
        try {
            IN = new FileInputStream(POM_FILE);
            DOCUMENT = new SAXReader().read(IN);
            projectElement = (Element) DOCUMENT.selectSingleNode(TagsEnum.PROJECT.getTabName());
            removeEmptyTextNodes(DOCUMENT.getRootElement());
        } catch (DocumentException | FileNotFoundException e) {
            throw new RuntimeException("pom 文件操作失败", e);
        }
    }


    /**
     * 如果不存在标签则创建它，如果是最底层标签，则无论是否存在都会创建
     *
     * @param path        标签路径 eg：dependencies/dependency
     * @param fromElement 根标签的上一级标签
     * @return 最下层创建的标签
     */
    private static Element createTagIfNotExist(String path, Element fromElement) {
        int index = path.indexOf("/");
        String targetTag;
        if (index == -1) {
            targetTag = path;
            path = null;
        } else {
            targetTag = path.substring(0, index);
            path = path.substring(index + 1);
        }
        Element targetElement = fromElement.element(targetTag);
        if (targetElement == null || path == null) {
            targetElement = fromElement.addElement(targetTag, URL);
        }

        if (path == null) {
            return targetElement;
        } else {
            return createTagIfNotExist(path, targetElement);
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
     *
     * @param dependencyItem 节点信息
     */
    public static void addNode(DependencyItem dependencyItem) {
        addNode(dependencyItem, projectElement);
    }

    /**
     * 添加节点
     *
     * @param dependencyItem 节点信息
     * @param element        包含节点的标签
     */
    private static void addNode(DependencyItem dependencyItem, Element element) {
        String path = dependencyItem.getPath();
        Element targetElement = createTagIfNotExist(path, element);

        Map<String, String> tags = dependencyItem.getTags();
        for (Map.Entry<String, String> entry : tags.entrySet()) {
            targetElement.addElement(entry.getKey()).addText(entry.getValue());
        }

        List<DependencyItem> child = dependencyItem.getChild();
        for (DependencyItem childItem : child) {
            addNode(childItem, targetElement);
        }
    }

    /**
     * 将内存中的 xml 写入磁盘，并关闭流
     */
    public static void refreshAndClose() throws IOException {
        //removeEmptyTextNodes(DOCUMENT.getRootElement());

        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        format.setNewlines(true);
        format.setIndent(true);
        format.setIndentSize(4);
        XMLWriter xmlWriter = new XMLWriter(Files.newOutputStream(POM_FILE.toPath()), format);
        xmlWriter.write(DOCUMENT);
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
