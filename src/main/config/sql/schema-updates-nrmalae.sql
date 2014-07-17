--alter table
--edo_dossier_t
--add
--secondary_unit VARCHAR2(12 BYTE);
--
--CREATE OR REPLACE FORCE VIEW "EDO"."EDO_CANDIDATEDOSSIER_V" ("FIRST_NAME", "LAST_NAME", "USERNAME", "CANDIDACY_CAMPUS", "CANDIDACY_SCHOOL", "CANDIDACY_YEAR", "CANDIDATE_ID", "CURRENT_RANK", "PRIMARY_DEPARTMENT_ID", "SECONDARY_DEPARTMENT_ID", "DOSSIER_ID", "DOSSIER_STATUS", "RANK_SOUGHT", "DUE_DATE", "DOCUMENT_ID", "WORKFLOW_ID")
--AS
--SELECT c.first_name,
--c.last_name,
--c.username,
--c.candidacy_campus,
--c.candidacy_school,
--c.candidacy_year,
--c.candidate_id,
--c.current_rank,
--c.primary_department_id,
--d.secondary_unit,
--d.dossier_id,
--d.dossier_status,
--d.rank_sought,
--d.due_date,
--d.document_id,
--d.workflow_id
--FROM edo_candidate_t c
--INNER JOIN edo_dossier_t d
--ON c.username = d.candidate_username;

update edo_supp_review_layer_def_t set WORKFLOW_ID = 'DEFAULT', WORKFLOW_QUALIFIER = 'departmentId' where REVIEW_LAYER_DEF_ID in (1,2);
update edo_supp_review_layer_def_t set WORKFLOW_ID = 'DEFAULT', WORKFLOW_QUALIFIER = 'schoolId' where REVIEW_LAYER_DEF_ID in (3,4);
update edo_supp_review_layer_def_t set WORKFLOW_ID = 'DEFAULT', WORKFLOW_QUALIFIER = 'campusId' where REVIEW_LAYER_DEF_ID in (5,6);
commit;

alter table EDO_SUPP_REVIEW_LAYER_DEF_T add DESCRIPTION CLOB;
commit;

update EDO_SUPP_REVIEW_LAYER_DEF_T set description = 'Supplemental Acknowledgement - School' where supplemental_node_name = 'supplSchoolLevelAck';
update EDO_SUPP_REVIEW_LAYER_DEF_T set description = 'Supplemental FYI - School' where supplemental_node_name = 'supplSchoolLevel';
update EDO_SUPP_REVIEW_LAYER_DEF_T set description = 'Supplemental Acknowledgement - Dean' where supplemental_node_name = 'supplDeanAck';
update EDO_SUPP_REVIEW_LAYER_DEF_T set description = 'Supplemental FYI - Dean' where supplemental_node_name = 'supplDean';
update EDO_SUPP_REVIEW_LAYER_DEF_T set description = 'Supplemental Acknowledgement - Campus' where supplemental_node_name = 'supplCampusLevelAck';
update EDO_SUPP_REVIEW_LAYER_DEF_T set description = 'Supplemental FYI - Campus' where supplemental_node_name = 'supplCampusLevel';
update EDO_SUPP_REVIEW_LAYER_DEF_T set description = 'Supplemental Acknowledgement - VCAA/Vice Provost' where supplemental_node_name = 'supplViceProvostAck';
update EDO_SUPP_REVIEW_LAYER_DEF_T set description = 'Supplemental FYI - VCAA/Vice Provost' where supplemental_node_name = 'supplViceProvost';

