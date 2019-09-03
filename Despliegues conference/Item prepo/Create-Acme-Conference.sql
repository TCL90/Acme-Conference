-- MySQL dump 10.13  Distrib 5.5.29, for Win64 (x86)
--
-- Host: localhost    Database: Acme-Conference
-- ------------------------------------------------------
-- Server version	5.5.29

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
-- Table structure for table `activity_attachments`
--

DROP TABLE IF EXISTS `activity_attachments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_attachments` (
  `activity` int(11) NOT NULL,
  `attachments` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_attachments`
--

LOCK TABLES `activity_attachments` WRITE;
/*!40000 ALTER TABLE `activity_attachments` DISABLE KEYS */;
INSERT INTO `activity_attachments` VALUES (2395,'http://drive.google.com/attach1, http://drive.google.com/attach2'),(2396,'http://drive.google.com/attach1, http://drive.google.com/attach2'),(2397,'http://drive.google.com/attach12, http://drive.google.com/attach32'),(2398,'http://drive.google.com/attach12, http://drive.google.com/attach32'),(2399,'http://drive.google.com/attach12, http://drive.google.com/attach32'),(2426,'http://drive.google.com/attach3, http://drive.google.com/attach4'),(2427,'http://drive.google.com/attach4, http://drive.google.com/attach5');
/*!40000 ALTER TABLE `activity_attachments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_speakers`
--

DROP TABLE IF EXISTS `activity_speakers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_speakers` (
  `activity` int(11) NOT NULL,
  `speakers` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_speakers`
--

LOCK TABLES `activity_speakers` WRITE;
/*!40000 ALTER TABLE `activity_speakers` DISABLE KEYS */;
INSERT INTO `activity_speakers` VALUES (2395,'Author nº1, Author nº2'),(2396,'Author nº3, Author nº2'),(2397,'Author nº1, Author nº2'),(2398,'Author nº2, Author nº3'),(2399,'Author nº1, Author nº3'),(2426,'Author nº1, Author nº2'),(2427,'Author nº1, Author nº2');
/*!40000 ALTER TABLE `activity_speakers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actor_boxes`
--

DROP TABLE IF EXISTS `actor_boxes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor_boxes` (
  `actor` int(11) NOT NULL,
  `boxes` int(11) NOT NULL,
  UNIQUE KEY `UK_6n6psqivvjho155qcf9kjvv1h` (`boxes`),
  CONSTRAINT `FK_6n6psqivvjho155qcf9kjvv1h` FOREIGN KEY (`boxes`) REFERENCES `box` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor_boxes`
--

LOCK TABLES `actor_boxes` WRITE;
/*!40000 ALTER TABLE `actor_boxes` DISABLE KEYS */;
INSERT INTO `actor_boxes` VALUES (2307,2319),(2307,2320),(2307,2321),(2309,2322),(2309,2323),(2309,2324),(2310,2325),(2310,2326),(2310,2327),(2311,2328),(2311,2329),(2311,2330),(2312,2331),(2312,2332),(2312,2333),(2316,2334),(2316,2335),(2316,2336),(2317,2337),(2317,2338),(2317,2339),(2308,2340),(2308,2341),(2308,2342),(2313,2343),(2313,2344),(2313,2345),(2314,2346),(2314,2347),(2314,2348),(2315,2349),(2315,2350),(2315,2351);
/*!40000 ALTER TABLE `actor_boxes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrator` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_7ohwsa2usmvu0yxb44je2lge` (`user_account`),
  CONSTRAINT `FK_7ohwsa2usmvu0yxb44je2lge` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (2307,0,'C/ Wednesday, nº 1','admin1@','','administrator1','+34 654456248','http://wwww.photo1.com','Exposito',2295),(2308,0,'C/ Wednesday, nº 2','admin2@','Admin','Administrador','+34 651236248','http://wwww.photo2.com','Exposito',2303);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `author` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) DEFAULT NULL,
  `score` double DEFAULT NULL,
  `finder` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ng8tt0uu0j791w837h00d4owq` (`finder`),
  KEY `FK_rjptsoy3q9dcfysbnmy1g0g31` (`user_account`),
  CONSTRAINT `FK_rjptsoy3q9dcfysbnmy1g0g31` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`),
  CONSTRAINT `FK_ng8tt0uu0j791w837h00d4owq` FOREIGN KEY (`finder`) REFERENCES `finder` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES (2309,1,'C/ Saturday, nº 11','author@gmail.com','CG','author1','+34 654455555','http://wwww.photo2.com','Prueba',2298,NULL,2382),(2310,1,'C/ Saturday, nº 12','author2@gmail.com','CC','author2','+34 654455556','http://wwww.photo3.com','Prueba2',2299,NULL,2383);
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `author_conferences`
--

DROP TABLE IF EXISTS `author_conferences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `author_conferences` (
  `author` int(11) NOT NULL,
  `conferences` int(11) NOT NULL,
  KEY `FK_q0t68by5l74o0y5yd6cdi2685` (`conferences`),
  KEY `FK_a0ai128gay07gphsxw8pg77q3` (`author`),
  CONSTRAINT `FK_a0ai128gay07gphsxw8pg77q3` FOREIGN KEY (`author`) REFERENCES `author` (`id`),
  CONSTRAINT `FK_q0t68by5l74o0y5yd6cdi2685` FOREIGN KEY (`conferences`) REFERENCES `conference` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author_conferences`
--

LOCK TABLES `author_conferences` WRITE;
/*!40000 ALTER TABLE `author_conferences` DISABLE KEYS */;
/*!40000 ALTER TABLE `author_conferences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `box`
--

DROP TABLE IF EXISTS `box`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `box` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `box`
--

LOCK TABLES `box` WRITE;
/*!40000 ALTER TABLE `box` DISABLE KEYS */;
INSERT INTO `box` VALUES (2319,0,'In'),(2320,0,'Notification'),(2321,0,'Out'),(2322,0,'In'),(2323,0,'Notification'),(2324,0,'Out'),(2325,0,'In'),(2326,0,'Notification'),(2327,0,'Out'),(2328,0,'In'),(2329,0,'Notification'),(2330,0,'Out'),(2331,0,'In'),(2332,0,'Notification'),(2333,0,'Out'),(2334,0,'In'),(2335,0,'Notification'),(2336,0,'Out'),(2337,0,'In'),(2338,0,'Notification'),(2339,0,'Out'),(2340,0,'In'),(2341,0,'Notification'),(2342,0,'Out'),(2343,0,'In'),(2344,0,'Notification'),(2345,0,'Out'),(2346,0,'In'),(2347,0,'Notification'),(2348,0,'Out'),(2349,0,'In'),(2350,0,'Notification'),(2351,0,'Out');
/*!40000 ALTER TABLE `box` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `box_messages`
--

DROP TABLE IF EXISTS `box_messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `box_messages` (
  `box` int(11) NOT NULL,
  `messages` int(11) NOT NULL,
  KEY `FK_acfjrqu1jeixjmv14c0386o0s` (`messages`),
  KEY `FK_e6boieojekgfg919on0dci4na` (`box`),
  CONSTRAINT `FK_e6boieojekgfg919on0dci4na` FOREIGN KEY (`box`) REFERENCES `box` (`id`),
  CONSTRAINT `FK_acfjrqu1jeixjmv14c0386o0s` FOREIGN KEY (`messages`) REFERENCES `message` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `box_messages`
--

LOCK TABLES `box_messages` WRITE;
/*!40000 ALTER TABLE `box_messages` DISABLE KEYS */;
INSERT INTO `box_messages` VALUES (2319,2407),(2324,2407);
/*!40000 ALTER TABLE `box_messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `camera_ready_paper`
--

DROP TABLE IF EXISTS `camera_ready_paper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `camera_ready_paper` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `document` varchar(255) DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `submission` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_c65sk86p20xnwvstgolqeq8h2` (`submission`),
  CONSTRAINT `FK_c65sk86p20xnwvstgolqeq8h2` FOREIGN KEY (`submission`) REFERENCES `submission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `camera_ready_paper`
--

LOCK TABLES `camera_ready_paper` WRITE;
/*!40000 ALTER TABLE `camera_ready_paper` DISABLE KEYS */;
INSERT INTO `camera_ready_paper` VALUES (2418,0,'http://www.document1.com','Summary 1. Natural language with artificial intelligence ...','Title 1. Artificial intelligence in environments...',2410),(2419,0,'http://www.document2.com','Summary example2. It\'s very important that artificial intelligence is explained to kids...','Title 2. Spring applied to...',2414);
/*!40000 ALTER TABLE `camera_ready_paper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `camera_ready_paper_authors`
--

DROP TABLE IF EXISTS `camera_ready_paper_authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `camera_ready_paper_authors` (
  `camera_ready_paper` int(11) NOT NULL,
  `authors` varchar(255) DEFAULT NULL,
  KEY `FK_nwy8nwxpu84h34qttyhjfmy7` (`camera_ready_paper`),
  CONSTRAINT `FK_nwy8nwxpu84h34qttyhjfmy7` FOREIGN KEY (`camera_ready_paper`) REFERENCES `camera_ready_paper` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `camera_ready_paper_authors`
--

LOCK TABLES `camera_ready_paper_authors` WRITE;
/*!40000 ALTER TABLE `camera_ready_paper_authors` DISABLE KEYS */;
INSERT INTO `camera_ready_paper_authors` VALUES (2418,'Author nº1, Author nº2'),(2419,'Author nº2, Author nº3');
/*!40000 ALTER TABLE `camera_ready_paper_authors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `title_esp` varchar(255) DEFAULT NULL,
  `title_ing` varchar(255) DEFAULT NULL,
  `parent` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_d285hl23ejq8efmum8hbvqrt2` (`parent`),
  CONSTRAINT `FK_d285hl23ejq8efmum8hbvqrt2` FOREIGN KEY (`parent`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (2363,0,'CONFERENCIA','CONFERENCE',NULL),(2364,0,'Ciencias de la computación','Computer Science',2363),(2365,0,'Inteligencia artificial','Artificial Intelligence',2364),(2366,0,'Aprendizaje profundo','Deep Learning',2365),(2367,0,'Aprendizaje basado en instancias','Instance-based learning',2365),(2368,0,'Regresión','Regression',2365),(2369,0,'Ingeniería del software','Software Engineering',2364),(2370,0,'Grandes datos (Big Data)','Big Data',2369),(2371,0,'Procesamiento de grandes datos (Big Processing)','Big Processing',2369),(2372,0,'Métodos','Methods',2369),(2373,0,'Herramientas','Tools',2369),(2374,0,'Física','Physics',2364),(2375,0,'Cinemática','Cinematic',2374),(2376,0,'Electricidad','Electricity',2374),(2377,0,'Electrónica','Electronics',2374),(2378,0,'Biología','Biology',2364),(2379,0,'Animales','Animals',2378),(2380,0,'Plantas','Plants',2378),(2381,0,'Fungi','Fungi',2378);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `moment` datetime DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `author` int(11) DEFAULT NULL,
  `commentable` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (2389,0,'2019-06-12 10:00:00','I didn\'t like this conference','Comment number 1',2309,2352),(2390,0,'2019-06-12 10:20:00','This conference was a w e s o m e','Comment number 2',NULL,2352),(2391,0,'2019-06-12 10:30:00','This conference was cool','Comment number 3',NULL,2352),(2392,0,'2019-06-12 10:30:02','It was funny','Comment number 4',NULL,2352),(2393,0,'2019-06-12 10:31:00','So bad organisation','Comment number 5',NULL,2352),(2394,0,'2019-06-12 10:32:00','It was cool but too quick','Comment number 6',NULL,2352),(2415,0,'2019-06-12 10:32:00','It sounds great','Comment number 7',NULL,2395),(2416,0,'2019-06-12 10:32:00','It will be very useful','Comment number 8',NULL,2397),(2417,0,'2019-06-12 10:32:00','It will be very useful','Comment number 9',NULL,2397);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conference`
--

DROP TABLE IF EXISTS `conference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conference` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `acronym` varchar(255) DEFAULT NULL,
  `camera_ready_deadline` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `fee` int(11) NOT NULL,
  `final_mode` bit(1) NOT NULL,
  `notification_deadline` datetime DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `submission_deadline` datetime DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `venue` varchar(255) DEFAULT NULL,
  `category` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ldxxtwdsnf262yu1ctl1awe02` (`category`),
  CONSTRAINT `FK_ldxxtwdsnf262yu1ctl1awe02` FOREIGN KEY (`category`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conference`
--

LOCK TABLES `conference` WRITE;
/*!40000 ALTER TABLE `conference` DISABLE KEYS */;
INSERT INTO `conference` VALUES (2352,1,'CNLP','2019-11-15 10:00:00','2019-12-31 10:00:00',12,'','2019-11-13 10:00:00','2019-10-11 10:00:00','2019-11-11 10:00:00','About natural language processing on domain models. Natural language processing is very important because...','Conference about natural language processing at artificial intelligence','FIBES, Sevilla',2365),(2353,1,'CAI','2019-11-15 10:00:00','2019-12-31 10:00:00',13,'','2019-11-13 10:00:00','2019-10-11 10:00:00','2019-11-11 10:00:00','About natural artificial intelligence and new technologies. Artificial intelligence is very important because...','Conference about artificial intelligence','FIBES, Sevilla',2366),(2354,1,'CADP','2019-06-15 10:00:00','2019-06-25 10:00:00',13,'','2019-06-13 10:00:00','2019-06-22 10:00:00','2019-06-11 10:00:00','About deep learning and artificial intelligence. Deep learning doesn\'t need a lot of artificial intelligence knoledge.','Conference about deep learning and artificial intelligence','FIBES, Sevilla',2365),(2355,1,'IAMS','2019-06-26 10:00:00','2019-07-02 10:00:00',13,'\0','2019-06-25 10:00:00','2019-06-29 10:00:00','2019-06-20 10:00:00','Conference about artificial intelligence technologies. A lot of artificial intelligence technologies will be reviewed','Conference about artificial intelligence II','FIBES, Sevilla',2365),(2356,1,'SCGF','2019-07-28 10:00:00','2019-09-20 10:00:00',13,'','2019-07-26 10:00:00','2019-07-29 10:00:00','2019-07-25 10:00:00','Conference about Spring framework and natural language processing. It includes references to artificial intelligence, natural language processing and Spring framework','Conference about Spring and natural language processing.','FIBES, Sevilla',2364),(2357,1,'CABD','2019-07-15 10:00:00','2019-09-20 10:00:00',13,'','2019-07-13 10:00:00','2019-07-29 10:00:00','2019-07-11 10:00:00','About big data and artificial intelligence','About Big Data with artificial intelligence','FIBES, Sevilla',2370),(2358,1,'CAVG','2019-07-01 10:00:00','2019-09-20 10:00:00',13,'','2019-07-01 10:00:00','2019-07-01 12:00:00','2019-07-01 10:00:00','We will talk about videogames history','Conference about Videogames','FIBES, Sevilla',2363),(2359,1,'CAJE','2019-06-01 10:00:00','2019-09-20 10:00:00',13,'','2019-06-01 10:00:00','2019-06-01 12:00:00','2019-06-27 10:00:00','About most common facts about Jane Eyre','Conference about Jane Eyre','FIBES, Sevilla',2363),(2360,1,'PCCF','2019-07-07 10:00:00','2019-09-20 10:00:00',13,'','2019-07-07 10:00:00','2019-07-07 12:00:00','2019-07-07 10:00:00','Talk about PC','Conference about Pc components','FIBES, Sevilla',2364),(2361,1,'CTT','2019-11-15 10:00:00','2019-12-31 10:00:00',12,'\0','2019-11-13 10:00:00','2019-10-11 10:00:00','2019-11-11 10:00:00','About new technologies on mobiles.','Mobile Congress ','FIBES, Sevilla',2365),(2362,1,'CTTD','2019-11-15 10:00:00','2019-12-31 10:00:00',12,'\0','2019-11-13 10:00:00','2019-10-11 10:00:00','2019-11-11 10:00:00','About natural language processing on domain models. Natural language processing is very important because...','Conference to test delete','FIBES, Sevilla',2365);
/*!40000 ALTER TABLE `conference` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customisation`
--

DROP TABLE IF EXISTS `customisation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customisation` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `banner_url` varchar(255) DEFAULT NULL,
  `phone_number_code` varchar(255) DEFAULT NULL,
  `system_name` varchar(255) DEFAULT NULL,
  `welcome_message_eng` varchar(255) DEFAULT NULL,
  `welcome_message_esp` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customisation`
--

LOCK TABLES `customisation` WRITE;
/*!40000 ALTER TABLE `customisation` DISABLE KEYS */;
INSERT INTO `customisation` VALUES (2318,0,'https://i.ibb.co/GVpZCtM/acme-conference.png','+34','Acme Conference','Welcome to Acme Conference! Your scientific event manager','¡Bienvenidos a Acme Conference!  Su gestor de eventos científicos');
/*!40000 ALTER TABLE `customisation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customisation_buzz_words_esp`
--

DROP TABLE IF EXISTS `customisation_buzz_words_esp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customisation_buzz_words_esp` (
  `customisation` int(11) NOT NULL,
  `buzz_words_esp` varchar(255) DEFAULT NULL,
  KEY `FK_b3sdyik16m1p0ft5pub2r9h1s` (`customisation`),
  CONSTRAINT `FK_b3sdyik16m1p0ft5pub2r9h1s` FOREIGN KEY (`customisation`) REFERENCES `customisation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customisation_buzz_words_esp`
--

LOCK TABLES `customisation_buzz_words_esp` WRITE;
/*!40000 ALTER TABLE `customisation_buzz_words_esp` DISABLE KEYS */;
INSERT INTO `customisation_buzz_words_esp` VALUES (2318,'a'),(2318,'acá'),(2318,'ahí'),(2318,'ajena/o/s'),(2318,'al'),(2318,'algo'),(2318,'algún/a/o/s'),(2318,'allá/í'),(2318,'ambos'),(2318,'ante'),(2318,'antes'),(2318,'aquel'),(2318,'aquella/o/s'),(2318,'aquí'),(2318,'arriba'),(2318,'así'),(2318,'atrás'),(2318,'aun'),(2318,'aunque'),(2318,'bajo'),(2318,'bastante'),(2318,'bien'),(2318,'cabe'),(2318,'cada'),(2318,'casi'),(2318,'cierto/a/s'),(2318,'como'),(2318,'con'),(2318,'conmigo'),(2318,'conseguimos'),(2318,'conseguir'),(2318,'consigo'),(2318,'consigue'),(2318,'consiguen'),(2318,'consigues'),(2318,'contigo'),(2318,'contra'),(2318,'cual'),(2318,'cuales'),(2318,'cualquier/a/s'),(2318,'cuan'),(2318,'cuando'),(2318,'cuanto/a/s'),(2318,'de'),(2318,'dejar'),(2318,'del'),(2318,'demás'),(2318,'demasiada/o/s'),(2318,'dentro'),(2318,'desde'),(2318,'donde'),(2318,'dos'),(2318,'el'),(2318,'él'),(2318,'ella/o/s'),(2318,'empleáis'),(2318,'emplean'),(2318,'emplear'),(2318,'empleas'),(2318,'empleo'),(2318,'en'),(2318,'encima'),(2318,'entonces'),(2318,'entre'),(2318,'era/s'),(2318,'eramos'),(2318,'eran'),(2318,'eres'),(2318,'es'),(2318,'esa/e/o/s'),(2318,'esta/s'),(2318,'estaba'),(2318,'estado'),(2318,'estáis'),(2318,'estamos'),(2318,'están'),(2318,'estar'),(2318,'este/o/s'),(2318,'estoy'),(2318,'etc'),(2318,'fin'),(2318,'fue'),(2318,'fueron'),(2318,'fui'),(2318,'fuimos'),(2318,'gueno'),(2318,'ha'),(2318,'hace/s'),(2318,'hacéis'),(2318,'hacemos'),(2318,'hacen'),(2318,'hacer'),(2318,'hacia'),(2318,'hago'),(2318,'hasta'),(2318,'incluso'),(2318,'intenta/s'),(2318,'intentáis'),(2318,'intentamos'),(2318,'intentan'),(2318,'intentar'),(2318,'intento'),(2318,'ir'),(2318,'jamás'),(2318,'junto/s'),(2318,'la/o/s'),(2318,'largo'),(2318,'más'),(2318,'me'),(2318,'menos'),(2318,'mi/s'),(2318,'mía/s'),(2318,'mientras'),(2318,'mío/s'),(2318,'misma/o/s'),(2318,'modo'),(2318,'mucha/s'),(2318,'muchísima/o/s'),(2318,'mucho/s'),(2318,'muy'),(2318,'nada'),(2318,'ni'),(2318,'ningún/a/o/s'),(2318,'no'),(2318,'nos'),(2318,'nosotras/os'),(2318,'nuestra/o/s'),(2318,'nunca'),(2318,'os'),(2318,'otra/o/s'),(2318,'para'),(2318,'parecer'),(2318,'pero'),(2318,'poca/o/s'),(2318,'podéis'),(2318,'podemos'),(2318,'poder'),(2318,'podría/s'),(2318,'podríais'),(2318,'podríamos'),(2318,'podrían'),(2318,'por'),(2318,'por'),(2318,'qué'),(2318,'porque'),(2318,'primero'),(2318,'puede/n'),(2318,'puedo'),(2318,'pues'),(2318,'que'),(2318,'qué'),(2318,'querer'),(2318,'quién/es'),(2318,'quienesquiera'),(2318,'quienquiera'),(2318,'quizá/s'),(2318,'sabe/s/n'),(2318,'sabéis'),(2318,'sabemos'),(2318,'saber'),(2318,'se'),(2318,'según'),(2318,'ser'),(2318,'si'),(2318,'sí'),(2318,'siempre'),(2318,'siendo'),(2318,'sin'),(2318,'sino'),(2318,'so'),(2318,'sobre'),(2318,'sois'),(2318,'solamente'),(2318,'solo'),(2318,'sólo'),(2318,'somos'),(2318,'soy'),(2318,'sr'),(2318,'sra'),(2318,'sres'),(2318,'sta'),(2318,'su/s'),(2318,'suya/o/s'),(2318,'tal/es'),(2318,'también'),(2318,'tampoco'),(2318,'tanta/o/s'),(2318,'te'),(2318,'tenéis'),(2318,'tenemos'),(2318,'tener'),(2318,'tengo'),(2318,'ti'),(2318,'tiempo'),(2318,'tiene'),(2318,'tienen'),(2318,'toda/o/s'),(2318,'tomar'),(2318,'trabaja/o'),(2318,'trabajáis'),(2318,'trabajamos'),(2318,'trabajan'),(2318,'trabajar'),(2318,'trabajas'),(2318,'tras'),(2318,'tú'),(2318,'tu'),(2318,'tus'),(2318,'tuya/o/s'),(2318,'último'),(2318,'ultimo'),(2318,'un/a/o/s'),(2318,'usa/s'),(2318,'usáis'),(2318,'usamos'),(2318,'usan'),(2318,'usar'),(2318,'uso'),(2318,'usted/es'),(2318,'va/n'),(2318,'vais'),(2318,'valor'),(2318,'vamos'),(2318,'varias/os'),(2318,'vaya'),(2318,'verdadera'),(2318,'vosotras/os'),(2318,'voy'),(2318,'vuestra/o/s'),(2318,'y'),(2318,'ya'),(2318,'yo');
/*!40000 ALTER TABLE `customisation_buzz_words_esp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customisation_buzz_words_ing`
--

DROP TABLE IF EXISTS `customisation_buzz_words_ing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customisation_buzz_words_ing` (
  `customisation` int(11) NOT NULL,
  `buzz_words_ing` varchar(255) DEFAULT NULL,
  KEY `FK_r98hm5j2vh91c6nsnppoirgll` (`customisation`),
  CONSTRAINT `FK_r98hm5j2vh91c6nsnppoirgll` FOREIGN KEY (`customisation`) REFERENCES `customisation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customisation_buzz_words_ing`
--

LOCK TABLES `customisation_buzz_words_ing` WRITE;
/*!40000 ALTER TABLE `customisation_buzz_words_ing` DISABLE KEYS */;
INSERT INTO `customisation_buzz_words_ing` VALUES (2318,'\'tis'),(2318,'\'twas'),(2318,'a'),(2318,'able'),(2318,'about'),(2318,'across'),(2318,'after'),(2318,'ain\'t'),(2318,'all'),(2318,'almost'),(2318,'also'),(2318,'am'),(2318,'among'),(2318,'an'),(2318,'and'),(2318,'any'),(2318,'are'),(2318,'aren\'t'),(2318,'as'),(2318,'at'),(2318,'be'),(2318,'because'),(2318,'been'),(2318,'but'),(2318,'by'),(2318,'can'),(2318,'can\'t'),(2318,'cannot'),(2318,'could'),(2318,'could\'ve'),(2318,'couldn\'t'),(2318,'dear'),(2318,'did'),(2318,'didn\'t'),(2318,'do'),(2318,'does'),(2318,'doesn\'t'),(2318,'don\'t'),(2318,'either'),(2318,'else'),(2318,'ever'),(2318,'every'),(2318,'for'),(2318,'from'),(2318,'get'),(2318,'got'),(2318,'had'),(2318,'has'),(2318,'hasn\'t'),(2318,'have'),(2318,'he'),(2318,'he\'d'),(2318,'he\'ll'),(2318,'he\'s'),(2318,'her'),(2318,'hers'),(2318,'him'),(2318,'his'),(2318,'how'),(2318,'how\'d'),(2318,'how\'ll'),(2318,'how\'s'),(2318,'however'),(2318,'i'),(2318,'i\'d'),(2318,'i\'ll'),(2318,'i\'m'),(2318,'i\'ve'),(2318,'if'),(2318,'in'),(2318,'into'),(2318,'is'),(2318,'isn\'t'),(2318,'it'),(2318,'it\'s'),(2318,'its'),(2318,'just'),(2318,'least'),(2318,'let'),(2318,'like'),(2318,'likely'),(2318,'may'),(2318,'me'),(2318,'might'),(2318,'might\'ve'),(2318,'mightn\'t'),(2318,'most'),(2318,'must'),(2318,'must\'ve'),(2318,'mustn\'t'),(2318,'my'),(2318,'neither'),(2318,'no'),(2318,'nor'),(2318,'not'),(2318,'of'),(2318,'off'),(2318,'often'),(2318,'on'),(2318,'only'),(2318,'or'),(2318,'other'),(2318,'our'),(2318,'own'),(2318,'rather'),(2318,'said'),(2318,'say'),(2318,'says'),(2318,'shan\'t'),(2318,'she'),(2318,'she\'d'),(2318,'she\'ll'),(2318,'she\'s'),(2318,'should'),(2318,'should\'ve'),(2318,'shouldn\'t'),(2318,'since'),(2318,'so'),(2318,'some'),(2318,'than'),(2318,'that'),(2318,'that\'ll'),(2318,'that\'s'),(2318,'the'),(2318,'their'),(2318,'them'),(2318,'then'),(2318,'there'),(2318,'there\'s'),(2318,'these'),(2318,'they'),(2318,'they\'d'),(2318,'they\'ll'),(2318,'they\'re'),(2318,'they\'ve'),(2318,'this'),(2318,'tis'),(2318,'to'),(2318,'too'),(2318,'twas'),(2318,'us'),(2318,'wants'),(2318,'was'),(2318,'wasn\'t'),(2318,'we'),(2318,'we\'d'),(2318,'we\'ll'),(2318,'we\'re'),(2318,'were'),(2318,'weren\'t'),(2318,'what'),(2318,'what\'d'),(2318,'what\'s'),(2318,'when'),(2318,'when'),(2318,'when\'d'),(2318,'when\'ll'),(2318,'when\'s'),(2318,'where'),(2318,'where\'d'),(2318,'where\'ll'),(2318,'where\'s'),(2318,'which'),(2318,'while'),(2318,'who'),(2318,'who\'d'),(2318,'who\'ll'),(2318,'who\'s'),(2318,'whom'),(2318,'why'),(2318,'why\'d'),(2318,'why\'ll'),(2318,'why\'s'),(2318,'will'),(2318,'with'),(2318,'won\'t'),(2318,'would'),(2318,'would\'ve'),(2318,'wouldn\'t'),(2318,'yet'),(2318,'you'),(2318,'you\'d'),(2318,'you\'ll'),(2318,'you\'re'),(2318,'you\'ve'),(2318,'your');
/*!40000 ALTER TABLE `customisation_buzz_words_ing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customisation_credit_card_makes`
--

DROP TABLE IF EXISTS `customisation_credit_card_makes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customisation_credit_card_makes` (
  `customisation` int(11) NOT NULL,
  `credit_card_makes` varchar(255) DEFAULT NULL,
  KEY `FK_my1ddakb8b5rim2xvdvl6sxit` (`customisation`),
  CONSTRAINT `FK_my1ddakb8b5rim2xvdvl6sxit` FOREIGN KEY (`customisation`) REFERENCES `customisation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customisation_credit_card_makes`
--

LOCK TABLES `customisation_credit_card_makes` WRITE;
/*!40000 ALTER TABLE `customisation_credit_card_makes` DISABLE KEYS */;
INSERT INTO `customisation_credit_card_makes` VALUES (2318,'VISA'),(2318,'MASTER'),(2318,'DINNERS'),(2318,'AMEX');
/*!40000 ALTER TABLE `customisation_credit_card_makes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customisation_topics_esp`
--

DROP TABLE IF EXISTS `customisation_topics_esp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customisation_topics_esp` (
  `customisation` int(11) NOT NULL,
  `topics_esp` varchar(255) DEFAULT NULL,
  KEY `FK_kvedu4wbfiyb2e17pg22uk0oa` (`customisation`),
  CONSTRAINT `FK_kvedu4wbfiyb2e17pg22uk0oa` FOREIGN KEY (`customisation`) REFERENCES `customisation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customisation_topics_esp`
--

LOCK TABLES `customisation_topics_esp` WRITE;
/*!40000 ALTER TABLE `customisation_topics_esp` DISABLE KEYS */;
INSERT INTO `customisation_topics_esp` VALUES (2318,'INVESTIGACIÓN'),(2318,'REFUTACIÓN'),(2318,'INSCRIPCIÓN'),(2318,'TEMAS'),(2318,'OTROS');
/*!40000 ALTER TABLE `customisation_topics_esp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customisation_topics_ing`
--

DROP TABLE IF EXISTS `customisation_topics_ing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customisation_topics_ing` (
  `customisation` int(11) NOT NULL,
  `topics_ing` varchar(255) DEFAULT NULL,
  KEY `FK_fh2phf9s1fhcfttew0kk1c900` (`customisation`),
  CONSTRAINT `FK_fh2phf9s1fhcfttew0kk1c900` FOREIGN KEY (`customisation`) REFERENCES `customisation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customisation_topics_ing`
--

LOCK TABLES `customisation_topics_ing` WRITE;
/*!40000 ALTER TABLE `customisation_topics_ing` DISABLE KEYS */;
INSERT INTO `customisation_topics_ing` VALUES (2318,'INQUIRY'),(2318,'REBUTTAL'),(2318,'REGISTRATION'),(2318,'TOPICS'),(2318,'OTHER');
/*!40000 ALTER TABLE `customisation_topics_ing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `finder`
--

DROP TABLE IF EXISTS `finder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `finder` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `end_date` datetime DEFAULT NULL,
  `keyword` varchar(255) DEFAULT NULL,
  `maximum_fee` int(11) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `category` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_n9t1ayk0x7h5vrsfuhygo043j` (`category`),
  CONSTRAINT `FK_n9t1ayk0x7h5vrsfuhygo043j` FOREIGN KEY (`category`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `finder`
--

LOCK TABLES `finder` WRITE;
/*!40000 ALTER TABLE `finder` DISABLE KEYS */;
INSERT INTO `finder` VALUES (2382,0,NULL,'',NULL,NULL,NULL),(2383,0,NULL,'',NULL,NULL,NULL);
/*!40000 ALTER TABLE `finder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `finder_conferences`
--

DROP TABLE IF EXISTS `finder_conferences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `finder_conferences` (
  `finder` int(11) NOT NULL,
  `conferences` int(11) NOT NULL,
  KEY `FK_nfuvovuvbk05r9vbyg5mbstxx` (`conferences`),
  KEY `FK_k666b9rxu2vqkol7uc9ostni9` (`finder`),
  CONSTRAINT `FK_k666b9rxu2vqkol7uc9ostni9` FOREIGN KEY (`finder`) REFERENCES `finder` (`id`),
  CONSTRAINT `FK_nfuvovuvbk05r9vbyg5mbstxx` FOREIGN KEY (`conferences`) REFERENCES `conference` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `finder_conferences`
--

LOCK TABLES `finder_conferences` WRITE;
/*!40000 ALTER TABLE `finder_conferences` DISABLE KEYS */;
/*!40000 ALTER TABLE `finder_conferences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('domain_entity',1);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` varchar(255) DEFAULT NULL,
  `broadcast` bit(1) NOT NULL,
  `moment` datetime DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `topic` varchar(255) DEFAULT NULL,
  `sender` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (2407,0,'Prueba','\0','2018-10-10 00:00:00','Prueba','Prueba',2309);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message_recipients`
--

DROP TABLE IF EXISTS `message_recipients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message_recipients` (
  `message` int(11) NOT NULL,
  `recipients` int(11) NOT NULL,
  KEY `FK_1odmg2n3n487tvhuxx5oyyya2` (`message`),
  CONSTRAINT `FK_1odmg2n3n487tvhuxx5oyyya2` FOREIGN KEY (`message`) REFERENCES `message` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message_recipients`
--

LOCK TABLES `message_recipients` WRITE;
/*!40000 ALTER TABLE `message_recipients` DISABLE KEYS */;
INSERT INTO `message_recipients` VALUES (2407,2307);
/*!40000 ALTER TABLE `message_recipients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `panel`
--

DROP TABLE IF EXISTS `panel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `panel` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `duration` int(11) NOT NULL,
  `room` varchar(255) DEFAULT NULL,
  `schedule` datetime DEFAULT NULL,
  `start_moment` datetime DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `conference` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_g9jn8oaylcvixfdbmtesav3c3` (`conference`),
  CONSTRAINT `FK_g9jn8oaylcvixfdbmtesav3c3` FOREIGN KEY (`conference`) REFERENCES `conference` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `panel`
--

LOCK TABLES `panel` WRITE;
/*!40000 ALTER TABLE `panel` DISABLE KEYS */;
INSERT INTO `panel` VALUES (2395,0,40,'A1.42','2019-12-12 10:40:00','2019-12-12 10:00:00','Summary panel 1','Panel number 1',2352),(2396,0,40,'A1.42','2019-12-12 10:40:00','2019-12-12 10:00:00','Summary panel 1','Panel number 2',2360);
/*!40000 ALTER TABLE `panel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paper`
--

DROP TABLE IF EXISTS `paper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paper` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `document` varchar(255) DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paper`
--

LOCK TABLES `paper` WRITE;
/*!40000 ALTER TABLE `paper` DISABLE KEYS */;
INSERT INTO `paper` VALUES (2384,0,'http://www.drive.google.com/paper1','Summary pap 1','Paper 1'),(2385,0,'http://www.drive.google.com/paper2','Summary pap 2','Paper 2'),(2386,0,'http://www.drive.google.com/paper3','Summary pap 3','Paper 3'),(2387,0,'http://www.drive.google.com/paper4','Summary pap 4','Paper 4'),(2388,0,'http://www.drive.google.com/paper5','Summary pap 5','Paper 5');
/*!40000 ALTER TABLE `paper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paper_authors`
--

DROP TABLE IF EXISTS `paper_authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paper_authors` (
  `paper` int(11) NOT NULL,
  `authors` varchar(255) DEFAULT NULL,
  KEY `FK_ae9a28ln0ji506i8uoqhv563h` (`paper`),
  CONSTRAINT `FK_ae9a28ln0ji506i8uoqhv563h` FOREIGN KEY (`paper`) REFERENCES `paper` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paper_authors`
--

LOCK TABLES `paper_authors` WRITE;
/*!40000 ALTER TABLE `paper_authors` DISABLE KEYS */;
INSERT INTO `paper_authors` VALUES (2384,'Author nº1, Author nº2, Author nº3'),(2385,'Author nº1, Author nº2, Author nº3'),(2386,'Author nº1, Author nº2, Author nº3'),(2387,'Author nº1, Author nº2, Author nº3'),(2388,'Author nº1, Author nº2, Author nº3');
/*!40000 ALTER TABLE `paper_authors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `presentation`
--

DROP TABLE IF EXISTS `presentation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `presentation` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `duration` int(11) NOT NULL,
  `room` varchar(255) DEFAULT NULL,
  `schedule` datetime DEFAULT NULL,
  `start_moment` datetime DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `conference` int(11) NOT NULL,
  `camera_ready_paper` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_sgmmubx8ftlft0yxjdvnft0m8` (`camera_ready_paper`),
  KEY `FK_fu56gussuaw8r8865h3x4cvq0` (`conference`),
  CONSTRAINT `FK_fu56gussuaw8r8865h3x4cvq0` FOREIGN KEY (`conference`) REFERENCES `conference` (`id`),
  CONSTRAINT `FK_sgmmubx8ftlft0yxjdvnft0m8` FOREIGN KEY (`camera_ready_paper`) REFERENCES `camera_ready_paper` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `presentation`
--

LOCK TABLES `presentation` WRITE;
/*!40000 ALTER TABLE `presentation` DISABLE KEYS */;
INSERT INTO `presentation` VALUES (2426,0,40,'A1.42','2019-12-13 10:40:00','2019-12-13 10:00:00','Summary presentation 1','Presentation number 1',2352,2418),(2427,0,40,'A1.43','2019-12-13 10:40:00','2019-10-11 10:00:00','Summary presentation 2','presentation number 2',2356,2419);
/*!40000 ALTER TABLE `presentation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registration`
--

DROP TABLE IF EXISTS `registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registration` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `cvv` int(11) DEFAULT NULL,
  `expiration_month` int(11) DEFAULT NULL,
  `expiration_year` int(11) DEFAULT NULL,
  `holder_name` varchar(255) DEFAULT NULL,
  `make_name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `author` int(11) NOT NULL,
  `conference` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_jpwxbrhvii9ekt338bmrdatfg` (`author`),
  KEY `FK_lc3odbpd5lgo7qc3w33ugwafj` (`conference`),
  CONSTRAINT `FK_lc3odbpd5lgo7qc3w33ugwafj` FOREIGN KEY (`conference`) REFERENCES `conference` (`id`),
  CONSTRAINT `FK_jpwxbrhvii9ekt338bmrdatfg` FOREIGN KEY (`author`) REFERENCES `author` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registration`
--

LOCK TABLES `registration` WRITE;
/*!40000 ALTER TABLE `registration` DISABLE KEYS */;
INSERT INTO `registration` VALUES (2408,0,123,12,2025,'Author1 Author1','VISA','1111222233334444',2309,2352),(2409,0,234,12,2025,'Author2 Author2','VISA','1111222233334444',2310,2353);
/*!40000 ALTER TABLE `registration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `decision` varchar(255) DEFAULT NULL,
  `originality_score` int(11) NOT NULL,
  `quality_score` int(11) NOT NULL,
  `readability_score` int(11) NOT NULL,
  `reviewer` int(11) NOT NULL,
  `submission` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_26pbnwfa1gklnebnnsotvqt88` (`reviewer`),
  KEY `FK_a0lt5jqh9b7s1gw3q77nywxxn` (`submission`),
  CONSTRAINT `FK_a0lt5jqh9b7s1gw3q77nywxxn` FOREIGN KEY (`submission`) REFERENCES `submission` (`id`),
  CONSTRAINT `FK_26pbnwfa1gklnebnnsotvqt88` FOREIGN KEY (`reviewer`) REFERENCES `reviewer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
INSERT INTO `report` VALUES (2420,0,'ACCEPT',6,5,7,2311,2411),(2421,0,'ACCEPT',4,6,8,2312,2411),(2422,0,'REJECT',2,3,4,2313,2411),(2423,0,'REJECT',2,3,4,2311,2412),(2424,0,'REJECT',3,3,2,2312,2412),(2425,0,'ACCEPT',6,5,5,2313,2412);
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviewer`
--

DROP TABLE IF EXISTS `reviewer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reviewer` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ite8gbxlfjyy7g7wqqiyjhkmn` (`user_account`),
  CONSTRAINT `FK_ite8gbxlfjyy7g7wqqiyjhkmn` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviewer`
--

LOCK TABLES `reviewer` WRITE;
/*!40000 ALTER TABLE `reviewer` DISABLE KEYS */;
INSERT INTO `reviewer` VALUES (2311,0,'C/ Saturday, nº 13','reviewer1@gmail.com','RC','Manuel','+34 654455557','http://wwww.photo4.com','Pérez',2300),(2312,0,'C/ Saturday, nº 14','reviewer2@gmail.com','RC2','Mónica','+34 654455558','http://wwww.photo5.com','Revilla',2301),(2313,0,'C/ Sunday, nº 14','reviewer3@gmail.com','Rev 3','Antonio','+34 654412358','http://wwww.photo8.com','Losada',2304),(2314,0,'C/ Tuesday, nº 14','reviewer4@gmail.com','Rev 4','Carlos','+34 654122358','http://wwww.photo4.com','Cruz',2305),(2315,0,'C/ Saturday, nº 14','reviewer5@gmail.com','Rev 5','Nelson','+34 651222358','http://wwww.photo5.com','Muñiz',2306);
/*!40000 ALTER TABLE `reviewer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviewer_expertise`
--

DROP TABLE IF EXISTS `reviewer_expertise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reviewer_expertise` (
  `reviewer` int(11) NOT NULL,
  `expertise` varchar(255) DEFAULT NULL,
  KEY `FK_5jghl4veyunssejabmkb34iy4` (`reviewer`),
  CONSTRAINT `FK_5jghl4veyunssejabmkb34iy4` FOREIGN KEY (`reviewer`) REFERENCES `reviewer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviewer_expertise`
--

LOCK TABLES `reviewer_expertise` WRITE;
/*!40000 ALTER TABLE `reviewer_expertise` DISABLE KEYS */;
INSERT INTO `reviewer_expertise` VALUES (2311,'research'),(2311,'reviews'),(2312,'research'),(2312,'reviews'),(2313,'research'),(2313,'reviews'),(2314,'natural'),(2314,'language'),(2314,'intelligence'),(2314,'artificial'),(2315,'natural'),(2315,'language'),(2315,'intelligence'),(2315,'artificial');
/*!40000 ALTER TABLE `reviewer_expertise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `section`
--

DROP TABLE IF EXISTS `section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `section` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `tutorial` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_6tcpk427cxiaiglbxvybc9fjh` (`tutorial`),
  CONSTRAINT `FK_6tcpk427cxiaiglbxvybc9fjh` FOREIGN KEY (`tutorial`) REFERENCES `tutorial` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `section`
--

LOCK TABLES `section` WRITE;
/*!40000 ALTER TABLE `section` DISABLE KEYS */;
INSERT INTO `section` VALUES (2400,0,'Summary section 1','Section number 1',2397),(2401,0,'Summary section 2','Section number 2',2398),(2402,0,'Summary section 3','Section number 3',2399);
/*!40000 ALTER TABLE `section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `section_pictures`
--

DROP TABLE IF EXISTS `section_pictures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `section_pictures` (
  `section` int(11) NOT NULL,
  `pictures` varchar(255) DEFAULT NULL,
  KEY `FK_fpgvw49vb6tytfbfcghj3o8sv` (`section`),
  CONSTRAINT `FK_fpgvw49vb6tytfbfcghj3o8sv` FOREIGN KEY (`section`) REFERENCES `section` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `section_pictures`
--

LOCK TABLES `section_pictures` WRITE;
/*!40000 ALTER TABLE `section_pictures` DISABLE KEYS */;
INSERT INTO `section_pictures` VALUES (2400,'http://drive.google.com/picture6, http://drive.google.com/picture2'),(2401,'http://drive.google.com/picture6, http://drive.google.com/picture2'),(2402,'http://drive.google.com/picture6, http://drive.google.com/picture2');
/*!40000 ALTER TABLE `section_pictures` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sponsor`
--

DROP TABLE IF EXISTS `sponsor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sponsor` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_du2w5ldt8rvlvxvtr7vyxk7g3` (`user_account`),
  CONSTRAINT `FK_du2w5ldt8rvlvxvtr7vyxk7g3` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sponsor`
--

LOCK TABLES `sponsor` WRITE;
/*!40000 ALTER TABLE `sponsor` DISABLE KEYS */;
INSERT INTO `sponsor` VALUES (2316,0,'C/ Saturday, nº 15','sponsor1@gmail.com','SP1','Sponsor1','+34 654455559','http://wwww.photo6.com','Prueba5',2296),(2317,0,'C/ Saturday, nº 16','sponsor2@gmail.com','SP2','Sponsor2','+34 654455560','http://wwww.photo7.com','Prueba6',2297);
/*!40000 ALTER TABLE `sponsor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sponsorship`
--

DROP TABLE IF EXISTS `sponsorship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sponsorship` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `banner` varchar(255) DEFAULT NULL,
  `cvv` int(11) DEFAULT NULL,
  `expiration_month` int(11) DEFAULT NULL,
  `expiration_year` int(11) DEFAULT NULL,
  `holder_name` varchar(255) DEFAULT NULL,
  `make_name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `target_url` varchar(255) DEFAULT NULL,
  `sponsor` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_huglhkud0ihqdljyou4eshra6` (`sponsor`),
  CONSTRAINT `FK_huglhkud0ihqdljyou4eshra6` FOREIGN KEY (`sponsor`) REFERENCES `sponsor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sponsorship`
--

LOCK TABLES `sponsorship` WRITE;
/*!40000 ALTER TABLE `sponsorship` DISABLE KEYS */;
INSERT INTO `sponsorship` VALUES (2403,0,'http://g-ec2.images-amazon.com/images/G/01/social/api-share/amazon_logo_500500.png',123,12,2021,'Sponsor1','VISA','4658308474901109','https://www.amazon.es/',2316),(2404,0,'https://imagenes.universia.net/gc/net/images/educacion/g/go/goo/goodreads-1511531089262.jpg',123,12,2021,'Sponsor1','VISA','4658308474901109','https://www.goodreads.com/',2316),(2405,0,'http://static.t13.cl/images/sizes/1200x675/1532011025-vlcsnap-2018-07-19-10h36m13s76.jpg',321,10,2020,'Sponsor2','MASTERCARD','4916697351767254','https://www.youtube.com/',2317),(2406,0,'https://www.pcactual.com/medio/2018/11/21/google-drive_0e2d10f8.jpg',321,10,2020,'Sponsor2','MASTERCARD','4916697351767254','https://www.google.com/intl/es_ALL/drive/',2317);
/*!40000 ALTER TABLE `sponsorship` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `submission`
--

DROP TABLE IF EXISTS `submission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `submission` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `moment` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `ticker` varchar(255) DEFAULT NULL,
  `author` int(11) NOT NULL,
  `conference` int(11) NOT NULL,
  `paper` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_3osm3qjvt3r41xau9xwf5nkdt` (`paper`),
  UNIQUE KEY `UK_9ayhftkow8judgm0cblwdb9mi` (`ticker`),
  KEY `FK_ssk77t9sokwi9utdru9hvodul` (`author`),
  KEY `FK_1vynnfw6cw1l40c8e342st672` (`conference`),
  CONSTRAINT `FK_3osm3qjvt3r41xau9xwf5nkdt` FOREIGN KEY (`paper`) REFERENCES `paper` (`id`),
  CONSTRAINT `FK_1vynnfw6cw1l40c8e342st672` FOREIGN KEY (`conference`) REFERENCES `conference` (`id`),
  CONSTRAINT `FK_ssk77t9sokwi9utdru9hvodul` FOREIGN KEY (`author`) REFERENCES `author` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `submission`
--

LOCK TABLES `submission` WRITE;
/*!40000 ALTER TABLE `submission` DISABLE KEYS */;
INSERT INTO `submission` VALUES (2410,0,'2018-09-15 10:00:00','ACCEPTED','AAA-1234',2309,2352,2384),(2411,0,'2018-09-15 10:00:00','UNDER-REVIEW','AAA-2345',2309,2353,2385),(2412,0,'2018-09-15 10:00:00','UNDER-REVIEW','AAA-3456',2309,2354,2386),(2413,0,'2018-09-15 10:00:00','ACCEPTED','AAA-3458',2310,2354,2387),(2414,0,'2018-09-15 10:00:00','UNDER-REVIEW','AAA-4567',2310,2356,2388);
/*!40000 ALTER TABLE `submission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `submission_form`
--

DROP TABLE IF EXISTS `submission_form`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `submission_form` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `document` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `ticker` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `author` int(11) NOT NULL,
  `camera_ready_paper` int(11) DEFAULT NULL,
  `conference` int(11) NOT NULL,
  `paper` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_760u2bubhhx01nxr7eylknwib` (`paper`),
  UNIQUE KEY `UK_nr1md7ognnsjuje1nriiy6cyv` (`ticker`),
  KEY `FK_3fnjmfprbovi710kqli73xku9` (`author`),
  KEY `FK_7yfmvo3s30c8qdojp2oew4rlh` (`camera_ready_paper`),
  KEY `FK_jcaas9u9rvyhdj9ba31sb3lph` (`conference`),
  CONSTRAINT `FK_760u2bubhhx01nxr7eylknwib` FOREIGN KEY (`paper`) REFERENCES `paper` (`id`),
  CONSTRAINT `FK_3fnjmfprbovi710kqli73xku9` FOREIGN KEY (`author`) REFERENCES `author` (`id`),
  CONSTRAINT `FK_7yfmvo3s30c8qdojp2oew4rlh` FOREIGN KEY (`camera_ready_paper`) REFERENCES `camera_ready_paper` (`id`),
  CONSTRAINT `FK_jcaas9u9rvyhdj9ba31sb3lph` FOREIGN KEY (`conference`) REFERENCES `conference` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `submission_form`
--

LOCK TABLES `submission_form` WRITE;
/*!40000 ALTER TABLE `submission_form` DISABLE KEYS */;
/*!40000 ALTER TABLE `submission_form` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `submission_form_authors`
--

DROP TABLE IF EXISTS `submission_form_authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `submission_form_authors` (
  `submission_form` int(11) NOT NULL,
  `authors` varchar(255) DEFAULT NULL,
  KEY `FK_rtaj4jml3bru5db7yg8jygk8w` (`submission_form`),
  CONSTRAINT `FK_rtaj4jml3bru5db7yg8jygk8w` FOREIGN KEY (`submission_form`) REFERENCES `submission_form` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `submission_form_authors`
--

LOCK TABLES `submission_form_authors` WRITE;
/*!40000 ALTER TABLE `submission_form_authors` DISABLE KEYS */;
/*!40000 ALTER TABLE `submission_form_authors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `submission_reviewers`
--

DROP TABLE IF EXISTS `submission_reviewers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `submission_reviewers` (
  `submission` int(11) NOT NULL,
  `reviewers` int(11) NOT NULL,
  KEY `FK_rx31lgg0k67efoplhxv8len0c` (`reviewers`),
  KEY `FK_iwsj2ioiue7vmn5bnhxb2oatb` (`submission`),
  CONSTRAINT `FK_iwsj2ioiue7vmn5bnhxb2oatb` FOREIGN KEY (`submission`) REFERENCES `submission` (`id`),
  CONSTRAINT `FK_rx31lgg0k67efoplhxv8len0c` FOREIGN KEY (`reviewers`) REFERENCES `reviewer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `submission_reviewers`
--

LOCK TABLES `submission_reviewers` WRITE;
/*!40000 ALTER TABLE `submission_reviewers` DISABLE KEYS */;
INSERT INTO `submission_reviewers` VALUES (2410,2311),(2410,2314),(2410,2315),(2412,2311),(2412,2312),(2412,2313),(2413,2311),(2413,2312),(2413,2313);
/*!40000 ALTER TABLE `submission_reviewers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tutorial`
--

DROP TABLE IF EXISTS `tutorial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tutorial` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `duration` int(11) NOT NULL,
  `room` varchar(255) DEFAULT NULL,
  `schedule` datetime DEFAULT NULL,
  `start_moment` datetime DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `conference` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_gd8wor872gtoqt1siaw2k4din` (`conference`),
  CONSTRAINT `FK_gd8wor872gtoqt1siaw2k4din` FOREIGN KEY (`conference`) REFERENCES `conference` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tutorial`
--

LOCK TABLES `tutorial` WRITE;
/*!40000 ALTER TABLE `tutorial` DISABLE KEYS */;
INSERT INTO `tutorial` VALUES (2397,0,40,'A1.45','2019-12-12 10:40:00','2019-12-12 10:00:00','Summary tutorial 1','Tutorial number 1',2352),(2398,0,40,'A1.45','2019-12-12 10:40:00','2019-12-12 10:00:00','Summary tutorial 2','Tutorial number 2',2356),(2399,0,40,'A1.45','2019-12-12 10:40:00','2019-12-12 10:00:00','Summary tutorial 3','Tutorial number 2',2356);
/*!40000 ALTER TABLE `tutorial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_castjbvpeeus0r8lbpehiu0e4` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES (2295,0,'21232f297a57a5a743894a0e4a801fc3','admin'),(2296,0,'42c63ad66d4dc07ed17753772bef96d6','sponsor1'),(2297,0,'3dc67f80a03324e01b1640f45d107485','sponsor2'),(2298,0,'b312ba4ffd5245fa2a1ab819ec0d0347','author1'),(2299,0,'9bd97baef2b853ec00cc3cffd269f679','author2'),(2300,0,'6ce19528a40dde9521d97cf7ba264eca','reviewer1'),(2301,0,'2693b57f0f59df94caacefb811e99851','reviewer2'),(2302,0,'1b3231655cebb7a1f783eddf27d254ca','super'),(2303,0,'c84258e9c39059a89ab77d846ddab909','admin2'),(2304,0,'315d31e7c8f3a136610aafa220d689be','reviewer3'),(2305,0,'5293e8663cbb7c157ff83eeae25177d3','reviewer4'),(2306,0,'86045e9e3b2e615d8d2484dc64b3f408','reviewer5');
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account_authorities`
--

DROP TABLE IF EXISTS `user_account_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account_authorities` (
  `user_account` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FK_pao8cwh93fpccb0bx6ilq6gsl` (`user_account`),
  CONSTRAINT `FK_pao8cwh93fpccb0bx6ilq6gsl` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account_authorities`
--

LOCK TABLES `user_account_authorities` WRITE;
/*!40000 ALTER TABLE `user_account_authorities` DISABLE KEYS */;
INSERT INTO `user_account_authorities` VALUES (2295,'ADMIN'),(2296,'SPONSOR'),(2297,'SPONSOR'),(2298,'AUTHOR'),(2299,'AUTHOR'),(2300,'REVIEWER'),(2301,'REVIEWER'),(2302,'ADMIN'),(2302,'SPONSOR'),(2302,'AUTHOR'),(2302,'REVIEWER'),(2303,'ADMIN'),(2304,'REVIEWER'),(2305,'REVIEWER'),(2306,'REVIEWER');
/*!40000 ALTER TABLE `user_account_authorities` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-03 18:00:14
