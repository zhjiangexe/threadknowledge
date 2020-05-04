package com.jiang.concurrenthashmap;

import java.util.concurrent.ConcurrentHashMap;

public class OptionsNotSafe implements Runnable {
  private static ConcurrentHashMap<String, Integer> scores = new ConcurrentHashMap<>();

  public static void main(String[] args) throws InterruptedException {
    scores.put("rock", 0);
    Thread t1 = new Thread(new OptionsNotSafe());
    Thread t2 = new Thread(new OptionsNotSafe());
    t1.start();
    t2.start();
    t1.join();
    t2.join();
    System.out.println(scores);
  }


  @Override
  public void run() {
    for (int i = 0; i < 1000; i++) {
      while (true) {
        Integer score = scores.get("rock");
        int newScore = score + 1;
        boolean b = scores.replace("rock", score, newScore);
        if (b) {
          break;
        }
      }
    }
  }
}
