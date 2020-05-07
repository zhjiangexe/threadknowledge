package com.jiang.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * implementing a simple latch by extend AQS
 */
public class OneShotLatch {
  private final Sync sync = new Sync();

  public void signal() {
    sync.releaseShared(0);
  }

  public void await() {
    sync.acquireShared(0);
  }

  private class Sync extends AbstractQueuedSynchronizer {
    @Override
    protected int tryAcquireShared(int arg) {
      return (getState() == 1) ? 1 : -1;
    }

    @Override
    protected boolean tryReleaseShared(int arg) {
      setState(1);
      return true;
    }
  }

  public static void main(String[] args) throws InterruptedException {
    OneShotLatch oneShotLatch = new OneShotLatch();
    for (int i = 0; i < 10; i++) {
      new Thread(() -> {
        System.out.println(Thread.currentThread().getName() + " try to get latch, if fail then wait");
        oneShotLatch.await();
        System.out.println(Thread.currentThread().getName() + " start process");
      }).start();
    }
    Thread.sleep(3000);
    oneShotLatch.signal();

    new Thread(() -> {
      System.out.println(Thread.currentThread().getName() + " try to get latch, if fail then wait");
      oneShotLatch.await();
      System.out.println(Thread.currentThread().getName() + " start process");
    }).start();
  }
}
