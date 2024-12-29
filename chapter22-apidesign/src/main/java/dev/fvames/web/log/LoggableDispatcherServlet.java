package dev.fvames.web.log;

import com.alibaba.fastjson.JSON;
import dev.fvames.web.util.HttpLoggingUtil;
import dev.fvames.web.util.RequestLogInfo;
import dev.fvames.web.util.RespLogInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component(value = DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_BEAN_NAME)
public class LoggableDispatcherServlet extends DispatcherServlet {

	@Override
	protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("3.----------------- dispatcherServlet start ------------------");
		ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
		RequestLogInfo requestLogInfo = HttpLoggingUtil.initByHttpServletRequest(requestWrapper);
		log.info(JSON.toJSONString(requestLogInfo));
		try {
			super.doDispatch(requestWrapper, response);
		} finally {
			ContentCachingResponseWrapper responseWrapper = (ContentCachingResponseWrapper) response;
			RespLogInfo respLogInfo = HttpLoggingUtil.updateByHttpServletResponse(requestLogInfo, responseWrapper);
			log.info(JSON.toJSONString(respLogInfo));
			log.info("3.----------------- dispatcherServlet end ------------------");
		}
	}
}
