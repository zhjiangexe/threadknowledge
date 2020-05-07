package com.jiang.cache;

import com.jiang.cache.computable.Computable;
import com.jiang.cache.computable.ExpensiveFunction;

import java.util.HashMap;
import java.util.Map;

/**
 * use decorate pattern to add cache
 */
public class DecorateCacheWithMultiThread<A, V> implements Computable<A, V> {
  private final Map<A, V> cache = new HashMap<>();
  private final Computable<A, V> c;

  public DecorateCacheWithMultiThread(Computable<A, V> c) {
    this.c = c;
  }

  @Override
  public synchronized V compute(A arg) throws Exception {
    System.out.println("go into cache mechanism");
    V result = cache.get(arg);
    if (result == null) {
      result = c.compute(arg);
      cache.put(arg, result);
    }
    return result;
  }

  public static void main(String[] args) throws Exception {
    DecorateCacheWithMultiThread<String, Integer> expensiveFunction = new DecorateCacheWithMultiThread<>(new ExpensiveFunction());
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
