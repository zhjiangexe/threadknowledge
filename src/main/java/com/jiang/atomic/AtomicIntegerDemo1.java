package com.jiang.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * show thread safe problem by comparing not atomic class
 * after using atomic class, needn't lock can promise thread safe
 */
public class AtomicIntegerDemo1 implements Runnable {
  private static final AtomicInteger atomicInteger = new AtomicInteger();

  public void incrementAtomic() {
    atomicInteger.getAndIncrement();
  }

  public static volatile int basicCount = 0;

  // adding synchronized promise basicCount
  public void incrementBasic() {
    basicCount++;
  }

  @Override
  public void run() {
    for (int i = 0; i < 1000; i++) {
      incrementAtomic();
      incrementBasic();
    }
  }

  public static void main(String[] args) throws InterruptedException {
    AtomicIntegerDemo1 r = new AtomicIntegerDemo1();
    Thread thread1 = new Thread(r);
    Thread thread2 = new Thread(r);
    thread1.start();
    thread2.start();
    thread1.join();
    thread2.join();
    System.out.println("atomic* :" + atomicInteger.get());
    System.out.println("basic: " + basicCount);
  }
}
