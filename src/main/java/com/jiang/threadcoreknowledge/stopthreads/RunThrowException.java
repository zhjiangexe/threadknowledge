package com.jiang.threadcoreknowledge.stopthreads;

public class RunThrowException {
  public void aVoid() throws Exception {
    throw new Exception();
  }

  public static void main(String[] args) {
    new Thread(new Runnable() {
      @Override
      public void run() {

      }
    });
  }
}
