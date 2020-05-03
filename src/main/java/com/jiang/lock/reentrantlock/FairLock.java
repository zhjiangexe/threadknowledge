package com.jiang.lock.reentrantlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairLock {
  public static void main(String[] args) {
    PrintQueue printQueue = new PrintQueue();
    Thread thread[] = new Thread[10];
    for (int i = 0; i < 10; i++) {
      thread[i] = new Thread(new Job(printQueue));
    }
    for (int i = 0; i < 10; i++) {
      thread[i].start();
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

}

class Job implements Runnable {
  PrintQueue printQueue;

  public Job(PrintQueue printQueue) {
    this.printQueue = printQueue;
  }

  @Override
  public void run() {
    System.out.println(Thread.currentThread().getName() + " start to print");
    printQueue.printJob(new Object());
    System.out.println(Thread.currentThread().getName() + " end to print");
  }
}

class PrintQueue {
  private Lock queueLock = new ReentrantLock(false);

  public void printJob(Object document) {
    queueLock.lock();
    try {
      int duration = new Random().nextInt(10) + 1;
      System.out.println(Thread.currentThread().getName() + " is printing, need " + duration);
      Thread.sleep(duration * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      queueLock.unlock();
    }

    queueLock.lock();
    try {
      int duration = new Random().nextInt(10) + 1;
      System.out.println(Thread.currentThread().getName() + ". it is printning, need " + duration);
      Thread.sleep(duration * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      queueLock.unlock();
    }
  }
}