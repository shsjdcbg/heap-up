package pers.dyx.thread.SingleThreadExecution;

/**
 * @author: dyx
 * @date: 2018/11/8 10:52
 * @description:
 */
public class FightSecurityTest{
    static class Passengers extends Thread {
        private final FightSecurity fightSecurity;

        private final String idCard;

        private final String boardingPass;

        public Passengers(FightSecurity fightSecurity,String idCard,String boardingPass){
            this.fightSecurity = fightSecurity;
            this.idCard = idCard;
            this.boardingPass = boardingPass;
        }

        @Override
        public void run(){
            while (true){
                synchronized (fightSecurity) {
                    fightSecurity.pass(boardingPass, idCard);
                }
            }
        }
    }

    public static void main(String args[]){
        final FightSecurity fightSecurity = new FightSecurity();
        new Passengers(fightSecurity,"A1","A11").start();
        new Passengers(fightSecurity,"B2","B22").start();
        new Passengers(fightSecurity,"C3","C33").start();
    }
}
