-- MySQL dump 10.13  Distrib 5.6.30, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: house
-- ------------------------------------------------------
-- Server version	5.6.30-0ubuntu0.14.04.1

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
-- Table structure for table `earth_basic_data`
--

DROP TABLE IF EXISTS `earth_basic_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `earth_basic_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `earth_license_id` varchar(255) NOT NULL COMMENT '国土证ID',
  `project_id` varchar(255) NOT NULL DEFAULT '' COMMENT '项目ID',
  `earth_license_no` varchar(255) NOT NULL COMMENT '国土证号',
  `location` varchar(255) NOT NULL DEFAULT '' COMMENT '土地座落',
  `userr` varchar(255) NOT NULL DEFAULT '' COMMENT '土地使用者',
  `earth_no` varchar(255) NOT NULL DEFAULT '' COMMENT '地号',
  `graph_no` varchar(255) NOT NULL DEFAULT '' COMMENT '图号',
  `usagee` varchar(255) NOT NULL DEFAULT '' COMMENT '土地用途',
  `levell` varchar(255) NOT NULL DEFAULT '' COMMENT '土地等级',
  `borrow_from` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '土地出让年限自',
  `use_right_kind` varchar(255) NOT NULL DEFAULT '' COMMENT '使用权类型',
  `use_area` varchar(255) NOT NULL DEFAULT '' COMMENT '使用权面积',
  `share_area` varchar(255) NOT NULL DEFAULT '' COMMENT '其中共用分推面积',
  `license_office` varchar(255) NOT NULL DEFAULT '' COMMENT '发证机关',
  `license_issue_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发证日期',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `earth_license_id_UNIQUE` (`earth_license_id`),
  KEY `borrow_from_INDEX` (`borrow_from`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-03 23:22:40
