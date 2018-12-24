package com.mall.system.dao;

import com.mall.common.base.dao.BaseDao;
import com.mall.system.model.SysDept;

import java.util.List;


/**
 * 部门管理
 */
public interface SysDeptDao extends BaseDao<SysDept> {

    /**
     * 查询子部门ID列表
     * @param parentId  上级部门ID
     */
    List<Long> queryDeptIdList(Object parentId);

    /**
     * 根据当前节点查询父节点
     * @param detpId
     * @return
     */
    Long queryParentDeptId(Object deptId);
}
