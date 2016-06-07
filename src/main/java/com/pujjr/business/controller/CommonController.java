package com.pujjr.business.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pujjr.common.Utils;

@Controller
@RequestMapping("/api/common")
public class CommonController extends BaseController 
{

	@RequestMapping(value="systemDate", method=RequestMethod.GET)
	@ResponseBody
	public String getSystemDate()
	{
		return Utils.getCurrentTime("yyyMMdd");
	}
}
