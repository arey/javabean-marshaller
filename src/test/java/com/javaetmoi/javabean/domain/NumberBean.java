package com.javaetmoi.javabean.domain;

import java.math.BigDecimal;
import java.math.BigInteger;

public class NumberBean {

    private float floatPrimitive;

    private double doublePrimitive;

    private BigDecimal bigDecimal;

    private Short shortWrapper;

    private BigInteger bigInteger;

    public float getFloatPrimitive() {
        return floatPrimitive;
    }

    public void setFloatPrimitive(float floatPrimitive) {
        this.floatPrimitive = floatPrimitive;
    }

    public double getDoublePrimitive() {
        return doublePrimitive;
    }

    public void setDoublePrimitive(double doublePrimitive) {
        this.doublePrimitive = doublePrimitive;
    }

    public BigDecimal getBigDecimal() {
        return bigDecimal;
    }

    public void setBigDecimal(BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal;
    }

    public Short getShortWrapper() {
        return shortWrapper;
    }

    public void setShortWrapper(Short shortWrapper) {
        this.shortWrapper = shortWrapper;
    }

    public BigInteger getBigInteger() {
        return bigInteger;
    }

    public void setBigInteger(BigInteger bigInteger) {
        this.bigInteger = bigInteger;
    }
}
