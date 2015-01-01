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


ALTER TABLE KREN_CHNL_PRODCR_T
    ADD CONSTRAINT KREN_CHNL_PRODCR_FK2 FOREIGN KEY (PRODCR_ID)
    REFERENCES KREN_PRODCR_T (PRODCR_ID)
/

ALTER TABLE KREN_CHNL_PRODCR_T
    ADD CONSTRAINT KREN_CHNL_PRODCR_FK1 FOREIGN KEY (CHNL_ID)
    REFERENCES KREN_CHNL_T (CHNL_ID)
/


ALTER TABLE KREN_CHNL_SUBSCRP_T
    ADD CONSTRAINT KREN_CHAN_SUBSCRP_FK1 FOREIGN KEY (CHNL_ID)
    REFERENCES KREN_CHNL_T (CHNL_ID)
/




ALTER TABLE KREN_MSG_DELIV_T
    ADD CONSTRAINT KREN_MSG_DELIV_FK1 FOREIGN KEY (MSG_ID)
    REFERENCES KREN_MSG_T (MSG_ID)
/



ALTER TABLE KREN_NTFCTN_MSG_DELIV_T
    ADD CONSTRAINT KREN_NTFCTN_MSG_DELIV_FK1 FOREIGN KEY (NTFCTN_ID)
    REFERENCES KREN_NTFCTN_T (NTFCTN_ID)
/


ALTER TABLE KREN_NTFCTN_T
    ADD CONSTRAINT KREN_NTFCTN_FK4 FOREIGN KEY (PRODCR_ID)
    REFERENCES KREN_PRODCR_T (PRODCR_ID)
/

ALTER TABLE KREN_NTFCTN_T
    ADD CONSTRAINT KREN_NTFCTN_FK3 FOREIGN KEY (PRIO_ID)
    REFERENCES KREN_PRIO_T (PRIO_ID)
/

ALTER TABLE KREN_NTFCTN_T
    ADD CONSTRAINT KREN_NTFCTN_FK2 FOREIGN KEY (CNTNT_TYP_ID)
    REFERENCES KREN_CNTNT_TYP_T (CNTNT_TYP_ID)
/

ALTER TABLE KREN_NTFCTN_T
    ADD CONSTRAINT KREN_NTFCTN_FK1 FOREIGN KEY (CHNL_ID)
    REFERENCES KREN_CHNL_T (CHNL_ID)
/





ALTER TABLE KREN_RECIP_LIST_T
    ADD CONSTRAINT KREN_RECIP_LIST_FK1 FOREIGN KEY (CHNL_ID)
    REFERENCES KREN_CHNL_T (CHNL_ID)
/



ALTER TABLE KREN_RECIP_T
    ADD CONSTRAINT KREN_RECIP_FK1 FOREIGN KEY (NTFCTN_ID)
    REFERENCES KREN_NTFCTN_T (NTFCTN_ID)
/


ALTER TABLE KREN_RVWER_T
    ADD CONSTRAINT NOTIFICATION_REVIEWERS_N_FK1 FOREIGN KEY (CHNL_ID)
    REFERENCES KREN_CHNL_T (CHNL_ID)
/


ALTER TABLE KREN_SNDR_T
    ADD CONSTRAINT KREN_SNDR_FK1 FOREIGN KEY (NTFCTN_ID)
    REFERENCES KREN_NTFCTN_T (NTFCTN_ID)
/




































ALTER TABLE KREW_RTE_NODE_CFG_PARM_T
    ADD CONSTRAINT EN_RTE_NODE_CFG_PARM_TR1 FOREIGN KEY (RTE_NODE_ID)
    REFERENCES KREW_RTE_NODE_T (RTE_NODE_ID)
/












ALTER TABLE KREW_RULE_T
    ADD CONSTRAINT KREW_RULE_TR1 FOREIGN KEY (RULE_EXPR_ID)
    REFERENCES KREW_RULE_EXPR_T (RULE_EXPR_ID)
/











