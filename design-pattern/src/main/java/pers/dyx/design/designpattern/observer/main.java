package pers.dyx.design.designpattern.observer;

/**
 * @author: dyx
 * @date: 2018/12/3 15:06
 * @description:
 */
public class main {

    public static void main(String args[]){
        Observable observable = new Observable();
        observable.addObserver(new Observer());
        observable.connectionLost();
    }
}
