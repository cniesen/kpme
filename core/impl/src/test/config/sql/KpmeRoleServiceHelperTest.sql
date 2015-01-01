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

-- SPECIFICATIONS
-- The inserts in this SQL file ensure that the Approver Role will have the following members: 
--  Work Area 
--   4444 -> principal called test_principal_1  (active from 2012 for all time)
--   4444 -> position role with position = POS-1  (active only for 2013) -> In the jobs table, POS-1-PRN-1 is active from 2012, POS-1-PRN-2 is active from 2014 for position POS-1  
--   4444 -> group called System Admnistrator (active from 2011 for all time) -> principal admin is active in System Administrator for all time (bootstrapped), principal SYS-ADMIN-PRN-1 is active only within 2013 and grp WorkflowAdmin only WITHIN 2014 -> principal WRKFLW-ADMIN-PRN1 is active for all time in grp WorkflowAdmin  
--   4444 -> position role with position = POS-2  (active for all time)  -> POS-2-PRN-1 is active from start of 2012 to end of 2013, POS-2-PRN-2 is active from start of 2014 for position POS-2
--   4444 -> default type role called GuestRole (active from 2013 for all time) -> principal guest is active in GuestRole for all time (bootstrapped)

--   8888 -> position role with position = POS-3  (active for all time) -> POS-3-PRN-1 is active from start of 2012 to end of 2015 for position POS-3 in the jobs table.
--   8888 -> principal called test_principal_2  (active only for 2013)
--   8888 -> position role with position = POS-1  (active for all time) -> In the jobs table, POS-1-PRN-1 is active from 2012, POS-1-PRN-2 is active from 2014 for position POS-1  



-- Insert a principal called test_principal_1 into the approver role for work area 4444 
INSERT INTO KRIM_ROLE_MBR_T (ROLE_MBR_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, LAST_UPDT_DT, OBJ_ID, VER_NBR, ACTV_FRM_DT) VALUES ('KPME_TEST_NEERAJ_1000', (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KPME-HR' AND ROLE_NM = 'Approver'), 'test_principal_1', 'P', NOW(), UUID(), 1, '2012-01-01');
INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID , ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID, VER_NBR) VALUES ('KPME_TEST_NEERAJ_1000', 'KPME_TEST_NEERAJ_1000', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KPME-WKFLW' AND NM = 'Work Area'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KPME-WKFLW' AND NM = 'workArea'), '4444', UUID(), 1);


-- Insert a derived role called Derived Role : Position with position number POS-1 into the approver role for work area 4444 active only for 2013
INSERT INTO KRIM_ROLE_MBR_T (ROLE_MBR_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, LAST_UPDT_DT, OBJ_ID, VER_NBR, ACTV_FRM_DT, ACTV_TO_DT) VALUES ('KPME_TEST_NEERAJ_1001', (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KPME-HR' AND ROLE_NM = 'Approver'), (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KPME-HR' AND ROLE_NM = 'Derived Role : Position'), 'R', NOW(), UUID(), 1, '2013-01-01', '2013-12-31');
INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID, VER_NBR) VALUES ('KPME_TEST_NEERAJ_1001', 'KPME_TEST_NEERAJ_1001', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KPME-WKFLW' AND NM = 'Work Area'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KPME-WKFLW' AND NM = 'workArea'), '4444', UUID(), 1);
INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID, VER_NBR) VALUES ('KPME_TEST_NEERAJ_1002', 'KPME_TEST_NEERAJ_1001', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KPME-WKFLW' AND NM = 'Position'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KPME-WKFLW' AND NM = 'position'), 'POS-1', UUID(), 1);


-- Insert a group called System Administrator into the approver role for work area 4444
INSERT INTO KRIM_ROLE_MBR_T (ROLE_MBR_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, LAST_UPDT_DT, OBJ_ID, VER_NBR, ACTV_FRM_DT) VALUES ('KPME_TEST_NEERAJ_1002', (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KPME-HR' AND ROLE_NM = 'Approver'), (SELECT GRP_ID FROM KRIM_GRP_T WHERE NMSPC_CD = 'KPME-HR' AND GRP_NM = 'System Administrator'), 'G', NOW(), UUID(), 1, '2011-01-01');
INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID, VER_NBR) VALUES ('KPME_TEST_NEERAJ_1003', 'KPME_TEST_NEERAJ_1002', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KPME-WKFLW' AND NM = 'Work Area'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KPME-WKFLW' AND NM = 'workArea'), '4444', UUID(), 1);
	

