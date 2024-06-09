package com.jpcenz.prueba.interfaces.rest.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@NoArgsConstructor
public class ExchangeRequest {
    private double amount;
    private String sourceCurrency;
    private String targetCurrency;


}