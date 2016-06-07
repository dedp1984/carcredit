package com.pujjr.business.controller;

import java.io.UnsupportedEncodingException;
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
import com.pujjr.business.domain.SysBranch;
import com.pujjr.business.domain.SysMenu;
import com.pujjr.business.service.SysBranchService;
import com.pujjr.business.service.SysMenuService;
import com.pujjr.business.vo.MenuTree;
import com.pujjr.business.vo.PageVo;
import com.pujjr.business.vo.RoleMenu;
import com.pujjr.common.Utils;

@Controller
@RequestMapping(value="/api/menus")
public class SysMenuController extends BaseController 
{
	@Autowired
	private SysMenuService sysMenuService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@ResponseBody
	public SysMenu get(@PathVariable String id)
	{
		return sysMenuService.get(id);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public void update(@RequestBody SysMenu record)
	{
		sysMenuService.update(record);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public void add(@RequestBody SysMenu record)
	{
		record.setId(Utils.get16UUID());
		sysMenuService.add(record);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public PageVo getList(String curPage,String pageSize,String parentid) throws UnsupportedEncodingException
	{	
		PageHelper.startPage(Integer.parseInt(curPage), Integer.parseInt(pageSize),true);
		List<SysMenu> list=sysMenuService.getList(parentid);
		PageVo page=new PageVo();
		page.setTotalItem(((Page)list).getTotal());
		page.setData(list);
		return page;
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable  String id)
	{
		sysMenuService.delete(id);
	}
	
	@RequestMapping(value="/rolemenu/{roleid}",method=RequestMethod.GET)
	@ResponseBody
	public RoleMenu getRoleMenuByRoleId(@PathVariable String roleid) throws Exception
	{
		MenuTree tree=sysMenuService.getMenuTreeByRoleId(roleid, "0");
		RoleMenu roleMenu=new RoleMenu();
		roleMenu.setId(roleid);
		roleMenu.setMenuTree(tree);
		return roleMenu;
	}
	
	@RequestMapping(value="/rolemenu/{roleid}",method=RequestMethod.PUT)
	@ResponseBody
	public void saveRoleMenuByRoleId(@RequestBody RoleMenu roleMenu) throws Exception
	{
		sysMenuService.saveRoleMenuTree(roleMenu);
	}
	

}
