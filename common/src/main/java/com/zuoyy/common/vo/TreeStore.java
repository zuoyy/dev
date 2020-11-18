package com.zuoyy.common.vo;

import java.io.Serializable;
import java.util.List;

public class TreeStore implements Serializable{

	private String id;

	private String pid;

	private String title;
	
	private String url;
	
	private Boolean open =true;
	
	private Boolean isParent;
	
	private String icon;

	private Integer sort;

	private Integer type;
	
	private List<TreeStore> children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public Boolean getParent() {
		return isParent;
	}

	public void setParent(Boolean parent) {
		isParent = parent;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<TreeStore> getChildren() {
		return children;
	}

	public void setChildren(List<TreeStore> children) {
		this.children = children;
	}


}
