package com.lhd.sys.vo;

import java.io.Serializable;

import com.lhd.sys.entity.SysPermission;

import io.swagger.annotations.ApiModelProperty;

public class PermissionVO extends SysPermission implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="页数",name="page",required=true)
	private Integer page ;
	
	@ApiModelProperty(value="行数",name="limit",required=true)
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
