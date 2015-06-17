package com.excelian.excache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.function.Predicate;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by jbowkett on 17/06/15.
 */

@RunWith(MockitoJUnitRunner.class)
public class QueryCacheTest {

  private Cache<String> theCache;
  private CacheRegistry<String> theCacheRegistry;
  private Cache<String> theQueryCache;
  private Predicate<String> thePredicate;

  //    final Predicate<String> isEmpty = String::isEmpty;
  //    final Predicate<String> selectIfLengthGreaterThanZero = s -> s.length() > 0;

  
  @Test
  public void testWhenACacheReceivesAnUpdateItConsultsEachOfTheQueryCachesPredicates(){
    given_AQueryCacheRegistryThatSpecifiesPredicatesForEachCache();
    given_AVanillaCacheOfTypeString();
    given_APredicateForTheQueryCache();
    given_AQueryCacheThatRegistersWithThePredicate();
    when_ACacheReceivesAnUpdatedValueOf("UPDATED VALUE");
    then_EachPredicateWillBeConsultedWithTheValue("UPDATED VALUE");
  }

  @Test
  public void testWhenAQueryCacheSpecifiesAPredicateThatWillPerformSelectionItReceivesAMessageToUpdateFromItsDatastore(){
    given_AQueryCacheRegistryThatSpecifiesPredicatesForEachCache();
    given_AVanillaCacheOfTypeString();
    given_APredicateForTheQueryCache();
    given_AQueryCacheThatRegistersWithThePredicate();
    given_ThePredicateReturnsTrueToSelect();
    when_ACacheReceivesAnUpdatedValueOf("UPDATED VALUE");
    then_TheQueryCacheWillReceiveAnEvictionMessageForTheValue("UPDATED VALUE");
  }

  private void given_APredicateForTheQueryCache() {
    thePredicate = (Predicate<String>)mock(Predicate.class);
  }

  private void given_AQueryCacheThatRegistersWithThePredicate() {
    theQueryCache = mock(Cache.class);
    theCacheRegistry.registerCache(theQueryCache, thePredicate);
  }

  private void given_AVanillaCacheOfTypeString() {
    theCache = new VanillaCache<>(theCacheRegistry);
  }

  private void given_AQueryCacheRegistryThatSpecifiesPredicatesForEachCache() {
    theCacheRegistry = new VanillaCacheRegistry<>();
  }

  private void when_ACacheReceivesAnUpdatedValueOf(String object) {
    theCache.updateValue(object);
  }

  private void then_EachPredicateWillBeConsultedWithTheValue(String theValue) {
    verify(thePredicate).test(theValue);
  }

  private void then_TheQueryCacheWillReceiveAnEvictionMessageForTheValue(String value) {
    verify(theQueryCache).evict(value);
  }

  private void given_ThePredicateReturnsTrueToSelect() {
    when(thePredicate.test(anyString())).thenReturn(true);
  }
}
