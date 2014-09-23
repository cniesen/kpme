--  *********************************************************************
--  Update Database Script
--  *********************************************************************
--  Change Log: src/main/resources/org/kuali/kpme/kpme-db/db/db.changelog-main-upgrade.xml
--  Ran at: 9/23/14 8:55 AM
--  Against: krtt@localhost@jdbc:mysql://localhost/krtt
--  Liquibase version: 3.1.1
--  *********************************************************************

--  Lock Database
--  Changeset org/kuali/kpme/kpme-db/db/2.1.1/db.changelog-201408201100.xml::1::mlemons
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID, VER_NBR) VALUES ('KPME0934', (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KPME-HR' AND ROLE_NM = 'KOHR Institution Administrator'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KPME-PM' AND NM = 'View Position Appointment'), 'Y', UUID(), '1');

INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID, VER_NBR) VALUES ('KPME0935', (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KPME-HR' AND ROLE_NM = 'KOHR Academic HR Administrator'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KPME-PM' AND NM = 'View Position Appointment'), 'Y', UUID(), '1');

INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID, VER_NBR) VALUES ('KPME0936', (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KPME-HR' AND ROLE_NM = 'KOHR Institution View Only'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KPME-PM' AND NM = 'View Position Appointment'), 'Y', UUID(), '1');

INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID, VER_NBR) VALUES ('KPME0937', (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KPME-HR' AND ROLE_NM = 'KOHR Location Administrator'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KPME-PM' AND NM = 'View Position Appointment'), 'Y', UUID(), '1');

INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID, VER_NBR) VALUES ('KPME0938', (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KPME-HR' AND ROLE_NM = 'KOHR Location View Only'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KPME-PM' AND NM = 'View Position Appointment'), 'Y', UUID(), '1');

INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID, VER_NBR) VALUES ('KPME0939', (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KPME-HR' AND ROLE_NM = 'KOHR Organization Administrator'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KPME-PM' AND NM = 'View Position Appointment'), 'Y', UUID(), '1');

INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID, VER_NBR) VALUES ('KPME0940', (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KPME-HR' AND ROLE_NM = 'KOHR Organization View Only'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KPME-PM' AND NM = 'View Position Appointment'), 'Y', UUID(), '1');

INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID, VER_NBR) VALUES ('KPME0941', (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KPME-HR' AND ROLE_NM = 'HR Department Administrator'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KPME-PM' AND NM = 'View Position Appointment'), 'Y', UUID(), '1');

INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID, VER_NBR) VALUES ('KPME0942', (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KPME-HR' AND ROLE_NM = 'HR Department View Only'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KPME-PM' AND NM = 'View Position Appointment'), 'Y', UUID(), '1');

INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID, VER_NBR) VALUES ('KPME0943', (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KPME-HR' AND ROLE_NM = 'HR Institution Approver'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KPME-PM' AND NM = 'View Position Appointment'), 'Y', UUID(), '1');

INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID, VER_NBR) VALUES ('KPME0944', (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KPME-HR' AND ROLE_NM = 'Academic HR Institution Approver'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KPME-PM' AND NM = 'View Position Appointment'), 'Y', UUID(), '1');

INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID, VER_NBR) VALUES ('KPME0945', (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KPME-HR' AND ROLE_NM = 'HR Location Approver'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KPME-PM' AND NM = 'View Position Appointment'), 'Y', UUID(), '1');

INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID, VER_NBR) VALUES ('KPME0946', (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KPME-HR' AND ROLE_NM = 'Academic HR Location Approver'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KPME-PM' AND NM = 'View Position Appointment'), 'Y', UUID(), '1');

INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID, VER_NBR) VALUES ('KPME0947', (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KPME-HR' AND ROLE_NM = 'HR Organization Approver'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KPME-PM' AND NM = 'View Position Appointment'), 'Y', UUID(), '1');

INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID, VER_NBR) VALUES ('KPME0948', (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KPME-HR' AND ROLE_NM = 'Department Approver'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KPME-PM' AND NM = 'View Position Appointment'), 'Y', UUID(), '1');


--  Changeset org/kuali/kpme/kpme-db/db/2.1.1/db.changelog-201409221035.xml::1::neerajsk
--  Create the KPME Employee Setup Context in the KPME-HR namespace and set the (pre-defined) rule and action types as valid for this context
INSERT INTO KRMS_CNTXT_T (CNTXT_ID, NMSPC_CD, NM, TYP_ID, ACTV, VER_NBR, DESC_TXT) VALUES ('KPME-EMP-SETUP-CONTEXT', 'KPME-HR', 'KPME Employee Setup Context', 'KPME0001', 'Y', '0', 'KPME context for Employee Setup');

