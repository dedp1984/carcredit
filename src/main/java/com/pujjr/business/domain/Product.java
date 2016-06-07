package com.pujjr.business.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Product {
    private String id;

    private String productname;

    private String productseq;

    private Double rate;

    private Integer periods;

    private Double charge;

    private Double yhk;

    private String reserver1;

    private String  reserver2;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductseq() {
        return productseq;
    }

    public void setProductseq(String productseq) {
        this.productseq = productseq;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Integer getPeriods() {
        return periods;
    }

    public void setPeriods(Integer periods) {
        this.periods = periods;
    }

    public Double getCharge() {
        return charge;
    }

    public void setCharge(Double charge) {
        this.charge = charge;
    }

    public Double getYhk() {
        return yhk;
    }

    public void setYhk(Double yhk) {
        this.yhk = yhk;
    }

    public String getReserver1() {
        return reserver1;
    }

    public void setReserver1(String reserver1) {
        this.reserver1 = reserver1;
    }

    public String getReserver2() {
        return reserver2;
    }

    public void setReserver2(String reserver2) {
        this.reserver2 = reserver2;
    }
}