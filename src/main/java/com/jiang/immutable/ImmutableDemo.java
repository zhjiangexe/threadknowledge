package com.jiang.immutable;

import java.util.HashSet;
import java.util.Set;

public class ImmutableDemo {
  private final Set<String> students = new HashSet<>();

  public ImmutableDemo() {
    students.add("bruce lee");
    students.add("rock");
  }

  public boolean isStudent(String name) {
    return students.contains(name);
  }
}
