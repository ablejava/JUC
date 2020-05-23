package com.juc.collections.hashmap;

import org.springframework.util.CollectionUtils;

import java.util.*;

public class HashMapMapping {


    /** 构建菜单树函数 */
    public static List<MenuVO> buildMenuTree(List<MenuDO> menuList) {
        // 检查列表为空
        if (CollectionUtils.isEmpty(menuList)) {
            return Collections.emptyList();
        }

        // 依次处理菜单
        int menuSize = menuList.size();
        List<MenuVO> rootList = new ArrayList<>(menuSize);
        Map<Long, MenuVO> menuMap = new HashMap<>(menuSize);
        for (MenuDO menuDO : menuList) {
            // 赋值菜单对象
            Long menuId = menuDO.getId();
            MenuVO menu = menuMap.get(menuId);
            if (Objects.isNull(menu)) {
                menu = new MenuVO();
                menu.setChildList(new ArrayList<>());
                menuMap.put(menuId, menu);
            }
            menu.setId(menuDO.getId());
            menu.setName(menuDO.getName());
            menu.setUrl(menuDO.getUrl());

            // 根据父标识处理
            Long parentId = menuDO.getParentId();
            if (Objects.nonNull(parentId)) {
                // 构建父菜单对象
                MenuVO parentMenu = menuMap.get(parentId);
                if (Objects.isNull(parentMenu)) {
                    parentMenu = new MenuVO();
                    parentMenu.setId(parentId);
                    parentMenu.setChildList(new ArrayList<>());
                    menuMap.put(parentId, parentMenu);
                }

                // 添加子菜单对象
                parentMenu.getChildList().add(menu);
            } else {
                // 添加根菜单对象
                rootList.add(menu);
            }
        }

        // 返回根菜单列表
        return rootList;
    }

}
