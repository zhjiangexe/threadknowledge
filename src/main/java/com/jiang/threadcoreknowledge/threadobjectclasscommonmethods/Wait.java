package com.jiang.threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * demonstrate wait and notify basic usage
 * 1. execute order
 * 2. prove wait release lock
 */
public class Wait {
  public static Object object = new Object();

  public static void main(String[] args) throws InterruptedException {
    Thread1 thread1 = new Thread1();
    Thread2 thread2 = new Thread2();
    thread1.start();
    Thread.sleep(2000);
    thread2.start();
  }
  static class Thread1 extends Thread {
    @Override
    public void run() {
      synchronized (object) {
        System.out.println(Thread.currentThread().getName() + " execute");
        try {
          object.wait(); // wait() release lock, let other synchronized block work
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("Thread " + Thread.currentThread().getName());
      }
    }
  }

  static class Thread2 extends Thread {
    @Override
    public void run() {
      synchronized (object) {
        object.notify(); // should not start thread 0 immediately
        // only leaving synchronized block will start
        System.out.println(Thread.currentThread().getName() + " invoke notify");
      }
    }
  }
}
