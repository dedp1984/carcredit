package com.pujjr.business.domain;

public class Sequence {
    private String name;

    private Integer curval;

    private Integer increment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCurval() {
        return curval;
    }

    public void setCurval(Integer curval) {
        this.curval = curval;
    }

    public Integer getIncrement() {
        return increment;
    }

    public void setIncrement(Integer increment) {
        this.increment = increment;
    }
}