INSERT INTO KRMS_CNTXT_VLD_RULE_TYP_T (CNTXT_VLD_RULE_ID, CNTXT_ID, RULE_TYP_ID) VALUES ('KPME0006', 'KPME-EMP-SETUP-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Validation Rule' and NMSPC_CD='KR-RULE'));

INSERT INTO KRMS_CNTXT_VLD_ACTN_TYP_T (CNTXT_VLD_ACTN_ID, CNTXT_ID, ACTN_TYP_ID) VALUES ('KPME0019', 'KPME-EMP-SETUP-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Validation Action' and NMSPC_CD='KR-RULE'));

INSERT INTO KRMS_CNTXT_VLD_ACTN_TYP_T (CNTXT_VLD_ACTN_ID, CNTXT_ID, ACTN_TYP_ID) VALUES ('KPME0020', 'KPME-EMP-SETUP-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Notify PeopleFlow' and NMSPC_CD='KR-RULE'));

INSERT INTO KRMS_CNTXT_VLD_ACTN_TYP_T (CNTXT_VLD_ACTN_ID, CNTXT_ID, ACTN_TYP_ID) VALUES ('KPME0021', 'KPME-EMP-SETUP-CONTEXT', (select TYP_ID from KRMS_TYP_T where NM='Route to PeopleFlow' and NMSPC_CD='KR-RULE'));


--  Changeset org/kuali/kpme/kpme-db/db/2.1.1/db.changelog-201409221035.xml::2::neerajsk
--  Create the term specification for the employee setup process
INSERT INTO KRMS_TERM_SPEC_T (TERM_SPEC_ID, NM, TYP, ACTV, VER_NBR, DESC_TXT, NMSPC_CD) VALUES ('KPME0014', 'employeeSetupProcess', 'java.lang.String', 'Y', '1', 'The process used when setting up the employee details', 'KPME-HR');


--  Changeset org/kuali/kpme/kpme-db/db/2.1.1/db.changelog-201409221035.xml::3::neerajsk
--  Create the actual term for the setup process, based on the above specification, so it can be used as a parameter in propositions for agenda rules
INSERT INTO KRMS_TERM_T (TERM_ID, TERM_SPEC_ID, VER_NBR, DESC_TXT) VALUES ('KPME0006', 'KPME0014', '1', 'The process used when setting up the employee details');


--  Changeset org/kuali/kpme/kpme-db/db/2.1.1/db.changelog-201409221035.xml::4::neerajsk
--  Set the above process term specification as valid for the employee setup context, so it can be used in agendas for that context
INSERT INTO KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) VALUES ('KPME0024', 'KPME-EMP-SETUP-CONTEXT', 'KPME0014', 'N');


--  Changeset org/kuali/kpme/kpme-db/db/2.1.1/db.changelog-201409221035.xml::5::neerajsk
--  Create a proposition that checks if the value of the setup process is 'New job/Payrate', and use that proposition in a new rule.
INSERT INTO KRMS_PROP_T (PROP_ID, DESC_TXT, DSCRM_TYP_CD, RULE_ID, VER_NBR) VALUES ('KPME0007', 'Is the setup process for new job or payrate?', 'S', 'KPME0024', '0');

INSERT INTO KRMS_PROP_PARM_T (PROP_PARM_ID, PROP_ID, PARM_VAL, PARM_TYP_CD, SEQ_NO, VER_NBR) VALUES ('KPME0019', 'KPME0007', 'KPME0006', 'T', '0', '0');

INSERT INTO KRMS_PROP_PARM_T (PROP_PARM_ID, PROP_ID, PARM_VAL, PARM_TYP_CD, SEQ_NO, VER_NBR) VALUES ('KPME0020', 'KPME0007', '=', 'O', '2', '0');

INSERT INTO KRMS_PROP_PARM_T (PROP_PARM_ID, PROP_ID, PARM_VAL, PARM_TYP_CD, SEQ_NO, VER_NBR) VALUES ('KPME0021', 'KPME0007', 'New Job/Payrate', 'C', '1', '0');

INSERT INTO KRMS_RULE_T (RULE_ID, NMSPC_CD, NM, DESC_TXT, PROP_ID, ACTV, VER_NBR) VALUES ('KPME0024', 'KPME-HR', 'Routing for new job or pay rate', 'Process selected in employee setup doc was New Job/Payrate', 'KPME0007', 'Y', '0');


