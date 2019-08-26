package com.jiang.threadcoreknowledge.stopthreads;

/**
 * there is sleep() in run method
 */
public class RightWayStopThreadWithSleep {
  public static void main(String[] args) throws InterruptedException {
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        int num = 0;
        try {
          while (num <= 300 && !Thread.currentThread().isInterrupted()) {
            if (num % 100 == 0) {
              System.out.println(num + " is 100's multiple");
            }
            num++;
          }
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          // if threw sleep interrupt exception, handle in here
          e.printStackTrace();
        }
      }
    };

    Thread thread = new Thread(runnable);
    thread.start();
    Thread.sleep(500);
    thread.interrupt();// if thread is sleep, will threw "java.lang.InterruptedException: sleep interrupted"
  }


}
