-- Crear tabla de usuarios manualmente para control total del esquema
CREATE TABLE IF NOT EXISTS usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    enabled BOOLEAN NOT NULL
);

-- Crear tabla de intercambios manualmente para control total del esquema
CREATE TABLE IF NOT EXISTS intercambio (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    monto DECIMAL(19,2),
    moneda_origen VARCHAR(3),
    moneda_destino VARCHAR(3),
    monto_cambio DECIMAL(19,2),
    tipo_cambio DECIMAL(19,10)
);

-- INSERTAR USUARIOS DE PRUEBA
INSERT INTO usuarios (username, password, enabled, email) VALUES
('luis', '$2a$10$biNjUd2t8f2PXlcD7txFi.nO0nCtWXzPhJyYMmOMK5z.BrTXpg4sm', true, 'luis@dimensionjava.com');

INSERT INTO usuarios (username, password, enabled, email) VALUES
('marisol', '$2a$10$yOuDJ4Hpio3Y1KD6hYJEZOWJidy7PWXPO8r.AjGsG7CvMLH4E4STu', true, 'marisol@dimensionjava.com');

-- INSERTAR INTERCAMBIO DE EJEMPLO (USD a EUR)
INSERT INTO intercambio (monto, moneda_origen, moneda_destino, monto_cambio, tipo_cambio) VALUES
(1000.00, 'USD', 'EUR', 850.50, 0.8505);