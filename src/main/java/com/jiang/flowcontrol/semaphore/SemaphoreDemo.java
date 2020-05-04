package com.jiang.flowcontrol.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
  static Semaphore semaphore = new Semaphore(3, true);

  public static void main(String[] args) {
    ExecutorService service = Executors.newFixedThreadPool(50);
    for (int i = 0; i < 100; i++) {
      service.submit(new Task());
    }
    service.shutdown();
  }

  static class Task implements Runnable {
    @Override
    public void run() {
      try {
        semaphore.acquire(3);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(Thread.currentThread().getName() + " get permit");
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(Thread.currentThread().getName() + " release permit");
      semaphore.release(3);
    }
  }
}
