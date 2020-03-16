package com.lhd.sys.vo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotations.TableField;
import com.lhd.sys.entity.SysLog;

public class SysLogVO extends SysLog implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer page ;
	
	private Integer limit ;
	
	private Integer [] ids ;
	
	@TableField(exist=false)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date startTime ;
	
	@TableField(exist=false)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date endTime ;

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

	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
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
	 
	 

}
