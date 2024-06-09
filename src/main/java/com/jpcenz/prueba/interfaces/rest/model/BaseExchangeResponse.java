package com.jpcenz.prueba.interfaces.rest.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BaseExchangeResponse {
    String result;
    String description;

    public BaseExchangeResponse(String description) {
        this.description= description;
        this.result = "error";
    }
}
