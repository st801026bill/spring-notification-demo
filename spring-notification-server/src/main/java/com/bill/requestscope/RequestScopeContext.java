package com.bill.requestscope;

import com.bill.view.NotificationTemplate;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Data
@Component
@RequestScope
public class RequestScopeContext<T> {
    private NotificationTemplate<T> notificationTemplate;
}
