package com.jiang.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GetException {
  public static void main(String[] args) {
    ExecutorService service = Executors.newFixedThreadPool(2);
    Callable<Integer> callable = () -> {
      throw new IllegalArgumentException("callable throw exception");
    };

    Future<Integer> future = service.submit(callable);
    try {
      for (int i = 0; i < 5; i++) {
        System.out.println(i);
        Thread.sleep(500);
      }
      System.out.println(future.isDone());
      future.get();
    } catch (InterruptedException e) {
      e.printStackTrace();
      System.out.println("InterruptedException");
    } catch (ExecutionException e) {
      e.printStackTrace();
      System.out.println("ExecutionException");
    }
  }
}
