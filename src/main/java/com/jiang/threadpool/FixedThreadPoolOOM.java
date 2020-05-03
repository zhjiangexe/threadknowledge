package com.jiang.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolOOM {
  private static ExecutorService executorService = Executors.newFixedThreadPool(1);

  public static void main(String[] args) {
    for (int i = 0; i < Integer.MAX_VALUE; i++) {
      executorService.execute(new SubThread());
    }
  }
}

// give Queue is fulled
// it will get Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
class SubThread implements Runnable {
  @Override
  public void run() {
    try {
      Thread.sleep(1000000000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}