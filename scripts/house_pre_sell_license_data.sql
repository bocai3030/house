-- MySQL dump 10.13  Distrib 5.7.12, for Linux (x86_64)
--
-- Host: localhost    Database: house
-- ------------------------------------------------------
-- Server version	5.7.12-0ubuntu1.1

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
-- Table structure for table `pre_sell_license_data`
--

DROP TABLE IF EXISTS `pre_sell_license_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pre_sell_license_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pre_sell_license_id` varchar(255) NOT NULL COMMENT '预售许可证号',
  `building_count` int(11) NOT NULL DEFAULT '0' COMMENT '预售幢数',
  `building_house` varchar(255) NOT NULL DEFAULT '' COMMENT '报建屋数',
  `built_house` varchar(255) NOT NULL DEFAULT '' COMMENT '已建层数',
  `current_phase` int(11) NOT NULL DEFAULT '0' COMMENT '本期期数',
  `current_phase_building_area` varchar(255) NOT NULL DEFAULT '' COMMENT '本期报建总面积',
  `area_up_ground` varchar(255) NOT NULL DEFAULT '' COMMENT '地上面积',
  `area_under_ground` varchar(255) NOT NULL DEFAULT '' COMMENT '地下面积',
  `unit_count` int(11) NOT NULL DEFAULT '0' COMMENT '本期总单元套数',
  `total_buiding_area` varchar(255) NOT NULL DEFAULT '' COMMENT '总建筑面积',
  `contact_persion` varchar(255) NOT NULL DEFAULT '' COMMENT '联系人',
  `mortgage` varchar(255) NOT NULL DEFAULT '' COMMENT '土地是否抵押',
  `supporting_area` varchar(255) NOT NULL DEFAULT '' COMMENT '配套面积',
  `validate_from` varchar(255) NOT NULL DEFAULT '' COMMENT '有效期自',
  `validate_to` varchar(255) NOT NULL DEFAULT '' COMMENT '有效期至',
  `license_issue_date` varchar(255) NOT NULL DEFAULT '' COMMENT '发证日期',
  `distribute_of_residential_count` int(11) NOT NULL DEFAULT '0' COMMENT '住宅套数',
  `distribute_of_residential_area` varchar(255) NOT NULL DEFAULT '' COMMENT '住宅面积',
  `distribute_of_bussiness_count` int(11) NOT NULL DEFAULT '0' COMMENT '商业套数',
  `distribute_of_bussiness_area` varchar(255) NOT NULL DEFAULT '' COMMENT '商业面积',
  `distribute_of_office_count` int(11) NOT NULL DEFAULT '0' COMMENT '办公套数',
  `distribute_of_office_area` varchar(255) NOT NULL DEFAULT '' COMMENT '办公面积',
  `distribute_of_parking_count` int(11) NOT NULL DEFAULT '0' COMMENT '车位套数',
  `distribute_of_parking_area` varchar(255) NOT NULL DEFAULT '' COMMENT '车位面积',
  `distribute_of_other_count` int(11) NOT NULL DEFAULT '0' COMMENT '其他套数',
  `distribute_of_other_area` varchar(255) NOT NULL DEFAULT '' COMMENT '其他面积',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `pre_sell_license_id_UNIQUE` (`pre_sell_license_id`)
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

-- Dump completed on 2016-05-30 22:12:57
