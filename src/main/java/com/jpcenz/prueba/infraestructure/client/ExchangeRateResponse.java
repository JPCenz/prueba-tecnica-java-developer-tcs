package com.jpcenz.prueba.infraestructure.client;

import lombok.Data;

import java.util.Map;

@Data
public class ExchangeRateResponse {
    private String result;
    private String base_code;
    private Map<String, Double> rates;


}
