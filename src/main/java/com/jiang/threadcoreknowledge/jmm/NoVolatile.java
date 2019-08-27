package com.jiang.threadcoreknowledge.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * It is not suitable for volatile
 */
public class NoVolatile implements Runnable {

  volatile int a;
  static AtomicInteger realA = new AtomicInteger();
  public static void main(String[] args) throws InterruptedException {
    NoVolatile noVolatile = new NoVolatile();
    Thread t1 = new Thread(noVolatile);
    Thread t2 = new Thread(noVolatile);
    t1.start();
    t2.start();
    t1.join();
    t2.join();
    System.out.println(noVolatile.a);
    System.out.println(realA.get());
  }
  @Override
  public void run() {
    for (int i = 0; i < 10000; i++) {
      a++;
      realA.incrementAndGet();
    }
  }
}
