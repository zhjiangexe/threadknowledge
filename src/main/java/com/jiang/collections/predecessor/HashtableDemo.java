package com.jiang.collections.predecessor;

import java.util.Hashtable;

public class HashtableDemo {
  public static void main(String[] args) {
    Hashtable<Object, Object> hashtable = new Hashtable<>();
    hashtable.put("rock", "man");
    Object rock = hashtable.get("rock"); // many method is proctected by synchronized
    System.out.println(rock);
  }
}
