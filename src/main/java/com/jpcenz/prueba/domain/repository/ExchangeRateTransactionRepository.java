package com.jpcenz.prueba.domain.repository;

import com.jpcenz.prueba.domain.model.ExchangeRateTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRateTransactionRepository extends JpaRepository<ExchangeRateTransaction, Long> {

}
