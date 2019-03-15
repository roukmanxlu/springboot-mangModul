package com.minqing.service.system;

/**
 * @Description: 系统常量接口
 * @author minqing
 */
public interface SystemConstants {

	/**
	 * 响应code
	 */
	String CODE = "code";
	/**
	 * 响应message
	 */
	String MESSAGE = "message";
	/***
	 * 响应数据
	 */
	String RESULTS = "results";
	/***
	 * 日期格式
	 */
	String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";
	/***
	 * 发送验证码时手机号键
	 */
	String MOBILE_NUMBER = "mobile";
	/***
	 * 发送验证码时验证码键
	 */
	String MESSAGE_STR = "msg";
	/***
	 * 验证码位数
	 */
	int VALIDATE_CODE_LENTH = 4;
	/***
	 * 空字符串
	 */
	String NULL_STRING = "";
	/***
	 * 报文相应码
	 */
	String REP_CODE = "00";

	/**
	 * 响应msg_code
	 */
	String MSG_CODE = "msg_code";
	/**
	 * 响应msg_text
	 */
	String MSG_TEXT = "msg_text";
	/**
	 * 信息中心的消息状态1：已读，2：删除
	 */
	String MSG_STATUS_1 = "1";
	String MSG_STATUS_2 = "2";

	/**
	 * 0-否;1-是
	 */
	String FLAG_0 = "0";
	String FLAG_1 = "1";
	
	/**
	 * 默认账户以及系统自动生成账户
	 */
	String ACQUIESCENT="1";
	String BRANDID="6";
	
	/**
	 * 8-开通日结
	 */
	int BRANDID_CHECKDAY=8;
	
	/**
	 * 1:一级代理商
	 */
	int SUPERIOR=1;
	
	/**
	 * 若初始密码为123456Aa则提示用户去修改密码
	 */
	String USERPASSWORD="123456Aa";
	
	/**
	 * 是否需要提示修改用户密码 1-需要 ;0-不需要
	 */
	String WANT="1";
	
	String NEEDNOT="0";
	
	/**
	 * 用户账号状态 1-正常
	 */
	String STATUSNORMAL="1";

	/**
	 * 用户账号状态 2-黑名单
	 */
	String BLACK_LIST_STATUS="2";

}
