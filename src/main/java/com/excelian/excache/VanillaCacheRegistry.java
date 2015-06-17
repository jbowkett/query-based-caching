package com.excelian.excache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

/**
 * Created by jbowkett on 17/06/15.
 */
public class VanillaCacheRegistry<T extends Object> implements CacheRegistry<T> {
  
  final ConcurrentHashMap<Predicate<T>, QueryCache<T>> cacheRegistry = new ConcurrentHashMap<>();

  @Override
  public void registerCache(QueryCache<T> queryCache, Predicate<T> predicate) {
    cacheRegistry.put(predicate, queryCache);
  }

  @Override
  public void broadcast(T object) {
    for (Map.Entry<Predicate<T>, QueryCache<T>> entry : cacheRegistry.entrySet()) {
      entry.getKey().test(object);
    }
  }
}
