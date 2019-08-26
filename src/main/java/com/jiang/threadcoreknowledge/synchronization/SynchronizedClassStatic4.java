package com.jiang.threadcoreknowledge.synchronization;

/**
 * class lock static style
 */
public class SynchronizedClassStatic4 implements Runnable {
  static SynchronizedClassStatic4 instance1 = new SynchronizedClassStatic4();
  static SynchronizedClassStatic4 instance2 = new SynchronizedClassStatic4();

  public static void main(String[] args) {
    Thread thread1 = new Thread(instance1);
    Thread thread2 = new Thread(instance2);
    thread1.start();
    thread2.start();
    while (thread1.isAlive() || thread2.isAlive()) {
    }
    System.out.println("finish");
  }

  @Override
  public void run() {
    method();
  }

  public static synchronized void method() {
    System.out.println("I'm synchronized method, class lock style 1 " + Thread.currentThread().getName());
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(Thread.currentThread().getName() + " execute finish");
  }
}
