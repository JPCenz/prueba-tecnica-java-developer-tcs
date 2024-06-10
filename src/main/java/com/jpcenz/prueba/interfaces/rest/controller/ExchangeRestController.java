package com.jpcenz.prueba.interfaces.rest.controller;


import com.jpcenz.prueba.domain.service.ExchangeRateService;
import com.jpcenz.prueba.interfaces.rest.model.ExchangeRequest;
import com.jpcenz.prueba.interfaces.rest.model.ExchangeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api/exchange")
public class ExchangeRestController {

    @Autowired
    ExchangeRateService service;

    @PostMapping("/convert")
    public Mono<ExchangeResponse> convertCurrency(@RequestBody ExchangeRequest request) {
        Mono<ExchangeResponse> res = null;
        try {
            System.out.println(request);
            res = service.applyExchangeRate(request.getAmount(), request.getSourceCurrency(), request.getTargetCurrency());
            return res;


        } catch (Exception ex) {
            log.info(ex.getMessage());
            throw ex;

        }

    }

    @GetMapping("/history")
    public Flux<ExchangeResponse> getExchangeRateHistory(String request) {

        return service.getAllExchangeRates();
    }
}
