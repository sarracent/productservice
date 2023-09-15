package com.cloudx.productservice.service;

import java.util.function.Supplier;

public interface Resilience4jService {
    <T> T executeProduct(Supplier<T> operation);
}
