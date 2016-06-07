package com.pujjr.business.controller;

import javax.servlet.http.HttpServletRequest;

import io.jsonwebtoken.Claims;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pujjr.business.domain.LeasingCheck;
import com.pujjr.business.domain.SysAccount;
import com.pujjr.business.service.LeasingCheckService;
import com.pujjr.business.service.SysAccountService;
import com.pujjr.business.vo.AppData;

@Controller
@RequestMapping(value="/api/leasingchecks")
public class LeasingCheckController extends BaseController 
{
	@Autowired
	private LeasingCheckService leasingCheckServ;
	@Autowired
	private SysAccountService sysAccountService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@ResponseBody
	public LeasingCheck get(@PathVariable String id)
	{
		return leasingCheckServ.get(id);
	}
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public void update(@RequestBody LeasingCheck record)
	{
		leasingCheckServ.update(record);
	}
	@RequestMapping(value="saveCheck",method=RequestMethod.POST)
	@ResponseBody
	public void saveCheck(@RequestBody AppData data,HttpServletRequest request )
	{
		/*获取用户信息*/
		Claims claims=(Claims)request.getAttribute("claims");
		String userid=claims.getSubject();
		SysAccount checkAccount=sysAccountService.get(userid);
		leasingCheckServ.saveCheckData(data, checkAccount);
	}

	
}
