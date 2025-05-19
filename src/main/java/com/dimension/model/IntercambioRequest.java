package com.dimension.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class IntercambioRequest {

    private BigDecimal monto;
    private String monedaOrigen;
    private String monedaDestino;
}