-- Insert a derived role called Derived Role : Position with position number POS-2 into the approver role for work area 4444
INSERT INTO KRIM_ROLE_MBR_T (ROLE_MBR_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, LAST_UPDT_DT, OBJ_ID, VER_NBR) VALUES ('KPME_TEST_NEERAJ_1003', (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KPME-HR' AND ROLE_NM = 'Approver'), (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KPME-HR' AND ROLE_NM = 'Derived Role : Position'), 'R', NOW(), UUID(), 1);
INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID, VER_NBR) VALUES ('KPME_TEST_NEERAJ_1004', 'KPME_TEST_NEERAJ_1003', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KPME-WKFLW' AND NM = 'Work Area'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KPME-WKFLW' AND NM = 'workArea'), '4444', UUID(), 1);
INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID, VER_NBR) VALUES ('KPME_TEST_NEERAJ_1005', 'KPME_TEST_NEERAJ_1003', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KPME-WKFLW' AND NM = 'Position'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KPME-WKFLW' AND NM = 'position'), 'POS-2', UUID(), 1);


-- Insert a derived role called Derived Role : Position with position number POS-3 into the approver role for work area 8888
INSERT INTO KRIM_ROLE_MBR_T (ROLE_MBR_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, LAST_UPDT_DT, OBJ_ID, VER_NBR) VALUES ('KPME_TEST_NEERAJ_1004', (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KPME-HR' AND ROLE_NM = 'Approver'), (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KPME-HR' AND ROLE_NM = 'Derived Role : Position'), 'R', NOW(), UUID(), 1);
INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID, VER_NBR) VALUES ('KPME_TEST_NEERAJ_1006', 'KPME_TEST_NEERAJ_1004', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KPME-WKFLW' AND NM = 'Position'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KPME-WKFLW' AND NM = 'position'), 'POS-3', UUID(), 1);
INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID, VER_NBR) VALUES ('KPME_TEST_NEERAJ_1007', 'KPME_TEST_NEERAJ_1004', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KPME-WKFLW' AND NM = 'Work Area'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KPME-WKFLW' AND NM = 'workArea'), '8888' , UUID(), 1);


-- Insert a principal called test_principal_2 into the approver role for work area 8888
INSERT INTO KRIM_ROLE_MBR_T (ROLE_MBR_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, LAST_UPDT_DT, OBJ_ID, VER_NBR, ACTV_FRM_DT, ACTV_TO_DT) VALUES ('KPME_TEST_NEERAJ_1005', (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KPME-HR' AND ROLE_NM = 'Approver'), 'test_principal_2', 'P', NOW(), UUID(), 1, '2013-01-01', '2013-12-31');
INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID , ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID, VER_NBR) VALUES ('KPME_TEST_NEERAJ_1008', 'KPME_TEST_NEERAJ_1005', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KPME-WKFLW' AND NM = 'Work Area'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KPME-WKFLW' AND NM = 'workArea'), '8888', UUID(), 1);


-- Insert a default-type role called GuestRole into the approver role for work area 4444 active from 2013
INSERT INTO KRIM_ROLE_MBR_T (ROLE_MBR_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, LAST_UPDT_DT, OBJ_ID, VER_NBR, ACTV_FRM_DT) VALUES ('KPME_TEST_NEERAJ_1006', (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KPME-HR' AND ROLE_NM = 'Approver'), (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KUALI' AND ROLE_NM = 'GuestRole'), 'R', NOW(), UUID(), 1, '2013-01-01');
INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID, VER_NBR) VALUES ('KPME_TEST_NEERAJ_1009', 'KPME_TEST_NEERAJ_1006', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KPME-WKFLW' AND NM = 'Work Area'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KPME-WKFLW' AND NM = 'workArea'), '4444', UUID(), 1);

-- Insert a derived role called Derived Role : Position with position number POS-1 into the approver role for work area 8888 active for all time
INSERT INTO KRIM_ROLE_MBR_T (ROLE_MBR_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, LAST_UPDT_DT, OBJ_ID, VER_NBR) VALUES ('KPME_TEST_NEERAJ_1007', (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KPME-HR' AND ROLE_NM = 'Approver'), (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KPME-HR' AND ROLE_NM = 'Derived Role : Position'), 'R', NOW(), UUID(), 1);
INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID, VER_NBR) VALUES ('KPME_TEST_NEERAJ_1010', 'KPME_TEST_NEERAJ_1007', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KPME-WKFLW' AND NM = 'Work Area'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KPME-WKFLW' AND NM = 'workArea'), '8888', UUID(), 1);
INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID, VER_NBR) VALUES ('KPME_TEST_NEERAJ_1011', 'KPME_TEST_NEERAJ_1007', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KPME-WKFLW' AND NM = 'Position'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KPME-WKFLW' AND NM = 'position'), 'POS-1', UUID(), 1);


