package com.jiang.threadcoreknowledge.synchronization;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedToLock13 {
  Lock lock = new ReentrantLock();

  public static void main(String[] args) {
    SynchronizedToLock13 s = new SynchronizedToLock13();
    s.method1();
    s.method2();
  }
  public synchronized void method1() {
    System.out.println("I'm synchronized method lock");
  }

  public void method2() {
    lock.lock();
    try {
      System.out.println("I'm lock method lock");
    } finally {
      lock.unlock();
    }
  }
}
