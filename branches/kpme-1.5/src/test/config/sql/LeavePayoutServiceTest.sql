--
-- Copyright 2004-2014 The Kuali Foundation
--
-- Licensed under the Educational Community License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
--
-- http://www.opensource.org/licenses/ecl2.php
--
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--

delete from lm_accrual_category_rules_t where lm_accrual_category_rules_id >= 5000;
delete from lm_leave_plan_t where lm_leave_plan_id >= 80000;
delete from hr_principal_attributes_t where hr_principal_attribute_id >= 5000;
delete from lm_accrual_category_t where lm_accrual_category_id >= 5000;
delete from hr_job_t where hr_job_id >= 5000;
delete from lm_leave_document_header_t where document_id >= 5000;
delete from hr_earn_code_t where hr_earn_code_id >= 5000;
delete from hr_calendar_entries_t where hr_calendar_entry_id >= 5000;
delete from lm_leave_block_t where lm_leave_block_id >= 39430;
delete from lm_employee_override_t where lm_employee_override_id >= 3000;
delete from tk_document_header_t where document_id >= 5000;

insert into lm_employee_override_t values ('3000', 'testUser1', 'ye-xfer-eo', 'testLP', 'MB', 15, NULL,'Y', now(), uuid(), '1', '2011-02-03 12:10:23');
insert into lm_employee_override_t values ('3001', 'testUser1', 'ye-xfer-eo', 'testLP', 'MAC', 5, NULL,'Y', now(), uuid(), '1', '2011-02-03 12:10:23');
insert into lm_employee_override_t values ('3002', 'testUser1', 'ye-xfer-eo', 'testLP', 'MPA', 7, NULL,'Y', now(), uuid(), '1', '2011-02-03 12:10:23');

insert into lm_employee_override_t values ('3003', 'testUser2', 'ye-xfer-eo', 'testLP', 'MB', 15, NULL,'Y', now(), uuid(), '1', '2011-02-03 12:10:23');
insert into lm_employee_override_t values ('3004', 'testUser2', 'ye-xfer-eo', 'testLP', 'MAC', 5, NULL,'Y', now(), uuid(), '1', '2011-02-03 12:10:23');
insert into lm_employee_override_t values ('3005', 'testUser2', 'ye-xfer-eo', 'testLP', 'MPA', 7, NULL,'Y', now(), uuid(), '1', '2011-02-03 12:10:23');

