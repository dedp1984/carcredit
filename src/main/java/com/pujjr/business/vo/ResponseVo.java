package com.pujjr.business.vo;

public class ResponseVo {
	
	private boolean successResponse;
	
	private String message;
	
	private Object data;

	public boolean isSuccessResponse() {
		return successResponse;
	}

	public void setSuccessResponse(boolean successResponse) {
		this.successResponse = successResponse;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
}
