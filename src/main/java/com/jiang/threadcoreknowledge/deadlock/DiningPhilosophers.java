package com.jiang.threadcoreknowledge.deadlock;

/**
 * Philosophers dining cause deadlock
 */
public class DiningPhilosophers {
  public static void main(String[] args) {
    Philosopher[] philosophers = new Philosopher[5];
    Object[] chopsticks = new Object[philosophers.length];
    for (int i = 0; i < chopsticks.length; i++) {
      chopsticks[i] = new Object();
    }
    for (int i = 0; i < philosophers.length; i++) {
      Object leftChopstick = chopsticks[i];
      Object rightChopstick = chopsticks[(i + 1) % chopsticks.length];
      philosophers[i] = new Philosopher(leftChopstick, rightChopstick);

      new Thread(philosophers[i], "PH_" + (i + 1)).start();
    }
  }

  public static class Philosopher implements Runnable {
    private Object leftChopstick;
    private Object rightChopstick;

    public Philosopher(Object leftChopstick, Object rightChopstick) {
      this.leftChopstick = leftChopstick;
      this.rightChopstick = rightChopstick;
    }

    @Override
    public void run() {
      try {
        while (true) {
          doAction("Thinking");
          synchronized (leftChopstick) {
            doAction("Picked up left chopstick");
            synchronized (rightChopstick) {
              doAction("Picked up right chopstick -eating");
              doAction("put down right chopstick");
            }
            doAction("put down left chopstick");
          }
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    private void doAction(String action) throws InterruptedException {
      System.out.println(Thread.currentThread().getName() + " " + action);
      Thread.sleep((long) Math.random() * 10);
    }
  }
}
