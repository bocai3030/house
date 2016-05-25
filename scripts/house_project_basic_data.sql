CREATE DATABASE  IF NOT EXISTS `house` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `house`;
-- MySQL dump 10.13  Distrib 5.5.49, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: house
-- ------------------------------------------------------
-- Server version	5.5.49-0ubuntu0.14.04.1

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
-- Table structure for table `project_basic_data`
--

DROP TABLE IF EXISTS `project_basic_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_basic_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` varchar(255) NOT NULL COMMENT '项目ID',
  `project_name` varchar(255) DEFAULT NULL COMMENT '所属项目名称',
  `pre_sell_licence_no` varchar(255) NOT NULL COMMENT '预售许可证号',
  `project_address` varchar(255) DEFAULT NULL COMMENT '项目地址',
  `developer` varchar(255) DEFAULT NULL COMMENT '开发商',
  `division` varchar(255) DEFAULT NULL COMMENT '行政区划',
  `total_cost_area` varchar(255) DEFAULT NULL COMMENT '总占地面积',
  `total_build_area` varchar(255) DEFAULT NULL COMMENT '总建筑面积',
  `qualification_licence_no` varchar(255) DEFAULT NULL COMMENT '资质证书编号',
  `usagee` varchar(255) DEFAULT NULL COMMENT '用途',
  `pre_sell_total_count` int(11) DEFAULT NULL COMMENT '批准预售总套数',
  `pre_sell_total_area` varchar(255) DEFAULT NULL COMMENT '批准预售总面积',
  `building_count` int(11) DEFAULT NULL COMMENT '预售幢数',
  `licence_date` varchar(255) DEFAULT NULL COMMENT '发证日期',
  `create_time` datetime DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `project_id_UNIQUE` (`project_id`),
  UNIQUE KEY `pre_sell_licence_no_UNIQUE` (`pre_sell_licence_no`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-26  3:31:14
