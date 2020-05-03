package com.jiang.threadcoreknowledge.deadlock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * deadlock detection
 */
public class ThreadMXBeanDetection implements Runnable {
  private int flag = 1;
  static private final Object o1 = new Object();
  static private final Object o2 = new Object();

  public static void main(String[] args) throws InterruptedException {
    ThreadMXBeanDetection r1 = new ThreadMXBeanDetection();
    ThreadMXBeanDetection r2 = new ThreadMXBeanDetection();
    r1.flag = 1;
    r2.flag = 0;
    Thread t1 = new Thread(r1);
    Thread t2 = new Thread(r2);
    t1.start();
    t2.start();
    Thread.sleep(1000);
    ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
    long[] deadlockedThreads = threadMXBean.findDeadlockedThreads();
    if (deadlockedThreads != null && deadlockedThreads.length > 0) {
      for (int i = 0; i < deadlockedThreads.length; i++) {
        ThreadInfo threadInfo = threadMXBean.getThreadInfo(deadlockedThreads[i]);
        System.out.println("find deadlock" + threadInfo.getThreadName());
      }
    }
  }

  @Override
  public void run() {
    System.out.println("flag = " + flag);
    if (flag == 1) {
      synchronized (o1) {
        try {
          Thread.sleep(500);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        synchronized (o2) {
          System.out.println("thread1 get two lock successfully");
        }
      }
    }
    if (flag == 0) {
      synchronized (o2) {
        try {
          Thread.sleep(500);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        synchronized (o1) {
          System.out.println("thread2 get two lock successfully");
        }
      }
    }
  }
}