ALTER TABLE KRIM_DLGN_MBR_ATTR_DATA_T
    ADD CONSTRAINT KRIM_DLGN_MBR_ATTR_DATA_TR2 FOREIGN KEY (KIM_ATTR_DEFN_ID)
    REFERENCES KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID)
/

ALTER TABLE KRIM_DLGN_MBR_ATTR_DATA_T
    ADD CONSTRAINT KRIM_DLGN_MBR_ATTR_DATA_TR1 FOREIGN KEY (KIM_TYP_ID)
    REFERENCES KRIM_TYP_T (KIM_TYP_ID)
/


ALTER TABLE KRIM_DLGN_MBR_T
    ADD CONSTRAINT KRIM_DLGN_MBR_TR2 FOREIGN KEY (DLGN_ID)
    REFERENCES KRIM_DLGN_T (DLGN_ID)
/


ALTER TABLE KRIM_DLGN_T
    ADD CONSTRAINT KRIM_DLGN_TR1 FOREIGN KEY (ROLE_ID)
    REFERENCES KRIM_ROLE_T (ROLE_ID)
/

ALTER TABLE KRIM_DLGN_T
    ADD CONSTRAINT KRIM_DLGN_TR2 FOREIGN KEY (KIM_TYP_ID)
    REFERENCES KRIM_TYP_T (KIM_TYP_ID)
/





ALTER TABLE KRIM_ENTITY_ADDR_T
    ADD CONSTRAINT KRIM_ENTITY_ADDR_TR1 FOREIGN KEY (ENT_TYP_CD, ENTITY_ID)
    REFERENCES KRIM_ENTITY_ENT_TYP_T (ENT_TYP_CD, ENTITY_ID)
ON DELETE CASCADE
/

ALTER TABLE KRIM_ENTITY_ADDR_T
    ADD CONSTRAINT KRIM_ENTITY_ADDR_TR2 FOREIGN KEY (ADDR_TYP_CD)
    REFERENCES KRIM_ADDR_TYP_T (ADDR_TYP_CD)
/


ALTER TABLE KRIM_ENTITY_AFLTN_T
    ADD CONSTRAINT KRIM_ENTITY_AFLTN_TR1 FOREIGN KEY (ENTITY_ID)
    REFERENCES KRIM_ENTITY_T (ENTITY_ID)
ON DELETE CASCADE
/

ALTER TABLE KRIM_ENTITY_AFLTN_T
    ADD CONSTRAINT KRIM_ENTITY_AFLTN_TR2 FOREIGN KEY (AFLTN_TYP_CD)
    REFERENCES KRIM_AFLTN_TYP_T (AFLTN_TYP_CD)
/




ALTER TABLE KRIM_ENTITY_CTZNSHP_T
    ADD CONSTRAINT KRIM_ENTITY_CTZNSHP_TR1 FOREIGN KEY (ENTITY_ID)
    REFERENCES KRIM_ENTITY_T (ENTITY_ID)
ON DELETE CASCADE
/

ALTER TABLE KRIM_ENTITY_CTZNSHP_T
    ADD CONSTRAINT KRIM_ENTITY_CTZNSHP_TR2 FOREIGN KEY (CTZNSHP_STAT_CD)
    REFERENCES KRIM_CTZNSHP_STAT_T (CTZNSHP_STAT_CD)
/


ALTER TABLE KRIM_ENTITY_EMAIL_T
    ADD CONSTRAINT KRIM_ENTITY_EMAIL_TR1 FOREIGN KEY (ENT_TYP_CD, ENTITY_ID)
    REFERENCES KRIM_ENTITY_ENT_TYP_T (ENT_TYP_CD, ENTITY_ID)
ON DELETE CASCADE
/


ALTER TABLE KRIM_ENTITY_EMP_INFO_T
    ADD CONSTRAINT KRIM_ENTITY_EMP_INFO_TR4 FOREIGN KEY (ENTITY_AFLTN_ID)
    REFERENCES KRIM_ENTITY_AFLTN_T (ENTITY_AFLTN_ID)
/

