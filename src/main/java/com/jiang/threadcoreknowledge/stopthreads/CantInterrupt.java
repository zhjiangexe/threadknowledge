package com.jiang.threadcoreknowledge.stopthreads;

/**
 * there is try catch in loop, would cause interrupt fail
 */
public class CantInterrupt {
  public static void main(String[] args) throws InterruptedException {
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        int num = 0;
        while (
//            !Thread.currentThread().isInterrupted() &&
            num <= 1000
        ) {
          if (num % 100 == 0) {
            System.out.println(num + " is 100's multiple");
          }
          num++;
          try {
            Thread.sleep(10);
          } catch (InterruptedException e) {
            e.printStackTrace(); // do not quit loop, because response does clean this time interrupt
          }
        }
      }
    };
    Thread thread = new Thread(runnable);
    thread.start();
    Thread.sleep(5000);
    thread.interrupt();
  }
}
