package com.pujjr.business.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pujjr.business.dao.SysMenuMapper;
import com.pujjr.business.dao.SysRoleMenuMapper;
import com.pujjr.business.domain.SysMenu;
import com.pujjr.business.domain.SysRoleMenuKey;
import com.pujjr.business.vo.MenuTree;
import com.pujjr.business.vo.RoleMenu;

@Service
@Transactional(rollbackFor=Exception.class) 
public class SysMenuService {
	
	@Autowired
	private SysMenuMapper sysMenuDao;
	
	@Autowired
	private SysRoleMenuMapper sysRoleMenuDao;
	
	public void add(SysMenu record)
	{
		sysMenuDao.insert(record);
	}
	
	public void update(SysMenu record)
	{
		sysMenuDao.updateByPrimaryKey(record);
	}
	
	public void delete(String id)
	{
		sysMenuDao.deleteByPrimaryKey(id);
	}
	
	public SysMenu get(String id)
	{
		return sysMenuDao.selectByPrimaryKey(id);
	}
	
	public List<SysMenu> getList(String parentid)
	{
		return sysMenuDao.selectSubMenuList(parentid);
	
	}
	
	public MenuTree getMenuTreeByRoleId(String roleid,String parentmenuid) throws Exception
	{
		MenuTree tree=new MenuTree();
		SysMenu menu=sysMenuDao.selectByPrimaryKey(parentmenuid);
		if(menu==null)
		{
			throw new Exception("菜单不存在");
		}
		tree.setId(menu.getId());
		tree.setTitle(menu.getMenuname());
		tree.setRoutepath(menu.getRoutepath());
		tree.setIconclass(menu.getIconclass());
		tree.setParentid(menu.getParentid());
		SysRoleMenuKey key=new SysRoleMenuKey();
		key.setRoleid(roleid);
		key.setMenuid(menu.getId());
		if(sysRoleMenuDao.selectByPrimaryKey(key)!=null)
		{
			tree.setChecked(true);
		}
		else
		{
			tree.setChecked(false);
		}
		tree.setChildren(generateMenuTreeByRoleId(roleid, parentmenuid));
		return tree;
	}
	
	public List<MenuTree> generateMenuTreeByRoleId(String roleid,String parentmenuid)
	{
		List<MenuTree> treeNodeList=new ArrayList<MenuTree>();
		
		List<SysMenu> menuList=sysMenuDao.selectSubMenuList(parentmenuid);
		for(int i=0;i<menuList.size();i++)
		{
			SysMenu menu=menuList.get(i);
			MenuTree treeNode=new MenuTree();
			treeNode.setId(menu.getId());
			treeNode.setIconclass(menu.getIconclass());
			treeNode.setTitle(menu.getMenuname());
			treeNode.setParentid(menu.getParentid());
			//判断是否属于此角色菜单
			SysRoleMenuKey key=new SysRoleMenuKey();
			key.setRoleid(roleid);
			key.setMenuid(menu.getId());
			if(sysRoleMenuDao.selectByPrimaryKey(key)!=null)
			{
				treeNode.setChecked(true);
			}
			else
			{
				treeNode.setChecked(false);
			}
			//判断此节点是否有子节点
			List<MenuTree> subTreeNodeList=generateMenuTreeByRoleId(roleid,treeNode.getId());
			if(subTreeNodeList.size()!=0)
			{
				treeNode.setChildren(subTreeNodeList);
			}
			treeNodeList.add(treeNode);
		}
		return treeNodeList;
		
	}
	
	public void saveRoleMenuTree(RoleMenu record)
	{
		String roleid=record.getId();
		MenuTree menuTree=record.getMenuTree();
		List<MenuTree> list=getCheckMenuList(menuTree);
		sysRoleMenuDao.deleteByRoleId(roleid);
		for(int i=0;i<list.size();i++)
		{
			SysRoleMenuKey item=new SysRoleMenuKey();
			item.setRoleid(roleid);
			item.setMenuid(list.get(i).getId());
			sysRoleMenuDao.insert(item);
		}
	}
	
	private List<MenuTree> getCheckMenuList(MenuTree menuTree)
	{
		List<MenuTree> list=new ArrayList<MenuTree>();
		if(menuTree.isChecked())
			list.add(menuTree);
		List<MenuTree> children=menuTree.getChildren();
		for(int i=0;i<children.size();i++)
		{
			list.addAll(getCheckMenuList(children.get(i)));
		}
		return list;
	}
	
	public List<MenuTree>  generateSysMenuTreeByAccountId(String accountid,String parentid)
	{
		List<SysMenu>  list=sysMenuDao.selectSubMenuListByAccountIdAndParentMenuId(accountid, parentid);
		List<MenuTree> treeList=new ArrayList<MenuTree>();
		for(int i=0;i<list.size();i++)
		{
			SysMenu menu=list.get(i);
			MenuTree treeNode=new MenuTree();
			treeNode.setId(menu.getId());
			treeNode.setIconclass(menu.getIconclass());
			treeNode.setTitle(menu.getMenuname());
			treeNode.setParentid(menu.getParentid());
			treeNode.setRoutepath(menu.getRoutepath());
			List<SysMenu> child=sysMenuDao.selectSubMenuList(menu.getId());
			if(child.size()!=0)
			{
				treeNode.setChildren(this.generateSysMenuTreeByAccountId(accountid, menu.getId()));
			}
			treeList.add(treeNode);
		}
		return treeList;
	}
	
	public List<SysMenu> selectAuthMenusByAccountId(String accountid)
	{
		return sysMenuDao.selectAuthMenusByAccountId(accountid);
	}
	
	
}
