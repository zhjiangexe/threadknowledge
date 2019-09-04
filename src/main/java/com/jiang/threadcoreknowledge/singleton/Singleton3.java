package com.jiang.threadcoreknowledge.singleton;

/**
 * lazy mode
 * thread non-safe
 */
public class Singleton3 {
  private static Singleton3 instance;

  private Singleton3() {

  }

  public static Singleton3 getInstance() {
    if (instance == null) {
      instance = new Singleton3(); // maybe create two times
    }
    return instance;
  }
}
