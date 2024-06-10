package com.jpcenz.prueba.domain.service;

import com.jpcenz.prueba.interfaces.rest.model.ExchangeResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ExchangeRateService {
    Mono<ExchangeResponse> applyExchangeRate(double amount, String sourceCurrency, String targetCurrency);
    Flux<ExchangeResponse> getAllExchangeRates();
}
