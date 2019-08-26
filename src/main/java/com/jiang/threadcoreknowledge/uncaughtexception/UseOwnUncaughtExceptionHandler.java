package com.jiang.threadcoreknowledge.uncaughtexception;

/**
 * use MyUncaughtExceptionHandler
 */
public class UseOwnUncaughtExceptionHandler implements Runnable {

  public static void main(String[] args) throws InterruptedException {
    Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler("caught"));
    new Thread(new CantCatchDirectly(), "MyThread-1").start();
    Thread.sleep(300);
    new Thread(new CantCatchDirectly(), "MyThread-2").start();
    Thread.sleep(300);
    new Thread(new CantCatchDirectly(), "MyThread-3").start();
    Thread.sleep(300);
    new Thread(new CantCatchDirectly(), "MyThread-4").start();
  }

  @Override
  public void run() {
    throw new RuntimeException();
  }

}
