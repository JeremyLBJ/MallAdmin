package com.lhd.sys.common;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class TreeNode {
	
	private Integer id ;
	@JsonProperty("parentId")
	private Integer pid ;
	private String title ;
	private String icon ;
	private String href ;
	private Boolean spread ;
	private List<TreeNode> children = new ArrayList<TreeNode>() ;
	
	private String checkArr = "0" ; // 0 代表不选中  1代表选中
	
	/**
	 * 
	 * 首页 导航菜单
	 * 
	 * 
	 */
	public TreeNode(Integer id, Integer pid, String title, String icon, String href, Boolean spread) {
		super();
		this.id = id;
		this.pid = pid;
		this.title = title;
		this.icon = icon;
		this.href = href;
		this.spread = spread;
	}
	
	/**
	 * dtree返回数据格式
	 * 
	 * @return
	 */
	public TreeNode(Integer id, Integer pid, String title, Boolean spread) {
		super();
		this.id = id;
		this.pid = pid;
		this.title = title;
		this.spread = spread;
	}
	
	/**
	 * 
	 * dtree复选树
	 * 
	 * @param id
	 * @param pid
	 * @param title
	 * @param spread
	 * @param checkArr
	 */
	
	public TreeNode(Integer id, Integer pid, String title, Boolean spread, String checkArr) {
		super();
		this.id = id;
		this.pid = pid;
		this.title = title;
		this.spread = spread;
		this.checkArr = checkArr;
	}

	
	public List<TreeNode> getChildren() {
		return children;
	}
	

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getPid() {
		return pid;
	}
	
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getIcon() {
		return icon;
	}
	
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public String getHref() {
		return href;
	}
	
	public void setHref(String href) {
		this.href = href;
	}
	
	public Boolean getSpread() {
		return spread;
	}
	
	public void setSpread(Boolean spread) {
		this.spread = spread;
	}

	public String getCheckArr() {
		return checkArr;
	}

	public void setCheckArr(String checkArr) {
		this.checkArr = checkArr;
	}

	@Override
	public String toString() {
		return "TreeNode [id=" + id + ", pid=" + pid + ", title=" + title + ", icon=" + icon + ", href=" + href
				+ ", spread=" + spread + ", children=" + children + "]";
	}
	

}
