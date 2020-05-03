package com.jiang.lock.lock;

import java.util.concurrent.atomic.AtomicInteger;

public class PessimismOptimismLock {
  int a;

  public static void main(String[] args) {
    AtomicInteger atomicInteger = new AtomicInteger();
    atomicInteger.incrementAndGet();
  }

  // different thread can not invoke this method
  public synchronized void testMethod() {
    a++;
  }
}
