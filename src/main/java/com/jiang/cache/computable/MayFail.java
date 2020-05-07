package com.jiang.cache.computable;

import java.io.IOException;

/**
 *
 */
public class MayFail implements Computable<String, Integer> {
  @Override
  public Integer compute(String arg) throws Exception {
    double random = Math.random();
    if (random > 0.5) {
      throw new IOException("read document fail");
    }
    Thread.sleep(3000);
    return Integer.valueOf(arg);
  }
}
