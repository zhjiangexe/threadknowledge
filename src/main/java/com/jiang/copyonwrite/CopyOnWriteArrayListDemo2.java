package com.jiang.copyonwrite;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListDemo2 {
  public static void main(String[] args) {
    CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>(new Integer[]{1, 2, 3});
    System.out.println(list);

    Iterator<Integer> itr1 = list.iterator(); // copy list at the moment 1, 2, 3
    list.add(4);
    System.out.println(list);

    Iterator<Integer> itr2 = list.iterator(); // copy list at the moment 1, 2, 3, 4
    itr1.forEachRemaining(System.out::println);
    itr2.forEachRemaining(System.out::println);
  }
}
