package com.jiang.threadcoreknowledge.stopthreads;

/**
 * no sleep() or wait() in run method, stop sleep
 */
public class RightWayStopThreadWithoutSleep implements Runnable {

  @Override
  public void run() {
    int num = 0;
    while (
        !Thread.currentThread().isInterrupted() &&
            num <= Integer.MAX_VALUE / 2) {
      if (num % 10000 == 0) {
        System.out.println(num + " is 10000's multiple");
      }
      num++;
    }
    System.out.println("execute task is finish");
  }

  public static void main(String[] args) throws InterruptedException {
    Thread thread = new Thread(new RightWayStopThreadWithoutSleep());
    thread.start();
    Thread.sleep(1000);
    thread.interrupt(); // can not interrupt
  }
}
