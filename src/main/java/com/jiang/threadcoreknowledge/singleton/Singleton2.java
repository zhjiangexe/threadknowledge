package com.jiang.threadcoreknowledge.singleton;

/**
 * static block
 * thread safe
 */
public class Singleton2 {
  private final static Singleton2 INSTANCE;

  static {
    INSTANCE = new Singleton2();
  }


  private Singleton2() {

  }

  public static Singleton2 getInstance() {
    return INSTANCE;
  }
}
