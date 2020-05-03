package com.jiang.concurrency.threadlocal;

import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadLocalUsage2
 */
public class ThreadLocalNormalUsage06 {
  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(5);
    for (int i = 0; i < 1000; i++) {
      executorService.execute(new Thread(() -> new Service1().process()));
    }
  }
}

/**
 * should be a filter
 */
class Service1 {
  private Service2 service2 = new Service2();

  public void process() {
    User user = new User("rockman");
    UserContextHolder.holder.set(user);
    service2.process();
  }
}

class Service2 {
  private Service3 service3 = new Service3();

  public void process() {
    // Get different ThreadLocal in same Thread, one thread can use ThreadLocalMap to cantain many ThreadLocal
    User user = UserContextHolder.holder.get();
    SimpleDateFormat simpleDateFormat = ThreadSafeFormatter.dateFormatThreadLocal.get();

    System.out.println("service2 get user: " + user.name);
    service3.process();

  }
}

class Service3 {
  public void process() {
    User user = UserContextHolder.holder.get();
    System.out.println("service3 get user: " + user.name);
  }
}

class UserContextHolder {
  public static ThreadLocal<User> holder = new ThreadLocal<>();
}

class User {
  String name;

  public User(String name) {
    this.name = name;
  }
}