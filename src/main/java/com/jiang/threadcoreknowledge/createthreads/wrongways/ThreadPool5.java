package com.jiang.threadcoreknowledge.createthreads.wrongways;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 */
public class ThreadPool5 {
  public static void main(String[] args) {
    // also create thread
    ExecutorService executorService = Executors.newCachedThreadPool();
    for (int i = 0; i < 1000; i++) {
      // add task into thread pool
      executorService.submit(new Task(){});
    }
  }
}

class Task implements Runnable {

  @Override
  public void run() {
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(Thread.currentThread().getName());
  }
}