package com.jiang.immutable;

public class FinalVarialeDemo {
  private final int a;
  private static final int b;

  {
    a = 1;
  }

  static {
    b = 2;
  }
}
