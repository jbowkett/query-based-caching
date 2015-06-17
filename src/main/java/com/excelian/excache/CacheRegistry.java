package com.excelian.excache;

import java.util.function.Predicate;

/**
 * Created by jbowkett on 17/06/15.
 */
public interface CacheRegistry<T extends Object> {
  public void registerCache(Cache<T> queryCache, Predicate<T> predicate);

  void broadcast(T object);
}
