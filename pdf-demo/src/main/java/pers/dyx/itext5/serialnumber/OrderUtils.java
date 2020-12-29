package pers.dyx.itext5.serialnumber;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class OrderUtils {

    /**
     * 根据系统时间获取流水单号。
     * 规则：年月日+时分秒+毫秒+四位随机数+随机大写字母。
     * 例：201607291708220585691A
     * @return 流水单号
     */
    public static String getSerialnumber(){
        Date date = new Date();
        SimpleDateFormat sdf = new  SimpleDateFormat("yyyyMMddHHmmssSSS");
        String serialnumber = sdf.format(date);
        String letter[]={"A","B","C","D","E",
                "F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","x","Y","Z"};
        //添加四个随机数
        for (int i = 1; i < 5; i++) {
            serialnumber+=Math.floor(Math.random()*10);
        }
        //添加一个随机字母
        serialnumber+=letter[new Random().nextInt(26)];
        return serialnumber;
    }

    public static void main(String[] args) {
        String ss =  getSerialnumber();
        System.out.println(ss);
    }

}
