package com.lhd.sys.untils;

import java.io.Serializable;
import java.util.List;

public class LaYuiPage implements Serializable{
	/**
	 * Layui分页
	 *
	 * @author 
	 * @since 
	 */
	    private static final long serialVersionUID = 1L;
	    private int code = 0;
	    //总记录数
	    private long count;
	    
	    private String msg = "" ;
	    
	    //列表数据
	    private List<?> data;

	    public LaYuiPage(List<?> data, long count) {
	        this.data = data;
	        this.count = count;
	    }

	    public int getCode() {
	        return code;
	    }

	    public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public void setCount(long count) {
			this.count = count;
		}

		public void setCode(int code) {
	        this.code = code;
	    }

	    public long getCount() {
	        return count;
	    }

	    public List<?> getData() {
	        return data;
	    }

	    public void setData(List<?> data) {
	        this.data = data;
	    }

		@Override
		public String toString() {
			return "LaYuiPage [code=" + code + ", count=" + count + ", data=" + data + "]";
		}
	    
	}
