package com.mall.system.cache;


import com.mall.common.constant.BaseConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 崔立东
 * @date 2018/12/10 17:13
 */
@Component
public final class CacheFactory {
    /**
     * Logger.
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(CacheFactory.class);

    /**
     * Caches.
     */
    private static final Map<String, Cache> CACHES = Collections.synchronizedMap(new HashMap<String, Cache>());

    /**
     * Private default constructor.
     */
    private CacheFactory() {
    }

    /**
     * Gets a cache specified by the given cache name.
     *
     * @param cacheName the given cache name
     * @return a cache specified by the given cache name
     */
    public static synchronized Cache getCache(final String cacheName) {
        LOGGER.info("Constructing cache [name={0}]....", cacheName);

        Cache ret = CACHES.get(cacheName);

        try {
            if (null == ret) {
                Class<Cache> cacheClass;
                switch (BaseConstants.getRuntimeCache()) {
                    case LOCAL_LRU:
                        cacheClass = (Class<Cache>) Class.forName("com.mall.system.cache.memory.LruMemoryCache");

                        break;
//                    case REDIS:
//                        cacheClass = (Class<Cache>) Class.forName("org.b3log.latke.cache.redis.RedisCache");
//
//                        break;
//                    case NONE:
//                        cacheClass = (Class<Cache>) Class.forName("org.b3log.latke.cache.NoneCache");
//
//                        break;
                    default:
                        throw new RuntimeException("Latke runs in the hell.... Please set the environment correctly");
                }

                ret = cacheClass.newInstance();

                ret.setName(cacheName);
                CACHES.put(cacheName, ret);
            }
        } catch (final Exception e) {
            throw new RuntimeException("Can not get cache: " + e.getMessage(), e);
        }

        LOGGER.info("Constructed cache [name={0}, runtime={1}]", cacheName, BaseConstants.getRuntimeCache());

        return ret;
    }

    /**
     * Clears all caches.
     */
    public static synchronized void clearAll() {
        for (final Map.Entry<String, Cache> entry : CACHES.entrySet()) {
            final Cache cache = entry.getValue();

            cache.removeAll();
            LOGGER.info("Cleared cache [name={0}]", entry.getKey());
        }
    }
}
