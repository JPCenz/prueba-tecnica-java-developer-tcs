package com.jpcenz.prueba.interfaces.rest.controller;


import com.jpcenz.prueba.domain.model.ExchangeRateTransaction;
import com.jpcenz.prueba.domain.service.ExchangeRateService;
import com.jpcenz.prueba.interfaces.rest.model.BaseExchangeResponse;
import com.jpcenz.prueba.interfaces.rest.model.ExchangeRequest;
import com.jpcenz.prueba.interfaces.rest.model.ExchangeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/exchange")
public class ExchangeRestController {

    @Autowired
    ExchangeRateService service;

    @PostMapping("/convert")
    public Mono<BaseExchangeResponse> convertCurrency(@RequestBody ExchangeRequest request) {
        Mono<BaseExchangeResponse> res = null;
        try {
            System.out.println(request);
            res = service.applyExchangeRate(request.getAmount(), request.getSourceCurrency(), request.getTargetCurrency());
            res.subscribe(value -> {
                // Handle the value
                System.out.println("Value: " + value);
            });
            return res;


        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Mono.just(new BaseExchangeResponse(ex.getMessage()))).getBody();




        }

    }

    @PostMapping("/history")
    public Mono<String> getExchangeRateHistory(String request) {
        System.out.println(request);
        List<ExchangeRateTransaction> exchangeRateTransactionMono =  service.getAllExchangeRates();


        return Mono.just("Alex");
    }
}
