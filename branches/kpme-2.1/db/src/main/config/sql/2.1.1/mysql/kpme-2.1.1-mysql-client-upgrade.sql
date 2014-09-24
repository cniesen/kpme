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

--  *********************************************************************
--  Update Database Script
--  *********************************************************************
--  Change Log: src/main/resources/org/kuali/kpme/kpme-db/db/db.changelog-main-upgrade.xml
--  Ran at: 9/23/14 8:55 AM
--  Against: tk@localhost@jdbc:mysql://localhost/tk
--  Liquibase version: 3.1.1
--  *********************************************************************

--  Lock Database
--  Changeset org/kuali/kpme/kpme-db/db/2.1.1/db.changelog-201408221100.xml::1::mlemons
ALTER TABLE TK_WEEKLY_OVERTIME_RL_T ADD APPLY_TO_ERN_GROUP VARCHAR(10) NULL;

ALTER TABLE TK_WEEKLY_OVERTIME_RL_T ADD OVERRIDE_WORKAREA_DEFAULT VARCHAR(1) NULL DEFAULT 'N';

UPDATE TK_WEEKLY_OVERTIME_RL_T SET APPLY_TO_ERN_GROUP = CONVERT_FROM_ERN_GROUP;


--  Changeset org/kuali/kpme/kpme-db/db/2.1.1/db.changelog-201409041150.xml::1::schoo
CREATE TABLE tk_remote_swipe_device_t (IP_ADDRESS VARCHAR(20) DEFAULT '' NOT NULL, DEVICE_NM VARCHAR(100) DEFAULT '' NOT NULL, PRINCIPAL_ID VARCHAR(40) DEFAULT '' NOT NULL, LAST_COMMUNICATE_TIME VARCHAR(50) DEFAULT '' NULL, active VARCHAR(50) DEFAULT 'N' NOT NULL, CONSTRAINT PK_TK_REMOTE_SWIPE_DEVICE_T PRIMARY KEY (IP_ADDRESS)) ENGINE INNODB;


--  Changeset org/kuali/kpme/kpme-db/db/2.1.1/db.changelog-201409101150.xml::1::jjhanso
ALTER TABLE TK_HOUR_DETAIL_T ADD TTL_MIN DECIMAL(8, 0) NULL;

ALTER TABLE TK_HOUR_DETAIL_HIST_T ADD TTL_MIN DECIMAL(8, 0) NULL;

ALTER TABLE TK_TIME_BLOCK_T ADD TTL_MIN DECIMAL(8, 0) NULL;

ALTER TABLE TK_TIME_BLOCK_HIST_T ADD TTL_MIN DECIMAL(8, 0) NULL;

UPDATE TK_HOUR_DETAIL_T SET TTL_MIN = (60*HOURS);

UPDATE TK_HOUR_DETAIL_HIST_T SET TTL_MIN = (60*HOURS);

UPDATE TK_TIME_BLOCK_T SET TTL_MIN = (60*HOURS);

UPDATE TK_TIME_BLOCK_HIST_T SET TTL_MIN = (60*HOURS);


--  Release Database Lock
--  Release Database Lock
