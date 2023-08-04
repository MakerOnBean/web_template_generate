package tech.beanmak1r.generate.starter.base.dependency;

import tech.beanmak1r.generate.enums.TagsEnum;

import java.util.*;

/**
 * @author makeronbean
 * @createDate 2023-05-02  21:34
 * @description
 */

public class DependencyItem {
    private String path;

    private final Map<String, String> tags;

    private final List<DependencyItem> child;

    public DependencyItem() {
        tags = new HashMap<>();
        child = new LinkedList<>();
    }

    public String getPath() {
        return path;
    }

    public DependencyItem setPath(String path) {
        this.path = path;
        return this;
    }

    public DependencyItem setPath(TagsEnum tagsEnum) {
        this.path = tagsEnum.getTabName();
        return this;
    }

    public DependencyItem addTag(String tag, String value) {
        tags.put(tag, value);
        return this;
    }

    public DependencyItem addTag(TagsEnum tagsEnum, String value) {
        tags.put(tagsEnum.getTabName(), value);
        return this;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public DependencyItem addChild(DependencyItem... dependencyItem2s) {
        child.addAll(Arrays.asList(dependencyItem2s));
        return this;
    }

    public List<DependencyItem> getChild() {
        return child;
    }
}
