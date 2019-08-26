package com.jiang.threadcoreknowledge.synchronization;

public class DisappearRequest1 implements Runnable {
  static DisappearRequest1 instance = new DisappearRequest1();
  static int i = 0;

  public static void main(String[] args) throws InterruptedException {
    // these thread share instance
    Thread thread1 = new Thread(instance);
    Thread thread2 = new Thread(instance);
    thread1.start();
    thread2.start();
    thread1.join(); // ordering execution
//    thread2.join(); // ordering execution
    System.out.println(i);
  }

  @Override
  public void run() {
    synchronized (instance) {
      for (int j = 0; j < 100000; j++) {
        i++;
//        System.out.println(i);
      }
    }
  }
}
