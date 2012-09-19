-- TK tables
ALTER TABLE `tk`.`tk_time_block_t` CHANGE COLUMN `EARN_CODE` `EARN_CODE` VARCHAR(15) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL  ;

ALTER TABLE `tk`.`tk_time_block_hist_t` CHANGE COLUMN `EARN_CODE` `EARN_CODE` VARCHAR(15) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL  ;

ALTER TABLE `tk`.`tk_time_block_hist_detail_t` CHANGE COLUMN `EARN_CODE` `EARN_CODE` VARCHAR(15) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL  ;

ALTER TABLE `tk`.`tk_work_area_t` CHANGE COLUMN `DEFAULT_OVERTIME_EARNCODE` `DEFAULT_OVERTIME_EARNCODE` VARCHAR(15) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL  ;

ALTER TABLE `tk`.`hr_job_t` ADD COLUMN `flsa_status` VARCHAR(15) NULL  AFTER `active` ;

-- ALTER TABLE `tk`.`tk_time_sheet_init_t` CHANGE COLUMN `py_calendar_entries_id` `hr_calendar_entry_id` VARCHAR(60) NOT NULL DEFAULT '';

-- ALTER TABLE `tk`.`tk_time_sheet_init_t` CHANGE COLUMN `py_calendar_group` `calendar_name` VARCHAR(30) NOT NULL DEFAULT '';

ALTER TABLE `tk`.`hr_earn_code_t` CHANGE COLUMN `EARN_CODE` `EARN_CODE` VARCHAR(15) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL  ;

ALTER TABLE `tk`.`hr_earn_code_t` DROP COLUMN `record_amount` ;

ALTER TABLE `tk`.`hr_earn_code_t` DROP COLUMN `record_time` ;

ALTER TABLE `tk`.`hr_earn_code_t` DROP COLUMN `record_hours` ;

ALTER TABLE `tk`.`hr_earn_code_t` ADD COLUMN `recordMethod` VARCHAR(5) NULL  AFTER `timestamp` ;

ALTER TABLE `tk`.`hr_paytype_t` CHANGE COLUMN `reg_ern_code` `reg_ern_code` VARCHAR(15) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ;

ALTER TABLE `tk`.`lm_accruals_t` CHANGE COLUMN `accrual_category` `accrual_category` VARCHAR(15) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ;

ALTER TABLE `tk`.`tk_assign_acct_t` CHANGE COLUMN `earn_code` `earn_code` VARCHAR(15) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ;

ALTER TABLE `tk`.`tk_shift_differential_rl_t` CHANGE COLUMN `earn_code` `earn_code` VARCHAR(15) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ;

ALTER TABLE `tk`.`tk_shift_differential_rl_t` CHANGE COLUMN `PY_CALENDAR_GROUP` `calendar_name` VARCHAR(30) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL  ;

ALTER TABLE `tk`.`tk_weekly_overtime_rl_t` CHANGE COLUMN `CONVERT_TO_ERNCD` `CONVERT_TO_ERNCD` VARCHAR(15) CHARACTER SET 'utf8' COLLATE 'utf8_bin';

ALTER TABLE `tk`.`hr_dept_earn_code_t` RENAME TO  `tk`.`hr_earn_code_security_t` ;

ALTER TABLE `tk`.`hr_earn_code_security_t` ADD COLUMN `earn_code_type` VARCHAR(1) NOT NULL  AFTER `earn_code` ;

ALTER TABLE `tk`.`hr_earn_code_security_t` CHANGE COLUMN `earn_code` `earn_code` VARCHAR(15) CHARACTER SET 'utf8' COLLATE 'utf8_bin';

ALTER TABLE `tk`.`hr_earn_code_security_t` CHANGE COLUMN `hr_dept_earn_code_id` `hr_earn_code_security_id` VARCHAR(60) CHARACTER SET 'utf8' COLLATE 'utf8_bin';

ALTER TABLE `tk`.`tk_daily_overtime_rl_t` CHANGE COLUMN `earn_code` `earn_code` VARCHAR(15) CHARACTER SET 'utf8' COLLATE 'utf8_bin';

ALTER TABLE `tk`.`tk_hour_detail_t` CHANGE COLUMN `earn_code` `earn_code` VARCHAR(15) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL;

DROP TABLE IF EXISTS `hr_principal_calendar_t`;

