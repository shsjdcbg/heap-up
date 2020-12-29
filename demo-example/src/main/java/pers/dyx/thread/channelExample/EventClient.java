package pers.dyx.thread.channelExample;

import java.util.concurrent.TimeUnit;

/**
 * @author: dyx
 * @date: 2018/11/6 11:55
 * @description:
 */
public class EventClient {
    public static void main(String args[]){
        final EventQueue eventQueue = new EventQueue();
        new Thread(()-> {
            for(;;){
                eventQueue.offer(new EventQueue.Event());
            }
        },"producer").start();

        new Thread(()-> {
            for(;;){
                eventQueue.take();
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"consumer").start();

    }
}
