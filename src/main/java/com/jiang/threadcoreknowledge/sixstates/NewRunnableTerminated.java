package com.jiang.threadcoreknowledge.sixstates;

/**
 * demonstrated thread's new runnable terminated status
 * even though it is executing, is still "runnable" status, is not "running" status
 */
public class NewRunnableTerminated implements Runnable {
  public static void main(String[] args) {
    Thread thread = new Thread(new NewRunnableTerminated());
    System.out.println(thread.getState());
    thread.start();
    System.out.println(thread.getState());
    try {
      Thread.sleep(10);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    // output runnable status, is still "runnable" status, is not "running" status
    System.out.println(thread.getState()); // YES, still runnable
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(thread.getState());
  }

  @Override
  public void run() {
    for (int i = 0; i < 1000; i++) {
      System.out.println(i);
    }
  }
}
