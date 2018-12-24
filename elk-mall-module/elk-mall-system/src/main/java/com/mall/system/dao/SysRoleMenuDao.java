package com.mall.system.dao;

import com.mall.common.base.dao.BaseDao;
import com.mall.system.model.SysRoleMenu;

import java.util.List;


/**
 * 角色与菜单对应关系
 */
public interface SysRoleMenuDao extends BaseDao<SysRoleMenu> {

    /**
     * 根据角色ID，获取菜单ID列表
     */
    List<Long> queryMenuIdList(Object roleId);

    /**
     * 根据角色ID，获取扩展参数列表
     */
    List<SysRoleMenu> queryParamsList(Object roleId);
}
