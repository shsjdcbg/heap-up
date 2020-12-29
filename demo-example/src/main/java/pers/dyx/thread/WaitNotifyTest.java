package pers.dyx.thread;

/**
 * @author: dyx
 * @date: 2018/11/6 17:44
 * @description:
 */
public class WaitNotifyTest {
    private Integer num = 0;

    public Integer read(){
        synchronized (num) {
            if (num == 0) {
                try {
                    num.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(num);
            return num;
        }
    }

    public void wirte(){
        synchronized (num){
//            num = 1;
            num.notify();
        }
    }

    public static void main(String args[]){
        WaitNotifyTest waitNotifyTest = new WaitNotifyTest();
        new Thread(()-> {
            waitNotifyTest.read();
        }).start();

        new Thread(()->{
            waitNotifyTest.wirte();
        }).start();

    }
}
