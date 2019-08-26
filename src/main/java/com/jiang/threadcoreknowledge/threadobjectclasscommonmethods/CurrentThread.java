package com.jiang.threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * demonstrate main, thread-0, thread-1
 */
public class CurrentThread implements Runnable {
  public static void main(String[] args) {
    new CurrentThread().run(); // main
    new Thread(new CurrentThread()).start(); // thread-0
    new Thread(new CurrentThread()).start(); // thread-1
  }

  @Override
  public void run() {
    System.out.println(Thread.currentThread().getName());
  }
}
