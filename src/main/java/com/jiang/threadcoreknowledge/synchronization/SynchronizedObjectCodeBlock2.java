package com.jiang.threadcoreknowledge.synchronization;

/**
 * Object, coding block
 */
public class SynchronizedObjectCodeBlock2 implements Runnable {
  private static SynchronizedObjectCodeBlock2 instance = new SynchronizedObjectCodeBlock2();

  public static void main(String[] args) {
    Thread thread1 = new Thread(instance);
    Thread thread2 = new Thread(instance);
    thread1.start();
    thread2.start();
    while (thread1.isAlive() || thread2.isAlive()) {

    }
    System.out.println("finish");
  }

  @Override
  public void run() {
    synchronized (this) {
      System.out.println("I am coding block 1 of object lock" + Thread.currentThread().getName());
      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(Thread.currentThread().getName() + " lock 1 executed finish");
    }
  }
}
