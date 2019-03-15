package com.minqing.service.response;

import com.minqing.service.enums.StatusCode;

/**
 * @desc:通用的响应模型
 * @author minqing
 * @param <T>
 */
public class BaseResponse <T>{

	private String msg;
	private String code;
	private T data;
	
	
	public BaseResponse(String msg,String code) {
		this.code = code;
		this.msg = msg;
	}
	
	public BaseResponse(StatusCode statusCode) {
		this.msg = statusCode.getMsg();
		this.code = statusCode.getCode();
	}
	
	public BaseResponse(StatusCode statusCode, T data) {
		this.msg = statusCode.getMsg();
		this.code = statusCode.getCode();
		this.data = data;
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
}
