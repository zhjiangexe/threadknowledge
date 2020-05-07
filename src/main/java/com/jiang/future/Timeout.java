package com.jiang.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * if get timeout, handle future.cancel()
 */
public class Timeout {
  private static final Ad DEFAULT_AD = new Ad("no internet advertisement");
  private static final ExecutorService service = Executors.newFixedThreadPool(10);

  static class Ad {
    String name;

    public Ad(String name) {
      this.name = name;
    }

    @Override
    public String toString() {
      return "Ad{" +
          "name='" + name + '\'' +
          '}';
    }
  }

  static class FetchAdTask implements Callable<Ad> {

    @Override
    public Ad call() throws Exception {
      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) {
        System.out.println("sleep throw InterruptedException");
        return new Ad("InterruptedException advertisement"); // useless
      }
      return new Ad("travel ticket advertisement");
    }
  }

  private void printAd() {
    Future<Ad> future = service.submit(new FetchAdTask());
    Ad ad;
    try {
      ad = future.get(2000, TimeUnit.MILLISECONDS);
    } catch (InterruptedException e) {
      ad = new Ad("InterruptedException advertisement");
    } catch (ExecutionException e) {
      ad = new Ad("ExecutionException advertisement");
    } catch (TimeoutException e) {
      ad = new Ad("TimeoutException advertisement");
      System.out.println("overtime,  get advertisement");
      boolean cancel = future.cancel(true); // true: direct interrupt sleep
      System.out.println("cancel result: " + cancel);
    }
    service.shutdown();
    System.out.println(ad);
  }

  public static void main(String[] args) {
    new Timeout().printAd();
  }
}
