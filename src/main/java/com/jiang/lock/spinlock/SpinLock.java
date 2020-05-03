package com.jiang.lock.spinlock;

import java.util.concurrent.atomic.AtomicReference;

public class SpinLock {
  private AtomicReference<Thread> sign = new AtomicReference<>();

  public void lock() {
    Thread current = Thread.currentThread();
    while (!sign.compareAndSet(null, current)) { // if you
      System.out.println(current.getName() + " get spinlock is fail, try again");
    }
  }

  public void unlock() {
    Thread current = Thread.currentThread();
    sign.compareAndSet(current, null);
  }

  public static void main(String[] args) {
    SpinLock spinLock = new SpinLock();
    Runnable runnable = () -> {
      System.out.println(Thread.currentThread().getName() + " trying to get spinlock");
      spinLock.lock();
      System.out.println(Thread.currentThread().getName() + " got spinlock");
      try {
        Thread.sleep(5);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        spinLock.unlock();
        System.out.println(Thread.currentThread().getName() + " release spinlock");
      }
    };
    Thread thread1 = new Thread(runnable);
    Thread thread2 = new Thread(runnable);
    thread1.start();
    thread2.start();
  }
}
