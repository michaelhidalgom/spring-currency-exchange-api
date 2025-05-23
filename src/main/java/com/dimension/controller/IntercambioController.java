package com.dimension.controller;

import com.dimension.model.IntercambioRequest;
import com.dimension.model.IntercambioResponse;

import com.dimension.service.IntercambioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RestController
@RequestMapping("/api/intercambios")
@CrossOrigin(origins = "http://localhost:4200")
public class IntercambioController {

    @Autowired
    private IntercambioService intercambioService;

    @PostMapping("/procesa")
    public ResponseEntity<IntercambioResponse> procesarCambio(@RequestBody IntercambioRequest request) {

        IntercambioResponse response = intercambioService.procesarCambio(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<IntercambioResponse>> obtenerIntercambios(){

        return ResponseEntity.ok(intercambioService.obtenerIntercambios());
    }
}
