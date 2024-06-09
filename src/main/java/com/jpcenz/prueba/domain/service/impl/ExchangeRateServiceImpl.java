package com.jpcenz.prueba.domain.service.impl;

import com.jpcenz.prueba.domain.model.ExchangeRateTransaction;
import com.jpcenz.prueba.domain.repository.ExchangeRateTransactionRepository;
import com.jpcenz.prueba.domain.service.ExchangeRateService;
import com.jpcenz.prueba.infraestructure.client.ExchangeRateClient;
import com.jpcenz.prueba.interfaces.rest.model.BaseExchangeResponse;
import com.jpcenz.prueba.interfaces.rest.model.ExchangeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    @Autowired
    ExchangeRateTransactionRepository repository;

    @Autowired
    ExchangeRateClient client;

    @Override
    public Mono<BaseExchangeResponse> applyExchangeRate(double amount, String sourceCurrency, String targetCurrency) {
        try {
            var res = client.getExchangeRates(sourceCurrency);
            Double rate = null;
            if (Objects.equals(res.getResult(), "success")){
                rate = res.getRates().get(targetCurrency);
            } else {
                return Mono.error(new RuntimeException(res.getResult()));
            }
            if (rate == null) {
                return Mono.error(new RuntimeException("Currency not supported"));
            }
            Double convertedAmount = amount * rate;
            ExchangeRateTransaction exchangeRate = new ExchangeRateTransaction(null,amount, convertedAmount, sourceCurrency, targetCurrency, rate);
            repository.save(exchangeRate);
            var exchangeRateResponse = new ExchangeResponse(amount,convertedAmount,sourceCurrency,targetCurrency,rate);
            exchangeRateResponse.setResult("sucess");
            return Mono.just(exchangeRateResponse);

        } catch (Exception ex){
            return Mono.error(new RuntimeException("Exchange Rate Service not available"));

        }


    }

    @Override
    public List<ExchangeRateTransaction> getAllExchangeRates() {
        return repository.findAll();
    }

}
