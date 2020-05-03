package com.jiang.lock.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class LockInterruptibility implements Runnable {
  private Lock lock = new ReentrantLock();

  public static void main(String[] args) {
    LockInterruptibility lockInterruptibility = new LockInterruptibility();
    Thread thread0 = new Thread(lockInterruptibility);
    Thread thread1 = new Thread(lockInterruptibility);
    thread0.start();
    thread1.start();
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    thread1.interrupt();
  }
  @Override
  public void run() {
    System.out.println(Thread.currentThread().getName() + " try to get lock");
    try {
      lock.lockInterruptibly();
      try {
        System.out.println(Thread.currentThread().getName() + " get lock");
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        System.out.println(Thread.currentThread().getName() + " interrupt sleep");
      } finally {
        lock.unlock();
        System.out.println(Thread.currentThread().getName() + " released lock");
      }
    } catch (InterruptedException e) {
      System.out.println(Thread.currentThread().getName() + " interrupt get lock");
    }
  }
}
