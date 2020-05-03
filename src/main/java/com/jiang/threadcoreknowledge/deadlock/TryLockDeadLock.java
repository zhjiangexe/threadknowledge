package com.jiang.threadcoreknowledge.deadlock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockDeadLock implements Runnable {
  int flag = 1;
  static Lock lock1 = new ReentrantLock();
  static Lock lock2 = new ReentrantLock();

  public static void main(String[] args) {
    TryLockDeadLock r1 = new TryLockDeadLock();
    r1.flag = 1;
    TryLockDeadLock r2 = new TryLockDeadLock();
    r2.flag = 0;
    new Thread(r1).start();
    new Thread(r2).start();
  }

  @Override
  public void run() {
    for (int i = 0; i < 100; i++) {
      if (flag == 1) {
        try {
          if (lock1.tryLock(800, TimeUnit.MILLISECONDS)) {
            System.out.println("thread 1 get lock1 successfully");
            Thread.sleep(new Random().nextInt(1000));
            if (lock2.tryLock(800, TimeUnit.MILLISECONDS)) {
              System.out.println("thread 1 get lock2 successfully");
              System.out.println("thread 1 get two lock successfully");
              lock2.unlock();
              lock1.unlock();
              break;
            } else {
              System.out.println("thread 1 get lock2 fail, retry");
              lock1.unlock();
              Thread.sleep(new Random().nextInt(1000));
            }
          } else {
            System.out.println("thread 1 get lock1 fail, retry");
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      if (flag == 0) {
        try {
          if (lock2.tryLock(3000, TimeUnit.MILLISECONDS)) {
            System.out.println("thread 2 get lock2 successfully");
            Thread.sleep(new Random().nextInt(1000));
            if (lock1.tryLock(3000, TimeUnit.MILLISECONDS)) {
              System.out.println("thread 2 get lock1 successfully");
              System.out.println("thread 2 get two lock successfully");
              lock2.unlock();
              lock1.unlock();
              break;
            } else {
              System.out.println("thread 2 et lock1 fail, retry");
              lock1.unlock();
              Thread.sleep(new Random().nextInt(1000));
            }
          } else {
            System.out.println("thread 2 get lock2 fail, retry");
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
