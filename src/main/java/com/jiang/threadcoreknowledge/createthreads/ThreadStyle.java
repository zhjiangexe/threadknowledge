package com.jiang.threadcoreknowledge.createthreads;

/**
 * use Thread create thread
 */
public class ThreadStyle extends Thread {
  public void run() { // override original run (runnable)
    System.out.println("use Thread implement thread");
  }

  public static void main(String[] args) {
    new ThreadStyle().start();
  }
}
