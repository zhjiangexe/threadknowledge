package com.jiang.threadcoreknowledge.singleton;

/**
 * double check
 * thread safe
 * recommend for interview
 */
public class Singleton6 {
  /**
   * no volatile no visibility
   */
  private volatile static Singleton6 instance;

  private Singleton6() {

  }

  /**
   * give synchronized, different thread can not quick get the instance, slow
   */
  public static Singleton6 getInstance() {
    if (instance == null) {
      synchronized (Singleton6.class) { // first thread can be let second see
        if (instance == null) {
          instance = new Singleton6(); // maybe create two times, volatile: create empty object, constructor, assign to
        }
      }
    }
    return instance;
  }
}
