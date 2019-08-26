package com.jiang.threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * 3 thread, thread 1 and thread 2 first block, thread 3 notify them
 * execute start() do not represent thread start immediately
 */
public class WaitNotifyAll implements Runnable {
  private static final Object resA = new Object();

  public static void main(String[] args) throws InterruptedException {
    Runnable r = new WaitNotifyAll();
    Thread threadA = new Thread(r);
    Thread threadB = new Thread(r);
    Thread threadC = new Thread(new Runnable() {
      @Override
      public void run() {
        synchronized (resA) {
          resA.notifyAll();
//          resA.notify();
          System.out.println("Thread C notified");
        }
      }
    });
    threadA.start();
    threadB.start();
    Thread.sleep(200);
    threadC.start();
  }

  @Override
  public void run() {
    synchronized (resA) {
      System.out.println(Thread.currentThread().getName() + " got resourceA lock.");
      try {
        System.out.println(Thread.currentThread().getName() + " waits to start.");
        resA.wait();
        System.out.println(Thread.currentThread().getName() + "'s waiting to end.");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