ALTER TABLE KRIM_ENTITY_EMP_INFO_T
    ADD CONSTRAINT KRIM_ENTITY_EMP_INFO_TR1 FOREIGN KEY (ENTITY_ID)
    REFERENCES KRIM_ENTITY_T (ENTITY_ID)
ON DELETE CASCADE
/

ALTER TABLE KRIM_ENTITY_EMP_INFO_T
    ADD CONSTRAINT KRIM_ENTITY_EMP_INFO_TR2 FOREIGN KEY (EMP_STAT_CD)
    REFERENCES KRIM_EMP_STAT_T (EMP_STAT_CD)
/

ALTER TABLE KRIM_ENTITY_EMP_INFO_T
    ADD CONSTRAINT KRIM_ENTITY_EMP_INFO_TR3 FOREIGN KEY (EMP_TYP_CD)
    REFERENCES KRIM_EMP_TYP_T (EMP_TYP_CD)
/


ALTER TABLE KRIM_ENTITY_ENT_TYP_T
    ADD CONSTRAINT KRIM_ENTITY_ENT_TYP_TR2 FOREIGN KEY (ENT_TYP_CD)
    REFERENCES KRIM_ENT_TYP_T (ENT_TYP_CD)
/

ALTER TABLE KRIM_ENTITY_ENT_TYP_T
    ADD CONSTRAINT KRIM_ENTITY_ENT_TYP_TR1 FOREIGN KEY (ENTITY_ID)
    REFERENCES KRIM_ENTITY_T (ENTITY_ID)
ON DELETE CASCADE
/


ALTER TABLE KRIM_ENTITY_ETHNIC_T
    ADD CONSTRAINT KRIM_ENTITY_ETHNIC_TR1 FOREIGN KEY (ENTITY_ID)
    REFERENCES KRIM_ENTITY_T (ENTITY_ID)
/


ALTER TABLE KRIM_ENTITY_EXT_ID_T
    ADD CONSTRAINT KRIM_ENTITY_EXT_ID_TR2 FOREIGN KEY (EXT_ID_TYP_CD)
    REFERENCES KRIM_EXT_ID_TYP_T (EXT_ID_TYP_CD)
/

ALTER TABLE KRIM_ENTITY_EXT_ID_T
    ADD CONSTRAINT KRIM_ENTITY_EXT_ID_TR1 FOREIGN KEY (ENTITY_ID)
    REFERENCES KRIM_ENTITY_T (ENTITY_ID)
ON DELETE CASCADE
/


ALTER TABLE KRIM_ENTITY_NM_T
    ADD CONSTRAINT KRIM_ENTITY_NM_TR1 FOREIGN KEY (ENTITY_ID)
    REFERENCES KRIM_ENTITY_T (ENTITY_ID)
ON DELETE CASCADE
/


ALTER TABLE KRIM_ENTITY_PHONE_T
    ADD CONSTRAINT KRIM_ENTITY_PHONE_TR1 FOREIGN KEY (ENT_TYP_CD, ENTITY_ID)
    REFERENCES KRIM_ENTITY_ENT_TYP_T (ENT_TYP_CD, ENTITY_ID)
ON DELETE CASCADE
/

ALTER TABLE KRIM_ENTITY_PHONE_T
    ADD CONSTRAINT KRIM_ENTITY_PHONE_TR2 FOREIGN KEY (PHONE_TYP_CD)
    REFERENCES KRIM_PHONE_TYP_T (PHONE_TYP_CD)
/



ALTER TABLE KRIM_ENTITY_RESIDENCY_T
    ADD CONSTRAINT KRIM_ENTITY_RESIDENCY_TR1 FOREIGN KEY (ENTITY_ID)
    REFERENCES KRIM_ENTITY_T (ENTITY_ID)
/



ALTER TABLE KRIM_ENTITY_VISA_T
    ADD CONSTRAINT KRIM_ENTITY_VISA_TR1 FOREIGN KEY (ENTITY_ID)
    REFERENCES KRIM_ENTITY_T (ENTITY_ID)
/





