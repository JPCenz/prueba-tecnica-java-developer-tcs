package com.jpcenz.prueba.domain.service;

import com.jpcenz.prueba.domain.model.ExchangeRateTransaction;
import com.jpcenz.prueba.infraestructure.client.ExchangeRateResponse;
import com.jpcenz.prueba.interfaces.rest.model.BaseExchangeResponse;
import com.jpcenz.prueba.interfaces.rest.model.ExchangeResponse;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ExchangeRateService {
    Mono<BaseExchangeResponse> applyExchangeRate(double amount, String sourceCurrency, String targetCurrency);
    List<ExchangeRateTransaction> getAllExchangeRates();
}
