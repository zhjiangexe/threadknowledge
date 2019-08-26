package com.jiang.threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * prove wait only release current lock
 * 1. one for release
 * 2. one for lock
 */
public class WaitNotifyReleaseOwnMonitor {
  private static volatile Object resA = new Object();
  private static volatile Object resB = new Object();

  public static void main(String[] args) {
    Thread threadA = new Thread(new Runnable() {
      @Override
      public void run() {
        synchronized (resA) {
          System.out.println("ThreadA got resA lock.");
          synchronized (resB) {
            System.out.println("ThreadA got resB lock.");
            try {
              System.out.println("ThreadA release resA lock.");
              resA.wait();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        }
      }
    });

    Thread threadB = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        synchronized (resA) {
          System.out.println("ThreadB got resA lock.");
          System.out.println("ThreadB tries to resB lock.");
          synchronized (resB) {
            System.out.println("ThreadB got resB lock.");
          }
        }
      }
    });
    threadA.start();
    threadB.start();
  }
}
