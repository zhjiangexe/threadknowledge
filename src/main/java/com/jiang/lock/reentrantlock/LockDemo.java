package com.jiang.lock.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * interrupt
 */
public class LockDemo {
  public static void main(String[] args) {
    new LockDemo().init();
  }

  private void init() {
    final Outputer outputer = new Outputer();
    new Thread(() -> {
      while (true) {
        try {
          Thread.sleep(5);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        outputer.output("rockman");
      }
    }).start();

    new Thread(() -> {
      while (true) {
        try {
          Thread.sleep(5);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        outputer.output("forte");
      }
    }).start();
  }

  static class Outputer {
    Lock lock = new ReentrantLock();

    public void output(String name) {
      int len = name.length();
      lock.lock();
      try {
        for (int i = 0; i < len; i++) {
          System.out.print(name.charAt(i));
        }
        System.out.println("");
      } finally {
        lock.unlock();
      }
    }
  }
}
