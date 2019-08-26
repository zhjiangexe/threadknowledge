package com.jiang.threadcoreknowledge.stopthreads;

/**
 * always sleep() or wait() in loop
 */
public class RightWayStopWithSlepEveryLoop {
  public static void main(String[] args) throws InterruptedException {
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        int num = 0;
        try {
          while (
//              !Thread.currentThread().isInterrupted() &&
              num <= 10000) {
            if (num % 100 == 0) {
              System.out.println(num + " is 100's multiple");
            }
            num++;
            Thread.sleep(10); // if there is sleep() in every loop, don not need isInterrupted check, sleep that can react it threw exception
          }
        } catch (InterruptedException e) {
          // if threw sleep interrupt exception, handle in here
          e.printStackTrace();
        }
      }
    };

    Thread thread = new Thread(runnable);
    thread.start();
    Thread.sleep(5000);
    thread.interrupt();// if thread is sleep, will threw "java.lang.InterruptedException: sleep interrupted"
  }
}
