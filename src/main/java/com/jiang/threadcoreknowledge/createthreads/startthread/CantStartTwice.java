package com.jiang.threadcoreknowledge.createthreads.startthread;

/**
 * can not invoke start method twice, otherwise throw IllegalThreadStateException
 */
public class CantStartTwice {
  public static void main(String[] args) {
    Thread thread = new Thread();
    thread.start();
    thread.start();
  }
}
