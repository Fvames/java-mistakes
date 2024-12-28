package dev.fvames.web.log;

import com.alibaba.fastjson.JSON;
import dev.fvames.web.util.HttpLoggingUtil;
import dev.fvames.web.util.RequestLogInfo;
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
		ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
		ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
		RequestLogInfo requestLogInfo = HttpLoggingUtil.initByHttpServletRequest(requestWrapper);
		try {
			super.doDispatch(requestWrapper, responseWrapper);
		} finally {
			HttpLoggingUtil.updateByHttpServletResponse(requestLogInfo, requestWrapper, responseWrapper);
			log.info("3.----------------- dispatcherServlet start ------------------");
			log.info(JSON.toJSONString(requestLogInfo));
			log.info("3.----------------- dispatcherServlet end ------------------");
		}
	}
}
