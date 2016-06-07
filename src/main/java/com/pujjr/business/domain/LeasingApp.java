package com.pujjr.business.domain;

import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class LeasingApp {
    
	private String id;

    private String producttype;

    private String productname;

    private String jxsid;

    private Double lcj;

    private Double gpsfee;

    private Double gzs;

    private Double fwf;

    private Double bxf;

    private Double ybf;

    private Double ghf;

    private Double jzf;

    private Double rzsxf;

    private Boolean clyh;

    private String ppcx;

    private String clys;

    private String scs;

    private String cjh;

    private String fdjh;

    private Double pgj;

    private Double bzj;

    private Double sfbl;

    private Double rzje;

    private Integer rzqx;

    private String name;

    private String idtype;

    private String idno;

    private String mobile;

    private String qq;

    private String weixin;

    private String xl;

    private String hyzk;

    private String xxxdz;

    private String zsqs;

    private String tzr;

    private String hjss;

    private String jtcy;

    private String lbdsc;

    private String fcdz1;

    private String fczt1;

    private String fcdz2;

    private String fczt2;

    private Integer ccsl;

    private String ccpp;

    private String cczt;

    private String czr1dwmc;

    private String czr1dwlx;

    private String czr1sshy;

    private String czr1dwdh;

    private String czr1zj;

    private String czr1zwmc;

    private String czr1dwdz;

    private Double czr1ysr;

    private Double czr1nsr;

    private String poname;

    private String poidtype;

    private String poidno;

    private String pomobile;

    private String poqq;

    private String poweixin;

    private String podwmc;

    private String podwlx;

    private String posshy;

    private String podwdh;

    private String pozj;

    private String pozwmc;

    private String podwdz;

    private Double poysr;

    private Double ponsr;

    private String czr2lx;

    private String czr2name;

    private String czr2idtype;

    private String czr2idno;

    private String czr2mobile;

    private String czr2qq;

    private String czr2weixin;

    private String czr2brgx;

    private String czr2dwmc;

    private String czr2dwlx;

    private String czr2sshy;

    private String czr2dwdh;

    private String czr2zj;

    private String czr2zwmc;

    private String czr2dwdz;

    private Double czr2ysr;

    private Double czr2nsr;

    private String lxr1name;

    private String lxr1mobile;

    private String lxr1gx;

    private String lxr1dz;

    private String lxr1sfzdgc;

    private String lxr2name;

    private String lxr2mobile;

    private String lxr2gx;

    private String lxr2dz;

    private String lxr2sfzdgc;

    private Double jtfzyhqkje;

    private Double jtfzyhdqkhk;

    private Double jtfzp2pqkje;

    private Double jtfzp2pqkyhk;

    private Double jtfzdbqkje;

    private Double jtfzdbqkyhk;

    private Double jtfzxykqkje;

    private Double jtfzxykqkyhk;

    private Double jtfzqqqkje;

    private Double jtfzqqqkyhk;

    private Double jtfzpyqkje;

    private Double jtfzpyqkyhk;

    private Double jtfzzcgsqkje;

    private Double jtfzzcgsqkyhk;

    private Double jtfzze;

    private Double jtfzzhke;

    private String sqdzt;

    private String sqfqr;

    private Timestamp sqfqsj;

    private String refundacctno;

    private String refundbankno;

    private String contractid;

    private String cph;

    private String confirmdate;

    private Double reserver1;

    private Double reserver2;

    private Double reserver3;

    private Double reserver4;

    private String reserver5;

    private String reserver6;

    private String reserver7;

    private Boolean reserver8;

    private String reserver9;

    private String reserver10;
    
    private SysBranch sysBranch;

	public SysBranch getSysBranch() {
		return sysBranch;
	}

	public void setSysBranch(SysBranch sysBranch) {
		this.sysBranch = sysBranch;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProducttype() {
        return producttype;
    }

    public void setProducttype(String producttype) {
        this.producttype = producttype;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getJxsid() {
        return jxsid;
    }

    public void setJxsid(String jxsid) {
        this.jxsid = jxsid;
    }

    public Double getLcj() {
        return lcj;
    }

    public void setLcj(Double lcj) {
        this.lcj = lcj;
    }

    public Double getGpsfee() {
        return gpsfee;
    }

    public void setGpsfee(Double gpsfee) {
        this.gpsfee = gpsfee;
    }

    public Double getGzs() {
        return gzs;
    }

    public void setGzs(Double gzs) {
        this.gzs = gzs;
    }

    public Double getFwf() {
        return fwf;
    }

    public void setFwf(Double fwf) {
        this.fwf = fwf;
    }

    public Double getBxf() {
        return bxf;
    }

    public void setBxf(Double bxf) {
        this.bxf = bxf;
    }

    public Double getYbf() {
        return ybf;
    }

    public void setYbf(Double ybf) {
        this.ybf = ybf;
    }

    public Double getGhf() {
        return ghf;
    }

    public void setGhf(Double ghf) {
        this.ghf = ghf;
    }

    public Double getJzf() {
        return jzf;
    }

    public void setJzf(Double jzf) {
        this.jzf = jzf;
    }

    public Double getRzsxf() {
        return rzsxf;
    }

    public void setRzsxf(Double rzsxf) {
        this.rzsxf = rzsxf;
    }

    public Boolean getClyh() {
        return clyh;
    }

    public void setClyh(Boolean clyh) {
        this.clyh = clyh;
    }

    public String getPpcx() {
        return ppcx;
    }

    public void setPpcx(String ppcx) {
        this.ppcx = ppcx;
    }

    public String getClys() {
        return clys;
    }

    public void setClys(String clys) {
        this.clys = clys;
    }

    public String getScs() {
        return scs;
    }

    public void setScs(String scs) {
        this.scs = scs;
    }

    public String getCjh() {
        return cjh;
    }

    public void setCjh(String cjh) {
        this.cjh = cjh;
    }

    public String getFdjh() {
        return fdjh;
    }

    public void setFdjh(String fdjh) {
        this.fdjh = fdjh;
    }

    public Double getPgj() {
        return pgj;
    }

    public void setPgj(Double pgj) {
        this.pgj = pgj;
    }

    public Double getBzj() {
        return bzj;
    }

    public void setBzj(Double bzj) {
        this.bzj = bzj;
    }

    public Double getSfbl() {
        return sfbl;
    }

    public void setSfbl(Double sfbl) {
        this.sfbl = sfbl;
    }

    public Double getRzje() {
        return rzje;
    }

    public void setRzje(Double rzje) {
        this.rzje = rzje;
    }

    public Integer getRzqx() {
        return rzqx;
    }

    public void setRzqx(Integer rzqx) {
        this.rzqx = rzqx;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdtype() {
        return idtype;
    }

    public void setIdtype(String idtype) {
        this.idtype = idtype;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getXl() {
        return xl;
    }

    public void setXl(String xl) {
        this.xl = xl;
    }

    public String getHyzk() {
        return hyzk;
    }

    public void setHyzk(String hyzk) {
        this.hyzk = hyzk;
    }

    public String getXxxdz() {
        return xxxdz;
    }

    public void setXxxdz(String xxxdz) {
        this.xxxdz = xxxdz;
    }

    public String getZsqs() {
        return zsqs;
    }

    public void setZsqs(String zsqs) {
        this.zsqs = zsqs;
    }

    public String getTzr() {
        return tzr;
    }

    public void setTzr(String tzr) {
        this.tzr = tzr;
    }

    public String getHjss() {
        return hjss;
    }

    public void setHjss(String hjss) {
        this.hjss = hjss;
    }

    public String getJtcy() {
        return jtcy;
    }

    public void setJtcy(String jtcy) {
        this.jtcy = jtcy;
    }

    public String getLbdsc() {
        return lbdsc;
    }

    public void setLbdsc(String lbdsc) {
        this.lbdsc = lbdsc;
    }

    public String getFcdz1() {
        return fcdz1;
    }

    public void setFcdz1(String fcdz1) {
        this.fcdz1 = fcdz1;
    }

    public String getFczt1() {
        return fczt1;
    }

    public void setFczt1(String fczt1) {
        this.fczt1 = fczt1;
    }

    public String getFcdz2() {
        return fcdz2;
    }

    public void setFcdz2(String fcdz2) {
        this.fcdz2 = fcdz2;
    }

    public String getFczt2() {
        return fczt2;
    }

    public void setFczt2(String fczt2) {
        this.fczt2 = fczt2;
    }

    public Integer getCcsl() {
        return ccsl;
    }

    public void setCcsl(Integer ccsl) {
        this.ccsl = ccsl;
    }

    public String getCcpp() {
        return ccpp;
    }

    public void setCcpp(String ccpp) {
        this.ccpp = ccpp;
    }

    public String getCczt() {
        return cczt;
    }

    public void setCczt(String cczt) {
        this.cczt = cczt;
    }

    public String getCzr1dwmc() {
        return czr1dwmc;
    }

    public void setCzr1dwmc(String czr1dwmc) {
        this.czr1dwmc = czr1dwmc;
    }

    public String getCzr1dwlx() {
        return czr1dwlx;
    }

    public void setCzr1dwlx(String czr1dwlx) {
        this.czr1dwlx = czr1dwlx;
    }

    public String getCzr1sshy() {
        return czr1sshy;
    }

    public void setCzr1sshy(String czr1sshy) {
        this.czr1sshy = czr1sshy;
    }

    public String getCzr1dwdh() {
        return czr1dwdh;
    }

    public void setCzr1dwdh(String czr1dwdh) {
        this.czr1dwdh = czr1dwdh;
    }

    public String getCzr1zj() {
        return czr1zj;
    }

    public void setCzr1zj(String czr1zj) {
        this.czr1zj = czr1zj;
    }

    public String getCzr1zwmc() {
        return czr1zwmc;
    }

    public void setCzr1zwmc(String czr1zwmc) {
        this.czr1zwmc = czr1zwmc;
    }

    public String getCzr1dwdz() {
        return czr1dwdz;
    }

    public void setCzr1dwdz(String czr1dwdz) {
        this.czr1dwdz = czr1dwdz;
    }

    public Double getCzr1ysr() {
        return czr1ysr;
    }

    public void setCzr1ysr(Double czr1ysr) {
        this.czr1ysr = czr1ysr;
    }

    public Double getCzr1nsr() {
        return czr1nsr;
    }

    public void setCzr1nsr(Double czr1nsr) {
        this.czr1nsr = czr1nsr;
    }

    public String getPoname() {
        return poname;
    }

    public void setPoname(String poname) {
        this.poname = poname;
    }

    public String getPoidtype() {
        return poidtype;
    }

    public void setPoidtype(String poidtype) {
        this.poidtype = poidtype;
    }

    public String getPoidno() {
        return poidno;
    }

    public void setPoidno(String poidno) {
        this.poidno = poidno;
    }

    public String getPomobile() {
        return pomobile;
    }

    public void setPomobile(String pomobile) {
        this.pomobile = pomobile;
    }

    public String getPoqq() {
        return poqq;
    }

    public void setPoqq(String poqq) {
        this.poqq = poqq;
    }

    public String getPoweixin() {
        return poweixin;
    }

    public void setPoweixin(String poweixin) {
        this.poweixin = poweixin;
    }

    public String getPodwmc() {
        return podwmc;
    }

    public void setPodwmc(String podwmc) {
        this.podwmc = podwmc;
    }

    public String getPodwlx() {
        return podwlx;
    }

    public void setPodwlx(String podwlx) {
        this.podwlx = podwlx;
    }

    public String getPosshy() {
        return posshy;
    }

    public void setPosshy(String posshy) {
        this.posshy = posshy;
    }

    public String getPodwdh() {
        return podwdh;
    }

    public void setPodwdh(String podwdh) {
        this.podwdh = podwdh;
    }

    public String getPozj() {
        return pozj;
    }

    public void setPozj(String pozj) {
        this.pozj = pozj;
    }

    public String getPozwmc() {
        return pozwmc;
    }

    public void setPozwmc(String pozwmc) {
        this.pozwmc = pozwmc;
    }

    public String getPodwdz() {
        return podwdz;
    }

    public void setPodwdz(String podwdz) {
        this.podwdz = podwdz;
    }

    public Double getPoysr() {
        return poysr;
    }

    public void setPoysr(Double poysr) {
        this.poysr = poysr;
    }

    public Double getPonsr() {
        return ponsr;
    }

    public void setPonsr(Double ponsr) {
        this.ponsr = ponsr;
    }

    public String getCzr2lx() {
        return czr2lx;
    }

    public void setCzr2lx(String czr2lx) {
        this.czr2lx = czr2lx;
    }

    public String getCzr2name() {
        return czr2name;
    }

    public void setCzr2name(String czr2name) {
        this.czr2name = czr2name;
    }

    public String getCzr2idtype() {
        return czr2idtype;
    }

    public void setCzr2idtype(String czr2idtype) {
        this.czr2idtype = czr2idtype;
    }

    public String getCzr2idno() {
        return czr2idno;
    }

    public void setCzr2idno(String czr2idno) {
        this.czr2idno = czr2idno;
    }

    public String getCzr2mobile() {
        return czr2mobile;
    }

    public void setCzr2mobile(String czr2mobile) {
        this.czr2mobile = czr2mobile;
    }

    public String getCzr2qq() {
        return czr2qq;
    }

    public void setCzr2qq(String czr2qq) {
        this.czr2qq = czr2qq;
    }

    public String getCzr2weixin() {
        return czr2weixin;
    }

    public void setCzr2weixin(String czr2weixin) {
        this.czr2weixin = czr2weixin;
    }

    public String getCzr2brgx() {
        return czr2brgx;
    }

    public void setCzr2brgx(String czr2brgx) {
        this.czr2brgx = czr2brgx;
    }

    public String getCzr2dwmc() {
        return czr2dwmc;
    }

    public void setCzr2dwmc(String czr2dwmc) {
        this.czr2dwmc = czr2dwmc;
    }

    public String getCzr2dwlx() {
        return czr2dwlx;
    }

    public void setCzr2dwlx(String czr2dwlx) {
        this.czr2dwlx = czr2dwlx;
    }

    public String getCzr2sshy() {
        return czr2sshy;
    }

    public void setCzr2sshy(String czr2sshy) {
        this.czr2sshy = czr2sshy;
    }

    public String getCzr2dwdh() {
        return czr2dwdh;
    }

    public void setCzr2dwdh(String czr2dwdh) {
        this.czr2dwdh = czr2dwdh;
    }

    public String getCzr2zj() {
        return czr2zj;
    }

    public void setCzr2zj(String czr2zj) {
        this.czr2zj = czr2zj;
    }

    public String getCzr2zwmc() {
        return czr2zwmc;
    }

    public void setCzr2zwmc(String czr2zwmc) {
        this.czr2zwmc = czr2zwmc;
    }

    public String getCzr2dwdz() {
        return czr2dwdz;
    }

    public void setCzr2dwdz(String czr2dwdz) {
        this.czr2dwdz = czr2dwdz;
    }

    public Double getCzr2ysr() {
        return czr2ysr;
    }

    public void setCzr2ysr(Double czr2ysr) {
        this.czr2ysr = czr2ysr;
    }

    public Double getCzr2nsr() {
        return czr2nsr;
    }

    public void setCzr2nsr(Double czr2nsr) {
        this.czr2nsr = czr2nsr;
    }

    public String getLxr1name() {
        return lxr1name;
    }

    public void setLxr1name(String lxr1name) {
        this.lxr1name = lxr1name;
    }

    public String getLxr1mobile() {
        return lxr1mobile;
    }

    public void setLxr1mobile(String lxr1mobile) {
        this.lxr1mobile = lxr1mobile;
    }

    public String getLxr1gx() {
        return lxr1gx;
    }

    public void setLxr1gx(String lxr1gx) {
        this.lxr1gx = lxr1gx;
    }

    public String getLxr1dz() {
        return lxr1dz;
    }

    public void setLxr1dz(String lxr1dz) {
        this.lxr1dz = lxr1dz;
    }

    public String getLxr1sfzdgc() {
        return lxr1sfzdgc;
    }

    public void setLxr1sfzdgc(String lxr1sfzdgc) {
        this.lxr1sfzdgc = lxr1sfzdgc;
    }

    public String getLxr2name() {
        return lxr2name;
    }

    public void setLxr2name(String lxr2name) {
        this.lxr2name = lxr2name;
    }

    public String getLxr2mobile() {
        return lxr2mobile;
    }

    public void setLxr2mobile(String lxr2mobile) {
        this.lxr2mobile = lxr2mobile;
    }

    public String getLxr2gx() {
        return lxr2gx;
    }

    public void setLxr2gx(String lxr2gx) {
        this.lxr2gx = lxr2gx;
    }

    public String getLxr2dz() {
        return lxr2dz;
    }

    public void setLxr2dz(String lxr2dz) {
        this.lxr2dz = lxr2dz;
    }

    public String getLxr2sfzdgc() {
        return lxr2sfzdgc;
    }

    public void setLxr2sfzdgc(String lxr2sfzdgc) {
        this.lxr2sfzdgc = lxr2sfzdgc;
    }

    public Double getJtfzyhqkje() {
        return jtfzyhqkje;
    }

    public void setJtfzyhqkje(Double jtfzyhqkje) {
        this.jtfzyhqkje = jtfzyhqkje;
    }

    public Double getJtfzyhdqkhk() {
        return jtfzyhdqkhk;
    }

    public void setJtfzyhdqkhk(Double jtfzyhdqkhk) {
        this.jtfzyhdqkhk = jtfzyhdqkhk;
    }

    public Double getJtfzp2pqkje() {
        return jtfzp2pqkje;
    }

    public void setJtfzp2pqkje(Double jtfzp2pqkje) {
        this.jtfzp2pqkje = jtfzp2pqkje;
    }

    public Double getJtfzp2pqkyhk() {
        return jtfzp2pqkyhk;
    }

    public void setJtfzp2pqkyhk(Double jtfzp2pqkyhk) {
        this.jtfzp2pqkyhk = jtfzp2pqkyhk;
    }

    public Double getJtfzdbqkje() {
        return jtfzdbqkje;
    }

    public void setJtfzdbqkje(Double jtfzdbqkje) {
        this.jtfzdbqkje = jtfzdbqkje;
    }

    public Double getJtfzdbqkyhk() {
        return jtfzdbqkyhk;
    }

    public void setJtfzdbqkyhk(Double jtfzdbqkyhk) {
        this.jtfzdbqkyhk = jtfzdbqkyhk;
    }

    public Double getJtfzxykqkje() {
        return jtfzxykqkje;
    }

    public void setJtfzxykqkje(Double jtfzxykqkje) {
        this.jtfzxykqkje = jtfzxykqkje;
    }

    public Double getJtfzxykqkyhk() {
        return jtfzxykqkyhk;
    }

    public void setJtfzxykqkyhk(Double jtfzxykqkyhk) {
        this.jtfzxykqkyhk = jtfzxykqkyhk;
    }

    public Double getJtfzqqqkje() {
        return jtfzqqqkje;
    }

    public void setJtfzqqqkje(Double jtfzqqqkje) {
        this.jtfzqqqkje = jtfzqqqkje;
    }

    public Double getJtfzqqqkyhk() {
        return jtfzqqqkyhk;
    }

    public void setJtfzqqqkyhk(Double jtfzqqqkyhk) {
        this.jtfzqqqkyhk = jtfzqqqkyhk;
    }

    public Double getJtfzpyqkje() {
        return jtfzpyqkje;
    }

    public void setJtfzpyqkje(Double jtfzpyqkje) {
        this.jtfzpyqkje = jtfzpyqkje;
    }

    public Double getJtfzpyqkyhk() {
        return jtfzpyqkyhk;
    }

    public void setJtfzpyqkyhk(Double jtfzpyqkyhk) {
        this.jtfzpyqkyhk = jtfzpyqkyhk;
    }

    public Double getJtfzzcgsqkje() {
        return jtfzzcgsqkje;
    }

    public void setJtfzzcgsqkje(Double jtfzzcgsqkje) {
        this.jtfzzcgsqkje = jtfzzcgsqkje;
    }

    public Double getJtfzzcgsqkyhk() {
        return jtfzzcgsqkyhk;
    }

    public void setJtfzzcgsqkyhk(Double jtfzzcgsqkyhk) {
        this.jtfzzcgsqkyhk = jtfzzcgsqkyhk;
    }

    public Double getJtfzze() {
        return jtfzze;
    }

    public void setJtfzze(Double jtfzze) {
        this.jtfzze = jtfzze;
    }

    public Double getJtfzzhke() {
        return jtfzzhke;
    }

    public void setJtfzzhke(Double jtfzzhke) {
        this.jtfzzhke = jtfzzhke;
    }

    public String getSqdzt() {
        return sqdzt;
    }

    public void setSqdzt(String sqdzt) {
        this.sqdzt = sqdzt;
    }

    public String getSqfqr() {
        return sqfqr;
    }

    public void setSqfqr(String sqfqr) {
        this.sqfqr = sqfqr;
    }

    public Timestamp getSqfqsj() {
        return sqfqsj;
    }

    public void setSqfqsj(Timestamp sqfqsj) {
        this.sqfqsj = sqfqsj;
    }

    public String getRefundacctno() {
        return refundacctno;
    }

    public void setRefundacctno(String refundacctno) {
        this.refundacctno = refundacctno;
    }

    public String getRefundbankno() {
        return refundbankno;
    }

    public void setRefundbankno(String refundbankno) {
        this.refundbankno = refundbankno;
    }

    public String getContractid() {
        return contractid;
    }

    public void setContractid(String contractid) {
        this.contractid = contractid;
    }

    public String getCph() {
        return cph;
    }

    public void setCph(String cph) {
        this.cph = cph;
    }

    public String getConfirmdate() {
        return confirmdate;
    }

    public void setConfirmdate(String confirmdate) {
        this.confirmdate = confirmdate;
    }

    public Double getReserver1() {
        return reserver1;
    }

    public void setReserver1(Double reserver1) {
        this.reserver1 = reserver1;
    }

    public Double getReserver2() {
        return reserver2;
    }

    public void setReserver2(Double reserver2) {
        this.reserver2 = reserver2;
    }

    public Double getReserver3() {
        return reserver3;
    }

    public void setReserver3(Double reserver3) {
        this.reserver3 = reserver3;
    }

    public Double getReserver4() {
        return reserver4;
    }

    public void setReserver4(Double reserver4) {
        this.reserver4 = reserver4;
    }

    public String getReserver5() {
        return reserver5;
    }

    public void setReserver5(String reserver5) {
        this.reserver5 = reserver5;
    }

    public String getReserver6() {
        return reserver6;
    }

    public void setReserver6(String reserver6) {
        this.reserver6 = reserver6;
    }

    public String getReserver7() {
        return reserver7;
    }

    public void setReserver7(String reserver7) {
        this.reserver7 = reserver7;
    }

    public Boolean getReserver8() {
        return reserver8;
    }

    public void setReserver8(Boolean reserver8) {
        this.reserver8 = reserver8;
    }

    public String getReserver9() {
        return reserver9;
    }

    public void setReserver9(String reserver9) {
        this.reserver9 = reserver9;
    }

    public String getReserver10() {
        return reserver10;
    }

    public void setReserver10(String reserver10) {
        this.reserver10 = reserver10;
    }
}