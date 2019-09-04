package com.jiang.threadcoreknowledge.singleton;

/**
 * lazy mode
 * thread non-safe
 * do not recommend
 */
public class Singleton5 {
  private static Singleton5 instance;

  private Singleton5() {

  }

  /**
   * give synchronized, different thread can not quick get the instance, slow
   */
  public static Singleton5 getInstance() {
    if (instance == null) {
      synchronized (Singleton5.class){
        instance = new Singleton5(); // maybe create two times
      }
    }
    return instance;
  }
}
