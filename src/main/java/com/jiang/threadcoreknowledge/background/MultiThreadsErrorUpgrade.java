package com.jiang.threadcoreknowledge.background;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * demonstrate count is inaccuracy, find the problem point
 */
public class MultiThreadsErrorUpgrade implements Runnable {
  static MultiThreadsErrorUpgrade instance = new MultiThreadsErrorUpgrade();
  private int index = 0;
  static AtomicInteger realIndex = new AtomicInteger();
  static AtomicInteger wrongCount = new AtomicInteger();
  static volatile CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2); // 2 meant is how many thread you wait
  static volatile CyclicBarrier cyclicBarrier2 = new CyclicBarrier(2);
  final boolean[] marked = new boolean[10000000];

  public static void main(String[] args) throws InterruptedException {
    Thread t1 = new Thread(instance);
    Thread t2 = new Thread(instance);
    t1.start();
    t2.start();
    t1.join(); // main thread wait t1
    t2.join(); // main thread wait t2
    System.out.println("outer: " + instance.index);
    System.out.println("realIndex: " + realIndex.get());
    System.out.println("wrongCount: " + wrongCount.get());
  }

  @Override
  public void run() {
    for (int i = 0; i < 10000; i++) {
      try {
        cyclicBarrier2.reset();
        cyclicBarrier1.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (BrokenBarrierException e) {
        e.printStackTrace();
      }
      index++;
      try {
        cyclicBarrier1.reset();
        cyclicBarrier2.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (BrokenBarrierException e) {
        e.printStackTrace();
      }
      realIndex.incrementAndGet();
      synchronized (instance) { // visible
        if (marked[index] && marked[index - 1]) {
          System.out.println("cause error: " + index);
          wrongCount.incrementAndGet();
        }
        marked[index] = true;
      }
    }
  }
}
