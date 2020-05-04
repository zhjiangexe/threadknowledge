package com.jiang.collections.predecessor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Collections.synchronizedList
 */
public class SyncList {
  public static void main(String[] args) {
    List<Integer> integers = Collections.synchronizedList(new ArrayList<Integer>());
    integers.add(5);
    System.out.println(integers.get(0));
  }
}
