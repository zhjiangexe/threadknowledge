package com.jiang.threadcoreknowledge.threadobjectclasscommonmethods;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * every second output current time, interrupt, watch
 * Thread.sleep()
 * TimeUnit.SECONDS.sleep()
 */
public class SleepInterrupted implements Runnable {
  public static void main(String[] args) throws InterruptedException {
    Thread thread = new Thread(new SleepInterrupted());
    thread.start();
    Thread.sleep(6500);
    thread.interrupt();
  }

  @Override
  public void run() {
    for (int i = 0; i < 10; i++) {
      System.out.println(new Date());
      try {
        TimeUnit.SECONDS.sleep(3); // lock key release
      } catch (InterruptedException e) {
        System.out.println(Thread.currentThread().getName() + " was interrupted");
        e.printStackTrace();
      }
    }
  }
}
