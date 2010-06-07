CREATE TABLE `tk_work_area_t` (
`WORK_AREA_ID decimal(19,0),
`EFFDT` DATE NOT NULL,
`EFFSEQ` decimal(3,0) DEFAULT NULL,
`EFF_STATUS` VARCHAR(1),
`DESCR` VARCHAR(30),
`DEPTID` VARCHAR(10),
`EMPL_TYPE` VARCHAR(5),
`OVERTIME_PREFERENCE` VARCHAR(3),
`ADMIN_DESCR` VARCHAR(30),
`USER_EMPLID` VARCHAR(10),
`TIMESTAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
`OBJ_ID` VARCHAR(36) NOT NULL,
`VER_NBR` DECIMAL(8,0) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `tk_work_area_s` (
  `ID` BIGINT(19) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=1000 DEFAULT CHARSET=latin1;

