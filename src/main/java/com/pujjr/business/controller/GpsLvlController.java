package com.pujjr.business.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pujjr.business.domain.GpsLvl;
import com.pujjr.business.service.GpsLvlService;

@Controller
@RequestMapping("/api/gpslvl")
public class GpsLvlController  extends BaseController
{
	@Autowired
	private GpsLvlService gpsLvlServ;
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public List<GpsLvl> queryGpsLvl(String lvlamt)
	{
		return gpsLvlServ.queryGpsLvlList(lvlamt);
	}
}
