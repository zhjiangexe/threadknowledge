package com.jiang.concurrency.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 30 thread print date
 */
public class ThreadLocalNormalUsage01 {
  public String date(int seconds) {
    // parameter start with 1970.1.1 00:00:00 GMT
    Date date = new Date(seconds * 1000);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    return simpleDateFormat.format(date);
  }

  public static void main(String[] args) throws InterruptedException {
    for (int i = 0; i < 30; i++) {
      int finalI = i;
      new Thread(new Runnable() {
        @Override
        public void run() {
          String date = new ThreadLocalNormalUsage01().date(finalI);
          System.out.println(date);
        }
      }).start();
      Thread.sleep(100);
    }


  }
}
