package com.jiang.threadcoreknowledge.singleton;

/**
 *
 * thread safe
 * can be use
 */
public class Singleton7 {

  private Singleton7() {

  }

  /**
   * only one instance
   */
  private static class SingletonInstance{
    private static final Singleton7 INSTANCE = new Singleton7();
  }

  /**
   * give synchronized, different thread can not quick get the instance, slow
   */
  public static Singleton7 getInstance() {
    return SingletonInstance.INSTANCE;
  }
}
