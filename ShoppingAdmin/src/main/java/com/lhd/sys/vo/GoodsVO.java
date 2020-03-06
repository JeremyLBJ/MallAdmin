package com.lhd.sys.vo;

import java.io.Serializable;

import com.lhd.sys.entity.ClassificationofGoodsItem;


public class GoodsVO extends ClassificationofGoodsItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer page ;
	
	private Integer limit ;
	private String orderno ;
	
	
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
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	
	
}
