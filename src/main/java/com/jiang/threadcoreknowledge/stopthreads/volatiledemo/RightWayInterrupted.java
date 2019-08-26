package com.jiang.threadcoreknowledge.stopthreads.volatiledemo;

/**
 * Note:
 * Thread.interrupted() target object is currentThread,
 * DO NOT care which object owes this method
 */
public class RightWayInterrupted {
  public static void main(String[] args) throws InterruptedException {
    Thread threadOne = new Thread(new Runnable() {
      @Override
      public void run() {
        for (; ; ) {
        }
      }
    });

    threadOne.start();
    threadOne.interrupt();
    // get has interrupted
    System.out.println("isInterrupted: " + threadOne.isInterrupted());
    // get has interrupted, and reset
    System.out.println("isInterrupted: " + threadOne.interrupted()); // main thread isn't interrupt
    // get has interrupted, and reset
    System.out.println("isInterrupted: " + Thread.interrupted()); // main thread clean interrupt
    // get has interrupted
    System.out.println("isInterrupted: " + threadOne.isInterrupted()); // do not clean
    threadOne.join();
    System.out.println("Main thread is over.");
  }
}
