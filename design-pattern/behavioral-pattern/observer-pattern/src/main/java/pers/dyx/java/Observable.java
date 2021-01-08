package pers.dyx.java;

/**
 * @author: dyx
 * @date: 2018/12/3 15:04
 * @description: 被观察者
 */
public class Observable extends java.util.Observable {

    public void connectionLost() {
        doBusiness();
    }

    /**
     * 此方法一经调用，立马可以通知观察者，在本例中是监听线程
     */
    public void doBusiness() {
        if (true) {
            super.setChanged();
        }
        notifyObservers();
    }
}
