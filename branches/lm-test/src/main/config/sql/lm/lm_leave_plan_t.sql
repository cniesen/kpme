--
-- Table structure for table `lm_leave_plan_t`
--
DROP TABLE IF EXISTS `lm_leave_plan_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lm_leave_plan_t` (
  `LM_LEAVE_PLAN_ID` bigint(20) NOT NULL,
  `LEAVE_PLAN` varchar(15) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION` varchar(30) COLLATE utf8_bin DEFAULT NULL, 
  `EFFDT` date NOT NULL DEFAULT '0000-00-00',
  `SAL_GROUP` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `TIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `OBJ_ID` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `VER_NBR` bigint(20) NOT NULL DEFAULT '1',
  `location` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `unit_of_time` varchar(1) DEFAULT NULL,
  `active` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`LM_LEAVE_PLAN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `lm_leave_plan_s`
--

DROP TABLE IF EXISTS `lm_leave_plan_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lm_leave_plan_s` (
  `ID` bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=1101 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `lm_leave_accrual_category_t`
--
DROP TABLE IF EXISTS `lm_leave_accrual_category_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lm_leave_accrual_category_t` (
  `lm_leave_accrual_category_id` bigint(20) NOT NULL,
  `accrual_category` varchar(15) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION` varchar(30) COLLATE utf8_bin DEFAULT NULL, 
  `EFFDT` date NOT NULL DEFAULT '0000-00-00',
  `leave_plan` varchar(15) COLLATE utf8_bin DEFAULT NULL,
  `accrual_earn_interal` varchar(1) DEFAULT NULL,
  `TIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `OBJ_ID` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `VER_NBR` bigint(20) NOT NULL DEFAULT '1',
  `proration` varchar(1) DEFAULT NULL,
  `show_on_grid` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`lm_leave_accrual_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `lm_leave_accrual_category_s`
--

DROP TABLE IF EXISTS `lm_leave_accrual_category_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lm_leave_accrual_category_s` (
  `ID` bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=1101 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `lm_leave_accrual_category_rl_t`
--
DROP TABLE IF EXISTS `lm_leave_accrual_category_lr_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lm_leave_accrual_category_rl_t` (
  `lm_leave_accrual_category_rl_id` bigint(20) NOT NULL,
  `leave_accrual_category` varchar(15) NOT NULL,
  `service_unit_of_time` varchar(1) DEFAULT NULL,
  `rl_start` integer(11) NOT NULL,
  `rl_end` integer(11) NOT NULL,
  `accrual_rate` integer(11) NOT NULL,
  `max_balance` integer(11) NOT NULL,
  `action_at_max_balance` varchar(1) NOT NULL,
  `max_balance_transfer_to_accrl_category` varchar(15) COLLATE utf8_bin DEFAULT NULL,
  `max_balance_transfer_factor` decimal(3,2) DEFAULT NULL,
  `max_transfer_amount` integer(11) DEFAULT NULL,
  `max_payout_amount` integer(11) DEFAULT NULL,
  `max_payout_leave_code` varchar(15) DEFAULT NULL,
  `max_usage` integer(11) DEFAULT NULL,
  `max_carry_over` integer(11) DEFAULT NULL,
  `TIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `OBJ_ID` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `VER_NBR` bigint(20) NOT NULL DEFAULT '1',
  PRIMARY KEY (`lm_leave_accrual_category_rl_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `lm_leave_accrual_category_rl_s`
--

DROP TABLE IF EXISTS `lm_leave_accrual_category_rl_s`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lm_leave_accrual_category_rl_s` (
  `ID` bigint(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=1101 DEFAULT CHARSET=latin1;
