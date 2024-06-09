package com.jpcenz.prueba.interfaces.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeResponse extends BaseExchangeResponse {
    private Double amount;
    private Double convertedAmount;
    private String sourceCurrency;
    private String targetCurrency;
    private Double exchangeRate;
}
