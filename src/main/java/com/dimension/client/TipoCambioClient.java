package com.dimension.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "exchangeRateApi", url = "https://open.er-api.com/v6/latest")
public interface TipoCambioClient {

    @GetMapping("/{base}")
    TipoCambioResponse getTipoCambio(@PathVariable("base") String base);
}

