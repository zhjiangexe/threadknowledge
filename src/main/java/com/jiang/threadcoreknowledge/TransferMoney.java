package com.jiang.threadcoreknowledge;

/**
 *
 */
public class TransferMoney implements Runnable {
  private int flag = 1;
  static Account a = new Account(500);
  static Account b = new Account(500);

  public static void main(String[] args) throws InterruptedException {
    TransferMoney r1 = new TransferMoney();
    TransferMoney r2 = new TransferMoney();
    r1.flag = 1;
    r2.flag = 0;
    Thread t1 = new Thread(r1);
    Thread t2 = new Thread(r2);
    t1.start();
    t2.start();
    t1.join();
    t2.join();
    System.out.println("a remain: " + a.balance +" , b remain: " + b.balance);
  }
  @Override
  public void run() {
    if (flag == 1) {
      transferMoney(a, b, 200);
    }
    if (flag == 0) {
      transferMoney(b, a, 200);
    }
  }

  public static void transferMoney(Account from, Account to, int amount) {
    synchronized (from) {
      // point: wait each other (maybe internet speed)
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      synchronized (to) {
        if (from.balance - amount < 0) {
          System.out.println("saving is not enough, transfer fail");
        }
        from.balance = from.balance - amount;
        to.balance = to.balance + amount;
        System.out.println("transfer success: " + amount);
      }
    }
  }

  static class Account {
    int balance;

    public Account(int balance) {
      this.balance = balance;
    }
  }
}
