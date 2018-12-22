package com.mall.system.dao;

import com.mall.common.base.dao.BaseDao;
import com.mall.system.model.SysMenu;

import java.util.List;
import java.util.Map;

public interface SysMenuDao extends BaseDao<SysMenu> {

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
     * 查询用户的权限列表
     */
    List<SysMenu> queryUserList(Object userId);

    /**
     * 根据用户id、权限编码查询扩展该用户已授权的扩展参数
     * @return
     */
    String queryParamsByUserAndPerm(Map<String, Object> map);
}