insert into lm_accrual_category_rules_t (`lm_accrual_category_rules_id`, `SERVICE_UNIT_OF_TIME`, `START_ACC`, `END_ACC`, `ACCRUAL_RATE`, `MAX_BAL`, `MAX_BAL_ACTION_FREQUENCY`, `ACTION_AT_MAX_BAL`, `MAX_BAL_TRANS_ACC_CAT`, `MAX_BAL_TRANS_CONV_FACTOR`, `MAX_TRANS_AMOUNT`, `MAX_PAYOUT_AMOUNT`, `MAX_PAYOUT_EARN_CODE`, `MAX_USAGE`, `MAX_CARRY_OVER`, `LM_ACCRUAL_CATEGORY_ID`, `OBJ_ID`, `VER_NBR`, `ACTIVE`, `TIMESTAMP`, `MAX_BAL_FLAG`) values ('5000', 'M', 0, 888, 16, 15.00, 'OD', 'P', NULL, NULL, NULL, 10.00, 'EC1', NULL, NULL, '5000', 'DEDC243D-4E51-CCDE-1326-E1700B2631E1', '1', 'Y', '2011-02-03 12:10:23', 'Y');
insert into lm_accrual_category_rules_t (`lm_accrual_category_rules_id`, `SERVICE_UNIT_OF_TIME`, `START_ACC`, `END_ACC`, `ACCRUAL_RATE`, `MAX_BAL`, `MAX_BAL_ACTION_FREQUENCY`, `ACTION_AT_MAX_BAL`, `MAX_BAL_TRANS_ACC_CAT`, `MAX_BAL_TRANS_CONV_FACTOR`, `MAX_TRANS_AMOUNT`, `MAX_PAYOUT_AMOUNT`, `MAX_PAYOUT_EARN_CODE`, `MAX_USAGE`, `MAX_CARRY_OVER`, `LM_ACCRUAL_CATEGORY_ID`, `OBJ_ID`, `VER_NBR`, `ACTIVE`, `TIMESTAMP`, `MAX_BAL_FLAG`) values ('5001', 'M', 0, 888, 16, 15.00, 'YE', 'P', NULL, NULL, NULL, 10.00, 'EC2', NULL, NULL, '5001', 'DEDC243D-4E51-CCDE-1326-E1700B2631E1', '1', 'Y', '2011-02-03 12:10:23', 'Y');
insert into lm_accrual_category_rules_t (`lm_accrual_category_rules_id`, `SERVICE_UNIT_OF_TIME`, `START_ACC`, `END_ACC`, `ACCRUAL_RATE`, `MAX_BAL`, `MAX_BAL_ACTION_FREQUENCY`, `ACTION_AT_MAX_BAL`, `MAX_BAL_TRANS_ACC_CAT`, `MAX_BAL_TRANS_CONV_FACTOR`, `MAX_TRANS_AMOUNT`, `MAX_PAYOUT_AMOUNT`, `MAX_PAYOUT_EARN_CODE`, `MAX_USAGE`, `MAX_CARRY_OVER`, `LM_ACCRUAL_CATEGORY_ID`, `OBJ_ID`, `VER_NBR`, `ACTIVE`, `TIMESTAMP`, `MAX_BAL_FLAG`) values ('5002', 'M', 0, 888, 16, 15.00, 'LA', 'P', NULL, NULL, NULL, 10.00, 'EC3', NULL, NULL, '5002', 'DEDC243D-4E51-CCDE-1326-E1700B2631E1', '1', 'Y', '2011-02-03 12:10:23', 'Y');
insert into lm_accrual_category_rules_t (`lm_accrual_category_rules_id`, `SERVICE_UNIT_OF_TIME`, `START_ACC`, `END_ACC`, `ACCRUAL_RATE`, `MAX_BAL`, `MAX_BAL_ACTION_FREQUENCY`, `ACTION_AT_MAX_BAL`, `MAX_BAL_TRANS_ACC_CAT`, `MAX_BAL_TRANS_CONV_FACTOR`, `MAX_TRANS_AMOUNT`, `MAX_PAYOUT_AMOUNT`, `MAX_PAYOUT_EARN_CODE`, `MAX_USAGE`, `MAX_CARRY_OVER`, `LM_ACCRUAL_CATEGORY_ID`, `OBJ_ID`, `VER_NBR`, `ACTIVE`, `TIMESTAMP`, `MAX_BAL_FLAG`) values ('5003', 'M', 0, 888, 16, 15.00, 'OD', 'P', NULL, NULL, NULL, 10.00, 'EC4', NULL, 10.00, '5003', 'DEDC243D-4E51-CCDE-1326-E1700B2631E1', '1', 'Y', '2011-02-03 12:10:23', 'Y');
insert into lm_accrual_category_rules_t (`lm_accrual_category_rules_id`, `SERVICE_UNIT_OF_TIME`, `START_ACC`, `END_ACC`, `ACCRUAL_RATE`, `MAX_BAL`, `MAX_BAL_ACTION_FREQUENCY`, `ACTION_AT_MAX_BAL`, `MAX_BAL_TRANS_ACC_CAT`, `MAX_BAL_TRANS_CONV_FACTOR`, `MAX_TRANS_AMOUNT`, `MAX_PAYOUT_AMOUNT`, `MAX_PAYOUT_EARN_CODE`, `MAX_USAGE`, `MAX_CARRY_OVER`, `LM_ACCRUAL_CATEGORY_ID`, `OBJ_ID`, `VER_NBR`, `ACTIVE`, `TIMESTAMP`, `MAX_BAL_FLAG`) values ('5004', 'M', 0, 888, 16, 15.00, 'YE', 'P', NULL, NULL, NULL, 10.00, 'EC5', NULL, 10.00, '5004', 'DEDC243D-4E51-CCDE-1326-E1700B2631E1', '1', 'Y', '2011-02-03 12:10:23', 'Y');
insert into lm_accrual_category_rules_t (`lm_accrual_category_rules_id`, `SERVICE_UNIT_OF_TIME`, `START_ACC`, `END_ACC`, `ACCRUAL_RATE`, `MAX_BAL`, `MAX_BAL_ACTION_FREQUENCY`, `ACTION_AT_MAX_BAL`, `MAX_BAL_TRANS_ACC_CAT`, `MAX_BAL_TRANS_CONV_FACTOR`, `MAX_TRANS_AMOUNT`, `MAX_PAYOUT_AMOUNT`, `MAX_PAYOUT_EARN_CODE`, `MAX_USAGE`, `MAX_CARRY_OVER`, `LM_ACCRUAL_CATEGORY_ID`, `OBJ_ID`, `VER_NBR`, `ACTIVE`, `TIMESTAMP`, `MAX_BAL_FLAG`) values ('5005', 'M', 0, 888, 16, 15.00, 'LA', 'P', NULL, NULL, NULL, 10.00, 'EC6', NULL, 10.00, '5005', 'DEDC243D-4E51-CCDE-1326-E1700B2631E1', '1', 'Y', '2011-02-03 12:10:23', 'Y');

insert into lm_accrual_category_rules_t (`lm_accrual_category_rules_id`, `SERVICE_UNIT_OF_TIME`, `START_ACC`, `END_ACC`, `ACCRUAL_RATE`, `MAX_BAL`, `MAX_BAL_ACTION_FREQUENCY`, `ACTION_AT_MAX_BAL`, `MAX_BAL_TRANS_ACC_CAT`, `MAX_BAL_TRANS_CONV_FACTOR`, `MAX_TRANS_AMOUNT`, `MAX_PAYOUT_AMOUNT`, `MAX_PAYOUT_EARN_CODE`, `MAX_USAGE`, `MAX_CARRY_OVER`, `LM_ACCRUAL_CATEGORY_ID`, `OBJ_ID`, `VER_NBR`, `ACTIVE`, `TIMESTAMP`, `MAX_BAL_FLAG`) values ('5012', 'M', 0, 888, 16, 100.00, 'YE', 'P', NULL, NULL, NULL, 10.00, 'EC7', NULL, 10.00, '5012', 'DEDC243D-4E51-CCDE-1326-E1700B2631E1', '1', 'Y', '2011-02-03 12:10:23', 'Y');