--  Changeset org/kuali/kpme/kpme-db/db/2.1.1/db.changelog-201409221035.xml::6::neerajsk
--  Create an agenda for the employee setup, and its initial agenda item (pointing to the rule created above).
INSERT INTO KRMS_AGENDA_T (AGENDA_ID, NM, CNTXT_ID, INIT_AGENDA_ITM_ID, ACTV) VALUES ('KPME0007', 'KPME Employee Setup Agenda', 'KPME-EMP-SETUP-CONTEXT', 'KPME0024', 'Y');

INSERT INTO KRMS_AGENDA_ITM_T (AGENDA_ITM_ID, RULE_ID, AGENDA_ID, VER_NBR) VALUES ('KPME0024', 'KPME0024', 'KPME0007', '1');


--  Changeset org/kuali/kpme/kpme-db/db/2.1.1/db.changelog-201409221035.xml::7::neerajsk
--  Create a proposition that checks if the value of the setup process is 'Change Job Dates', and use that proposition in a new rule.
INSERT INTO KRMS_PROP_T (PROP_ID, DESC_TXT, DSCRM_TYP_CD, RULE_ID, VER_NBR) VALUES ('KPME0008', 'Is the setup process for changing job dates?', 'S', 'KPME0025', '0');

INSERT INTO KRMS_PROP_PARM_T (PROP_PARM_ID, PROP_ID, PARM_VAL, PARM_TYP_CD, SEQ_NO, VER_NBR) VALUES ('KPME0022', 'KPME0008', 'KPME0006', 'T', '0', '0');

INSERT INTO KRMS_PROP_PARM_T (PROP_PARM_ID, PROP_ID, PARM_VAL, PARM_TYP_CD, SEQ_NO, VER_NBR) VALUES ('KPME0023', 'KPME0008', '=', 'O', '2', '0');

INSERT INTO KRMS_PROP_PARM_T (PROP_PARM_ID, PROP_ID, PARM_VAL, PARM_TYP_CD, SEQ_NO, VER_NBR) VALUES ('KPME0024', 'KPME0008', 'Change Job Dates', 'C', '1', '0');

INSERT INTO KRMS_RULE_T (RULE_ID, NMSPC_CD, NM, DESC_TXT, PROP_ID, ACTV, VER_NBR) VALUES ('KPME0025', 'KPME-HR', 'Routing for changing job dates', 'Process selected in employee setup doc was Change Job Dates', 'KPME0008', 'Y', '0');


--  Changeset org/kuali/kpme/kpme-db/db/2.1.1/db.changelog-201409221035.xml::8::neerajsk
--  Create an agenda item that points to above rule and update the when-false branch of the previous agenda item to point to this item.
INSERT INTO KRMS_AGENDA_ITM_T (AGENDA_ITM_ID, RULE_ID, AGENDA_ID, VER_NBR) VALUES ('KPME0025', 'KPME0025', 'KPME0007', '1');

UPDATE KRMS_AGENDA_ITM_T SET WHEN_FALSE = 'KPME0025' WHERE AGENDA_ITM_ID = 'KPME0024';


--  Changeset org/kuali/kpme/kpme-db/db/2.1.1/db.changelog-201409221035.xml::9::neerajsk
--  Create a proposition that checks if the value of the setup process is a 'Update Job Details', and use that proposition in a new rule.
INSERT INTO KRMS_PROP_T (PROP_ID, DESC_TXT, DSCRM_TYP_CD, RULE_ID, VER_NBR) VALUES ('KPME0009', 'Is the setup process for updating job details?', 'S', 'KPME0026', '0');

INSERT INTO KRMS_PROP_PARM_T (PROP_PARM_ID, PROP_ID, PARM_VAL, PARM_TYP_CD, SEQ_NO, VER_NBR) VALUES ('KPME0025', 'KPME0009', 'KPME0006', 'T', '0', '0');

INSERT INTO KRMS_PROP_PARM_T (PROP_PARM_ID, PROP_ID, PARM_VAL, PARM_TYP_CD, SEQ_NO, VER_NBR) VALUES ('KPME0026', 'KPME0009', '=', 'O', '2', '0');

INSERT INTO KRMS_PROP_PARM_T (PROP_PARM_ID, PROP_ID, PARM_VAL, PARM_TYP_CD, SEQ_NO, VER_NBR) VALUES ('KPME0027', 'KPME0009', 'Update Job Details', 'C', '1', '0');

