package com.jiang.flowcontrol.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * basic
 */
public class ConditionDemo1 {
  private ReentrantLock lock = new ReentrantLock();
  private Condition condition = lock.newCondition();

  void method1() {
    lock.lock();
    try {
      System.out.println("condition is not met, start to await");
      condition.await();
      System.out.println("condition is met, start to execute");
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  void method2() {
    lock.lock();
    try {
      System.out.println("prepare job is finished, signal other thread");
      condition.signal();
    } finally {
      lock.unlock();
    }
  }

  public static void main(String[] args) {
    ConditionDemo1 conditionDemo1 = new ConditionDemo1(); // 1

    new Thread(() -> {
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      conditionDemo1.method2(); // 2
    }).start();

    conditionDemo1.method1(); // 3
  }
}
