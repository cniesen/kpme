--
-- MySql
--
USE TK;
--
DROP TABLE IF EXISTS `t2_leave_accrual_rl_s`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `t2_leave_accrual_rl_s` (
  `ID` BIGINT(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=1000 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;
LOCK TABLES `t2_leave_accrual_rl_s` WRITE;
INSERT INTO `t2_leave_accrual_rl_s` VALUES 
('1001'),
('1002');
UNLOCK TABLES;
DROP TABLE IF EXISTS `t2_leave_accrual_rl_t`;
CREATE TABLE `t2_leave_accrual_rl_t` (
`LEAVE_ACCRUAL_ID` BIGINT(19),
`DESCRIPTION` VARCHAR(100) NOT NULL DEFAULT '',
`ACCRUAL_TYPE_ID` BIGINT(19),
`EMPL_TYPE_ID` BIGINT(19),
`LEAVE_TYPE_ID` BIGINT(19),
`JOB_ID` BIGINT(19),
`UNION_ID` BIGINT(19),
`ACCRUAL_FREQ_ID` BIGINT(19),
`PAY_TYPE_ID` BIGINT(19),
`WORK_SCHEDULE_ID` BIGINT(19),
`PRORATE_PCT_IND` VARCHAR(1) NOT NULL DEFAULT '',
`BENE_ELIG_IND` VARCHAR(1) NOT NULL DEFAULT '',
`CHK_JOB_ELIG_IND` VARCHAR(1) NOT NULL DEFAULT '',
`CHK_EMPL_STATUS_IND` VARCHAR(1) NOT NULL DEFAULT '',
`FACULTY_IND` VARCHAR(1) NOT NULL DEFAULT '',
`STAFF_IND` VARCHAR(1) NOT NULL DEFAULT '',
`STUDENT_IND` VARCHAR(1) NOT NULL DEFAULT '',
`CHK_HRS_BEFORE_ACCRUAL_IND` VARCHAR(1) NOT NULL DEFAULT '',
`HRS_BEFORE_ACCRUAL` DECIMAL(10,2),
`PRORATE_HRS_IN_PERIOD_IND` VARCHAR(1) NOT NULL DEFAULT '',
`CHK_WORK_SCHEDULE_IND` VARCHAR(1) NOT NULL DEFAULT '',
`HRS_MINIMUM_PER` DECIMAL(10,2) NOT NULL DEFAULT '0',
`HRS_MAXIMUM_PER` DECIMAL(10,2) NOT NULL DEFAULT '0',
`HRS_MINIMUM_YR` DECIMAL(10,2) NOT NULL DEFAULT '0',
`HRS_MAXIMUM_YR` DECIMAL(10,2) NOT NULL DEFAULT '0',
`HRS_MAXIMUM_TOT` DECIMAL(10,2) NOT NULL DEFAULT '0',
`ALLOW_USE_IND` VARCHAR(1) NOT NULL DEFAULT '',
`ALLOW_ACCRUE_IND` VARCHAR(1) NOT NULL DEFAULT '',
`ALLOW_SCHEDULE_IND` VARCHAR(1) NOT NULL DEFAULT '',
`KEEP_BALANCE_IND` VARCHAR(1) NOT NULL DEFAULT '',
`ACCRUAL_RATE_HRS` DECIMAL(10,2) NOT NULL DEFAULT '0',
`SERVICE_BEG_HRS` DECIMAL(10,2) NOT NULL DEFAULT '0',
`SERVICE_END_HRS` DECIMAL(10,2) NOT NULL DEFAULT '0',
`EFFDT` DATE NOT NULL,
`ACTIVE_IND` VARCHAR(1) NOT NULL DEFAULT '',
`TIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
`OBJ_ID` VARCHAR(36) NOT NULL,
`VER_NBR` DECIMAL(8,0) NOT NULL DEFAULT '1',
PRIMARY KEY (`LEAVE_ACCRUAL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
--
--
LOCK TABLES `t2_leave_accrual_rl_t` WRITE;
INSERT INTO `t2_leave_accrual_rl_t` VALUES 
('1001','Rule Table Entry 1','1002','1002','1001','JOB1','UNION1', 'ACCFREQ1','PAYTYPE1','WORKSCHED1','Y','Y','Y','Y','N','Y','N','Y','8.00','Y','Y','8.00','24.00','208.00','48.00','624.00','Y','Y','Y','Y','24.00','8.00','8.00','2010-05-23','Y','2010-05-26 00:00:00','C6BE6D27517648749BCE13B30859B413','1' ),
('1002','Rule Table Entry 2','1001','1001','1002','JOB2','UNION2', 'ACCFREQ2','PAYTYPE2','WORKSCHED2','Y','Y','Y','Y','N','Y','N','Y','8.00','Y','Y','8.00','24.00','208.00','48.00','624.00','Y','Y','Y','Y','24.00','8.00','8.00','2010-05-23','Y','2010-05-26 00:00:00','A6BE6D27517648749BCE13B30859B413','1' );
UNLOCK TABLES;
--
--
DROP TABLE IF EXISTS `t2_accrual_type_s`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `t2_accrual_type_s` (
  `ID` BIGINT(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=1000 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;
LOCK TABLES `t2_accrual_type_s` WRITE;
INSERT INTO `t2_accrual_type_s` VALUES 
('1001'),
('1002');
UNLOCK TABLES;
DROP TABLE IF EXISTS `t2_accrual_type_t`;
CREATE TABLE `t2_accrual_type_t` (
  `ACCRUAL_TYPE_ID` BIGINT(19) NOT NULL default '0',
  `ACCRUAL_TYPE_CODE` VARCHAR(10) NOT NULL default '',
  `DESCRIPTION` varchar(100) collate utf8_bin default NULL,
  `ACTIVE_IND` VARCHAR(1) NOT NULL DEFAULT '',
  `TIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `OBJ_ID` varchar(36) collate utf8_bin NOT NULL,
  `VER_NBR` decimal(8,0) NOT NULL default '1',
  PRIMARY KEY  (`ACCRUAL_TYPE_ID`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
LOCK TABLES `t2_accrual_type_t` WRITE;
INSERT INTO `t2_accrual_type_t` VALUES 
('1001','ACC1','Accrual Type 1','Y','2010-05-26 00:00:00','1','1'),
('1002','ACC2','Accrual Type 2','Y','2010-05-26 00:00:00','1','1');
UNLOCK TABLES;
--
--
DROP TABLE IF EXISTS `t2_empl_type_s`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `t2_empl_type_s` (
  `ID` BIGINT(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=1000 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;
LOCK TABLES `t2_empl_type_s` WRITE;
INSERT INTO `t2_empl_type_s` VALUES 
('1001'),
('1002');
UNLOCK TABLES;
DROP TABLE IF EXISTS `t2_empl_type_t`;
CREATE TABLE `t2_empl_type_t` (
  `EMPL_TYPE_ID` BIGINT(19) NOT NULL default '0',
  `EMPL_TYPE_CODE` VARCHAR(10) NOT NULL default '',
  `DESCRIPTION` varchar(100) collate utf8_bin default NULL,
  `ACTIVE_IND` VARCHAR(1) NOT NULL DEFAULT '',
  `TIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `OBJ_ID` varchar(36) collate utf8_bin NOT NULL,
  `VER_NBR` decimal(8,0) NOT NULL default '1',
  PRIMARY KEY  (`EMPL_TYPE_ID`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
LOCK TABLES `t2_empl_type_t` WRITE;
INSERT INTO `t2_empl_type_t` VALUES 
('1001','EMP1','Employee Type 1','Y','2010-05-26 00:00:00','1','1'),
('1002','EMP2','Employee Type 2','Y','2010-05-26 00:00:00','1','1');
UNLOCK TABLES;
--
--
DROP TABLE IF EXISTS `t2_leave_type_s`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `t2_leave_type_s` (
  `ID` BIGINT(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=1000 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;
LOCK TABLES `t2_leave_type_s` WRITE;
INSERT INTO `t2_leave_type_s` VALUES 
('1001'),
('1002');
UNLOCK TABLES;
DROP TABLE IF EXISTS `t2_leave_type_t`;
CREATE TABLE `t2_leave_type_t` (
  `LEAVE_TYPE_ID` BIGINT(19) NOT NULL default '0',
  `LEAVE_TYPE_CODE` VARCHAR(10) NOT NULL default '',  
  `DESCRIPTION` varchar(100) collate utf8_bin default NULL,
  `ACTIVE_IND` VARCHAR(1) NOT NULL DEFAULT '',
  `TIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `OBJ_ID` varchar(36) collate utf8_bin NOT NULL,
  `VER_NBR` decimal(8,0) NOT NULL default '1',
  PRIMARY KEY  (`LEAVE_TYPE_ID`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
LOCK TABLES `t2_leave_type_t` WRITE;
INSERT INTO `t2_leave_type_t` VALUES 
('1001','LEAVE1','Leave Type 1','Y','2010-05-26 00:00:00','1','1'),
('1002','LEAVE2','Leave Type 2','Y','2010-05-26 00:00:00','1','1');
UNLOCK TABLES;
--
--
DROP TABLE IF EXISTS `t2_job_s`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `t2_job_s` (
  `ID` BIGINT(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=1000 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;
LOCK TABLES `t2_job_s` WRITE;
INSERT INTO `t2_job_s` VALUES 
('1001'),
('1002');
UNLOCK TABLES;
DROP TABLE IF EXISTS `t2_job_t`;
CREATE TABLE `t2_job_t` (
  `JOB_ID` BIGINT(19) NOT NULL default '0',
  `JOB_CODE` VARCHAR(10) NOT NULL default '',
  `DESCRIPTION` varchar(100) collate utf8_bin default NULL,
  `ACTIVE_IND` VARCHAR(1) NOT NULL DEFAULT '',
  `TIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `OBJ_ID` varchar(36) collate utf8_bin NOT NULL,
  `VER_NBR` decimal(8,0) NOT NULL default '1',
  PRIMARY KEY  (`JOB_ID`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
LOCK TABLES `t2_job_t` WRITE;
INSERT INTO `t2_job_t` VALUES 
('1001','JOB1','Job Type 1','Y','2010-05-26 00:00:00','1','1'),
('1002','JOB2','Job Type 2','Y','2010-05-26 00:00:00','1','1');
UNLOCK TABLES;
--
--
DROP TABLE IF EXISTS `t2_union_s`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `t2_union_s` (
  `ID` BIGINT(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=1000 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;
LOCK TABLES `t2_union_s` WRITE;
INSERT INTO `t2_union_s` VALUES 
('1001'),
('1002');
UNLOCK TABLES;
DROP TABLE IF EXISTS `t2_union_t`;
CREATE TABLE `t2_union_t` (
  `UNION_ID` BIGINT(19) NOT NULL default '0',
  `UNION_CODE` VARCHAR(10) NOT NULL default '',
  `DESCRIPTION` varchar(100) collate utf8_bin default NULL,
  `ACTIVE_IND` VARCHAR(1) NOT NULL DEFAULT '',
  `TIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `OBJ_ID` varchar(36) collate utf8_bin NOT NULL,
  `VER_NBR` decimal(8,0) NOT NULL default '1',
  PRIMARY KEY  (`UNION_ID`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
LOCK TABLES `t2_union_t` WRITE;
INSERT INTO `t2_union_t` VALUES 
('1001','UNION1','Union Type 1','Y','2010-05-26 00:00:00','1','1'),
('1002','UNION2','Union Type 2','Y','2010-05-26 00:00:00','1','1');
UNLOCK TABLES;
--
--
DROP TABLE IF EXISTS `t2_accrual_freq_s`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `t2_accrual_freq_s` (
  `ID` BIGINT(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=1000 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;
LOCK TABLES `t2_accrual_freq_s` WRITE;
INSERT INTO `t2_accrual_freq_s` VALUES 
('1001'),
('1002');
UNLOCK TABLES;
DROP TABLE IF EXISTS `t2_accrual_freq_t`;
CREATE TABLE `t2_accrual_freq_t` (
  `ACCRUAL_FREQ_ID` BIGINT(19) NOT NULL default '0',
  `ACCRUAL_FREQ_CODE` VARCHAR(10) NOT NULL default '',
  `DESCRIPTION` varchar(100) collate utf8_bin default NULL,
  `ACTIVE_IND` VARCHAR(1) NOT NULL DEFAULT '',
  `TIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `OBJ_ID` varchar(36) collate utf8_bin NOT NULL,
  `VER_NBR` decimal(8,0) NOT NULL default '1',
  PRIMARY KEY  (`ACCRUAL_FREQ_ID`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
LOCK TABLES `t2_accrual_freq_t` WRITE;
INSERT INTO `t2_accrual_freq_t` VALUES 
('1001','ACCFREQ1','Accrual Frequency Type 1','Y','2010-05-26 00:00:00','1','1'),
('1002','ACCFREQ2','Accrual Frequency Type 2','Y','2010-05-26 00:00:00','1','1');
UNLOCK TABLES;
--
-- 
DROP TABLE IF EXISTS `t2_pay_type_s`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `t2_pay_type_s` (
  `ID` BIGINT(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=1000 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;
LOCK TABLES `t2_pay_type_s` WRITE;
INSERT INTO `t2_pay_type_s` VALUES 
('1001'),
('1002');
UNLOCK TABLES;
DROP TABLE IF EXISTS `t2_pay_type_t`;
CREATE TABLE `t2_pay_type_t` (
  `PAY_TYPE_ID` BIGINT(19) NOT NULL default '0',
  `PAY_TYPE_CODE` VARCHAR(10) NOT NULL default '',
  `DESCRIPTION` varchar(100) collate utf8_bin default NULL,
  `ACTIVE_IND` VARCHAR(1) NOT NULL DEFAULT '',
  `TIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `OBJ_ID` varchar(36) collate utf8_bin NOT NULL,
  `VER_NBR` decimal(8,0) NOT NULL default '1',
  PRIMARY KEY  (`PAY_TYPE_ID`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
LOCK TABLES `t2_pay_type_t` WRITE;
INSERT INTO `t2_pay_type_t` VALUES 
('1001','PAYTYPE1','Pay Type 1','Y','2010-05-26 00:00:00','1','1'),
('1002','PAYTYPE2','Pay Type 2','Y','2010-05-26 00:00:00','1','1');
UNLOCK TABLES;
--
--
DROP TABLE IF EXISTS `t2_work_schedule_s`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `t2_work_schedule_s` (
  `ID` BIGINT(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=1000 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;
LOCK TABLES `t2_work_schedule_s` WRITE;
INSERT INTO `t2_work_schedule_s` VALUES 
('1001'),
('1002');
UNLOCK TABLES;
DROP TABLE IF EXISTS `t2_work_schedule_t`;
CREATE TABLE `t2_work_schedule_t` (
  `WORK_SCHEDULE_ID` VARCHAR(10) NOT NULL default '',
  `WORK_SCHEDULE_CODE` VARCHAR(10) NOT NULL default '',
  `DESCRIPTION` varchar(100) collate utf8_bin default NULL,
  `ACTIVE_IND` VARCHAR(1) NOT NULL DEFAULT '',
  `TIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `OBJ_ID` varchar(36) collate utf8_bin NOT NULL,
  `VER_NBR` decimal(8,0) NOT NULL default '1',
  PRIMARY KEY  (`WORK_SCHEDULE_ID`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
LOCK TABLES `t2_work_schedule_t` WRITE;
INSERT INTO `t2_work_schedule_t` VALUES 
('1001','WORKSCHED1','Work Schedule Type 1','Y','2010-05-26 00:00:00','1','1'),
('1002','WORKSCHED2','Work Schedule Type 2','Y','2010-05-26 00:00:00','1','1');
UNLOCK TABLES;
