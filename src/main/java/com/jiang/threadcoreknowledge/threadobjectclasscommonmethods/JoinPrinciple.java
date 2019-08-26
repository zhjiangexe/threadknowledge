package com.jiang.threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * join theory, alternative join written
 */
public class JoinPrinciple {
  public static void main(String[] args) throws InterruptedException {
    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " execute finished.");
      }
    });
    thread.start();
    System.out.println("wait child thread execute finish");
//    thread.join(); // lower layer is wait()
    synchronized (thread) { // same with join()
      thread.wait();
    }
    System.out.println("all child thread execute finish");
  }
}
