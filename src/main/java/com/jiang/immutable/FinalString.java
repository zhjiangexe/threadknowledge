package com.jiang.immutable;

public class FinalString {
  public static void main(String[] args){
    String a = "wukong2";
    final String b = "wukong"; // compile time get data
    String d = "wukong";
    String c = b + 2; // compile
    String e = d + 2;
    System.out.println((a == c)); // true
    System.out.println((a == e)); // false
  }
}
