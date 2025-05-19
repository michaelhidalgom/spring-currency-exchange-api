package com.dimension;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class TipoCambioApplication {

	public static void main(String[] args) {
		SpringApplication.run(TipoCambioApplication.class, args);
	}

}
