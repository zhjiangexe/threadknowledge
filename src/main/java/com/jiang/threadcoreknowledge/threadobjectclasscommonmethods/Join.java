package com.jiang.threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * demonstrate join, notice
 */
public class Join {
  public static void main(String[] args) throws InterruptedException {
    Thread t1 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " execute finished");
      }
    });

    Thread t2 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " execute finished");
      }
    });

    t1.start();
    t2.start();
    System.out.println("start wait child thread execute to finish");
    t1.join();
    t2.join();
    System.out.println("because t1 and t2 use join, main thread will wait all child thread execute finished");
  }
}
