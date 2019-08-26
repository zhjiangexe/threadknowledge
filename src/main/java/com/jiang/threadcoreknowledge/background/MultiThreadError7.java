package com.jiang.threadcoreknowledge.background;


/**
 * observable pattern
 */
public class MultiThreadError7 {

  int count;
  private EventListener listener;

  private MultiThreadError7(MySource source) {
    listener = new EventListener() {
      @Override
      public void onEvent(Event e) {
        System.out.println("\nI got a number: " + count);
      }
    };
    for (int i = 0; i < 10000; i++) { // simulate initial active
      System.out.print(i);
    }
    count = 100;
  }

  public static MultiThreadError7 getInstance(MySource mySource) {
    MultiThreadError7 safeListener = new MultiThreadError7(mySource);
    mySource.registerListener(safeListener.listener);
    return safeListener;
  }

  public static void main(String[] args) {
    MySource mySource = new MySource();
    MultiThreadError7 instance = MultiThreadError7.getInstance(mySource);
    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          Thread.sleep(10);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        mySource.eventCome(new Event() {
        });
      }
    }).start();
  }

  static class MySource {
    private EventListener listener;

    void registerListener(EventListener eventListener) {
      this.listener = eventListener;
    }

    void eventCome(Event e) {
      if (listener != null) {
        listener.onEvent(e);
      } else {
        System.out.println("initial not yet");
      }
    }
  }

  interface EventListener {
    void onEvent(Event e);
  }

  interface Event {

  }
}
