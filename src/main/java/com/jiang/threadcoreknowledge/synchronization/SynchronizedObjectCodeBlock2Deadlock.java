package com.jiang.threadcoreknowledge.synchronization;

/**
 * Object, coding block
 */
public class SynchronizedObjectCodeBlock2Deadlock implements Runnable {
  private static SynchronizedObjectCodeBlock2Deadlock instance1 = new SynchronizedObjectCodeBlock2Deadlock();
  private static SynchronizedObjectCodeBlock2Deadlock instance2 = new SynchronizedObjectCodeBlock2Deadlock();

  public static void main(String[] args) {
    Thread thread1 = new Thread(instance1);
    Thread thread2 = new Thread(instance1);
    thread1.start();
    thread2.start();
    while (thread1.isAlive() || thread2.isAlive()) {

    }
    System.out.println("finish");
  }

  @Override
  public void run() {
    synchronized (instance1) {
      System.out.println("I am coding block 1 of object lock" + Thread.currentThread().getName());
      synchronized (instance2) {
        try {
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      
      System.out.println(Thread.currentThread().getName() + " lock 1 executed finish");
    }

    synchronized (instance2){
      synchronized (instance1) {
      }
    }
  }
}
