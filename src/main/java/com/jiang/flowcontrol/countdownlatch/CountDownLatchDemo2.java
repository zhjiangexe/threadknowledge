package com.jiang.flowcontrol.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * simulate 100 meter race, 5 athlete are ready, only wait Judges, everyone run
 */
public class CountDownLatchDemo2 {
  public static void main(String[] args) throws InterruptedException {
    CountDownLatch begin = new CountDownLatch(1);
    ExecutorService service = Executors.newFixedThreadPool(5);
    for (int i = 0; i < 5; i++) {
      final int no = i + 1;
      Runnable runnable = () -> {
        System.out.println("NO." + no + " ready, wait");
        try {
          begin.await();
          System.out.println("NO." + no + " start to run");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      };
      service.submit(runnable);
    }
    Thread.sleep(5000);
    System.out.println("race begin");
    begin.countDown();
    service.shutdown();
  }
}
