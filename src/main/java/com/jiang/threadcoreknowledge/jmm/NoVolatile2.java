package com.jiang.threadcoreknowledge.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 */
public class NoVolatile2 implements Runnable {
  volatile boolean done = false;
  static AtomicInteger realA = new AtomicInteger();

  public static void main(String[] args) throws InterruptedException {
    NoVolatile2 noVolatile = new NoVolatile2();
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
      setFlipDone();
      realA.incrementAndGet();
    }
  }

  private void setFlipDone() {
    done = !done; // dependent previous status
  }
}
