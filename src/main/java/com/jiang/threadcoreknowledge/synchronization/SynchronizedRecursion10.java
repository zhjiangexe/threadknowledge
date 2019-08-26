package com.jiang.threadcoreknowledge.synchronization;

/**
 * reentrant same method
 */
public class SynchronizedRecursion10 {
  int a = 0;

  public static void main(String[] args) {
    SynchronizedRecursion10 recursion10 = new SynchronizedRecursion10();
    recursion10.method1();
  }

  private synchronized void method1() {
    System.out.println("method, a= " + a);
    if (a == 0) {
      a++;
      method1();
    }
  }
}
