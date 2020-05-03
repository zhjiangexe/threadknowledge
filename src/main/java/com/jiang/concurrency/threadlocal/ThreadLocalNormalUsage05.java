package com.jiang.concurrency.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * use ThreadLocal, provide every thread get its own simpleDateFormat,
 * ensure thread safe and use memory efficiently
 */
public class ThreadLocalNormalUsage05 {
  public static ExecutorService threadPool = Executors.newFixedThreadPool(10);

  public String date(int seconds) {
    // parameter start with 1970.1.1 00:00:00 GMT
    Date date = new Date(seconds * 1000);
    SimpleDateFormat simpleDateFormat = ThreadSafeFormatter.dateFormatThreadLocal.get();
    return simpleDateFormat.format(date);
  }

  public static void main(String[] args) throws InterruptedException {
    for (int i = 0; i < 1000; i++) {
      int finalI = i;
      threadPool.submit(new Runnable() {
        @Override
        public void run() {
          String date = new ThreadLocalNormalUsage05().date(finalI);
          System.out.println(date);
        }
      });
    }
    threadPool.shutdown();
  }
}
class ThreadSafeFormatter {
  public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = new ThreadLocal<SimpleDateFormat>(){
    @Override
    protected SimpleDateFormat initialValue() {
      System.out.println(Thread.currentThread().getName());
      return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    }
  };
}