package com.jiang.flowcontrol.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
  public static void main(String[] args) {
    CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> {
      System.out.println("Everyone is here, Let's Go");
    });
    for (int i = 0; i < 10; i++) {
      new Thread(new Task(i, cyclicBarrier)).start();
    }
  }

  static class Task implements Runnable {
    private int id;
    private CyclicBarrier cyclicBarrier;

    public Task(int id, CyclicBarrier cyclicBarrier) {
      this.id = id;
      this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
      System.out.println("Thread " + id + " go to the assemble place");
      try {
        Thread.sleep((long) (Math.random() * 10000));
        System.out.println("Thread " + id + " got the point, wait for others");
        cyclicBarrier.await();
        System.out.println("Thread " + id + " Go GO");
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (BrokenBarrierException e) {
        e.printStackTrace();
      }
    }
  }
}
