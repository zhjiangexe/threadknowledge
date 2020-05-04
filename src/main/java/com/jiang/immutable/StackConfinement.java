package com.jiang.immutable;

public class StackConfinement implements Runnable {
  int index = 0;

  public void inThread() {
    int neverGoOut = 0;
    for (int i = 0; i < 10000; i++) {
      neverGoOut++;
    }
    System.out.println("the number is protected by stack, it means that is thread-safe " + neverGoOut);
  }

  @Override
  public void run() {
    for (int i = 0; i < 10000; i++) {
      index++;
    }
    inThread();
  }

  public static void main(String[] args) throws InterruptedException {
    StackConfinement r1 = new StackConfinement();
    Thread t1 = new Thread(r1);
    Thread t2 = new Thread(r1);
    t1.start();
    t2.start();
    t1.join();
    t1.join();
    System.out.println(r1.index);
  }
}
