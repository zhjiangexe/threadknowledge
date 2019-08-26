package com.jiang.threadcoreknowledge.threadobjectclasscommonmethods;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * demonstrate do not release lock (lock should release manually)
 */
public class SleepDontReleaseLock implements Runnable {
  private static final Lock lock = new ReentrantLock();

  @Override
  public void run() {
    lock.lock();
    System.out.println(Thread.currentThread().getName() + " get lock");
    try {
      Thread.sleep(5000);
      System.out.println(Thread.currentThread().getName() + " is waked up");
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  public static void main(String[] args) {
    SleepDontReleaseLock sleepDontReleaseLock = new SleepDontReleaseLock();
    Thread thread1 = new Thread(sleepDontReleaseLock);
    Thread thread2 = new Thread(sleepDontReleaseLock);
    thread1.start();
    thread2.start();
  }
}
