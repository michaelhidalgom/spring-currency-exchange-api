package com.dimension.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class Intercambio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal monto;
    private String monedaOrigen;
    private String monedaDestino;
    private BigDecimal montoCambio;
    private BigDecimal tipoCambio;
}
