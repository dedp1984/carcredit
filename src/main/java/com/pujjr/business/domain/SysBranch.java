package com.pujjr.business.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class SysBranch {
    private String id;

    private String branchname;

    private String areano;

    private String openbankno;

    private String acctname;

    private String openbankname;

    private String branchtel;

    private String branchaddress;

    private String branchalias;

    private String linkmain;

    private String linktel;

    private String zipcode;

    private String manager;

    private String managertel;

    private String createid;

    private Date createdate;

    private String reserver1;

    private String reserver2;
    
    private List<GpsLvl> gpslvl0t4s;
    
    private List<GpsLvl> gpslvl4t6s;
    
    private List<GpsLvl> gpslvl6s;

    public List<GpsLvl> getGpslvl0t4s() {
		return gpslvl0t4s;
	}

	public void setGpslvl0t4s(List<GpsLvl> gpslvl0t4s) {
		this.gpslvl0t4s = gpslvl0t4s;
	}

	public List<GpsLvl> getGpslvl4t6s() {
		return gpslvl4t6s;
	}

	public void setGpslvl4t6s(List<GpsLvl> gpslvl4t6s) {
		this.gpslvl4t6s = gpslvl4t6s;
	}

	public List<GpsLvl> getGpslvl6s() {
		return gpslvl6s;
	}

	public void setGpslvl6s(List<GpsLvl> gpslvl6s) {
		this.gpslvl6s = gpslvl6s;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBranchname() {
        return branchname;
    }

    public void setBranchname(String branchname) {
        this.branchname = branchname;
    }

    public String getAreano() {
        return areano;
    }

    public void setAreano(String areano) {
        this.areano = areano;
    }

    public String getOpenbankno() {
        return openbankno;
    }

    public void setOpenbankno(String openbankno) {
        this.openbankno = openbankno;
    }

    public String getAcctname() {
        return acctname;
    }

    public void setAcctname(String acctname) {
        this.acctname = acctname;
    }

    public String getOpenbankname() {
        return openbankname;
    }

    public void setOpenbankname(String openbankname) {
        this.openbankname = openbankname;
    }

    public String getBranchtel() {
        return branchtel;
    }

    public void setBranchtel(String branchtel) {
        this.branchtel = branchtel;
    }

    public String getBranchaddress() {
        return branchaddress;
    }

    public void setBranchaddress(String branchaddress) {
        this.branchaddress = branchaddress;
    }

    public String getBranchalias() {
        return branchalias;
    }

    public void setBranchalias(String branchalias) {
        this.branchalias = branchalias;
    }

    public String getLinkmain() {
        return linkmain;
    }

    public void setLinkmain(String linkmain) {
        this.linkmain = linkmain;
    }

    public String getLinktel() {
        return linktel;
    }

    public void setLinktel(String linktel) {
        this.linktel = linktel;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getManagertel() {
        return managertel;
    }

    public void setManagertel(String managertel) {
        this.managertel = managertel;
    }

    public String getCreateid() {
        return createid;
    }

    public void setCreateid(String createid) {
        this.createid = createid;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
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