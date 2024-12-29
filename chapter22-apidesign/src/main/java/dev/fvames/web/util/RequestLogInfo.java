package dev.fvames.web.util;

import lombok.Data;

@Data
public class RequestLogInfo {
	/**
	 * 请求资源
	 */
	private String requestUri;

	/**
	 * 调用者地址
	 */
	private String remoteAddr;

	/**
	 * 请求头
	 */
	private Object requestHeaders;

	/**
	 * 被调方法
	 */
	private String method;

	/**
	 * 请求参数
	 */
	private Object request;

	/**
	 * 接口耗时
	 */
	private Long beginTimeMillis;
}
