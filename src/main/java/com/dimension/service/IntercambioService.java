    package com.dimension.service;

    import com.dimension.client.TipoCambioClient;
    import com.dimension.client.TipoCambioResponse;
    import com.dimension.model.IntercambioRequest;
    import com.dimension.model.IntercambioResponse;
    import com.dimension.model.Intercambio;
    import com.dimension.repository.IntercambioRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.math.BigDecimal;
    import java.util.List;
    import java.util.stream.Collectors;

    @Service
    public class IntercambioService {

        @Autowired
        private TipoCambioClient tipoCambioClient;
        @Autowired
        private IntercambioRepository intercambioRepository;

        public IntercambioResponse procesarCambio(IntercambioRequest request) {

            //Obtiene los valores de cambio de la moneda origen en la API externa (FEIGN)
            TipoCambioResponse tipoCambioResponse = tipoCambioClient.getTipoCambio(request.getMonedaOrigen());

            //Obtiene el valor de cambio de la moneda destino
            BigDecimal tasaIntercambio = tipoCambioResponse.getRates().get(request.getMonedaDestino());
            //Obtiene el monto convertido
            BigDecimal montoConvertido = request.getMonto().multiply(tasaIntercambio);

            //Guarda la operacion en base de datos
            Intercambio intercambio = new Intercambio();
            intercambio.setMonto(request.getMonto());
            intercambio.setMonedaOrigen(request.getMonedaOrigen());
            intercambio.setMonedaDestino(request.getMonedaDestino());
            intercambio.setMontoCambio(montoConvertido);
            intercambio.setTipoCambio(tasaIntercambio);
            intercambioRepository.save(intercambio);

            //Envia respuesta al cliente
            IntercambioResponse intercambioResponse = new IntercambioResponse();
            intercambioResponse.setMonto(request.getMonto());
            intercambioResponse.setMontoCambio(montoConvertido);
            intercambioResponse.setMonedaOrigen(request.getMonedaOrigen());
            intercambioResponse.setMonedaDestino(request.getMonedaDestino());
            intercambioResponse.setTipoCambio(tasaIntercambio);

            return intercambioResponse;
        }

        public List<IntercambioResponse> obtenerIntercambios() {

            List<Intercambio> intercambioEntities = intercambioRepository.findAll();

            return intercambioEntities.stream()
                    .map(intercambio -> mapToDomain(intercambio))
                    .collect(Collectors.toList());
        }

        private IntercambioResponse mapToDomain(Intercambio intercambio) {

            IntercambioResponse intercambioResponse = new IntercambioResponse();
            intercambioResponse.setMonedaOrigen(intercambio.getMonedaOrigen());
            intercambioResponse.setMonedaDestino(intercambio.getMonedaDestino());
            intercambioResponse.setTipoCambio(intercambio.getTipoCambio());
            intercambioResponse.setMonto(intercambio.getMonto());
            intercambioResponse.setMontoCambio(intercambio.getMontoCambio());

            return intercambioResponse;
        }

    }