--
-- Copyright 2004-2013 The Kuali Foundation
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

delete from hr_principal_attributes_t where principal_id = 'fran';
delete from hr_principal_attributes_t where principal_id = 'frank';
delete from hr_principal_attributes_t where principal_id = 'edna';
delete from hr_principal_attributes_t where principal_id = 'fred';

insert into hr_principal_attributes_t (`hr_principal_attribute_id`, `principal_id`, `pay_calendar`, `service_date`, `fmla_eligible`, `worksman_eligible`, `timezone`, `EFFDT`, `TIMESTAMP`, `OBJ_ID`, `VER_NBR`, `active`) values('2001','fran', 'BWS-CAL', '2010-01-01', 'Y','Y',null, '2010-01-01',now(), uuid(), '1', 'Y');
insert into hr_principal_attributes_t (`hr_principal_attribute_id`, `principal_id`, `pay_calendar`, `service_date`, `fmla_eligible`, `worksman_eligible`, `timezone`, `EFFDT`, `TIMESTAMP`, `OBJ_ID`, `VER_NBR`, `active`) values('2002','frank', 'BWS-CAL', '2010-01-01', 'Y','Y',null, '2010-01-01',now(), uuid(), '1', 'Y');
insert into hr_principal_attributes_t (`hr_principal_attribute_id`, `principal_id`, `pay_calendar`, `service_date`, `fmla_eligible`, `worksman_eligible`, `timezone`, `EFFDT`, `TIMESTAMP`, `OBJ_ID`, `VER_NBR`, `active`) values('2003','edna', 'BWS-CAL', '2010-01-01', 'Y','Y',null, '2010-01-01',now(), uuid(), '1', 'Y');
insert into hr_principal_attributes_t (`hr_principal_attribute_id`, `principal_id`, `pay_calendar`, `service_date`, `fmla_eligible`, `worksman_eligible`, `timezone`, `EFFDT`, `TIMESTAMP`, `OBJ_ID`, `VER_NBR`, `active`) values('2004','fred', 'BWS-CAL', '2010-01-01', 'Y','Y',null, '2010-01-01',now(), uuid(), '1', 'Y');