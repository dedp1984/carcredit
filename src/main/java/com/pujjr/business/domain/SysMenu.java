package com.pujjr.business.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class SysMenu {
    private String id;

    private String menuname;

    private String iconclass;

    private String routepath;

    private String parentid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public String getIconclass() {
        return iconclass;
    }

    public void setIconclass(String iconclass) {
        this.iconclass = iconclass;
    }

    public String getRoutepath() {
        return routepath;
    }

    public void setRoutepath(String routepath) {
        this.routepath = routepath;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }
}