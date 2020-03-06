package com.lhd.sys.vo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.lhd.sys.entity.SysLogInfo;

public class LoginfoVO extends SysLogInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Integer page ;
	
	private Integer limit ;
	
	private Integer [] ids ;
	
	 @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date startTime ;
	
	 @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date endTime ;

	
	
	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
	}

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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "LoginfoVO [page=" + page + ", limit=" + limit + ", ids=" + Arrays.toString(ids) + ", startTime="
				+ startTime + ", endTime=" + endTime + "]";
	}

	
}
