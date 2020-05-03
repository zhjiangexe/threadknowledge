package com.jiang.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;

public class LongAdderDemo {
  public static void main(String[] args) throws InterruptedException {
    LongAdder counter = new LongAdder();
    ExecutorService executorService = Executors.newFixedThreadPool(20);
    long start = System.currentTimeMillis();
    for (int i = 0; i < 10000; i++) {
      executorService.submit(new LongAdderDemo.Task(counter));
    }
    executorService.shutdown();
    while (!executorService.isTerminated()) {

    }
    long end = System.currentTimeMillis();
    System.out.println(counter.sum());
    System.out.println("time spending: " + (end - start));
  }

  private static class Task implements Runnable {
    private LongAdder counter;

    public Task(LongAdder counter) {
      this.counter = counter;
    }

    @Override
    public void run() {
      for (int i = 0; i < 10000; i++) {
        counter.increment();
      }
    }
  }
}
