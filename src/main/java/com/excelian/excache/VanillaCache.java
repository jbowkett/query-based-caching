package com.excelian.excache;

import java.util.ArrayList;

/**
 * Created by jbowkett on 17/06/15.
 */
public class VanillaCache<T> implements Cache<T> {
  private final ArrayList<CacheListener<T>> listeners = new ArrayList<>();

  @Override
  public void registerListener(CacheListener<T> listener){
    listeners.add(listener);
  }

  @Override
  public void updateValue(T object) {
    storeItInMyCacheOrWhatever(object);
    listeners.forEach(l -> l.broadcast(object));
  }
  
  @Override
  public void evict(T value){

  }
  

  private void storeItInMyCacheOrWhatever(T object) {
    
  }
}
