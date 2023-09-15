package com.cloudx.productservice.service.impl;

import com.cloudx.productservice.service.Resilience4jService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class Resilience4jServiceImpl implements Resilience4jService {

    private static final String PRODUCT_API = "product";

    @Override
    @CircuitBreaker(name = PRODUCT_API)
    @RateLimiter(name = PRODUCT_API)
    @Bulkhead(name = PRODUCT_API)
    @Retry(name = PRODUCT_API)
    public <T> T executeProduct(Supplier<T> operation) {
        return operation.get();
    }


}
