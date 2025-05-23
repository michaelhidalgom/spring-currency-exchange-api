package com.dimension.client;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class TipoCambioResponse {

    private String base_code;
    private Map<String, BigDecimal> rates;
}
