package com.jiang.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ArrayBlockingQueueDemo {


  public static void main(String[] args) {
    ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
    Interviewer r1 = new Interviewer(queue);
    Consumer r2 = new Consumer(queue);
    new Thread(r1).start();
    new Thread(r2).start();
  }
}

class Interviewer implements Runnable {
  private BlockingQueue<String> queue;

  public Interviewer(BlockingQueue<String> queue) {
    this.queue = queue;
  }

  @Override
  public void run() {
    System.out.println("10 candidate are here");
    for (int i = 0; i < 10; i++) {
      String candidate = "Candidate" + i;
      try {
        queue.put(candidate);
        System.out.println(candidate + " is ready");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    try {
      queue.put("stop");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

class Consumer implements Runnable {
  private BlockingQueue<String> queue;

  public Consumer(BlockingQueue<String> queue) {
    this.queue = queue;
  }

  @Override
  public void run() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    String msg;
    try {
      while (!(msg = queue.take()).equals("stop")) {
        System.out.println(msg + " is interviewing");
      }
      System.out.println("interview of all candidate is finished");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println();
  }
}