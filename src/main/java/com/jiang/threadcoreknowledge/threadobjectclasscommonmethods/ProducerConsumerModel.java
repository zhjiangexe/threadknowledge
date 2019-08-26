package com.jiang.threadcoreknowledge.threadobjectclasscommonmethods;

import java.util.Date;
import java.util.LinkedList;

/**
 * implement producer consumer pattern by using wait/notify
 */
public class ProducerConsumerModel {

  public static void main(String[] args) {
    EventStorage storage = new EventStorage();
    Producer producer = new Producer(storage);
    Consumer consumer = new Consumer(storage);
    new Thread(producer).start();
    new Thread(consumer).start();
  }
}

class Producer implements Runnable {
  private EventStorage storage;

  public Producer(EventStorage storage) {
    this.storage = storage;
  }

  @Override
  public void run() {
    for (int i = 0; i < 100; i++) {
      storage.put();
    }
  }
}

class Consumer implements Runnable {
  private EventStorage storage;

  public Consumer(EventStorage storage) {
    this.storage = storage;
  }

  @Override
  public void run() {
    for (int i = 0; i < 100; i++) {
      storage.take();
    }
  }
}

class EventStorage {
  private int maxSize;
  private LinkedList<Date> storage;

  public EventStorage() {
    maxSize = 10;
    storage = new LinkedList<>();
  }

  public synchronized void put() {
    while (storage.size() == maxSize) {
      try {
        wait(); // return key
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    storage.add(new Date());
    System.out.println("storage has " + storage.size() + " item.");
    notify(); // get key
  }

  public synchronized void take() {
    while (storage.size() == 0) {
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.out.println("get " + storage.poll() + ", remain " + storage.size());
    notify();
  }
}
