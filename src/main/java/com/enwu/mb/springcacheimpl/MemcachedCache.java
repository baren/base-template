package com.enwu.mb.springcacheimpl;

import org.springframework.cache.Cache;

/**
 * Created by user on 16/7/26.
 */
public class MemcachedCache implements Cache {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public Object getNativeCache() {
        return null;
    }

    @Override
    public ValueWrapper get(Object key) {
        return null;
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        return null;
    }

    @Override
    public void put(Object key, Object value) {

    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        return null;
    }

    @Override
    public void evict(Object key) {

    }

    @Override
    public void clear() {

    }
}
