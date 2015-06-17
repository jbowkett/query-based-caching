package com.excelian.excache;

/**
 * Created by jbowkett on 17/06/15.
 */
public class VanillaCache<T> implements Cache<T> {
  private final CacheRegistry<T> theCacheRegistry;

  public VanillaCache(CacheRegistry<T> theCacheRegistry) {
    this.theCacheRegistry = theCacheRegistry;
  }

  @Override
  public void updateValue(T object) {
    storeItInMyCacheOrWhatever(object);
    theCacheRegistry.broadcast(object);
  }
  
  @Override
  public void evict(T value){

  }
  

  private void storeItInMyCacheOrWhatever(T object) {
    
  }
}
