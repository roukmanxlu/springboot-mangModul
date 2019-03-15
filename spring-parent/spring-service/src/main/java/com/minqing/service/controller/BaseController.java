package com.minqing.service.controller;

import com.alibaba.fastjson.JSONObject;
import com.minqing.service.enums.StatusCode;
import com.minqing.service.system.SystemConstants;

public class BaseController {
	public JSONObject getSuccess() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(SystemConstants.CODE, StatusCode.SUCCESS.getCode());
		jsonObject.put(SystemConstants.MESSAGE, StatusCode.SUCCESS.getMsg());
		jsonObject.put(SystemConstants.RESULTS, SystemConstants.NULL_STRING);
		return jsonObject;
	}

	public JSONObject getSuccess(String code, String message) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(SystemConstants.CODE, code);
		jsonObject.put(SystemConstants.MESSAGE, message);
		jsonObject.put(SystemConstants.RESULTS, SystemConstants.NULL_STRING);
		return jsonObject;
	}

	public JSONObject getFail() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(SystemConstants.CODE, StatusCode.FAIL.getCode());
		jsonObject.put(SystemConstants.MESSAGE, StatusCode.FAIL.getMsg());
		jsonObject.put(SystemConstants.RESULTS, SystemConstants.NULL_STRING);
		return jsonObject;
	}

	public JSONObject getFail(String code, String message) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(SystemConstants.CODE, code);
		jsonObject.put(SystemConstants.MESSAGE, message);
		jsonObject.put(SystemConstants.RESULTS, SystemConstants.NULL_STRING);
		return jsonObject;
	}
}
