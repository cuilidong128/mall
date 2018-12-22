package com.mall.system.service.impl;

import com.mall.common.util.constant.Constant;
import com.mall.common.util.redis.RedisKeys;
import com.mall.common.util.redis.RedisUtils;
import com.mall.common.util.spring.SpringContextUtils;
import com.mall.system.dao.SysMenuDao;
import com.mall.system.dao.SysUserDao;
import com.mall.system.model.SysMenu;
import com.mall.system.model.SysUser;
import com.mall.system.service.ShiroService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("shiroService")
public class ShiroServiceImpl implements ShiroService {
    @Autowired
    private SysMenuDao sysMenuDao;
    @Autowired
    private SysUserDao sysUserDao;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Transactional(readOnly = true)
    public Set<String> getUserPermissions(Object userId, boolean cache, String token) {

        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        if(cache){
            String permKey = RedisKeys.getPermsKey(userId,token);
            RedisUtils redisUtils= SpringContextUtils.getBean(RedisUtils.class);
            permsSet = (Set)redisUtils.sget(permKey);
        }else{
            List<String> permsList;
            if(userId.equals(Constant.SUPER_ADMIN)){//系统管理员，拥有最高权限
                List<SysMenu> menuList = sysMenuDao.queryList(new HashMap<>());
                permsList = new ArrayList<>(menuList.size());
                for(SysMenu menu : menuList){
                    permsList.add(menu.getPerms());
                }
            }else{
                permsList = sysUserDao.queryAllPerms(userId);
            }
            for(String perms : permsList){
                if(StringUtils.isBlank(perms)){
                    continue;
                }
                permsSet.addAll(Arrays.asList(perms.trim().split(",")));
            }
        }

        return permsSet;
    }

    @Transactional(readOnly = true)
    public SysUser queryUser(Object userId) {
        return sysUserDao.queryObject(userId);
    }
}
