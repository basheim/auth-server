package com.beandon.auth.pojos.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@RedisHash(value="refresh", timeToLive=36000) // 10 hours
public class Auth implements Serializable {
    @Id
    private String id;
    private String scope;
    private String subject;
}
