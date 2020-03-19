package com.lhd.sys.vo.act;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;


public class ActDeployMent {
	
	  private String id ;
	  private String name;
	  private String category;
	  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	  private Date deploymentTime;
	  
	  
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Date getDeploymentTime() {
		return deploymentTime;
	}
	public void setDeploymentTime(Date deploymentTime) {
		this.deploymentTime = deploymentTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "ActDeployMent [id=" + id + ", name=" + name + ", category=" + category + ", deploymentTime="
				+ deploymentTime + "]";
	}
	

}
