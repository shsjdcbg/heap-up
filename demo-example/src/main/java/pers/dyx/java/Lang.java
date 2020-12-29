package pers.dyx.java;

/**
 * Description：基础数据类型 VS 基本类型封装类
 *
 * @author dyx
 * @date 2020/6/15 13:20
 */
public class Lang {

    private byte aByte;
    private Byte bByte;

    private short aShort;
    private Short bShort;

    private int aInt;
    private Integer bInt;

    private long aLong;
    private Long bLong;

    private float aFloat;
    private Float bFloat;

    private double aDouble;
    private Double bDouble;

    private char aChar;
    private Character bChar;

    private boolean aBoolean;
    private Boolean bBoolean;

    public byte getaByte() {
        return aByte;
    }

    public void setaByte(byte aByte) {
        this.aByte = aByte;
    }

    public Byte getbByte() {
        return bByte;
    }

    public void setbByte(Byte bByte) {
        this.bByte = bByte;
    }

    public short getaShort() {
        return aShort;
    }

    public void setaShort(short aShort) {
        this.aShort = aShort;
    }

    public Short getbShort() {
        return bShort;
    }

    public void setbShort(Short bShort) {
        this.bShort = bShort;
    }

    public int getaInt() {
        return aInt;
    }

    public void setaInt(int aInt) {
        this.aInt = aInt;
    }

    public Integer getbInt() {
        return bInt;
    }

    public void setbInt(Integer bInt) {
        this.bInt = bInt;
    }

    public long getaLong() {
        return aLong;
    }

    public void setaLong(long aLong) {
        this.aLong = aLong;
    }

    public Long getbLong() {
        return bLong;
    }

    public void setbLong(Long bLong) {
        this.bLong = bLong;
    }

    public float getaFloat() {
        return aFloat;
    }

    public void setaFloat(float aFloat) {
        this.aFloat = aFloat;
    }

    public Float getbFloat() {
        return bFloat;
    }

    public void setbFloat(Float bFloat) {
        this.bFloat = bFloat;
    }

    public double getaDouble() {
        return aDouble;
    }

    public void setaDouble(double aDouble) {
        this.aDouble = aDouble;
    }

    public Double getbDouble() {
        return bDouble;
    }

    public void setbDouble(Double bDouble) {
        this.bDouble = bDouble;
    }

    public char getaChar() {
        return aChar;
    }

    public void setaChar(char aChar) {
        this.aChar = aChar;
    }

    public Character getbChar() {
        return bChar;
    }

    public void setbChar(Character bChar) {
        this.bChar = bChar;
    }

    public boolean isaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    public Boolean getbBoolean() {
        return bBoolean;
    }

    public void setbBoolean(Boolean bBoolean) {
        this.bBoolean = bBoolean;
    }

    @Override
    public String toString() {
        return "Lang{" +
                "aByte=" + aByte +
                ", bByte=" + bByte +
                ", aShort=" + aShort +
                ", bShort=" + bShort +
                ", aInt=" + aInt +
                ", bInt=" + bInt +
                ", aLong=" + aLong +
                ", bLong=" + bLong +
                ", aFloat=" + aFloat +
                ", bFloat=" + bFloat +
                ", aDouble=" + aDouble +
                ", bDouble=" + bDouble +
                ", aChar=" + aChar +
                ", bChar=" + bChar +
                ", aBoolean=" + aBoolean +
                ", bBoolean=" + bBoolean +
                '}';
    }
}
