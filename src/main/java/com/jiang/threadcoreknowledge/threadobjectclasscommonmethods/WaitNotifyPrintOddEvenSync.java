package com.jiang.threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * two thread 0~100 in turn
 */
public class WaitNotifyPrintOddEvenSync {
  private static int count;
  private final static Object lock = new Object();

  // create two thread
  // 1. one for handle odd, one for handle even (bit ope)
  // use synchronized communication
  public static void main(String[] args) {
    new Thread(new Runnable() {
      @Override
      public void run() {
        while (count < 100) {
          synchronized (lock) { // might be reenter, Lock Contention
//            if (count % 2 == 0) {
            if ((count & 1) == 0) {
              System.out.println(Thread.currentThread().getName() + ": " + count++);
            }
          }
        }
      }
    }, "even thread").start();

    new Thread(new Runnable() {
      @Override
      public void run() {
        while (count < 100) {
          synchronized (lock) { // might be reenter, Lock Contention
//            if (count % 2 == 0) {
            if ((count & 1) == 1) {
              System.out.println(Thread.currentThread().getName() + ": " + count++);
            }
          }
        }
      }
    }, "odd thread").start();
  }
}
