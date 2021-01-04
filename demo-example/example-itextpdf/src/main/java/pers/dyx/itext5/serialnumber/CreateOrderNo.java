package pers.dyx.itext5.serialnumber;

import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by ZP on 2017/8/18.
 */
public class CreateOrderNo {
//    private static final Long    maxNo                = 10000000l;                // 最大流水号
//    private static final int     stepNo               = 1;                        // 流水号每次增加数
//    private static final Integer completionZeroLength = maxNo.toString().length(); // 流水号补0长度
//    private static final String  dateFormat           = "yyyyMMddhhmmssSSS";      // 格式化日期格式
//    private static final String  serialNumberFormat   = "%0{0}d";                 // // 0 代表前面补充0, {0}代表补0长度,d代表参数为正数型
//    private static final int     randomNumber         = 6;                        // 随机数位数
//
//    /**
//     * 说明：根据台头和流水号生成单据号：单据号+时间：年月日时分秒毫秒+8位流水号（很有可能重复）
//     *
//     * @param headStr 单据台头
//     * @param serialNo 流水号（最近一个）,最好是根据每天的数据量生成count
//     * @return
//     * @author yangdong
//     */
//    public static String createOrderNo(String headStr, long serialNo) {
//        String nowdate = DateUtil.dateToFormat(new Date(), dateFormat);
//        String serialNumberFormatStr = MessageFormat.format(serialNumberFormat, completionZeroLength);
//        if (serialNo >= maxNo) {
//            serialNo = serialNo - maxNo;
//        }
//        serialNo = serialNo + stepNo;
//        String sequenceNo = String.format(serialNumberFormatStr, serialNo);
//
//        String orderNumber = headStr + nowdate + sequenceNo;
//
//        return orderNumber;
//    }
//
//    /**
//     * 方法说明：根据台头生成单据号：单据号+时间：年月日时分秒毫秒+6位水随机数（重复可能性很小）
//     *
//     * @param headStr
//     * @return String
//     * @time 2015年8月16日 下午2:38:26
//     * @author yangdong
//     */
//    public static String createOrderNo(String headStr) {
//        String nowdate = DateUtil.dateToFormat(new Date(), dateFormat);
//        String orderNumber = headStr + nowdate + RandomUtil.getRandom(randomNumber);
//        return orderNumber;
//    }
//
//     public static void main(String[] args) {
//     Map<String, String> str = new HashMap<String, String>();
//     for (int i = 0; i < 100000; i++) {
//     String string = CreateOrderNo.createOrderNo("test");
//     str.put(string, string);
//     }
//     Set<String> ss = str.keySet();
//     System.out.println("---------打印值");
//     for (String string : ss) {
//     System.out.println(str.get(string));
//     }
//     }
}
