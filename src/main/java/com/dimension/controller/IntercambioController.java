package com.dimension.controller;

import com.dimension.model.IntercambioRequest;
import com.dimension.model.IntercambioResponse;

import com.dimension.service.IntercambioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/intercambios")
public class IntercambioController {

    @Autowired
    private IntercambioService intercambioService;

    // (B)
    // http://localhost:8090/api/intercambios/procesa
    @PostMapping("/procesa")
    public ResponseEntity<IntercambioResponse> procesarCambio(@RequestBody IntercambioRequest request) {

        IntercambioResponse response = intercambioService.procesarCambio(request);
        return ResponseEntity.ok(response);
    }

    // http://localhost:8090/api/intercambios
    @GetMapping
    public ResponseEntity<List<IntercambioResponse>> obtenerIntercambios(){

        return ResponseEntity.ok(intercambioService.obtenerIntercambios());
    }
}