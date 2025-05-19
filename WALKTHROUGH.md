
1. Se ingresan los datos de cambio, se realiza la solicitud y se recibe
   la respuesta esperada

```bash
curl --location 'http://localhost:8090/api/intercambios/procesa' \
--header 'Content-Type: application/json' \
--data '{
    "monto": 100.00,
    "monedaOrigen": "USD",
    "monedaDestino": "PEN"
}'
```

![alt text](image.png)

2. Para listar todos los cambios de moneda, se ingresa el token y se realiza
   la solicitud

```bash
curl --location 'http://localhost:8090/api/intercambios' \
--data ''
```
![alt text](image-1.png)