ALTER TABLE KRIM_GRP_ATTR_DATA_T
    ADD CONSTRAINT KRIM_GRP_ATTR_DATA_TR1 FOREIGN KEY (KIM_TYP_ID)
    REFERENCES KRIM_TYP_T (KIM_TYP_ID)
/

ALTER TABLE KRIM_GRP_ATTR_DATA_T
    ADD CONSTRAINT KRIM_GRP_ATTR_DATA_TR3 FOREIGN KEY (GRP_ID)
    REFERENCES KRIM_GRP_T (GRP_ID)
ON DELETE CASCADE
/

ALTER TABLE KRIM_GRP_ATTR_DATA_T
    ADD CONSTRAINT KRIM_GRP_ATTR_DATA_TR2 FOREIGN KEY (KIM_ATTR_DEFN_ID)
    REFERENCES KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID)
/



ALTER TABLE KRIM_GRP_MBR_T
    ADD CONSTRAINT KRIM_GRP_MBR_TR1 FOREIGN KEY (GRP_ID)
    REFERENCES KRIM_GRP_T (GRP_ID)
ON DELETE CASCADE
/


ALTER TABLE KRIM_GRP_T
    ADD CONSTRAINT KRIM_GRP_TR1 FOREIGN KEY (KIM_TYP_ID)
    REFERENCES KRIM_TYP_T (KIM_TYP_ID)
/


ALTER TABLE KRIM_PERM_ATTR_DATA_T
    ADD CONSTRAINT KRIM_PERM_ATTR_DATA_TR1 FOREIGN KEY (KIM_TYP_ID)
    REFERENCES KRIM_TYP_T (KIM_TYP_ID)
/

ALTER TABLE KRIM_PERM_ATTR_DATA_T
    ADD CONSTRAINT KRIM_PERM_ATTR_DATA_TR3 FOREIGN KEY (PERM_ID)
    REFERENCES KRIM_PERM_T (PERM_ID)
ON DELETE CASCADE
/

ALTER TABLE KRIM_PERM_ATTR_DATA_T
    ADD CONSTRAINT KRIM_PERM_ATTR_DATA_TR2 FOREIGN KEY (KIM_ATTR_DEFN_ID)
    REFERENCES KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID)
/


ALTER TABLE KRIM_PERM_T
    ADD CONSTRAINT KRIM_PERM_TR1 FOREIGN KEY (PERM_TMPL_ID)
    REFERENCES KRIM_PERM_TMPL_T (PERM_TMPL_ID)
/


ALTER TABLE KRIM_PERM_TMPL_T
    ADD CONSTRAINT KRIM_PERM_TMPL_TR1 FOREIGN KEY (KIM_TYP_ID)
    REFERENCES KRIM_TYP_T (KIM_TYP_ID)
/





ALTER TABLE KRIM_PND_AFLTN_MT
    ADD CONSTRAINT KRIM_PND_AFLTN_MT_FK1 FOREIGN KEY (FDOC_NBR)
    REFERENCES KRIM_PERSON_DOCUMENT_T (FDOC_NBR)
/




















ALTER TABLE KRIM_PRNCPL_T
    ADD CONSTRAINT KRIM_PRNCPL_TR1 FOREIGN KEY (ENTITY_ID)
    REFERENCES KRIM_ENTITY_T (ENTITY_ID)
ON DELETE CASCADE
/



ALTER TABLE KRIM_ROLE_MBR_ATTR_DATA_T
    ADD CONSTRAINT KRIM_ROLE_MBR_ATTR_DATA_TR1 FOREIGN KEY (KIM_TYP_ID)
    REFERENCES KRIM_TYP_T (KIM_TYP_ID)
/

ALTER TABLE KRIM_ROLE_MBR_ATTR_DATA_T
    ADD CONSTRAINT KRIM_ROLE_MBR_ATTR_DATA_TR2 FOREIGN KEY (KIM_ATTR_DEFN_ID)
    REFERENCES KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID)
/


