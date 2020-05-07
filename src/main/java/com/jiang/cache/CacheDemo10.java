package com.jiang.cache;

import com.jiang.cache.computable.Computable;
import com.jiang.cache.computable.MayFail;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * expiry date, auto remove
 */
public class CacheDemo10<A, V> implements Computable<A, V> {
  private final ConcurrentHashMap<A, Future<V>> cache = new ConcurrentHashMap<>();
  private final Computable<A, V> c;

  public CacheDemo10(Computable<A, V> c) {
    this.c = c;
  }

  @Override
  public V compute(A arg) throws ExecutionException, InterruptedException {
    while (true) {
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
      try {
        return f.get();
      } catch (CancellationException e) {
        System.out.println("compute was canceled");
        cache.remove(arg);
        throw e;
      } catch (InterruptedException e) {
        cache.remove(arg);
        throw e;
      } catch (ExecutionException e) {
        cache.remove(arg);
        System.out.println("compute wrong, retry");
      }
    }
  }

  public final static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

  public V compute(A arg, long expire) throws ExecutionException, InterruptedException {
    if (expire > 0) {
      executorService.schedule(new Runnable() {
        @Override
        public void run() {
          expire(arg);
        }
      }, expire, TimeUnit.MILLISECONDS);
    }
    return compute(arg);
  }

  private synchronized void expire(A key) {
    Future<V> future = cache.get(key);
    if (future != null) {
      if (!future.isDone()) {
        System.out.println("FutureTask was canceled");
        future.cancel(true);
      }
      System.out.println(key + " is expired");
      cache.remove(key);
    }
  }

  private V computeRandomExpire(A arg) throws ExecutionException, InterruptedException {
    long randomExpire = (long) (Math.random() * 10000);
    return compute(arg, randomExpire);
  }

  public static void main(String[] args) throws Exception {
    CacheDemo10<String, Integer> expensiveFunction = new CacheDemo10<>(new MayFail());
    new Thread(() -> {
      try {
        Integer result = expensiveFunction.compute("666", 5000L);
        System.out.println("first compute: " + result);
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
    new Thread(() -> {
      try {
        Integer result = expensiveFunction.compute("667");
        System.out.println("second compute: " + result);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }).start();
    Thread.sleep(6000);
    Integer compute = expensiveFunction.compute("666");
    System.out.println("main: " + compute);
    executorService.shutdown();
//    Thread.sleep(500);
//    Future<Integer> future = expensiveFunction.cache.get("666");
//    future.cancel(false);
  }
}
