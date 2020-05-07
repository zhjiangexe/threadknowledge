package com.jiang.cache;

import com.jiang.cache.computable.Computable;
import com.jiang.cache.computable.ExpensiveFunction;

import java.util.HashMap;
import java.util.Map;

/**
 * use synchronized to do, but it's not thread-safe
 */
public class CacheDemo4<A, V> implements Computable<A, V> {
  private final Map<A, V> cache = new HashMap<>();
  private final Computable<A, V> c;

  public CacheDemo4(Computable<A, V> c) {
    this.c = c;
  }

  @Override
  public V compute(A arg) throws Exception {
    System.out.println("go into cache mechanism");
    V result = cache.get(arg);
    if (result == null) {
      result = c.compute(arg);
      synchronized (this) {
        cache.put(arg, result);
      }
    }
    return result;
  }

  public static void main(String[] args) throws Exception {
    CacheDemo4<String, Integer> expensiveFunction = new CacheDemo4<>(new ExpensiveFunction());
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
