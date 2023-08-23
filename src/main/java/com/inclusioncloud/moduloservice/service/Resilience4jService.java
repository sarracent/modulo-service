package com.inclusioncloud.moduloservice.service;

import java.util.function.Supplier;

public interface Resilience4jService {
    <T> T executeCalculateModulo(Supplier<T> operation);
}
