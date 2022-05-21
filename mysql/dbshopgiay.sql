CREATE DATABASE  IF NOT EXISTS `dbshopgiay` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `dbshopgiay`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: dbshopgiay
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `brand`
--

DROP TABLE IF EXISTS `brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `brand` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` text,
  `name_brand` varchar(128) NOT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `thumbnail` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` VALUES (1,'','Nike',1,'~/img/brand/1.png'),(2,'','Adidas',1,'~/img/brand/2.png'),(3,'','Vans',1,'~/img/brand/3.png'),(4,'','Converse',1,'~/img/brand/4.png');
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name_category` varchar(128) NOT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `brand_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK57gpi2dtsm4euf8w2rpi7odge` (`brand_id`),
  CONSTRAINT `FK57gpi2dtsm4euf8w2rpi7odge` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Nike Air Fear Of God',1,1),(2,'Nike Air Force 1',1,1),(3,'Nike Air Max',1,1),(4,'AlphaBounce',1,2),(5,'Ultra Boost',1,2),(6,'Yeezy',1,2),(7,'Vans Classic',1,3),(8,'Vans Vault',1,3),(9,'Converse 1970s',1,4),(10,'Converse CDG',1,4);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` text,
  `created_at` datetime(6) DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKm1rmnfcvq5mk26li4lit88pc5` (`product_id`),
  KEY `FKqm52p1v3o13hy268he0wcngr5` (`user_id`),
  CONSTRAINT `FKm1rmnfcvq5mk26li4lit88pc5` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKqm52p1v3o13hy268he0wcngr5` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'Sản phẩm này rất đẹp\r\n','2022-05-21 08:38:18.690000',2,2),(2,'Sản phẩm rất đẹp\r\n','2022-05-21 08:38:51.986000',29,2);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_details`
--

DROP TABLE IF EXISTS `order_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_details` (
  `id` int NOT NULL AUTO_INCREMENT,
  `quantity` int DEFAULT NULL,
  `size` int DEFAULT NULL,
  `order_id` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjyu2qbqt8gnvno9oe9j2s2ldk` (`order_id`),
  KEY `FKinivj2k1370kw224lavkm3rqm` (`product_id`),
  CONSTRAINT `FKinivj2k1370kw224lavkm3rqm` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKjyu2qbqt8gnvno9oe9j2s2ldk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_details`
--

LOCK TABLES `order_details` WRITE;
/*!40000 ALTER TABLE `order_details` DISABLE KEYS */;
INSERT INTO `order_details` VALUES (1,1,36,1,6),(2,1,37,1,2),(3,12,36,2,4),(4,10,37,2,2);
/*!40000 ALTER TABLE `order_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address_receiver` varchar(255) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `name_receiver` varchar(128) NOT NULL,
  `note` text,
  `phone_number_receiver` varchar(15) NOT NULL,
  `price` float DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK787ibr3guwp6xobrpbofnv7le` (`product_id`),
  KEY `FK32ql8ubntj5uh44ph9659tiih` (`user_id`),
  CONSTRAINT `FK32ql8ubntj5uh44ph9659tiih` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK787ibr3guwp6xobrpbofnv7le` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'Xã Nam Hồng, huyện Tiền Hải, tỉnh Thái Bình','2022-05-21 08:36:08.136000',NULL,'Nguyễn Uy','','0975059542',3000,1,NULL,2),(2,'283 TO NGOC VAN','2022-05-21 08:37:52.498000',NULL,'THI PHAN','','+84983197506',44000,0,NULL,3);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `description` text,
  `modified_at` datetime(6) DEFAULT NULL,
  `name` varchar(128) NOT NULL,
  `price` float DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `thumbnail` varchar(255) DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1mtsbur82frn64de7balymq9s` (`category_id`),
  CONSTRAINT `FK1mtsbur82frn64de7balymq9s` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (2,'2022-05-21 08:03:27.879000','','2022-05-21 08:08:47.113000','Nike Air Force 1 Low White Brown',2000,11,1,'~/img/product/p4-removebg-preview-removebg-preview.png',2),(3,'2022-05-21 08:04:51.413000','','2022-05-21 08:08:39.807000','Nike Air Force 1 Low Supreme White Like Auth',1000,12,1,'~/img/product/p3-removebg-preview-removebg-preview.png',2),(4,'2022-05-21 08:05:22.144000','','2022-05-21 08:08:32.655000','Nike Air Force 1 Low ID Gucci Cream Green Red Like Auth',2000,12,1,'~/img/product/p1-removebg-preview-removebg-preview.png',2),(5,'2022-05-21 08:05:57.900000','',NULL,'Nike Air Force 1 Low Stussy Fossil Like Auth',1000,12,1,'~/img/product/p2-removebg-preview-removebg-preview.png',2),(6,'2022-05-21 08:06:45.326000','',NULL,'Nike Air Fear of God 1 Sail Black Like Auth',1000,11,1,'~/img/product/p15-removebg-preview-removebg-preview.png',1),(7,'2022-05-21 08:07:13.962000','',NULL,'Nike Air Fear of God 1 Light Bone Like Auth',2000,12,1,'~/img/product/p16-removebg-preview-removebg-preview.png',1),(8,'2022-05-21 08:09:13.057000','',NULL,'Nike Air Fear of God 1 Oatmeal Like Auth',1000,12,1,'~/img/product/p14-removebg-preview-removebg-preview.png',1),(9,'2022-05-21 08:09:51.244000','','2022-05-21 08:21:06.888000','Nike Air Max 97 Undefeated trắng',1000,12,1,'~/img/product/p19-removebg-preview-removebg-preview.png',3),(10,'2022-05-21 08:10:19.067000','',NULL,'Nike Air Max 270 hồng',1000,12,1,'~/img/product/p22-removebg-preview-removebg-preview.png',3),(11,'2022-05-21 08:10:48.580000','','2022-05-21 08:20:51.869000','Nike Air Max 97 MSCHF x INRI Jesus',2000,12,1,'~/img/product/p18-removebg-preview-removebg-preview.png',3),(12,'2022-05-21 08:11:44.782000','','2022-05-21 08:20:41.015000',' Adidas Alphabounce Instinct M xám xanh',2000,12,1,'~/img/product/p31-removebg-preview-removebg-preview.png',4),(13,'2022-05-21 08:12:13.116000','',NULL,'Adidas Alphabounce Instinct M đen trắng',2000,12,1,'~/img/product/p32-removebg-preview-removebg-preview.png',4),(14,'2022-05-21 08:12:36.534000','',NULL,'Adidas Alphabounce Instinct M xám đỏ',2000,12,1,'~/img/product/p29-removebg-preview-removebg-preview.png',4),(15,'2022-05-21 08:13:14.682000','',NULL,'Adidas Ultra Boost 4.0 trắng',1000,12,1,'~/img/product/p33-removebg-preview-removebg-preview (1).png',5),(16,'2022-05-21 08:13:46.129000','',NULL,'Adidas Ultra Boost 2020 Space Race Grey Rep',1000,12,1,'~/img/product/p35-removebg-preview-removebg-preview (1).png',5),(17,'2022-05-21 08:14:19.202000','',NULL,'Adidas Ultra Boost OG Cloud White Active Red',1000,12,1,'~/img/product/p36-removebg-preview-removebg-preview (1).png',5),(18,'2022-05-21 08:14:58.786000','',NULL,'Adidas Yeezy Boost 350 V2 Desert Sage',2000,12,1,'~/img/product/p23-removebg-preview-removebg-preview.png',6),(19,'2022-05-21 08:15:23.263000','',NULL,'Adidas Yeezy Boost 350 V2 Tail Light',2000,12,1,'~/img/product/p24-removebg-preview-removebg-preview.png',6),(20,'2022-05-21 08:15:43.854000','',NULL,'Adidas Yeezy 350 V2 Static Reflective',2000,12,1,'~/img/product/p25-removebg-preview-removebg-preview.png',6),(21,'2022-05-21 08:16:22.024000','',NULL,'Vans Classic Slip On Fear of God',1000,12,1,'~/img/product/p51-removebg-preview-removebg-preview.png',7),(22,'2022-05-21 08:16:54.586000','',NULL,'Vans Vault Old Skool Black 2021 Like Auth',1000,12,1,'~/img/product/p47-removebg-preview-removebg-preview.png',8),(23,'2022-05-21 08:17:11.849000','',NULL,'Vans Vault Old Skool Black White',1000,12,1,'~/img/product/p50-removebg-preview-removebg-preview.png',8),(24,'2022-05-21 08:18:07.617000','',NULL,'Converse Chuck Taylor 1970s High Fear Of God Đen',2000,12,1,'~/img/product/p55-removebg-preview-removebg-preview.png',9),(25,'2022-05-21 08:18:28.212000','',NULL,'Converse Chuck Taylor 1970s High Fear Of God Trắng',1000,12,1,'~/img/product/p54-removebg-preview-removebg-preview.png',9),(26,'2022-05-21 08:18:51.336000','',NULL,'Converse 1970s Xanh Navy Cao Cổ',1000,12,1,'~/img/product/p57-removebg-preview-removebg-preview.png',9),(27,'2022-05-21 08:19:19.888000','',NULL,'Converse CDG đen cổ thấp',1000,12,1,'~/img/product/p58-removebg-preview-removebg-preview.png',10),(28,'2022-05-21 08:19:38.155000','',NULL,'Converse Trái Tim CDG trắng cổ thấp',1000,12,1,'~/img/product/p60-removebg-preview-removebg-preview.png',10),(29,'2022-05-21 08:19:57.146000','',NULL,'Converse CDG đen cổ cao',1000,132,1,'~/img/product/p61-removebg-preview-removebg-preview.png',10);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_size`
--

DROP TABLE IF EXISTS `product_size`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_size` (
  `id` int NOT NULL AUTO_INCREMENT,
  `quantity` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `size_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8i3jm2ctt0lsyeik2wt76yvv0` (`product_id`),
  KEY `FK1yl8bbmokvonm64131xlscnci` (`size_id`),
  CONSTRAINT `FK1yl8bbmokvonm64131xlscnci` FOREIGN KEY (`size_id`) REFERENCES `sizes` (`id`),
  CONSTRAINT `FK8i3jm2ctt0lsyeik2wt76yvv0` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_size`
--

LOCK TABLES `product_size` WRITE;
/*!40000 ALTER TABLE `product_size` DISABLE KEYS */;
INSERT INTO `product_size` VALUES (2,11,2,2),(3,12,3,1),(4,12,4,1),(5,12,5,1),(6,11,6,1),(7,12,7,2),(8,12,8,1),(9,12,9,5),(10,12,10,1),(11,12,11,1),(12,12,12,1),(13,12,13,1),(14,12,14,3),(15,12,15,5),(16,12,16,7),(17,12,17,1),(18,12,18,3),(19,12,19,6),(20,12,20,2),(21,12,21,1),(22,12,22,1),(23,12,23,1),(24,12,24,1),(25,12,25,1),(26,12,26,1),(27,12,27,2),(28,12,28,1),(29,120,29,1),(30,12,29,3);
/*!40000 ALTER TABLE `product_size` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `roles_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`roles_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'USER'),(2,'ADMIN');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sizes`
--

DROP TABLE IF EXISTS `sizes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sizes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `number_size` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sizes`
--

LOCK TABLES `sizes` WRITE;
/*!40000 ALTER TABLE `sizes` DISABLE KEYS */;
INSERT INTO `sizes` VALUES (1,36),(2,37),(3,38),(4,39),(5,40),(6,41),(7,42);
/*!40000 ALTER TABLE `sizes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `id` int NOT NULL,
  `roles_id` int NOT NULL,
  PRIMARY KEY (`id`,`roles_id`),
  KEY `FKdbv8tdyltxa1qjmfnj9oboxse` (`roles_id`),
  CONSTRAINT `FKdbv8tdyltxa1qjmfnj9oboxse` FOREIGN KEY (`roles_id`) REFERENCES `roles` (`roles_id`),
  CONSTRAINT `FKnmt12ry8d6a20i1un4cj253k6` FOREIGN KEY (`id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,1),(2,1),(3,1),(1,2);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `email` varchar(200) NOT NULL,
  `first_name` varchar(128) NOT NULL,
  `last_name` varchar(128) NOT NULL,
  `password` varchar(64) NOT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'283 TO NGOC VAN',NULL,'admin@gmail.com','Admin','Manager','$2a$10$QJd4HBYmqIGUcMFTw72RVOpwxuzJCsx7r7VlAUXfYlxiBF8S/xziy','+84983197506',0),(2,'Xã Nam Hồng, huyện Tiền Hải, tỉnh Thái Bình',NULL,'uynguyen@gmail.com','Nguyễn','Uy','$2a$10$386G11oQGlhrU7tE7mMvm.ste1H72LQF9tdEeOi7oZ2T0dztrBVKa','0975059542',1),(3,'283 TO NGOC VAN',NULL,'ngoan@gmail.com','THI','PHAN','$2a$10$qcWswM9INl6fr3N0DgdDNe5w4jC9ibq6d4rt02niOAtiyv8yvvdNO','+84983197506',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-21  8:42:45
