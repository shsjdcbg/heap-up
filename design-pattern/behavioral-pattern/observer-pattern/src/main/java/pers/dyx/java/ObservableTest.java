package pers.dyx.java;

/**
 * @author: dyx
 * @date: 2018/12/3 15:06
 * @description:
 */
public class ObservableTest {

    public static void main(String args[]){
        Observable observable = new Observable();
        observable.addObserver(new Observer());
        observable.connectionLost();
    }
}
