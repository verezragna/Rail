package railroad.service;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseService<K, T> {
    private Map<K, CachedEntity<T>> cache = new HashMap<>();

    private static final long TTL = 3000;

    private static class CachedEntity<T> {
        T entity;
        long endLife;

        CachedEntity(T entity) {
            this.entity = entity;
            endLife = System.currentTimeMillis() + TTL;
        }
    }

    void putEntity(K id, T entity) {
        cache.put(id, new CachedEntity<>(entity));
    }

    T getCachedEntity(K id) {
        CachedEntity<T> cachedEntities = cache.get(id);
        if ((cachedEntities != null) && (System.currentTimeMillis() > cachedEntities.endLife)) {
            return cachedEntities.entity;
        } else return null;
    }
}