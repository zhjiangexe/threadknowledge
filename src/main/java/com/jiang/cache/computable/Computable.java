package com.jiang.cache.computable;

/**
 * every computer
 */
public interface Computable<A, V> {
  V compute(A arg) throws Exception;
}
