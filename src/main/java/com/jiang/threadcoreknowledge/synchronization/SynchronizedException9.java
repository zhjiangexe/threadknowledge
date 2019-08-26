package com.jiang.threadcoreknowledge.synchronization;

/**
 * method throw exception, will release lock
 * when first thread throw exception, second thread will enter sync method immediately
 */
public class SynchronizedException9 implements Runnable {
  static SynchronizedException9 target = new SynchronizedException9();

  public static void main(String[] args) {
    Thread thread1 = new Thread(target);
    Thread thread2 = new Thread(target);
    thread1.start();
    thread2.start();
    while (thread1.isAlive() || thread2.isAlive()) {

    }
    System.out.println("finished");
  }

  @Override
  public void run() {
    if (Thread.currentThread().getName().equals("Thread-0")) {
      methodOne();
    } else {
      methodTwo();
    }
  }

  public synchronized void methodOne() {
    System.out.println("I'm static lock method. I'm " + Thread.currentThread().getName());
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    throw new RuntimeException();
//    System.out.println(Thread.currentThread().getName() + " execute finish");
  }

  public synchronized void methodTwo() {
    System.out.println("I'm non-static lock method. I'm " + Thread.currentThread().getName());
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(Thread.currentThread().getName() + " execute finish");
  }
}
