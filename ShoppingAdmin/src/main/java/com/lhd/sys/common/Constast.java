package com.lhd.sys.common;

public interface Constast {
	
	/**
	 * 
	 * 存放相关状态码
	 * 
	 */
	
	public static final Integer OK = 200 ;
	public static final Integer ERROR = -1 ;
	public static final Integer CODE_ERROR = 501 ;
	
	
	
	/**
	 * 
	 * 
	 * 菜单权限类型
	 * 
	 */
	public static final String TYPE_MENU = "menu" ;
	public static final String TYPE_PERMISSION = "permission" ;
	
	/**
	 * 
	 * 可用状态
	 * 
	 */
	public static final Integer AVAILABLE_TRUE = 1 ;
	public static final Integer AVAILABLE_FALSE = 0 ;
	
	/**
	 * 
	 * 用户类型
	 * 
	 */
	public static final Integer USER_TYPE_SUPER = 1 ;
	public static final Integer USER_TYPE_NORMAL = 2 ;
	
	
	/**
	 * 
	 * 展开类型
	 * 
	 */
	public static final Integer OPEN_TRUE = 1 ;
	public static final Integer OPEN_FALSE = 0 ;
	
	/**
	 * 
	 * 
	 * 请假单的状态
	 * 
	 */
	public static final String STATE_LEVEBILL_ZORO = "0" ; //未提交
	public static final String STATE_LEVEBILL_ONE = "1" ;  //审批中
	public static final String STATE_LEVEBILL_TOW = "2" ; //审批完成
	public static final String STATE_LEVEBILL_THREE = "3" ;	//已放弃
	
}
