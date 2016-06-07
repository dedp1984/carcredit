package com.pujjr.business.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pujjr.business.domain.LeasingApp;
import com.pujjr.business.domain.SysAccount;
import com.pujjr.business.domain.SysMenu;
import com.pujjr.business.service.KeyService;
import com.pujjr.business.service.SysAccountService;
import com.pujjr.business.service.SysMenuService;
import com.pujjr.business.vo.NewPasswdVo;
import com.pujjr.business.vo.PageVo;
import com.pujjr.common.Utils;

@Controller
@RequestMapping(value="/api/accounts")
public class SysAccountController  extends BaseController 
{
	@Autowired
	private SysAccountService sysAccountService;
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private KeyService keyService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@ResponseBody
	public SysAccount get(@PathVariable String id)
	{
		return sysAccountService.get(id);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public void update(@RequestBody SysAccount record)
	{
		sysAccountService.update(record);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public void add(@RequestBody SysAccount record) throws Exception
	{
		record.setPasswd(keyService.generateEncryptPasswd(record.getId(), "111111"));
		sysAccountService.add(record);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public PageVo getList(String curPage,String pageSize) throws UnsupportedEncodingException
	{
		PageHelper.startPage(Integer.parseInt(curPage), Integer.parseInt(pageSize),true);
		List<SysAccount> list=sysAccountService.getList();
		PageVo page=new PageVo();
		page.setTotalItem(((Page)list).getTotal());
		page.setData(list);
		return page;
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable  String id)
	{
		sysAccountService.delete(id);
	}
	@RequestMapping(value="/authmenu/{id}",method=RequestMethod.GET)
	@ResponseBody
	public List<SysMenu> getMenusByAccountId(@PathVariable String id)
	{
		return sysMenuService.selectAuthMenusByAccountId(id);
	}
	@RequestMapping(value="/modifyPasswd",method=RequestMethod.POST)
	@ResponseBody
	public void modifyPasswd(@RequestBody NewPasswdVo passwd) throws Exception
	{
		SysAccount sysAccount=sysAccountService.get(passwd.getAccountId());
		if(sysAccount==null)
		{
			throw new Exception("µÇÂ½ID²»´æÔÚ");
		}
		if(!keyService.verifyPasswd(passwd.getAccountId(), passwd.getOldPasswd(), sysAccount.getPasswd()))
		{
			throw new Exception("ÃÜÂë´íÎó");
		}
		String encNewPasswd=keyService.generateEncryptPasswd(passwd.getAccountId(), passwd.getNewPasswd());
		sysAccount.setPasswd(encNewPasswd);
		sysAccountService.onlyUpdateAccount(sysAccount);
	}
	@RequestMapping(value="/resetPasswd",method=RequestMethod.POST)
	@ResponseBody
	public void resetPasswd(@RequestBody String id) throws Exception
	{
		SysAccount sysAccount=sysAccountService.get(id);
		if(sysAccount==null)
		{
			throw new Exception("µÇÂ½ID²»´æÔÚ");
		}
		String encNewPasswd=keyService.generateEncryptPasswd(id, "111111");
		sysAccount.setPasswd(encNewPasswd);
		sysAccountService.onlyUpdateAccount(sysAccount);
	}
	
}
