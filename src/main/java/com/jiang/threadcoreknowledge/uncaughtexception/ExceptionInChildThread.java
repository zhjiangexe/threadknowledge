package com.jiang.threadcoreknowledge.uncaughtexception;

/**
 * single thread, throw, handle,
 * multi thread cause Exception, what different
 */
public class ExceptionInChildThread implements Runnable {
  public static void main(String[] args) {
    new Thread(new ExceptionInChildThread()).start();
    for (int i = 0; i < 1000; i++) {
      System.out.println(i);
    }
  }

  @Override
  public void run() {
    throw new RuntimeException();
  }
}
