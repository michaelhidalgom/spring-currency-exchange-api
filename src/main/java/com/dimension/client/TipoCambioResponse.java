package com.dimension.client;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;
//import com.fasterxml.jackson.annotation.JsonAlias;

@Data
public class TipoCambioResponse {

    //@JsonAlias("base_code")
    private String base_code;

    private Map<String, BigDecimal> rates;
}
