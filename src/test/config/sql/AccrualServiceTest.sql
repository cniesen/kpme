delete from lm_leave_plan_t where LM_LEAVE_PLAN_ID >= '8000';
delete from lm_accrual_category_t where lm_accrual_category_id >= '5000';
delete from lm_accrual_category_rules_t where lm_accrual_category_rules_id >= '5000';
delete from lm_leave_code_t where lm_leave_code_id >= '5000';
delete from hr_earn_code_t where hr_earn_code_id >= '5000';
delete from hr_principal_attributes_t where hr_principal_attribute_id >= 5000;
delete from hr_principal_attributes_t where principal_id in('testUser', 'testUser1', 'testUser2', 'testUser3', 'testUser4', 'testUser5', 'testUser6');
delete from hr_job_t where hr_job_id >= '5000';
delete from lm_sys_schd_timeoff_t where lm_sys_schd_timeoff_id = '5000';
delete from lm_leave_block_t where principal_id in ('testUser', 'testUser1', 'testUser2', 'testUser3', 'testUser4', 'testUser5', 'testUser6');
delete from lm_leave_block_hist_t where principal_id in ('testUser', 'testUser1', 'testUser2', 'testUser3', 'testUser4', 'testUser5', 'testUser6');

insert into lm_leave_plan_t values ('8000', 'testLP', 'Test Leave Plan', '02/01', '2012-02-01', '', '1', 'Y', '2012-02-06 11:59:46', '12');
insert into hr_principal_attributes_t values('5001', 'testUser', 'BWS-CAL', 'testLP', '2012-03-01', 'Y', 'Y', null, '2012-03-01', now(), uuid(), '1', 'Y', 'LM', 'Y', 'Y');
insert into lm_accrual_category_t values('5000', 'testAC', 'testLP', 'test', 'M', '40', '2012-02-01', '8421CD29-E1F4-4B9A-AE33-F3F4752505CE', '1', null, null, 'Y', 'Y',now(), '0', 'EC', 'Y');
insert into lm_accrual_category_rules_t values ('5000', 'M', 0, 888, 16, 100.00, 'NA', 'NA', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5000', 'DEDC243D-4E51-CCDE-1326-E1700B2631E1', '1', 'Y', '2012-02-03 12:10:23', 'N');
insert into hr_earn_code_t values('5000', 'EC', 'test', '2012-02-01', 'Y', 'Y', 'B2991ADA-E866-F28C-7E95-A897AC377D0C', '1', now(), 'testAC', '1.5', '1.5', 'testLP', 'None', '99', 'T', 'N', 'Y', 'Y', 'Y', 'Y', 'test', null, 'N', 'Hours');
# insert into lm_leave_code_t values ('5000',	'testLP', 'Y', 'testAC', 'TC1', 'testLC', 'testLC', 'D', 99,	'T', 'Y', 'N', 'N', null, 'Y', 'N', 'N', '2012-02-01', 'B2991ADA-E866-F28C-7E95-A897AC377D0C',	'1', 'Y', '2012-02-02 11:38:04', 'N', 'N');

# two not eligible for leave jobs and one eligible one
insert into hr_job_t values ('5000', 'testUser', '1', '2012-01-01', 'TEST-DEPT', 'SD1', 'SD1', now(), uuid(), '1', '0.000000', 'SD1', '40.00', 'BW', 'Y',  'Y', 'N', 'N', null);
insert into hr_job_t values ('5001', 'testUser', '2', '2012-02-01', 'TEST-DEPT', 'SD1', 'SD1', now(), uuid(), '1', '0.000000', 'SD1', '40.00', 'BW', 'Y',  'Y', 'N', 'N', null);
insert into hr_job_t values ('5002', 'testUser', '3', '2012-03-01', 'TEST-DEPT', 'SD1', 'SD1', now(), uuid(), '1', '0.000000', 'SD1', '40.00', 'BW', 'Y',  'Y', 'N', 'Y', null);

# set up a system scheduled time off on 04/10/2012
insert into lm_accrual_category_t values('5001', 'holAC', 'testLP', 'test', 'M', '40', '2012-02-01', '8421CD29-E1F4-4B9A-AE33-F3F4752505CE', '1', null, null, 'Y', 'Y', now(), '0', 'hEC', 'Y');
insert into hr_earn_code_t values('5001', 'hEC', 'test', '2012-02-01', 'Y', 'Y', 'B2991ADA-E866-F28C-7E95-A897AC377D0C', '1', now(), 'holAC', '1.5', '1.5', 'testLP', 'None', '99', 'T', 'N', 'Y', 'Y', 'Y', 'Y', 'test', null, 'N', 'Hours');
#insert into lm_leave_code_t values ('5001', 'testLP', 'Y', 'holAC', 'TC1', 'holLC', 'holLC', 'D', 99,	'T', 'Y', 'N', 'N', null, 'Y', 'N', 'N', '2012-02-01', 'B2991ADA-E866-F28C-7E95-A897AC377D0C',	'1', 'Y', '2012-02-02 11:38:04', 'N', 'N');
insert into lm_sys_schd_timeoff_t values ('5000', 'testLP', 'holAC', 'hEC', '2012-04-10', NULL, 'testLocation','testSSTO', '8', null, null, null, null, 'testH', '2012-03-01', uuid(), '1', 'Y', now());

# add an inactive entry for job 3 and add a new job 4 for employee status change
# employee changed status on 04/01, fte is changed from 1 to 0.5
insert into hr_job_t values ('5003', 'testUser', '3', '2012-04-01', 'TEST-DEPT', 'SD1', 'SD1', now(), uuid(), '1', '0.000000', 'SD1', '40.00', 'BW', 'N',  'Y', 'N', 'Y', null);
insert into hr_job_t values ('5004', 'testUser', '4', '2012-04-01', 'TEST-DEPT', 'SD1', 'SD1', now(), uuid(), '1', '0.000000', 'SD1', '20.00', 'BW', 'Y',  'Y', 'N', 'Y', null);

# for testRunAccrualForRuleChangeS
# user testUser2 has 2 rules
insert into lm_leave_plan_t values ('8001', 'testLP2', 'Test Leave Plan', '02/01', '2012-02-01', '', '1', 'Y', '2012-02-06 11:59:46', '80');
insert into hr_principal_attributes_t values('5002', 'testUser2', 'BWS-CAL', 'testLP2', '2012-03-01', 'Y', 'Y', null, '2012-03-01', now(), uuid(), '1', 'Y', 'LM', 'Y', 'Y');
insert into lm_accrual_category_t values('5002', 'testAC2', 'testLP2', 'test', 'M', '40', '2012-02-01', '8421CD29-E1F4-4B9A-AE33-F3F4752505CE', '1', null, null, 'Y', 'Y',now(), '0', 'EC', 'Y');
insert into lm_accrual_category_rules_t values ('5001', 'M', 0, 5, 16, 100.00, 'NA', 'NA', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5002', 'DEDC243D-4E51-CCDE-1326-E1700B2631E1', '1', 'Y', '2012-02-03 12:10:23', 'N');
insert into lm_accrual_category_rules_t values ('5002', 'M', 5, 900, 24, 500.00, 'NA', 'NA', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5002', 'DEDC243D-4E51-CCDE-1326-E1700B2631E1', '1', 'Y', '2012-02-03 12:10:23', 'N');
insert into hr_job_t values ('5005', 'testUser2', '5', '2012-03-01', 'TEST-DEPT', 'SD1', 'SD1', now(), uuid(), '1', '0.000000', 'SD1', '40.00', 'BW', 'Y',  'Y', 'N', 'Y', null);

# for testRunAccrualWithDifferentAccrualIntervals
# user testUser3 has leavePlan testLP3 which has 2 accrual categories
insert into lm_leave_plan_t values ('8002', 'testLP3', 'Test Leave Plan', '02/01', '2012-02-01', '', '1', 'Y', '2012-02-06 11:59:46', '80');
insert into hr_principal_attributes_t values('5003', 'testUser3', 'BWS-CAL', 'testLP3', '2012-03-01', 'Y', 'Y', null, '2012-03-01', now(), uuid(), '1', 'Y', 'LM', 'Y', 'Y');
# testAC3 has leaveCode testLC1, Semi-Monthly as earn interval
insert into hr_earn_code_t values('5002', 'EC1', 'test', '2012-02-01', 'Y', 'Y', 'B2991ADA-E866-F28C-7E95-A897AC377D0C', '1', now(), 'testAC3', '1.5', '1.5', 'testLP', 'None', '99', 'T', 'N', 'Y', 'Y', 'Y', 'Y', 'test', null, 'N', 'Hours');
#insert into lm_leave_code_t values ('5002',	'testLP3', 'Y', 'testAC3', 'TC1', 'testLC1', 'testLC1', 'D', 99,	'T', 'Y', 'N', 'N', null, 'Y', 'N', 'N', '2012-02-01', 'B2991ADA-E866-F28C-7E95-A897AC377D0C',	'1', 'Y', '2012-02-02 11:38:04', 'N', 'N');
insert into lm_accrual_category_t values('5003', 'testAC3', 'testLP3', 'test', 'S', '40', '2012-02-01', '8421CD29-E1F4-4B9A-AE33-F3F4752505CE', '1', null, null, 'Y', 'Y',now(), '0', 'EC1', 'Y');
insert into lm_accrual_category_rules_t values ('5003', 'M', 0, 800, 8, 100.00, 'NA', 'NA', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5003', 'DEDC243D-4E51-CCDE-1326-E1700B2631E1', '1', 'Y', '2012-02-03 12:10:23', 'N');
# testAC4 has leaveCode testLC2, monthly as earn interval
insert into hr_earn_code_t values('5003', 'EC2', 'test', '2012-02-01', 'Y', 'Y', 'B2991ADA-E866-F28C-7E95-A897AC377D0C', '1', now(), 'testAC4', '1.5', '1.5', 'testLP', 'None', '99', 'T', 'N', 'Y', 'Y', 'Y', 'Y', 'test', null, 'N', 'Hours');
#insert into lm_leave_code_t values ('5003',	'testLP3', 'Y', 'testAC4', 'TC1', 'testLC2', 'testLC2', 'D', 99,	'T', 'Y', 'N', 'N', null, 'Y', 'N', 'N', '2012-02-01', 'B2991ADA-E866-F28C-7E95-A897AC377D0C',	'1', 'Y', '2012-02-02 11:38:04', 'N', 'N');
insert into lm_accrual_category_t values('5004', 'testAC4', 'testLP3', 'test', 'M', '40', '2012-02-01', '8421CD29-E1F4-4B9A-AE33-F3F4752505CE', '1', null, null, 'Y', 'Y',now(), '0', 'EC2', 'Y');
insert into lm_accrual_category_rules_t values ('5004', 'M', 0, 900, 24, 500.00, 'NA', 'NA', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5004', 'DEDC243D-4E51-CCDE-1326-E1700B2631E1', '1', 'Y', '2012-02-03 12:10:23', 'N');
insert into hr_job_t values ('5006', 'testUser3', '6', '2012-03-01', 'TEST-DEPT', 'SD1', 'SD1', now(), uuid(), '1', '0.000000', 'SD1', '40.00', 'BW', 'Y',  'Y', 'N', 'Y', null);

# for testRunAccrualForMinimumPercentage
# testUser4 has leavePlan testLP4 which has 2 accrual categories
insert into lm_leave_plan_t values ('8003', 'testLP4', 'Test Leave Plan', '02/01', '2012-02-01', '', '1', 'Y', '2012-02-06 11:59:46', '80');
# testUser4 has a service date of 03/18/2012 and Accrual category with Minimum percentage of 50% so for March 2012, 
# there should not be accrual for testUser4 for testAC5
insert into hr_principal_attributes_t values('5004', 'testUser4', 'BWS-CAL', 'testLP4', '2012-03-18', 'Y', 'Y', null, '2012-03-18', now(), uuid(), '1', 'Y', 'LM', 'Y', 'Y');
# employment ends on 2012-08-08
insert into hr_principal_attributes_t values('5005', 'testUser4', 'BWS-CAL', 'testLP4', '2012-03-18', 'Y', 'Y', null, '2012-08-08', now(), uuid(), '1', 'N', 'LM', 'Y', 'Y');
insert into hr_earn_code_t values('5004', 'EC4', 'test', '2012-02-01', 'Y', 'Y', 'B2991ADA-E866-F28C-7E95-A897AC377D0C', '1', now(), 'testAC5', '1.5', '1.5', 'testLP', 'None', '99', 'T', 'N', 'Y', 'Y', 'Y', 'Y', 'test', null, 'N', 'Hours');
#insert into lm_leave_code_t values ('5004',	'testLP4', 'Y', 'testAC5', 'TC1', 'testLC4', 'testLC4', 'D', 99,	'T', 'Y', 'N', 'N', null, 'Y', 'N', 'N', '2012-02-01', 'B2991ADA-E866-F28C-7E95-A897AC377D0C',	'1', 'Y', '2012-02-02 11:38:04', 'N', 'N');
insert into lm_accrual_category_t values('5005', 'testAC5', 'testLP4', 'test', 'M', '40', '2012-02-01', '8421CD29-E1F4-4B9A-AE33-F3F4752505CE', '1', 'Y', null, 'Y', 'Y',now(), '0.5', 'EC4', 'Y');
insert into lm_accrual_category_rules_t values ('5005', 'M', 0, 800, 8, 100.00, 'NA', 'NA', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5005', 'DEDC243D-4E51-CCDE-1326-E1700B2631E1', '1', 'Y', '2012-02-03 12:10:23', 'N');

insert into hr_earn_code_t values('5005', 'EC5', 'test', '2012-02-01', 'Y', 'Y', 'B2991ADA-E866-F28C-7E95-A897AC377D0C', '1', now(), 'testAC6', '1.5', '1.5', 'testLP', 'None', '99', 'T', 'N', 'Y', 'Y', 'Y', 'Y', 'test', null, 'N', 'Hours');
#insert into lm_leave_code_t values ('5005',	'testLP4', 'Y', 'testAC6', 'TC1', 'testLC5', 'testLC5', 'D', 99,	'T', 'Y', 'N', 'N', null, 'Y', 'N', 'N', '2012-02-01', 'B2991ADA-E866-F28C-7E95-A897AC377D0C',	'1', 'Y', '2012-02-02 11:38:04', 'N', 'N');
insert into lm_accrual_category_t values('5006', 'testAC6', 'testLP4', 'test', 'M', '40', '2012-02-01', '8421CD29-E1F4-4B9A-AE33-F3F4752505CE', '1', null, null, 'Y', 'Y',now(), '0', 'EC5', 'Y');
insert into lm_accrual_category_rules_t values ('5006', 'M', 0, 800, 16, 100.00, 'NA', 'NA', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5006', 'DEDC243D-4E51-CCDE-1326-E1700B2631E1', '1', 'Y', '2012-02-03 12:10:23', 'N');

insert into hr_job_t values ('5007', 'testUser4', '7', '2012-03-01', 'TEST-DEPT', 'SD1', 'SD1', now(), uuid(), '1', '0.000000', 'SD1', '40.00', 'BW', 'Y',  'Y', 'N', 'Y', null);

# for testMinReachedProrationFalseAndRuleChange
# user testUser5 has 2 rules
insert into lm_leave_plan_t values ('8004', 'testLP5', 'Test Leave Plan', '02/01', '2012-02-01', '', '1', 'Y', '2012-02-06 11:59:46', '80');
insert into hr_principal_attributes_t values('5006', 'testUser5', 'BWS-CAL', 'testLP5', '2012-03-10', 'Y', 'Y', null, '2012-03-10', now(), uuid(), '1', 'Y', 'LM', 'Y', 'Y');
# accrual category testAC7 has proration = fasle and minimum percentage = 0.5
insert into lm_accrual_category_t values('5007', 'testAC7', 'testLP5', 'test', 'M', '40', '2012-02-01', '8421CD29-E1F4-4B9A-AE33-F3F4752505CE', '1', 'N', null, 'Y', 'Y',now(), '0.5', 'EC', 'Y');
insert into lm_accrual_category_rules_t values ('5007', 'M', 0, 12, 16, 100.00, 'NA', 'NA', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5007', 'DEDC243D-4E51-CCDE-1326-E1700B2631E1', '1', 'Y', '2012-02-03 12:10:23', 'N');
insert into lm_accrual_category_rules_t values ('5008', 'M', 12, 900, 24, 500.00, 'NA', 'NA', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5007', 'DEDC243D-4E51-CCDE-1326-E1700B2631E1', '1', 'Y', '2012-02-03 12:10:23', 'N');
insert into hr_job_t values ('5008', 'testUser5', '8', '2012-03-01', 'TEST-DEPT', 'SD1', 'SD1', now(), uuid(), '1', '0.000000', 'SD1', '40.00', 'BW', 'Y',  'Y', 'N', 'Y', null);

# for testMinNOTReachedProrationFalseAndRuleChange
# user testUser6 has 2 rules
insert into lm_leave_plan_t values ('8005', 'testLP6', 'Test Leave Plan', '02/01', '2012-02-01', '', '1', 'Y', '2012-02-06 11:59:46', '80');
insert into hr_principal_attributes_t values('5007', 'testUser6', 'BWS-CAL', 'testLP6', '2012-03-25', 'Y', 'Y', null, '2012-03-25', now(), uuid(), '1', 'Y', 'LM', 'Y', 'Y');
# accrual category testAC8 has proration = fasle, minimum percentage = 0.5 and earnInterval = semi-monthly
insert into lm_accrual_category_t values('5008', 'testAC8', 'testLP6', 'test', 'S', '40', '2012-02-01', '8421CD29-E1F4-4B9A-AE33-F3F4752505CE', '1', 'N', null, 'Y', 'Y',now(), '0.5', 'EC', 'Y');
insert into lm_accrual_category_rules_t values ('5009', 'M', 0, 6, 16, 100.00, 'NA', 'NA', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5008', 'DEDC243D-4E51-CCDE-1326-E1700B2631E1', '1', 'Y', '2012-02-03 12:10:23', 'N');
insert into lm_accrual_category_rules_t values ('5010', 'M', 6, 900, 24, 500.00, 'NA', 'NA', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5008', 'DEDC243D-4E51-CCDE-1326-E1700B2631E1', '1', 'Y', '2012-02-03 12:10:23', 'N');
insert into hr_job_t values ('5009', 'testUser6', '9', '2012-03-01', 'TEST-DEPT', 'SD1', 'SD1', now(), uuid(), '1', '0.000000', 'SD1', '40.00', 'BW', 'Y',  'Y', 'N', 'Y', null);