-- CREATE TABLE `hr_principal_calendar_t` (
--   `hr_principal_attribute_id` varchar(60) COLLATE utf8_bin NOT NULL DEFAULT '',
--   `principal_id` varchar(40) COLLATE utf8_bin NOT NULL DEFAULT '',
--   `pay_calendar` varchar(15) COLLATE utf8_bin NOT NULL DEFAULT '',
--   `timezone` varchar(10) COLLATE utf8_bin DEFAULT NULL,
--   `EFFDT` date NOT NULL DEFAULT '0000-00-00',
--   `service_date` date NOT NULL DEFAULT '0000-00-00',
--   `TIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
--   `active` varchar(1) COLLATE utf8_bin NOT NULL DEFAULT 'Y',
--   `fmla_eligible` varchar(1),
--   `workmansCompEligible` varchar(1),
--   `OBJ_ID` varchar(36) COLLATE utf8_bin NOT NULL,
--   `VER_NBR` decimal(8,0) NOT NULL DEFAULT '1',
--   `holiday_calendar_group` varchar(3) COLLATE utf8_bin DEFAULT NULL,
--   PRIMARY KEY (`hr_principal_attribute_id`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

DROP TABLE IF EXISTS `hr_principal_attributes_t`;

CREATE  TABLE `tk`.`hr_principal_attributes_t` (
  `hr_principal_attribute_id` VARCHAR(60) NOT NULL ,
  `principal_id` VARCHAR(40) NOT NULL ,
  `pay_calendar` VARCHAR(15) NULL ,
  `timezone` VARCHAR(10) NULL ,
  `EFFDT` DATE NULL ,
  `service_date` DATE NOT NULL ,
  `TIMESTAMP` TIMESTAMP NOT NULL ,
  `active` VARCHAR(1) NOT NULL ,
  `fmla_eligible` VARCHAR(1) NULL ,
  `worksman_eligible` VARCHAR(1) NULL ,
  `holiday_calendar_group` VARCHAR(3) NULL ,
  `OBJ_ID` VARCHAR(36) NULL ,
  PRIMARY KEY (`hr_principal_attribute_id`) );

ALTER TABLE `tk`.`hr_principal_attributes_t` ADD COLUMN `ver_nbr` BIGINT NULL  AFTER `OBJ_ID` 
, ADD INDEX `OBJ_ID` (`OBJ_ID` ASC) ;

-- ALTER TABLE `tk`.`hr_earn_code_security_t` ADD COLUMN `sal_group` VARCHAR(20) ;

-- ALTER TABLE `tk`.`tk_batch_job_t` CHANGE COLUMN `hr_py_calendar_entry_id` `hr_calendar_entry_id` VARCHAR(15) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL;

-- ALTER TABLE `tk`.`tk_batch_job_entry_t` CHANGE COLUMN `HR_PY_CALENDAR_ENTRY_ID` `HR_CALENDAR_ENTRY_ID` VARCHAR(60) NOT NULL  ;

ALTER TABLE `tk`.`hr_position_t` ADD COLUMN `WORK_AREA` BIGINT AFTER `TIMESTAMP` ;

DROP TABLE IF EXISTS `tk`.`hr_calendar_entries_t`;

CREATE TABLE `hr_calendar_entries_t` (
  `hr_calendar_entry_id` VARCHAR(60) NOT NULL,
  `hr_calendar_id` varchar(60),
  `calendar_name` varchar(30),
  `begin_period_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `end_period_date` timestamp NOT NULL DEFAULT 0,
  `initiate_date` date NOT NULL DEFAULT '0000-00-00',
  `initiate_time` time NOT NULL DEFAULT '00:00:00',
  `end_pay_period_date` date NOT NULL DEFAULT '0000-00-00',
  `end_pay_period_time` time NOT NULL DEFAULT '00:00:00',
  `employee_approval_date` date NOT NULL DEFAULT '0000-00-00',
  `employee_approval_time` time NOT NULL DEFAULT '00:00:00',
  `supervisor_approval_date` date NOT NULL DEFAULT '0000-00-00',
  `supervisor_approval_time` time NOT NULL DEFAULT '00:00:00',
  PRIMARY KEY (`hr_calendar_entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

DROP TABLE IF EXISTS `tk`.`hr_calendar_t`;

CREATE TABLE `hr_calendar_t` (
  `hr_calendar_id` varchar(60) NOT NULL,
  `calendar_name` varchar(30),
  `calendar_descriptions` varchar(50),
  `flsa_begin_day` varchar(9),
  `flsa_begin_time` time,
  `calendar_types` varchar(9),
  PRIMARY KEY (`hr_calendar_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- DROP tables that are not in use anymore.

drop table `tk`.`hr_py_calendar_entries_t`;

drop table `tk`.`hr_py_calendar_t`;

alter table `hr_pay_grade_t`ADD COLUMN `sal_group` VARCHAR(20);

alter table `tk_time_sheet_init_t` change `calendar_group` `PY_CALENDAR_GROUP` varchar(30);