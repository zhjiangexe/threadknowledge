package com.jiang.threadcoreknowledge.sixstates;

/**
 * demonstrate blocked, waiting, timed_waiting
 */
public class BlockedWaitingTimedWaiting implements Runnable {
  public static void main(String[] args) throws InterruptedException {
    BlockedWaitingTimedWaiting runnable = new BlockedWaitingTimedWaiting();
    Thread thread1 = new Thread(runnable);
    thread1.start();
    Thread thread2 = new Thread(runnable);
    thread2.start();
    Thread.sleep(200);
    System.out.println("thread1 " + thread1.getState()); // TIMED_WAITING, because timed_waiting
    System.out.println("thread2 " + thread2.getState()); // BLOCK, thread2 can not get lock key, because sync()
    Thread.sleep(1200);
    System.out.println(thread1.getState());
  }

  @Override
  public void run() {
    syn();
  }

  private synchronized void syn() {
    try {
      Thread.sleep(1000);
      wait();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
