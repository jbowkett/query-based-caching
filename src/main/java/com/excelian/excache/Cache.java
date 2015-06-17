package com.excelian.excache;

/**
 * Created by jbowkett on 17/06/15.
 */
public interface Cache<T> {
  void updateValue(T object);

  void evict(T value);
}
