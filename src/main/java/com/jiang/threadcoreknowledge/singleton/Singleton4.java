package com.jiang.threadcoreknowledge.singleton;

/**
 * lazy mode
 * thread safe
 * do not recommend
 */
public class Singleton4 {
  private static Singleton4 instance;

  private Singleton4() {

  }

  /**
   * give synchronized, many different thread can not quick get the instance, slow
   */
  public synchronized static Singleton4 getInstance() {
    if (instance == null) {
      instance = new Singleton4(); // maybe create two times
    }
    return instance;
  }
}
