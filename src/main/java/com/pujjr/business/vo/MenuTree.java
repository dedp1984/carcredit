package com.pujjr.business.vo;

import java.util.ArrayList;
import java.util.List;

public class MenuTree 
{
	private  String id;
	private  String title;
	private  String routepath;
	private  String iconclass;
	private  boolean checked;
	private  boolean $$expanded=true;
	private  String  parentid;
	private  List<MenuTree> children=new ArrayList<MenuTree>();
	
	
	public boolean is$$expanded() {
		return $$expanded;
	}
	public void set$$expanded(boolean $$expanded) {
		this.$$expanded = $$expanded;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRoutepath() {
		return routepath;
	}
	public void setRoutepath(String routepath) {
		this.routepath = routepath;
	}
	public String getIconclass() {
		return iconclass;
	}
	public void setIconclass(String iconclass) {
		this.iconclass = iconclass;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public List<MenuTree> getChildren() {
		return children;
	}
	public void setChildren(List<MenuTree> children) {
		this.children = children;
	}
	
	public String toString()
	{
		return id+","+this.title+","+this.iconclass+","+this.routepath+","+this.parentid;
	}
	
	
}
