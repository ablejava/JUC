package com.juc.collections.hashmap;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/** 菜单VO类 */
@Setter
@Getter
@ToString
public class MenuVO {
    /** 菜单标识 */
    private Long id;
    /** 菜单名称 */
    private String name;
    /** 菜单链接 */
    private String url;
    /** 子菜单列表 */
    private List<MenuVO> childList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<MenuVO> getChildList() {
        return childList;
    }

    public void setChildList(List<MenuVO> childList) {
        this.childList = childList;
    }
}
