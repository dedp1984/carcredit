package com.pujjr.business.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pujjr.business.domain.LeasingApp;
import com.pujjr.business.domain.LeasingApprove;
import com.pujjr.business.domain.LeasingCheck;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AppData {
	
	private LeasingApp app;
	
	private LeasingCheck check;
	
	private LeasingApprove approve;

	public LeasingApp getApp() {
		return app;
	}

	public void setApp(LeasingApp app) {
		this.app = app;
	}

	public LeasingCheck getCheck() {
		return check;
	}

	public void setCheck(LeasingCheck check) {
		this.check = check;
	}

	public LeasingApprove getApprove() {
		return approve;
	}

	public void setApprove(LeasingApprove approve) {
		this.approve = approve;
	}
	
	
	
	

}
