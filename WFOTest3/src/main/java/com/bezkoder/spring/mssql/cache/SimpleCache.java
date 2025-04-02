package com.bezkoder.spring.mssql.cache;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * Simple implementation of a cache
 * TODOs also to implement eviction
 */
public class SimpleCache {
    Map<String, CacheValue> map = new HashMap<>();

    public void put(String key, Object value, int ttl) {
        Instant expiredAt = Instant.now().plusSeconds(ttl);
        CacheValue cacheValue = new CacheValue(value, expiredAt);
        map.put(key, cacheValue);
    }

    public <T> T get(String key, Class<T> clazz) {
        CacheValue cacheValue = map.get(key);
        if (cacheValue != null) {
            if (cacheValue.expireAt.isBefore(Instant.now())) {
                map.remove(key);
            } else {
                T t;
                try {
                    t = clazz.cast(cacheValue.getValue());
                } catch (ClassCastException e) {
                    throw new IllegalArgumentException("Error on deserializing value for "  +key + " invalid target class " + clazz);
                }
                return t;
            }
        }

        return null;

    }

}
