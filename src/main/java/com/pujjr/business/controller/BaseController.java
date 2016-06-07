package com.pujjr.business.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pujjr.business.vo.ResponseVo;

@Controller
public class BaseController 
{
	@ExceptionHandler
	@ResponseBody
	public ResponseVo handlerException(Exception e)
	{
		e.printStackTrace();
		ResponseVo response=new ResponseVo();
		response.setSuccessResponse(false);
		response.setMessage(e.getMessage());
		return response;
	}
	
	/**
	 * 包装Controller的JSON返回结果为ResponseVo
	 * **/
	public ResponseVo wrapperJson(Object obj)
	{
		ResponseVo response=new ResponseVo();
		response.setSuccessResponse(true);
		response.setMessage("交易成功");
		response.setData(obj);
		return response;
	}

}
