package com.jiang.threadcoreknowledge;

import java.util.Random;

public class MultiTransferMoney {
  private static final int NUM_MONEY = 1000;
  private static final int Num_ACCOUNTS = 20;
  private static final int NUM_ITERATIONS = 1000000;
  private static final int NUM_THREADS = 10;

  public static void main(String[] args) {
    Random random = new Random();
    TransferMoney.Account[] accounts = new TransferMoney.Account[Num_ACCOUNTS];

    for (int i = 0; i < accounts.length; i++) {
      accounts[i] = new TransferMoney.Account(NUM_MONEY);
    }
    class TransferThread extends Thread {
      @Override
      public void run() {
        for (int i = 0; i < NUM_ITERATIONS; i++) {
          int fromAcct = random.nextInt(Num_ACCOUNTS);
          int toAcct = random.nextInt(Num_ACCOUNTS);
          int amount = random.nextInt(NUM_MONEY);
          TransferMoney.transferMoney(accounts[fromAcct], accounts[toAcct], amount);
        }
      }
    }
    for (int i = 0; i < NUM_THREADS; i++) {
      new TransferThread().start();
    }
  }
}
