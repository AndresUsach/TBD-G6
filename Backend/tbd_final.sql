CREATE DATABASE  IF NOT EXISTS `tbd` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `tbd`;
-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: tbd
-- ------------------------------------------------------
-- Server version	5.7.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `artista`
--

DROP TABLE IF EXISTS `artista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `artista` (
  `idartista` int(11) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `idgenero` int(11) DEFAULT NULL,
  `idusuario` int(11) DEFAULT NULL,
  `comentarios_negativos` int(11) DEFAULT '0',
  `comentarios_positivos` int(11) DEFAULT '0',
  `comentarios_neutros` int(11) DEFAULT '0',
  PRIMARY KEY (`idartista`),
  KEY `FKs8gk3coub0i028b8t8i52d5v` (`idgenero`),
  KEY `FK615lq1grx3igoepun0bv6b0u6` (`idusuario`),
  CONSTRAINT `FK615lq1grx3igoepun0bv6b0u6` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`),
  CONSTRAINT `FKs8gk3coub0i028b8t8i52d5v` FOREIGN KEY (`idgenero`) REFERENCES `genero` (`idgenero`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artista`
--

LOCK TABLES `artista` WRITE;
/*!40000 ALTER TABLE `artista` DISABLE KEYS */;
INSERT INTO `artista` VALUES (1,'El negrito de ojos claros','Ozuna',1,1,0,0,0);
INSERT INTO `artista` VALUES (2,'Cantante y Actriz','Becky G',2,1,0,0,0);
INSERT INTO `artista` VALUES (3,'Cantante y compositor de reggaeton','Maluma',2,1,0,0,0);
INSERT INTO `artista` VALUES (4,'Cantante','Luis Fonsi',4,1,0,0,0);
INSERT INTO `artista` VALUES (5,'Cantante y compositor','Danny Ocean',3,1,0,0,0);
INSERT INTO `artista` VALUES (6,'Cantautor','J Balvin',2,1,0,0,0);
INSERT INTO `artista` VALUES (7,'Cantautor','Romeo Santos',4,1,0,0,0);
INSERT INTO `artista` VALUES (8,'Cantante','Shakira',5,1,0,0,0);
INSERT INTO `artista` VALUES (9,'Rey del reguetón','Daddy Yankee',2,1,0,0,0);
INSERT INTO `artista` VALUES (10,'La Leyenda Viviente','Yandel',2,1,0,0,0);



/*!40000 ALTER TABLE `artista` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genero`
--

DROP TABLE IF EXISTS `genero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genero` (
  `idgenero` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `comentarios_negativos` int(11) DEFAULT '0',
  `comentarios_positivos` int(11) DEFAULT '0',
  `comentarios_neutros` int(11) DEFAULT '0',
  PRIMARY KEY (`idgenero`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genero`
--

LOCK TABLES `genero` WRITE;
/*!40000 ALTER TABLE `genero` DISABLE KEYS */;
INSERT INTO `genero` VALUES (1,'trap',0,0,0);
INSERT INTO `genero` VALUES (2,'reggaeton',0,0,0);
INSERT INTO `genero` VALUES (3,'electropop',0,0,0);
INSERT INTO `genero` VALUES (4,'bachata',0,0,0);
INSERT INTO `genero` VALUES (5,'pop latino',0,0,0);

/*!40000 ALTER TABLE `genero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `keyword`
--

DROP TABLE IF EXISTS `keyword`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `keyword` (
  `idkeyword` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idkeyword`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `keyword`
--

LOCK TABLES `keyword` WRITE;
/*!40000 ALTER TABLE `keyword` DISABLE KEYS */;
/*!40000 ALTER TABLE `keyword` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `keyword_artista`
--

DROP TABLE IF EXISTS `keyword_artista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `keyword_artista` (
  `idkeyword` int(11) NOT NULL,
  `idartista` int(11) NOT NULL,
  KEY `FK26addct2dhaxsqeowwbsw10y` (`idartista`),
  KEY `FKhx2fj34tpscmk3emthgtnd0ua` (`idkeyword`),
  CONSTRAINT `FK26addct2dhaxsqeowwbsw10y` FOREIGN KEY (`idartista`) REFERENCES `artista` (`idartista`),
  CONSTRAINT `FKhx2fj34tpscmk3emthgtnd0ua` FOREIGN KEY (`idkeyword`) REFERENCES `keyword` (`idkeyword`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `keyword_artista`
--

LOCK TABLES `keyword_artista` WRITE;
/*!40000 ALTER TABLE `keyword_artista` DISABLE KEYS */;
/*!40000 ALTER TABLE `keyword_artista` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `idusuario` int(11) NOT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'admin','admin@admin.cl','admin','admin','admin');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-14  2:04:08

delimiter //
CREATE TRIGGER updateGenero AFTER UPDATE ON artista 
FOR EACH ROW
BEGIN

UPDATE genero SET comentarios_negativos = comentarios_negativos + NEW.comentarios_negativos WHERE genero.idgenero = NEW.idgenero;
UPDATE genero SET comentarios_positivos = comentarios_positivos + NEW.comentarios_positivos WHERE genero.idgenero = NEW.idgenero;
UPDATE genero SET comentarios_neutros = comentarios_neutros + NEW.comentarios_neutros WHERE genero.idgenero = NEW.idgenero;

END
//
delimiter ;

CREATE TABLE `tbd`.`pais` (
  `idpais` INT NOT NULL,
  `nombre` VARCHAR(255) NULL,
  `comentarios_positivos` INT NULL,
  `idartista` INT NULL,
  PRIMARY KEY (`idpais`),
  INDEX `idartista_idx` (`idartista` ASC),
  CONSTRAINT `idartista`
  FOREIGN KEY (`idartista`)
  REFERENCES `tbd`.`artista` (`idartista`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

INSERT INTO `tbd`.`pais` (`idpais`, `nombre`) VALUES ('1', 'argentina');
INSERT INTO `tbd`.`pais` (`idpais`, `nombre`) VALUES ('2', 'bolivia');
INSERT INTO `tbd`.`pais` (`idpais`, `nombre`) VALUES ('3', 'chile');
INSERT INTO `tbd`.`pais` (`idpais`, `nombre`) VALUES ('4', 'colombia');
INSERT INTO `tbd`.`pais` (`idpais`, `nombre`) VALUES ('5', 'costaRica');
INSERT INTO `tbd`.`pais` (`idpais`, `nombre`) VALUES ('6', 'ecuador');
INSERT INTO `tbd`.`pais` (`idpais`, `nombre`) VALUES ('7', 'guatemala');
INSERT INTO `tbd`.`pais` (`idpais`, `nombre`) VALUES ('8', 'honduras');
INSERT INTO `tbd`.`pais` (`idpais`, `nombre`) VALUES ('9', 'mexico');
INSERT INTO `tbd`.`pais` (`idpais`, `nombre`) VALUES ('10', 'nicaragua');
INSERT INTO `tbd`.`pais` (`idpais`, `nombre`) VALUES ('11', 'panama');
INSERT INTO `tbd`.`pais` (`idpais`, `nombre`) VALUES ('12', 'paraguay');
INSERT INTO `tbd`.`pais` (`idpais`, `nombre`) VALUES ('13', 'peru');
INSERT INTO `tbd`.`pais` (`idpais`, `nombre`) VALUES ('14', 'salvador');
INSERT INTO `tbd`.`pais` (`idpais`, `nombre`) VALUES ('15', 'uruguay');
INSERT INTO `tbd`.`pais` (`idpais`, `nombre`) VALUES ('16', 'venezuela');

UPDATE `tbd`.`pais` SET `comentarios_positivos`='0' WHERE `idpais`='1';
UPDATE `tbd`.`pais` SET `comentarios_positivos`='0' WHERE `idpais`='2';
UPDATE `tbd`.`pais` SET `comentarios_positivos`='0' WHERE `idpais`='3';
UPDATE `tbd`.`pais` SET `comentarios_positivos`='0' WHERE `idpais`='4';
UPDATE `tbd`.`pais` SET `comentarios_positivos`='0' WHERE `idpais`='5';
UPDATE `tbd`.`pais` SET `comentarios_positivos`='0' WHERE `idpais`='6';
UPDATE `tbd`.`pais` SET `comentarios_positivos`='0' WHERE `idpais`='7';
UPDATE `tbd`.`pais` SET `comentarios_positivos`='0' WHERE `idpais`='8';
UPDATE `tbd`.`pais` SET `comentarios_positivos`='0' WHERE `idpais`='9';
UPDATE `tbd`.`pais` SET `comentarios_positivos`='0' WHERE `idpais`='10';
UPDATE `tbd`.`pais` SET `comentarios_positivos`='0' WHERE `idpais`='11';
UPDATE `tbd`.`pais` SET `comentarios_positivos`='0' WHERE `idpais`='12';
UPDATE `tbd`.`pais` SET `comentarios_positivos`='0' WHERE `idpais`='13';
UPDATE `tbd`.`pais` SET `comentarios_positivos`='0' WHERE `idpais`='14';
UPDATE `tbd`.`pais` SET `comentarios_positivos`='0' WHERE `idpais`='15';
UPDATE `tbd`.`pais` SET `comentarios_positivos`='0' WHERE `idpais`='16';


