package com.jiang.collections.predecessor;

import java.util.HashMap;

public class HashMapEndlessLoop {
  private static HashMap<Integer, String> map = new HashMap<>(2, 1.5f);

  public static void main(String[] args) {
    map.put(5, "C");
    map.put(7, "B");
    map.put(3, "A");
    new Thread(new Runnable() {
      @Override
      public void run() {
        map.put(15, "D");
      }
    }, "Thread 1").start();
    new Thread(new Runnable() {
      @Override
      public void run() {
        map.put(1, "E");
      }
    }, "Thread 2").start();
  }
}