insert into lm_accrual_category_rules_t (`lm_accrual_category_rules_id`, `SERVICE_UNIT_OF_TIME`, `START_ACC`, `END_ACC`, `ACCRUAL_RATE`, `MAX_BAL`, `MAX_BAL_ACTION_FREQUENCY`, `ACTION_AT_MAX_BAL`, `MAX_BAL_TRANS_ACC_CAT`, `MAX_BAL_TRANS_CONV_FACTOR`, `MAX_TRANS_AMOUNT`, `MAX_PAYOUT_AMOUNT`, `MAX_PAYOUT_EARN_CODE`, `MAX_USAGE`, `MAX_CARRY_OVER`, `LM_ACCRUAL_CATEGORY_ID`, `OBJ_ID`, `VER_NBR`, `ACTIVE`, `TIMESTAMP`, `MAX_BAL_FLAG`) values ('6000', 'M', 0, 888, 16, 15.00, 'OD', 'P', NULL, NULL, NULL, 10.00, 'EC1a', NULL, NULL, '5000', 'DEDC243D-4E51-CCDE-1326-E1700B2631E1', '1', 'Y', '2011-02-03 12:10:23', 'Y');
insert into lm_accrual_category_rules_t (`lm_accrual_category_rules_id`, `SERVICE_UNIT_OF_TIME`, `START_ACC`, `END_ACC`, `ACCRUAL_RATE`, `MAX_BAL`, `MAX_BAL_ACTION_FREQUENCY`, `ACTION_AT_MAX_BAL`, `MAX_BAL_TRANS_ACC_CAT`, `MAX_BAL_TRANS_CONV_FACTOR`, `MAX_TRANS_AMOUNT`, `MAX_PAYOUT_AMOUNT`, `MAX_PAYOUT_EARN_CODE`, `MAX_USAGE`, `MAX_CARRY_OVER`, `LM_ACCRUAL_CATEGORY_ID`, `OBJ_ID`, `VER_NBR`, `ACTIVE`, `TIMESTAMP`, `MAX_BAL_FLAG`) values ('6001', 'M', 0, 888, 16, 15.00, 'YE', 'P', NULL, NULL, NULL, 10.00, 'EC2a', NULL, NULL, '5001', 'DEDC243D-4E51-CCDE-1326-E1700B2631E1', '1', 'Y', '2011-02-03 12:10:23', 'Y');
insert into lm_accrual_category_rules_t (`lm_accrual_category_rules_id`, `SERVICE_UNIT_OF_TIME`, `START_ACC`, `END_ACC`, `ACCRUAL_RATE`, `MAX_BAL`, `MAX_BAL_ACTION_FREQUENCY`, `ACTION_AT_MAX_BAL`, `MAX_BAL_TRANS_ACC_CAT`, `MAX_BAL_TRANS_CONV_FACTOR`, `MAX_TRANS_AMOUNT`, `MAX_PAYOUT_AMOUNT`, `MAX_PAYOUT_EARN_CODE`, `MAX_USAGE`, `MAX_CARRY_OVER`, `LM_ACCRUAL_CATEGORY_ID`, `OBJ_ID`, `VER_NBR`, `ACTIVE`, `TIMESTAMP`, `MAX_BAL_FLAG`) values ('6002', 'M', 0, 888, 16, 15.00, 'LA', 'P', NULL, NULL, NULL, 10.00, 'EC3a', NULL, NULL, '5002', 'DEDC243D-4E51-CCDE-1326-E1700B2631E1', '1', 'Y', '2011-02-03 12:10:23', 'Y');
insert into lm_accrual_category_rules_t (`lm_accrual_category_rules_id`, `SERVICE_UNIT_OF_TIME`, `START_ACC`, `END_ACC`, `ACCRUAL_RATE`, `MAX_BAL`, `MAX_BAL_ACTION_FREQUENCY`, `ACTION_AT_MAX_BAL`, `MAX_BAL_TRANS_ACC_CAT`, `MAX_BAL_TRANS_CONV_FACTOR`, `MAX_TRANS_AMOUNT`, `MAX_PAYOUT_AMOUNT`, `MAX_PAYOUT_EARN_CODE`, `MAX_USAGE`, `MAX_CARRY_OVER`, `LM_ACCRUAL_CATEGORY_ID`, `OBJ_ID`, `VER_NBR`, `ACTIVE`, `TIMESTAMP`, `MAX_BAL_FLAG`) values ('6003', 'M', 0, 888, 16, 15.00, 'OD', 'P', NULL, NULL, NULL, 10.00, 'EC4a', NULL, 10.00, '5003', 'DEDC243D-4E51-CCDE-1326-E1700B2631E1', '1', 'Y', '2011-02-03 12:10:23', 'Y');
insert into lm_accrual_category_rules_t (`lm_accrual_category_rules_id`, `SERVICE_UNIT_OF_TIME`, `START_ACC`, `END_ACC`, `ACCRUAL_RATE`, `MAX_BAL`, `MAX_BAL_ACTION_FREQUENCY`, `ACTION_AT_MAX_BAL`, `MAX_BAL_TRANS_ACC_CAT`, `MAX_BAL_TRANS_CONV_FACTOR`, `MAX_TRANS_AMOUNT`, `MAX_PAYOUT_AMOUNT`, `MAX_PAYOUT_EARN_CODE`, `MAX_USAGE`, `MAX_CARRY_OVER`, `LM_ACCRUAL_CATEGORY_ID`, `OBJ_ID`, `VER_NBR`, `ACTIVE`, `TIMESTAMP`, `MAX_BAL_FLAG`) values ('6004', 'M', 0, 888, 16, 15.00, 'YE', 'P', NULL, NULL, NULL, 10.00, 'EC5a', NULL, 10.00, '5004', 'DEDC243D-4E51-CCDE-1326-E1700B2631E1', '1', 'Y', '2011-02-03 12:10:23', 'Y');
insert into lm_accrual_category_rules_t (`lm_accrual_category_rules_id`, `SERVICE_UNIT_OF_TIME`, `START_ACC`, `END_ACC`, `ACCRUAL_RATE`, `MAX_BAL`, `MAX_BAL_ACTION_FREQUENCY`, `ACTION_AT_MAX_BAL`, `MAX_BAL_TRANS_ACC_CAT`, `MAX_BAL_TRANS_CONV_FACTOR`, `MAX_TRANS_AMOUNT`, `MAX_PAYOUT_AMOUNT`, `MAX_PAYOUT_EARN_CODE`, `MAX_USAGE`, `MAX_CARRY_OVER`, `LM_ACCRUAL_CATEGORY_ID`, `OBJ_ID`, `VER_NBR`, `ACTIVE`, `TIMESTAMP`, `MAX_BAL_FLAG`) values ('6005', 'M', 0, 888, 16, 15.00, 'LA', 'P', NULL, NULL, NULL, 10.00, 'EC6a', NULL, 10.00, '5005', 'DEDC243D-4E51-CCDE-1326-E1700B2631E1', '1', 'Y', '2011-02-03 12:10:23', 'Y');

