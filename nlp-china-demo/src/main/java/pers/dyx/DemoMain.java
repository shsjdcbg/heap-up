package pers.dyx;

import org.ansj.splitWord.analysis.*;

/**
 * @author dyx
 * @date 2020/9/15 8:20
 */
public class DemoMain {

    public static void main(String[] args) {
        // 分词
        String str = "洁面仪配合洁面深层清洁毛孔，清洁鼻孔面膜碎觉使劲挤才能出一点点皱纹 脸颊毛孔修复的看不见啦 草莓鼻历史遗留问题没辙 脸和脖子差不多颜色的皮肤才是健康的 长期使用安全健康的比同龄人显小五到十岁 28岁的妹子看看你们的鱼尾纹";

        System.out.println(BaseAnalysis.parse(str));
        System.out.println(ToAnalysis.parse(str));
        System.out.println(DicAnalysis.parse(str));
        System.out.println(IndexAnalysis.parse(str));
        System.out.println(NlpAnalysis.parse(str));

    }
}
