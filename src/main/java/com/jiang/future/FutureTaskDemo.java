package com.jiang.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * shows usage of FutureTask
 */
public class FutureTaskDemo {
  public static void main(String[] args) {
    Task task = new Task();
    FutureTask<Integer> integerFutureTask = new FutureTask<>(task);
//    new Thread(integerFutureTask).start();
    ExecutorService service = Executors.newCachedThreadPool();
    service.submit(integerFutureTask);
    try {
      Integer integer = integerFutureTask.get();
      System.out.println("task result: " + integer);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }


}

class Task implements Callable<Integer> {

  @Override
  public Integer call() throws Exception {
    System.out.println("child thread is computing");
    Thread.sleep(3000);
    int sum = 0;
    for (int i = 0; i < 100; i++) {
      sum += i;
    }
    return sum;
  }
}
