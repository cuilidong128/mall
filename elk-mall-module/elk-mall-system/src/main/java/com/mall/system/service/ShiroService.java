package com.mall.system.service;

import com.mall.system.model.SysUser;

import java.util.Set;

/**
 * 用户权限列表
 */
public interface ShiroService {
    /**
     * 获取用户权限列表
     */
    Set<String> getUserPermissions(Object userId, boolean cache, String token);

    /**
     * 根据用户ID，查询用户
     * @param userId
     */
    SysUser queryUser(Object userId);
}
