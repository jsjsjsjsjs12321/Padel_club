DROP DATABASE IF EXISTS padel_club;
CREATE DATABASE padel_club;
USE padel_club;

CREATE TABLE `usuario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(40) DEFAULT NULL,
  `apellidos` varchar(60) DEFAULT NULL,
  `dni` varchar(10) DEFAULT NULL,
  `telefono` int DEFAULT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  `edad` int DEFAULT NULL,
  esAdmin boolean,
  esBaja boolean,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `pistas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `numPista` int NOT NULL,
  mantenimiento boolean,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `disponibilidad_pistas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dia` varchar(10) DEFAULT NULL,
  `hora` varchar(2) DEFAULT NULL,
  `franjaHoraria` varchar(20) DEFAULT NULL,
  esPagado boolean,
   idUsuario int not null,
  idPista int not null,
  PRIMARY KEY (`id`),
  FOREIGN KEY (idUsuario) REFERENCES usuario(id),
  FOREIGN KEY (idPista) REFERENCES pistas(id)
) ENGINE=InnoDB;
