package com.jiang.concurrency.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 2 thread print date
 */
public class ThreadLocalNormalUsage00 {
  public String date(int seconds) {
    // parameter start with 1970.1.1 00:00:00 GMT
    Date date = new Date(seconds * 1000);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    return simpleDateFormat.format(date);
  }

  public static void main(String[] args) {
    new Thread(new Runnable() {
      @Override
      public void run() {
        String date = new ThreadLocalNormalUsage00().date(10);
        System.out.println(date);
      }
    }).start();

    new Thread(new Runnable() {
      @Override
      public void run() {
        String date = new ThreadLocalNormalUsage00().date(1007);
        System.out.println(date);
      }
    }).start();
  }
}