-- setup leave plan, principal hr attributes, leave eligible jobs, leave calendar.
insert into lm_leave_plan_t (`lm_leave_plan_id`, `LEAVE_PLAN`, `DESCR`, `CAL_YEAR_START`, `EFFDT`, `OBJ_ID`, `VER_NBR`, `ACTIVE`, `TIMESTAMP`, `PLANNING_MONTHS`) values ('80000', 'testLP', 'Test Leave Plan', '02/01', '2011-02-01', '', '1', 'Y', '2011-02-06 11:59:46', '80');

insert into hr_principal_attributes_t (`hr_principal_attribute_id`, `principal_id`, `pay_calendar`, `leave_plan`, `service_date`, `fmla_eligible`, `workers_eligible`, `timezone`, `EFFDT`, `TIMESTAMP`, `OBJ_ID`, `VER_NBR`, `active`, `leave_calendar`) values('5000', 'testUser1', 'BWS-LM', 'testLP', '2012-03-10', 'Y', 'Y', null, '2012-03-10', now(), uuid(), '1', 'Y', 'BWS-LM');
insert into hr_principal_attributes_t (`hr_principal_attribute_id`, `principal_id`, `pay_calendar`, `leave_plan`, `service_date`, `fmla_eligible`, `workers_eligible`, `timezone`, `EFFDT`, `TIMESTAMP`, `OBJ_ID`, `VER_NBR`, `active`, `leave_calendar`) values('5001', 'testUser2', 'BWS-LM', 'testLP', '2011-03-10', 'Y', 'Y', null, '2011-03-10', now(), uuid(), '1', 'Y', 'BWS-CAL');

