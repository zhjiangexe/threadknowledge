package com.jiang.threadcoreknowledge.deadlock;

/**
 *
 */
public class TransferMoneyFix implements Runnable {
  private int flag = 1;
  static Account a = new Account(500);
  static Account b = new Account(500);
  static final Object lock = new Object();

  public static void main(String[] args) throws InterruptedException {
    TransferMoneyFix r1 = new TransferMoneyFix();
    TransferMoneyFix r2 = new TransferMoneyFix();
    r1.flag = 1;
    r2.flag = 0;
    Thread t1 = new Thread(r1);
    Thread t2 = new Thread(r2);
    t1.start();
    t2.start();
    t1.join();
    t2.join();
    System.out.println("a remain: " + a.balance + " , b remain: " + b.balance);
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
    class Helper {
      public void tranfer() {
        if (from.balance - amount < 0) {
          System.out.println("saving is not enough, transfer fail");
        }
        from.balance = from.balance - amount;
        to.balance = to.balance + amount;
        System.out.println("transfer success: " + amount);
      }
    }
    int fromHash = System.identityHashCode(from);
    int toHash = System.identityHashCode(to);
    // fromHash, toHash can be instead of Primary Key
    if (fromHash < toHash) {
      synchronized (from) {
        synchronized (to) {
          new Helper().tranfer();
        }
      }
    } else if (fromHash > toHash) {
      synchronized (to) {
        synchronized (from) {
          new Helper().tranfer();
        }
      }
    } else {
      synchronized (lock) { // if hashcode are same
        synchronized (to) {
          synchronized (from) {
            new Helper().tranfer();
          }
        }
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
