package com.bill.requestscope;

import com.bill.view.NotificationTemplate;
import java.lang.reflect.Type;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class RequestAdviceAdapter extends RequestBodyAdviceAdapter {
    private final RequestScopeContext<Object> requestScopeContext;

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType,
        Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage,
        MethodParameter parameter,
        Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        requestScopeContext.setNotificationTemplate((NotificationTemplate) body);
        return body;
    }
}
