package dev.fvames.apidesign.headerapiversion;

import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

@Profile(value = "header-api-version")
public class HeaderAPIVersionHandlerMapping extends RequestMappingHandlerMapping {
    @Override
    protected boolean isHandler(Class<?> beanType) {
        return AnnotatedElementUtils.hasAnnotation(beanType, Controller.class);
    }

    @Override
    protected RequestCondition<APIVersionCondition> getCustomTypeCondition(Class<?> handlerType) {
        APIVersion apiVersion = AnnotationUtils.findAnnotation(handlerType, APIVersion.class);
        return createCondition(apiVersion);
    }

    @Override
    protected RequestCondition<APIVersionCondition> getCustomMethodCondition(Method method) {
        APIVersion apiVersion = AnnotationUtils.findAnnotation(method, APIVersion.class);
        return createCondition(apiVersion);
    }

    private RequestCondition<APIVersionCondition> createCondition(APIVersion apiVersion) {
        return apiVersion == null ? null : new APIVersionCondition(apiVersion.value(), apiVersion.headerKey());
    }
}