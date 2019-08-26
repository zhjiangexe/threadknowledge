package com.jiang.threadcoreknowledge.stopthreads.volatiledemo;

/**
 * demonstrate limitation of volatile: seems to work
 */
public class WrongWayVolatile implements Runnable {
  /** different thread can see volatile */
  private volatile boolean canceled = false;

  @Override
  public void run() {
    int num = 0;
    try {
      while (num <= 10000 && !canceled) {
        if (num % 100 == 0) {
          System.out.println(num + " is 100's multiple");
        }
        num++;
        Thread.sleep(1);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) throws InterruptedException {
    WrongWayVolatile target = new WrongWayVolatile();
    Thread thread = new Thread(target);
    thread.start();
    Thread.sleep(5000);
    target.canceled = true;
  }
}
