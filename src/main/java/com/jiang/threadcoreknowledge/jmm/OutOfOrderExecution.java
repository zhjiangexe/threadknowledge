package com.jiang.threadcoreknowledge.jmm;

import java.util.concurrent.CountDownLatch;

public class OutOfOrderExecution {
  private static int x = 0, y = 0;
  private static int a = 0, b = 0;

  public static void main(String[] args) throws InterruptedException {
    int i = 0;
    for (; ; ) {
      i++;
      a = 0;
      b = 0;
      x = 0;
      y = 0;
      CountDownLatch latch = new CountDownLatch(1);
      Thread one = new Thread(() -> {
        try {
          latch.await();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        a = 1;
        x = b;
      });
      Thread two = new Thread(() -> {
        try {
          latch.await();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        b = 1;
        y = a;
      });
      one.start();
      two.start();
      latch.countDown(); // work with await(), launch start
      one.join();
      two.join();
      if (x == 0 && y == 0) {
        System.out.println(i + " times, x = " + x + ", y = " + y);
        break;
      } else {
        System.out.println(i + " times, x = " + x + ", y = " + y);
      }
    }
  }
}
