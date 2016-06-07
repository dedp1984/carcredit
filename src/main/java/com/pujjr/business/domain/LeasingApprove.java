package com.pujjr.business.domain;

import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class LeasingApprove {
    private String id;

    private String jyjg;

    private String fkfs;

    private String fkfjtj;

    private String shyyj;

    private String qxr1yj;

    private String qxr2yj;

    private String zjlyj;

    private String spr;

    private Timestamp spsj;

    private String reserver1;

    private String reserver2;

    private String reserver3;

    private String reserver4;

    private String reserver5;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJyjg() {
        return jyjg;
    }

    public void setJyjg(String jyjg) {
        this.jyjg = jyjg;
    }

    public String getFkfs() {
        return fkfs;
    }

    public void setFkfs(String fkfs) {
        this.fkfs = fkfs;
    }

    public String getFkfjtj() {
        return fkfjtj;
    }

    public void setFkfjtj(String fkfjtj) {
        this.fkfjtj = fkfjtj;
    }

    public String getShyyj() {
        return shyyj;
    }

    public void setShyyj(String shyyj) {
        this.shyyj = shyyj;
    }

    public String getQxr1yj() {
        return qxr1yj;
    }

    public void setQxr1yj(String qxr1yj) {
        this.qxr1yj = qxr1yj;
    }

    public String getQxr2yj() {
        return qxr2yj;
    }

    public void setQxr2yj(String qxr2yj) {
        this.qxr2yj = qxr2yj;
    }

    public String getZjlyj() {
        return zjlyj;
    }

    public void setZjlyj(String zjlyj) {
        this.zjlyj = zjlyj;
    }

    public String getSpr() {
        return spr;
    }

    public void setSpr(String spr) {
        this.spr = spr;
    }

    public Timestamp getSpsj() {
        return spsj;
    }

    public void setSpsj(Timestamp spsj) {
        this.spsj = spsj;
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

    public String getReserver3() {
        return reserver3;
    }

    public void setReserver3(String reserver3) {
        this.reserver3 = reserver3;
    }

    public String getReserver4() {
        return reserver4;
    }

    public void setReserver4(String reserver4) {
        this.reserver4 = reserver4;
    }

    public String getReserver5() {
        return reserver5;
    }

    public void setReserver5(String reserver5) {
        this.reserver5 = reserver5;
    }
}