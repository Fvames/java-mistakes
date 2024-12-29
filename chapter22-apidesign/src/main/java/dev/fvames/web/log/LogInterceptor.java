package dev.fvames.web.log;

import com.alibaba.fastjson.JSON;
import dev.fvames.web.util.HttpLoggingUtil;
import dev.fvames.web.util.RequestLogInfo;
import dev.fvames.web.util.RespLogInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LogInterceptor extends HandlerInterceptorAdapter {

	private final ThreadLocal<Long> startTimeThreadLocal = new ThreadLocal<>();
	@Override
	public boolean preHandle(HttpServletRequest request,
							 HttpServletResponse response, Object handler) {
		log.info("2.----------------- Interceptor start ------------------");
		ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
		RequestLogInfo requestLogInfo = HttpLoggingUtil.initByHttpServletRequest(requestWrapper);
		log.info(JSON.toJSONString(requestLogInfo));

		startTimeThreadLocal.set(requestLogInfo.getBeginTimeMillis());
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
								HttpServletResponse response, Object handler, Exception ex) throws Exception {
		if (response instanceof ContentCachingResponseWrapper) {

			ContentCachingResponseWrapper responseWrapper = (ContentCachingResponseWrapper) response;

			RequestLogInfo requestLogInfo = new RequestLogInfo();
			requestLogInfo.setBeginTimeMillis(startTimeThreadLocal.get());

			RespLogInfo respLogInfo = HttpLoggingUtil.updateByHttpServletResponse(requestLogInfo, responseWrapper);
			log.info(JSON.toJSONString(respLogInfo));

		}
		startTimeThreadLocal.remove();
		log.info("2.----------------- Interceptor end ------------------");
	}
}