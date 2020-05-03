package com.jiang.threadpool;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShutDowm {
  public static void main(String[] args) throws InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    for (int i = 0; i < 1000; i++) {
      executorService.execute(new ShutDownTask());
    }
    Thread.sleep(1500);
//    System.out.println(executorService.isShutdown());
//    executorService.shutdown();
//    System.out.println(executorService.isShutdown());
//    Thread.sleep(1500);
//    System.out.println(executorService.isTerminated());
//    executorService.execute(new ShutDownTask());
//    executorService.isShutdown();

//    boolean b = executorService.awaitTermination(3L, TimeUnit.SECONDS);
//    System.out.println(b);

    List<Runnable> runnables = executorService.shutdownNow();
    for (Runnable runnable : runnables) {
      System.out.println(((ShutDownTask) runnable).zero);
    }
  }
}

class ShutDownTask implements Runnable {
  String zero = "100";

  @Override
  public void run() {
    try {
      Thread.sleep(500);
      System.out.println(Thread.currentThread().getName());
    } catch (InterruptedException e) {
      System.out.println(Thread.currentThread().getName() + " was interrupted");
    }
  }
}