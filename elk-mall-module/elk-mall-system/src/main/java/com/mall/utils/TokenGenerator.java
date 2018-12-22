package com.mall.utils;


import com.mall.common.util.constant.Constant;
import com.mall.common.util.exception.RRException;
import com.mall.common.util.redis.RedisKeys;
import com.mall.common.util.redis.RedisUtils;
import com.mall.system.model.SysUserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.util.Date;
import java.util.UUID;

/**
 * 生成token
 * @author King chen
 * @emai 396885563@qq.com
 * @date 2018年1月11日
 */
@Component
public class TokenGenerator {
    @Autowired
    private RedisUtils redisUtils;

    public static String generateValue() {
        return generateValue(UUID.randomUUID().toString());
    }

    private static final char[] hexCode = "0123456789abcdef".toCharArray();

    public static String toHexString(byte[] data) {
        if(data == null) {
            return null;
        }
        StringBuilder r = new StringBuilder(data.length*2);
        for ( byte b : data) {
            r.append(hexCode[(b >> 4) & 0xF]);
            r.append(hexCode[(b & 0xF)]);
        }
        return r.toString();
    }

    public static String generateValue(String param) {
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(param.getBytes());
            byte[] messageDigest = algorithm.digest();
            return toHexString(messageDigest);
        } catch (Exception e) {
            throw new RRException("生成Token失败", e);
        }
    }

    public static void tokenExpireRefresh(String param){
        //	Object expireTime = RedisUtils.hget(key, hashKey, expire);
        //	RedisUtils.hset(key, hashKey, value);

    }

    public void saveOrUpdate(SysUserToken token) {
        if(token == null){
            return ;
        }
        String key = RedisKeys.getTokenKey(token.getToken());
        Date expireTime = new Date(System.currentTimeMillis()+ Constant.TOKEN_EXPIRE);    //半小时失效
        token.setExpireTime(expireTime);
        redisUtils.set(key, token,Constant.HALF_HOUR);
    }

    public void delete(String tokenKey) {
        String key = RedisKeys.getTokenKey(tokenKey);
        redisUtils.delete(key);
    }

    public SysUserToken get(String tokenKey){
        String key = RedisKeys.getTokenKey(tokenKey);
        return redisUtils.get(key, SysUserToken.class);
    }
}
