package com.jiang.threadcoreknowledge.stopthreads;

/**
 * after catch interruptedException, should threw exception at method signature
 * run() is force try/catch
 */
public class RightWayStopThreadInProd implements Runnable {

  @Override
  public void run() {
    while (true) {
      System.out.println("go");
      try {
        throwInMethod();
      } catch (InterruptedException e) {
        System.out.println("log or stop process");
        e.printStackTrace();
      }
    }
  }

  private void throwInMethod() throws InterruptedException {
    Thread.sleep(2000);
  }

  public static void main(String[] args) throws InterruptedException {
    Thread thread = new Thread(new RightWayStopThreadInProd());
    thread.start();
    Thread.sleep(1000);
    thread.interrupt();
  }
}
