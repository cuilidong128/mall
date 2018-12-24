package com.mall.system.service.impl;

import com.mall.common.annotation.DataFilter;
import com.mall.common.base.service.BaseServiceImpl;
import com.mall.common.util.Page;
import com.mall.common.util.constant.Constant;
import com.mall.common.util.pattern.StringToolkit;
import com.mall.common.util.redis.RedisKeys;
import com.mall.common.util.redis.RedisUtils;
import com.mall.common.util.spring.SpringContextUtils;
import com.mall.system.dao.SysRoleDao;
import com.mall.system.dao.SysUserRoleDao;
import com.mall.system.model.SysRole;
import com.mall.system.service.ShiroService;
import com.mall.system.service.SysDeptService;
import com.mall.system.service.SysMenuService;
import com.mall.system.service.SysRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 角色
 */
@Transactional
@Service("sysRoleService")
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements SysRoleService {
    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysDeptService sysDeptService;
    @Autowired
    private SysUserRoleDao sysUserRoleDao;
    @Autowired
    private ShiroService shiroService;

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Transactional(readOnly = true)
    @DataFilter(tableAlias = "r", user = false)
    public List<SysRole> queryList(Map<String, Object> map) {
        return sysRoleDao.queryList(map);
    }

    @Transactional(readOnly = true)
    @DataFilter(tableAlias = "r", user = false)
    public Page getPage(Map<String, Object> map) {
        Page page = null;
        if (map.get("limit") != null && map.get("page") != null) {
            List<SysRole> list = sysRoleDao.queryList(map);
            int totalCount = sysRoleDao.queryTotal(map);
            page = new Page(list, totalCount, (int) map.get("limit"), (int) map.get("page"));
        }
        return page;
    }

    @Override
    public void save(SysRole role) {
        role.setCreateTime(new Date());
        sysRoleDao.save(role);
        //保存角色与菜单关系
        sysMenuService.saveOrUpdate_R_M(role.getRoleId(), role.getMenuIdList(),StringToolkit.getObjectString(role.getParamExt()));
        //保存角色与部门关系
        sysDeptService.saveOrUpdate_R_D(role.getRoleId(), role.getDeptIdList());
        //保存角色与用户关系
        saveOrUpdate_U_R(role.getRoleId(), role.getUserIdList());
    }

    @Override
    public void update(SysRole role,String token) {
        sysRoleDao.update(role);
        //更新角色与菜单关系
        sysMenuService.saveOrUpdate_R_M(role.getRoleId(), role.getMenuIdList(), StringToolkit.getObjectString(role.getParamExt()));
        //更新角色与部门关系
        sysDeptService.saveOrUpdate_R_D(role.getRoleId(), role.getDeptIdList());
        //保存角色与用户关系
        saveOrUpdate_U_R(role.getRoleId(), role.getUserIdList());
        // 刷新权限缓存
        List<Long> userList = queryUserIdList(role.getRoleId());
        for (Long userId : userList) {
            String permKey = RedisKeys.getPermsKey(userId, token);
            RedisUtils redisUtils = SpringContextUtils.getBean(RedisUtils.class);
            String pattern = RedisKeys.getPermsKey(userId, "");
            Set<String> permKeys = redisUtils.likeKey(pattern);
            Iterator<String> its = permKeys.iterator();
            while (its.hasNext()) {
                permKey=its.next();
                redisUtils.delete(permKey);
            }
            Iterator<String> is = permKeys.iterator();
            while (is.hasNext()) {
                permKey=is.next();
                Set<String> perms=shiroService.getUserPermissions(userId, false,token);
                Iterator<String> it = perms.iterator();
                while (it.hasNext()) {
                    redisUtils.sset(permKey, it.next(), Constant.PERMS_EXPIRE);
                }
            }
        }
    }

    @Override
    public void saveOrUpdate_U_R(Object roleId, List<Long> userIdList) {
        try {
            //先删除用户与角色关系
            sysUserRoleDao.deleteByRoleId(roleId);

            //保存用户与角色关系
            Map<String, Object> map = new HashMap<>();
            map.put("roleId", roleId);
            map.put("userIdList", userIdList);
            if(!userIdList.isEmpty()){
                sysUserRoleDao.saveUserList(map);
                logger.info("角色修改成功");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

    }

    @Override
    public void saveOrUpdate_R_U(Object userId, List<Long> roleIdList) {
        try {
            //先删除用户与角色关系
            sysUserRoleDao.delete(userId);

            //保存用户与角色关系
            Map<String, Object> map = new HashMap<>();
            map.put("userId", userId);
            map.put("roleIdList", roleIdList);
            if(!roleIdList.isEmpty()){
                sysUserRoleDao.save(map);
                logger.info("角色修改成功");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

    }

    @Transactional(readOnly = true)
    public List<Long> queryRoleIdList(Object userId) {
        return sysUserRoleDao.queryRoleIdList(userId);
    }

    @Transactional(readOnly = true)
    public List<Long> queryUserIdList(Object roleId) {
        return sysUserRoleDao.queryUserIdList(roleId);
    }

    @Override
    public void delete_R_U(Object userId) {
        sysUserRoleDao.delete(userId);
    }

}
