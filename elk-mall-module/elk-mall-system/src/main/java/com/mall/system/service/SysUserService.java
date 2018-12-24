package com.mall.system.service;

import com.mall.common.base.service.BaseService;
import com.mall.common.util.JsonResponse;
import com.mall.system.model.SysUser;
import com.mall.system.model.SysUserToken;

import java.util.List;

/**
 * 系统用户
 */

public interface SysUserService extends BaseService<SysUser> {

    /**
     * 查询用户的所有权限
     * @param userId  用户ID
     */
    List<String> queryAllPerms(Object userId);

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Object userId);

    /**
     * 根据用户名，查询系统用户
     */
    SysUser queryByUserName(String username);

    /**
     * 修改密码
     * @param userId       用户ID
     * @param password     原密码
     * @param newPassword  新密码
     */
    int updatePassword(Object userId, String password, String newPassword);

    /**
     * 生成token
     * @param userId  用户ID,String ip
     */
    JsonResponse createToken(long userId, String ip, String userAgent);

    /**
     * 退出，修改token值
     * @param userId  用户ID
     */
    void logout(SysUserToken token);

    /**
     * 根据部门查询用户
     * @param deptId
     * @return
     */
    List<SysUser> queryByDeptId(Object deptId);

    /**
     * 根据部门递归查询所属部门及下级部门用户
     * @param deptIdList
     * @return
     */
    List<SysUser> queryByDeptIds(Object deptId);

    /**
     * 根据角色Id查询用户
     * @param roleId
     * @return
     */
    List<SysUser> queryByRoleId(Object roleId);

}
