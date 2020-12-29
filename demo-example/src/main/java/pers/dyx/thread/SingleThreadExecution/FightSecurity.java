package pers.dyx.thread.SingleThreadExecution;

import java.util.concurrent.locks.Lock;

/**
 * @author: dyx
 * @date: 2018/11/8 10:48
 * @description:
 */
public class FightSecurity {
    private int count = 0;
    private String boardingPass = "null";
    private String idCard = "null";

    public void pass(String boardingPass, String idCard) {
        this.boardingPass = boardingPass;
        this.idCard = idCard;
        this.count++;
        check();
    }

    private void check() {
        if (boardingPass.charAt(0) != idCard.charAt(0)) {
            throw new RuntimeException("不符合" + toString());
        }
    }

    @Override
    public String toString() {
        return "FightSecurity{" +
                "count=" + count +
                ", boardingPass='" + boardingPass + '\'' +
                ", idCard='" + idCard + '\'' +
                '}';
    }
}
