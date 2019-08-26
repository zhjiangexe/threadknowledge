package com.jiang.threadcoreknowledge.background;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class MultiThreadsError3 {
  private Map<String, String> states;
  public MultiThreadsError3(){
    states = new HashMap<>();
    states.put("1", "Monday");
    states.put("2", "Tuesday");
  }
  public Map<String, String> getStates(){
    return this.states;
  }

  public static void main(String[] args) {
    MultiThreadsError3 multiThreadsError3 = new MultiThreadsError3();
    Map<String, String> states = multiThreadsError3.getStates();
    String s = states.get("1");
    System.out.println(s);
    states.remove("1");
    String s1 = states.get("1");
    System.out.println(s1);
  }
}
