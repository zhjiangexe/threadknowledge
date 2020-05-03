package com.jiang.lock.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述 Lock不會像synchronized一樣，
 * 異常的時候自動釋放鎖，所以最佳實踐是，
 * finally中釋放鎖，以便保證發生異常的時候鎖一定被釋放
 */
public class MustUnlock {
  private static Lock lock = new ReentrantLock();

  public static void main(String[] args) {
    lock.lock();
    try {
      // get resource in this lock
      System.out.println(Thread.currentThread().getName() + " start run");
    } finally {
      lock.unlock();
    }
  }
}