insert into lm_accrual_category_t (`lm_accrual_category_id`, `ACCRUAL_CATEGORY`, `LEAVE_PLAN`, `DESCR`, `ACCRUAL_INTERVAL_EARN`, `UNIT_OF_TIME`, `EFFDT`, `OBJ_ID`, `VER_NBR`, `PRORATION`, `DONATION`, `SHOW_ON_GRID`, `ACTIVE`, `TIMESTAMP`, `MIN_PERCENT_WORKED`, `EARN_CODE`, `HAS_RULES`) values('5000', 'od-xfer', 'testLP', 'test', 'M', '40', '2011-03-01', '8421CD29-E1F4-4B9A-AE33-F3F4752505CE', '1', 'N', null, 'Y', 'Y',now(), '0', 'EC1', 'Y');
insert into lm_accrual_category_t (`lm_accrual_category_id`, `ACCRUAL_CATEGORY`, `LEAVE_PLAN`, `DESCR`, `ACCRUAL_INTERVAL_EARN`, `UNIT_OF_TIME`, `EFFDT`, `OBJ_ID`, `VER_NBR`, `PRORATION`, `DONATION`, `SHOW_ON_GRID`, `ACTIVE`, `TIMESTAMP`, `MIN_PERCENT_WORKED`, `EARN_CODE`, `HAS_RULES`) values('5001', 'ye-xfer', 'testLP', 'test', 'M', '40', '2011-03-01', '8421CD29-E1F4-4B9A-AE33-F3F4752505CE', '1', 'N', null, 'Y', 'Y',now(), '0', 'EC2', 'Y');
insert into lm_accrual_category_t (`lm_accrual_category_id`, `ACCRUAL_CATEGORY`, `LEAVE_PLAN`, `DESCR`, `ACCRUAL_INTERVAL_EARN`, `UNIT_OF_TIME`, `EFFDT`, `OBJ_ID`, `VER_NBR`, `PRORATION`, `DONATION`, `SHOW_ON_GRID`, `ACTIVE`, `TIMESTAMP`, `MIN_PERCENT_WORKED`, `EARN_CODE`, `HAS_RULES`) values('5002', 'la-xfer', 'testLP', 'test', 'M', '40', '2011-03-01', '8421CD29-E1F4-4B9A-AE33-F3F4752505CE', '1', 'N', null, 'Y', 'Y',now(), '0', 'EC3', 'Y');
insert into lm_accrual_category_t (`lm_accrual_category_id`, `ACCRUAL_CATEGORY`, `LEAVE_PLAN`, `DESCR`, `ACCRUAL_INTERVAL_EARN`, `UNIT_OF_TIME`, `EFFDT`, `OBJ_ID`, `VER_NBR`, `PRORATION`, `DONATION`, `SHOW_ON_GRID`, `ACTIVE`, `TIMESTAMP`, `MIN_PERCENT_WORKED`, `EARN_CODE`, `HAS_RULES`) values('5003', 'od-xfer-mac', 'testLP', 'test', 'M', '40', '2011-03-01', '8421CD29-E1F4-4B9A-AE33-F3F4752505CE', '1', 'N', null, 'Y', 'Y',now(), '0', 'EC4', 'Y');
insert into lm_accrual_category_t (`lm_accrual_category_id`, `ACCRUAL_CATEGORY`, `LEAVE_PLAN`, `DESCR`, `ACCRUAL_INTERVAL_EARN`, `UNIT_OF_TIME`, `EFFDT`, `OBJ_ID`, `VER_NBR`, `PRORATION`, `DONATION`, `SHOW_ON_GRID`, `ACTIVE`, `TIMESTAMP`, `MIN_PERCENT_WORKED`, `EARN_CODE`, `HAS_RULES`) values('5004', 'ye-xfer-mac', 'testLP', 'test', 'M', '40', '2011-03-01', '8421CD29-E1F4-4B9A-AE33-F3F4752505CE', '1', 'N', null, 'Y', 'Y',now(), '0', 'EC5', 'Y');
insert into lm_accrual_category_t (`lm_accrual_category_id`, `ACCRUAL_CATEGORY`, `LEAVE_PLAN`, `DESCR`, `ACCRUAL_INTERVAL_EARN`, `UNIT_OF_TIME`, `EFFDT`, `OBJ_ID`, `VER_NBR`, `PRORATION`, `DONATION`, `SHOW_ON_GRID`, `ACTIVE`, `TIMESTAMP`, `MIN_PERCENT_WORKED`, `EARN_CODE`, `HAS_RULES`) values('5005', 'la-xfer-mac', 'testLP', 'test', 'M', '40', '2011-03-01', '8421CD29-E1F4-4B9A-AE33-F3F4752505CE', '1', 'N', null, 'Y', 'Y',now(), '0', 'EC6', 'Y');