ALTER TABLE KRIM_ROLE_MBR_T
    ADD CONSTRAINT KRIM_ROLE_MBR_TR1 FOREIGN KEY (ROLE_ID)
    REFERENCES KRIM_ROLE_T (ROLE_ID)
ON DELETE CASCADE
/


ALTER TABLE KRIM_ROLE_PERM_T
    ADD CONSTRAINT KRIM_ROLE_PERM_TR1 FOREIGN KEY (PERM_ID)
    REFERENCES KRIM_PERM_T (PERM_ID)
/



ALTER TABLE KRIM_ROLE_RSP_T
    ADD CONSTRAINT KRIM_ROLE_RSP_TR1 FOREIGN KEY (RSP_ID)
    REFERENCES KRIM_RSP_T (RSP_ID)
/


ALTER TABLE KRIM_ROLE_T
    ADD CONSTRAINT KRIM_ROLE_TR1 FOREIGN KEY (KIM_TYP_ID)
    REFERENCES KRIM_TYP_T (KIM_TYP_ID)
/


ALTER TABLE KRIM_RSP_ATTR_DATA_T
    ADD CONSTRAINT KRIM_RSP_ATTR_DATA_TR3 FOREIGN KEY (RSP_ID)
    REFERENCES KRIM_RSP_T (RSP_ID)
ON DELETE CASCADE
/

ALTER TABLE KRIM_RSP_ATTR_DATA_T
    ADD CONSTRAINT KRIM_RSP_ATTR_DATA_TR1 FOREIGN KEY (KIM_TYP_ID)
    REFERENCES KRIM_TYP_T (KIM_TYP_ID)
/

ALTER TABLE KRIM_RSP_ATTR_DATA_T
    ADD CONSTRAINT KRIM_RSP_ATTR_DATA_TR2 FOREIGN KEY (KIM_ATTR_DEFN_ID)
    REFERENCES KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID)
/


ALTER TABLE KRIM_RSP_T
    ADD CONSTRAINT KRIM_RSP_TR1 FOREIGN KEY (RSP_TMPL_ID)
    REFERENCES KRIM_RSP_TMPL_T (RSP_TMPL_ID)
/


ALTER TABLE KRIM_RSP_TMPL_T
    ADD CONSTRAINT KRIM_RSP_TMPL_TR1 FOREIGN KEY (KIM_TYP_ID)
    REFERENCES KRIM_TYP_T (KIM_TYP_ID)
/


ALTER TABLE KRIM_TYP_ATTR_T
    ADD CONSTRAINT KRIM_TYP_ATTRIBUTE_TR1 FOREIGN KEY (KIM_TYP_ID)
    REFERENCES KRIM_TYP_T (KIM_TYP_ID)
ON DELETE CASCADE
/

ALTER TABLE KRIM_TYP_ATTR_T
    ADD CONSTRAINT KRIM_TYP_ATTR_TR2 FOREIGN KEY (KIM_ATTR_DEFN_ID)
    REFERENCES KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID)
ON DELETE CASCADE
/




ALTER TABLE KRNS_ATT_T
    ADD CONSTRAINT KRNS_ATT_TR1 FOREIGN KEY (NTE_ID)
    REFERENCES KRNS_NTE_T (NTE_ID)
/


ALTER TABLE KRNS_CAMPUS_T
    ADD CONSTRAINT SH_CAMPUS_TR1 FOREIGN KEY (CAMPUS_TYP_CD)
    REFERENCES KRNS_CMP_TYP_T (CAMPUS_TYP_CD)
/







ALTER TABLE KRNS_MAINT_DOC_T
    ADD CONSTRAINT KRNS_MAINT_DOC_TR1 FOREIGN KEY (DOC_HDR_ID)
    REFERENCES KRNS_DOC_HDR_T (DOC_HDR_ID)
/




ALTER TABLE KRNS_NTE_T
    ADD CONSTRAINT KRNS_NTE_TR1 FOREIGN KEY (NTE_TYP_CD)
    REFERENCES KRNS_NTE_TYP_T (NTE_TYP_CD)
/



