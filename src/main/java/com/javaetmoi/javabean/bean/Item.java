package com.javaetmoi.javabean.bean;

/**
 * Wraps an item value in order to rework it before generating code.
 * <p>
 * Usage:
 * <ul>
 * <li>Key or value of a Map</li>
 * <li>Value of an array</li>
 * </ul>
 */
public class Item {

    private Object val;
    private final Class<?> clazz;

    /**
     * Javapoet expression
     */
    private String pattern = "$L";

    public Item(Object val) {
        this.val = val;
        this.clazz = val.getClass();
    }

    public void setVal(Object val) {
        this.val = val;
    }

    public Object getVal() {
        return val;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public void appendAfterVal(String str) {
        val = val + str;
    }

    public void appendBeforeVal(String str) {
        val = str + val;
    }
}
