package com.jiang.threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * demonstrate thread sleep would not release synchronized's monitor
 * end of sleep then release
 */
public class SleepDontReleaseMonitor implements Runnable {

  public static void main(String[] args) {
    SleepDontReleaseMonitor target = new SleepDontReleaseMonitor();
    new Thread(target).start();
    new Thread(target).start();
  }

  @Override
  public void run() {
    syn();
  }

  private synchronized void syn() {
    System.out.println(Thread.currentThread().getName() + " get monitor");
    try {
      Thread.sleep(5000); // sleep do not release lock
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(Thread.currentThread().getName() + " exit synchronized block");
  }
}
