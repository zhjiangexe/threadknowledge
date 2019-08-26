package com.jiang.threadcoreknowledge.background;

import java.util.HashMap;
import java.util.Map;

/**
 * create thread in constructor
 */
public class MultiThreadError6 {
  private Map<String, String> states;
  public MultiThreadError6(){
    new Thread(new Runnable() {
      @Override
      public void run() {
        states = new HashMap<>();
        states.put("1", "Monday");
        states.put("2", "Tuesday");
      }
    }).start();
  }
  public Map<String, String> getStates(){
    return this.states;
  }

  public static void main(String[] args) {
    MultiThreadError6 multiThreadsError6 = new MultiThreadError6();
    Map<String, String> states = multiThreadsError6.getStates();
    String s = states.get("1");
    System.out.println(s);
    states.remove("1");
    String s1 = states.get("1");
    System.out.println(s1);
  }
}
