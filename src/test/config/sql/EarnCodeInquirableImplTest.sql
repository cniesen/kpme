--
-- Copyright 2004-2012 The Kuali Foundation
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

delete from hr_earn_code_t where hr_earn_code_id >= '5000';

insert into hr_earn_code_t (`hr_earn_code_id` , `earn_code` ,`descr`,`effdt` ,`ovt_earn_code` ,`ACTIVE`,`OBJ_ID` ,`VER_NBR` ,`timestamp`,`ACCRUAL_CATEGORY`,`inflate_min_hours` , `inflate_factor` ,`RECORD_METHOD`) values ('5000', 'TestEarnCode', 'test', '2011-12-01', 'Y', 'Y', 'B2991ADA-E866-F28C-7E95-A897AC377D0C', '1', now(), 'testAC', '1.5', '1.5', 'Hours');
insert into hr_earn_code_t (`hr_earn_code_id` , `earn_code` ,`descr`,`effdt` ,`ovt_earn_code` ,`ACTIVE`,`OBJ_ID` ,`VER_NBR` ,`timestamp`,`ACCRUAL_CATEGORY`,`inflate_min_hours` , `inflate_factor` ,`RECORD_METHOD`) values ('5001', 'TestEarnCode', 'test', '2012-01-01', 'Y', 'Y', 'B2991ADA-E866-F28C-7E95-A897AC377D0C', '1', now(), 'testAC', '1.5', '1.5', 'Hours');
insert into hr_earn_code_t (`hr_earn_code_id` , `earn_code` ,`descr`,`effdt` ,`ovt_earn_code` ,`ACTIVE`,`OBJ_ID` ,`VER_NBR` ,`timestamp`,`ACCRUAL_CATEGORY`,`inflate_min_hours` , `inflate_factor` ,`RECORD_METHOD`) values ('5002', 'TestEarnCode', 'test', '2012-03-01', 'Y', 'Y', 'B2991ADA-E866-F28C-7E95-A897AC377D0C', '1', now(), 'testAC', '1.5', '1.5', 'Hours');