package com.jin.eledger.pojo;

import java.io.Serializable;
import java.util.List;

public class LayuiPage<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5368610734830950794L; 
 
	private String code="0";
	private String msg="";
	private Long count;
	private List<T> data;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	 
}
