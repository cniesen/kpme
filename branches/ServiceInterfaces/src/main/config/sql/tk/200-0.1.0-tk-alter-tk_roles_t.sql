ALTER TABLE `tk`.`tk_roles_t` ADD COLUMN `tk_assignment_id` BIGINT NULL DEFAULT NULL  AFTER `user_principal_id` ;
ALTER TABLE `tk`.`tk_roles_t` ADD COLUMN `OBJ_ID` varchar(36) COLLATE utf8_bin NOT NULL;
ALTER TABLE `tk`.`tk_roles_t` ADD COLUMN `VER_NBR` bigint(20) NOT NULL DEFAULT '1';
  
