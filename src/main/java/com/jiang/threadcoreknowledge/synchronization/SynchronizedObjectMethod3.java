package com.jiang.threadcoreknowledge.synchronization;

public class SynchronizedObjectMethod3 implements Runnable {
  static SynchronizedObjectMethod3 instance = new SynchronizedObjectMethod3();

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
    method();
  }

  private synchronized void method() {
    System.out.println("I'm synchronized method " + Thread.currentThread().getName());
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(Thread.currentThread().getName() + " execute finish");
  }
}
