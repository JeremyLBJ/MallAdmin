package com.lhd.sys.common;

import java.io.Serializable;
import java.util.List;

import com.lhd.sys.entity.SysUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiverUser extends SysUser implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SysUser sysUser ;
	
	private List<String> roles ;
	
	private List<String> permissions ;

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}

	@Override
	public String toString() {
		return "ActiverUser [sysUser=" + sysUser + ", roles=" + roles + ", permissions=" + permissions + "]";
	}

	

}
