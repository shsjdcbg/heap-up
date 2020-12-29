package pers.dyx.unitconvert.defs;

import java.math.BigDecimal;

/**
 * Description：定义信息对象
 *
 * @author dyx
 * @date 2020/3/14 14:46
 */
public class DefInfo {

    /**
     * 英文简写
     */
    private String simpEnName;

    /**
     * 英文名称
     */
    private String enName;

    /**
     * 中文名称
     */
    private String chName;

    /**
     * 锚点
     */
    private BigDecimal toAnchor;

    /**
     * 制式
     */
    private Standard type;

    public DefInfo(String simpEnName, String enName, String chName, BigDecimal toAnchor) {
        this.simpEnName = simpEnName;
        this.enName = enName;
        this.chName = chName;
        this.toAnchor = toAnchor;
    }

    public DefInfo(String simpEnName, String enName, String chName, BigDecimal toAnchor, Standard type) {
        this.simpEnName = simpEnName;
        this.enName = enName;
        this.chName = chName;
        this.toAnchor = toAnchor;
        this.type = type;
    }

    public String getSimpEnName() {
        return simpEnName;
    }

    public DefInfo setSimpEnName(String simpEnName) {
        this.simpEnName = simpEnName;
        return this;
    }

    public String getEnName() {
        return enName;
    }

    public DefInfo setEnName(String enName) {
        this.enName = enName;
        return this;
    }

    public String getChName() {
        return chName;
    }

    public DefInfo setChName(String chName) {
        this.chName = chName;
        return this;
    }

    public BigDecimal getToAnchor() {
        return toAnchor;
    }

    public DefInfo setToAnchor(BigDecimal toAnchor) {
        this.toAnchor = toAnchor;
        return this;
    }

    public Standard getType() {
        return type;
    }

    public DefInfo setType(Standard type) {
        this.type = type;
        return this;
    }
}
