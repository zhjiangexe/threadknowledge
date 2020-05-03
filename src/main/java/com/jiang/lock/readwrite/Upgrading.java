package com.jiang.lock.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Upgrading {
  private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(false);
  private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
  private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

  private static void readUpgrading() {
    readLock.lock();
    try {
      System.out.println(Thread.currentThread().getName() + " get read lock, it's reading");
      Thread.sleep(1000);
      System.out.println("upgrading will bring block");
      writeLock.lock(); // upgrading fail
      System.out.println(Thread.currentThread().getName() + " upgrade readlock to writelock");
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      writeLock.unlock();
      System.out.println(Thread.currentThread().getName() + " released read lock");
      readLock.unlock();
    }
  }

  private static void writeDowngrading() {
    writeLock.lock();
    try {
      System.out.println(Thread.currentThread().getName() + " get write lock, it's writing");
      Thread.sleep(1000);
      readLock.lock(); // upgrading fail
      System.out.println(Thread.currentThread().getName() + " when do not release writelock, directly get readlock successfuly");
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      readLock.unlock();
      System.out.println(Thread.currentThread().getName() + " released write lock");
      writeLock.unlock();
    }
  }

  public static void main(String[] args) throws InterruptedException {
    System.out.println("downgrading is able to");
//    Thread thread1 = new Thread(() -> writeDowngrading(), "Thread1");
//    thread1.start();
//    thread1.join();
    System.out.println("----------------");
    System.out.println("show upgrade will fail");
    new Thread(() -> readUpgrading(), "Thread2").start();
  }
}
