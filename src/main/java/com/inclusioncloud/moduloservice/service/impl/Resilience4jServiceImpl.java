package com.inclusioncloud.moduloservice.service.impl;

import com.inclusioncloud.moduloservice.service.Resilience4jService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class Resilience4jServiceImpl implements Resilience4jService {

    private static final String MODULO_API = "modulo";

    @Override
    @CircuitBreaker(name = MODULO_API)
    @RateLimiter(name = MODULO_API)
    @Bulkhead(name = MODULO_API)
    @Retry(name = MODULO_API)
    public <T> T executeCalculateModulo(Supplier<T> operation) {
        return operation.get();
    }


}
