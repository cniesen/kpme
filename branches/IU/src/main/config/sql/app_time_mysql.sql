-- MySQL dump 10.13  Distrib 5.1.41, for debian-linux-gnu (i486)
--
-- Host: andover    Database: tk
-- ------------------------------------------------------
-- Server version	5.1.41-3ubuntu12

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
-- Table structure for table `tk_assignment_s`
--

DROP TABLE IF EXISTS `tk_assignment_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_assignment_s` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2085 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_assignment_t`
--

DROP TABLE IF EXISTS `tk_assignment_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_assignment_t` (
  `ASSIGNMENT_ID` bigint(19) NOT NULL,
  `PRINCIPAL_ID` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `JOB_NUMBER` decimal(3,0) DEFAULT NULL,
  `EFFDT` date NOT NULL DEFAULT '0000-00-00',
  `ERNCD` varchar(3) COLLATE utf8_bin DEFAULT NULL,
  `EFFSEQ` decimal(38,0) DEFAULT NULL,
  `WORK_AREA_ID` decimal(10,0) DEFAULT NULL,
  `TASK_ID` decimal(10,0) DEFAULT NULL,
  `PERCENT` decimal(5,2) DEFAULT NULL,
  `OBJ_ID` varchar(36) COLLATE utf8_bin NOT NULL,
  `VER_NBR` decimal(8,0) NOT NULL DEFAULT '1',
  PRIMARY KEY (`ASSIGNMENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_clock_location_rl_s`
--

DROP TABLE IF EXISTS `tk_clock_location_rl_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_clock_location_rl_s` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2080 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_clock_location_rl_t`
--

DROP TABLE IF EXISTS `tk_clock_location_rl_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_clock_location_rl_t` (
  `CLOCK_LOC_RULE_ID` bigint(19) NOT NULL,
  `WORK_AREA_ID` decimal(10,0) DEFAULT NULL,
  `PRINCIPALID` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `JOB_NUMBER` decimal(3,0) DEFAULT NULL,
  `EFFDT` date DEFAULT NULL,
  `EFF_STATUS` bit(1) DEFAULT NULL,
  `IP_ADDRESS` varchar(15) COLLATE utf8_bin DEFAULT NULL,
  `USER_PRINCIPALID` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `TIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `OBJ_ID` varchar(36) COLLATE utf8_bin NOT NULL,
  `VER_NBR` decimal(8,0) NOT NULL DEFAULT '1',
  `department_id` bigint(19) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_clock_log_s`
--

DROP TABLE IF EXISTS `tk_clock_log_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_clock_log_s` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2092 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_clock_log_t`
--

DROP TABLE IF EXISTS `tk_clock_log_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_clock_log_t` (
  `PRINCIPAL_ID` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `JOB_NUMBER` decimal(3,0) DEFAULT NULL,
  `WORK_AREA_ID` decimal(10,0) DEFAULT NULL,
  `TASK_ID` decimal(10,0) DEFAULT NULL,
  `CLOCK_TS` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `CLOCK_TS_TZ` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `CLOCK_ACTION` varchar(2) COLLATE utf8_bin DEFAULT NULL,
  `IP_ADDRESS` varchar(15) COLLATE utf8_bin DEFAULT NULL,
  `USER_PRINCIPAL_ID` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `TIMESTAMP` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `OBJ_ID` varchar(36) COLLATE utf8_bin NOT NULL,
  `VER_NBR` decimal(8,0) NOT NULL DEFAULT '1',
  `CLOCK_LOG_ID` bigint(19) NOT NULL,
  PRIMARY KEY (`CLOCK_LOG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_dept_t`
--

DROP TABLE IF EXISTS `tk_dept_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_dept_t` (
  `dept_name` varchar(7) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `ORG` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `CHART` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `dept_id` bigint(19) NOT NULL,
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_time_block_t`
--

DROP TABLE IF EXISTS `tk_time_block_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_time_block_t` (
  `DOCUMENT_ID` varchar(14) COLLATE utf8_bin DEFAULT NULL,
  `JOB_NUMBER` decimal(3,0) DEFAULT NULL,
  `WORK_AREA_ID` decimal(10,0) DEFAULT NULL,
  `TASK_ID` decimal(10,0) DEFAULT NULL,
  `ERNCD` varchar(3) COLLATE utf8_bin DEFAULT NULL,
  `BEGIN_TS` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `BEGIN_TS_TZ` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `END_TS` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `END_TS_TZ` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `CLOCK_LOG_CREATED` bit(1) DEFAULT b'0',
  `HOURS` decimal(5,2) DEFAULT NULL,
  `USER_PRINCIPAL_ID` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `TIMESTAMP` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `OBJ_ID` varchar(36) COLLATE utf8_bin NOT NULL,
  `VER_NBR` decimal(8,0) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2010-06-03 16:44:32
