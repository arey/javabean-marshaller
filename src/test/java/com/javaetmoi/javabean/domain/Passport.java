package com.javaetmoi.javabean.domain;


public class Passport {

    private int serialNum;

    private Customer owner;

    public Passport() {
    }

    public Passport(int serialNum, Customer owner) {
        this.serialNum = serialNum;
        this.owner = owner;
    }

    public int getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(int serialNum) {
        this.serialNum = serialNum;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }
}