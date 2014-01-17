--  *********************************************************************
--  Update Database Script
--  *********************************************************************
--  Change Log: src/main/config/db/db.changelog-main-upgrade.xml
--  Ran at: 1/17/14 9:28 AM
--  Against: krtt@localhost@jdbc:mysql://localhost/krtt
--  Liquibase version: 2.0.5
--  *********************************************************************


--  Changeset src/main/config/db/2.0.1/db.changelog-201401151035.xml::1::neerajsk::(Checksum: 3:b458706dfe70fea06c3421e3a462e7b0)
--  Adding Rice KIM type + KIM role bootstrap data for the KPME role-proxy derived role
INSERT INTO `KRIM_TYP_T` (`ACTV_IND`, `KIM_TYP_ID`, `NM`, `NMSPC_CD`, `OBJ_ID`, `SRVC_NM`, `VER_NBR`) VALUES ('Y', 'KPME0005', 'Derived Role: KPME Role Proxy', 'KPME-WKFLW', UUID(), '{http://kpme.kuali.org/core/v2_0}kpmeRoleProxyDerivedRoleTypeService', '1');

INSERT INTO `KRIM_ROLE_T` (`ACTV_IND`, `DESC_TXT`, `KIM_TYP_ID`, `LAST_UPDT_DT`, `NMSPC_CD`, `OBJ_ID`, `ROLE_ID`, `ROLE_NM`, `VER_NBR`) VALUES ('Y', 'The derived role that acts as a KPME role proxy', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KPME-WKFLW' AND NM = 'Derived Role: KPME Role Proxy'), NOW(), 'KPME-HR', UUID(), 'KPME0019', 'Derived Role : KPME Role Proxy', '1');