ALTER TABLE KRNS_PARM_DTL_TYP_T
    ADD CONSTRAINT KRNS_PARM_DTL_TYP_TR1 FOREIGN KEY (NMSPC_CD)
    REFERENCES KRNS_NMSPC_T (NMSPC_CD)
/


ALTER TABLE KRNS_PARM_T
    ADD CONSTRAINT KRNS_PARM_TR2 FOREIGN KEY (PARM_TYP_CD)
    REFERENCES KRNS_PARM_TYP_T (PARM_TYP_CD)
/

ALTER TABLE KRNS_PARM_T
    ADD CONSTRAINT KRNS_PARM_TR1 FOREIGN KEY (NMSPC_CD)
    REFERENCES KRNS_NMSPC_T (NMSPC_CD)
/










ALTER TABLE KRSB_QRTZ_BLOB_TRIGGERS
    ADD CONSTRAINT KRSB_QRTZ_BLOB_TRIGGERS_TR1 FOREIGN KEY (TRIGGER_NAME, TRIGGER_GROUP)
    REFERENCES KRSB_QRTZ_TRIGGERS (TRIGGER_NAME, TRIGGER_GROUP)
/



ALTER TABLE KRSB_QRTZ_CRON_TRIGGERS
    ADD CONSTRAINT KRSB_QRTZ_CRON_TRIGGERS_TR1 FOREIGN KEY (TRIGGER_NAME, TRIGGER_GROUP)
    REFERENCES KRSB_QRTZ_TRIGGERS (TRIGGER_NAME, TRIGGER_GROUP)
/




ALTER TABLE KRSB_QRTZ_JOB_LISTENERS
    ADD CONSTRAINT KRSB_QUARTZ_JOB_LISTENERS_TR1 FOREIGN KEY (JOB_NAME, JOB_GROUP)
    REFERENCES KRSB_QRTZ_JOB_DETAILS (JOB_NAME, JOB_GROUP)
/





ALTER TABLE KRSB_QRTZ_SIMPLE_TRIGGERS
    ADD CONSTRAINT KRSB_QRTZ_SIMPLE_TRIGGERS_TR1 FOREIGN KEY (TRIGGER_NAME, TRIGGER_GROUP)
    REFERENCES KRSB_QRTZ_TRIGGERS (TRIGGER_NAME, TRIGGER_GROUP)
/



ALTER TABLE KRSB_QRTZ_TRIGGER_LISTENERS
    ADD CONSTRAINT KRSB_QRTZ_TRIGGER_LISTENE_TR1 FOREIGN KEY (TRIGGER_NAME, TRIGGER_GROUP)
    REFERENCES KRSB_QRTZ_TRIGGERS (TRIGGER_NAME, TRIGGER_GROUP)
/





ALTER TABLE KR_POSTAL_CODE_T
    ADD CONSTRAINT KR_POSTAL_CODE_TR3 FOREIGN KEY (COUNTY_CD, POSTAL_STATE_CD, POSTAL_CNTRY_CD)
    REFERENCES KR_COUNTY_T (COUNTY_CD, STATE_CD, POSTAL_CNTRY_CD)
/

ALTER TABLE KR_POSTAL_CODE_T
    ADD CONSTRAINT KR_POSTAL_CODE_TR2 FOREIGN KEY (POSTAL_STATE_CD, POSTAL_CNTRY_CD)
    REFERENCES KR_STATE_T (POSTAL_STATE_CD, POSTAL_CNTRY_CD)
/

ALTER TABLE KR_POSTAL_CODE_T
    ADD CONSTRAINT KR_POSTAL_CODE_TR1 FOREIGN KEY (POSTAL_CNTRY_CD)
    REFERENCES KR_COUNTRY_T (POSTAL_CNTRY_CD)
/


ALTER TABLE KR_STATE_T
    ADD CONSTRAINT KR_STATE_TR1 FOREIGN KEY (POSTAL_CNTRY_CD)
    REFERENCES KR_COUNTRY_T (POSTAL_CNTRY_CD)
/
