package com.mall.utils;


import com.mall.common.util.redis.RedisKeys;
import com.mall.common.util.redis.RedisUtils;
import com.mall.system.model.SysConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 系统配置Redis
 */
@Component
public class SysConfigRedis {
    @Autowired
    private RedisUtils redisUtils;

    public void saveOrUpdate(SysConfig config) {
        if(config == null){
            return ;
        }
        String key = RedisKeys.getSysConfigKey(config.getKey());
        redisUtils.set(key, config);
        if(!config.getStatus()){
            redisUtils.delete(key);
        }
    }

    public void delete(String configKey) {
        String key = RedisKeys.getSysConfigKey(configKey);
        redisUtils.delete(key);
    }

    public SysConfig get(String configKey){
        String key = RedisKeys.getSysConfigKey(configKey);
        return redisUtils.get(key, SysConfig.class);
    }
}
