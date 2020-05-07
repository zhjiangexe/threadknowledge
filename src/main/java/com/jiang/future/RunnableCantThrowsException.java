package com.jiang.future;

import java.util.concurrent.Callable;

/**
 * can't not throw checked Exception
 */
public class RunnableCantThrowsException {
  public static void main(String[] args) {
    Runnable runnable = ()-> {
      try {
        throw new Exception();
      } catch (Exception e) {
        e.printStackTrace();
      }
    };

    Callable<String> callable = () -> {
      if(true) {
        throw new Exception("rock");
      } else {
        return "rock";
      }
    };
  }
}
