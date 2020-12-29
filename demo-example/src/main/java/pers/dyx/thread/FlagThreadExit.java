package pers.dyx.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author: dyx
 * @date: 2018/11/6 09:54
 * @description: 由于线程的interrupt标识很有可能被擦除，或者逻辑单元中不会调用任何可中断方法
 * 所以使用volatile修饰的开关flag关闭线程也是一种常见的做法。
 */
public class FlagThreadExit {

    static class MyTask extends Thread {
        private volatile boolean closed = false;

        @Override
        public void run() {
            System.out.println("I will start work");
            while (!closed && !isInterrupted()) {
                //working
            }
            System.out.println("I will be exiting");
        }

        public void close() {
            this.closed = true;
            this.interrupt();
        }
    }

    public static void main(String args[]) throws InterruptedException {
        MyTask t = new MyTask();
        t.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("System will be shutdown.");
        t.close();
    }
}
