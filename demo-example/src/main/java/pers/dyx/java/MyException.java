package pers.dyx.java;

/**
 * 自定义异常
 *
 * @author dyx
 * @version 1.0
 * @date 2020/7/24 13:11
 */
public class MyException extends Exception {

    public MyException() {
    }

    public MyException(String message) {
        super(message);
    }

    public MyException(String message, Throwable cause) {
        super(message, cause);
    }
}
