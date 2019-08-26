package com.jiang.threadcoreknowledge.synchronization;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample15 {
  public static void main(String[] args) {
    Lock lock = new ReentrantLock();
    lock.lock();
    lock.unlock();
    lock.newCondition();
    lock.tryLock();
//    lock.tryLock(10, TimeUnit.HOURS);
  }
}
