package com.pujjr.business.domain;

public class BranchGpsLvl {
    private String id;

    private String branchid;

    private Double minlvlamt;

    private Double maxlvlamt;

    private String lvls;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBranchid() {
        return branchid;
    }

    public void setBranchid(String branchid) {
        this.branchid = branchid;
    }

    public Double getMinlvlamt() {
        return minlvlamt;
    }

    public void setMinlvlamt(Double minlvlamt) {
        this.minlvlamt = minlvlamt;
    }

    public Double getMaxlvlamt() {
        return maxlvlamt;
    }

    public void setMaxlvlamt(Double maxlvlamt) {
        this.maxlvlamt = maxlvlamt;
    }

    public String getLvls() {
        return lvls;
    }

    public void setLvls(String lvls) {
        this.lvls = lvls;
    }
}