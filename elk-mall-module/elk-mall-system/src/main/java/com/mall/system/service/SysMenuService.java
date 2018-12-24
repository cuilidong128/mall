package com.mall.system.service;


import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mall.common.base.service.BaseService;
import com.mall.system.model.SysMenu;


/**
 * 菜单管理
 * @author King chen
 * @email 396885563@qq.com
 * @date 2017年12月29日
 */
public interface SysMenuService extends BaseService<SysMenu> {

    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     * @param menuIdList  用户菜单ID
     */
    List<SysMenu> queryListParentId(Object parentId, List<Long> menuIdList);

    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     */
    List<SysMenu> queryListParentId(Object parentId);

    /**
     * 获取不包含按钮的菜单列表
     */
    List<SysMenu> queryNotButtonList();

    /**
     * 获取用户菜单列表
     */
    List<SysMenu> getUserMenuList(Object userId);

    /**
     *  查询用户所属权限的菜单列表
     */
    List<SysMenu> queryUserList(Object userId);

    /**
     * 保存或更新角色菜单相关连
     */
    void saveOrUpdate_R_M(Object roleId, List<Long> menuIdList,String params);

    /**
     * 根据角色ID，获取菜单ID列表
     */
    List<Long> queryMenuIdList(Object roleId);

    /**
     * 根据角色ID，获取扩展参数列表
     */
    JSONArray queryParamsList(Object roleId);

    JSONObject queryParamsByUserAndPerm(Object userId,String perms);
}
