package com.jiang.threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * two thread notify wait in turns
 */
public class WaitNotifyPrintOddEvenWait {
  // 1. get lock then print
  // 2. print over, then notify other, wait self
  private static int count;
  private final static Object lock = new Object();

  static class TurningRunner implements Runnable {

    @Override
    public void run() {
      while (count <= 100) {
        synchronized (lock) {
          System.out.println(Thread.currentThread().getName() + ": " + count++);
          lock.notify();
          if (count <= 100) {
            try {
              lock.wait(); // release
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        }
      }
    }
  }

  public static void main(String[] args) {
    new Thread(new TurningRunner(), "even").start();
    new Thread(new TurningRunner(), "odd").start();
  }
}
