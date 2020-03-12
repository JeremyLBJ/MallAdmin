package com.lhd.sys.entity;

import java.util.Date;

public class AopEntity {
	
	//描述
	private  String operation ;
	
	//方法
	private String method ;
	
	//参数
	private String Params ;
	
	//ip
	private String ip ;
	
	//登录名
	private String loginname ;
	
	//时间
	private long time ;
	
	//创建时间
	private Date createDate ;

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getParams() {
		return Params;
	}

	public void setParams(String params) {
		Params = params;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "AopEntity [operation=" + operation + ", method=" + method + ", Params=" + Params + ", ip=" + ip
				+ ", loginname=" + loginname + ", time=" + time + ", createDate=" + createDate + "]";
	}
	

}
