package com.jiang.threadcoreknowledge.synchronization;

/**
 * reentrant different class
 */
public class SynchronizedSuperClass12 {
  public synchronized void doSomething() {
    System.out.println("I'm parent class");
  }

}

class TestClass extends SynchronizedSuperClass12 {
  @Override
  public synchronized void doSomething() {
    System.out.println("I'm child class");
    super.doSomething();
  }

  public static void main(String[] args) {
    TestClass s = new TestClass();
    s.doSomething();
  }
}
