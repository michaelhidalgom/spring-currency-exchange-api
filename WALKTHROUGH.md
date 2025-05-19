# Guía de uso de la API de Intercambio de Monedas

Esta guía muestra cómo utilizar la API de intercambio de monedas paso a paso.

## 1. Autenticación en la API

Primero, necesitas obtener un token de acceso enviando tus credenciales:

```bash
curl --location 'http://localhost:8090/api/auth/login' \
--header 'Content-Type: application/json' \
--data '{
	"username": "luis",
	"password": "luis123"
}'
```

![Envío de credenciales en Postman](https://github.com/user-attachments/assets/7316bb75-e4df-4823-8fea-7d7d40e171c6)

## 2. Obtención del token de acceso

Al enviar la solicitud de autenticación, la API te retornará un token de acceso que deberás utilizar en las siguientes peticiones:

![Respuesta con token de acceso](https://github.com/user-attachments/assets/4481e781-d5fa-4ad5-a466-806fa6e28e7f)

## 3. Configuración del token para solicitudes

Para realizar cualquier solicitud a la API, debes incluir el token de acceso en el encabezado de autorización:

```bash
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsdWlzIiwiaWF0IjoxNzQ3NjY2NDQ2LCJleHAiOjE3NDgyNzEyNDZ9.W7dLQcDcDSxkId8FqH6DO40i0CPRu3zaP8Ei5vNcdtBOJ1k5dBHCf9zWvzmD2jS9NF18bjnmfveGqunJ501QOg'
```

![Configuración del token en Postman](https://github.com/user-attachments/assets/ad235e2c-8991-4c62-9822-ca7490c0964a)

## 4. Procesamiento de intercambio de monedas

Una vez autenticado, puedes realizar una solicitud de cambio de moneda especificando el monto, la moneda de origen y la moneda de destino:

```bash
curl --location 'http://localhost:8090/api/intercambios/procesa' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsdWlzIiwiaWF0IjoxNzQ3NjY2NDQ2LCJleHAiOjE3NDgyNzEyNDZ9.W7dLQcDcDSxkId8FqH6DO40i0CPRu3zaP8Ei5vNcdtBOJ1k5dBHCf9zWvzmD2jS9NF18bjnmfveGqunJ501QOg' \
--data '{
    "monto": 100.00,
    "monedaOrigen": "USD",
    "monedaDestino": "PEN"
}'
```

![Solicitud de cambio de moneda y respuesta](https://github.com/user-attachments/assets/91017796-e670-4a0f-82d1-96550039437a)

## 5. Listado de todos los intercambios

Para obtener un listado de todos los intercambios realizados, envía una solicitud al endpoint correspondiente con el token de autorización:

```bash
curl --location 'http://localhost:8090/api/intercambios' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsdWlzIiwiaWF0IjoxNzQ3NjY2NDQ2LCJleHAiOjE3NDgyNzEyNDZ9.W7dLQcDcDSxkId8FqH6DO40i0CPRu3zaP8Ei5vNcdtBOJ1k5dBHCf9zWvzmD2jS9NF18bjnmfveGqunJ501QOg' \
--data ''
```

![Listado de intercambios](https://github.com/user-attachments/assets/3cec4dd2-d3be-4d9e-a15c-461828feef9a)


