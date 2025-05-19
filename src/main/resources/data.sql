
--USUARIOS
INSERT INTO `usuarios` (username, password, enabled, email)  /* password: luis123*/
VALUES ('luis','$2a$10$biNjUd2t8f2PXlcD7txFi.nO0nCtWXzPhJyYMmOMK5z.BrTXpg4sm',1, 'user@bolsadeideas.com');

INSERT INTO `usuarios` (username, password, enabled, email)  /* password: mari123*/
VALUES ('marisol','$2a$10$yOuDJ4Hpio3Y1KD6hYJEZOWJidy7PWXPO8r.AjGsG7CvMLH4E4STu',1,'admin@bolsadeideas.com');

--ROLES
INSERT INTO `roles` (authority)
VALUES ('ROLE_USER');

INSERT INTO `roles` (authority)
VALUES ('ROLE_ADMIN');

--USUARIOS_ROLES
INSERT INTO `usuarios_roles` (usuario_id, rol_id) VALUES (1, 1);
INSERT INTO `usuarios_roles` (usuario_id, rol_id) VALUES (2, 1);
INSERT INTO `usuarios_roles` (usuario_id, rol_id) VALUES (2, 2);


