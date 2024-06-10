package com.jpcenz.prueba.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor

public class ExchangeRateTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double amount;
    private double convertedAmount;
    private String sourceCurrency;
    private String targetCurrency;
    private double exchangeRate;

    @Column(updatable = false)
    @CreationTimestamp
    private Date createdAt;

    public ExchangeRateTransaction(Long id, double amount, double convertedAmount, String sourceCurrency, String targetCurrency, double exchangeRate) {
        this.id = id;
        this.amount = amount;
        this.convertedAmount = convertedAmount;
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
        this.exchangeRate = exchangeRate;
    }
}
