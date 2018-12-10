package com.mall.system.cache.memory;

/**
 * @author 崔立东
 * @date 2018/12/10 17:19
 */

import com.mall.system.cache.AbstractCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.dubbo.common.json.JSONObject;
import java.io.Serializable;
import java.util.Collection;

/**
 * This is a Least Recently Used (LRU) pure memory cache. This cache use a thread-safe {@link DoubleLinkedMap} to hold
 * the objects, and the least recently used objects will be moved to the end of the list and to remove by invoking
 * {@link #collect()} method.
 *
 * @author <a href="http://88250.b3log.org">Liang Ding</a>
 * @version 2.0.3.10, Jul 6, 2017
 */
public final class LruMemoryCache extends AbstractCache implements Serializable {

    /**
     * Logger.
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(LruMemoryCache.class);

    /**
     * a thread-safe double linked list is used to hold all objects.
     */
    private DoubleLinkedMap<String, JSONObject> map;

    /**
     * Constructs a {@code LruMemoryCache} object.
     */
    public LruMemoryCache() {
        map = new DoubleLinkedMap<>();
    }

    @Override
    public void put(final String key, final JSONObject value) {
        remove(key);

        putCountInc();

        synchronized (this) {
            if (getCachedCount() >= getMaxCount()) {
                collect();
            }

            map.addFirst(key, value);

            cachedCountInc();
        }
    }

    @Override
    public synchronized JSONObject get(final String key) {
        final JSONObject ret = map.get(key);
        if (null != ret) {
            hitCountInc();
            map.makeFirst(key);

            return ret;
        }

        missCountInc();

        return null;
    }

    @Override
    public synchronized void remove(final String key) {
        final boolean removed = map.remove(key);
        if (removed) {
            cachedCountDec();
        }
    }

    @Override
    public synchronized void remove(final Collection<String> keys) {
        for (final String key : keys) {
            remove(key);
        }
    }

    @Override
    public synchronized void collect() {
        map.removeLast();
        cachedCountDec();
    }

    @Override
    public synchronized void removeAll() {
        map.removeAll();
        setCachedCount(0);
        setMissCount(0);
        setHitCount(0);
    }

    @Override
    public boolean contains(final String key) {
        return null != get(key); // XXX: performance issue
    }
}
