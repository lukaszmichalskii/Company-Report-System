-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: database
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `decisions`
--

DROP TABLE IF EXISTS `decisions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `decisions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `subject` varchar(45) NOT NULL,
  `employee` varchar(45) NOT NULL,
  `priority` varchar(45) NOT NULL,
  `description` longtext NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `decisions`
--

LOCK TABLES `decisions` WRITE;
/*!40000 ALTER TABLE `decisions` DISABLE KEYS */;
INSERT INTO `decisions` VALUES (1,'2021-07-14','Expo conference','Valentino Rossi','medium','Conference about Expo placed in Dubai shifted from 2020'),(2,'2021-11-14','2022 budget','Felipe Massa','high','Approval of the company\'s budget plan for 2022'),(3,'2021-11-10','Recruting process','Mara Lynd','low','Onboarding of rookies'),(4,'2021-11-18','2021 year summary','Łukasz Michalski','medium','Summary of 2021 year company performace'),(7,'2021-11-15','Database system update','Mark Christian','high','System database software update is scheduled next week (22.11.2021) rom 20:00 to 22:00'),(8,'2021-11-15','Test','Kendall Roy','low','Testing new functionality of CRS'),(9,'2021-10-31','Halloween','Koko Gauff','low','Information about halloween event in company'),(10,'2021-12-31','New Year Party','Zac Brown','low','Company New Year party scheduled on 31.12.2021 at 18:00'),(13,'2021-11-21','Qatar GP','Lewis Hamilton','high','Formula 1 Qatar Gran Prix 2021. Max Verstapen leading championships by 16 points'),(14,'2021-11-21','Project evaluation','Łukasz Michalski','medium','Report of progress in CRS project'),(35,'2021-11-27','CRS - analysis board','Łukasz Michalski','high','Designing the analysis panel dashboard and how to fix data\nstore problem.');
/*!40000 ALTER TABLE `decisions` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-07 21:31:18
