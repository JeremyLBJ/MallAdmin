package com.lhd.sys.vo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.lhd.sys.entity.SysNotice;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper=false)
public class NoticeVO extends SysNotice implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="页数",name="page")
	private Integer page ;
	
	@ApiModelProperty(value="行数",name="limit")
	private Integer limit ;
	
	@ApiModelProperty(value="批量删除参数",name="ids")
	private Integer [] ids ;
	
	@ApiModelProperty(value="开始时间",name="startTime")
	 @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date startTime ;
	
	
	@ApiModelProperty(value="结束时间",name="endTime")
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
