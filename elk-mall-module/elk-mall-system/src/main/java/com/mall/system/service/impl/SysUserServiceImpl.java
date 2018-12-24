package com.mall.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mall.common.annotation.DataFilter;
import com.mall.common.base.service.BaseServiceImpl;
import com.mall.common.util.JsonResponse;
import com.mall.common.util.Page;
import com.mall.common.util.constant.Constant;
import com.mall.common.util.network.AddressUtils;
import com.mall.common.util.redis.RedisKeys;
import com.mall.common.util.redis.RedisUtils;
import com.mall.system.dao.SysUserDao;
import com.mall.system.model.SysUser;
import com.mall.system.model.SysUserToken;
import com.mall.system.service.ShiroService;
import com.mall.system.service.SysDeptService;
import com.mall.system.service.SysRoleService;
import com.mall.system.service.SysUserService;
import com.mall.utils.TokenGenerator;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@Service("sysUserService")
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService {

    @Autowired
    private TokenGenerator tokenGenerator;
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private ShiroService shiroService;
    @Autowired
    private SysDeptService sysDeptService;
    @Autowired
    private RedisUtils redisUtils;

    @Transactional(readOnly = true)
    public List<String> queryAllPerms(Object userId) {
        return sysUserDao.queryAllPerms(userId);
    }

    @Transactional(readOnly = true)
    public List<Long> queryAllMenuId(Object userId) {
        return sysUserDao.queryAllMenuId(userId);
    }

    @Transactional(readOnly = true)
    public SysUser queryByUserName(String username) {
        return sysUserDao.queryByUserName(username);
    }

    @Transactional(readOnly = true)
    @DataFilter(tableAlias = "u", user = false)
    public List<SysUser> queryList(Map<String, Object> map){
        return sysUserDao.queryList(map);
    }


    @Transactional(readOnly = true)
    @DataFilter(tableAlias = "u", user = true)
    public Page getPage(Map<String, Object> map) {
        Page page = null;
        if (map.get("limit") != null && map.get("page") != null) {
            List<SysUser> list = sysUserDao.queryList(map);
            int totalCount = sysUserDao.queryTotal(map);
            page = new Page(list, totalCount, (int) map.get("limit"), (int) map.get("page"));
        }
        return page;
    }

    @Override
    public void save(SysUser user) {
        user.setCreateTime(new Date());
        //sha256加密
        String salt = RandomStringUtils.randomAlphanumeric(20);
        user.setSalt(salt);
        user.setPassword(new Sha256Hash(user.getPassword(), user.getSalt()).toHex());
        sysUserDao.save(user);

        //保存用户与角色关系
        sysRoleService.saveOrUpdate_R_U(user.getUserId(), user.getRoleIdList());
    }

    @Override
    public void update(SysUser user) {
        if(StringUtils.isBlank(user.getPassword())){
            user.setPassword(null);
        }else{
            user.setPassword(new Sha256Hash(user.getPassword(), user.getSalt()).toHex());
        }
        sysUserDao.update(user);
        if(redisUtils.opsForGetValue(RedisKeys.getUser(user.getUserId()+""),null)!=null)
            redisUtils.opsForSetValue(RedisKeys.getUser(user.getUserId()+""), user, Constant.HALF_HOUR);
        //保存用户与角色关系/不能修改当前用户角色
        SysUserToken sysUserToken = tokenGenerator.get(user.getToken());
        if(sysUserToken!=null && !sysUserToken.getUserId().equals(user.getUserId())){
            sysRoleService.saveOrUpdate_R_U(user.getUserId(), user.getRoleIdList());
            String permKey =RedisKeys.getPermsKey(user.getUserId(),user.getToken());
            String pattern = RedisKeys.getPermsKey(user.getUserId(),"");
            Set<String> permKeys=redisUtils.likeKey(pattern);
            Iterator<String> its = permKeys.iterator();
            while (its.hasNext()) {
                permKey=its.next();
                redisUtils.delete(permKey);
            }
            Iterator<String> is = permKeys.iterator();
            while (is.hasNext()) {
                permKey=is.next();
                Set<String> perms=shiroService.getUserPermissions(user.getUserId(), false,user.getToken());
                Iterator<String> it = perms.iterator();
                while (it.hasNext()) {
                    redisUtils.sset(permKey, it.next(),Constant.PERMS_EXPIRE);
                }
            }
        }

    }

    @Override
    public int updatePassword(Object userId, String password, String newPassword) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("password", password);
        map.put("newPassword", newPassword);
        return sysUserDao.updatePassword(map);
    }


    @Override
    public JsonResponse createToken(long userId, String ip, String userAgent) {
        //生成一个token
        String token = TokenGenerator.generateValue();
        SysUser sysUser=sysUserDao.queryObject(userId);
        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime() + Constant.TOKEN_EXPIRE);

        //判断是否生成过token
        SysUserToken tokenEntity =  new SysUserToken();
        tokenEntity.setId(UUID.randomUUID().toString());
        tokenEntity.setUserId(userId);
        tokenEntity.setUserName(sysUser.getUsername());
        tokenEntity.setToken(token);
        tokenEntity.setIp(ip);
        tokenEntity.setUpdateTime(now);
        tokenEntity.setExpireTime(expireTime);
        tokenEntity.setAddress(AddressUtils.getRealAddressByIP(ip));
        tokenEntity.setUserAgent(userAgent);
        tokenGenerator.saveOrUpdate(tokenEntity);
        //缓存用户信息
        redisUtils.opsForSetValue(RedisKeys.getUser(userId+""), sysUser, Constant.HALF_HOUR);
        //缓存权限
        String permKey =RedisKeys.getPermsKey(userId,token);
        redisUtils.delete(permKey);
        Set<String> perms=shiroService.getUserPermissions(userId, false,token);
        Iterator<String> it = perms.iterator();
        while (it.hasNext()) {
            redisUtils.sset(permKey, it.next(),Constant.PERMS_EXPIRE);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", token);
        jsonObject.put("expire", Constant.HALF_HOUR);
        jsonObject.put("userName", sysUser.getUsername());
        return JsonResponse.success(jsonObject);
    }

    @Override
    public void logout(SysUserToken token) {
        String permsKey = RedisKeys.getPermsKey(token.getUserId(),token.getToken());
        redisUtils.delete(permsKey);
        tokenGenerator.delete(token.getToken());
    }

    @Override
    @Transactional(readOnly = true)
    public List<SysUser> queryByDeptId(Object deptId) {

        return sysUserDao.queryByDeptId(deptId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SysUser> queryByDeptIds(Object deptId) {
        String deptIds=sysDeptService.getDownDeptIdList(deptId,true);
        String[] list= deptIds.split(",");
        return sysUserDao.queryByDeptIds(list);
    }

    @Transactional(readOnly = true)
    public List<SysUser> queryByRoleId(Object roleId) {

        return sysUserDao.queryByRoleId(roleId);
    }

    @Override
    @Transactional(readOnly = true)
    public SysUser queryObject(Object userId){
        SysUser user= sysUserDao.queryObject(userId);
        List<Long> roleIdList = sysRoleService.queryRoleIdList(userId);
        user.setRoleIdList(roleIdList);
        return user;
    }
}
