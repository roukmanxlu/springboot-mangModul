package com.minqing.service.enums;
/**
 * @Description: 响应码枚举类
 * @author minqing
 *
 */
public enum StatusCode {

	SUCCESS("0000", "处理成功"),
	FAIL("0001","处理失败");
	
	private String code;
	private String msg;
	
	private StatusCode() {
		
	}
	
	private StatusCode(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

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
	
	
}
