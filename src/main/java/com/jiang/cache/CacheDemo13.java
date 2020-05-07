package com.jiang.cache;

import com.jiang.cache.computable.ExpensiveFunction;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CacheDemo13 {
  static CacheDemo10<String, Integer> expensiveComputer = new CacheDemo10<>(new ExpensiveFunction());
  static CountDownLatch countDownLatch = new CountDownLatch(1);

  public static void main(String[] args) throws InterruptedException {
    ExecutorService service = Executors.newFixedThreadPool(100);
    for (int i = 0; i < 100; i++) {
      service.submit(() -> {
        Integer result = null;
        try {
          System.out.println(Thread.currentThread().getName() + " start waiting");
          countDownLatch.await();
          SimpleDateFormat dateFormat = ThreadSafeFormatter.dateFormatThreadLocal.get();
          String time = dateFormat.format(new Date());
          System.out.println(Thread.currentThread().getName() + " active at " + time);
          result = expensiveComputer.compute("666");
        } catch (ExecutionException e) {
          e.printStackTrace();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(result);
      });
    }
    Thread.sleep(5000);
    countDownLatch.countDown();
//    service.shutdown();
  }
}

class ThreadSafeFormatter {
  public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = new ThreadLocal<SimpleDateFormat>() {
    @Override
    protected SimpleDateFormat initialValue() {
      System.out.println(Thread.currentThread().getName());
      return new SimpleDateFormat("mm:ss");
    }

    public SimpleDateFormat get() {
      return super.get();
    }
  };
}