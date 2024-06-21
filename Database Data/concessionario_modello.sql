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
-- Table structure for table `modello`
--

DROP TABLE IF EXISTS `modello`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `modello` (
  `Marca` varchar(100) NOT NULL,
  `Nome` varchar(100) NOT NULL,
  `Numero_di_posti` int NOT NULL,
  `Cilindrata` int NOT NULL,
  `Potenza` int NOT NULL,
  `Prezzo_di_vendita` int NOT NULL,
  `Prezzo_di_noleggio` int NOT NULL,
  PRIMARY KEY (`Marca`,`Nome`),
  UNIQUE KEY `IDMODELLO_IND` (`Marca`,`Nome`),
  CONSTRAINT `FKAppartenenza` FOREIGN KEY (`Marca`) REFERENCES `marca` (`Nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modello`
--

LOCK TABLES `modello` WRITE;
/*!40000 ALTER TABLE `modello` DISABLE KEYS */;
INSERT INTO `modello` VALUES ('BMW','Century',7,3873,169,89546,83),('BMW','Ram Van 1500',2,4335,360,35847,259),('BMW','S-Class',3,3905,113,87401,294),('BMW','TL',5,4392,199,292972,244),('BMW','XC60',3,4501,200,281774,244),('Citroen','Aviator',4,4124,216,128186,191),('Citroen','Econoline E250',2,2529,225,228610,113),('Citroen','G37',7,1787,306,183086,275),('Citroen','S10 Blazer',2,4995,176,232193,72),('Citroen','Savana 3500',4,2024,166,42653,70),('Citroen','Sonoma Club',5,2593,91,208704,267),('Citroen','Stratus',3,4791,334,180263,223),('Citroen','Viper',4,2778,288,74026,221),('Dodge','Escalade EXT',5,4952,141,152710,149),('Ferrari','Aero 8',5,2586,183,66754,196),('Ferrari','IS',6,3975,354,140968,272),('Ferrari','Sephia',2,1613,278,96729,113),('Ferrari','Taurus',5,4895,186,184598,203),('Fiat','850',6,2475,301,136241,108),('Fiat','Acadia',6,1600,92,261445,274),('Fiat','Leone',5,2184,278,228420,149),('Fiat','Lumina',7,4653,379,279549,205),('Fiat','Panda',5,1000,60,20000,50),('Fiat','Sierra 3500',7,2179,132,58981,234),('Ford','Festiva',6,2443,295,90791,173),('Ford','S60',5,4712,160,165968,153),('Ford','Voyager',6,4374,77,128712,176),('Lancia','Blazer',5,2874,256,196792,106),('Lancia','Delta',4,2000,200,50000,150),('Lancia','Pajero',3,2337,190,277948,139),('Lancia','Ranger',3,2047,181,146184,284),('Lancia','Tacoma Xtra',6,4318,107,199796,197),('Lancia','Tundra',2,3546,366,212152,221),('Lancia','Wrangler',2,3886,225,284601,202),('Opel','Azera',2,1145,285,190688,240),('Opel','Expedition',6,1769,173,189010,201),('Opel','Firebird',3,3563,305,208588,296),('Opel','Mountaineer',3,1601,226,142866,262),('Opel','Quantum',2,2279,122,101611,142),('Opel','QX',5,1125,194,121704,98),('Pegout','9-3',3,2345,278,196446,252),('Pegout','Bronco',5,4283,213,117921,176),('Pegout','Esprit',4,3043,108,107350,131),('Pegout','GTI',4,2353,76,141514,232),('Pegout','Samurai',7,2777,207,169593,188),('Pegout','SC',7,4673,303,285902,65),('Volkswagen','C8',3,1588,275,26056,287),('Volkswagen','Century',6,2425,185,107253,209),('Volkswagen','Colorado',2,2670,123,103343,289),('Volkswagen','Gemini',3,1162,194,139205,173),('Volkswagen','GS',2,1056,233,273437,214),('Volkswagen','LR2',4,3751,365,282799,135);
/*!40000 ALTER TABLE `modello` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-21 14:52:09
