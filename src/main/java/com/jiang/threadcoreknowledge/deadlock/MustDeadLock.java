package com.jiang.threadcoreknowledge.deadlock;

/**
 *
 */
public class MustDeadLock implements Runnable {
  private int flag = 1;
  static private final Object o1 = new Object();
  static private final Object o2 = new Object();

  public static void main(String[] args) {
    MustDeadLock r1 = new MustDeadLock();
    MustDeadLock r2 = new MustDeadLock();
    r1.flag = 1;
    r2.flag = 0;
    Thread t1 = new Thread(r1);
    Thread t2 = new Thread(r2);
    t1.start();
    t2.start();
  }

  @Override
  public void run() {
    System.out.println("flag = " + flag);
    if (flag == 1) {
      synchronized (o1) {
        try {
          Thread.sleep(500);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        synchronized (o2) {
          System.out.println("thread1 get two lock successfully");
        }
      }
    }
    if (flag == 0) {
      synchronized (o2) {
        try {
          Thread.sleep(500);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        synchronized (o1) {
          System.out.println("thread2 get two lock successfully");
        }
      }
    }
  }
}
