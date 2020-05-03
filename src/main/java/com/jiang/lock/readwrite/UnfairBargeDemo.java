package com.jiang.lock.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * fair and unfair strategy
 */
public class UnfairBargeDemo {
  private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(false);
  private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
  private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

  private static void read() {
    System.out.println(Thread.currentThread().getName() + " trying to get readlock");
    readLock.lock();
    try {
      System.out.println(Thread.currentThread().getName() + " get the readlock, reading...");
      Thread.sleep(20);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      System.out.println(Thread.currentThread().getName() + " release readlock");
      readLock.unlock();
    }
  }

  private static void write() {
    System.out.println(Thread.currentThread().getName() + " trying to get writelock");
    writeLock.lock();
    try {
      System.out.println(Thread.currentThread().getName() + " get the writelock, writing...");
      Thread.sleep(40);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      System.out.println(Thread.currentThread().getName() + " release writelock");
      writeLock.unlock();
    }
  }

  public static void main(String[] args) {
    new Thread(() -> write(), "Thread1").start(); // block thread 2 and 3
    new Thread(() -> read(), "Thread2").start();
    new Thread(() -> read(), "Thread3").start();
    new Thread(() -> write(), "Thread4").start();
    new Thread(() -> read(), "Thread5").start();
    new Thread(() -> {
      Thread[] threads = new Thread[10];
      for (int i = 0; i < 10; i++) {
        threads[i] = new Thread(() -> read(), "sub thread" + i);
      }
      for (int i = 0; i < 10; i++) {
        threads[i].start();
      }
    }).start();
  }
}
