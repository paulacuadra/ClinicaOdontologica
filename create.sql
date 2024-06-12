DROP TABLE IF EXISTS DOMICILIOS;
CREATE TABLE DOMICILIOS (
ID INT AUTO_INCREMENT PRIMARY KEY,
CALLE VARCHAR(50) NOT NULL,
NUMERO INT NOT NULL,
LOCALIDAD VARCHAR(50) NOT NULL,
PROVINCIA VARCHAR(50) NOT NULL);

DROP TABLE IF EXISTS PACIENTES;
CREATE TABLE PACIENTES(
ID INT AUTO_INCREMENT PRIMARY KEY,
NOMBRE VARCHAR(50) NOT NULL,
APELLIDO VARCHAR(50) NOT NULL,
DNI VARCHAR(50) NOT NULL,
FECHA_INGRESO DATE NOT NULL,
ID_DOMICILIO INT);

INSERT INTO DOMICILIOS VALUES (DEFAULT, 'SIEMPRE VIVA', 123, 'SAN PEDRO','JUJUY');
INSERT INTO DOMICILIOS VALUES (DEFAULT, 'LAA PAJARERA', 123, 'NEZAHUALCOYOTL','ESTADO DE MEXICO');

INSERT INTO PACIENTES VALUES (DEFAULT, 'COSME','FULANITO', '1545646', '2024-02-15', 1);
INSERT INTO PACIENTES VALUES (DEFAULT, 'MARIA','MORENO', '39', '2024-02-15', 2);

DROP TABLE IF EXISTS ODONTOLOGOS;
CREATE TABLE ODONTOLOGOS(
ID INT AUTO_INCREMENT PRIMARY KEY,
NRO_MATRICULA INT NOT NULL,
NOMBRE VARCHAR(50) NOT NULL,
APELLIDO VARCHAR(50) NOT NULL);


INSERT INTO ODONTOLOGOS VALUES (DEFAULT, 1111,'Roberto','Garcia');
INSERT INTO ODONTOLOGOS VALUES (DEFAULT, 2222,'Maria','Lopez');