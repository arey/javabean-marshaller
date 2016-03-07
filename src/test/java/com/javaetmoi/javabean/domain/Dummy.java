package com.javaetmoi.javabean.domain;

import java.util.*;
import java.util.Date;


public class Dummy {

    private int intNumber;

    private Set<Integer> integerSet = new LinkedHashSet<>();

    private Collection<Long> longQueue;

    private Map<String, String> stringStringMap = new TreeMap<>();

    private Map<Integer, Long> integerLongMap = new HashMap<>();

    private Map<Date, Boolean> dateBooleanMap = new HashMap<>();

    private java.sql.Date sqlDate;

    private List<List<String>> listOfStringList = new ArrayList<>();

    private Calendar calendar;

    private Map<String, Calendar> calendarMap = new TreeMap<>();

    private String[][] multiDimArray;

    private List<String> stringlist;

    public Set<Integer> getIntegerSet() {
        return integerSet;
    }

    public void setIntegerSet(Set<Integer> integerSet) {
        this.integerSet = integerSet;
    }

    public Collection<Long> getLongQueue() {
        return longQueue;
    }

    public void setLongQueue(Collection<Long> longQueue) {
        this.longQueue = longQueue;
    }

    public Map<String, String> getStringStringMap() {
        return stringStringMap;
    }

    public void setStringStringMap(Map<String, String> stringStringMap) {
        this.stringStringMap = stringStringMap;
    }

    public Map<Integer, Long> getIntegerLongMap() {
        return integerLongMap;
    }

    public void setIntegerLongMap(Map<Integer, Long> integerLongMap) {
        this.integerLongMap = integerLongMap;
    }

    public Map<Date, Boolean> getDateBooleanMap() {
        return dateBooleanMap;
    }

    public void setDateBooleanMap(Map<Date, Boolean> dateBooleanMap) {
        this.dateBooleanMap = dateBooleanMap;
    }

    public int getIntNumber() {
        return intNumber;
    }

    public void setIntNumber(int intNumber) {
        this.intNumber = intNumber;
    }

    public java.sql.Date getSqlDate() {
        return sqlDate;
    }

    public void setSqlDate(java.sql.Date sqlDate) {
        this.sqlDate = sqlDate;
    }

    public List<List<String>> getListOfStringList() {
        return listOfStringList;
    }

    public void setListOfStringList(List<List<String>> listOfStringList) {
        this.listOfStringList = listOfStringList;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public Map<String, Calendar> getCalendarMap() {
        return calendarMap;
    }

    public void setCalendarMap(Map<String, Calendar> calendarMap) {
        this.calendarMap = calendarMap;
    }

    public String[][] getMultiDimArray() {
        return multiDimArray;
    }

    public void setMultiDimArray(String[][] multiDimArray) {
        this.multiDimArray = multiDimArray;
    }

    public List<String> getStringlist() {
        return stringlist;
    }

    public void setStringlist(List<String> stringlist) {
        this.stringlist = stringlist;
    }
}

