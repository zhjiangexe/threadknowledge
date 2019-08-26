package com.jiang.threadcoreknowledge.jmm;

/**
 * b=3,a=3
 * b=2,a=3
 * b=2,a=1
 * b=3,a=1 ??? data sync into main memory not yet
 */
public class FieldVisibilityVolatile {
  volatile int a = 1;
  volatile int b = 2;

  private void change() {
    a = 3;
    b = a;
  }

  private void print() {
    System.out.println("b= " + b + ", a= " + a);
  }

  public static void main(String[] args) {
    while (true) {
      FieldVisibilityVolatile test = new FieldVisibilityVolatile();
      new Thread(new Runnable() {
        @Override
        public void run() {
          try {
            Thread.sleep(1);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          test.change();
        }
      }).start();
      new Thread(new Runnable() {
        @Override
        public void run() {
          try {
            Thread.sleep(1);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          test.print();
        }
      }).start();
    }
  }
}
