package com.mall.system.cache;

import com.alibaba.dubbo.common.json.JSONObject;
import java.util.Collection;

/**
 * None cache.
 */
public final class NoneCache extends AbstractCache {

    @Override
    public boolean contains(final String key) {
        return false;
    }

    @Override
    public void put(final String key, final JSONObject value) {
    }

    @Override
    public JSONObject get(final String key) {
        return null;
    }

    @Override
    public void remove(final String key) {
    }

    @Override
    public void remove(final Collection<String> keys) {
    }

    @Override
    public void removeAll() {
    }

    @Override
    public void collect() {
    }
}
