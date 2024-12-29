package dev.fvames.web.util;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class HttpLoggingUtil {
	public static RequestLogInfo initByHttpServletRequest(ContentCachingRequestWrapper requestWrapper) {
		RequestLogInfo requestLogInfo = new RequestLogInfo();
		requestLogInfo.setBeginTimeMillis(System.currentTimeMillis());
		requestLogInfo.setRequestUri(requestWrapper.getRequestURI());
		requestLogInfo.setRemoteAddr(requestWrapper.getRemoteAddr());
		// requestLogInfo.setRequestHeaders(getRequestHeaders(requestWrapper));
		String method = requestWrapper.getMethod();
		requestLogInfo.setMethod(method);
		if (method.equals(RequestMethod.GET.name())) {
			requestLogInfo.setRequest(requestWrapper.getParameterMap());
		} else {
			requestLogInfo.setRequest(new String(requestWrapper.getContentAsByteArray()));
		}

		return requestLogInfo;
	}

	public static RespLogInfo updateByHttpServletResponse(RequestLogInfo requestLogInfo,
												   ContentCachingResponseWrapper responseWrapper) throws IOException {

		RespLogInfo respLogInfo = new RespLogInfo();
		respLogInfo.setStatus(responseWrapper.getStatus());
		respLogInfo.setResponse(new String(responseWrapper.getContentAsByteArray()));
		// respLogInfo.setResponseHeaders(getResponsetHeaders(responseWrapper));
		respLogInfo.setCosTimeMillis(System.currentTimeMillis() - requestLogInfo.getBeginTimeMillis());

		return respLogInfo;

	}

	private static Map<String, Object> getResponsetHeaders(ContentCachingResponseWrapper response) {
		Map<String, Object> headers = new HashMap<>(16);
		Collection<String> headerNames = response.getHeaderNames();
		for (String headerName : headerNames) {
			headers.put(headerName, response.getHeader(headerName));
		}
		return headers;
	}

	private static Map<String, Object> getRequestHeaders(HttpServletRequest request) {
		Map<String, Object> headers = new HashMap<>(16);
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			headers.put(headerName, request.getHeader(headerName));
		}
		return headers;
	}

}
