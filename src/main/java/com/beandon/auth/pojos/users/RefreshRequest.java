package com.beandon.auth.pojos.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class RefreshRequest {
    @NonNull
    private String refresh;
    @NonNull
    private String name;
}
