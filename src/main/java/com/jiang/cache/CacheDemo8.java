package com.jiang.cache;

import com.jiang.cache.computable.Computable;
import com.jiang.cache.computable.ExpensiveFunction;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * use Future to avoid compute duplicately
 */
public class CacheDemo8<A, V> implements Computable<A, V> {
  private final ConcurrentHashMap<A, Future<V>> cache = new ConcurrentHashMap<>();
  private final Computable<A, V> c;

  public CacheDemo8(Computable<A, V> c) {
    this.c = c;
  }

  @Override
  public V compute(A arg) throws Exception {
    Future<V> f = cache.get(arg);
    if (f == null) {
      FutureTask<V> ft = new FutureTask<>(new Callable<V>() {
        @Override
        public V call() throws Exception {
          return c.compute(arg);
        }
      });
      f = cache.putIfAbsent(arg, ft); // first thread put ft return null, then second thread will get future, then go to f.get() to wait data
      if (f == null) {
        f = ft;
        System.out.println("invoke compute by FutureTask");
        ft.run(); // run call
      }
    }
    return f.get();
  }

  public static void main(String[] args) throws Exception {
    CacheDemo8<String, Integer> expensiveFunction = new CacheDemo8<>(new ExpensiveFunction());
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
