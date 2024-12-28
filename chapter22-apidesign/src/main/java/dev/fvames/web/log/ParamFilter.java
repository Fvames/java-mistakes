package dev.fvames.web.log;

import com.alibaba.fastjson.JSON;
import dev.fvames.web.util.HttpLoggingUtil;
import dev.fvames.web.util.RequestLogInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@Component
public class ParamFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper((HttpServletRequest) request);
			ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) response);
			RequestLogInfo requestLogInfo = HttpLoggingUtil.initByHttpServletRequest(requestWrapper);
			// 处理业务逻辑
			chain.doFilter(requestWrapper, responseWrapper);
			// 处理日志
			HttpLoggingUtil.updateByHttpServletResponse(requestLogInfo, requestWrapper, responseWrapper);
			log.info("1.----------------- Filter start ------------------");
			log.info(JSON.toJSONString(requestLogInfo));
			log.info("1.----------------- Filter end ------------------");
		} else {
			chain.doFilter(request, response);
		}
	}

}
