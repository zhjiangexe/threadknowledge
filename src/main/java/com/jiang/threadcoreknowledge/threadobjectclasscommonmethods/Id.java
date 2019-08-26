package com.jiang.threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * ID start from 1,
 */
public class Id {
  public static void main(String[] args) {
    Thread thread = new Thread();

    // 1
    System.out.println(Thread.currentThread().getId());
    // 12
    System.out.println(thread.getId());
  }
}
