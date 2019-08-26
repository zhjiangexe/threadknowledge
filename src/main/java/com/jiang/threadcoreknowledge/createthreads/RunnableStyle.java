package com.jiang.threadcoreknowledge.createthreads;

/**
 * use runnable create thread
 */
public class RunnableStyle implements Runnable {
  public static void main(String[] args) {
    Thread thread = new Thread(new RunnableStyle()); // inject runnable
    thread.start();
  }

  @Override
  public void run() {
    System.out.println("use runnable implement thread");
  }
}
