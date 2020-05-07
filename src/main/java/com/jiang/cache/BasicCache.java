package com.jiang.cache;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * HashMap
 */
public class BasicCache {
  private final HashMap<String, Integer> cache = new HashMap<>();

  public synchronized Integer compute(String userId) throws InterruptedException {
    Integer result = cache.get(userId);
    if (result == null) {
      result = doCompute(userId);
      cache.put(userId, result);
    }
    return result;
  }

  private Integer doCompute(String userId) throws InterruptedException {
    TimeUnit.SECONDS.sleep(5);
    return new Integer(userId);
  }

  public static void main(String[] args) throws InterruptedException {
    BasicCache basicCache = new BasicCache();
    System.out.println("start compute");
    Integer compute = basicCache.compute("13");
    System.out.println("first compute: " + compute);
    compute = basicCache.compute("13");
    System.out.println("second compute: " + compute);
  }
}
