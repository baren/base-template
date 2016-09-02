package com.enwu.mb.springcacheimpl;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.AbstractCacheManager;

import java.util.Collection;

/**
 * Created by user on 16/7/26.
 */
public class CommonCacheManager extends AbstractCacheManager {

    @Override
    protected Collection<? extends Cache> loadCaches() {
        return null;
    }

}
