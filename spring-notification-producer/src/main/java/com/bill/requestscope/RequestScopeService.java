package com.bill.requestscope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestScopeService {
    @Autowired
    private RequestScopeContext<?> requestScopeContext;

//    public String getType() { return requestScopeContext.getNotificationTemplate().getHeader().getType(); }
}
