package com.jiang.threadcoreknowledge.synchronization;

public class SynchronizedClassClass5 implements Runnable {
  static SynchronizedClassClass5 instance1 = new SynchronizedClassClass5();
  static SynchronizedClassClass5 instance2 = new SynchronizedClassClass5();
  @Override
  public void run() {
    try {
      method();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void method() throws InterruptedException {
    synchronized (SynchronizedClassClass5.class) {
      System.out.println("I'm class lock second form: synchronized(*.class). I'm " + Thread.currentThread().getName());
      Thread.sleep(3000);
      System.out.println(Thread.currentThread().getName() + "execute finish");
    }
  }

  public static void main(String[] args) {
    Thread thread1 = new Thread(instance1);
    Thread thread2 = new Thread(instance2);
    thread1.start();
    thread2.start();
    while (thread1.isAlive() || thread2.isAlive()) {
    }
    System.out.println("finish");
  }
}
