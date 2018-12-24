package com.mall.common.util.entityMapper;

import com.mall.common.util.redis.RedisKeys;
import com.mall.common.util.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntityMapperRedis {

    @Autowired
    private RedisUtils redisUtils;


    public void set(String entity,String field,Object value) {
        if(entity == null){
            return ;
        }
        String key = RedisKeys.getEnttyKey(entity);
        redisUtils.hset(key.trim(), field.trim(),value,-1);
    }

    public <T> T get(Class<T> clazz){
        String key = RedisKeys.getEnttyKey(clazz.getSimpleName());
        return  redisUtils.get(key, clazz);
    }
}
