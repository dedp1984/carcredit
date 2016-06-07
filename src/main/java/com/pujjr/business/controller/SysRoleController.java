package com.pujjr.business.controller;

import java.io.UnsupportedEncodingException;
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
import com.pujjr.business.domain.SysBranch;
import com.pujjr.business.domain.SysMenu;
import com.pujjr.business.domain.SysRole;
import com.pujjr.business.service.SysBranchService;
import com.pujjr.business.service.SysMenuService;
import com.pujjr.business.service.SysRoleService;
import com.pujjr.business.vo.PageVo;
import com.pujjr.common.Utils;

@Controller
@RequestMapping(value="/api/sysroles")
public class SysRoleController extends BaseController 
{
	@Autowired
	private SysRoleService sysRoleService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@ResponseBody
	public SysRole get(@PathVariable String id)
	{
		return sysRoleService.get(id);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public void update(@RequestBody SysRole record)
	{
		sysRoleService.update(record);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public void add(@RequestBody SysRole record)
	{
		record.setId(Utils.get16UUID());
		sysRoleService.add(record);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public PageVo getList(String curPage,String pageSize) throws UnsupportedEncodingException
	{	
		PageHelper.startPage(Integer.parseInt(curPage), Integer.parseInt(pageSize),true);
		List<SysRole> list=sysRoleService.getList();
		PageVo page=new PageVo();
		page.setTotalItem(((Page)list).getTotal());
		page.setData(list);
		return page;
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable  String id)
	{
		sysRoleService.delete(id);
	}
}
