-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: concessionario
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `dipendente`
--

DROP TABLE IF EXISTS `dipendente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dipendente` (
  `Nome` varchar(100) NOT NULL,
  `Cognome` varchar(100) NOT NULL,
  `Codice_fiscale` char(16) NOT NULL,
  `Numero_di_telefono` char(10) DEFAULT NULL,
  `Numero_di_contratti` int NOT NULL DEFAULT '0',
  `Stipendio` int NOT NULL,
  `Data_di_assunzione` date NOT NULL,
  PRIMARY KEY (`Codice_fiscale`),
  UNIQUE KEY `IDDIPENDENTE_IND` (`Codice_fiscale`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dipendente`
--

LOCK TABLES `dipendente` WRITE;
/*!40000 ALTER TABLE `dipendente` DISABLE KEYS */;
INSERT INTO `dipendente` VALUES ('Giovanni','Rotondo','BFGNM345BVLK45CS','5761250934',0,1400,'2023-07-18'),('Andrea','Belli','BGHJFKL093LKMA23','8904318923',0,2350,'2023-07-22'),('Keely','Feldberger','BMCGMG46A40H199F','2829028322',3,1563,'2019-12-12'),('Margaret','Chasen','EBUDLQ02T46Y962A','1043169834',0,1881,'2019-07-25'),('Catrina','Friskey','FFCZHG24E64G389M','1302178420',1,1558,'2019-06-18'),('Lyndsay','Clixby','HBHDLW29W35E215O','6614747245',2,2332,'2019-05-30'),('Francesco','Virgolini','HFG4LK5MNACVNRB5','7659023489',2,2500,'2023-07-19'),('Suellen','Searle','KJKAPV94Y75X516S','3782569521',1,1596,'2019-12-02'),('Carlynne','Saladino','OYVLXK70M51A320J','9411406911',4,2883,'2022-10-20'),('Domini','Boyde','WCDQYI76G40F452U','1237561403',0,1991,'2022-10-25'),('Isobel','Perotti','WPNPED96D54W759T','2456496144',3,1580,'2022-03-21'),('Robinette','Issacof','WRFAJZ27B36J853S','4874479153',3,2035,'2023-02-17'),('Almeria','Nemchinov','YDPFNU33D42F297H','2743612807',1,2805,'2021-07-02');
/*!40000 ALTER TABLE `dipendente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-21 14:52:12