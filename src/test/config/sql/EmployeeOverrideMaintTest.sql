delete from lm_employee_override_t where lm_employee_override_id = '3000';
delete from lm_accrual_category_t where lm_accrual_category_id = '3000';
delete from lm_accrual_category_t where lm_accrual_category_id = '3001';
delete from hr_principal_attributes_t where principal_id = '111';
insert into lm_employee_override_t values ('3000', '111', 'testAC', 'testLP', 'Max Balance', NULL, NULL,'Y', now(), uuid(), '1', null);
insert into lm_accrual_category_t values('3000', 'testAC', 'testLP', 'test', '', '', '2010-01-01', '8421CD29-E1F4-4B9A-AE33-F3F4752505CE', '1', null, null, null, 'Y',now(), '1.5', 'LC-DEFAULT', 'Y');
insert into lm_accrual_category_t values('3001', 'testAC2', 'testLP2', 'test', '', '', '2010-01-01', '8421CD29-E1F4-4B9A-AE33-F3F4752505CE', '1', null, null, null, 'Y',now(), '1.5', 'LC-DEFAULT', 'Y');
insert into hr_principal_attributes_t values('1001', '111', 'BWS-CAL', 'testLP','2010-01-01', 'Y','Y',null, '2010-01-01',now(), uuid(), '1', 'Y', null, 'Y', 'LM');