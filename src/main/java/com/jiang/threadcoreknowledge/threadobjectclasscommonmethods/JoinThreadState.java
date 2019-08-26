package com.jiang.threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * first join then mainThread.getState()
 * through debugger watch join state
 */
public class JoinThreadState {
  public static void main(String[] args) throws InterruptedException {
    Thread mainThread = Thread.currentThread();
    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          Thread.sleep(3000);
          System.out.println(mainThread.getState());
          System.out.println("thread-0 execute finish");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    thread.start();
    System.out.println("wait child thread execute finish");
    thread.join();
    System.out.println(mainThread.getState());
    System.out.println("child thread execute finish");
  }
}
