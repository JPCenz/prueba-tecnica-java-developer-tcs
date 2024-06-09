package com.jpcenz.prueba.infraestructure.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

@FeignClient(name = "exchangeRateClient", url = "${exchangerate.url}")
public interface ExchangeRateClient {

    @GetMapping(value = "/{currency}", produces = "application/json")
    ExchangeRateResponse getExchangeRates(@PathVariable("currency") String value);
}