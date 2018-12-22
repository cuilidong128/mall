package com.mall.oauth2;


import java.util.Set;

import com.mall.common.util.constant.Constant;
import com.mall.common.util.redis.RedisKeys;
import com.mall.common.util.redis.RedisUtils;
import com.mall.system.model.SysUser;
import com.mall.system.model.SysUserToken;
import com.mall.system.service.ShiroService;
import com.mall.utils.ShiroUtils;
import com.mall.utils.TokenGenerator;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



/**
 * shiro认证
 * @author King chen
 * @emai 396885563@qq.com
 * @date 2018年1月11日
 */
@Component
public class OAuth2Realm extends AuthorizingRealm {
    @Autowired
    private TokenGenerator tokenGenerator;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private ShiroService shiroService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SysUser user = (SysUser)principals.getPrimaryPrincipal();
        Long userId = user.getUserId();

        //用户权限列表
        Set<String> permsSet = shiroService.getUserPermissions(userId,true, ShiroUtils.getUserEntity().getToken());
        //刷新失效时间
        String permKey = RedisKeys.getPermsKey(userId,ShiroUtils.getUserEntity().getToken());
        redisUtils.expire(permKey, Constant.PERMS_EXPIRE);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String accessToken = (String) token.getPrincipal();
        // 根据accessToken，查询用户信息
        SysUserToken userToken = null;
        userToken = tokenGenerator.get(accessToken);
        if (userToken == null || userToken.getExpireTime().getTime() < System.currentTimeMillis()) {
            throw new IncorrectCredentialsException("token失效，请重新登录");
        } else {
            tokenGenerator.saveOrUpdate(userToken);
        }
        //获取user并更新过期时间
        SysUser user = (SysUser)redisUtils.opsForGetValue(RedisKeys.getUser(userToken.getUserId()+""),Constant.HALF_HOUR);
        if (user == null)
            user = shiroService.queryUser(userToken.getUserId());
        user.setToken(accessToken);
        // 账号锁定
        if (user.getStatus() == false) {
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, getName());
        return info;
    }
}
