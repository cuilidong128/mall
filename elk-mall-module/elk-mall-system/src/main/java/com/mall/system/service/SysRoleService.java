package com.mall.system.service;

import com.mall.common.base.service.BaseService;
import com.mall.system.model.SysRole;

import java.util.List;



/**
 * 角色
 * @author King chen
 * @email 396885563@qq.com
 * @date 2017年12月29日
 */
public interface SysRoleService extends BaseService<SysRole> {

    /**
     * 保存或更新用户时更新角色用户相关联
     */
    void saveOrUpdate_R_U(Object userId, List<Long> roleIdList);

    /**
     * 保存或更新角色时更新角色用户相关联
     */
    void saveOrUpdate_U_R(Object userId, List<Long> roleIdList);

    /**
     * 根据用户ID，获取角色ID列表
     */
    List<Long> queryRoleIdList(Object userId);

    /**
     * 根据角色ID，获取授权用户ID列表
     */
    List<Long> queryUserIdList(Object roleId);

    /**
     * 根据用户ID，删除角色用户关联
     */
    void delete_R_U(Object userId);

    /**
     * 更新角色
     */
    void update(SysRole sysRole,String token);
}