INSERT INTO KRMS_RULE_T (RULE_ID, NMSPC_CD, NM, DESC_TXT, PROP_ID, ACTV, VER_NBR) VALUES ('KPME0026', 'KPME-HR', 'Routing for updating job details', 'Process selected in employee setup doc was Update Job Details', 'KPME0009', 'Y', '0');


--  Changeset org/kuali/kpme/kpme-db/db/2.1.1/db.changelog-201409221035.xml::10::neerajsk
--  Create an agenda item that points to above rule and update the when-false branch of the previous agenda item to point to this item.
INSERT INTO KRMS_AGENDA_ITM_T (AGENDA_ITM_ID, RULE_ID, AGENDA_ID, VER_NBR) VALUES ('KPME0026', 'KPME0026', 'KPME0007', '1');

UPDATE KRMS_AGENDA_ITM_T SET WHEN_FALSE = 'KPME0026' WHERE AGENDA_ITM_ID = 'KPME0025';


--  Changeset org/kuali/kpme/kpme-db/db/2.1.1/db.changelog-201409221035.xml::11::neerajsk
--  Create a proposition that checks if the value of the setup process is a 'End Job', and use that proposition in a new rule.
INSERT INTO KRMS_PROP_T (PROP_ID, DESC_TXT, DSCRM_TYP_CD, RULE_ID, VER_NBR) VALUES ('KPME0010', 'Is the setup process for ending job?', 'S', 'KPME0027', '0');

INSERT INTO KRMS_PROP_PARM_T (PROP_PARM_ID, PROP_ID, PARM_VAL, PARM_TYP_CD, SEQ_NO, VER_NBR) VALUES ('KPME0028', 'KPME0010', 'KPME0006', 'T', '0', '0');

INSERT INTO KRMS_PROP_PARM_T (PROP_PARM_ID, PROP_ID, PARM_VAL, PARM_TYP_CD, SEQ_NO, VER_NBR) VALUES ('KPME0029', 'KPME0010', '=', 'O', '2', '0');

INSERT INTO KRMS_PROP_PARM_T (PROP_PARM_ID, PROP_ID, PARM_VAL, PARM_TYP_CD, SEQ_NO, VER_NBR) VALUES ('KPME0030', 'KPME0010', 'End Job', 'C', '1', '0');

INSERT INTO KRMS_RULE_T (RULE_ID, NMSPC_CD, NM, DESC_TXT, PROP_ID, ACTV, VER_NBR) VALUES ('KPME0027', 'KPME-HR', 'Routing for ending job', 'Process selected in employee setup doc was End Job', 'KPME0010', 'Y', '0');


--  Changeset org/kuali/kpme/kpme-db/db/2.1.1/db.changelog-201409221035.xml::12::neerajsk
--  Create an agenda item that points to above rule and update the when-false branch of the previous agenda item to point to this item.
INSERT INTO KRMS_AGENDA_ITM_T (AGENDA_ITM_ID, RULE_ID, AGENDA_ID, VER_NBR) VALUES ('KPME0027', 'KPME0027', 'KPME0007', '1');

UPDATE KRMS_AGENDA_ITM_T SET WHEN_FALSE = 'KPME0027' WHERE AGENDA_ITM_ID = 'KPME0026';


--  Changeset org/kuali/kpme/kpme-db/db/2.1.1/db.changelog-201409221035.xml::13::neerajsk
--  Create a maintain KPME-HR agenda permission, and assign it to three sys-admin roles: position, leave and time.
INSERT INTO KRIM_PERM_T (PERM_ID, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND, OBJ_ID, VER_NBR) VALUES ('KPME0191', (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-RULE' AND NM = 'KRMS Agenda Permission'), 'KPME-HR', 'Maintain KRMS Agenda', 'Allows users to maintain any KPME-HR namespace agendas.', 'Y', UUID(), '1');

INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID, VER_NBR) VALUES ('KPME0949', (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KPME-PM' AND ROLE_NM = 'Position System Administrator'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KPME-HR' AND NM = 'Maintain KRMS Agenda'), 'Y', UUID(), '1');

INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID, VER_NBR) VALUES ('KPME0950', (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KPME-TK' AND ROLE_NM = 'Time System Administrator'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KPME-HR' AND NM = 'Maintain KRMS Agenda'), 'Y', UUID(), '1');

INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID, VER_NBR) VALUES ('KPME0951', (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KPME-LM' AND ROLE_NM = 'Leave System Administrator'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KPME-HR' AND NM = 'Maintain KRMS Agenda'), 'Y', UUID(), '1');


--  Release Database Lock
--  Release Database Lock
