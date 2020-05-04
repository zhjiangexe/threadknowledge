package com.jiang.collections.predecessor;

import java.util.HashMap;
import java.util.Map;

/**
 * basic usage of map
 */
public class MapDemo {

  public static void main(String[] args) {
    Map<String, String> map = new HashMap<>();
    System.out.println(map.isEmpty());
    map.put("rock", "man");
    map.put("cut", "man");
    System.out.println(map.keySet());
    System.out.println(map.get("rock"));
    System.out.println(map.size());
    System.out.println(map.containsKey("cut"));
  }
}
