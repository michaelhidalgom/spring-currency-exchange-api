# Desafío Técnico - Descripción

Desarrollar una API con las siguientes consideraciones:

## Consideraciones técnicas:
* El lenguaje de programación a utilizar es **Java** con Spring Boot.

## Funcionalidades Requeridas:
* Debe consumir servicio gratuito para tipo de cambio ([https://open.er-api.com/v6/latest/USD](https://open.er-api.com/v6/latest/USD))
* Se debe crear una API para aplicar un tipo de cambio a un monto, esta api debe consumir el servicio gratuito usando Feign.
* La API debe recibir el valor **"monto"**, **"moneda origen"**, **"moneda destino"** y devolver el 
  **"monto"**, **"monto con tipo de cambio"**, **"moneda origen"**, **"moneda destino"** y **"tipo de cambio"**.
* Se debe almacenar todo los cambios realizados en una *in memory database*, por ejemplo H2.
* El uso de la API debe ser mostrada desde Postman.

## Funcionalidades Opcionales:
* Crear una API para listar todos los registros guardados del tipo de cambio.
* Implementar un nivel de seguridad en la consulta (JWT).
