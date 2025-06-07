-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         8.4.3 - MySQL Community Server - GPL
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.8.0.6908
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Volcando estructura para tabla moduloinventario.atencion
CREATE TABLE IF NOT EXISTS `atencion` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `_05error` varchar(255) NOT NULL,
  `_04especificacion` varchar(255) NOT NULL,
  `_09estado` int NOT NULL,
  `_07fechaatencion` date NOT NULL,
  `_02fechasolicitud` date NOT NULL,
  `_08horaatencion` varchar(255) NOT NULL,
  `_03horasolicitud` varchar(255) NOT NULL,
  `_06solucion` varchar(255) NOT NULL,
  `_01idequipo` bigint NOT NULL,
  `_10idpertenece` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2xyp4asxnk5yl2kg0ln8lgrr3` (`_01idequipo`),
  KEY `FK4qmgjg05hrioxehf5nru1lrjv` (`_10idpertenece`),
  CONSTRAINT `FK2xyp4asxnk5yl2kg0ln8lgrr3` FOREIGN KEY (`_01idequipo`) REFERENCES `equipo` (`id`),
  CONSTRAINT `FK4qmgjg05hrioxehf5nru1lrjv` FOREIGN KEY (`_10idpertenece`) REFERENCES `pertenece` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla moduloinventario.atencion: ~1 rows (aproximadamente)
INSERT INTO `atencion` (`id`, `_05error`, `_04especificacion`, `_09estado`, `_07fechaatencion`, `_02fechasolicitud`, `_08horaatencion`, `_03horasolicitud`, `_06solucion`, `_01idequipo`, `_10idpertenece`) VALUES
	(1, 'no prende', 'arreglo laptop', 0, '2025-04-21', '2025-04-21', '09:00', '08:30', 'revisar', 8, 2);

-- Volcando estructura para tabla moduloinventario.componentespc
CREATE TABLE IF NOT EXISTS `componentespc` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `_03capacidad` varchar(255) NOT NULL,
  `_07cortapico` varchar(255) NOT NULL,
  `_08detalle` varchar(255) NOT NULL,
  `_06disco` varchar(255) NOT NULL,
  `_01fuente` varchar(255) NOT NULL,
  `_02memorias` int NOT NULL,
  `_04micro` varchar(255) NOT NULL,
  `_05microcapacidad` varchar(255) NOT NULL,
  `_10mouse` varchar(255) NOT NULL,
  `_09teclado` varchar(255) NOT NULL,
  `_11versionamiento` varchar(255) DEFAULT NULL,
  `_12idequipo` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2pcovtc1dkyxvff7erpay8hb1` (`_12idequipo`),
  CONSTRAINT `FK2pcovtc1dkyxvff7erpay8hb1` FOREIGN KEY (`_12idequipo`) REFERENCES `equipo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla moduloinventario.componentespc: ~8 rows (aproximadamente)
INSERT INTO `componentespc` (`id`, `_03capacidad`, `_07cortapico`, `_08detalle`, `_06disco`, `_01fuente`, `_02memorias`, `_04micro`, `_05microcapacidad`, `_10mouse`, `_09teclado`, `_11versionamiento`, `_12idequipo`) VALUES
	(1, '16GB', 'Tripp Lite 6 tomas', 'Equipo de oficina para tareas administrativas', 'SSD 500GB', 'EVGA 650W 80+', 2, 'Intel Core i5', '2.5GHz', 'Logitech M170', 'Logitech K380', NULL, 8),
	(2, '8GB', 'CORT', 'det', 'string', 'AH 650W 70+', 1, 'Intel Core i7', '2.0GHz', 'Logitech', 'Logitech', NULL, 13),
	(3, '8GB', 'si', 'buen estado', '2TB', 'si', 4, '1', '2.8', 'si', 'si', NULL, 14),
	(4, '8GB', 'si', 'buen estado', '2TB', 'si', 4, '1', '2.8', 'si', 'si', NULL, 15),
	(5, '32GB DDR4', 'si', 'Equipo de alta gama para renderizado', 'SSD 1TB + HDD 2TB', '850W 80+ Gold', 2, 'Intel Core i9-12900K', '3.2 GHz', 'Logitech MX Master 3', 'Logitech MX Keys', NULL, 18),
	(6, '64GB DDR4 ECC', 'si', 'Equipo de alto rendimiento para virtualización y desarrollo', 'SSD NVMe 2TB + HDD 4TB RAID', '1000W Certificada 80+ Platinum', 4, 'AMD Threadripper PRO 5995WX', '4.5 GHz', 'Lenovo ThinkPad Mouse', 'Lenovo ThinkPad TrackPoint II', 'V2.5', 19),
	(7, '8GB DDR2', '', 'TARJETA MADRE: INTEL DH67BL 1300 MHZ / TARJETA DE VIDEO: 1GB GFORCE / TARJETA DE SONIDO: CODEC REALTEK / TARJETA DE RED: LAN / LECTOR CD-DVD 22X MULTIFORMATO / LECTOR DE MEMORIAS 5 FORMATOS / QUEMADOR DE CD-DVD LG / PARLANTES 2 MINI DELUX', '1 TB ULTRA ATA SATA II, 7200 RPM', 'ATX DELUX COLOR NEGRO CON VIVO ROJO', 1, 'Intel Core i7', '3.06 GHz', 'OPTICO DELUX MODELO M110, SERIE M110111002367, COLOR NEGRO, PAD AZUL CON GEL', 'DELUX MODELO K3100, SERIE K31001111002367, COLOR NEGRO', NULL, 20),
	(8, '8GB DDR4', '', 'TARJETA MADRE: ASUS H310 / TARJETA DE VIDEO: NVIDIA GFORCE 2GB / QUEMADOR DE CD-DVD MULTIFORMATO LG / PARLANTE: COLOR NEGRO', '1 TB', 'DELUX COLOR NEGRO', 1, 'Intel Core i5', '3.0 GHz, 8va generación', 'OPTICO DELUX, MODELO M133, SERIE M1338C000568', 'MULTIMEDIA DELUX, MODELO K3100, SERIE K31008C003684, COLOR NEGRO', '', 21),
	(9, '8 GB', 'No especificado', 'TARJETA MADRE INTEL / PARLANTES DELL x2 (CN-042DJY-77801-560-011), TECLADO DELL (CN-DF2JV2-71676-67L1HTN-AO), MOUSE DELL NEGRO', 'HDD 1TB', 'No especificada', 1, 'Intel Core i5-6400', '2.7 GHz, 6ta generación', 'Dell Negro', 'Dell CN-DF2JV2-71676-67L1HTN-AO', '', 24),
	(10, '1 TB, 5400 RPM', '', 'Tarjeta de video: NVIDIA GeForce, tarjeta de red: integrado 10/100/1000', '1 TB HDD', '', 8, 'Intel Core i7', '3.0 GHz, 6 núcleos, 8va generación', '', '', '', 27),
	(11, '1 TB', 'Cortapicos, 6 contactos, pad mouse', 'Tarjeta madre ASUS Prime B360M, tarjeta de video MSI GT710, sonido integrado, red 10/100/1000, lector de CD/DVD 24X SATA, case ATX Delux', 'SATA 1 TB', '', 8, 'Intel Core i7', '4.6 GHz Turbo Boost, 9na generación', 'Óptico Delux negro', 'Delux negro', '', 32),
	(12, '596 GB', '', 'Tarjeta madre ASUS IMIMV.CF, tarjeta de video GeForce 256 MB DDR2, lector de CD/DVD Super Multi 8X, parlantes incorporados.', 'SATA 596 GB', '', 4, 'Intel Core 2 Duo T6400', '2000 MHz', 'HP modelo RM7132A, serie SJ849MJ07984', 'HP modelo RK713A, serie SJ849KBJ07984', '', 42),
	(13, '1 TB', 'Cortapicos, 6 contactos, pad mouse', 'ASUS Prime B360M, MSI GT710, sonido integrado, red 10/100/1000, lector CD/DVD 24X, case ATX Delux', 'SATA 1 TB', '', 8, 'Intel Core i7', '4.6 GHz Turbo Boost, 9na generación', 'Óptico Delux negro', 'Delux negro', '', 49),
	(14, '1 TB', 'Cortapicos, 6 contactos, pad mouse', 'Tarjeta madre ASUS Prime B360M, tarjeta de video MSI GT710, sonido integrado, red 10/100/1000, lector de CD/DVD 24X SATA, case ATX Delux', 'SATA 1 TB', '', 8, 'Intel Core i7', '4.6 GHz Turbo Boost, 9na generación', 'Óptico Delux negro', 'Delux negro', '', 50);

-- Volcando estructura para tabla moduloinventario.equipo
CREATE TABLE IF NOT EXISTS `equipo` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `_01codigo` varchar(255) NOT NULL,
  `_06detalle` varchar(5000) NOT NULL,
  `_02mac_serie` varchar(255) NOT NULL,
  `_03marca` varchar(255) NOT NULL,
  `_05modelo` varchar(255) NOT NULL,
  `_04id_tipo` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKa3fhft5xts6x0rwwjyglb3i46` (`_04id_tipo`),
  CONSTRAINT `FKa3fhft5xts6x0rwwjyglb3i46` FOREIGN KEY (`_04id_tipo`) REFERENCES `tipo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla moduloinventario.equipo: ~47 rows (aproximadamente)
INSERT INTO `equipo` (`id`, `_01codigo`, `_06detalle`, `_02mac_serie`, `_03marca`, `_05modelo`, `_04id_tipo`) VALUES
	(1, '848464AQ', 'televisor', '789765424s', 'SONNY', 'JP12', 2),
	(5, '788214LR', 'impresora', '567987m', 'HP', 'LT12HP', 2),
	(6, '7889461W', 'laptop', '54887ss', 'DELL', 'DELL145k', 1),
	(8, '788214LR', 'laptop', '567987m', 'HP', 'LT12HP', 1),
	(9, '1225184', 'Telefono IP Unidad TIC EXT 9200', '00-15-65-82-67-FD', 'yealink', 'T23P', 4),
	(10, '1144470', 'Impresora Laser Cartucho Toner 35A', 'HPK-BOISB-0605-00', 'HP', 'HP Laser JET P1006', 3),
	(11, '1147086', 'Monitor desarrollo unitad TIC', '1147086', 'SONNY', 'BRAVIA', 2),
	(12, '123456789', 'prueba', '10000', 'prueba', 'mod', 2),
	(13, '65941', 'fhfgky', 'mas123', 'dell', 'modelo a1', 1),
	(14, '1241017', 'KRL4', '0C-D9-92-1B-E1-C6', 'AZUSS', 'AZUSS', 1),
	(15, '1241017', 'KRL4', '0C-D9-92-1B-E1-C6', 'AZUSS', 'AZUSS', 1),
	(17, 'EQ-2025-001', 'monitor', '00:1A:2B:3C:4D:5E', 'HP', 'EliteDesk 800 G6', 2),
	(18, 'CPU-2025-001', 'cpu', 'AA:BB:CC:DD:EE:FF', 'Dell', 'Precision 5820', 1),
	(19, 'CPU-2025-002', 'Workstation para desarrollo y análisis de datos', '54:EF:A1:B2:C3:D4', 'Lenovo', 'ThinkStation P620', 1),
	(20, '1190477', 'EQUIPO DE COMPUTACION CORE I7, MONITOR LCD SAMSUNG 18.5 PULGADAS', 'ZTYRPHYJBB00603T', 'Samsung', 'SyncMaster S19A300N', 1),
	(21, '1235571', 'CPU DELUX, MONITOR NO INCLUIDO', 'K31008C003684', 'DELUX', 'CPU DELUX NEGRO', 1),
	(22, '1235573', 'MONITOR LED FULL HD CURVO, 32 PULGADAS, COLOR BLANCO, INCLUYE 1 CABLE DE PODER Y 1 CABLE HDMI', '050LHTHK800003V', 'Samsung', 'C32F391FWL', 2),
	(23, '1148455', 'IMPRESORA HP LASERJET PRO 400 M401N, COLOR NEGRO, PRODUCTO BRASILEÑO. ACCESORIOS: 1 CABLE DE PODER, CABLE USB, 1 CD INSTALADOR', 'BRFSDDJNY5', 'HP', 'CZ195A', 3),
	(24, '1215838', 'CPU COLOR NEGRO, INCLUYE PARLANTES, TECLADO Y MOUSE DELL NEGROS', 'GDY7M82', 'Dell', 'Vostro 3250', 1),
	(25, '1225182', 'APARATO TELEFÓNICO IP, COLOR NEGRO-PLOMO', '4123114110102377', 'Yealink', 'SIP-T23P', 4),
	(26, '1147086', 'Televisor LCD, 40 pulgadas, color negro, 1 control remoto Sony RM-YD035, 1 cable poder, 1 pedestal', 'S01-8509131', 'Sony', 'JKLD40EX605', 2),
	(27, '1244230', 'Computadora portátil color negro, pantalla 15.6 pulgadas, 1 cable poder con adaptador, tipo de case: notebook', 'K4NRCX00J45316B', 'Asus', 'GL503G', 1),
	(28, '1256685', 'Switch 24 puertos PoE+, color negro, procedencia China, 1 cable poder', '219801A2HSP24600001L', 'H3C', 'S5170-28S-HPWR-EI', 6),
	(29, '1148453', 'Televisor LED, 46 pulgadas, color negro, ensamblado en México. Accesorios: 1 control remoto color negro, 1 cable poder, 1 pedestal con 8 tornillos, 2 lentes 3D', 'Z5P33CVD300059', 'Samsung', 'UN46E6030G', 2),
	(30, '1144470', 'Impresora, color negro-plomo. Accesorios: 1 cable poder, 1 cable USB Omega, 1 CD, 1 manual', 'VNC4206906', 'HP', 'LaserJet P1006', 3),
	(31, '1231344', 'Switch Catalyst 2960, color plomo, 24 puertos', 'FOC2201Y0XZ', 'Cisco', 'WS-C2960+24TC-L', 6),
	(32, '1241077', 'Computadora de escritorio. Tarjeta madre ASUS Prime B360M. Memoria RAM de 8 GB DDR4 a 3 GHz. Tarjeta de video MSI GT710. Tarjeta de sonido integrada. Tarjeta de red 10/100/1000. Lector de CD/DVD 24X tipo SATA.', '', 'Delux', 'ATX Torre', 1),
	(33, '1161521', 'HUB de 24 puertos, incluye 2 puertos para fibra óptica.', '', 'SISCOM SYSTEM', 'Catalyst 2950', 6),
	(34, '1148777', 'Proyector color negro, con 2 parlantes incorporados. Accesorios: control, lápiz interactivo con soporte y cables, cable VGA, USB, cargador y CD.', 'BKPB31500292', 'INFOCUS', 'IN 3926', 2),
	(35, '1256150', 'Proyector, color blanco, procedencia China. Accesorios: cable de poder.', 'CRNP31700049', 'INFOCUS', 'P162 / IN114X', 2),
	(36, '1256151', 'Proyector, color blanco, procedencia Filipinas. Accesorios: cable de poder, cable HDMI y control remoto.', 'X8B24504178', 'EPSON', 'PowerLite E20 / H981A', 2),
	(37, '1161206', 'Televisor de 24 pulgadas, color negro. Accesorios: cable de poder, adaptador, cable VGA y CD.', '407MXEZA9700', 'LG', '24MT45A-PM', 2),
	(38, '1001147', 'Televisor LED Full HD Smart TV 3D, color negro, pantalla de 50 pulgadas. Accesorios: control remoto RM-YD099, adaptador SONY negro, soporte metálico de mesa color plomo y 2 lentes 3D.', '7001309', 'SONY', 'KDL-50W805B', 2),
	(39, '1048703', 'Televisor LED LG de 42 pulgadas, color negro. Accesorios: cable de poder, control remoto y 2 patas laterales.', '403MXMT8V387', 'LG', '42LB550V', 2),
	(40, '1048705', 'Cámara fotográfica digital, color negro. Accesorios: lente Nikon DX 18-55mm (serie 55510797), cargador, batería, enchufe oblicuo, correa negro-amarillo, cable AV, cable USB', '8402581', 'Nikon', 'D5100', 4),
	(41, '1236354', 'Cámara filmadora, color negro, 24 megapíxeles. Accesorios: 1 batería Nikon EN-EL15, 1 cargador de batería, 1 cable poder, 1 cable USB, 1 correa, 1 lente 18-140 mm (serie 30439832), 1 lente 18-55 mm (serie 21429617) y 1 lente 50 mm (serie 3375458).', '9533344', 'Nikon', 'D7100', 4),
	(42, '1144375', 'Equipo de computación todo en uno HP Touch Smart IQ 506, color negro. Procesador: Mobile Intel Core 2 Duo T6400, 2000 MHz. Memoria: 4060 MB DDR2 SDRAM. Disco duro SATA de 596 GB. Monitor LCD HP Touch Smart de 22 pulgadas, producto NC700AAR, serie 3CU9080F6Y. Accesorios: control remoto HP para TV, teclado HP modelo RK713A (serie SJ849KBJ07984), mouse HP modelo RM7132A (serie SJ849MJ07984), todos color negro.', '3CU9080F6Y', 'HP', 'Touch Smart IQ 506', 1),
	(43, '1145510', 'Cámara filmadora Panasonic. Accesorios: batería Panasonic modelo CGA-DU14, cargador Panasonic modelo VSK0651 (serie K6047200DC), control a distancia Panasonic, control remoto Panasonic, batería adaptador, cable USB, cable AV/S-Video.', 'L6HC00070R', 'Panasonic', 'NV-GS500', 4),
	(44, '1145515', 'Cámara fotográfica. Accesorios: cable USB, CD instalador, memoria Sony Memory Stick PRO Duo de 1 GB, manual.', '0149145', 'Sony', 'Cyber-shot DSC-S650', 4),
	(45, '1148431', 'Televisor LED Smart 46 pulgadas, color negro, ensamblado en México. Accesorios: 1 control remoto Samsung color negro, 1 cable poder, 1 pedestal con 8 tornillos.', 'ZS7Q3CAC701139K', 'Samsung', 'UN46EH5300G', 2),
	(46, '1186040', 'Impresora multifunción, color negro, pantalla touch. Accesorios: 1 cable poder, 1 cable USB, 1 CD.', 'SCNG8G1R958', 'HP', 'LaserJet 1536DNF', 3),
	(47, '1225181', 'Aparato telefónico IP, color negro-plomo.', '4123114110102692', 'Yealink', 'SIP-T23P', 4),
	(48, '1227783', 'Impresora fotográfica, color negro. Cuenta con 6 tintas de 6 colores en estuche lateral. Accesorios: 1 cable poder, 1 cable USB.', 'UBHY005770', 'Epson', 'L1800 B472C', 3),
	(49, '1228212', 'CPU Intel Core i7 9na gen. 4.6 GHz Turbo Boost, RAM 8 GB DDR4, HDD 1 TB SATA, placa madre ASUS Prime B360M, tarjeta de video MSI GT710, sonido integrado, red 10/100/1000, lector CD/DVD 24X SATA. Case Delux ATX negro. Accesorios: teclado y mouse óptico Delux negros, cortapicos, 6 contactos, pad mouse.', '', 'Delux', 'ATX Torre', 1),
	(50, '1241089', 'Computadora de escritorio con procesador Intel Core i7, 4.6 GHz Turbo Boost, 9na generación. Memoria RAM de 8 GB DDR4 a 3 GHz. Disco duro SATA de 1 TB con cooler.', '', 'Delux', 'ATX Torre', 1),
	(51, '1241101', 'Monitor LED de 24 pulgadas color negro. Accesorios: cable poder, adaptador, cable VGA, cable HDMI.', 'ZZPCH4ZM401402R', 'Samsung', 'S24F350FHL', 2),
	(52, '1241108', 'Monitor LED de 24 pulgadas, color negro. Accesorios: cable poder, adaptador, cable VGA, cable HDMI.', 'ZZPCH4ZM401338R', 'Samsung', 'S24F350FHL', 2);

-- Volcando estructura para tabla moduloinventario.pertenece
CREATE TABLE IF NOT EXISTS `pertenece` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `_01cif` bigint NOT NULL,
  `_05estado` varchar(255) NOT NULL,
  `_03fechaadd` date NOT NULL,
  `_04fechadel` date NOT NULL,
  `_02idequipo` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbk0loxhwg8sbbk86iiy4fbdvi` (`_02idequipo`),
  CONSTRAINT `FKbk0loxhwg8sbbk86iiy4fbdvi` FOREIGN KEY (`_02idequipo`) REFERENCES `equipo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla moduloinventario.pertenece: ~43 rows (aproximadamente)
INSERT INTO `pertenece` (`id`, `_01cif`, `_05estado`, `_03fechaadd`, `_04fechadel`, `_02idequipo`) VALUES
	(1, 11, 'inactivo', '2025-03-21', '2025-04-21', 1),
	(2, 22, 'activo', '2025-01-30', '2025-12-30', 8),
	(3, 33, 'inactivo', '2025-03-21', '2025-12-21', 6),
	(4, 20903198600, 'activo', '2025-03-10', '2025-08-30', 9),
	(5, 1234, 'inactivo', '2025-03-07', '2025-06-07', 12),
	(6, 8888, 'activo', '2025-01-07', '2025-11-07', 13),
	(7, 20903198600, 'activo', '2025-10-03', '2025-08-30', 14),
	(8, 20903198600, 'activo', '2025-10-03', '2025-08-30', 15),
	(9, 20903198600, 'activo', '2025-05-01', '2025-12-30', 17),
	(10, 20905678900, 'activo', '2025-02-02', '2025-07-27', 18),
	(11, 20907654321, 'activo', '2025-05-05', '2025-12-31', 19),
	(12, 21610197600, 'activo', '2025-03-14', '2025-12-14', 20),
	(13, 12611198700, 'activo', '2025-01-14', '2025-11-14', 21),
	(14, 12611198700, 'activo', '2025-01-14', '2025-12-14', 22),
	(15, 13103199500, 'activo', '2025-05-14', '2025-12-14', 23),
	(16, 13103199500, 'activo', '2025-01-14', '2025-10-14', 24),
	(17, 13103199500, 'activo', '2025-01-14', '2025-12-14', 25),
	(18, 20903198600, 'inactivo', '2025-01-19', '2025-12-19', 26),
	(19, 20903198600, 'activo', '2025-01-19', '2025-11-19', 27),
	(21, 20903198600, 'activo', '2025-01-19', '2025-12-19', 28),
	(22, 20903198600, 'activo', '2025-01-19', '2025-11-19', 29),
	(23, 20903198600, 'activo', '2025-01-19', '2025-11-19', 30),
	(24, 20903198600, 'activo', '2025-01-19', '2025-11-19', 31),
	(25, 20903198600, 'activo', '2025-01-19', '2025-12-19', 32),
	(26, 20903198600, 'activo', '2025-01-20', '2025-12-20', 33),
	(27, 20903198600, 'activo', '2025-01-20', '2025-12-20', 34),
	(28, 20903198600, 'activo', '2025-01-20', '2025-12-20', 35),
	(29, 20903198600, 'activo', '2025-01-20', '2025-12-20', 36),
	(30, 20903198600, 'activo', '2025-01-20', '2025-12-20', 37),
	(31, 20903198600, 'activo', '2025-01-20', '2025-12-20', 38),
	(32, 23110199500, 'activo', '2025-01-20', '2025-12-20', 39),
	(33, 23110199500, 'activo', '2025-01-20', '2025-12-20', 40),
	(34, 23110199500, 'activo', '2025-01-20', '2025-12-20', 41),
	(35, 23110199500, 'activo', '2025-01-20', '2025-12-20', 42),
	(36, 23110199500, 'activo', '2025-01-20', '2025-12-20', 43),
	(37, 23110199500, 'activo', '2025-01-20', '2025-12-20', 44),
	(38, 23110199500, 'activo', '2025-01-20', '2025-12-20', 45),
	(39, 23110199500, 'activo', '2025-01-20', '2025-12-20', 46),
	(40, 23110199500, 'activo', '2025-01-20', '2025-12-20', 47),
	(41, 23110199500, 'activo', '2025-01-20', '2025-12-20', 49),
	(42, 23110199500, 'activo', '2025-01-20', '2025-12-20', 50),
	(43, 23110199500, 'activo', '2025-01-20', '2025-12-20', 51),
	(44, 23110199500, 'activo', '2025-01-20', '2025-12-20', 52);

-- Volcando estructura para tabla moduloinventario.red
CREATE TABLE IF NOT EXISTS `red` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `_03dns` varchar(255) NOT NULL,
  `_07fecharegistro` date NOT NULL,
  `_01ip` varchar(255) NOT NULL,
  `_06puerto` varchar(255) NOT NULL,
  `_02segmento` varchar(255) NOT NULL,
  `_05switch` varchar(255) NOT NULL,
  `_04vlan` varchar(255) NOT NULL,
  `_08idequipo` bigint NOT NULL,
  `_09estado` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK56vf3cqt9550ptnppstjsv3u0` (`_06puerto`),
  KEY `FKrpa2l6tynh4e7kjckpdswwjkj` (`_08idequipo`),
  CONSTRAINT `FKrpa2l6tynh4e7kjckpdswwjkj` FOREIGN KEY (`_08idequipo`) REFERENCES `equipo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla moduloinventario.red: ~5 rows (aproximadamente)
INSERT INTO `red` (`id`, `_03dns`, `_07fecharegistro`, `_01ip`, `_06puerto`, `_02segmento`, `_05switch`, `_04vlan`, `_08idequipo`, `_09estado`) VALUES
	(1, '8.8.8.8', '2025-05-01', '192.168.1.25', 'P2-E25', 'Administración', 'Switch-Principal-P2', 'VLAN10', 17, 1),
	(2, '8.8.8.8', '2025-05-02', '192.168.2.15', 'P3-E15', 'Diseño', 'Switch-Diseño-P3', 'VLAN20', 18, 1),
	(3, '8.8.8.8', '2025-05-05', '192.168.5.10', 'P4-E10', 'Desarrollo', 'Switch-Dev-P4', 'VLAN50', 19, 1),
	(4, '198.18.19.20', '2025-05-21', '172.16.114.212', '3', '255.255.255.128', '10.11.206.120', '175', 33, 0),
	(5, '198.18.20.15', '2025-05-26', '172.16.114.214', '2', '255.255.255.128', '10.11.206.120', '176', 33, 1);

-- Volcando estructura para tabla moduloinventario.software
CREATE TABLE IF NOT EXISTS `software` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `_03estado_licencia` varchar(255) NOT NULL,
  `_06fecha` date NOT NULL,
  `_01nombre` varchar(255) NOT NULL,
  `_04tipo` varchar(255) NOT NULL,
  `_02version` varchar(255) NOT NULL,
  `_05idequipo` bigint NOT NULL,
  `_07estado` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhog17vghvxv0fn87jucdmibtr` (`_05idequipo`),
  CONSTRAINT `FKhog17vghvxv0fn87jucdmibtr` FOREIGN KEY (`_05idequipo`) REFERENCES `equipo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla moduloinventario.software: ~2 rows (aproximadamente)
INSERT INTO `software` (`id`, `_03estado_licencia`, `_06fecha`, `_01nombre`, `_04tipo`, `_02version`, `_05idequipo`, `_07estado`) VALUES
	(1, 'Activado', '2025-05-01', 'Windows 11 Pro', 'Sistema Operativo', '22H2', 17, 1),
	(2, 'Suscripción Activa', '2025-05-02', 'Adobe Creative Cloud', 'Suite de Diseño', '2025', 18, 1),
	(3, 'Licencia Corporativa', '2025-05-05', 'JetBrains All Products Pack', 'Suite de Desarrollo', '2025.1', 19, 1);

-- Volcando estructura para tabla moduloinventario.tipo
CREATE TABLE IF NOT EXISTS `tipo` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `_04detalle` varchar(255) NOT NULL,
  `_03icono` varchar(255) NOT NULL,
  `_02nombre` varchar(255) NOT NULL,
  `_01sigla` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla moduloinventario.tipo: ~6 rows (aproximadamente)
INSERT INTO `tipo` (`id`, `_04detalle`, `_03icono`, `_02nombre`, `_01sigla`) VALUES
	(1, 'Computadoras, Servidores, laptops', 'cil-screen-smartphone', 'Computadora', 'CPU'),
	(2, 'Monitores / Televisores', 'cil-monitor', 'Monitor', 'Monitor'),
	(3, 'Impresoras / Perifericos de Salidas', 'cil-print', 'Impresoras', 'Impresora'),
	(4, 'Telefonos / Equipos de Comunicacion', 'cil-rss', 'Telefonos', 'Telefonos'),
	(5, 'Equipos de Registro y control de tiempo', 'cil-monitor', 'Biometrico', 'Biometrico'),
	(6, 'Equipos de Comunicacion e Infraestructura de Red', 'cil-monitor', 'Switch', 'Switch');

-- Volcando estructura para tabla moduloinventario.ubicacion
CREATE TABLE IF NOT EXISTS `ubicacion` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `_02ambiente` varchar(255) NOT NULL,
  `_05fecha` date NOT NULL,
  `_03latitud` varchar(255) DEFAULT NULL,
  `_04longitud` varchar(255) DEFAULT NULL,
  `_01idequipo` bigint NOT NULL,
  `_06estado` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKg4t2i63m3mwdf8s519lrej619` (`_01idequipo`),
  CONSTRAINT `FKg4t2i63m3mwdf8s519lrej619` FOREIGN KEY (`_01idequipo`) REFERENCES `equipo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla moduloinventario.ubicacion: ~36 rows (aproximadamente)
INSERT INTO `ubicacion` (`id`, `_02ambiente`, `_05fecha`, `_03latitud`, `_04longitud`, `_01idequipo`, `_06estado`) VALUES
	(1, 'Oficina Administración 2do Piso', '2025-05-01', '-16.504047', '-68.133254', 17, 1),
	(2, 'Departamento de Diseño 3er Piso', '2025-05-02', '-16.504321', '-68.134567', 18, 1),
	(3, 'Departamento de Desarrollo 4to Piso', '2025-05-05', '-16.505987', '-68.132456', 19, 1),
	(4, 'OF. PAHUICHI TIPNIS', '2025-01-14', '', '', 20, 1),
	(5, 'OF. SECRETARIA CARRERA LINGUISTICA', '2025-02-14', '', '', 21, 1),
	(6, 'OF. SECRETARIA CARRERA LINGUISTICA', '2025-02-14', '', '', 22, 1),
	(7, 'AREÁ CONTABLE', '2025-05-14', '', '', 23, 1),
	(8, 'AREA CONTABLE 2', '2025-01-14', '', '', 24, 1),
	(9, 'AREÁ CONTABLE 2', '2025-02-14', '', '', 25, 1),
	(10, 'UNIDAD DE COMUNICACIÓN Y RELACIONES PUBLICAS', '2025-05-19', '', '', 26, 1),
	(11, 'UNIDAD DE COMUNICACIÓN Y RELACIONES PUBLICAS', '2025-01-19', '', '', 27, 1),
	(12, 'OF. DECANATO', '2025-01-19', '', '', 28, 1),
	(13, 'UTIC', '2025-01-19', '', '', 29, 1),
	(14, 'UTIC', '2025-01-19', '', '', 30, 1),
	(15, 'UTIC', '2025-01-19', '', '', 31, 1),
	(16, 'UTIC', '2025-01-19', '', '', 32, 1),
	(17, 'UTIC', '2025-01-20', '', '', 33, 0),
	(18, 'UTIC', '2025-01-20', '', '', 34, 1),
	(19, 'UTIC', '2025-01-20', '', '', 35, 1),
	(20, 'UTIC', '2025-01-20', '', '', 36, 1),
	(21, 'UTIC', '2025-01-20', '', '', 37, 1),
	(22, 'UTIC', '2025-01-20', '', '', 38, 1),
	(23, 'Unidad de Comunicación y Relaciones Públicas', '2025-01-20', '', '', 39, 1),
	(24, 'Unidad de Comunicación y Relaciones Públicas', '2025-01-20', '', '', 40, 1),
	(25, 'Unidad de Comunicación y Relaciones Públicas', '2025-01-20', '', '', 41, 1),
	(26, 'Unidad de Comunicación y Relaciones Públicas', '2025-01-20', '', '', 42, 1),
	(27, 'Unidad de Comunicación y Relaciones Públicas', '2025-01-20', '', '', 43, 1),
	(28, 'Unidad de Comunicación y Relaciones Públicas', '2025-01-20', '', '', 44, 1),
	(29, 'Unidad de Comunicación y Relaciones Públicas', '2025-01-20', '', '', 45, 1),
	(30, 'Unidad de Comunicación y Relaciones Públicas', '2025-01-20', '', '', 46, 1),
	(31, 'Unidad de Comunicación y Relaciones Públicas', '2025-01-20', '', '', 47, 1),
	(32, 'Unidad de Comunicación y Relaciones Públicas', '2025-01-20', '', '', 49, 1),
	(33, 'Unidad de Comunicación y Relaciones Públicas', '2025-01-20', '', '', 50, 1),
	(34, 'Unidad de Comunicación y Relaciones Públicas', '2025-01-20', '', '', 51, 1),
	(35, 'Unidad de Comunicación y Relaciones Públicas', '2025-01-20', '', '', 52, 1),
	(36, 'Unidad de Comunicación', '2025-05-26', '', '', 33, 1);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
