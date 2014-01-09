--  *********************************************************************
--  Update Database Script
--  *********************************************************************
--  Change Log: src/main/config/db/db.changelog-main-upgrade.xml
--  Ran at: 1/9/14 8:32 AM
--  Against: tk@localhost@jdbc:mysql://localhost/tk
--  Liquibase version: 2.0.5
--  *********************************************************************

--  Changeset src/main/config/db/2.0.1/db.changelog-201312251230.xml::1::umehta::(Checksum: 3:593aaff4e8995af1676fe620fca30a52)
ALTER TABLE `LM_LEAVE_BLOCK_HIST_T` ADD `USER_PRINCIPAL_ID` VARCHAR(40);
