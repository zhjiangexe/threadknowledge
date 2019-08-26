package com.jiang.threadcoreknowledge.createthreads.wrongways;

public class Lambda {
  public static void main(String[] args) {
    new Thread(){
      @Override
      public void run() {
        System.out.println(Thread.currentThread().getName());
      }
    }.start();

    new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println(Thread.currentThread().getName());
      }
    }).start();
  }
}
