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
-- Table structure for table `noleggio`
--

DROP TABLE IF EXISTS `noleggio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `noleggio` (
  `Numero_di_contratto` int NOT NULL,
  `Data_di_inizio_noleggio` date NOT NULL,
  `Data_di_fine_noleggio` date NOT NULL,
  PRIMARY KEY (`Numero_di_contratto`),
  UNIQUE KEY `FKTipologia_IND` (`Numero_di_contratto`),
  CONSTRAINT `FKTipologia_FK` FOREIGN KEY (`Numero_di_contratto`) REFERENCES `contratto` (`Numero_di_contratto`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `noleggio_chk_1` CHECK ((`Data_di_inizio_noleggio` <= `Data_di_fine_noleggio`))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `noleggio`
--

LOCK TABLES `noleggio` WRITE;
/*!40000 ALTER TABLE `noleggio` DISABLE KEYS */;
INSERT INTO `noleggio` VALUES (7,'2023-07-17','2023-07-17'),(11,'2023-07-18','2023-07-18'),(12,'2023-07-18','2023-07-20'),(14,'2023-07-18','2023-08-18'),(16,'2023-07-18','2023-07-21'),(18,'2023-07-19','2023-08-10'),(20,'2023-07-19','2023-07-19'),(21,'2023-07-19','2023-07-25'),(23,'2023-07-22','2023-10-10'),(25,'2023-07-22','2023-07-27');
/*!40000 ALTER TABLE `noleggio` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-21 14:52:10
