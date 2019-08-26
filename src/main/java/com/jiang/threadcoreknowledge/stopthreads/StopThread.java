package com.jiang.threadcoreknowledge.stopthreads;

/**
 * stop() let thread suddenly stop, can not finish unit, cause dirty data
 */
public class StopThread implements Runnable {

  public static void main(String[] args) {
    Thread thread = new Thread(new StopThread());
    thread.start();
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    thread.stop(); // stop is unsecurity
    // suspend would cause deadlock

  }

  @Override
  public void run() {
    // simulate squard
    for (int i = 0; i < 5; i++) {
      System.out.println("squard " + i + " draw weapon start");
      for (int i1 = 0; i1 < 12; i1++) {
        System.out.println(i1);
        try {
          Thread.sleep(50);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      System.out.println("squard " + i + " draw weapon end");
    }
  }
}
