package com.jiang.atomic;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * LongAdder比AtomicLong性能好
 */
public class AtomicLongDemo {
  public static void main(String[] args) throws InterruptedException {
    AtomicLong counter = new AtomicLong(0);
    ExecutorService executorService = Executors.newFixedThreadPool(20);
    long start = System.currentTimeMillis();
    for (int i = 0; i < 10000; i++) {
      executorService.submit(new Task(counter));
    }
    executorService.shutdown();
    while (!executorService.isTerminated()) {

    }
    long end = System.currentTimeMillis();
    System.out.println(counter.get());
    System.out.println("time spending: " + (end - start));
  }

  private static class Task implements Runnable {
    private AtomicLong counter;

    public Task(AtomicLong counter) {
      this.counter = counter;

    }

    @Override
    public void run() {
      for (int i = 0; i < 10000; i++) {
        counter.incrementAndGet();
      }
    }
  }
}
