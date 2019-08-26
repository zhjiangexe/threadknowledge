package com.jiang.threadcoreknowledge.stopthreads.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * use interrupt() fix unlimited wait
 */
public class WrongWayVolatileFixed {
  public static void main(String[] args) throws InterruptedException {
    // inner class isn't static, outer class should instance first
    WrongWayVolatileFixed body = new WrongWayVolatileFixed();
    // put at full and get at empty will block
    ArrayBlockingQueue storage = new ArrayBlockingQueue(10);
    Producer producer = body.new Producer(storage);
    Thread producerThread = new Thread(producer);
    producerThread.start();
    Thread.sleep(1000); // put until full
    Consumer consumer = body.new Consumer(storage);
    while (consumer.needMoreNums()) {
      System.out.println(consumer.storage.take() + " is consumed");
      Thread.sleep(100);
    }
    System.out.println("consumer need not more data");
    producerThread.interrupt();
  }

  class Producer implements Runnable {
    BlockingQueue<Integer> storage;

    public Producer(BlockingQueue storage) {
      this.storage = storage;
    }

    @Override
    public void run() {
      int num = 0;
      try {
        while (num <= 10000 && !Thread.currentThread().isInterrupted()) {
          if (num % 100 == 0) {
            storage.put(num); // full will be blocked
            System.out.println(num + " is 100's multiple, put into storage");
          }
          num++;
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        System.out.println("producer stopped");
      }
    }
  }

  class Consumer {
    BlockingQueue storage;

    public Consumer(BlockingQueue storage) {
      this.storage = storage;
    }

    public boolean needMoreNums() {
      if (Math.random() > 0.95) {
        return false;
      }
      return true;
    }
  }
}