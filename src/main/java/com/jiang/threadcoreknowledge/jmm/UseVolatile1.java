package com.jiang.threadcoreknowledge.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 */
public class UseVolatile1 implements Runnable {
  volatile boolean done = false;
  static AtomicInteger realA = new AtomicInteger();

  public static void main(String[] args) throws InterruptedException {
    UseVolatile1 noVolatile = new UseVolatile1();
    Thread t1 = new Thread(noVolatile);
    Thread t2 = new Thread(noVolatile);
    t1.start();
    t2.start();
    t1.join();
    t2.join();
    System.out.println(noVolatile.done);
    System.out.println(realA.get());
  }

  @Override
  public void run() {
    for (int i = 0; i < 10000; i++) {
      setDone();
      realA.incrementAndGet();
    }
  }

  private void setDone() {
    done = true; // do not care previous status
  }
}
