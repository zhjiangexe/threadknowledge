package com.jiang.threadcoreknowledge.createthreads;

/**
 * runnable and thread
 */
public class BothRunnableThread {
  public static void main(String[] args) {
    // thread has a attribute: Runnable, original Thread class run method will execute runnable's run method
    // second run "from thread" will override the Thread run method
    new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println("from runnable");
      }
    }) {
      @Override
      public void run() {
        System.out.println("from thread");
      }
    }.start();
  }
}
