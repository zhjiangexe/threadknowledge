package com.jiang.collections.predecessor;

import java.util.Vector;

/**
 * only for checking source code,
 */
public class VectorDemo {
  public static void main(String[] args) {
    Vector<String> vector = new Vector<>();
    vector.add("test");
    String result = vector.get(0); // many method is proctected by synchronized
    System.out.println(result);
  }
}
