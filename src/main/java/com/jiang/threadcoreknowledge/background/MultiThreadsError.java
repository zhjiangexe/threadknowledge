package com.jiang.threadcoreknowledge.background;

/**
 * demonstrate count is inaccuracy, find the problem point
 */
public class MultiThreadsError implements Runnable {
  static MultiThreadsError instance = new MultiThreadsError();
  private int index = 0;

  public static void main(String[] args) throws InterruptedException {
    Thread t1 = new Thread(instance);
    Thread t2 = new Thread(instance);
    t1.start();
    t2.start();
    t1.join(); // main thread wait t1
    t2.join(); // main thread wait t2
    System.out.println(instance.index);
  }
  @Override
  public void run() {
    for (int i = 0; i < 10000; i++) {
      index++;
    }
  }
}
