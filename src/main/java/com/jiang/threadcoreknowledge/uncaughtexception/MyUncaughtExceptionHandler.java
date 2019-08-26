package com.jiang.threadcoreknowledge.uncaughtexception;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
  private String name;

  public MyUncaughtExceptionHandler(String name) {
    this.name = name;
  }

  @Override
  public void uncaughtException(Thread t, Throwable e) {
    Logger anonymousLogger = Logger.getAnonymousLogger();
    anonymousLogger.log(Level.WARNING, "thread exception, terminate! " + t.getName(), e);
    System.out.println(name + " catch exception " + t.getName() + e);
  }
}
