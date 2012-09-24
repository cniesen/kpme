delete from hr_calendar_entries_t where hr_calendar_entry_id = '5000';
delete from lm_leave_document_header_t where document_id >= '5000';
delete from lm_leave_block_t where lm_leave_block_id >='5000';
delete from lm_accrual_category_t where lm_accrual_category_id >= '5000';

insert into hr_calendar_entries_t (`hr_calendar_entry_id`,`hr_calendar_id`, `calendar_name`, `begin_period_date`, `end_period_date`, `initiate_date`, `initiate_time`, `end_pay_period_date`, `end_pay_period_time`, `employee_approval_date`, `employee_approval_time`,`supervisor_approval_date`,`supervisor_approval_time` ) values ('5000', '2', 'BWS-CAL', '2012-03-01 00:00:00', '2012-03-15 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

/* Leave Calnedar Document */
INSERT INTO lm_leave_document_header_t (`document_id`,`principal_id`,`begin_date`,`end_date`,`document_status`,`obj_id`,`ver_nbr`) values ('5000', 'admin', '2012-03-01 00:00:00','2012-03-15 00:00:00', 'F', '7EE387AB-26B0-B6A6-9C4C-5B5F687F0E97', '1');

/* Leave Block */
insert into lm_leave_block_t (`lm_leave_block_id`, `leave_date`,`description`,  `principal_id` , `earn_code` ,`hr_earn_code_id` ,`lm_sys_schd_timeoff_id` ,`lm_accrual_category_id` ,`leave_amount`, `apply_to_ytd_used`, `document_id`, `accrual_generated`, `block_id`,`ver_nbr`, `obj_id`, `timestamp`,`principal_id_modified`, `tk_assignment_id`, `request_status`, `leave_block_type`) values('5000', '2012-03-05', 'Send for Approval', 'admin', 'EC6', '5006', NULL, '5000', '8', null, null, 'N', '0','1','B2991ADA-E866-F28C-7E95-A897AC377D0C', now(), 'admin',  null, 'P', 'AS');

/* Accrual Category */
insert into lm_accrual_category_t (`lm_accrual_category_id`, `ACCRUAL_CATEGORY`, `LEAVE_PLAN`, `DESCR`, `ACCRUAL_INTERVAL_EARN`, `UNIT_OF_TIME`, `EFFDT`, `OBJ_ID`, `VER_NBR`, `PRORATION`, `DONATION`, `SHOW_ON_GRID`, `ACTIVE`, `TIMESTAMP`, `MIN_PERCENT_WORKED`, `EARN_CODE`, `HAS_RULES`) values('5000', 'testAC', 'testLP', 'test', 'M', '40', '2012-02-01', '8421CD29-E1F4-4B9A-AE33-F3F4752505CE', '1', null, null, 'Y', 'Y',now(), '0', 'EC', 'Y');