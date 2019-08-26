package com.jiang.threadcoreknowledge.uncaughtexception;

/**
 * 1. do not add try catch, would throw 4 exception, all with thread name
 * 2. add try catch, expect catch first, then stop 2,3,4 thread, print caught Exception
 * 3. execute found, no caught exception, 2,3,4 thread keep going, cannot control
 * <p>
 * indicate thread can not use
 */
public class CantCatchDirectly implements Runnable {

  public static void main(String[] args) throws InterruptedException {
    try {
      new Thread(new CantCatchDirectly(), "MyThread-1").start();
      Thread.sleep(300);
      new Thread(new CantCatchDirectly(), "MyThread-2").start();
      Thread.sleep(300);
      new Thread(new CantCatchDirectly(), "MyThread-3").start();
      Thread.sleep(300);
      new Thread(new CantCatchDirectly(), "MyThread-4").start();
    } catch (RuntimeException e) {
      System.out.println("Caught Exception.");
    }
  }

  @Override
  public void run() {
    throw new RuntimeException();
  }
}
