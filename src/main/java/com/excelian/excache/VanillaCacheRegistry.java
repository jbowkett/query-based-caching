package com.excelian.excache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

/**
 * Created by jbowkett on 17/06/15.
 */
public class VanillaCacheRegistry<T extends Object> implements CacheRegistry<T> {
  
  final ConcurrentHashMap<Predicate<T>, Cache<T>> cacheRegistry = new ConcurrentHashMap<>();

  @Override
  public void registerCache(Cache<T> queryCache, Predicate<T> predicate) {
    cacheRegistry.put(predicate, queryCache);
  }

  @Override
  public void broadcast(T value) {
    for (Map.Entry<Predicate<T>, Cache<T>> entry : cacheRegistry.entrySet()) {
      final boolean shouldEvict = entry.getKey().test(value);
      if(shouldEvict){
        entry.getValue().evict(value);
      }
    }
  }
}
