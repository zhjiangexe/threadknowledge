package com.jiang.flowcontrol.condition;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * implementing producer consumer pattern
 */
public class ConditionDemo2 {
  private int queueSize = 10;
  private PriorityQueue<Integer> queue = new PriorityQueue<>(queueSize);
  private ReentrantLock lock = new ReentrantLock();
  private Condition notFull = lock.newCondition();
  private Condition notEmpty = lock.newCondition();

  public static void main(String[] args) {
    ConditionDemo2 conditionDemo2 = new ConditionDemo2();
    Producer producer = conditionDemo2.new Producer();
    Consumer consumer = conditionDemo2.new Consumer();
    producer.start();
    consumer.start();

  }
  class Consumer extends Thread {
    @Override
    public void run() {
      consume();
    }

    private void consume() {
      while (true) {
        lock.lock();
        try {
          while (queue.size() == 0) {
            System.out.println("queue is empty, await");
            try {
              notEmpty.await();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
          queue.poll();
          notFull.signalAll();
          System.out.println("get one elem, queue remains : " + queue.size() + " elem.");
        } finally {
          lock.unlock();
        }
      }
    }
  }

  class Producer extends Thread {
    @Override
    public void run() {
      produce();
    }

    private void produce() {
      while (true) {
        lock.lock();
        try {
          while (queue.size() == queueSize) {
            System.out.println("queue is full, await ");
            try {
              notFull.await();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
          queue.offer(1);
          notEmpty.signalAll();
          System.out.println("put one elem, queue remains : " + (queueSize- queue.size()) + " elem.");
        } finally {
          lock.unlock();
        }
      }
    }
  }
}
