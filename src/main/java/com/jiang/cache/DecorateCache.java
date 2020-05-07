package com.jiang.cache;

import com.jiang.cache.computable.Computable;
import com.jiang.cache.computable.ExpensiveFunction;

import java.util.HashMap;
import java.util.Map;

/**
 * use decorate pattern to add cache
 */
public class DecorateCache<A, V> implements Computable<A, V> {
  private final Map<A, V> cache = new HashMap<>();
  private final Computable<A, V> c;

  public DecorateCache(Computable<A, V> c) {
    this.c = c;
  }

  @Override
  public V compute(A arg) throws Exception {
    System.out.println("go into cache mechanism");
    V result = cache.get(arg);
    if (result == null) {
      result = c.compute(arg);
      cache.put(arg, result);
    }
    return result;
  }

  public static void main(String[] args) throws Exception {
    DecorateCache<String, Integer> expensiveFunction = new DecorateCache<>(new ExpensiveFunction());
    Integer result = expensiveFunction.compute("666");
    System.out.println("first compute: " + result);
    result = expensiveFunction.compute("666");
    System.out.println("second compute: " + result);
  }
}
