--
-- Copyright 2004-2015 The Kuali Foundation
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

--
-- KRNS_NTE_TYP_T
--


INSERT INTO KRNS_NTE_TYP_T (ACTV_IND,NTE_TYP_CD,OBJ_ID,TYP_DESC_TXT,VER_NBR)
  VALUES ('Y','BO','53680C68F5A9AD9BE0404F8189D80A6C','DOCUMENT BUSINESS OBJECT',1)
/
INSERT INTO KRNS_NTE_TYP_T (ACTV_IND,NTE_TYP_CD,OBJ_ID,TYP_DESC_TXT,VER_NBR)
  VALUES ('Y','DH','53680C68F5AAAD9BE0404F8189D80A6C','DOCUMENT HEADER',1)
/

--
-- KRSB_QRTZ_LOCKS
--


INSERT INTO KRSB_QRTZ_LOCKS (LOCK_NAME)
  VALUES ('CALENDAR_ACCESS')
/
INSERT INTO KRSB_QRTZ_LOCKS (LOCK_NAME)
  VALUES ('JOB_ACCESS')
/
INSERT INTO KRSB_QRTZ_LOCKS (LOCK_NAME)
  VALUES ('MISFIRE_ACCESS')
/
INSERT INTO KRSB_QRTZ_LOCKS (LOCK_NAME)
  VALUES ('STATE_ACCESS')
/
INSERT INTO KRSB_QRTZ_LOCKS (LOCK_NAME)
  VALUES ('TRIGGER_ACCESS')
/
