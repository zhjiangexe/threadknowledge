package com.jiang.cache;

import com.jiang.cache.computable.ExpensiveFunction;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CacheDemo12 {
  static CacheDemo10<String, Integer> expensiveComputer = new CacheDemo10<>(new ExpensiveFunction());

  public static void main(String[] args) {
    ExecutorService service = Executors.newFixedThreadPool(100);
    long start = System.currentTimeMillis();
    for (int i = 0; i < 100; i++) {
      service.submit(() -> {
        Integer result = null;
        try {
          result = expensiveComputer.compute("666");
        } catch (ExecutionException e) {
          e.printStackTrace();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(result);
      });
    }
    service.shutdown();
    while (!service.isTerminated()) {

    }
    System.out.println("spend time: " + (System.currentTimeMillis() - start));
  }
}
