package com.jiang.threadcoreknowledge.stopthreads;

/**
 * invoke Thread.currentThread().interrupt() restore interrupt in catch statement,
 * then you know the interrupt
 */
public class RightWayStopThreadInProd2 implements Runnable {
  @Override
  public void run() {
    while (true) {
      if (Thread.currentThread().isInterrupted()) {
        System.out.println("interrupted, process end");
        break;
      }
      reInterrupt();
    }
  }

  private void reInterrupt() {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt(); // restore interrupt
      e.printStackTrace();
    }
  }

  public static void main(String[] args) throws InterruptedException {
    Thread thread = new Thread(new RightWayStopThreadInProd2());
    thread.start();
    Thread.sleep(1000);
    thread.interrupt();
  }
}