-- Now populate the principals in jobs and groups
-- Jobs
-- principal POS-1-PRN-1 is active from start of 2012 
INSERT INTO HR_JOB_T (HR_JOB_ID, PRINCIPAL_ID, JOB_NUMBER, EFFDT, DEPT, HR_SAL_GROUP, PAY_GRADE, TIMESTAMP, OBJ_ID, VER_NBR, COMP_RATE, GRP_KEY_CD, STD_HOURS, HR_PAYTYPE, ACTIVE, PRIMARY_INDICATOR, POSITION_NBR, ELIGIBLE_FOR_LEAVE, FLSA_STATUS) VALUES ('KPME_TEST_NEERAJ_1000', 'POS-1-PRN-1', '10', '2012-01-01', 'TEST-DEPT', 'SD1', 'SD1', NOW(), UUID(), '1', '0.000000', 'IU-IN', '30.00', 'BW', 'Y', 'Y', 'POS-1', 'N', 'NE');

-- principal POS-1-PRN-2 is active from start of 2014 
INSERT INTO HR_JOB_T (HR_JOB_ID, PRINCIPAL_ID, JOB_NUMBER, EFFDT, DEPT, HR_SAL_GROUP, PAY_GRADE, TIMESTAMP, OBJ_ID, VER_NBR, COMP_RATE, GRP_KEY_CD, STD_HOURS, HR_PAYTYPE, ACTIVE, PRIMARY_INDICATOR, POSITION_NBR, ELIGIBLE_FOR_LEAVE, FLSA_STATUS) VALUES ('KPME_TEST_NEERAJ_1001', 'POS-1-PRN-2', '10', '2014-01-01', 'TEST-DEPT', 'SD1', 'SD1', NOW(), UUID(), '1', '0.000000', 'IU-IN', '30.00', 'BW', 'Y', 'Y', 'POS-1', 'N', 'NE');

-- principal POS-2-PRN-1 is active from start of 2012 to end of 2013
INSERT INTO HR_JOB_T (HR_JOB_ID, PRINCIPAL_ID, JOB_NUMBER, EFFDT, DEPT, HR_SAL_GROUP, PAY_GRADE, TIMESTAMP, OBJ_ID, VER_NBR, COMP_RATE, GRP_KEY_CD, STD_HOURS, HR_PAYTYPE, ACTIVE, PRIMARY_INDICATOR, POSITION_NBR, ELIGIBLE_FOR_LEAVE, FLSA_STATUS) VALUES ('KPME_TEST_NEERAJ_1002', 'POS-2-PRN-1', '11', '2012-01-01', 'TEST-DEPT', 'SD1', 'SD1', NOW(), UUID(), '1', '0.000000', 'IU-IN', '30.00', 'BW', 'Y', 'Y', 'POS-2', 'N', 'NE');
INSERT INTO HR_JOB_T (HR_JOB_ID, PRINCIPAL_ID, JOB_NUMBER, EFFDT, DEPT, HR_SAL_GROUP, PAY_GRADE, TIMESTAMP, OBJ_ID, VER_NBR, COMP_RATE, GRP_KEY_CD, STD_HOURS, HR_PAYTYPE, ACTIVE, PRIMARY_INDICATOR, POSITION_NBR, ELIGIBLE_FOR_LEAVE, FLSA_STATUS) VALUES ('KPME_TEST_NEERAJ_1003', 'POS-2-PRN-1', '11', '2013-12-31', 'TEST-DEPT', 'SD1', 'SD1', NOW(), UUID(), '1', '0.000000', 'IU-IN', '30.00', 'BW', 'N', 'Y', 'POS-2', 'N', 'NE');

