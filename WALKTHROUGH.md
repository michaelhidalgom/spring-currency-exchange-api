 
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

![image](https://github.com/user-attachments/assets/138a52d4-bf03-4bec-80ac-7f47b7800879)

2. Para listar todos los cambios de moneda, se ingresa el token y se realiza
   la solicitud

```bash
curl --location 'http://localhost:8090/api/intercambios' \
--data ''
```
![image](https://github.com/user-attachments/assets/b12b8dad-cede-4479-989d-502f7aef829b)








