package com.jiang.immutable;

public class FinalString2 {
  public static void main(String[] args) {
    String a = "wukong2";
    final String b = getWuKong();
    String c = b + 2;
    System.out.println(a == c);
  }

  private static String getWuKong() {
    return "wukong";
  }
}
