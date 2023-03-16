package com.beandon.auth.pojos.utility;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestLog {
    private String method;
    private String uri;
    private Object payload;
    private String queryParams;
}
