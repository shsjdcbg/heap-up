package pers.dyx.java;

import java.util.Observable;

/**
 * @author: dyx
 * @date: 2018/12/3 15:02
 * @description: 观察者
 */
public class Observer implements java.util.Observer{
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("做件事");
    }
}
