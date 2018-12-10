package com.mall.common.constant;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 崔立东
 * @date 2018/12/10 17:11
 */
public final class BaseConstants {

    private static final Properties LOCAL_PROPS = new Properties();
    /**
     * Executor service.
     */
    public static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();

    public enum RuntimeCache {

        /**
         * None.
         */
        NONE,
        /**
         * Local LRU memory cache.
         */
        LOCAL_LRU,
        /**
         * Redis.
         */
        REDIS,
    }

    public static RuntimeCache getRuntimeCache() {
        final String runtimeCache = LOCAL_PROPS.getProperty("runtimeCache");
        if (null == runtimeCache) {
           // LOGGER.debug("Not found [runtimeCache] in local.properties, uses [LOCAL_LRU] as default");

            return RuntimeCache.LOCAL_LRU;
        }

        final RuntimeCache ret = RuntimeCache.valueOf(runtimeCache);
        if (null == ret) {
            throw new RuntimeException("Please configures a valid runtime cache in local.properties!");
        }

        return ret;
    }

}
