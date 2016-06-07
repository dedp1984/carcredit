package com.pujjr.business.controller;

import io.jsonwebtoken.Claims;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pujjr.business.domain.SysAccount;
import com.pujjr.business.domain.SysBranch;
import com.pujjr.business.service.SysAccountService;
import com.pujjr.business.service.SysBranchService;
import com.pujjr.business.vo.PageVo;

@Controller
@RequestMapping(value="/api/branchs")
public class SysBranchController extends BaseController 
{
	@Autowired
	private SysBranchService sysBranchService;
	@Autowired
	private SysAccountService sysAccountService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@ResponseBody
	public SysBranch get(@PathVariable String id)
	{
		return sysBranchService.get(id);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public void update(@RequestBody SysBranch record)
	{
		sysBranchService.update(record);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public void add(@RequestBody SysBranch record,HttpServletRequest request)
	{
		Claims claims=(Claims)request.getAttribute("claims");
		String userid=claims.getSubject();
		SysAccount sysAccount=sysAccountService.get(userid);
		record.setCreateid(sysAccount.getId());
		record.setCreatedate(new Date());
		sysBranchService.add(record);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public PageVo getList(String curPage,String pageSize) throws UnsupportedEncodingException
	{
		PageHelper.startPage(Integer.parseInt(curPage), Integer.parseInt(pageSize),true);
		List<SysBranch> list=sysBranchService.getList();
		PageVo page=new PageVo();
		page.setTotalItem(((Page)list).getTotal());
		page.setData(list);
		return page;
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable  String id)
	{
		sysBranchService.delete(id);
	}
}
