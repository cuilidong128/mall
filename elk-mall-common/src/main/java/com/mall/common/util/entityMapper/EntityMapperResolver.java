package com.mall.common.util.entityMapper;

import com.mall.common.util.redis.RedisKeys;
import com.mall.common.util.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

@Component("entityMapperResolver")
public  class EntityMapperResolver {
    @Autowired
    private RedisUtils redisUtils;
    /**
     * 根据实体、属性获取字段
     //* @param enttyName
     * @param attribute
     * @return
     */
    public  JSONObject getColumn(String entityName,String attribute){
        Object column = redisUtils.hget(RedisKeys.getEnttyKey(entityName), attribute);
        JSONObject column_json = new JSONObject();
        if(column !=null){
            column_json =JSONObject.parseObject(column.toString());
        }
        return column_json;
    }

    /**
     * 实体是否存在该属性
     //* @param enttyName
     * @param attribute
     * @return
     */
    public Boolean isExistAttribute(String entityName,String attribute){
        Boolean isExist =false;
        String key =RedisKeys.getEnttyKey(entityName);
        Object column = redisUtils.hget(key.trim(),attribute.trim());
        if(column !=null){
            isExist =true;
        }
        return isExist;
    }

}
