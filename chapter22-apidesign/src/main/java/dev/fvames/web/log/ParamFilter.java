package dev.fvames.web.log;

import com.alibaba.fastjson.JSON;
import dev.fvames.web.util.HttpLoggingUtil;
import dev.fvames.web.util.RequestLogInfo;
import dev.fvames.web.util.RespLogInfo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Slf4j
@Component
public class ParamFilter implements Filter {

	public static final String C_REQ_ID = "cReqId";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			MDC.put(C_REQ_ID, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));

			ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper((HttpServletRequest) request);
			ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) response);
			log.info("1.----------------- Filter start ------------------");
			RequestLogInfo requestLogInfo = HttpLoggingUtil.initByHttpServletRequest(requestWrapper);
			log.info(JSON.toJSONString(requestLogInfo));

			// 处理业务逻辑
			chain.doFilter(requestWrapper, responseWrapper);

			// 处理日志
			RespLogInfo respLogInfo = HttpLoggingUtil.updateByHttpServletResponse(requestLogInfo, responseWrapper);
			log.info(JSON.toJSONString(respLogInfo));
			// 响应流只能被消费一次，这里需要最后执行，从缓存中直接返回给客户端
			responseWrapper.copyBodyToResponse();

			// 销毁
			MDC.remove(C_REQ_ID);
			log.info("1.----------------- Filter end ------------------");
		} else {
			chain.doFilter(request, response);
		}
	}

}
