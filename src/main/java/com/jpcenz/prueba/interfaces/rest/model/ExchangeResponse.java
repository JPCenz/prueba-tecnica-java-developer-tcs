package com.jpcenz.prueba.interfaces.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeResponse {
    private Long   id;
    private Double amount;
    private Double convertedAmount;
    private String sourceCurrency;
    private String targetCurrency;
    private Double exchangeRate;
    private Date createdAt;
}
