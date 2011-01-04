
-- START CHANGE SCRIPT #1: 001-0.1.0-tk_la_ca_hr_schema.sql

-- MySQL dump 10.13  Distrib 5.1.41, for debian-linux-gnu (i486)
--
-- Host: andover    Database: tk
-- ------------------------------------------------------
-- Server version	5.1.41-3ubuntu12.6

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
-- Table structure for table `ca_account_t`
--

DROP TABLE IF EXISTS `ca_account_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ca_account_t` (
  `FIN_COA_CD` varchar(2) COLLATE utf8_bin NOT NULL DEFAULT '',
  `ACCOUNT_NBR` varchar(10) COLLATE utf8_bin NOT NULL DEFAULT '',
  `OBJ_ID` varchar(36) COLLATE utf8_bin NOT NULL,
  `VER_NBR` decimal(8,0) NOT NULL DEFAULT '1',
  `ACCOUNT_NM` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `ORG_CD` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `ACCT_CREATE_DT` datetime DEFAULT NULL,
  `ACCT_EFFECT_DT` datetime DEFAULT NULL,
  `ACCT_EXPIRATION_DT` datetime DEFAULT NULL,
  `ACCT_CLOSED_IND` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`FIN_COA_CD`,`ACCOUNT_NBR`),
  UNIQUE KEY `CA_ACCOUNT_TC0` (`OBJ_ID`),
  KEY `CA_ACCOUNT_TI2` (`ACCOUNT_NBR`,`FIN_COA_CD`),
  KEY `CA_ACCOUNT_TR2` (`FIN_COA_CD`,`ORG_CD`),
  CONSTRAINT `CA_ACCOUNT_TR1` FOREIGN KEY (`FIN_COA_CD`) REFERENCES `ca_chart_t` (`FIN_COA_CD`),
  CONSTRAINT `CA_ACCOUNT_TR2` FOREIGN KEY (`FIN_COA_CD`, `ORG_CD`) REFERENCES `ca_org_t` (`FIN_COA_CD`, `ORG_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ca_chart_t`
--

DROP TABLE IF EXISTS `ca_chart_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ca_chart_t` (
  `FIN_COA_CD` varchar(2) COLLATE utf8_bin NOT NULL DEFAULT '',
  `OBJ_ID` varchar(36) COLLATE utf8_bin NOT NULL,
  `VER_NBR` decimal(8,0) NOT NULL DEFAULT '1',
  `FIN_COA_DESC` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `FIN_COA_ACTIVE_CD` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`FIN_COA_CD`),
  UNIQUE KEY `CA_CHART_TC0` (`OBJ_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ca_object_code_t`
--

DROP TABLE IF EXISTS `ca_object_code_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ca_object_code_t` (
  `UNIV_FISCAL_YR` decimal(4,0) NOT NULL DEFAULT '0',
  `FIN_COA_CD` varchar(2) COLLATE utf8_bin NOT NULL DEFAULT '',
  `FIN_OBJECT_CD` varchar(5) COLLATE utf8_bin NOT NULL DEFAULT '',
  `OBJ_ID` varchar(36) COLLATE utf8_bin NOT NULL,
  `VER_NBR` decimal(8,0) NOT NULL DEFAULT '1',
  `FIN_OBJ_CD_NM` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `FIN_OBJ_CD_SHRT_NM` varchar(12) COLLATE utf8_bin DEFAULT NULL,
  `FIN_OBJ_ACTIVE_CD` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`UNIV_FISCAL_YR`,`FIN_COA_CD`,`FIN_OBJECT_CD`),
  UNIQUE KEY `CA_OBJECT_CODE_TC0` (`OBJ_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ca_org_t`
--

DROP TABLE IF EXISTS `ca_org_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ca_org_t` (
  `FIN_COA_CD` varchar(2) COLLATE utf8_bin NOT NULL DEFAULT '',
  `ORG_CD` varchar(10) COLLATE utf8_bin NOT NULL DEFAULT '',
  `OBJ_ID` varchar(36) COLLATE utf8_bin NOT NULL,
  `VER_NBR` decimal(8,0) NOT NULL DEFAULT '1',
  `ORG_NM` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `ORG_ACTIVE_CD` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`FIN_COA_CD`,`ORG_CD`),
  UNIQUE KEY `CA_ORG_TC0` (`OBJ_ID`),
  CONSTRAINT `CA_ORG_TR1` FOREIGN KEY (`FIN_COA_CD`) REFERENCES `ca_chart_t` (`FIN_COA_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ca_project_t`
--

DROP TABLE IF EXISTS `ca_project_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ca_project_t` (
  `PROJECT_CD` varchar(10) COLLATE utf8_bin NOT NULL DEFAULT '',
  `OBJ_ID` varchar(36) COLLATE utf8_bin NOT NULL,
  `VER_NBR` decimal(8,0) NOT NULL DEFAULT '1',
  `PROJECT_NM` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `FIN_COA_CD` varchar(2) COLLATE utf8_bin DEFAULT NULL,
  `ORG_CD` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `PROJ_ACTIVE_CD` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `PROJECT_DESC` varchar(400) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`PROJECT_CD`),
  UNIQUE KEY `CA_PROJECT_TC0` (`OBJ_ID`),
  KEY `CA_PROJECT_TI2` (`FIN_COA_CD`,`ORG_CD`),
  CONSTRAINT `CA_PROJECT_TR1` FOREIGN KEY (`FIN_COA_CD`) REFERENCES `ca_chart_t` (`FIN_COA_CD`),
  CONSTRAINT `CA_PROJECT_TR2` FOREIGN KEY (`FIN_COA_CD`, `ORG_CD`) REFERENCES `ca_org_t` (`FIN_COA_CD`, `ORG_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ca_sub_acct_t`
--

DROP TABLE IF EXISTS `ca_sub_acct_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ca_sub_acct_t` (
  `FIN_COA_CD` varchar(2) COLLATE utf8_bin NOT NULL DEFAULT '',
  `ACCOUNT_NBR` varchar(10) COLLATE utf8_bin NOT NULL DEFAULT '',
  `SUB_ACCT_NBR` varchar(5) COLLATE utf8_bin NOT NULL DEFAULT '',
  `OBJ_ID` varchar(36) COLLATE utf8_bin NOT NULL,
  `VER_NBR` decimal(8,0) NOT NULL DEFAULT '1',
  `SUB_ACCT_NM` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `SUB_ACCT_ACTV_CD` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`FIN_COA_CD`,`ACCOUNT_NBR`,`SUB_ACCT_NBR`),
  UNIQUE KEY `CA_SUB_ACCT_TC0` (`OBJ_ID`),
  KEY `CA_SUB_ACCT_TI2` (`FIN_COA_CD`,`ACCOUNT_NBR`),
  CONSTRAINT `CA_SUB_ACCT_TR2` FOREIGN KEY (`FIN_COA_CD`) REFERENCES `ca_chart_t` (`FIN_COA_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ca_sub_object_cd_t`
--

DROP TABLE IF EXISTS `ca_sub_object_cd_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ca_sub_object_cd_t` (
  `UNIV_FISCAL_YR` decimal(4,0) NOT NULL DEFAULT '0',
  `FIN_COA_CD` varchar(2) COLLATE utf8_bin NOT NULL DEFAULT '',
  `ACCOUNT_NBR` varchar(10) COLLATE utf8_bin NOT NULL DEFAULT '',
  `FIN_OBJECT_CD` varchar(5) COLLATE utf8_bin NOT NULL DEFAULT '',
  `FIN_SUB_OBJ_CD` varchar(3) COLLATE utf8_bin NOT NULL DEFAULT '',
  `OBJ_ID` varchar(36) COLLATE utf8_bin NOT NULL,
  `VER_NBR` decimal(8,0) NOT NULL DEFAULT '1',
  `FIN_SUB_OBJ_CD_NM` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `FIN_SUBOBJ_SHRT_NM` varchar(12) COLLATE utf8_bin DEFAULT NULL,
  `FIN_SUBOBJ_ACTV_CD` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`UNIV_FISCAL_YR`,`FIN_COA_CD`,`ACCOUNT_NBR`,`FIN_OBJECT_CD`,`FIN_SUB_OBJ_CD`),
  UNIQUE KEY `CA_SUB_OBJECT_CD_TC0` (`OBJ_ID`),
  KEY `CA_SUB_OBJECT_CD_TI2` (`UNIV_FISCAL_YR`),
  KEY `CA_SUB_OBJECT_CD_TR3` (`FIN_COA_CD`),
  KEY `CA_SUB_OBJECT_CD_TR1` (`UNIV_FISCAL_YR`,`FIN_COA_CD`,`FIN_OBJECT_CD`),
  CONSTRAINT `CA_SUB_OBJECT_CD_TR1` FOREIGN KEY (`UNIV_FISCAL_YR`, `FIN_COA_CD`, `FIN_OBJECT_CD`) REFERENCES `ca_object_code_t` (`UNIV_FISCAL_YR`, `FIN_COA_CD`, `FIN_OBJECT_CD`),
  CONSTRAINT `CA_SUB_OBJECT_CD_TR3` FOREIGN KEY (`FIN_COA_CD`) REFERENCES `ca_chart_t` (`FIN_COA_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hr_job_t`
--

DROP TABLE IF EXISTS `hr_job_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_job_t` (
  `HR_JOB_ID` bigint(20) NOT NULL,
  `PRINCIPAL_ID` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `JOB_NUMBER` bigint(20) DEFAULT NULL,
  `EFFDT` date NOT NULL DEFAULT '0000-00-00',
  `dept` varchar(21) COLLATE utf8_bin NOT NULL,
  `TK_SAL_GROUP` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `PAY_GRADE` varchar(3) COLLATE utf8_bin DEFAULT NULL,
  `TIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `OBJ_ID` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `VER_NBR` bigint(20) NOT NULL DEFAULT '1',
  `location` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `std_hours` decimal(5,2) DEFAULT NULL,
  `fte` bit(1) DEFAULT NULL,
  `hr_paytype` varchar(5) COLLATE utf8_bin DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`HR_JOB_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hr_job_s`
--

DROP TABLE IF EXISTS `hr_job_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_job_s` (
  `ID` bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=1101 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hr_paytype_t`
--

DROP TABLE IF EXISTS `hr_paytype_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_paytype_t` (
  `HR_PAYTYPE_ID` bigint(20) NOT NULL,
  `PAYTYPE` varchar(5) COLLATE utf8_bin NOT NULL,
  `DESCR` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `CALENDAR_GROUP` varchar(30) COLLATE utf8_bin NOT NULL,
  `REG_ERN_CODE` varchar(3) COLLATE utf8_bin NOT NULL,
  `EFFDT` date NOT NULL,
  `TIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `HOLIDAY_CALENDAR_GROUP` varchar(30) COLLATE utf8_bin NOT NULL,
  `OBJ_ID` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `VER_NBR` bigint(20) NOT NULL DEFAULT '1',
  `ACTIVE` bit(1) DEFAULT NULL,
  PRIMARY KEY (`HR_PAYTYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hr_paytype_s`
--

DROP TABLE IF EXISTS `hr_paytype_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_paytype_s` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2085 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hr_work_schedule_entry_t`
--

DROP TABLE IF EXISTS `hr_work_schedule_entry_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_work_schedule_entry_t` (
  `HR_WORK_SCHEDULE_ENTRY_ID` bigint(20) NOT NULL,
  `HR_WORK_SCHEDULE_ID` bigint(20) NOT NULL,
  `CALENDAR_GROUP` varchar(45) COLLATE utf8_bin NOT NULL,
  `DAY_OF_PERIOD` int(11) NOT NULL,
  `REG_HOURS` int(11) NOT NULL,
  `OBJ_ID` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `VER_NBR` bigint(20) NOT NULL DEFAULT '1',
  PRIMARY KEY (`HR_WORK_SCHEDULE_ENTRY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hr_work_schedule_entry_s`
--

DROP TABLE IF EXISTS `hr_work_schedule_entry_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_work_schedule_entry_s` (
  `ID` bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=1023 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hr_work_schedule_t`
--

DROP TABLE IF EXISTS `hr_work_schedule_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_work_schedule_t` (
  `HR_WORK_SCHEDULE_ID` bigint(20) NOT NULL,
  `WORK_SCHEDULE_DESC` varchar(30) COLLATE utf8_bin NOT NULL,
  `EFFDT` date NOT NULL DEFAULT '0000-00-00',
  `DEPT` varchar(21) COLLATE utf8_bin DEFAULT NULL,
  `WORK_AREA` bigint(10) DEFAULT NULL,
  `PRINCIPAL_ID` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `ACTIVE` bit(1) DEFAULT NULL,
  `OBJ_ID` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `VER_NBR` bigint(20) NOT NULL DEFAULT '1',
  `TIMESTAMP` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`HR_WORK_SCHEDULE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hr_work_schedule_s`
--

DROP TABLE IF EXISTS `hr_work_schedule_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_work_schedule_s` (
  `ID` bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=1024 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `la_accrual_categories_t`
--

DROP TABLE IF EXISTS `la_accrual_categories_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `la_accrual_categories_t` (
  `LA_ACCRUAL_CATEGORY_ID` bigint(20) NOT NULL,
  `ACCRUAL_CATEGORY` varchar(3) COLLATE utf8_bin NOT NULL,
  `DESCR` varchar(30) COLLATE utf8_bin NOT NULL,
  `EFFDT` date NOT NULL,
  `OBJ_ID` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `VER_NBR` bigint(20) NOT NULL DEFAULT '1',
  `ACTIVE` bit(1) DEFAULT NULL,
  `TIMESTAMP` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`LA_ACCRUAL_CATEGORY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `la_accrual_categories_s`
--

DROP TABLE IF EXISTS `la_accrual_categories_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `la_accrual_categories_s` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2085 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `la_accruals_t`
--

DROP TABLE IF EXISTS `la_accruals_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `la_accruals_t` (
  `LA_ACCRUALS_ID` bigint(20) NOT NULL,
  `PRINCIPAL_ID` varchar(21) COLLATE utf8_bin NOT NULL,
  `ACCRUAL_CATEGORY` varchar(3) COLLATE utf8_bin NOT NULL,
  `EFFDT` datetime NOT NULL,
  `HOURS_ACCRUED` int(11) NOT NULL,
  `HOURS_TAKEN` int(11) NOT NULL,
  `HOURS_ADJUST` int(11) NOT NULL,
  `OBJ_ID` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `VER_NBR` bigint(20) NOT NULL DEFAULT '1',
  PRIMARY KEY (`LA_ACCRUALS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `la_accruals_s`
--

DROP TABLE IF EXISTS `la_accruals_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `la_accruals_s` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2085 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_assign_acct_s`
--

DROP TABLE IF EXISTS `tk_assign_acct_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_assign_acct_s` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2085 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_assign_acct_t`
--

DROP TABLE IF EXISTS `tk_assign_acct_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_assign_acct_t` (
  `TK_ASSIGN_ACCT_ID` bigint(19) NOT NULL,
  `FIN_COA_CD` varchar(2) COLLATE utf8_bin DEFAULT NULL,
  `ACCOUNT_NBR` varchar(7) COLLATE utf8_bin DEFAULT NULL,
  `SUB_ACCT_NBR` varchar(5) COLLATE utf8_bin DEFAULT NULL,
  `FIN_OBJECT_CD` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `FIN_SUB_OBJ_CD` varchar(3) COLLATE utf8_bin DEFAULT NULL,
  `PROJECT_CD` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `ORG_REF_ID` varchar(8) COLLATE utf8_bin DEFAULT NULL,
  `PERCENT` decimal(5,2) DEFAULT NULL,
  `TK_ASSIGNMENT_ID` bigint(19) NOT NULL,
  `OBJ_ID` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `VER_NBR` decimal(8,0) NOT NULL DEFAULT '1',
  `EARN_CODE` varchar(3) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`TK_ASSIGN_ACCT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_assignment_t`
--

DROP TABLE IF EXISTS `tk_assignment_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_assignment_t` (
  `TK_ASSIGNMENT_ID` bigint(20) NOT NULL,
  `PRINCIPAL_ID` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `JOB_NUMBER` bigint(20) DEFAULT NULL,
  `EFFDT` date NOT NULL DEFAULT '0000-00-00',
  `WORK_AREA` bigint(10) DEFAULT NULL,
  `TASK` bigint(10) DEFAULT NULL,
  `OBJ_ID` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `VER_NBR` bigint(20) NOT NULL DEFAULT '1',
  `active` bit(1) DEFAULT NULL,
  `timestamp` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`TK_ASSIGNMENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_assignment_s`
--

DROP TABLE IF EXISTS `tk_assignment_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_assignment_s` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2108 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
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
) ENGINE=MyISAM AUTO_INCREMENT=2233 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_clock_location_rl_t`
--

DROP TABLE IF EXISTS `tk_clock_location_rl_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_clock_location_rl_t` (
  `TK_CLOCK_LOC_RULE_ID` bigint(20) NOT NULL,
  `WORK_AREA` bigint(10) DEFAULT NULL,
  `PRINCIPAL_ID` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `job_number` bigint(20) DEFAULT NULL,
  `EFFDT` date DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  `IP_ADDRESS` varchar(15) COLLATE utf8_bin DEFAULT NULL,
  `USER_PRINCIPAL_ID` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `TIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `OBJ_ID` varchar(36) COLLATE utf8_bin NOT NULL,
  `VER_NBR` bigint(20) NOT NULL DEFAULT '1',
  `dept` varchar(21) COLLATE utf8_bin DEFAULT NULL
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
) ENGINE=MyISAM AUTO_INCREMENT=2478 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_clock_log_t`
--

DROP TABLE IF EXISTS `tk_clock_log_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_clock_log_t` (
  `TK_CLOCK_LOG_ID` bigint(20) NOT NULL,
  `PRINCIPAL_ID` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `JOB_NUMBER` bigint(20) DEFAULT NULL,
  `WORK_AREA` bigint(10) DEFAULT NULL,
  `TASK` bigint(10) DEFAULT NULL,
  `TK_WORK_AREA_ID` bigint(20) DEFAULT NULL,
  `TK_TASK_ID` bigint(20) DEFAULT NULL,
  `CLOCK_TS` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `CLOCK_TS_TZ` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `CLOCK_ACTION` varchar(2) COLLATE utf8_bin DEFAULT NULL,
  `IP_ADDRESS` varchar(15) COLLATE utf8_bin DEFAULT NULL,
  `HR_JOB_ID` bigint(20) DEFAULT NULL,
  `USER_PRINCIPAL_ID` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `TIMESTAMP` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `OBJ_ID` varchar(36) COLLATE utf8_bin NOT NULL,
  `VER_NBR` bigint(20) NOT NULL DEFAULT '1',
  PRIMARY KEY (`TK_CLOCK_LOG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_daily_overtime_rl_t`
--

DROP TABLE IF EXISTS `tk_daily_overtime_rl_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_daily_overtime_rl_t` (
  `tk_daily_overtime_rl_id` bigint(20) NOT NULL,
  `LOCATION` varchar(2) COLLATE utf8_bin DEFAULT NULL,
  `PAYTYPE` varchar(5) COLLATE utf8_bin DEFAULT NULL,
  `EFFDT` date DEFAULT NULL,
  `USER_PRINCIPAL_ID` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `TIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `DEPT` varchar(21) COLLATE utf8_bin DEFAULT NULL,
  `WORK_AREA` bigint(10) DEFAULT NULL,
  `TASK` bigint(10) DEFAULT NULL,
  `MAX_GAP` decimal(3,0) DEFAULT NULL,
  `SHIFT_HOURS` decimal(2,0) DEFAULT NULL,
  `OVERTIME_PREFERENCE` varchar(3) COLLATE utf8_bin DEFAULT NULL,
  `ACTIVE` bit(1) DEFAULT NULL,
  PRIMARY KEY (`tk_daily_overtime_rl_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_daily_overtime_rl_s`
--

DROP TABLE IF EXISTS `tk_daily_overtime_rl_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_daily_overtime_rl_s` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2096 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_dept_earn_code_t`
--

DROP TABLE IF EXISTS `tk_dept_earn_code_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_dept_earn_code_t` (
  `tk_dept_earn_code_id` bigint(20) NOT NULL,
  `dept` varchar(21) COLLATE utf8_bin NOT NULL,
  `tk_sal_group` varchar(10) COLLATE utf8_bin NOT NULL,
  `earn_code` varchar(3) COLLATE utf8_bin NOT NULL,
  `employee` bit(1) DEFAULT b'0',
  `approver` bit(1) DEFAULT b'0',
  `org_admin` bit(1) DEFAULT b'0',
  `OBJ_ID` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `VER_NBR` bigint(20) DEFAULT '1',
  `active` bit(1) DEFAULT NULL,
  `effdt` date DEFAULT NULL,
  `timestamp` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`tk_dept_earn_code_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_dept_earn_code_s`
--

DROP TABLE IF EXISTS `tk_dept_earn_code_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_dept_earn_code_s` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2103 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_dept_lunch_rl_t`
--

DROP TABLE IF EXISTS `tk_dept_lunch_rl_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_dept_lunch_rl_t` (
  `TK_DEPT_LUNCH_RL_ID` bigint(20) NOT NULL,
  `DEPT` varchar(21) COLLATE utf8_bin DEFAULT NULL,
  `WORK_AREA` bigint(10) DEFAULT NULL,
  `principal_id` varchar(10) COLLATE utf8_bin NOT NULL DEFAULT '',
  `job_number` bigint(20) DEFAULT NULL,
  `EFFDT` date NOT NULL,
  `REQUIRED_CLOCK_FL` varchar(3) COLLATE utf8_bin DEFAULT NULL,
  `MAX_MINS` decimal(2,0) DEFAULT NULL,
  `user_principal_id` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `TIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `OBJ_ID` varchar(36) COLLATE utf8_bin NOT NULL,
  `VER_NBR` bigint(20) NOT NULL DEFAULT '1',
  `active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`TK_DEPT_LUNCH_RL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_dept_lunch_rl_s`
--

DROP TABLE IF EXISTS `tk_dept_lunch_rl_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_dept_lunch_rl_s` (
  `ID` bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=2090 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_dept_t`
--

DROP TABLE IF EXISTS `tk_dept_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_dept_t` (
  `tk_dept_id` bigint(20) NOT NULL,
  `dept` varchar(21) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `ORG` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `CHART` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `effdt` date DEFAULT NULL,
  `timestamp` timestamp NULL DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`tk_dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_dept_s`
--

DROP TABLE IF EXISTS `tk_dept_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_dept_s` (
  `ID` bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=2090 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_earn_code_t`
--

DROP TABLE IF EXISTS `tk_earn_code_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_earn_code_t` (
  `tk_earn_code_id` int(11) NOT NULL,
  `earn_code` varchar(3) COLLATE utf8_bin NOT NULL,
  `descr` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `effdt` date DEFAULT NULL,
  `record_time` varchar(1) DEFAULT 'n',
  `record_amount`  varchar(1) DEFAULT 'n',
  `record_hours`  varchar(1) DEFAULT 'n',
  `active` bit(1) DEFAULT NULL,
  `OBJ_ID` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `VER_NBR` bigint(20) DEFAULT '1',
  `timestamp` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`tk_earn_code_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_earn_code_s`
--

DROP TABLE IF EXISTS `tk_earn_code_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_earn_code_s` (
  `ID` bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_earn_group_def_t`
--

DROP TABLE IF EXISTS `tk_earn_group_def_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_earn_group_def_t` (
  `tk_earn_group_id` bigint(20) DEFAULT NULL,
  `earn_code` varchar(3) COLLATE utf8_bin DEFAULT NULL,
  `OBJ_ID` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `VER_NBR` bigint(20) DEFAULT '1',
  `tk_earn_group_def_id` bigint(20) NOT NULL,
  PRIMARY KEY (`tk_earn_group_def_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_earn_group_def_s`
--

DROP TABLE IF EXISTS `tk_earn_group_def_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_earn_group_def_s` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2085 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_earn_group_t`
--

DROP TABLE IF EXISTS `tk_earn_group_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_earn_group_t` (
  `tk_earn_group_id` bigint(20) NOT NULL,
  `earn_group` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `descr` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `effdt` date DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  `OBJ_ID` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `VER_NBR` bigint(20) DEFAULT '1',
  `timestamp` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`tk_earn_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_earn_group_s`
--

DROP TABLE IF EXISTS `tk_earn_group_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_earn_group_s` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2085 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_grace_period_rl_t`
--

DROP TABLE IF EXISTS `tk_grace_period_rl_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_grace_period_rl_t` (
  `TK_GRACE_PERIOD_RULE_ID` bigint(20) DEFAULT NULL,
  `EFFDT` date DEFAULT NULL,
  `GRACE_MINS` decimal(2,0) DEFAULT NULL,
  `HOUR_FACTOR` decimal(2,2) DEFAULT NULL,
  `user_principal_id` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `TIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `OBJ_ID` varchar(36) COLLATE utf8_bin NOT NULL,
  `VER_NBR` bigint(20) NOT NULL DEFAULT '1',
  `active` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_grace_period_rl_s`
--

DROP TABLE IF EXISTS `tk_grace_period_rl_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_grace_period_rl_s` (
  `ID` bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=2078 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_holiday_calendar_dates_t`
--

DROP TABLE IF EXISTS `tk_holiday_calendar_dates_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_holiday_calendar_dates_t` (
  `HOLIDAY_CALENDAR_DATES_ID` bigint(20) NOT NULL,
  `HOLIDAY_DATE` date NOT NULL,
  `HOLIDAY_DESC` varchar(30) COLLATE utf8_bin NOT NULL,
  `HOLIDAY_CALENDAR_ID` int(11) NOT NULL,
  `OBJ_ID` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `VER_NBR` bigint(20) NOT NULL DEFAULT '1',
  PRIMARY KEY (`HOLIDAY_CALENDAR_DATES_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_holiday_calendar_dates_s`
--

DROP TABLE IF EXISTS `tk_holiday_calendar_dates_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_holiday_calendar_dates_s` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2087 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_holiday_calendar_t`
--

DROP TABLE IF EXISTS `tk_holiday_calendar_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_holiday_calendar_t` (
  `HOLIDAY_CALENDAR_ID` bigint(20) NOT NULL,
  `HOLIDAY_CALENDAR_GROUP` varchar(3) COLLATE utf8_bin NOT NULL,
  `DESCR` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `OBJ_ID` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `VER_NBR` bigint(20) NOT NULL DEFAULT '1',
  PRIMARY KEY (`HOLIDAY_CALENDAR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_holiday_calendar_s`
--

DROP TABLE IF EXISTS `tk_holiday_calendar_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_holiday_calendar_s` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2085 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_py_calendar_t`
--

DROP TABLE IF EXISTS `tk_py_calendar_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_py_calendar_t` (
  `tk_py_calendar_id` bigint(20) NOT NULL,
  `calendar_group` varchar(45) COLLATE utf8_bin NOT NULL,
  `chart` varchar(45) COLLATE utf8_bin NOT NULL,
  `begin_date` date NOT NULL,
  `begin_time` time NOT NULL DEFAULT '00:00:00',
  `end_date` date NOT NULL,
  `end_time` time NOT NULL DEFAULT '23:59:59',
  PRIMARY KEY (`tk_py_calendar_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_py_calendar_s`
--

DROP TABLE IF EXISTS `tk_py_calendar_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_py_calendar_s` (
  `ID` bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=2083 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_py_calendar_dates_t`
--

DROP TABLE IF EXISTS `tk_py_calendar_dates_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_py_calendar_dates_t` (
  `tk_py_calendar_dates_id` bigint(20) NOT NULL,
  `tk_py_calendar_id` bigint(20) NOT NULL,
  `begin_period_date` timestamp NOT NULL,
  `end_period_date` timestamp NOT NULL,
  `initiate_date` date DEFAULT NULL,
  `initiate_time` time DEFAULT NULL,
  `end_pay_period_date` date DEFAULT NULL,
  `end_pay_period_time` time DEFAULT NULL,
  `employee_approval_date` date DEFAULT NULL,
  `employee_approval_time` time DEFAULT NULL,
  `supervisor_approval_date` date DEFAULT NULL,
  `supervisor_approval_time` time DEFAULT NULL,
  PRIMARY KEY (`tk_py_calendar_dates_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_py_calendar_dates_s`
--

DROP TABLE IF EXISTS `tk_py_calendar_dates_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_py_calendar_dates_s` (
  `ID` bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=2085 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_sal_group_t`
--

DROP TABLE IF EXISTS `tk_sal_group_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_sal_group_t` (
  `TK_SAL_GROUP_ID` bigint(20) NOT NULL,
  `TK_SAL_GROUP` varchar(10) COLLATE utf8_bin NOT NULL,
  `EFFDT` date NOT NULL,
  `TIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ACTIVE` bit(1) DEFAULT NULL,
  `OBJ_ID` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `VER_NBR` bigint(20) NOT NULL DEFAULT '1',
  PRIMARY KEY (`TK_SAL_GROUP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_sal_group_s`
--

DROP TABLE IF EXISTS `tk_sal_group_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_sal_group_s` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2085 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_shift_differential_rl_t`
--

DROP TABLE IF EXISTS `tk_shift_differential_rl_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_shift_differential_rl_t` (
  `TK_SHIFT_DIFF_RL_ID` bigint(20) NOT NULL,
  `LOCATION` varchar(2) COLLATE utf8_bin DEFAULT NULL,
  `TK_SAL_GROUP` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `PAY_GRADE` varchar(3) COLLATE utf8_bin DEFAULT NULL,
  `EFFDT` date DEFAULT NULL,
  `EARN_CODE` varchar(3) COLLATE utf8_bin DEFAULT NULL,
  `BEGIN_TS` varchar(8) COLLATE utf8_bin DEFAULT NULL,
  `END_TS` varchar(8) COLLATE utf8_bin DEFAULT NULL,
  `MIN_HRS` decimal(2,0) DEFAULT NULL,
  `DAY0` bit(1) DEFAULT NULL,
  `DAY1` bit(1) DEFAULT NULL,
  `DAY2` bit(1) DEFAULT NULL,
  `DAY3` bit(1) DEFAULT NULL,
  `DAY4` bit(1) DEFAULT NULL,
  `DAY5` bit(1) DEFAULT NULL,
  `DAY6` bit(1) DEFAULT NULL,
  `CALENDAR_GROUP` varchar(30) COLLATE utf8_bin NOT NULL,
  `MAX_GAP` decimal(2,0) DEFAULT NULL,
  `USER_PRINCIPAL_ID` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `TIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ACTIVE` bit(1) DEFAULT NULL,
  PRIMARY KEY (`TK_SHIFT_DIFF_RL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_shift_differential_rl_s`
--

DROP TABLE IF EXISTS `tk_shift_differential_rl_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_shift_differential_rl_s` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2106 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_system_lunch_rl_t`
--

DROP TABLE IF EXISTS `tk_system_lunch_rl_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_system_lunch_rl_t` (
  `TK_SYSTEM_LUNCH_RL_ID` bigint(20) NOT NULL,
  `EFFDT` date NOT NULL,
  `MIN_MINS` decimal(3,0) DEFAULT NULL,
  `BLOCK_HOURS` decimal(3,0) DEFAULT NULL,
  `TIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ACTIVE` bit(1) DEFAULT NULL,
  `OBJ_ID` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `VER_NBR` bigint(20) NOT NULL DEFAULT '1',
  `USER_PRINCIPAL_ID` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`TK_SYSTEM_LUNCH_RL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_system_lunch_rl_s`
--

DROP TABLE IF EXISTS `tk_system_lunch_rl_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_system_lunch_rl_s` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2085 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_task_t`
--

DROP TABLE IF EXISTS `tk_task_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_task_t` (
  `tk_task_id` bigint(20) NOT NULL,
  `task` bigint(10) DEFAULT NULL,
  `work_area` bigint(10) DEFAULT NULL,
  `tk_work_area_id` bigint(20) NOT NULL,
  `descr` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `admin_descr` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `obj_id` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `ver_nbr` bigint(20) DEFAULT '1',
  `USER_PRINCIPAL_ID` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`tk_task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_task_s`
--

DROP TABLE IF EXISTS `tk_task_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_task_s` (
  `ID` bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=1005 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_time_block_hist_t`
--

DROP TABLE IF EXISTS `tk_time_block_hist_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_time_block_hist_t` (
  `TK_TIME_BLOCK_HIST_ID` bigint(20) NOT NULL,
  `TK_TIME_BLOCK_ID` bigint(20) DEFAULT NULL,
  `DOCUMENT_ID` varchar(14) COLLATE utf8_bin DEFAULT NULL,
  `JOB_NUMBER` bigint(20) DEFAULT NULL,
  `TASK` bigint(10) DEFAULT NULL,
  `WORK_AREA` bigint(10) DEFAULT NULL,
  `TK_WORK_AREA_ID` bigint(20) DEFAULT NULL,
  `TK_TASK_ID` bigint(20) DEFAULT NULL,
  `EARN_CODE` varchar(3) COLLATE utf8_bin DEFAULT NULL,
  `BEGIN_TS` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `BEGIN_TS_TZ` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `END_TS` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `END_TS_TZ` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `CLOCK_LOG_CREATED` bit(1) DEFAULT b'0',
  `HOURS` decimal(5,2) DEFAULT NULL,
  `USER_PRINCIPAL_ID` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `TIMESTAMP` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `ACTION_HISTORY` varchar(15) COLLATE utf8_bin DEFAULT NULL,
  `MODIFIED_BY_PRINCIPAL_ID` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `TIMESTAMP_MODIFIED` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `OBJ_ID` varchar(36) COLLATE utf8_bin NOT NULL,
  `VER_NBR` bigint(20) NOT NULL DEFAULT '1',
  `amount` decimal(6,2) DEFAULT '0.00',
  `HR_JOB_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`TK_TIME_BLOCK_HIST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_time_block_hist_s`
--

DROP TABLE IF EXISTS `tk_time_block_hist_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_time_block_hist_s` (
  `ID` bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=1096 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_time_block_t`
--

DROP TABLE IF EXISTS `tk_time_block_s`;
CREATE TABLE `tk_time_block_s` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=5109 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `tk_time_block_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_time_block_t` (
  `TK_TIME_BLOCK_ID` bigint(20) NOT NULL,
  `DOCUMENT_ID` varchar(14) COLLATE utf8_bin DEFAULT NULL,
  `JOB_NUMBER` bigint(20) DEFAULT NULL,
  `WORK_AREA` bigint(10) DEFAULT NULL,
  `TASK` bigint(20) DEFAULT NULL,
  `TK_WORK_AREA_ID` bigint(20) DEFAULT NULL,
  `HR_JOB_ID` bigint(20) DEFAULT NULL,
  `TK_TASK_ID` bigint(20) DEFAULT NULL,
  `EARN_CODE` varchar(3) COLLATE utf8_bin DEFAULT NULL,
  `BEGIN_TS` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `BEGIN_TS_TZ` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `END_TS` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `END_TS_TZ` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `CLOCK_LOG_CREATED` bit(1) DEFAULT b'0',
  `HOURS` decimal(5,2) DEFAULT NULL,
  `amount` decimal(6,2) DEFAULT '0.00',
  `USER_PRINCIPAL_ID` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `TIMESTAMP` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `OBJ_ID` varchar(36) COLLATE utf8_bin NOT NULL,
  `VER_NBR` bigint(20) NOT NULL DEFAULT '1',
  PRIMARY KEY (`TK_TIME_BLOCK_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS `tk_time_collection_rl_s`;
CREATE TABLE `tk_time_collection_rl_s` (
  `ID` bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=1018 DEFAULT CHARSET=latin1;

--
-- Table structure for table `tk_time_collection_rl_t`
--

DROP TABLE IF EXISTS `tk_time_collection_rl_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_time_collection_rl_t` (
  `TK_TIME_COLL_RULE_ID` bigint(20) NOT NULL,
  `DEPT` varchar(21) COLLATE utf8_bin DEFAULT NULL,
  `WORK_AREA` bigint(10) DEFAULT NULL,
  `EFFDT` date DEFAULT NULL,
  `CLOCK_USERS_FL` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `HRS_DISTRIBUTION_FL` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `USER_PRINCIPAL_ID` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `TIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `OBJ_ID` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `VER_NBR` bigint(20) DEFAULT '1',
  `ACTIVE` bit(1) DEFAULT NULL,
  PRIMARY KEY (`TK_TIME_COLL_RULE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_weekly_overtime_rl_t`
--

DROP TABLE IF EXISTS `tk_weekly_overtime_rl_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_weekly_overtime_rl_t` (
  `TK_WEEKLY_OVERTIME_RL_ID` bigint(20) NOT NULL,
  `MAX_HRS_ERN_GROUP` varchar(3) COLLATE utf8_bin DEFAULT NULL,
  `CONVERT_FROM_ERN_GROUP` varchar(3) COLLATE utf8_bin DEFAULT NULL,
  `CONVERT_TO_ERNCD` varchar(3) COLLATE utf8_bin DEFAULT NULL,
  `EFFDT` date DEFAULT NULL,
  `STEP` decimal(2,0) DEFAULT NULL,
  `MAX_HRS` decimal(3,0) DEFAULT NULL,
  `USER_PRINCIPAL_ID` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `TIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `OBJ_ID` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `VER_NBR` bigint(20) DEFAULT '1',
  `ACTIVE` bit(1) DEFAULT NULL,
  PRIMARY KEY (`TK_WEEKLY_OVERTIME_RL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_weekly_overtime_rl_s`
--

DROP TABLE IF EXISTS `tk_weekly_overtime_rl_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_weekly_overtime_rl_s` (
  `ID` bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=1021 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_work_area_document_t`
--

DROP TABLE IF EXISTS `tk_work_area_document_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_work_area_document_t` (
  `doc_hdr_id` varchar(50) COLLATE utf8_bin NOT NULL,
  `ver_nbr` bigint(20) DEFAULT NULL,
  `obj_id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`doc_hdr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_work_area_s`
--

DROP TABLE IF EXISTS `tk_work_area_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_work_area_s` (
  `ID` bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=1001 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_work_area_t`
--

DROP TABLE IF EXISTS `tk_work_area_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_work_area_t` (
  `TK_WORK_AREA_ID` bigint(20) NOT NULL,
  `WORK_AREA` bigint(10) NOT NULL,
  `DEPT` varchar(21) COLLATE utf8_bin DEFAULT NULL,
  `EFFDT` date NOT NULL,
  `ACTIVE` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `DESCR` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `DEFAULT_OVERTIME_PREFERENCE` varchar(3) COLLATE utf8_bin DEFAULT NULL,
  `ADMIN_DESCR` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `USER_PRINCIPAL_ID` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `TIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `OBJ_ID` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `VER_NBR` bigint(20) DEFAULT '1',
  PRIMARY KEY (`TK_WORK_AREA_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_hour_detail_s`
--

DROP TABLE IF EXISTS `tk_hour_detail_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_hour_detail_s` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=1049 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_hour_detail_t`
--

DROP TABLE IF EXISTS `tk_hour_detail_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_hour_detail_t` (
  `TK_HOUR_DETAIL_ID` bigint(20) NOT NULL,
  `TK_TIME_BLOCK_ID` bigint(20) NOT NULL,
  `EARN_CODE` varchar(3) COLLATE utf8_bin DEFAULT NULL,
  `HOURS` decimal(5,2) DEFAULT NULL,
  `amount` decimal(6,2) DEFAULT '0.00',
  `OBJ_ID` varchar(36) COLLATE utf8_bin NOT NULL,
  `VER_NBR` bigint(20) NOT NULL DEFAULT '1',
  PRIMARY KEY (`TK_HOUR_DETAIL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_roles_s`
--

DROP TABLE IF EXISTS `tk_roles_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_roles_s` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2090 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_roles_t`
--

DROP TABLE IF EXISTS `tk_roles_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_roles_t` (
  `tk_roles_id` bigint(19) NOT NULL,
  `principal_id` varchar(20) COLLATE utf8_bin NOT NULL,
  `role_name` varchar(20) COLLATE utf8_bin NOT NULL,
  `user_principal_id` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `work_area` bigint(20) DEFAULT NULL,
  `dept` varchar(21) COLLATE utf8_bin DEFAULT NULL,
  `effdt` date NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`tk_roles_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_work_area_ovt_pref_t`
--

DROP TABLE IF EXISTS `tk_work_area_ovt_pref_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_work_area_ovt_pref_t` (
  `tk_work_area_ovt_pref_id` bigint(20) NOT NULL,
  `tk_work_area_id` bigint(20) DEFAULT NULL,
  `paytype` varchar(5) COLLATE utf8_bin DEFAULT NULL,
  `overtime_preference` varchar(3) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`tk_work_area_ovt_pref_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_work_area_ovt_pref_s`
--

DROP TABLE IF EXISTS `tk_work_area_ovt_pref_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_work_area_ovt_pref_s` (
  `ID` bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=1018 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_hour_detail_hist_t`
--

DROP TABLE IF EXISTS `tk_hour_detail_hist_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_hour_detail_hist_t` (
  `TK_HOUR_DETAIL_HIST_ID` bigint(20) NOT NULL,
  `TK_HOUR_DETAIL_ID` bigint(20) NOT NULL,
  `EARN_CODE` varchar(3) COLLATE utf8_bin DEFAULT NULL,
  `HOURS` decimal(5,2) DEFAULT NULL,
  `amount` decimal(6,2) DEFAULT '0.00',
  `OBJ_ID` varchar(36) COLLATE utf8_bin NOT NULL,
  `VER_NBR` bigint(20) NOT NULL DEFAULT '1',
  `ACTION_HISTORY` varchar(15) COLLATE utf8_bin DEFAULT NULL,
  `MODIFIED_BY_PRINCIPAL_ID` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `TIMESTAMP_MODIFIED` timestamp NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_hour_detail_hist_s`
--

DROP TABLE IF EXISTS `tk_hour_detail_hist_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_hour_detail_hist_s` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tk_document_header_t`
--

DROP TABLE IF EXISTS `tk_document_header_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_document_header_t` (
  `DOCUMENT_ID` bigint(19) NOT NULL,
  `PRINCIPAL_ID` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `PAY_END_DT` datetime DEFAULT NULL,
  `DOCUMENT_STATUS` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `PAY_BEGIN_DT` datetime DEFAULT NULL,
  PRIMARY KEY (`DOCUMENT_ID`)
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

-- Dump completed on 2010-10-15 11:10:06


INSERT INTO changelog (change_number, complete_dt, applied_by, description)
 VALUES (1, CURRENT_TIMESTAMP, USER(), '001-0.1.0-tk_la_ca_hr_schema.sql');

COMMIT;

-- END CHANGE SCRIPT #1: 001-0.1.0-tk_la_ca_hr_schema.sql


-- START CHANGE SCRIPT #2: 002-0.1.0-tk_la_ca_hr_bootstrap.sql

#
# HR Job
DELETE FROM `hr_job_s`;
INSERT INTO `hr_job_s` (`ID`)	VALUES	('1100');
DELETE FROM `hr_job_t`;
INSERT INTO `hr_job_t` (`HR_JOB_ID`,`PRINCIPAL_ID`,`JOB_NUMBER`,`EFFDT`,`active`,`dept`,`TK_SAL_GROUP`,`PAY_GRADE`,`TIMESTAMP`,`OBJ_ID`,`VER_NBR`,`location`,`std_hours`,`fte`,`hr_paytype`) VALUES
	('1012', 'admin', '1', '2010-08-01', 1, 'TEST-DEPT', 'A10', NULL, '2010-08-1 16:00:13', 'A9225D4A-4871-4277-5638-4C7880A57621', '1', NULL, '40.00', NULL, 'BW'),
	('1013', 'admin', '2', '2010-08-01', 1, 'TEST-DEPT', 'A12', NULL, '2010-08-10 16:00:13', 'A9225D4A-4871-4277-5638-4C7880A57621', '1', NULL, '40.00', NULL, 'BW'),
	('1014', 'admin', '2', '2010-08-11', 1, 'TEST-DEPT', 'A12', NULL, '2010-08-11 16:00:14', 'A9225D4A-4871-4277-5638-4C7880A57621', '1', NULL, '40.00', NULL, 'BW'),
	('1015', 'admin', '2', '2010-08-11', 1, 'TEST-DEPT', 'A12', NULL, '2010-08-11 16:00:15', 'A9225D4A-4871-4277-5638-4C7880A57621', '1', NULL, '40.00', NULL, 'BW'),
	('1016', 'admin', '2', '2010-08-12', 1, 'TEST-DEPT', 'A12', NULL, '2010-08-12 16:00:13', 'A9225D4A-4871-4277-5638-4C7880A57621', '1', NULL, '40.00', NULL, 'BW'),
	('1017', 'eric', '1', '2010-08-12', 1, 'TEST-DEPT', '10', NULL, '2010-08-12 16:00:13', 'A9225D4A-4871-4277-5638-4C7880A57621', '1', NULL, '40.00', NULL, 'BW'),
	('1018', 'eric', '1', '2010-08-12', 1, 'TEST-DEPT', '10', NULL, '2010-08-12 16:00:13', 'A9225D4A-4871-4277-5638-4C7880A57621', '1', NULL, '40.00', NULL, 'BW'),
	('1019', 'eric', '2', '2010-08-10', 1, 'TEST-DEPT', '10', NULL, '2010-08-10 16:22:13', 'A9225D4A-4871-4277-5638-4C7880A57621', '1', NULL, '40.00', NULL, 'BW'),
	('1020', 'eric', '2', '2010-08-13', 1, 'TEST-DEPT', '11', NULL, '2010-08-13 16:22:22', 'A9225D4A-4871-4277-5638-4C7880A57621', '1', NULL, '40.00', NULL, 'BW'),
	('1021', 'admin', '3', '2010-08-15', 1, 'NODEP', 'NOP', NULL, '2010-08-12 16:00:13', 'A9225D4A-4871-4277-5638-4C7880A57621', '1', NULL, '40.00', NULL, 'BW'),
	('1022', 'admin', '4', '2010-08-15', 1, 'NODEP', 'A10', NULL, '2010-08-12 16:00:13', 'A9225D4A-4871-4277-5638-4C7880A57621', '1', NULL, '40.00', NULL, 'BW'),
	('1023', 'admin', '5', '2010-08-15', 1, 'LORA-DEPT', 'A10', NULL, '2010-08-12 16:00:13', 'A9225D4A-4871-4277-5638-4C7880A57621', '1', NULL, '40.00', NULL, 'BW'),
	('1024', 'admin', '6', '2010-01-01', 1, 'SHFT-DEPT', 'A10', NULL, '2010-08-12 16:00:13', 'A9225D4A-4871-4277-5638-4C7880A57621', '1', NULL, '40.00', NULL, 'BW');

#
# Departments
DELETE FROM `tk_dept_s`;
INSERT INTO `tk_dept_s` (`ID`) VALUES ('1000');
DELETE FROM `tk_dept_t`;
INSERT INTO `tk_dept_t` (`tk_dept_id`,`dept`,`DESCRIPTION`,`ORG`,`CHART`,`EFFDT`,`TIMESTAMP`,`ACTIVE`) VALUES
    (100 , 'TEST-DEPT'  , 'test department'  , 'TEST' , 'DEPT' , '2010-01-31' , '2010-07-27 10:25:13' , 1)  ,
    (101 , 'TEST-DEPT1' , 'test department1' , 'TEST' , 'DEPT' , '2010-01-31' , '2010-07-27 10:25:13' , 1)  ,
    (102 , 'TEST-DEPT2' , 'test department2' , 'TEST' , 'DEPT' , '2010-01-31' , '2010-07-27 10:25:13' , 1)  ,
    (103 , 'TEST-DEPT3' , 'test department3' , 'TEST' , 'DEPT' , '2010-01-31' , '2010-07-27 10:25:13' , 1)  ,
    (104 , 'TEST-DEPT4' , 'test department4' , 'TEST' , 'DEPT' , '2010-01-31' , '2010-07-27 10:25:13' , 1)  ,
    (105 , 'TEST-DEPT5' , 'test department5' , 'TEST' , 'DEPT' , '2010-01-31' , '2010-07-27 10:25:13' , 1)  ,
    (106 , 'TEST-DEPT6' , 'test department6' , 'TEST' , 'DEPT' , '2010-01-31' , '2010-07-27 10:25:13' , 1)  ,
    (107 , 'NODEP'      , 'test department7' , 'NODEP', 'DEPT' , '2010-01-31' , '2010-07-27 10:25:13' , 1),
    (108 , 'TEST-DEPT7' , 'test department7' , 'TEST' , 'DEPT' , '2010-01-31' , '2010-07-27 10:25:13' , 1),
    (109 , 'LORA-DEPT'  , 'lora\'s department' , 'LORA' , 'DEPT' , '2010-01-31' , '2010-07-27 10:25:13' , 1),
    (110 , 'SHFT-DEPT'  , 'shift department' , 'SHFT' , 'DEPT' , '2010-01-01' , '2010-07-27 10:25:13' , 1);

#
# Work Areas
DELETE FROM `tk_work_area_s`;
INSERT INTO `tk_work_area_s` (`ID`) VALUES ('1000');
DELETE FROM `tk_work_area_t`;
INSERT INTO `tk_work_area_t` (`TK_WORK_AREA_ID`, `WORK_AREA`, `EFFDT`,`ACTIVE`,`DESCR`,`DEPT`,`DEFAULT_OVERTIME_PREFERENCE`,`ADMIN_DESCR`,`USER_PRINCIPAL_ID`,`TIMESTAMP`,`OBJ_ID`,`VER_NBR`) VALUES
    (100,'1234', '2010-01-05', 1, 'work area description', 'TEST-DEPT', 'OT1', 'work area admin description', 'admin', '2010-07-27 10:25:13', '7EE387AB-26B0-B6A6-9C4C-5B5F687F0E97', '20'),
    (101, '2345', '2010-01-05', 1, 'work area description2', 'TEST-DEPT2', 'OT1', 'work area admin description2', 'admin', '2010-07-27 10:25:13', '7EE387AB-26B0-B6A6-9C4C-5B5F687F0E97', '20'),
    (102, '3456', '2010-01-05', 1, 'work area description3', 'TEST-DEPT3', 'OT1', 'work area admin description2', 'admin', '2010-07-27 10:25:13', '7EE387AB-26B0-B6A6-9C4C-5B5F687F0E97', '20'),
    (103, '4567', '2010-01-05', 1, 'work area description4', 'TEST-DEPT4', 'OT1', 'work area admin description2', 'admin', '2010-07-27 10:25:13', '7EE387AB-26B0-B6A6-9C4C-5B5F687F0E97', '20'),
    (104, '1000', '2010-01-05', 1, 'lora\'s work area', 'LORA-DEPT', 'OT1', 'work area admin description2', 'admin', '2010-07-27 10:25:13', '7EE387AB-26B0-B6A6-9C4C-5B5F687F0E97', '20'),
    (105, '1100', '2010-01-01', 1, 'shift-workarea', 'SHFT-DEPT', 'OT1', 'work area admin description2', 'admin', '2010-07-27 10:25:13', '7EE387AB-26B0-B6A6-9C4C-5B5F687F0E97', '20');

#
# Task
DELETE FROM `tk_task_s`;
INSERT INTO `tk_task_s` (`ID`) VALUES ('1000');
DELETE FROM `tk_task_t`;
INSERT INTO `tk_task_t`(`tk_task_id`,`task`,`work_area`,`tk_work_area_id`,`descr`,`admin_descr`,`obj_id`, `ver_nbr`,`USER_PRINCIPAL_ID`)  VALUES
    (100, 1, '1234', 100,'description 1', 'admin description 1', '8421CD29-E1F4-4B9A-AE33-F3F4752505CE', '1', 'admin'),
    (101, 2, '1234', 100,'description 2', 'admin description 2', '8421CD29-E1F4-4B9A-AE33-F3F4752505CE', '1', 'admin'),
    (102, 3, '1234', 100,'description 3', 'admin description 3', '8421CD29-E1F4-4B9A-AE33-F3F4752505CE', '1', 'admin'),
    (103, 4, '1000', 104,'task 4', 'admin description 4', '8421CD29-E1F4-4B9A-AE33-F3F4752505CE', '1', 'admin'),
    (104, 5, '1100', 105,'task 5', 'admin description 4', '8421CD29-E1F4-4B9A-AE33-F3F4752505CE', '1', 'admin');

#
# Assignments
DELETE FROM `tk_assignment_s`;
INSERT INTO `tk_assignment_s` (`ID`) VALUES ('1000');
DELETE FROM `tk_assignment_t`;
INSERT INTO `tk_assignment_t` (`TK_ASSIGNMENT_ID`,`PRINCIPAL_ID`,`JOB_NUMBER`,`EFFDT`,`WORK_AREA`,`TASK`,`OBJ_ID`,`TIMESTAMP`,`VER_NBR`,`active`) VALUES
    (10 , 'admin' , 1 , '2010-08-01' , 1234 , 1 , '8421CD29-E1F4-4B9A-AE33-F3F4752505CE' , '2010-07-27 10:25:13' , '1' , 1)  ,
    (11 , 'admin' , 2 , '2010-08-01' , 1234 , 2 , '8421CD29-E1F4-4B9A-AE33-F3F4752505CE' , '2010-07-27 10:25:13' , '1' , 1)  ,
    (12 , 'eric'  , 1 , '2010-08-01' , 2345 , 1 , '8421CD29-E1F4-4B9A-AE33-F3F4752505CE' , '2010-07-27 10:25:13' , '1' , 1)  ,
    (13 , 'eric'  , 2 , '2010-08-01' , 3456 , 1 , '8421CD29-E1F4-4B9A-AE33-F3F4752505CE' , '2010-07-27 10:25:13' , '1' , 1)  ,
    (14 , 'admin' , 3 , '2010-08-15' , 1234 , 1 , '8421CD29-E1F4-4B9A-AE33-F3F4752505CE' , '2010-08-27 10:25:13' , '1' , 1)  ,
    (15 , 'admin' , 1 , '2010-08-04' , 1234 , 1 , '8421CD29-E1F4-4B9A-AE33-F3F4752505CE' , '2010-08-27 10:25:13' , '1' , 1)  ,
    (16 , 'admin' , 4 , '2010-08-15' , 1234 , 1 , '8421CD29-E1F4-4B9A-AE33-F3F4752505CE' , '2010-08-27 10:25:13' , '1' , 1)  ,
    (17 , 'admin' , 5 , '2010-08-15' , 1000 , 4 , '8421CD29-E1F4-4B9A-AE33-F3F4752505CE' , '2010-08-27 10:25:13' , '1' , 1)  ,
    (18 , 'admin' , 6 , '2010-01-01' , 1100 , 5 , '8421CD29-E1F4-4B9A-AE33-F3F4752505CE' , '2010-08-27 10:25:13' , '1' , 1)  ;

#
# dept earn code
DELETE FROM `tk_dept_earn_code_s`;
INSERT INTO `tk_dept_earn_code_s` VALUES('1000');
DELETE FROM `tk_dept_earn_code_t`;
INSERT INTO `TK_DEPT_EARN_CODE_T`
    (`TK_DEPT_EARN_CODE_ID` , `DEPT`       , `TK_SAL_GROUP` , `EARN_CODE` , `EMPLOYEE` , `APPROVER` , `ORG_ADMIN` , `EFFDT`      , `TIMESTAMP`           , `ACTIVE`) VALUES
    ('10'                   , 'TEST-DEPT'  , 'A10'          , 'RGH'       , 1          , 1          , 1           , '2010-08-01' , '2010-01-01 08:08:08' , 1)               ,
    ('11'                   , 'TEST-DEPT'  , 'A10'          , 'SCK'       , 1          , 1          , 1           , '2010-08-01' , '2010-01-01 08:08:08' , 1)               ,
    ('12'                   , 'TEST-DEPT'  , 'A10'          , 'VAC'       , 1          , 1          , 1           , '2010-08-01' , '2010-01-01 08:08:08' , 1)               ,
    ('13'                   , 'TEST-DEPT'  , 'A10'          , 'WEP'       , 1          , 1          , 1           , '2010-08-01' , '2010-01-01 08:08:08' , 1)               ,
    ('14'                   , 'TEST-DEPT2' , 'A10'          , 'HAZ'       , 1          , 1          , 1           , '2010-08-01' , '2010-01-01 08:08:08' , 1)               ,
    ('15'                   , 'TEST-DEPT2' , 'A10'          , 'HIP'       , 1          , 1          , 1           , '2010-08-01' , '2010-01-01 08:08:08' , 1)               ,
    ('16'                   , 'TEST-DEPT2' , 'A10'          , 'OC1'       , 1          , 1          , 1           , '2010-08-01' , '2010-01-01 08:08:08' , 1)               ,
    ('17'                   , 'TEST-DEPT2' , 'A10'          , 'OC2'       , 1          , 1          , 1           , '2010-08-01' , '2010-01-01 08:08:08' , 1)               ,
    ('18'                   , '*'          , 'A10'          , 'XYZ'       , 1          , 1          , 1           , '2010-08-01' , '2010-01-01 08:08:08' , 1)               ,
    ('19'                   , 'TEST-DEPT'  , '*'            , 'XYY'       , 1          , 1          , 1           , '2010-08-01' , '2010-01-01 08:08:08' , 1)               ,
    ('20'                   , '*'          , '*'            , 'XZZ'       , 1          , 1          , 1           , '2010-08-01' , '2010-01-01 08:08:08' , 1)               ,
    ('21'                   , 'LORA-DEPT'  , 'A10'          , 'RGH'       , 1          , 1          , 1           , '2010-08-01' , '2010-01-01 08:08:08' , 1)               ,
    ('22'                   , 'LORA-DEPT'  , 'A10'          , 'SCK'       , 1          , 1          , 1           , '2010-08-01' , '2010-01-01 08:08:08' , 1)               ,
    ('23'                   , 'LORA-DEPT'  , 'A10'          , 'VAC'       , 1          , 1          , 1           , '2010-08-01' , '2010-01-01 08:08:08' , 1)  ;

#
# earn code
DELETE FROM `tk_earn_code_s`;
INSERT INTO `tk_earn_code_s` VALUES('1000');
DELETE FROM `tk_earn_code_T`;
INSERT INTO `TK_EARN_CODE_T` (`TK_EARN_CODE_ID`, `EARN_CODE`, `DESCR`, `RECORD_TIME`,`RECORD_HOURS`,`RECORD_AMOUNT`,`EFFDT`, `TIMESTAMP`, `ACTIVE`) VALUES
	('9'  , 'RGN' , 'REGULAR'           , 'y','n','n', '2010-01-01' , '2010-01-01 08:08:08' , 1) ,
	('10' , 'RGH' , 'REGULAR HOURLY'    , 'y','n','n', '2010-01-01' , '2010-01-01 08:08:08' , 1) ,
	('11' , 'SCK' , 'SICK'              , 'y','n','n', '2010-01-01' , '2010-01-01 08:08:08' , 1) ,
	('12' , 'VAC' , 'VACATION'          , 'y','n','n', '2010-01-01' , '2010-01-01 08:08:08' , 1) ,
	('13' , 'WEP' , 'EMERGENCY'         , 'y','n','n', '2010-01-01' , '2010-01-01 08:08:08' , 1) ,
	('14' , 'HAZ' , 'HAZARD DAY'        , 'y','n','n', '2010-01-01' , '2010-01-01 08:08:08' , 1) ,
	('15' , 'HIP' , 'HOLIDAY INCENTIVE' , 'y','n','n', '2010-01-01' , '2010-01-01 08:08:08' , 1) ,
	('16' , 'OC1' , 'ON CALL - 1.50'    , 'y','n','n', '2010-01-01' , '2010-01-01 08:08:08' , 1) ,
	('17' , 'OC2' , 'ON CALL - 2.00'    , 'y','n','n', '2010-01-01' , '2010-01-01 08:08:08' , 1) ,
	('18' , 'PRM' , 'PREMIUM'           , 'y','n','n', '2010-01-01' , '2010-01-01 08:08:08' , 1) ,
	('19' , 'XYZ' , 'XYZ'               , 'y','n','n', '2010-01-01' , '2010-01-01 08:08:08' , 1) ,
	('20' , 'XYY' , 'XYY'               , 'y','n','n', '2010-01-01' , '2010-01-01 08:08:08' , 1) ,
	('21' , 'XZZ' , 'XZZ'               , 'y','n','n', '2010-01-01' , '2010-01-01 08:08:08' , 1) ;

# Sal Group
DELETE FROM `tk_sal_group_s`;
INSERT INTO `tk_sal_group_S` (`ID`) VALUES ('1000');
DELETE FROM `tk_sal_group_t`;
INSERT INTO `tk_sal_group_t` (`TK_SAL_GROUP_ID`, `TK_SAL_GROUP`, `EFFDT`, `TIMESTAMP`, `ACTIVE`) VALUES
    ('10', 'A10', '2010-01-01', '2010-01-01 08:08:08' , 1),
    ('11', 'S10', '2010-01-01', '2010-01-01 08:08:08' , 1),
    ('12', 'A12', '2010-01-01', '2010-01-01 08:08:08' , 1),
    ('13', 'S12', '2010-01-01', '2010-01-01 08:08:08' , 1),
    ('14', 'NOP', '2010-01-01', '2010-01-01 08:08:08' , 1);

# HR Pay Types
DELETE FROM `hr_paytype_s`;
INSERT INTO `hr_paytype_s` (`ID`)	VALUES	('1000');
DELETE FROM `hr_paytype_t`;
INSERT INTO `hr_paytype_t` (`HR_PAYTYPE_ID`,`PAYTYPE`,`DESCR`,`CALENDAR_GROUP`,`REG_ERN_CODE`,`EFFDT`,`TIMESTAMP`,`HOLIDAY_CALENDAR_GROUP`,`OBJ_ID`,`VER_NBR`,`ACTIVE`) VALUES
	(1, 'BW', 'description', 'BW-CAL1', 'RGN', '2010-08-01', '2010-08-01 16:01:07', 'HOL', '47326FEA-46E7-7D89-0B13-85DFA45EA8C1', '1',1),
	(2, 'BW', 'description', 'TST-CAL', 'RGN', '2010-01-01', '2010-08-01 16:01:07', 'HOL', '47326FEA-46E7-7D89-0B13-85DFA45EA8C1', '1',1);

# time collection rule
DELETE FROM `tk_time_collection_rl_s`;
INSERT INTO `tk_time_collection_rl_s` VALUES('1000');
DELETE FROM `tk_time_collection_rl_t`;
INSERT INTO `tk_time_collection_rl_t` (`TK_TIME_COLL_RULE_ID`,`DEPT`,`WORK_AREA`,`EFFDT`,`CLOCK_USERS_FL`,`HRS_DISTRIBUTION_FL`,`USER_PRINCIPAL_ID`,
`TIMESTAMP`,`ACTIVE`) VALUES
	('1' , 'TEST-DEPT' , 1234 , '2010-01-01' , 1 , 1 , 'admin' , '2010-01-01 08:08:08' , 1)  ,
	('2' , '*'         , 1234 , '2010-01-01' , 1 , 1 , 'admin' , '2010-01-01 08:08:08' , 1)  ,
	('3' , 'TEST-DEPT' , -1   , '2010-01-01' , 1 , 1 , 'admin' , '2010-01-01 08:08:08' , 1)  ,
	('4' , '*'         , -1   , '2010-01-01' , 1 , 1 , 'admin' , '2010-01-01 08:08:08' , 1);


DELETE FROM `tk_time_block_s`;
INSERT INTO `tk_time_block_s` VALUES ('5000');
DELETE FROM `tk_time_block_t`;
INSERT INTO `tk_time_block_t` (`TK_TIME_BLOCK_ID`,`DOCUMENT_ID`,`JOB_NUMBER`,`WORK_AREA`,`TASK`,`TK_WORK_AREA_ID`,
	`HR_JOB_ID`,`TK_TASK_ID`,`EARN_CODE`,`BEGIN_TS`,`BEGIN_TS_TZ`,`END_TS`,`END_TS_TZ`,
	`CLOCK_LOG_CREATED`,`HOURS`,`amount`,`USER_PRINCIPAL_ID`,`TIMESTAMP`,`OBJ_ID`,`VER_NBR`) VALUES
		(2000,'4145',1,1234,0,100,1012,100,'RGN','2010-09-19 08:08:08', 'EST', '2010-09-19 10:08:08', 'EST',0,2,0,'admin','2010-09-19 08:08:08','38382',1),
		(2001,'4145',1,1234,0,100,1012,100,'RGN','2010-09-20 08:08:08', 'EST', '2010-09-20 10:08:08', 'EST',0,2,0,'admin','2010-09-20 08:08:08','38382',1);

DELETE FROM `tk_hour_detail_s`;
INSERT INTO `tk_hour_detail_s` VALUES ('1000');
DELETE FROM `tk_hour_detail_t`;
INSERT INTO `tk_hour_detail_t` (`TK_HOUR_DETAIL_ID`,`TK_TIME_BLOCK_ID`,`EARN_CODE`,`HOURS`, `amount`,`OBJ_ID`,`VER_NBR`) VALUES
	(100,2000,'RGN',2,0,'888223f',1),
	(101,2001,'RGN',2,0,'23432ff',1);

DELETE FROM `tk_earn_group_s`;
INSERT INTO `tk_earn_group_s` VALUES ('1000');
DELETE FROM `tk_earn_group_t`;
INSERT INTO `tk_earn_group_t` (`tk_earn_group_id`,`earn_group`,`descr`,`effdt`,`active`,`OBJ_ID`,`VER_NBR`,`timestamp`) VALUES
	(100,'REG','Regular', '2010-01-01',1,'7EE387AB-26B0-B6A6-9C4C-5B5F687F0E97','20','2010-07-27 10:25:13' ),
	(101,'OVT','Overtime', '2010-01-01',1,'7EE387AB-26B0-B6A6-9C4C-5B5F687F0E97','20','2010-07-27 10:25:13' ),
	(102,'TC1','Regular', '2010-01-01',1,'7EE387AB-26B0-B6A6-9C4C-5B5F687F0E97','20','2010-07-27 10:25:13' ),
	(103,'TC2','Regular', '2010-01-01',1,'7EE387AB-26B0-B6A6-9C4C-5B5F687F0E97','20','2010-07-27 10:25:13' ),
	(104,'TC3','Regular', '2010-01-01',1,'7EE387AB-26B0-B6A6-9C4C-5B5F687F0E97','20','2010-07-27 10:25:13' );

DELETE FROM `tk_earn_group_def_s`;
INSERT INTO `tk_earn_group_def_s` VALUES ('1000');
DELETE FROM `tk_earn_group_def_t`;
INSERT INTO `tk_earn_group_def_t` (`tk_earn_group_def_id`, `tk_earn_group_id`,`earn_code`,`OBJ_ID`,`VER_NBR`) VALUES
	(100,100,'REG','7EE387AB-26B0-B6A6-9C4C-5B5F687F0E97',1),
	(101,100,'RGN','7EE387AB-26B0-B6A6-9C4C-5B5F687F0E97',1),
	(102,100,'RGH','7EE387AB-26B0-B6A6-9C4C-5B5F687F0E97',1),
	(109,101,'OVT','7EE387AB-26B0-B6A6-9C4C-5B5F687F0E97',1);

DELETE FROM `tk_roles_s`;
INSERT INTO `tk_roles_s` VALUES ('1000');
DELETE FROM `tk_roles_t`;
INSERT INTO `tk_roles_t` (`TK_ROLES_ID`, `PRINCIPAL_ID`, `ROLE_NAME`, `USER_PRINCIPAL_ID`, `WORK_AREA`, `DEPT`, `EFFDT`, `TIMESTAMP`, `ACTIVE`) VALUES
    ('1', 'admin', 'TK_APPROVER', 'admin', '999', NULL, '2010-08-01', '2010-08-01 15:10:57', 1),
    ('2', 'admin', 'TK_APPROVER', 'admin', '999', NULL, '2010-08-10', '2010-08-10 15:10:57', 1),
    ('3', 'admin', 'TK_APPROVER', 'admin', '999', NULL, '2010-08-20', '2010-08-20 15:10:57', 1),
    ('4', 'admin', 'TK_APPROVER', 'admin', '999', NULL, '2010-08-20', '2010-08-20 15:11:57', 1),
    ('5', 'admin', 'TK_APPROVER', 'admin', '999', NULL, '2010-08-20', '2010-08-20 15:12:57', 1),
    ('6', 'eric',  'TK_APPROVER', 'admin', '999', NULL, '2010-08-01', '2010-08-01 16:10:57', 1),
    ('7', 'eric',  'TK_APPROVER', 'admin', '999', NULL, '2010-08-10', '2010-08-10 16:10:57', 1),
    ('8', 'eric',  'TK_APPROVER', 'admin', '999', NULL, '2010-08-20', '2010-08-20 16:10:57', 1),
    ('9', 'eric',  'TK_APPROVER', 'admin', '999', NULL, '2010-08-20', '2010-08-20 16:11:57', 1),
    ('10','eric',  'TK_APPROVER', 'admin', '999', NULL, '2010-08-20', '2010-08-20 16:12:57', 1),
    ('11','admin', 'TK_ORG_ADMIN', 'admin', '999', NULL, '2010-08-01', '2010-08-01 15:10:57', 1),
    ('12','admin', 'TK_ORG_ADMIN', 'admin', '999', NULL, '2010-08-10', '2010-08-10 15:10:57', 1),
    ('13','admin', 'TK_ORG_ADMIN', 'admin', '999', NULL, '2010-08-20', '2010-08-20 15:10:57', 1),
    ('14','admin', 'TK_ORG_ADMIN', 'admin', '999', NULL, '2010-08-20', '2010-08-20 15:11:57', 1),
    ('15','admin', 'TK_ORG_ADMIN', 'admin', '999', NULL, '2010-08-20', '2010-08-20 15:12:57', 1),
    ('16','eric',  'TK_ORG_ADMIN', 'admin', '999', NULL, '2010-08-01', '2010-08-01 16:10:57', 1),
    ('17','eric',  'TK_ORG_ADMIN', 'admin', '999', NULL, '2010-08-10', '2010-08-10 16:10:57', 1),
    ('18','eric',  'TK_ORG_ADMIN', 'admin', '999', NULL, '2010-08-20', '2010-08-20 16:10:57', 1),
    ('19','eric',  'TK_ORG_ADMIN', 'admin', '999', NULL, '2010-08-20', '2010-08-20 16:11:57', 1),
    ('20','eric',  'TK_ORG_ADMIN', 'admin', '999', NULL, '2010-08-20', '2010-08-20 16:12:57', 1),
    ('21','eric',  'TK_APPROVER', 'admin', '999', NULL, '2010-08-20', '2010-08-20 16:13:57', 0),
    ('22','admin', 'TK_APPROVER', 'admin', '1234', NULL, '2010-01-05', '2010-01-05 15:12:57', 1);

#
# Pay Calendar
DELETE FROM `tk_py_calendar_s`;
INSERT INTO `tk_py_calendar_s`	(`ID`)	VALUES	('30');
DELETE FROM `tk_py_calendar_t`;
INSERT INTO `tk_py_calendar_t`	(`tk_py_calendar_id`,	`calendar_group`,	`chart`,	`begin_date`,	`begin_time`,	`end_date`,	`end_time`)	VALUES
	('1',  'TST-CAL', 'CHART1', '2010-01-01', '12:00:00', '2011-01-01', '12:00:00'),
	('20', 'BW-CAL1', 'CHART1', '2010-01-01', '00:00:00', '2010-12-31', '23:59:59'),
	('21', 'BW-CAL2', 'CHART2', '2010-01-01', '00:00:00', '2010-12-31', '23:59:59'),
	('22', 'BW-CAL3', 'CHART3', '2010-01-01', '00:00:00', '2010-12-31', '23:59:59');

#
# Pay Calendar Dates
DELETE FROM `tk_py_calendar_dates_s`;
INSERT INTO `tk_py_calendar_dates_s`	(`ID`)	VALUES	('30');
DELETE FROM `tk_py_calendar_dates_t`;
INSERT INTO `tk_py_calendar_dates_t` (`tk_py_calendar_dates_id`,`tk_py_calendar_id`,`begin_period_date`,`end_period_date`,`initiate_date`,`initiate_time`,`end_pay_period_date`,`end_pay_period_time`,`employee_approval_date`,`employee_approval_time`,`supervisor_approval_date`,`supervisor_approval_time`) VALUES
	('1', '20', '2010-08-01 00:00:00', '2010-08-14 23:59:59', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	('2', '20', '2010-08-15 00:00:00', '2010-08-31 23:59:59', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	('3', '20', '2010-09-01 00:00:00', '2010-09-14 23:59:59', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	('4', '20', '2010-09-15 00:00:00', '2010-09-30 23:59:59', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	('5', '20', '2010-10-01 00:00:00', '2010-10-15 23:59:59', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	('6', '1',  '2010-01-01 12:00:00', '2010-01-16 12:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	('7', '1',  '2010-01-16 12:00:00', '2010-01-31 12:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	('8', '20', '2010-10-16 00:00:00', '2010-10-31 23:59:59', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

	
	

#
# Daily Overtime Rule
#
# Note - Do not change IDs, Dept, WorkArea or Task.  These values are used in
# the test cases.  Other values may be modified for now, until the test cases
# start looking at them as well
DELETE FROM `tk_daily_overtime_rl_s`;
INSERT INTO `tk_daily_overtime_rl_s` (`ID`) VALUES (1000);
DELETE FROM `tk_daily_overtime_rl_t`;
INSERT INTO `tk_daily_overtime_rl_t` (`tk_daily_overtime_rl_id`,`dept`,`work_area`,`task`,`location`,`paytype`,`max_gap`,`shift_hours`,`overtime_preference`,`user_principal_id`,`effdt`,`active`,`timestamp`) VALUES
    (1,  'TEST-DEPT', 1234, 1, 'IN', 'BW', -1, -1, 'OVT', 'admin', '2010-01-01', 1, '2010-08-20 16:13:57'),
    (2,  'TEST-DEPT', 1234,-1, 'IN', 'BW', -1, -1, 'OVT', 'admin', '2010-01-01', 1, '2010-08-20 16:13:57'),
    (3,  'TEST-DEPT',   -1, 1, 'IN', 'BW', -1, -1, 'OVT', 'admin', '2010-01-01', 1, '2010-08-20 16:13:57'),
    (4,  'TEST-DEPT',   -1,-1, 'IN', 'BW', -1, -1, 'OVT', 'admin', '2010-01-01', 1, '2010-08-20 16:13:57'),
    (5,  '*',         1234, 1, 'IN', 'BW', -1, -1, 'OVT', 'admin', '2010-01-01', 1, '2010-08-20 16:13:57'),
    (6,  '*',         1234,-1, 'IN', 'BW', -1, -1, 'OVT', 'admin', '2010-01-01', 1, '2010-08-20 16:13:57'),
    (7,  '*',           -1, 1, 'IN', 'BW', -1, -1, 'OVT', 'admin', '2010-01-01', 1, '2010-08-20 16:13:57'),
    (8,  '*',           -1,-1, 'IN', 'BW', -1, -1, 'OVT', 'admin', '2010-01-01', 1, '2010-08-20 16:13:57'),
    (9,  'TEST-DEPT2',  -1,-1, 'IN', 'BW', -1, -1, 'OVT', 'admin', '2010-01-01', 1, '2010-08-20 16:13:57');
# Task ID 7 above doesn't really make sense, the lookup form is not supported.

#
# Hr Work Schedule
#
# 1-8: Basic wildcard test data
DELETE FROM `HR_WORK_SCHEDULE_S`;
INSERT INTO `HR_WORK_SCHEDULE_S` (`ID`) VALUES (1000);
DELETE FROM `HR_WORK_SCHEDULE_T`;
INSERT INTO `HR_WORK_SCHEDULE_T` (`HR_WORK_SCHEDULE_ID`,`WORK_SCHEDULE_DESC`,`PRINCIPAL_ID`,`DEPT`,`WORK_AREA`,`ACTIVE`,`EFFDT`,`TIMESTAMP`,`OBJ_ID`,`VER_NBR`) VALUES
    (1 , 'used for testing' , 'admin' , 'TEST-DEPT' , 1234 , 1 , '2010-01-01' , '2010-01-01 12:00:00' , 'uuid' , 1)  ,
    (2 , 'used for testing' , 'admin' , 'TEST-DEPT' , -1   , 1 , '2010-01-01' , '2010-01-01 12:00:00' , 'uuid' , 1)  ,
    (3 , 'used for testing' , 'admin' , '*'         , 1234 , 1 , '2010-01-01' , '2010-01-01 12:00:00' , 'uuid' , 1)  ,
    (4 , 'used for testing' , 'admin' , '*'         , -1   , 1 , '2010-01-01' , '2010-01-01 12:00:00' , 'uuid' , 1)  ,
    (5 , 'used for testing' , '*'     , 'TEST-DEPT' , 1234 , 1 , '2010-01-01' , '2010-01-01 12:00:00' , 'uuid' , 1)  ,
    (6 , 'used for testing' , '*'     , 'TEST-DEPT' , -1   , 1 , '2010-01-01' , '2010-01-01 12:00:00' , 'uuid' , 1)  ,
    (7 , 'used for testing' , '*'     , '*'         , 1234 , 1 , '2010-01-01' , '2010-01-01 12:00:00' , 'uuid' , 1)  ,
    (8 , 'used for testing' , '*'     , '*'         , -1   , 1 , '2010-01-01' , '2010-01-01 12:00:00' , 'uuid' , 1);

#
# 1-16: Basic entries, 2 per Work schedule for wildcard testing
DELETE FROM `HR_WORK_SCHEDULE_ENTRY_S`;
INSERT INTO `HR_WORK_SCHEDULE_ENTRY_S` (`ID`) VALUES (1000);
DELETE FROM `HR_WORK_SCHEDULE_ENTRY_T`;
INSERT INTO `HR_WORK_SCHEDULE_ENTRY_T` (`HR_WORK_SCHEDULE_ENTRY_ID`, `HR_WORK_SCHEDULE_ID`, `CALENDAR_GROUP`, `DAY_OF_PERIOD`, `REG_HOURS`, `OBJ_ID`, `VER_NBR`) VALUES
	(1  , 1 , 'BW-CAL1' , 1 , 40 , 'uuid' , 1)  ,
	(2  , 1 , 'BW-CAL1' , 1 , 40 , 'uuid' , 1)  ,
	(3  , 2 , 'BW-CAL1' , 1 , 40 , 'uuid' , 1)  ,
	(4  , 2 , 'BW-CAL1' , 1 , 40 , 'uuid' , 1)  ,
	(5  , 3 , 'BW-CAL1' , 1 , 40 , 'uuid' , 1)  ,
	(6  , 3 , 'BW-CAL1' , 1 , 40 , 'uuid' , 1)  ,
	(7  , 4 , 'BW-CAL1' , 1 , 40 , 'uuid' , 1)  ,
	(8  , 4 , 'BW-CAL1' , 1 , 40 , 'uuid' , 1)  ,
	(9  , 5 , 'BW-CAL1' , 1 , 40 , 'uuid' , 1)  ,
	(10 , 5 , 'BW-CAL1' , 1 , 40 , 'uuid' , 1)  ,
	(11 , 6 , 'BW-CAL1' , 1 , 40 , 'uuid' , 1)  ,
	(12 , 6 , 'BW-CAL1' , 1 , 40 , 'uuid' , 1)  ,
	(13 , 7 , 'BW-CAL1' , 1 , 40 , 'uuid' , 1)  ,
	(14 , 7 , 'BW-CAL1' , 1 , 40 , 'uuid' , 1)  ,
	(15 , 8 , 'BW-CAL1' , 1 , 40 , 'uuid' , 1)  ,
	(16 , 8 , 'BW-CAL1' , 1 , 40 , 'uuid' , 1);

DELETE FROM `TK_WEEKLY_OVERTIME_RL_S`;
INSERT INTO `TK_WEEKLY_OVERTIME_RL_S` (`ID`) VALUES (1000);
DELETE FROM `TK_WEEKLY_OVERTIME_RL_T`;
#INSERT INTO `TK_WEEKLY_OVERTIME_RL_T` (`tk_weekly_overtime_rl_id`,`max_hrs_ern_group`,`convert_from_ern_group`,`convert_to_erncd`,`step`,`max_hrs`,`user_principal_id`,`effdt`,`active`,`timestamp`,`obj_id`,`ver_nbr`) VALUES
#    ();


INSERT INTO changelog (change_number, complete_dt, applied_by, description)
 VALUES (2, CURRENT_TIMESTAMP, USER(), '002-0.1.0-tk_la_ca_hr_bootstrap.sql');

COMMIT;

-- END CHANGE SCRIPT #2: 002-0.1.0-tk_la_ca_hr_bootstrap.sql


-- START CHANGE SCRIPT #3: 003.0.1.0-tk_krns_krsb_qrtz_schema_bootstrap.sql

-- MySQL dump 10.13  Distrib 5.1.41, for debian-linux-gnu (i486)
--
-- Host: andover    Database: tk
-- ------------------------------------------------------
-- Server version	5.1.41-3ubuntu12.6

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
-- Table structure for table `krns_adhoc_rte_actn_recip_t`
--

DROP TABLE IF EXISTS `krns_adhoc_rte_actn_recip_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `krns_adhoc_rte_actn_recip_t` (
  `RECIP_TYP_CD` decimal(1,0) NOT NULL DEFAULT '0',
  `ACTN_RQST_CD` varchar(30) COLLATE utf8_bin NOT NULL DEFAULT '',
  `ACTN_RQST_RECIP_ID` varchar(70) COLLATE utf8_bin NOT NULL DEFAULT '',
  `OBJ_ID` varchar(36) COLLATE utf8_bin NOT NULL,
  `VER_NBR` decimal(8,0) NOT NULL DEFAULT '1',
  `DOC_HDR_ID` varchar(14) COLLATE utf8_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`RECIP_TYP_CD`,`ACTN_RQST_CD`,`ACTN_RQST_RECIP_ID`,`DOC_HDR_ID`),
  UNIQUE KEY `KRNS_ADHOC_RTE_ACTN_RECIP_TC0` (`OBJ_ID`),
  KEY `KRNS_ADHOC_RTE_ACTN_RECIP_T2` (`DOC_HDR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `krns_adhoc_rte_actn_recip_t`
--

LOCK TABLES `krns_adhoc_rte_actn_recip_t` WRITE;
/*!40000 ALTER TABLE `krns_adhoc_rte_actn_recip_t` DISABLE KEYS */;
/*!40000 ALTER TABLE `krns_adhoc_rte_actn_recip_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `krns_att_t`
--

DROP TABLE IF EXISTS `krns_att_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `krns_att_t` (
  `NTE_ID` decimal(14,0) NOT NULL DEFAULT '0',
  `OBJ_ID` varchar(36) COLLATE utf8_bin NOT NULL,
  `VER_NBR` decimal(8,0) NOT NULL DEFAULT '1',
  `MIME_TYP` varchar(150) COLLATE utf8_bin DEFAULT NULL,
  `FILE_NM` varchar(250) COLLATE utf8_bin DEFAULT NULL,
  `ATT_ID` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `FILE_SZ` decimal(14,0) DEFAULT NULL,
  `ATT_TYP_CD` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`NTE_ID`),
  UNIQUE KEY `KRNS_ATT_TC0` (`OBJ_ID`),
  CONSTRAINT `KRNS_ATT_TR1` FOREIGN KEY (`NTE_ID`) REFERENCES `krns_nte_t` (`NTE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `krns_att_t`
--

LOCK TABLES `krns_att_t` WRITE;
/*!40000 ALTER TABLE `krns_att_t` DISABLE KEYS */;
/*!40000 ALTER TABLE `krns_att_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `krns_doc_hdr_t`
--

DROP TABLE IF EXISTS `krns_doc_hdr_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `krns_doc_hdr_t` (
  `DOC_HDR_ID` varchar(14) COLLATE utf8_bin NOT NULL DEFAULT '',
  `OBJ_ID` varchar(36) COLLATE utf8_bin NOT NULL,
  `VER_NBR` decimal(8,0) NOT NULL DEFAULT '1',
  `FDOC_DESC` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `ORG_DOC_HDR_ID` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `TMPL_DOC_HDR_ID` varchar(14) COLLATE utf8_bin DEFAULT NULL,
  `EXPLANATION` varchar(400) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`DOC_HDR_ID`),
  UNIQUE KEY `KRNS_DOC_HDR_TC0` (`OBJ_ID`),
  KEY `KRNS_DOC_HDR_TI3` (`ORG_DOC_HDR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `krns_doc_hdr_t`
--

LOCK TABLES `krns_doc_hdr_t` WRITE;
/*!40000 ALTER TABLE `krns_doc_hdr_t` DISABLE KEYS */;
/*!40000 ALTER TABLE `krns_doc_hdr_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `krns_lock_s`
--

DROP TABLE IF EXISTS `krns_lock_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `krns_lock_s` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2000 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `krns_lock_s`
--

LOCK TABLES `krns_lock_s` WRITE;
/*!40000 ALTER TABLE `krns_lock_s` DISABLE KEYS */;
/*!40000 ALTER TABLE `krns_lock_s` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `krns_lookup_rslt_s`
--

DROP TABLE IF EXISTS `krns_lookup_rslt_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `krns_lookup_rslt_s` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2000 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `krns_lookup_rslt_s`
--

LOCK TABLES `krns_lookup_rslt_s` WRITE;
/*!40000 ALTER TABLE `krns_lookup_rslt_s` DISABLE KEYS */;
/*!40000 ALTER TABLE `krns_lookup_rslt_s` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `krns_lookup_rslt_t`
--

DROP TABLE IF EXISTS `krns_lookup_rslt_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `krns_lookup_rslt_t` (
  `LOOKUP_RSLT_ID` varchar(14) COLLATE utf8_bin NOT NULL DEFAULT '',
  `OBJ_ID` varchar(36) COLLATE utf8_bin NOT NULL,
  `VER_NBR` decimal(8,0) NOT NULL DEFAULT '1',
  `PRNCPL_ID` varchar(40) COLLATE utf8_bin NOT NULL,
  `LOOKUP_DT` datetime NOT NULL,
  `SERIALZD_RSLTS` longtext COLLATE utf8_bin,
  PRIMARY KEY (`LOOKUP_RSLT_ID`),
  UNIQUE KEY `KRNS_LOOKUP_RSLT_TC0` (`OBJ_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `krns_lookup_rslt_t`
--

LOCK TABLES `krns_lookup_rslt_t` WRITE;
/*!40000 ALTER TABLE `krns_lookup_rslt_t` DISABLE KEYS */;
/*!40000 ALTER TABLE `krns_lookup_rslt_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `krns_lookup_sel_t`
--

DROP TABLE IF EXISTS `krns_lookup_sel_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `krns_lookup_sel_t` (
  `LOOKUP_RSLT_ID` varchar(14) COLLATE utf8_bin NOT NULL DEFAULT '',
  `OBJ_ID` varchar(36) COLLATE utf8_bin NOT NULL,
  `VER_NBR` decimal(8,0) NOT NULL DEFAULT '1',
  `PRNCPL_ID` varchar(40) COLLATE utf8_bin NOT NULL,
  `LOOKUP_DT` datetime NOT NULL,
  `SEL_OBJ_IDS` longtext COLLATE utf8_bin,
  PRIMARY KEY (`LOOKUP_RSLT_ID`),
  UNIQUE KEY `KRNS_LOOKUP_SEL_TC0` (`OBJ_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `krns_lookup_sel_t`
--

LOCK TABLES `krns_lookup_sel_t` WRITE;
/*!40000 ALTER TABLE `krns_lookup_sel_t` DISABLE KEYS */;
/*!40000 ALTER TABLE `krns_lookup_sel_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `krns_maint_doc_att_t`
--

DROP TABLE IF EXISTS `krns_maint_doc_att_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `krns_maint_doc_att_t` (
  `DOC_HDR_ID` varchar(14) COLLATE utf8_bin NOT NULL DEFAULT '',
  `ATT_CNTNT` longblob NOT NULL,
  `FILE_NM` varchar(150) COLLATE utf8_bin DEFAULT NULL,
  `CNTNT_TYP` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `OBJ_ID` varchar(36) COLLATE utf8_bin NOT NULL,
  `VER_NBR` decimal(8,0) NOT NULL DEFAULT '1',
  PRIMARY KEY (`DOC_HDR_ID`),
  UNIQUE KEY `KRNS_MAINT_DOC_ATT_TC0` (`OBJ_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `krns_maint_doc_att_t`
--

LOCK TABLES `krns_maint_doc_att_t` WRITE;
/*!40000 ALTER TABLE `krns_maint_doc_att_t` DISABLE KEYS */;
/*!40000 ALTER TABLE `krns_maint_doc_att_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `krns_maint_doc_t`
--

DROP TABLE IF EXISTS `krns_maint_doc_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `krns_maint_doc_t` (
  `DOC_HDR_ID` varchar(14) COLLATE utf8_bin NOT NULL DEFAULT '',
  `OBJ_ID` varchar(36) COLLATE utf8_bin NOT NULL,
  `VER_NBR` decimal(8,0) NOT NULL DEFAULT '1',
  `DOC_CNTNT` longtext COLLATE utf8_bin,
  PRIMARY KEY (`DOC_HDR_ID`),
  UNIQUE KEY `KRNS_MAINT_DOC_TC0` (`OBJ_ID`),
  CONSTRAINT `KRNS_MAINT_DOC_TR1` FOREIGN KEY (`DOC_HDR_ID`) REFERENCES `krns_doc_hdr_t` (`DOC_HDR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `krns_maint_doc_t`
--

LOCK TABLES `krns_maint_doc_t` WRITE;
/*!40000 ALTER TABLE `krns_maint_doc_t` DISABLE KEYS */;
/*!40000 ALTER TABLE `krns_maint_doc_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `krns_maint_lock_s`
--

DROP TABLE IF EXISTS `krns_maint_lock_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `krns_maint_lock_s` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2050 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `krns_maint_lock_s`
--

LOCK TABLES `krns_maint_lock_s` WRITE;
/*!40000 ALTER TABLE `krns_maint_lock_s` DISABLE KEYS */;
/*!40000 ALTER TABLE `krns_maint_lock_s` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `krns_maint_lock_t`
--

DROP TABLE IF EXISTS `krns_maint_lock_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `krns_maint_lock_t` (
  `MAINT_LOCK_REP_TXT` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `OBJ_ID` varchar(36) COLLATE utf8_bin NOT NULL,
  `VER_NBR` decimal(8,0) NOT NULL DEFAULT '1',
  `DOC_HDR_ID` varchar(14) COLLATE utf8_bin NOT NULL,
  `MAINT_LOCK_ID` varchar(14) COLLATE utf8_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`MAINT_LOCK_ID`),
  UNIQUE KEY `KRNS_MAINT_LOCK_TC0` (`OBJ_ID`),
  KEY `KRNS_MAINT_LOCK_TI2` (`DOC_HDR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `krns_maint_lock_t`
--

LOCK TABLES `krns_maint_lock_t` WRITE;
/*!40000 ALTER TABLE `krns_maint_lock_t` DISABLE KEYS */;
/*!40000 ALTER TABLE `krns_maint_lock_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `krns_nte_s`
--

DROP TABLE IF EXISTS `krns_nte_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `krns_nte_s` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2024 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `krns_nte_s`
--

LOCK TABLES `krns_nte_s` WRITE;
/*!40000 ALTER TABLE `krns_nte_s` DISABLE KEYS */;
INSERT INTO `krns_nte_s` VALUES (2020),(2021),(2022),(2023);
/*!40000 ALTER TABLE `krns_nte_s` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `krns_nte_t`
--

DROP TABLE IF EXISTS `krns_nte_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `krns_nte_t` (
  `NTE_ID` decimal(14,0) NOT NULL DEFAULT '0',
  `OBJ_ID` varchar(36) COLLATE utf8_bin NOT NULL,
  `VER_NBR` decimal(8,0) NOT NULL DEFAULT '1',
  `RMT_OBJ_ID` varchar(36) COLLATE utf8_bin NOT NULL,
  `AUTH_PRNCPL_ID` varchar(40) COLLATE utf8_bin NOT NULL,
  `POST_TS` datetime NOT NULL,
  `NTE_TYP_CD` varchar(4) COLLATE utf8_bin NOT NULL,
  `TXT` varchar(800) COLLATE utf8_bin DEFAULT NULL,
  `PRG_CD` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `TPC_TXT` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`NTE_ID`),
  UNIQUE KEY `KRNS_NTE_TC0` (`OBJ_ID`),
  KEY `KRNS_NTE_TR1` (`NTE_TYP_CD`),
  CONSTRAINT `KRNS_NTE_TR1` FOREIGN KEY (`NTE_TYP_CD`) REFERENCES `krns_nte_typ_t` (`NTE_TYP_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `krns_nte_t`
--

LOCK TABLES `krns_nte_t` WRITE;
/*!40000 ALTER TABLE `krns_nte_t` DISABLE KEYS */;
/*!40000 ALTER TABLE `krns_nte_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `krns_nte_typ_t`
--

DROP TABLE IF EXISTS `krns_nte_typ_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `krns_nte_typ_t` (
  `NTE_TYP_CD` varchar(4) COLLATE utf8_bin NOT NULL DEFAULT '',
  `OBJ_ID` varchar(36) COLLATE utf8_bin NOT NULL,
  `VER_NBR` decimal(8,0) NOT NULL DEFAULT '1',
  `TYP_DESC_TXT` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `ACTV_IND` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`NTE_TYP_CD`),
  UNIQUE KEY `KRNS_NTE_TYP_TC0` (`OBJ_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `krns_nte_typ_t`
--

LOCK TABLES `krns_nte_typ_t` WRITE;
/*!40000 ALTER TABLE `krns_nte_typ_t` DISABLE KEYS */;
/*!40000 ALTER TABLE `krns_nte_typ_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `krns_pessimistic_lock_t`
--

DROP TABLE IF EXISTS `krns_pessimistic_lock_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `krns_pessimistic_lock_t` (
  `PESSIMISTIC_LOCK_ID` decimal(14,0) NOT NULL DEFAULT '0',
  `OBJ_ID` varchar(36) COLLATE utf8_bin NOT NULL,
  `VER_NBR` decimal(8,0) NOT NULL DEFAULT '1',
  `LOCK_DESC_TXT` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DOC_HDR_ID` varchar(14) COLLATE utf8_bin NOT NULL,
  `GNRT_DT` datetime NOT NULL,
  `PRNCPL_ID` varchar(40) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`PESSIMISTIC_LOCK_ID`),
  UNIQUE KEY `KRNS_PESSIMISTIC_LOCK_TC0` (`OBJ_ID`),
  KEY `KRNS_PESSIMISTIC_LOCK_TI1` (`DOC_HDR_ID`),
  KEY `KRNS_PESSIMISTIC_LOCK_TI2` (`PRNCPL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `krns_pessimistic_lock_t`
--

LOCK TABLES `krns_pessimistic_lock_t` WRITE;
/*!40000 ALTER TABLE `krns_pessimistic_lock_t` DISABLE KEYS */;
/*!40000 ALTER TABLE `krns_pessimistic_lock_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `krns_sesn_doc_t`
--

DROP TABLE IF EXISTS `krns_sesn_doc_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `krns_sesn_doc_t` (
  `SESN_DOC_ID` varchar(40) COLLATE utf8_bin NOT NULL DEFAULT '',
  `DOC_HDR_ID` varchar(14) COLLATE utf8_bin NOT NULL DEFAULT '',
  `PRNCPL_ID` varchar(40) COLLATE utf8_bin NOT NULL DEFAULT '',
  `IP_ADDR` varchar(60) COLLATE utf8_bin NOT NULL DEFAULT '',
  `SERIALZD_DOC_FRM` longblob,
  `LAST_UPDT_DT` datetime DEFAULT NULL,
  `CONTENT_ENCRYPTED_IND` char(1) COLLATE utf8_bin DEFAULT 'N',
  PRIMARY KEY (`SESN_DOC_ID`,`DOC_HDR_ID`,`PRNCPL_ID`,`IP_ADDR`),
  KEY `KRNS_SESN_DOC_TI1` (`LAST_UPDT_DT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `krns_sesn_doc_t`
--

LOCK TABLES `krns_sesn_doc_t` WRITE;
/*!40000 ALTER TABLE `krns_sesn_doc_t` DISABLE KEYS */;
/*!40000 ALTER TABLE `krns_sesn_doc_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `krsb_bam_parm_s`
--

DROP TABLE IF EXISTS `krsb_bam_parm_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `krsb_bam_parm_s` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2000 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `krsb_bam_parm_s`
--

LOCK TABLES `krsb_bam_parm_s` WRITE;
/*!40000 ALTER TABLE `krsb_bam_parm_s` DISABLE KEYS */;
/*!40000 ALTER TABLE `krsb_bam_parm_s` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `krsb_bam_parm_t`
--

DROP TABLE IF EXISTS `krsb_bam_parm_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `krsb_bam_parm_t` (
  `BAM_PARM_ID` decimal(14,0) NOT NULL DEFAULT '0',
  `BAM_ID` decimal(14,0) NOT NULL,
  `PARM` longtext COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`BAM_PARM_ID`),
  KEY `KREW_BAM_PARM_TI1` (`BAM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `krsb_bam_parm_t`
--

LOCK TABLES `krsb_bam_parm_t` WRITE;
/*!40000 ALTER TABLE `krsb_bam_parm_t` DISABLE KEYS */;
/*!40000 ALTER TABLE `krsb_bam_parm_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `krsb_bam_s`
--

DROP TABLE IF EXISTS `krsb_bam_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `krsb_bam_s` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2000 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `krsb_bam_s`
--

LOCK TABLES `krsb_bam_s` WRITE;
/*!40000 ALTER TABLE `krsb_bam_s` DISABLE KEYS */;
/*!40000 ALTER TABLE `krsb_bam_s` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `krsb_bam_t`
--

DROP TABLE IF EXISTS `krsb_bam_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `krsb_bam_t` (
  `BAM_ID` decimal(14,0) NOT NULL DEFAULT '0',
  `SVC_NM` varchar(255) COLLATE utf8_bin NOT NULL,
  `SVC_URL` varchar(500) COLLATE utf8_bin NOT NULL,
  `MTHD_NM` varchar(2000) COLLATE utf8_bin NOT NULL,
  `THRD_NM` varchar(500) COLLATE utf8_bin NOT NULL,
  `CALL_DT` datetime NOT NULL,
  `TGT_TO_STR` varchar(2000) COLLATE utf8_bin NOT NULL,
  `SRVR_IND` decimal(1,0) NOT NULL,
  `EXCPN_TO_STR` varchar(2000) COLLATE utf8_bin DEFAULT NULL,
  `EXCPN_MSG` longtext COLLATE utf8_bin,
  PRIMARY KEY (`BAM_ID`),
  KEY `KRSB_BAM_TI1` (`SVC_NM`,`MTHD_NM`(255)),
  KEY `KRSB_BAM_TI2` (`SVC_NM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `krsb_bam_t`
--

LOCK TABLES `krsb_bam_t` WRITE;
/*!40000 ALTER TABLE `krsb_bam_t` DISABLE KEYS */;
/*!40000 ALTER TABLE `krsb_bam_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `krsb_msg_pyld_t`
--

DROP TABLE IF EXISTS `krsb_msg_pyld_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `krsb_msg_pyld_t` (
  `MSG_QUE_ID` decimal(14,0) NOT NULL DEFAULT '0',
  `MSG_PYLD` longtext COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`MSG_QUE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `krsb_msg_pyld_t`
--

LOCK TABLES `krsb_msg_pyld_t` WRITE;
/*!40000 ALTER TABLE `krsb_msg_pyld_t` DISABLE KEYS */;
/*!40000 ALTER TABLE `krsb_msg_pyld_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `krsb_msg_que_s`
--

DROP TABLE IF EXISTS `krsb_msg_que_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `krsb_msg_que_s` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2068 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `krsb_msg_que_s`
--

LOCK TABLES `krsb_msg_que_s` WRITE;
/*!40000 ALTER TABLE `krsb_msg_que_s` DISABLE KEYS */;
/*!40000 ALTER TABLE `krsb_msg_que_s` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `krsb_msg_que_t`
--

DROP TABLE IF EXISTS `krsb_msg_que_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `krsb_msg_que_t` (
  `MSG_QUE_ID` decimal(14,0) NOT NULL DEFAULT '0',
  `DT` datetime NOT NULL,
  `EXP_DT` datetime DEFAULT NULL,
  `PRIO` decimal(8,0) NOT NULL,
  `STAT_CD` char(1) COLLATE utf8_bin NOT NULL,
  `RTRY_CNT` decimal(8,0) NOT NULL,
  `IP_NBR` varchar(2000) COLLATE utf8_bin NOT NULL,
  `SVC_NM` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SVC_NMSPC` varchar(255) COLLATE utf8_bin NOT NULL,
  `SVC_MTHD_NM` varchar(2000) COLLATE utf8_bin DEFAULT NULL,
  `APP_VAL_ONE` varchar(2000) COLLATE utf8_bin DEFAULT NULL,
  `APP_VAL_TWO` varchar(2000) COLLATE utf8_bin DEFAULT NULL,
  `VER_NBR` decimal(8,0) DEFAULT '0',
  PRIMARY KEY (`MSG_QUE_ID`),
  KEY `KRSB_MSG_QUE_TI1` (`SVC_NM`,`SVC_MTHD_NM`(255)),
  KEY `KRSB_MSG_QUE_TI2` (`SVC_NMSPC`,`STAT_CD`,`IP_NBR`(255),`DT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `krsb_msg_que_t`
--

LOCK TABLES `krsb_msg_que_t` WRITE;
/*!40000 ALTER TABLE `krsb_msg_que_t` DISABLE KEYS */;
/*!40000 ALTER TABLE `krsb_msg_que_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `krsb_svc_def_s`
--

DROP TABLE IF EXISTS `krsb_svc_def_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `krsb_svc_def_s` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4058 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `krsb_svc_def_s`
--

LOCK TABLES `krsb_svc_def_s` WRITE;
/*!40000 ALTER TABLE `krsb_svc_def_s` DISABLE KEYS */;
/*!40000 ALTER TABLE `krsb_svc_def_s` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `krsb_svc_def_t`
--

DROP TABLE IF EXISTS `krsb_svc_def_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `krsb_svc_def_t` (
  `SVC_DEF_ID` decimal(14,0) NOT NULL DEFAULT '0',
  `SVC_NM` varchar(255) COLLATE utf8_bin NOT NULL,
  `SVC_URL` varchar(500) COLLATE utf8_bin NOT NULL,
  `SRVR_IP` varchar(40) COLLATE utf8_bin NOT NULL,
  `SVC_NMSPC` varchar(255) COLLATE utf8_bin NOT NULL,
  `SVC_ALIVE` decimal(1,0) NOT NULL,
  `VER_NBR` decimal(8,0) DEFAULT '0',
  `FLT_SVC_DEF_ID` decimal(14,0) NOT NULL,
  `SVC_DEF_CHKSM` varchar(30) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`SVC_DEF_ID`),
  UNIQUE KEY `KRSB_SVC_DEF_TI2` (`FLT_SVC_DEF_ID`),
  KEY `KRSB_SVC_DEF_TI1` (`SRVR_IP`,`SVC_NMSPC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `krsb_svc_def_t`
--

LOCK TABLES `krsb_svc_def_t` WRITE;
/*!40000 ALTER TABLE `krsb_svc_def_t` DISABLE KEYS */;
/*!40000 ALTER TABLE `krsb_svc_def_t` ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS `qrtz_blob_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_blob_triggers` (
  `TRIGGER_NAME` varchar(80) COLLATE utf8_bin NOT NULL DEFAULT '',
  `TRIGGER_GROUP` varchar(80) COLLATE utf8_bin NOT NULL DEFAULT '',
  `BLOB_DATA` longblob,
  PRIMARY KEY (`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_BLOB_TRIGGERS_TR1` FOREIGN KEY (`TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_blob_triggers`
--

LOCK TABLES `qrtz_blob_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_blob_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_blob_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_calendars`
--

DROP TABLE IF EXISTS `qrtz_calendars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_calendars` (
  `CALENDAR_NAME` varchar(80) COLLATE utf8_bin NOT NULL DEFAULT '',
  `CALENDAR` longblob NOT NULL,
  PRIMARY KEY (`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_calendars`
--

LOCK TABLES `qrtz_calendars` WRITE;
/*!40000 ALTER TABLE `qrtz_calendars` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_calendars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_cron_triggers`
--

DROP TABLE IF EXISTS `qrtz_cron_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_cron_triggers` (
  `TRIGGER_NAME` varchar(80) COLLATE utf8_bin NOT NULL DEFAULT '',
  `TRIGGER_GROUP` varchar(80) COLLATE utf8_bin NOT NULL DEFAULT '',
  `CRON_EXPRESSION` varchar(80) COLLATE utf8_bin NOT NULL,
  `TIME_ZONE_ID` varchar(80) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_CRON_TRIGGERS_TR1` FOREIGN KEY (`TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_cron_triggers`
--

LOCK TABLES `qrtz_cron_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_cron_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_cron_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_fired_triggers`
--

DROP TABLE IF EXISTS `qrtz_fired_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_fired_triggers` (
  `ENTRY_ID` varchar(95) COLLATE utf8_bin NOT NULL DEFAULT '',
  `TRIGGER_NAME` varchar(80) COLLATE utf8_bin NOT NULL,
  `TRIGGER_GROUP` varchar(80) COLLATE utf8_bin NOT NULL,
  `IS_VOLATILE` varchar(1) COLLATE utf8_bin NOT NULL,
  `INSTANCE_NAME` varchar(80) COLLATE utf8_bin NOT NULL,
  `FIRED_TIME` decimal(13,0) NOT NULL,
  `PRIORITY` decimal(13,0) NOT NULL,
  `STATE` varchar(16) COLLATE utf8_bin NOT NULL,
  `JOB_NAME` varchar(80) COLLATE utf8_bin DEFAULT NULL,
  `JOB_GROUP` varchar(80) COLLATE utf8_bin DEFAULT NULL,
  `IS_STATEFUL` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ENTRY_ID`),
  KEY `qrtz_FIRED_TRIGGERS_TI1` (`JOB_GROUP`),
  KEY `qrtz_FIRED_TRIGGERS_TI2` (`JOB_NAME`),
  KEY `qrtz_FIRED_TRIGGERS_TI3` (`REQUESTS_RECOVERY`),
  KEY `qrtz_FIRED_TRIGGERS_TI4` (`IS_STATEFUL`),
  KEY `qrtz_FIRED_TRIGGERS_TI5` (`TRIGGER_GROUP`),
  KEY `qrtz_FIRED_TRIGGERS_TI6` (`INSTANCE_NAME`),
  KEY `qrtz_FIRED_TRIGGERS_TI7` (`TRIGGER_NAME`),
  KEY `qrtz_FIRED_TRIGGERS_TI8` (`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `qrtz_FIRED_TRIGGERS_TI9` (`IS_VOLATILE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_fired_triggers`
--

LOCK TABLES `qrtz_fired_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_fired_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_fired_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_job_details`
--

DROP TABLE IF EXISTS `qrtz_job_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_job_details` (
  `JOB_NAME` varchar(80) COLLATE utf8_bin NOT NULL DEFAULT '',
  `JOB_GROUP` varchar(80) COLLATE utf8_bin NOT NULL DEFAULT '',
  `DESCRIPTION` varchar(120) COLLATE utf8_bin DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(128) COLLATE utf8_bin NOT NULL,
  `IS_DURABLE` varchar(1) COLLATE utf8_bin NOT NULL,
  `IS_VOLATILE` varchar(1) COLLATE utf8_bin NOT NULL,
  `IS_STATEFUL` varchar(1) COLLATE utf8_bin NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) COLLATE utf8_bin NOT NULL,
  `JOB_DATA` longblob,
  PRIMARY KEY (`JOB_NAME`,`JOB_GROUP`),
  KEY `qrtz_JOB_DETAILS_TI1` (`REQUESTS_RECOVERY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_job_details`
--

LOCK TABLES `qrtz_job_details` WRITE;
/*!40000 ALTER TABLE `qrtz_job_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_job_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_job_listeners`
--

DROP TABLE IF EXISTS `qrtz_job_listeners`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_job_listeners` (
  `JOB_NAME` varchar(80) COLLATE utf8_bin NOT NULL DEFAULT '',
  `JOB_GROUP` varchar(80) COLLATE utf8_bin NOT NULL DEFAULT '',
  `JOB_LISTENER` varchar(80) COLLATE utf8_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`JOB_NAME`,`JOB_GROUP`,`JOB_LISTENER`),
  CONSTRAINT `KRSB_QUARTZ_JOB_LISTENERS_TR1` FOREIGN KEY (`JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_job_listeners`
--

LOCK TABLES `qrtz_job_listeners` WRITE;
/*!40000 ALTER TABLE `qrtz_job_listeners` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_job_listeners` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_locks`
--

DROP TABLE IF EXISTS `qrtz_locks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_locks` (
  `LOCK_NAME` varchar(40) COLLATE utf8_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_locks`
--

LOCK TABLES `qrtz_locks` WRITE;
/*!40000 ALTER TABLE `qrtz_locks` DISABLE KEYS */;
INSERT INTO `qrtz_locks` VALUES ('CALENDAR_ACCESS'),('JOB_ACCESS'),('MISFIRE_ACCESS'),('STATE_ACCESS'),('TRIGGER_ACCESS');
/*!40000 ALTER TABLE `qrtz_locks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_paused_trigger_grps`
--

DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `TRIGGER_GROUP` varchar(80) COLLATE utf8_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_paused_trigger_grps`
--

LOCK TABLES `qrtz_paused_trigger_grps` WRITE;
/*!40000 ALTER TABLE `qrtz_paused_trigger_grps` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_paused_trigger_grps` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_scheduler_state`
--

DROP TABLE IF EXISTS `qrtz_scheduler_state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_scheduler_state` (
  `INSTANCE_NAME` varchar(80) COLLATE utf8_bin NOT NULL DEFAULT '',
  `LAST_CHECKIN_TIME` decimal(13,0) NOT NULL,
  `CHECKIN_INTERVAL` decimal(13,0) NOT NULL,
  PRIMARY KEY (`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_scheduler_state`
--

LOCK TABLES `qrtz_scheduler_state` WRITE;
/*!40000 ALTER TABLE `qrtz_scheduler_state` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_scheduler_state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_simple_triggers`
--

DROP TABLE IF EXISTS `qrtz_simple_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_simple_triggers` (
  `TRIGGER_NAME` varchar(80) COLLATE utf8_bin NOT NULL DEFAULT '',
  `TRIGGER_GROUP` varchar(80) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REPEAT_COUNT` decimal(7,0) NOT NULL,
  `REPEAT_INTERVAL` decimal(12,0) NOT NULL,
  `TIMES_TRIGGERED` decimal(7,0) NOT NULL,
  PRIMARY KEY (`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_SIMPLE_TRIGGERS_TR1` FOREIGN KEY (`TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_simple_triggers`
--

LOCK TABLES `qrtz_simple_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_simple_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_simple_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_trigger_listeners`
--

DROP TABLE IF EXISTS `qrtz_trigger_listeners`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_trigger_listeners` (
  `TRIGGER_NAME` varchar(80) COLLATE utf8_bin NOT NULL DEFAULT '',
  `TRIGGER_GROUP` varchar(80) COLLATE utf8_bin NOT NULL DEFAULT '',
  `TRIGGER_LISTENER` varchar(80) COLLATE utf8_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_LISTENER`),
  CONSTRAINT `qrtz_TRIGGER_LISTENE_TR1` FOREIGN KEY (`TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_trigger_listeners`
--

LOCK TABLES `qrtz_trigger_listeners` WRITE;
/*!40000 ALTER TABLE `qrtz_trigger_listeners` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_trigger_listeners` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_triggers`
--

DROP TABLE IF EXISTS `qrtz_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_triggers` (
  `TRIGGER_NAME` varchar(80) COLLATE utf8_bin NOT NULL DEFAULT '',
  `TRIGGER_GROUP` varchar(80) COLLATE utf8_bin NOT NULL DEFAULT '',
  `JOB_NAME` varchar(80) COLLATE utf8_bin NOT NULL,
  `JOB_GROUP` varchar(80) COLLATE utf8_bin NOT NULL,
  `IS_VOLATILE` varchar(1) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(120) COLLATE utf8_bin DEFAULT NULL,
  `NEXT_FIRE_TIME` decimal(13,0) DEFAULT NULL,
  `PREV_FIRE_TIME` decimal(13,0) DEFAULT NULL,
  `PRIORITY` decimal(13,0) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) COLLATE utf8_bin NOT NULL,
  `TRIGGER_TYPE` varchar(8) COLLATE utf8_bin NOT NULL,
  `START_TIME` decimal(13,0) NOT NULL,
  `END_TIME` decimal(13,0) DEFAULT NULL,
  `CALENDAR_NAME` varchar(80) COLLATE utf8_bin DEFAULT NULL,
  `MISFIRE_INSTR` decimal(2,0) DEFAULT NULL,
  `JOB_DATA` longblob,
  PRIMARY KEY (`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `qrtz_TRIGGERS_TI1` (`NEXT_FIRE_TIME`),
  KEY `qrtz_TRIGGERS_TI2` (`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `qrtz_TRIGGERS_TI3` (`TRIGGER_STATE`),
  KEY `qrtz_TRIGGERS_TI4` (`IS_VOLATILE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_triggers`
--

LOCK TABLES `qrtz_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_triggers` DISABLE KEYS */;
INSERT INTO `qrtz_triggers` VALUES ('PeriodicMessageProcessingTrigger','KCB-Delivery','MessageProcessingJobDetail','KCB-Delivery','0','\n                Trigger that periodically runs the KCB message processing job\n            ','1221578432323','1221578402323','5','WAITING','BLOB','1221577052323','0',NULL,'0',NULL);
/*!40000 ALTER TABLE `qrtz_triggers` ENABLE KEYS */;
UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2010-10-15 11:25:54


INSERT INTO changelog (change_number, complete_dt, applied_by, description)
 VALUES (3, CURRENT_TIMESTAMP, USER(), '003.0.1.0-tk_krns_krsb_qrtz_schema_bootstrap.sql');

COMMIT;

-- END CHANGE SCRIPT #3: 003.0.1.0-tk_krns_krsb_qrtz_schema_bootstrap.sql


-- START CHANGE SCRIPT #4: 004-0.1.0-tk_alter_shiftdiffrule.sql

ALTER TABLE `tk`.`tk_shift_differential_rl_t` CHANGE COLUMN `BEGIN_TS` `BEGIN_TS` TIMESTAMP NULL DEFAULT NULL  , CHANGE COLUMN `END_TS` `END_TS` TIMESTAMP NULL DEFAULT NULL  ;


INSERT INTO changelog (change_number, complete_dt, applied_by, description)
 VALUES (4, CURRENT_TIMESTAMP, USER(), '004-0.1.0-tk_alter_shiftdiffrule.sql');

COMMIT;

-- END CHANGE SCRIPT #4: 004-0.1.0-tk_alter_shiftdiffrule.sql


-- START CHANGE SCRIPT #5: 005-0.1.0-tk_alter_jobshiftdiff.sql

ALTER TABLE hr_job_t ADD COLUMN `comp_rate` DECIMAL(5,2) NULL DEFAULT 0.00  AFTER `VER_NBR` ;
ALTER TABLE `tk_daily_overtime_rl_t` CHANGE COLUMN `MAX_GAP` `MAX_GAP` DECIMAL(3,2) NULL DEFAULT NULL  ;
ALTER TABLE `tk_shift_differential_rl_t` CHANGE COLUMN `MAX_GAP` `MAX_GAP` DECIMAL(2,2) NULL DEFAULT NULL  ;





INSERT INTO changelog (change_number, complete_dt, applied_by, description)
 VALUES (5, CURRENT_TIMESTAMP, USER(), '005-0.1.0-tk_alter_jobshiftdiff.sql');

COMMIT;

-- END CHANGE SCRIPT #5: 005-0.1.0-tk_alter_jobshiftdiff.sql


-- START CHANGE SCRIPT #6: 006-0.1.0-tk_alter_shift_diff.sql

ALTER TABLE tk_shift_differential_rl_t ADD COLUMN `from_earn_group` VARCHAR(10) NULL DEFAULT NULL  AFTER `ACTIVE` ;


INSERT INTO changelog (change_number, complete_dt, applied_by, description)
 VALUES (6, CURRENT_TIMESTAMP, USER(), '006-0.1.0-tk_alter_shift_diff.sql');

COMMIT;

-- END CHANGE SCRIPT #6: 006-0.1.0-tk_alter_shift_diff.sql


-- START CHANGE SCRIPT #7: 007-0.1.0-tk_alter.sql

ALTER TABLE tk_clock_location_rl_t CHANGE COLUMN `active` `active` VARCHAR(1) NULL DEFAULT NULL  ;
ALTER TABLE tk_daily_overtime_rl_t CHANGE COLUMN `ACTIVE` `ACTIVE` VARCHAR(1) NULL DEFAULT NULL  ;

ALTER TABLE tk_earn_group_t ADD COLUMN `show_summary` VARCHAR(1) NULL DEFAULT 'N'  AFTER `descr` ;

ALTER TABLE tk_system_lunch_rl_t DROP COLUMN `BLOCK_HOURS` , DROP COLUMN `MIN_MINS` , ADD COLUMN `SHOW_LUNCH_BUTTON` VARCHAR(1) NULL DEFAULT '0'  AFTER `USER_PRINCIPAL_ID` ;
ALTER TABLE tk_dept_lunch_rl_t DROP COLUMN `REQUIRED_CLOCK_FL`;
ALTER TABLE tk_dept_lunch_rl_t ADD COLUMN `SHIFT_HOURS` DECIMAL(2,1) NULL DEFAULT NULL  ;
ALTER TABLE tk_dept_lunch_rl_t ADD COLUMN `DEDUCTION_MINS` DECIMAL(3,0) NULL DEFAULT NULL  ;
ALTER TABLE tk_dept_lunch_rl_t CHANGE COLUMN `active` `active` VARCHAR(1) NULL DEFAULT NULL  ;





INSERT INTO changelog (change_number, complete_dt, applied_by, description)
 VALUES (7, CURRENT_TIMESTAMP, USER(), '007-0.1.0-tk_alter.sql');

COMMIT;

-- END CHANGE SCRIPT #7: 007-0.1.0-tk_alter.sql


-- START CHANGE SCRIPT #8: 008-0.1.0-tk_alter.sql

ALTER TABLE tk_sal_group_t CHANGE COLUMN `ACTIVE` `ACTIVE` VARCHAR(1) NULL DEFAULT NULL  ;
ALTER TABLE tk_grace_period_rl_t CHANGE COLUMN `ACTIVE` `ACTIVE` VARCHAR(1) NULL DEFAULT NULL  ;
ALTER TABLE tk_earn_code_t CHANGE COLUMN `ACTIVE` `ACTIVE` VARCHAR(1) NULL DEFAULT NULL  ;
ALTER TABLE tk_earn_code_t ADD COLUMN `ACCRUAL_CATEGORY` VARCHAR(3) NULL DEFAULT NULL  ;
ALTER TABLE la_accrual_categories_t CHANGE COLUMN `ACTIVE` `ACTIVE` VARCHAR(1) NULL DEFAULT 'N'  ;
ALTER TABLE la_accruals_t CHANGE COLUMN `HOURS_ACCRUED` `HOURS_ACCRUED` DECIMAL(6,2) NOT NULL  , CHANGE COLUMN `HOURS_TAKEN` `HOURS_TAKEN` DECIMAL(6,2) NOT NULL  , CHANGE COLUMN `HOURS_ADJUST` `HOURS_ADJUST` DECIMAL(6,2) NOT NULL  ;

ALTER TABLE tk_dept_earn_code_t CHANGE COLUMN `employee` `employee` VARCHAR(1) NULL DEFAULT 'N'  , CHANGE COLUMN `approver` `approver` VARCHAR(1) NULL DEFAULT 'N'  , CHANGE COLUMN `org_admin` `org_admin` VARCHAR(1) NULL DEFAULT 'N'  , CHANGE COLUMN `active` `active` VARCHAR(1) NULL DEFAULT 'N'  ;
ALTER TABLE tk_earn_group_t CHANGE COLUMN `active` `active` VARCHAR(1) NULL DEFAULT NULL  ;
ALTER TABLE hr_job_t CHANGE COLUMN `active` `active` VARCHAR(1) NULL DEFAULT 'N'  ;

ALTER TABLE tk_earn_code_t ADD COLUMN `inflate_factor` DECIMAL(3,2) NOT NULL DEFAULT 1  AFTER `accrual_category` , ADD COLUMN `inflate_min_hours` DECIMAL(3,2) NOT NULL DEFAULT 0  AFTER `accrual_category` ;












INSERT INTO changelog (change_number, complete_dt, applied_by, description)
 VALUES (8, CURRENT_TIMESTAMP, USER(), '008-0.1.0-tk_alter.sql');

COMMIT;

-- END CHANGE SCRIPT #8: 008-0.1.0-tk_alter.sql


-- START CHANGE SCRIPT #9: 009-0.1.0-tk-add-principal-cal.sql

CREATE TABLE `tk_principal_calendar_t` (
  `principal_id` varchar(40) COLLATE utf8_bin NOT NULL DEFAULT '',
  `calendar_group` varchar(45) COLLATE utf8_bin NOT NULL DEFAULT '',
  `holiday_calendar_group` varchar(45) COLLATE utf8_bin NOT NULL DEFAULT '',
  `EFFDT` date NOT NULL DEFAULT '0000-00-00',
  `TIMESTAMP` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `OBJ_ID` varchar(36) COLLATE utf8_bin NOT NULL,
  `VER_NBR` decimal(8,0) NOT NULL DEFAULT '1',
  PRIMARY KEY (`principal_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

insert into tk_principal_calendar_t values('admin','BW-CAL1','HOL','2010-01-01', now(),uuid(),1);
insert into tk_principal_calendar_t values('eric','BW-CAL1','HOL','2010-01-01', now(),uuid(),1);


INSERT INTO changelog (change_number, complete_dt, applied_by, description)
 VALUES (9, CURRENT_TIMESTAMP, USER(), '009-0.1.0-tk-add-principal-cal.sql');

COMMIT;

-- END CHANGE SCRIPT #9: 009-0.1.0-tk-add-principal-cal.sql


-- START CHANGE SCRIPT #10: 010-0.1.0-tk-alter.sql

ALTER TABLE tk_py_calendar_t ADD COLUMN `FLSA_BEGIN_DAY` VARCHAR(9) NOT NULL DEFAULT 'SUN';
ALTER TABLE tk_py_calendar_t ADD COLUMN `FLSA_BEGIN_TIME` TIME NOT NULL DEFAULT '0:00:00';

ALTER TABLE tk_system_lunch_rl_t CHANGE COLUMN `ACTIVE` `ACTIVE` VARCHAR(1) NULL DEFAULT NULL  ;
ALTER TABLE tk_shift_differential_rl_t DROP COLUMN `day0`,DROP COLUMN `day1`,DROP COLUMN `day2`,DROP COLUMN `day3`,
										DROP COLUMN `day4`,DROP COLUMN `day5`,DROP COLUMN `day6`,ADD COLUMN `sun` BIT(1) NOT NULL DEFAULT 0,
										ADD COLUMN `mon` BIT(1) NOT NULL DEFAULT 0,ADD COLUMN `tue` BIT(1) NOT NULL DEFAULT 0,ADD COLUMN `wed` BIT(1) NOT NULL DEFAULT 0,
										ADD COLUMN `thu` BIT(1) NOT NULL DEFAULT 0,ADD COLUMN `fri` BIT(1) NOT NULL DEFAULT 0,ADD COLUMN `sat` BIT(1) NOT NULL DEFAULT 0;
										

ALTER TABLE hr_paytype_t DROP COLUMN `CALENDAR_GROUP`, DROP COLUMN `HOLIDAY_CALENDAR_GROUP`;

ALTER TABLE tk_daily_overtime_rl_t CHANGE COLUMN `active` `active` VARCHAR(1) NULL DEFAULT 'N'  ;

ALTER TABLE hr_paytype_t CHANGE COLUMN `ACTIVE` `ACTIVE` VARCHAR(1) NULL DEFAULT 'N'  ;

ALTER TABLE hr_work_schedule_t CHANGE COLUMN `ACTIVE` `ACTIVE` VARCHAR(1) NULL DEFAULT 'N'  ;
ALTER TABLE tk_assignment_t CHANGE COLUMN `active` `active` VARCHAR(1) NULL DEFAULT 'N'  ;
ALTER TABLE tk_dept_t CHANGE COLUMN `active` `active` VARCHAR(1) NULL DEFAULT 'N'  ;
ALTER TABLE tk_roles_t CHANGE COLUMN `active` `active` VARCHAR(1) NULL DEFAULT 'N'  ;
ALTER TABLE tk_shift_differential_rl_t CHANGE COLUMN `ACTIVE` `ACTIVE` VARCHAR(1) NULL DEFAULT 'N'  ;
ALTER TABLE tk_time_collection_rl_t CHANGE COLUMN `ACTIVE` `ACTIVE` VARCHAR(1) NULL DEFAULT 'N'  ;
ALTER TABLE tk_weekly_overtime_rl_t CHANGE COLUMN `ACTIVE` `ACTIVE` VARCHAR(1) NULL DEFAULT 'N'  ;



INSERT INTO changelog (change_number, complete_dt, applied_by, description)
 VALUES (10, CURRENT_TIMESTAMP, USER(), '010-0.1.0-tk-alter.sql');

COMMIT;

-- END CHANGE SCRIPT #10: 010-0.1.0-tk-alter.sql


-- START CHANGE SCRIPT #11: 011-0.1.0-tk-alter.sql

ALTER TABLE tk_document_header_t ADD COLUMN `OBJ_ID` VARCHAR(36) DEFAULT NULL, ADD COLUMN `VER_NBR` BIGINT(20) DEFAULT 1;


INSERT INTO changelog (change_number, complete_dt, applied_by, description)
 VALUES (11, CURRENT_TIMESTAMP, USER(), '011-0.1.0-tk-alter.sql');

COMMIT;

-- END CHANGE SCRIPT #11: 011-0.1.0-tk-alter.sql


-- START CHANGE SCRIPT #12: 012-0.1.0-tk-adduserpref.sql

create table tk_user_pref_t (
  `PRINCIPAL_ID` varchar(40) NOT NULL,
  `TIME_ZONE` varchar(30) NULL,
    PRIMARY KEY (`PRINCIPAL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

#
# User Prefs data
#
delete from tk_user_pref_t;
insert Into tk_user_pref_t values('admin','pacific');


INSERT INTO changelog (change_number, complete_dt, applied_by, description)
 VALUES (12, CURRENT_TIMESTAMP, USER(), '012-0.1.0-tk-adduserpref.sql');

COMMIT;

-- END CHANGE SCRIPT #12: 012-0.1.0-tk-adduserpref.sql


-- START CHANGE SCRIPT #13: 013-0.1.0-tk-modifypaycal.sql

ALTER TABLE tk_py_calendar_t DROP COLUMN `begin_date` , 
DROP COLUMN `begin_time` , DROP COLUMN `chart` , DROP COLUMN `end_date` , DROP COLUMN `end_time` ;

alter table tk_py_calendar_dates_t rename tk_py_calendar_entries_t;
alter table tk_py_calendar_dates_s rename tk_py_calendar_entries_s;

ALTER TABLE tk_py_calendar_entries_t CHANGE COLUMN `tk_py_calendar_dates_id` `tk_py_calendar_entry_id` BIGINT(20) NOT NULL  
, DROP PRIMARY KEY , ADD PRIMARY KEY (`tk_py_calendar_entry_id`) ;


INSERT INTO changelog (change_number, complete_dt, applied_by, description)
 VALUES (13, CURRENT_TIMESTAMP, USER(), '013-0.1.0-tk-modifypaycal.sql');

COMMIT;

-- END CHANGE SCRIPT #13: 013-0.1.0-tk-modifypaycal.sql


-- START CHANGE SCRIPT #14: 014-0.1.0-tk-alterforweeklyovtmaint.sql

CREATE TABLE tk_weekly_ovt_group_t 
( `TK_WEEKLY_OVERTIME_GROUP_ID` BIGINT(20) NOT NULL,PRIMARY KEY (`TK_WEEKLY_OVERTIME_GROUP_ID`)) 
ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

insert into tk_weekly_ovt_group_t values(1);

ALTER TABLE `tk_weekly_overtime_rl_t` ADD COLUMN `TK_WEEKLY_OVT_GROUP_ID` BIGINT(20) NOT NULL DEFAULT 1  AFTER `ACTIVE` ;


INSERT INTO changelog (change_number, complete_dt, applied_by, description)
 VALUES (14, CURRENT_TIMESTAMP, USER(), '014-0.1.0-tk-alterforweeklyovtmaint.sql');

COMMIT;

-- END CHANGE SCRIPT #14: 014-0.1.0-tk-alterforweeklyovtmaint.sql


-- START CHANGE SCRIPT #15: 015-0.1.0-tk-alterdocumentheader.sql

ALTER TABLE `tk_document_header_t` CHANGE COLUMN `DOCUMENT_ID` `DOCUMENT_ID` VARCHAR(14) NOT NULL  ;


INSERT INTO changelog (change_number, complete_dt, applied_by, description)
 VALUES (15, CURRENT_TIMESTAMP, USER(), '015-0.1.0-tk-alterdocumentheader.sql');

COMMIT;

-- END CHANGE SCRIPT #15: 015-0.1.0-tk-alterdocumentheader.sql


-- START CHANGE SCRIPT #16: 016-0.1.0-tk-addhoursholiday.sql

ALTER TABLE `tk_holiday_calendar_dates_t` ADD COLUMN `HOLIDAY_HRS` DECIMAL(2,2) NOT NULL  AFTER `VER_NBR` ;


INSERT INTO changelog (change_number, complete_dt, applied_by, description)
 VALUES (16, CURRENT_TIMESTAMP, USER(), '016-0.1.0-tk-addhoursholiday.sql');

COMMIT;

-- END CHANGE SCRIPT #16: 016-0.1.0-tk-addhoursholiday.sql


-- START CHANGE SCRIPT #17: 017-0.1.0-tk-addprimary.sql

ALTER TABLE `hr_job_t` ADD COLUMN `primary_indicator` VARCHAR(1) DEFAULT 'N';


INSERT INTO changelog (change_number, complete_dt, applied_by, description)
 VALUES (17, CURRENT_TIMESTAMP, USER(), '017-0.1.0-tk-addprimary.sql');

COMMIT;

-- END CHANGE SCRIPT #17: 017-0.1.0-tk-addprimary.sql


-- START CHANGE SCRIPT #18: 018-0.1.0-tk-addIds.sql

ALTER TABLE `tk_roles_t` ADD COLUMN `tk_dept_id` BIGINT(20) NULL  AFTER `active`;
ALTER TABLE `tk_roles_t` ADD COLUMN `tk_work_area_id` BIGINT(20) NULL  AFTER `tk_dept_id`;



INSERT INTO changelog (change_number, complete_dt, applied_by, description)
 VALUES (18, CURRENT_TIMESTAMP, USER(), '018-0.1.0-tk-addIds.sql');

COMMIT;

-- END CHANGE SCRIPT #18: 018-0.1.0-tk-addIds.sql


-- START CHANGE SCRIPT #200: 200-0.1.0-tk-alter-tk_roles_t.sql

ALTER TABLE `tk`.`tk_roles_t` ADD COLUMN `tk_assignment_id` BIGINT NULL DEFAULT NULL  AFTER `user_principal_id` ;
ALTER TABLE `tk`.`tk_roles_t` ADD COLUMN `OBJ_ID` varchar(36) COLLATE utf8_bin NOT NULL;
ALTER TABLE `tk`.`tk_roles_t` ADD COLUMN `VER_NBR` bigint(20) NOT NULL DEFAULT '1';
  


INSERT INTO changelog (change_number, complete_dt, applied_by, description)
 VALUES (200, CURRENT_TIMESTAMP, USER(), '200-0.1.0-tk-alter-tk_roles_t.sql');

COMMIT;

-- END CHANGE SCRIPT #200: 200-0.1.0-tk-alter-tk_roles_t.sql


-- START CHANGE SCRIPT #201: 201-0.1.0-tk-alter-tk_assignment_t.sql

ALTER TABLE `tk_assignment_t`       ADD COLUMN `EARN_LINE`  BIGINT(10) DEFAULT NULL  AFTER `JOB_NUMBER` ;
ALTER TABLE `tk_assignment_t`       ADD COLUMN `START_DATE` DATE DEFAULT NULL AFTER `EARN_LINE` ;
ALTER TABLE `tk_assignment_t`       ADD COLUMN `END_DATE` DATE DEFAULT NULL AFTER `START_DATE` ;
ALTER TABLE `tk_assignment_t`       ADD COLUMN `RATE`     DECIMAL(6,2)  DEFAULT 0  AFTER `END_DATE` ;



INSERT INTO changelog (change_number, complete_dt, applied_by, description)
 VALUES (201, CURRENT_TIMESTAMP, USER(), '201-0.1.0-tk-alter-tk_assignment_t.sql');

COMMIT;

-- END CHANGE SCRIPT #201: 201-0.1.0-tk-alter-tk_assignment_t.sql


-- START CHANGE SCRIPT #202: 202-0.1.0-tk-alter-department.sql

ALTER TABLE `tk`.`tk_dept_t` CHANGE COLUMN `DESCRIPTION` `DESCRIPTION` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL  ;


INSERT INTO changelog (change_number, complete_dt, applied_by, description)
 VALUES (202, CURRENT_TIMESTAMP, USER(), '202-0.1.0-tk-alter-department.sql');

COMMIT;

-- END CHANGE SCRIPT #202: 202-0.1.0-tk-alter-department.sql


-- START CHANGE SCRIPT #900: 900-0.1.0-tk-add_principal_paytype_table.sql

DROP TABLE IF EXISTS `hr_principal_pay_type_t`;
create table hr_principal_pay_type_t (
	`hr_principal_pay_type_id` bigint(20) NOT NULL,
	`PRINCIPAL_ID` varchar(40) COLLATE utf8_bin DEFAULT NULL,
	`HR_PAYTYPE` varchar(5) COLLATE utf8_bin DEFAULT NULL,
	`OBJ_ID` varchar(36) COLLATE utf8_bin NOT NULL,
 	`VER_NBR` decimal(8,0) NOT NULL DEFAULT '1',
 	PRIMARY KEY (`hr_principal_pay_type_id`)
);

DROP TABLE IF EXISTS `hr_principal_pay_type_s`;
CREATE TABLE `hr_principal_pay_type_s` (
  `ID` bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=1101 DEFAULT CHARSET=latin1;


INSERT INTO changelog (change_number, complete_dt, applied_by, description)
 VALUES (900, CURRENT_TIMESTAMP, USER(), '900-0.1.0-tk-add_principal_paytype_table.sql');

COMMIT;

-- END CHANGE SCRIPT #900: 900-0.1.0-tk-add_principal_paytype_table.sql

