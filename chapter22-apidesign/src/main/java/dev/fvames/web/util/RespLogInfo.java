package dev.fvames.web.util;

import lombok.Data;

@Data
public class RespLogInfo {

	/**
	 * 响应状态
	 */
	private Integer status;

	/**
	 * 响应头
	 */
	private Object responseHeaders;

	/**
	 * 响应数据
	 */
	private Object response;

	/**
	 * 接口耗时
	 */
	private Long cosTimeMillis;
}