insert into lm_accrual_category_t (`lm_accrual_category_id`, `ACCRUAL_CATEGORY`, `LEAVE_PLAN`, `DESCR`, `ACCRUAL_INTERVAL_EARN`, `UNIT_OF_TIME`, `EFFDT`, `OBJ_ID`, `VER_NBR`, `PRORATION`, `DONATION`, `SHOW_ON_GRID`, `ACTIVE`, `TIMESTAMP`, `MIN_PERCENT_WORKED`, `EARN_CODE`, `HAS_RULES`) values('6001', 'od-xfer-dep', 'testLP', 'test', 'M', '40', '2011-03-01', '8421CD29-E1F4-4B9A-AE33-F3F4752505CE', '1', 'N', null, 'Y', 'Y',now(), '0', 'EC1a', 'Y');
insert into lm_accrual_category_t (`lm_accrual_category_id`, `ACCRUAL_CATEGORY`, `LEAVE_PLAN`, `DESCR`, `ACCRUAL_INTERVAL_EARN`, `UNIT_OF_TIME`, `EFFDT`, `OBJ_ID`, `VER_NBR`, `PRORATION`, `DONATION`, `SHOW_ON_GRID`, `ACTIVE`, `TIMESTAMP`, `MIN_PERCENT_WORKED`, `EARN_CODE`, `HAS_RULES`) values('6002', 'ye-xfer-dep', 'testLP', 'test', 'M', '40', '2011-03-01', '8421CD29-E1F4-4B9A-AE33-F3F4752505CE', '1', 'N', null, 'Y', 'Y',now(), '0', 'EC2a', 'Y');
insert into lm_accrual_category_t (`lm_accrual_category_id`, `ACCRUAL_CATEGORY`, `LEAVE_PLAN`, `DESCR`, `ACCRUAL_INTERVAL_EARN`, `UNIT_OF_TIME`, `EFFDT`, `OBJ_ID`, `VER_NBR`, `PRORATION`, `DONATION`, `SHOW_ON_GRID`, `ACTIVE`, `TIMESTAMP`, `MIN_PERCENT_WORKED`, `EARN_CODE`, `HAS_RULES`) values('6003', 'la-xfer-dep', 'testLP', 'test', 'M', '40', '2011-03-01', '8421CD29-E1F4-4B9A-AE33-F3F4752505CE', '1', 'N', null, 'Y', 'Y',now(), '0', 'EC3a', 'Y');
insert into lm_accrual_category_t (`lm_accrual_category_id`, `ACCRUAL_CATEGORY`, `LEAVE_PLAN`, `DESCR`, `ACCRUAL_INTERVAL_EARN`, `UNIT_OF_TIME`, `EFFDT`, `OBJ_ID`, `VER_NBR`, `PRORATION`, `DONATION`, `SHOW_ON_GRID`, `ACTIVE`, `TIMESTAMP`, `MIN_PERCENT_WORKED`, `EARN_CODE`, `HAS_RULES`) values('6004', 'od-xfer-mac-dep', 'testLP', 'test', 'M', '40', '2011-03-01', '8421CD29-E1F4-4B9A-AE33-F3F4752505CE', '1', 'N', null, 'Y', 'Y',now(), '0', 'EC4a', 'Y');
insert into lm_accrual_category_t (`lm_accrual_category_id`, `ACCRUAL_CATEGORY`, `LEAVE_PLAN`, `DESCR`, `ACCRUAL_INTERVAL_EARN`, `UNIT_OF_TIME`, `EFFDT`, `OBJ_ID`, `VER_NBR`, `PRORATION`, `DONATION`, `SHOW_ON_GRID`, `ACTIVE`, `TIMESTAMP`, `MIN_PERCENT_WORKED`, `EARN_CODE`, `HAS_RULES`) values('6005', 'ye-xfer-mac-dep', 'testLP', 'test', 'M', '40', '2011-03-01', '8421CD29-E1F4-4B9A-AE33-F3F4752505CE', '1', 'N', null, 'Y', 'Y',now(), '0', 'EC5a', 'Y');
insert into lm_accrual_category_t (`lm_accrual_category_id`, `ACCRUAL_CATEGORY`, `LEAVE_PLAN`, `DESCR`, `ACCRUAL_INTERVAL_EARN`, `UNIT_OF_TIME`, `EFFDT`, `OBJ_ID`, `VER_NBR`, `PRORATION`, `DONATION`, `SHOW_ON_GRID`, `ACTIVE`, `TIMESTAMP`, `MIN_PERCENT_WORKED`, `EARN_CODE`, `HAS_RULES`) values('6006', 'la-xfer-mac-dep', 'testLP', 'test', 'M', '40', '2011-03-01', '8421CD29-E1F4-4B9A-AE33-F3F4752505CE', '1', 'N', null, 'Y', 'Y',now(), '0', 'EC6a', 'Y');

insert into lm_accrual_category_t (`lm_accrual_category_id`, `ACCRUAL_CATEGORY`, `LEAVE_PLAN`, `DESCR`, `ACCRUAL_INTERVAL_EARN`, `UNIT_OF_TIME`, `EFFDT`, `OBJ_ID`, `VER_NBR`, `PRORATION`, `DONATION`, `SHOW_ON_GRID`, `ACTIVE`, `TIMESTAMP`, `MIN_PERCENT_WORKED`, `EARN_CODE`, `HAS_RULES`) values('5012', 'ye-xfer-eo', 'testLP', 'test', 'M', '40', '2011-03-01', '8421CD29-E1F4-4B9A-AE33-F3F4752505CE', '1', 'N', null, 'Y', 'Y',now(), '0', 'EC13', 'Y');

insert into lm_accrual_category_t (`lm_accrual_category_id`, `ACCRUAL_CATEGORY`, `LEAVE_PLAN`, `DESCR`, `ACCRUAL_INTERVAL_EARN`, `UNIT_OF_TIME`, `EFFDT`, `OBJ_ID`, `VER_NBR`, `PRORATION`, `DONATION`, `SHOW_ON_GRID`, `ACTIVE`, `TIMESTAMP`, `MIN_PERCENT_WORKED`, `EARN_CODE`, `HAS_RULES`) values('6013', 'ye-xfer-eo-dep', 'testLP', 'test', 'M', '40', '2011-03-01', '8421CD29-E1F4-4B9A-AE33-F3F4752505CE', '1', 'N', null, 'Y', 'Y',now(), '0', 'EC13a', 'Y');


