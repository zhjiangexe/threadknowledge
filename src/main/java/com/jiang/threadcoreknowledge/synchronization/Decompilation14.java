package com.jiang.threadcoreknowledge.synchronization;

/**
 * decompilation
 */
public class Decompilation14 {
  private Object object = new Object();

  public static void main(String[] args) {
  }

  public void insert(Thread thread){
    synchronized (object){
      thread.start();
    }
  }
}
