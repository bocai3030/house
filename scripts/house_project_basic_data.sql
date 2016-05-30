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
-- Table structure for table `project_basic_data`
--

DROP TABLE IF EXISTS `project_basic_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_basic_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` varchar(255) NOT NULL COMMENT '项目ID',
  `project_name` varchar(255) NOT NULL DEFAULT '' COMMENT '所属项目名称',
  `pre_sell_license_id` varchar(255) NOT NULL COMMENT '预售许可证号',
  `country_name` varchar(255) NOT NULL DEFAULT '' COMMENT '国土证名',
  `country_id` varchar(255) NOT NULL DEFAULT '' COMMENT '国土证ID',
  `agree_name` varchar(255) NOT NULL DEFAULT '' COMMENT '施工许可证名',
  `agree_id` varchar(255) NOT NULL DEFAULT '' COMMENT '施工许可证ID',
  `layout_name` varchar(255) NOT NULL DEFAULT '' COMMENT '规划许可证名',
  `layout_id` varchar(255) NOT NULL DEFAULT '' COMMENT '规划许可证ID',
  `project_address` varchar(255) NOT NULL DEFAULT '' COMMENT '项目地址',
  `developer` varchar(255) NOT NULL DEFAULT '' COMMENT '开发商',
  `developer_id` varchar(255) NOT NULL DEFAULT '' COMMENT '开发商ID',
  `division` varchar(255) NOT NULL DEFAULT '' COMMENT '行政区划',
  `section_id` varchar(255) NOT NULL DEFAULT '' COMMENT '区',
  `total_cost_area` varchar(255) NOT NULL DEFAULT '' COMMENT '总占地面积',
  `total_build_area` varchar(255) NOT NULL DEFAULT '' COMMENT '总建筑面积',
  `qualification_licence_no` varchar(255) NOT NULL DEFAULT '' COMMENT '资质证书编号',
  `usagee` varchar(255) NOT NULL DEFAULT '' COMMENT '用途',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `project_id_UNIQUE` (`project_id`),
  UNIQUE KEY `pre_sell_license_id_UNIQUE` (`pre_sell_license_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-30  9:17:16
