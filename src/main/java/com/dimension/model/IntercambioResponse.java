package com.dimension.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class IntercambioResponse {

    private BigDecimal monto;
    private BigDecimal montoCambio;
    private String monedaOrigen;
    private String monedaDestino;
    private BigDecimal tipoCambio;
}
