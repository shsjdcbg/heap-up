package pers.dyx.limit.exception;

/**
 * @author dyx
 * @date 2020/9/10 9:34
 */
public class LimitException extends RuntimeException {

    public LimitException(String message) {
        super(message);
    }

    public LimitException(String message, Throwable cause) {
        super(message, cause);
    }
}
