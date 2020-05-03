package com.jiang.concurrency.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 1000 thread print date, use treadpool
 */
public class ThreadLocalNormalUsage03 {
  public static ExecutorService threadPool = Executors.newFixedThreadPool(10);
  static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

  public String date(int seconds) {
    // parameter start with 1970.1.1 00:00:00 GMT
    Date date = new Date(seconds * 1000);
    return simpleDateFormat.format(date);
  }

  public static void main(String[] args) throws InterruptedException {
    for (int i = 0; i < 1000; i++) {
      int finalI = i;
      threadPool.submit(new Runnable() {
        @Override
        public void run() {
          String date = new ThreadLocalNormalUsage03().date(finalI);
          System.out.println(date);
        }
      });
    }
    threadPool.shutdown();

  }
}