insert into hr_job_t (`hr_job_id`, `PRINCIPAL_ID`, `JOB_NUMBER`, `EFFDT`, `dept`, `HR_SAL_GROUP`, `pay_grade`, `TIMESTAMP`, `OBJ_ID`, `VER_NBR`, `comp_rate`, `location`, `std_hours`, `hr_paytype`, `active`, `primary_indicator`, `position_nbr`, `eligible_for_leave`, `FLSA_STATUS`) values ('5000', 'testUser1', '19', '2012-03-01', 'TEST-DEPT', 'SD1', 'SD1', now(), uuid(), '1', '0.000000', 'SD1', '40.00', 'BW', 'Y',  'Y', 'N', 'Y', null);
insert into hr_job_t (`hr_job_id`, `PRINCIPAL_ID`, `JOB_NUMBER`, `EFFDT`, `dept`, `HR_SAL_GROUP`, `pay_grade`, `TIMESTAMP`, `OBJ_ID`, `VER_NBR`, `comp_rate`, `location`, `std_hours`, `hr_paytype`, `active`, `primary_indicator`, `position_nbr`, `eligible_for_leave`, `FLSA_STATUS`) values ('5001', 'testUser2', '19', '2011-03-01', 'TEST-DEPT', 'SD1', 'SD1', now(), uuid(), '1', '0.000000', 'SD1', '40.00', 'BW', 'Y',  'Y', 'N', 'Y', 'NE');

INSERT INTO lm_leave_document_header_t (`document_id`,`principal_id`,`begin_date`,`end_date`,`document_status`,`obj_id`,`ver_nbr`) values ('5000', 'testUser1', '2012-12-01 00:00:00','2013-01-01 00:00:00', 'S', '7EE387AB-26B0-B6A6-9C4C-5B5F687F0E97', '1');
INSERT INTO lm_leave_document_header_t (`document_id`,`principal_id`,`begin_date`,`end_date`,`document_status`,`obj_id`,`ver_nbr`) values ('5001', 'testUser1', '2013-01-01 00:00:00','2013-02-01 00:00:00', 'S', '7EE387AB-26B0-B6A6-9C4C-5B5F687F0E98', '1');
INSERT INTO lm_leave_document_header_t (`document_id`,`principal_id`,`begin_date`,`end_date`,`document_status`,`obj_id`,`ver_nbr`) values ('5002', 'testUser1', '2012-11-01 00:00:00','2012-12-01 00:00:00', 'S', '7EE387AB-26B0-B6A6-9C4C-5B5F687F0E96', '1');

insert into hr_calendar_entries_t (`hr_calendar_entry_id`,`hr_calendar_id`,`calendar_name`,`begin_period_date`,`end_period_date`) values ('5000','3','BWS-LM','2012-11-01 00:00:00','2012-12-01 00:00:00');
insert into hr_calendar_entries_t (`hr_calendar_entry_id`,`hr_calendar_id`,`calendar_name`,`begin_period_date`,`end_period_date`) values ('5001','3','BWS-LM','2012-12-01 00:00:00','2013-01-01 00:00:00');
insert into hr_calendar_entries_t (`hr_calendar_entry_id`,`hr_calendar_id`,`calendar_name`,`begin_period_date`,`end_period_date`) values ('5002','3','BWS-LM','2013-01-01 00:00:00','2013-02-01 00:00:00');

-- Timesheet tests
insert into tk_document_header_t (`document_id`, `principal_id`, `pay_end_dt`, `document_status`, `pay_begin_dt`, `obj_id`, `ver_nbr`) values ('5003', 'testUser2', '2012-02-05 00:00:00', 'I', '2011-01-22 00:00:00', NULL, '1');
insert into tk_document_header_t (`document_id`, `principal_id`, `pay_end_dt`, `document_status`, `pay_begin_dt`, `obj_id`, `ver_nbr`) values ('5002', 'testUser2', '2012-01-22 00:00:00', 'I', '2011-01-08 00:00:00', NULL, '1');
insert into tk_document_header_t (`document_id`, `principal_id`, `pay_end_dt`, `document_status`, `pay_begin_dt`, `obj_id`, `ver_nbr`) values ('5000', 'testUser2', '2011-12-25 00:00:00', 'I', '2011-12-11 00:00:00', NULL, '1');
insert into tk_document_header_t (`document_id`, `principal_id`, `pay_end_dt`, `document_status`, `pay_begin_dt`, `obj_id`, `ver_nbr`) values ('5001', 'testUser2', '2012-01-08 00:00:00', 'I', '2011-12-25 00:00:00', NULL, '1');

-- Calendar entries defined in src/test/config/db/test/hr_calendar_entries_t.csv

