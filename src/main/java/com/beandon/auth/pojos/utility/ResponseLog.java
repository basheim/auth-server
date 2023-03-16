package com.beandon.auth.pojos.utility;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseLog {
    private int code;
    private Object payload;
    private long executionTime;
}
