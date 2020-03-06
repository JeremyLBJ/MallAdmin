package com.lhd.sys.vo;

import java.io.Serializable;

import com.lhd.sys.entity.SysRole;

public class SysRoleVO extends SysRole implements Serializable{

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



	@Override
	public String toString() {
		return "SysRoleVO [page=" + page + ", limit=" + limit + "]";
	}
	
	

}
