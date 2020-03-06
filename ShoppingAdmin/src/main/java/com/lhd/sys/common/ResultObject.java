package com.lhd.sys.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ResultObject {
	
	public static final ResultObject LONG_SUCCESS = new ResultObject(Constast.OK, "登录成功") ;
	
	public static final ResultObject LONG_ERROR_PASS = new ResultObject(Constast.ERROR, "登录失败,用户名或密码不正确") ;
	
	public static final ResultObject LONG_ERROR_CODE= new ResultObject(Constast.ERROR, "登录失败,验证码不正确") ;
	
	public static final ResultObject DELETE_SUCCESS= new ResultObject(Constast.OK, "删除成功") ;
	
	public static final ResultObject DELETE_ERROR= new ResultObject(Constast.ERROR, "删除失败") ;
	
	public static final ResultObject ADD_SUCCESS= new ResultObject(Constast.OK, "添加成功") ;
	
	public static final ResultObject ADD_ERROR= new ResultObject(Constast.ERROR, "添加失败") ;
	
	public static final ResultObject UPDATE_SUCCESS= new ResultObject(Constast.OK, "修改成功") ;
	
	public static final ResultObject UPDATE_ERROR= new ResultObject(Constast.ERROR, "修改失败") ;
	
	public static final ResultObject RESET_SUCCESS= new ResultObject(Constast.OK, "重置成功") ;
	
	public static final ResultObject RESET_ERROR= new ResultObject(Constast.ERROR, "重置失败") ;
	
	public static final ResultObject DISPATCH_SUCCESS= new ResultObject(Constast.OK, "分配成功") ;
	
	public static final ResultObject DISPATCH_ERROR= new ResultObject(Constast.ERROR, "分配失败") ;
	
	public static final ResultObject OPERATION_SECCESS= new ResultObject(Constast.OK, "请上传图片") ;
	
	public static final ResultObject OPERATION_ERROR= new ResultObject(Constast.ERROR, "未知异常请联系管理员") ;
	
	
	public static final ResultObject LOGIN_OUT_SECCESS= new ResultObject(Constast.OK, "退出成功") ;
	
	@ApiModelProperty("登录状态码")
	private Integer code ;
	@ApiModelProperty("登录信息")
	private String msg ;
	
	
	
	public ResultObject(Integer code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	
	
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	
	@Override
	public String toString() {
		return "ResultObject [code=" + code + ", msg=" + msg + "]";
	}
	

}
