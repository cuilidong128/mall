package com.mall.system.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
/**
 * @author 崔立东
 * @date 2018/12/10 16:49
 */
@Component
public class RedisTemplateCache {
    /**
     * 前缀
     */
    private static final String KEY_PREFIX_VALUE = "mall:system:value:";
    private static final String KEY_PREFIX_SET = "mall:system:set:";
    private static final String KEY_PREFIX_LIST = "mall:system:list:";
    private final RedisTemplate redisTemplate;
    /**
     * 日志记录
     */
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public RedisTemplateCache(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 缓存value操作
     *
     * @param k    key
     * @param v    value
     * @param time time
     * @return boolean
     */
    public boolean cacheValue(String k, Object v, long time) {
        String key = KEY_PREFIX_VALUE + k;
        try {
            ValueOperations<String, Object> valueOps = redisTemplate.opsForValue();
            valueOps.set(key, v);
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Throwable t) {
            LOGGER.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 缓存value操作
     *
     * @param k key
     * @param v value
     * @return boolean
     */

    public boolean cacheValue(String k, Object v) {
        return cacheValue(k, v, -1);
    }

    /**
     * 判断缓存是否存在
     *
     * @param k key
     * @return boolean
     */

    public boolean containsValueKey(String k) {
        return containsKey(KEY_PREFIX_VALUE + k);
    }

    /**
     * 判断缓存是否存在
     *
     * @param k key
     * @return boolean
     */

    public boolean containsSetKey(String k) {
        return containsKey(KEY_PREFIX_SET + k);
    }

    /**
     * 判断缓存是否存在
     *
     * @param k key
     * @return boolean
     */

    public boolean containsListKey(String k) {
        return containsKey(KEY_PREFIX_LIST + k);
    }


    public boolean containsKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Throwable t) {
            LOGGER.error("判断缓存存在失败key[" + key + ", Codeor[" + t + "]");
        }
        return false;
    }

    /**
     * 获取缓存
     *
     * @param k key
     * @return string
     */

    public Object getValue(String k) {
        try {
            ValueOperations<String, Object> valueOps = redisTemplate.opsForValue();
            return valueOps.get(KEY_PREFIX_VALUE + k);
        } catch (Throwable t) {
            LOGGER.error("获取缓存失败key[" + KEY_PREFIX_VALUE + k + ", Codeor[" + t + "]");
        }
        return null;
    }

    /**
     * 移除缓存
     *
     * @param k key
     * @return boolean
     */

    public boolean removeValue(String k) {
        return remove(KEY_PREFIX_VALUE + k);
    }

    public boolean removeSet(String k) {
        return remove(KEY_PREFIX_SET + k);
    }


    public boolean removeList(String k) {
        return remove(KEY_PREFIX_LIST + k);
    }


    /**
     * 缓存set操作
     *
     * @param k    key
     * @param v    value
     * @param time time
     * @return boolean
     */

    public boolean cacheSet(String k, Object v, long time) {
        String key = KEY_PREFIX_SET + k;
        try {
            SetOperations<String, Object> valueOps = redisTemplate.opsForSet();
            valueOps.add(key, v);
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Throwable t) {
            LOGGER.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 缓存set
     *
     * @param k key
     * @param v value
     * @return boolean
     */

    public boolean cacheSet(String k, String v) {
        return cacheSet(k, v, -1);
    }

    /**
     * 缓存set
     *
     * @param k    key
     * @param v    value
     * @param time time
     * @return boolean
     */

    public boolean cacheSet(String k, Set<Object> v, long time) {
        String key = KEY_PREFIX_SET + k;
        try {
            SetOperations<String, Object> setOps = redisTemplate.opsForSet();
            setOps.add(key, v.toArray(new Object[v.size()]));
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Throwable t) {
            LOGGER.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 缓存set
     *
     * @param k key
     * @param v value
     * @return boolean
     */

    public boolean cacheSet(String k, Set<Object> v) {
        return cacheSet(k, v, -1);
    }

    /**
     * 获取缓存set数据
     *
     * @param k key
     * @return set
     */

    public Set<Object> getSet(String k) {
        try {
            SetOperations<String, Object> setOps = redisTemplate.opsForSet();
            return setOps.members(KEY_PREFIX_SET + k);
        } catch (Throwable t) {
            LOGGER.error("获取set缓存失败key[" + KEY_PREFIX_SET + k + ", Codeor[" + t + "]");
        }
        return null;
    }

    /**
     * list缓存
     *
     * @param k    key
     * @param v    value
     * @param time time
     * @return boolean
     */

    public boolean cacheList(String k, Object v, long time) {
        String key = KEY_PREFIX_LIST + k;
        try {
            ListOperations<String, Object> listOps = redisTemplate.opsForList();
            listOps.rightPush(key, v);
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Throwable t) {
            LOGGER.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 缓存list
     *
     * @param k key
     * @param v value
     * @return boolean
     */

    public boolean cacheList(String k, Object v) {
        return cacheList(k, v, -1);
    }

    /**
     * 缓存list
     *
     * @param k    key
     * @param v    value
     * @param time time
     * @return boolean
     */

    public boolean cacheList(String k, List<Object> v, long time) {
        String key = KEY_PREFIX_LIST + k;
        try {
            ListOperations<String, Object> listOps = redisTemplate.opsForList();
            listOps.rightPushAll(key, v);
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Throwable t) {
            LOGGER.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 缓存list
     *
     * @param k key
     * @param v value
     * @return boolean
     */

    public boolean cacheList(String k, List<Object> v) {
        return cacheList(k, v, -1);
    }

    /**
     * 获取list缓存
     *
     * @param k     key
     * @param start start
     * @param end   end
     * @return list
     */

    public List<Object> getList(String k, long start, long end) {
        try {
            ListOperations<String, Object> listOps = redisTemplate.opsForList();
            return listOps.range(KEY_PREFIX_LIST + k, start, end);
        } catch (Throwable t) {
            LOGGER.error("获取list缓存失败key[" + KEY_PREFIX_LIST + k + ", Codeor[" + t + "]");
        }
        return null;
    }

    /**
     * 获取总条数, 可用于分页
     *
     * @param k key
     * @return long
     */

    public long getListSize(String k) {
        try {
            ListOperations<String, String> listOps = redisTemplate.opsForList();
            return listOps.size(KEY_PREFIX_LIST + k);
        } catch (Throwable t) {
            LOGGER.error("获取list长度失败key[" + KEY_PREFIX_LIST + k + "], Codeor[" + t + "]");
        }
        return 0;
    }

    /**
     * 获取总条数, 可用于分页
     *
     * @param listOps listOps
     * @param k       k
     * @return long
     */

    public long getListSize(ListOperations<String, Object> listOps, String k) {
        try {
            return listOps.size(KEY_PREFIX_LIST + k);
        } catch (Throwable t) {
            LOGGER.error("获取list长度失败key[" + KEY_PREFIX_LIST + k + "], Codeor[" + t + "]");
        }
        return 0;
    }

    /**
     * 移除list缓存
     *
     * @param k k
     * @return boolean
     */

    public boolean removeOneOfList(String k) {
        String key = KEY_PREFIX_LIST + k;
        try {
            ListOperations<String, String> listOps = redisTemplate.opsForList();
            listOps.rightPop(key);
            return true;
        } catch (Throwable t) {
            LOGGER.error("移除list缓存失败key[" + KEY_PREFIX_LIST + k + ", Codeor[" + t + "]");
        }
        return false;
    }

    /**
     * 移除缓存
     *
     * @param key key
     * @return boolean
     */
    private boolean remove(String key) {
        try {
            redisTemplate.delete(key);
            return true;
        } catch (Throwable t) {
            LOGGER.error("获取缓存失败key[" + key + ", Codeor[" + t + "]");
        }
        return false;
    }
}

