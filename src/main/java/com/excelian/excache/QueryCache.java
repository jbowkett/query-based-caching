package com.excelian.excache;

import java.util.function.Predicate;

/**
 * Created by jbowkett on 17/06/15.
 */
public class QueryCache<T> {
  private final Predicate<T> selectorPredicate;

  public QueryCache(Predicate<T> predicate) {
    selectorPredicate = predicate;
  }
}
