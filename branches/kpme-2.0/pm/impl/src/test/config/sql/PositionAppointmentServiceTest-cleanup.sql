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

delete from PM_PSTN_APPOINTMENT_T where pstn_appointment = 'testAppointment';
#delete from KRLC_CMP_T where CAMPUS_CD = 'TS';
DELETE FROM HR_LOCATION_T WHERE HR_LOCATION_ID >= 1000;
delete from PM_INSTITUTION_T where pm_institution_id >= 5000;
