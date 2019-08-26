package com.jiang.threadcoreknowledge.createthreads.startthread;

/**
 * compare method between start and run
 */
public class StartAndRunMethod {
  public static void main(String[] args) {
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        System.out.println(Thread.currentThread().getName());
      }
    };
    runnable.run(); // get name => main, no sub thread create

    new Thread(runnable).start();
  }
}