-- principal POS-3-PRN-1 is active from start of 2012 to end of 2015
INSERT INTO HR_JOB_T (HR_JOB_ID, PRINCIPAL_ID, JOB_NUMBER, EFFDT, DEPT, HR_SAL_GROUP, PAY_GRADE, TIMESTAMP, OBJ_ID, VER_NBR, COMP_RATE, GRP_KEY_CD, STD_HOURS, HR_PAYTYPE, ACTIVE, PRIMARY_INDICATOR, POSITION_NBR, ELIGIBLE_FOR_LEAVE, FLSA_STATUS) VALUES ('KPME_TEST_NEERAJ_1004', 'POS-3-PRN-1', '12', '2012-01-01', 'TEST-DEPT', 'SD1', 'SD1', NOW(), UUID(), '1', '0.000000', 'IU-IN', '30.00', 'BW', 'Y', 'Y', 'POS-3', 'N', 'NE');
INSERT INTO HR_JOB_T (HR_JOB_ID, PRINCIPAL_ID, JOB_NUMBER, EFFDT, DEPT, HR_SAL_GROUP, PAY_GRADE, TIMESTAMP, OBJ_ID, VER_NBR, COMP_RATE, GRP_KEY_CD, STD_HOURS, HR_PAYTYPE, ACTIVE, PRIMARY_INDICATOR, POSITION_NBR, ELIGIBLE_FOR_LEAVE, FLSA_STATUS) VALUES ('KPME_TEST_NEERAJ_1005', 'POS-3-PRN-1', '12', '2015-12-31', 'TEST-DEPT', 'SD1', 'SD1', NOW(), UUID(), '1', '0.000000', 'IU-IN', '30.00', 'BW', 'N', 'Y', 'POS-3', 'N', 'NE');

-- principal POS-2-PRN-2 is active from start of 2014 
INSERT INTO HR_JOB_T (HR_JOB_ID, PRINCIPAL_ID, JOB_NUMBER, EFFDT, DEPT, HR_SAL_GROUP, PAY_GRADE, TIMESTAMP, OBJ_ID, VER_NBR, COMP_RATE, GRP_KEY_CD, STD_HOURS, HR_PAYTYPE, ACTIVE, PRIMARY_INDICATOR, POSITION_NBR, ELIGIBLE_FOR_LEAVE, FLSA_STATUS) VALUES ('KPME_TEST_NEERAJ_1006', 'POS-2-PRN-2', '10', '2014-01-01', 'TEST-DEPT', 'SD1', 'SD1', NOW(), UUID(), '1', '0.000000', 'IU-IN', '30.00', 'BW', 'Y', 'Y', 'POS-2', 'N', 'NE');


-- groups
-- principal SYS-ADMIN-PRN-1 is active only within 2013 in group System Administrator
INSERT INTO KRIM_GRP_MBR_T (GRP_MBR_ID, GRP_ID, MBR_ID, MBR_TYP_CD, LAST_UPDT_DT, OBJ_ID, VER_NBR, ACTV_FRM_DT, ACTV_TO_DT) VALUES ('KPME_TEST_NEERAJ_1000', (SELECT GRP_ID FROM KRIM_GRP_T WHERE NMSPC_CD = 'KPME-HR' AND GRP_NM = 'System Administrator'), 'SYS-ADMIN-PRN-1', 'P', NOW(), UUID(), 1, '2013-01-01', '2013-12-31');

-- group WorkflowAdmin is active only within 2013 in group System Administrator
INSERT INTO KRIM_GRP_MBR_T (GRP_MBR_ID, GRP_ID, MBR_ID, MBR_TYP_CD, LAST_UPDT_DT, OBJ_ID, VER_NBR, ACTV_FRM_DT, ACTV_TO_DT) VALUES ('KPME_TEST_NEERAJ_1001', (SELECT GRP_ID FROM KRIM_GRP_T WHERE NMSPC_CD = 'KPME-HR' AND GRP_NM = 'System Administrator'), (SELECT GRP_ID FROM KRIM_GRP_T WHERE NMSPC_CD = 'KR-WKFLW' AND GRP_NM = 'WorkflowAdmin'), 'G', NOW(), UUID(), 1, '2014-01-01', '2014-12-31');

-- principal WKFLW-ADMIN-PRN-1 is active for all time in group WokflowAdmin
INSERT INTO KRIM_GRP_MBR_T (GRP_MBR_ID, GRP_ID, MBR_ID, MBR_TYP_CD, LAST_UPDT_DT, OBJ_ID, VER_NBR) VALUES ('KPME_TEST_NEERAJ_1002', (SELECT GRP_ID FROM KRIM_GRP_T WHERE NMSPC_CD = 'KR-WKFLW' AND GRP_NM = 'WorkflowAdmin'), 'WKFLW-ADMIN-PRN-1', 'P', NOW(), UUID(), 1);
