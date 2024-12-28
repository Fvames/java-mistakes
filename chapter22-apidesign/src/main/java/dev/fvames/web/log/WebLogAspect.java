package dev.fvames.web.log;

import com.alibaba.fastjson.JSON;
import dev.fvames.web.util.RequestLogInfo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class WebLogAspect {

	private final ThreadLocal<RequestLogInfo> requestInfoThreadLocal = new ThreadLocal<>();

	@Pointcut("execution(public * dev.fvames.apidesign.web..*.*(..))")
	public void webLog() {

	}

	@Before(value = "webLog()")
	public void doBefore(JoinPoint point) {
		Long startTime = System.currentTimeMillis();
		ServletRequestAttributes attributes =
				(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		RequestLogInfo requestLogInfo = new RequestLogInfo();
		requestLogInfo.setCosTimeMillis(startTime);
		requestLogInfo.setRemoteAddr(request.getRemoteAddr());
		requestLogInfo.setRequestUri(request.getRequestURL().toString());
		requestLogInfo.setMethod(point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName());
		requestLogInfo.setRequest(Arrays.toString(point.getArgs()));
		requestInfoThreadLocal.set(requestLogInfo);
	}

	@AfterReturning(value = "webLog()", returning = "ret")
	public void doAferReturning(Object ret) {
		RequestLogInfo requestLogInfo = requestInfoThreadLocal.get();
		requestLogInfo.setCosTimeMillis(System.currentTimeMillis() - requestLogInfo.getCosTimeMillis());
		requestLogInfo.setResponse(ret);
		log.info("4.----------------- aspect start ------------------");
		log.info(JSON.toJSONString(requestLogInfo));
		log.info("4.----------------- aspect end ------------------");
		requestInfoThreadLocal.remove();
	}

}
