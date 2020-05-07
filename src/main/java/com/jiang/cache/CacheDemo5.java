package com.jiang.cache;

import com.jiang.cache.computable.Computable;
import com.jiang.cache.computable.ExpensiveFunction;

import java.util.concurrent.ConcurrentHashMap;

/**
 * use synchronized to do, but it's not thread-safe
 */
public class CacheDemo5<A, V> implements Computable<A, V> {
  private final ConcurrentHashMap<A, V> cache = new ConcurrentHashMap<>();
  private final Computable<A, V> c;

  public CacheDemo5(Computable<A, V> c) {
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
    CacheDemo5<String, Integer> expensiveFunction = new CacheDemo5<>(new ExpensiveFunction());
    new Thread(() -> {
      try {
        Integer result = expensiveFunction.compute("666");
        System.out.println("first compute: " + result);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }).start();

    new Thread(() -> {
      try {
        Integer result = expensiveFunction.compute("667");
        System.out.println("second compute: " + result);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }).start();

    new Thread(() -> {
      try {
        Integer result = expensiveFunction.compute("666");
        System.out.println("third compute: " + result);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }).start();
  }
}
