package com.jiang.concurrency.threadlocal;

public class ThreadLocalNPE {
  ThreadLocal<Long> longLocal = new ThreadLocal<>();

  public void set() {
    longLocal.set(Thread.currentThread().getId());
  }

  // long -> Long happen
  public Long get() {
    return longLocal.get();
  }

  public static void main(String[] args) {
    ThreadLocalNPE threadLocalNPE = new ThreadLocalNPE();
//    threadLocalNPE.set();
    System.out.println(threadLocalNPE.get());
    new Thread(() -> {
      threadLocalNPE.set();
      System.out.println(threadLocalNPE.get());
    }).start();
  }
}
