-- MySQL dump 10.13  Distrib 5.7.23, for Linux (x86_64)
--
-- Host: localhost    Database: db_ppfe
-- ------------------------------------------------------
-- Server version	5.7.23-0ubuntu0.16.04.1

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
-- Table structure for table `Purchase`
--

DROP TABLE IF EXISTS `Purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Purchase` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date_day` date NOT NULL,
  `count_total` int(11) NOT NULL,
  `id_voucher` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_voucher` (`id_voucher`),
  CONSTRAINT `Purchase_ibfk_1` FOREIGN KEY (`id_voucher`) REFERENCES `Voucher_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=114 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Purchase`
--

LOCK TABLES `Purchase` WRITE;
/*!40000 ALTER TABLE `Purchase` DISABLE KEYS */;
INSERT INTO `Purchase` VALUES (22,'2018-08-01',75,1),(23,'2018-08-01',124,2),(24,'2018-08-02',478,1),(25,'2018-08-02',47,2),(27,'2018-08-03',178,1),(28,'2018-08-03',48,2),(33,'2018-08-05',200,1),(34,'2018-08-05',141,2),(35,'2018-08-06',147,1),(36,'2018-08-06',15,2),(37,'2018-08-07',3,1),(38,'2018-08-07',238,2),(39,'2018-08-08',163,1),(40,'2018-08-08',97,2),(41,'2018-08-09',157,1),(42,'2018-08-09',1,2),(44,'2018-08-10',41,1),(45,'2018-08-10',327,2),(48,'2018-08-01',105,3),(49,'2018-08-01',159,4),(50,'2018-08-01',16,5),(51,'2018-08-01',193,6),(52,'2018-08-02',100,3),(53,'2018-08-02',160,4),(54,'2018-08-02',156,5),(55,'2018-08-02',200,6),(56,'2018-08-03',268,3),(57,'2018-08-03',168,4),(58,'2018-08-03',32,5),(59,'2018-08-03',156,6),(60,'2018-08-05',200,3),(61,'2018-08-05',38,4),(62,'2018-08-05',189,5),(63,'2018-08-05',1,6),(64,'2018-08-06',185,3),(65,'2018-08-06',169,4),(66,'2018-08-06',236,5),(67,'2018-08-06',78,6),(68,'2018-08-07',189,3),(69,'2018-08-07',207,4),(70,'2018-08-07',389,5),(71,'2018-08-07',89,6),(72,'2018-08-08',190,3),(73,'2018-08-08',208,4),(74,'2018-08-08',451,5),(75,'2018-08-08',145,6),(76,'2018-08-09',222,3),(77,'2018-08-09',345,4),(78,'2018-08-09',401,5),(79,'2018-08-09',165,6),(80,'2018-08-10',142,3),(81,'2018-08-10',111,4),(82,'2018-08-10',14,5),(83,'2018-08-10',200,6),(84,'2018-08-13',150,3),(85,'2018-08-13',156,4),(86,'2018-08-13',15,5),(87,'2018-08-13',15,6),(88,'2018-08-13',58,1),(89,'2018-08-13',330,2),(90,'2018-08-12',60,1),(91,'2018-08-12',224,2),(92,'2018-08-12',148,3),(93,'2018-08-12',254,4),(94,'2018-08-12',123,5),(95,'2018-08-12',198,6),(96,'2018-08-14',154,1),(97,'2018-08-14',248,2),(98,'2018-08-14',100,3),(99,'2018-08-14',158,4),(100,'2018-08-14',169,5),(101,'2018-08-14',147,6),(102,'2018-08-15',218,1),(103,'2018-08-15',139,2),(104,'2018-08-15',169,3),(105,'2018-08-15',100,4),(106,'2018-08-15',201,5),(107,'2018-08-15',11,6),(108,'2018-08-16',156,1),(109,'2018-08-16',296,2),(110,'2018-08-16',178,3),(111,'2018-08-16',3,4),(112,'2018-08-16',201,5),(113,'2018-08-16',99,6);
/*!40000 ALTER TABLE `Purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Voucher_type`
--

DROP TABLE IF EXISTS `Voucher_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Voucher_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(60) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Voucher_type`
--

LOCK TABLES `Voucher_type` WRITE;
/*!40000 ALTER TABLE `Voucher_type` DISABLE KEYS */;
INSERT INTO `Voucher_type` VALUES (1,'R10 AirTime'),(2,'R25 AirTime'),(3,'R50 AirTime'),(4,'R100 AirTime'),(5,'R150 AirTime'),(6,'R200 AirTime');
/*!40000 ALTER TABLE `Voucher_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Warning`
--

DROP TABLE IF EXISTS `Warning`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Warning` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `priority` varchar(60) NOT NULL,
  `count_difference` int(11) NOT NULL,
  `id_purchase` int(11) NOT NULL,
  `date_day` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_purchase` (`id_purchase`),
  CONSTRAINT `Warning_ibfk_1` FOREIGN KEY (`id_purchase`) REFERENCES `Purchase` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=244 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Warning`
--

LOCK TABLES `Warning` WRITE;
/*!40000 ALTER TABLE `Warning` DISABLE KEYS */;
INSERT INTO `Warning` VALUES (203,'high',-300,27,'2018-08-03'),(204,'medium',-53,35,'2018-08-06'),(205,'high',-144,37,'2018-08-07'),(206,'low',-6,41,'2018-08-09'),(207,'high',-116,44,'2018-08-10'),(208,'medium',-77,25,'2018-08-02'),(209,'high',-126,36,'2018-08-06'),(210,'high',-141,40,'2018-08-08'),(211,'medium',-96,42,'2018-08-09'),(212,'medium',-68,60,'2018-08-05'),(213,'low',-15,64,'2018-08-06'),(214,'medium',-80,80,'2018-08-10'),(215,'high',-130,61,'2018-08-05'),(216,'high',-234,81,'2018-08-10'),(217,'high',-124,58,'2018-08-03'),(218,'medium',-50,78,'2018-08-09'),(219,'high',-166,82,'2018-08-10'),(220,'low',-44,59,'2018-08-03'),(221,'high',-155,63,'2018-08-05'),(231,'medium',-68,85,'2018-08-13'),(232,'high',-133,86,'2018-08-13'),(233,'high',-239,87,'2018-08-13'),(234,'medium',-65,88,'2018-08-13'),(235,'high',-183,101,'2018-08-14'),(236,'high',-109,103,'2018-08-15'),(237,'medium',-58,105,'2018-08-15'),(238,'high',-136,107,'2018-08-15'),(242,'medium',-62,108,'2018-08-16'),(243,'medium',-97,111,'2018-08-16');
/*!40000 ALTER TABLE `Warning` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-16  9:34:00
