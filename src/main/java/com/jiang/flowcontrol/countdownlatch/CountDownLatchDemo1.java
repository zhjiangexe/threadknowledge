package com.jiang.flowcontrol.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * case1
 * a factory, 5 people are doing Product inspection, products are pass if they say yes
 */
public class CountDownLatchDemo1 {
  public static void main(String[] args) throws InterruptedException {
    CountDownLatch latch = new CountDownLatch(5); // support multiple thread
    ExecutorService service = Executors.newFixedThreadPool(5);
    for (int i = 0; i < 5; i++) {
      final int no = i + 1;
      Runnable runnable = () -> {
        try {
          Thread.sleep((long) (Math.random() * 10000));
          System.out.println("No." + no + " was finished");
        } catch (InterruptedException e) {
          e.printStackTrace();
        } finally {
          latch.countDown();
        }
      };
      service.submit(runnable);
    }
    System.out.println("waiting product inspection");
    latch.await();
    System.out.println("all people finished their job.");
  }
}
