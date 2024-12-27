package dev.fvames.cachedesign.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
public class ValidateSignatureInterceptor  extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Map<String, String[]> parameterMap = request.getParameterMap();
		parameterMap.entrySet().forEach(entry -> {
			log.info("key: {}, value: {}", entry.getKey(), entry.getValue()[0]);
		});
		return true;
	}
}
