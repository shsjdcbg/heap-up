package pers.dyx.unitconvert.defs;

/**
 * @author 0003653
 */
public interface DefEnumItf {

    /**
     * 获取对标枚举
     *
     * @return DefEnumItf
     */
    DefEnumItf getBenchmarking();

    /**
     * 获取精度
     *
     * @return 精度
     */
    int getAccuracy();

    /**
     * 获取定义基础信息
     *
     * @return DefInfo
     */
    DefInfo getDefInfo();

}
