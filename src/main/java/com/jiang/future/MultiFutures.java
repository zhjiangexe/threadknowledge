package com.jiang.future;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MultiFutures {
  public static void main(String[] args) throws InterruptedException {
    ExecutorService service = Executors.newFixedThreadPool(20);
    Callable<Integer> callable = () -> {
      Thread.sleep(3000);
      return new Random().nextInt();
    };

    List<Future<Integer>> futures = IntStream.range(0, 20)
        .mapToObj(i -> service.submit(callable))
        .collect(Collectors.toList());

    futures.forEach(future -> {
      try {
        Integer integer = future.get(); // suspend
        System.out.println(integer);
      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
      }
    });
  }
}
