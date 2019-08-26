package com.jiang.threadcoreknowledge.threadobjectclasscommonmethods;

/**
 *
 */
public class JoinInterrupt {
  public static void main(String[] args) {
    Thread mainThread = Thread.currentThread();
    Thread t1 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          mainThread.interrupt();
          Thread.sleep(5000);
          System.out.println("thread 1 finished.");
        } catch (InterruptedException e) {
//          e.printStackTrace();
          System.out.println("child thread interrupt");
        }
      }
    });
    t1.start();
    System.out.println("wait child thread execute finish");
    try {
      t1.join(); // join means add to main thread
    } catch (InterruptedException e) {
      System.out.println(Thread.currentThread().getName() + " thread interrupted.");
//      e.printStackTrace();
      t1.interrupt();
    }
    System.out.println("child thread execute finish");
  }
}
