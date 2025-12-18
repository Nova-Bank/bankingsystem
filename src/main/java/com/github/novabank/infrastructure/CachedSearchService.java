package com.github.novabank.infrastructure;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Temporary cache for search operations.
 */
@Service
public class CachedSearchService {

    private final Map<String, Object> cache = new HashMap<>();

    /**
     * Retrieves cached result.
     */
    public Object get(String key) {
        return cache.get(key);
    }

    /**
     * Stores cached result.
     */
    public void put(String key, Object value) {
        cache.put(key, value);
    }
}
