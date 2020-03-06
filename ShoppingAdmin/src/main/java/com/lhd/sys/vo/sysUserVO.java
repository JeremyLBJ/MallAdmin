package com.lhd.sys.vo;

import java.io.Serializable;

import com.lhd.sys.entity.SysUser;

public class sysUserVO extends SysUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer page ;
	private Integer limit ;
	
	
	
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	
	

}
