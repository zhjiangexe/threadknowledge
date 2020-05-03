package com.jiang.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicArrayDemo {
  public static void main(String[] args) throws InterruptedException {
    AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(1000);
    Decremeter decremeter = new Decremeter(atomicIntegerArray);
    Incrementer incrementer = new Incrementer(atomicIntegerArray);
    Thread[] threadsIncrementer = new Thread[100];
    Thread[] threadsDecrementer = new Thread[100];
    for (int i = 0; i < 100; i++) {
      threadsDecrementer[i] = new Thread(decremeter);
      threadsIncrementer[i] = new Thread(incrementer);
      threadsDecrementer[i].start();
      threadsIncrementer[i].start();
    }
    for (int i = 0; i < 100; i++) {
      try {
        threadsDecrementer[i].join();
        threadsIncrementer[i].join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    for (int i = 0; i < atomicIntegerArray.length(); i++) {
      if (atomicIntegerArray.get(i) != 0) {
        System.out.println("something wrong");
      }
      System.out.println(atomicIntegerArray.get(i));
    }
    System.out.println("executing finish");
//    Thread.sleep(10000);
  }
}

class Decremeter implements Runnable {
  private AtomicIntegerArray array;

  public Decremeter(AtomicIntegerArray array) {
    this.array = array;
  }

  @Override
  public void run() {
    for (int i = 0; i < array.length(); i++) {
      array.getAndDecrement(i);
    }
  }
}

class Incrementer implements Runnable {
  private AtomicIntegerArray array;

  public Incrementer(AtomicIntegerArray array) {
    this.array = array;
  }

  @Override
  public void run() {
    for (int i = 0; i < array.length(); i++) {
      array.getAndIncrement(i);
    }
  }
}