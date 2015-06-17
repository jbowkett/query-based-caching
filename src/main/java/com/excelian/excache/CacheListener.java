package com.excelian.excache;

/**
 * Created by jbowkett on 17/06/15.
 */
public interface CacheListener<T> {
  void broadcast(T object);
}
