package com.mall.system.dao;

import java.util.List;

import com.mall.common.base.dao.BaseDao;
import com.mall.system.model.SysRoleDept;
import org.apache.ibatis.annotations.Param;



/**
 * 角色与部门对应关系
 */
public interface SysRoleDeptDao extends BaseDao<SysRoleDept> {

    /**
     * 根据角色ID，获取部门ID列表
     */
    List<Long> queryDeptIdList(Object roleId);

    /**
     * 查询子部门ID列表
     * @param parentIds  上级部门ID
     */
    List<Long> queryDeptIdLists(@Param("parentIds")List<Long> parentIds);
}