update edo_supp_review_layer_def_t set supplemental_node_name = 'supplDeptChairLevelAck' where SUPP_REVIEW_LAYER_DEF_ID = 1;
update edo_supp_review_layer_def_t set supplemental_node_name = 'supplDeptChairLevel' where SUPP_REVIEW_LAYER_DEF_ID = 2;
commit;

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
(SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(13,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level3' and workflow_id = 'OPT'),'supplSchoolLevelAck','Y','OPT','schoolId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(14,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level3' and workflow_id = 'OPT'),'supplSchoolLevel','N','OPT','schoolId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(15,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level4' and workflow_id = 'OPT'),'supplDeanAck','Y','OPT','schoolId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(16,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level4' and workflow_id = 'OPT'),'supplDean','N','OPT','schoolId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(17,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level5' and workflow_id = 'OPT'),'supplCampusLevelAck','Y','OPT','campusId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(18,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level5' and workflow_id = 'OPT'),'supplCampusLevel','N','OPT','campusId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(19,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level6' and workflow_id = 'OPT'),'supplViceProvostAck','Y','OPT','campusId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(20,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level6' and workflow_id = 'OPT'),'supplViceProvost','N','OPT','campusId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(29,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level1' and workflow_id = 'SPEA'),'supplDeptChairLevelAck','Y','SPEA','departmentId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(30,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level1' and workflow_id = 'SPEA'),'supplDeptChairLevel','N','SPEA','departmentId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(31,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level2' and workflow_id = 'SPEA'),'supplDeptLevelAck','Y','SPEA','departmentId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(32,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level2' and workflow_id = 'SPEA'),'supplDeptLevel','N','SPEA','departmentId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(33,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level3' and workflow_id = 'SPEA'),'supplSchoolLevelAck','Y','SPEA','schoolId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(34,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level3' and workflow_id = 'SPEA'),'supplSchoolLevel','N','SPEA','schoolId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(35,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level4' and workflow_id = 'SPEA'),'supplDeanAck','Y','SPEA','schoolId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(36,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level4' and workflow_id = 'SPEA'),'supplDean','N','SPEA','schoolId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(37,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level5' and workflow_id = 'SPEA'),'supplCampusLevelAck','Y','SPEA','campusId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(38,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level5' and workflow_id = 'SPEA'),'supplCampusLevel','N','SPEA','campusId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(39,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level6' and workflow_id = 'SPEA'),'supplViceProvostAck','Y','SPEA','campusId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(40,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level6' and workflow_id = 'SPEA'),'supplViceProvost','N','SPEA','campusId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(41,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level1' and workflow_id = 'DEFAULT'),'supplDeptLevelAck','Y','DEFAULT','departmentId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(42,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level1' and workflow_id = 'DEFAULT'),'supplDeptLevel','N','DEFAULT','departmentId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(44,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level1' and workflow_id = 'JOUR'),'supplDeptLevelAck','N','JOUR','schoolId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(45,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level2' and workflow_id = 'JOUR'),'supplDeptChairLevelAck','Y','JOUR','schoolId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(46,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level2' and workflow_id = 'JOUR'),'supplDeptChairLevel','N','JOUR','schoolId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(47,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level3' and workflow_id = 'JOUR'),'supplSchoolLevelAck','Y','JOUR','schoolId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(48,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level3' and workflow_id = 'JOUR'),'supplSchoolLevel','N','JOUR','schoolId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(49,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level4' and workflow_id = 'JOUR'),'supplDeanAck','Y','JOUR','schoolId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(50,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level4' and workflow_id = 'JOUR'),'supplDean','N','JOUR','schoolId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(51,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level5' and workflow_id = 'JOUR'),'supplCampusLevelAck','Y','JOUR','campusId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(52,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level5' and workflow_id = 'JOUR'),'supplCampusLevel','N','JOUR','campusId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(53,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level6' and workflow_id = 'JOUR'),'supplViceProvostAck','Y','JOUR','campusId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(54,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level6' and workflow_id = 'JOUR'),'supplViceProvost','N','JOUR','campusId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(55,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level3' and workflow_id = 'KO'),'supplSchoolLevelAck','Y','KO','schoolId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(56,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level3' and workflow_id = 'KO'),'supplSchoolLevel','N','KO','schoolId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(57,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level4' and workflow_id = 'KO'),'supplDeanAck','Y','KO','schoolId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(58,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level4' and workflow_id = 'KO'),'supplDean','N','KO','schoolId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(59,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level5' and workflow_id = 'KO'),'supplCampusLevelAck','Y','KO','campusId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(60,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level5' and workflow_id = 'KO'),'supplCampusLevel','N','KO','campusId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(61,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level6' and workflow_id = 'KO'),'supplViceProvostAck','Y','KO','campusId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(62,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level6' and workflow_id = 'KO'),'supplViceProvost','N','KO','campusId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(63,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level1' and workflow_id = 'SCOTJONE'),'supplSchoolLevelAck','Y','SCOTJONE','schoolId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(64,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level1' and workflow_id = 'SCOTJONE'),'supplSchoolLevel','N','SCOTJONE','schoolId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(65,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level3' and workflow_id = 'SCOTJONE'),'supplDeanAck','Y','SCOTJONE','dossierId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(66,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level3' and workflow_id = 'SCOTJONE'),'supplDean','N','SCOTJONE','dossierId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(67,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level5' and workflow_id = 'SCOTJONE'),'supplCampusLevelAck','Y','SCOTJONE','campusId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(68,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level5' and workflow_id = 'SCOTJONE'),'supplCampusLevel','N','SCOTJONE','campusId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(69,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level6' and workflow_id = 'SCOTJONE'),'supplViceProvostAck','Y','SCOTJONE','campusId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(70,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level6' and workflow_id = 'SCOTJONE'),'supplViceProvost','N','SCOTJONE','campusId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(21,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level3' and workflow_id = 'LAW'),'supplSchoolLevelAck','Y','LAW','schoolId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(22,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level3' and workflow_id = 'LAW'),'supplSchoolLevel','N','LAW','schoolId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(23,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level4' and workflow_id = 'LAW'),'supplDeanAck','Y','LAW','schoolId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(24,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level4' and workflow_id = 'LAW'),'supplDean','N','LAW','schoolId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(25,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level5' and workflow_id = 'LAW'),'supplCampusLevelAck','Y','LAW','campusId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(26,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level5' and workflow_id = 'LAW'),'supplCampusLevel','N','LAW','campusId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(27,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level6' and workflow_id = 'LAW'),'supplViceProvostAck','Y','LAW','campusId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(28,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level6' and workflow_id = 'LAW'),'supplViceProvost','N','LAW','campusId');

Insert into EDO.EDO_SUPP_REVIEW_LAYER_DEF_T
 (SUPP_REVIEW_LAYER_DEF_ID,REVIEW_LAYER_DEF_ID,SUPPLEMENTAL_NODE_NAME,ACKNOWLEDGE_FLAG,WORKFLOW_ID,WORKFLOW_QUALIFIER)
values
(43,(SELECT review_layer_def_id from EDO.EDO_REVIEW_LAYER_DEF_T where NODE_NAME = 'Level1' and workflow_id = 'JOUR'),'supplDeptLevel','N','JOUR','schoolId');

COMMIT;

