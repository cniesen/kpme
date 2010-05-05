--
-- Table structure for table `tk_clock_location_rl_t`
--

DROP TABLE IF EXISTS `tk_clock_location_rl_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tk_clock_location_rl_t` (
  `DEPTID` varchar(7) COLLATE utf8_bin DEFAULT NULL,
  `WORK_AREA_ID` decimal(10,0) DEFAULT NULL,
  `PRINCIPALID` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `JOB_NUMBER` decimal(3,0) DEFAULT NULL,
  `EFFDT` date DEFAULT NULL,
  `EFFSEQ` decimal(3,0) DEFAULT NULL,
  `EFF_STATUS` bit(1) DEFAULT NULL,
  `IP_ADDRESS` varchar(15) COLLATE utf8_bin DEFAULT NULL,
  `USER_PRINCIPALID` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `TIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;