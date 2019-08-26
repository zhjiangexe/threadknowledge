package com.jiang.threadcoreknowledge.synchronization;

/**
 * reentrant different method
 */
public class SynchronizedOtherMethod11 {
  public synchronized void method1() {
    System.out.println("I'm method1");
    method2();
  }

  private synchronized void method2() {
    System.out.println("I'm method2");
  }

  public static void main(String[] args) {
    SynchronizedOtherMethod11 s = new SynchronizedOtherMethod11();
    s.method1();
  }
}
