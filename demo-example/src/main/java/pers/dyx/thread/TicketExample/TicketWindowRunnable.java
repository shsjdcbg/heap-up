package pers.dyx.thread.TicketExample;

/**
 * @author: dyx
 * @date: 2018/11/6 10:12
 * @description:
 */
public class TicketWindowRunnable implements Runnable {
    private int index = 1;
    private final static int MAX = 500;

    @Override
    public void run() {
        synchronized (this) {
            while (index <= MAX) {
                System.out.println(Thread.currentThread() + "的号码是"
                        + index++);
            }
        }
    }

    public static void main(String args[]){
        final TicketWindowRunnable task = new TicketWindowRunnable();
        Thread thread1 = new Thread(task,"1号窗口");
        Thread thread2 = new Thread(task,"2号窗口");
        Thread thread3 = new Thread(task,"3号窗口");
        Thread thread4 = new Thread(task,"4号窗口");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