insert into hr_earn_code_t values('5001', 'EC1', 'test', '2011-02-01', 'Y', 'Y', 'B2991ADA-E866-F28C-7E95-A897AC377D0C', '1', now(), 'od-xfer', '1.5', '1.5', 'Hours', 'testLP', 'None', '99', 'T', 'N', 'Y', 'Y', 'Y', 'Y', 'test', null, 'N', 'I');
insert into hr_earn_code_t values('5002', 'EC2', 'test', '2011-02-01', 'Y', 'Y', 'B2991ADA-E866-F28C-7E95-A897AC377D0C', '1', now(), 'ye-xfer', '1.5', '1.5', 'Hours', 'testLP', 'None', '99', 'T', 'N', 'Y', 'Y', 'Y', 'Y', 'test', null, 'N', 'I');
insert into hr_earn_code_t values('5003', 'EC3', 'test', '2011-02-01', 'Y', 'Y', 'B2991ADA-E866-F28C-7E95-A897AC377D0C', '1', now(), 'la-xfer', '1.5', '1.5', 'Hours', 'testLP', 'None', '99', 'T', 'N', 'Y', 'Y', 'Y', 'Y', 'test', null, 'N', 'I');
insert into hr_earn_code_t values('5004', 'EC4', 'test', '2011-02-01', 'Y', 'Y', 'B2991ADA-E866-F28C-7E95-A897AC377D0C', '1', now(), 'od-xfer-mac', '1.5', '1.5', 'Hours', 'testLP', 'None', '99', 'T', 'N', 'Y', 'Y', 'Y', 'Y', 'test', null, 'N', 'I');
insert into hr_earn_code_t values('5005', 'EC5', 'test', '2011-02-01', 'Y', 'Y', 'B2991ADA-E866-F28C-7E95-A897AC377D0C', '1', now(), 'ye-xfer-mac', '1.5', '1.5', 'Hours', 'testLP', 'None', '99', 'T', 'N', 'Y', 'Y', 'Y', 'Y', 'test', null, 'N', 'I');
insert into hr_earn_code_t values('5006', 'EC6', 'test', '2011-02-01', 'Y', 'Y', 'B2991ADA-E866-F28C-7E95-A897AC377D0C', '1', now(), 'la-xfer-mac', '1.5', '1.5', 'Hours', 'testLP', 'None', '99', 'T', 'N', 'Y', 'Y', 'Y', 'Y', 'test', null, 'N', 'I');

insert into hr_earn_code_t values('6001', 'EC1a', 'test', '2011-02-01', 'Y', 'Y', 'B2991ADA-E866-F28C-7E95-A897AC377D0C', '1', now(), 'od-xfer-dep', '1.5', '1.5', 'Hours', 'testLP', 'None', '99', 'T', 'N', 'Y', 'Y', 'Y', 'Y', 'test', null, 'N', 'I');
insert into hr_earn_code_t values('6002', 'EC2a', 'test', '2011-02-01', 'Y', 'Y', 'B2991ADA-E866-F28C-7E95-A897AC377D0C', '1', now(), 'ye-xfer-dep', '1.5', '1.5', 'Hours', 'testLP', 'None', '99', 'T', 'N', 'Y', 'Y', 'Y', 'Y', 'test', null, 'N', 'I');
insert into hr_earn_code_t values('6003', 'EC3a', 'test', '2011-02-01', 'Y', 'Y', 'B2991ADA-E866-F28C-7E95-A897AC377D0C', '1', now(), 'la-xfer-dep', '1.5', '1.5', 'Hours', 'testLP', 'None', '99', 'T', 'N', 'Y', 'Y', 'Y', 'Y', 'test', null, 'N', 'I');
insert into hr_earn_code_t values('6004', 'EC4a', 'test', '2011-02-01', 'Y', 'Y', 'B2991ADA-E866-F28C-7E95-A897AC377D0C', '1', now(), 'od-xfer-mac-dep', '1.5', '1.5', 'Hours', 'testLP', 'None', '99', 'T', 'N', 'Y', 'Y', 'Y', 'Y', 'test', null, 'N', 'I');
insert into hr_earn_code_t values('6005', 'EC5a', 'test', '2011-02-01', 'Y', 'Y', 'B2991ADA-E866-F28C-7E95-A897AC377D0C', '1', now(), 'ye-xfer-mac-dep', '1.5', '1.5', 'Hours', 'testLP', 'None', '99', 'T', 'N', 'Y', 'Y', 'Y', 'Y', 'test', null, 'N', 'I');
insert into hr_earn_code_t values('6006', 'EC6a', 'test', '2011-02-01', 'Y', 'Y', 'B2991ADA-E866-F28C-7E95-A897AC377D0C', '1', now(), 'la-xfer-mac-dep', '1.5', '1.5', 'Hours', 'testLP', 'None', '99', 'T', 'N', 'Y', 'Y', 'Y', 'Y', 'test', null, 'N', 'I');

insert into hr_earn_code_t values('7000', 'EC13', 'test', '2011-02-01', 'Y', 'Y', 'B2991ADA-E866-F28C-7E95-A897AC377D0C', '1', now(), 'ye-xfer-eo', '1.5', '1.5', 'Hours', 'testLP', 'None', '99', 'T', 'N', 'Y', 'Y', 'Y', 'Y', 'test', null, 'N', 'I');