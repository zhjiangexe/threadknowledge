package com.jiang.threadcoreknowledge.background;

/**
 * thread-safe problem deadLock
 */
public class MultiThreadError {
  static Object o1 = new Object();
  static Object o2 = new Object();

  public static void main(String[] args) {
    Thread t1 = new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println(Thread.currentThread().getName());
        synchronized (o1) {
          try {
            Thread.sleep(500);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          synchronized (o2) {
            System.out.println("1");
          }
        }
      }
    });
    Thread t2 = new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println(Thread.currentThread().getName());
        synchronized (o2) {
          try {
            Thread.sleep(500);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          synchronized (o1) {
            System.out.println("0");
          }
        }
      }
    });
    t1.start();
    t2.start();
  }

}
