package com.jpcenz.prueba.domain.service.impl;

import com.jpcenz.prueba.domain.model.ExchangeRateTransaction;
import com.jpcenz.prueba.domain.repository.ExchangeRateTransactionRepository;
import com.jpcenz.prueba.domain.service.ExchangeRateService;
import com.jpcenz.prueba.infraestructure.client.ExchangeRateClient;
import com.jpcenz.prueba.interfaces.rest.exception.CustomErrorException;
import com.jpcenz.prueba.interfaces.rest.model.ExchangeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    @Autowired
    ExchangeRateTransactionRepository repository;

    @Autowired
    ExchangeRateClient client;

    @Override
    public Mono<ExchangeResponse> applyExchangeRate(double amount, String sourceCurrency, String targetCurrency) {
        try {
            var res = client.getExchangeRates(sourceCurrency);
            Double rate = null;
            if (Objects.equals(res.getResult(), "success")){
                rate = res.getRates().get(targetCurrency);
            } else {
                return Mono.error(new CustomErrorException("Source Currency not supported"));
            }
            if (rate == null) {
                return Mono.error(new CustomErrorException("TargetCurrency not supported"));
            }
            Double convertedAmount = amount * rate;
            ExchangeRateTransaction exchangeRate = new ExchangeRateTransaction(null,amount, convertedAmount, sourceCurrency, targetCurrency, rate);
            repository.save(exchangeRate);
            var exchangeRateResponse = new ExchangeResponse(exchangeRate.getId(),amount,convertedAmount,sourceCurrency,targetCurrency,rate,exchangeRate.getCreatedAt());
            return Mono.just(exchangeRateResponse);

        } catch (Exception ex){
            return Mono.error(new Exception("Exchange Rate Service not available"));

        }


    }

    @Override
    public Flux<ExchangeResponse> getAllExchangeRates() {
         Flux<ExchangeRateTransaction>s = Flux.fromIterable(repository.findAll());
            return s.map(exchange ->{
             return new ExchangeResponse(
                     exchange.getId(),
                     exchange.getAmount(),
                     exchange.getConvertedAmount(),
                     exchange.getSourceCurrency(),
                     exchange.getTargetCurrency(),
                     exchange.getExchangeRate(),
                     exchange.getCreatedAt()
             );
         });

    }

}
