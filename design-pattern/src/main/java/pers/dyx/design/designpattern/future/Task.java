package pers.dyx.design.designpattern.future;

/**
 * @author: dyx
 * @date: 2018/11/8 13:43
 * @description:
 */
public interface Task<IN,OUT> {
    OUT get(IN input);
}
