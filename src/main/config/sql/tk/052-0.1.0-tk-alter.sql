            /* SQL changes from Allen */

            ALTER TABLE `tk_weekly_overtime_rl_t` modify `max_hrs_ern_group` varchar(10) NOT NULL;
            ALTER TABLE `tk_weekly_overtime_rl_t` modify `convert_from_ern_group` varchar(10) NOT NULL;
            ALTER TABLE `tk_weekly_overtime_rl_t` modify `convert_to_erncd` varchar(9) NOT NULL;

            /* SQL changes from Kenneth */

            ALTER TABLE `tk_dept_T` CHANGE COLUMN `tk_dept_id` `hr_dept_id` BIGINT(20) NOT NULL
            , DROP PRIMARY KEY
            , ADD PRIMARY KEY (`hr_dept_id`) , RENAME TO `hr_dept_t` ;
            ALTER TABLE `tk_dept_s` RENAME TO `hr_dept_s` ;

            ALTER TABLE `tk_sal_group_t` CHANGE COLUMN `TK_SAL_GROUP_ID` `HR_SAL_GROUP_ID` BIGINT(20) NOT NULL , CHANGE
            COLUMN `TK_SAL_GROUP` `HR_SAL_GROUP` VARCHAR(10) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL
            , DROP PRIMARY KEY
            , ADD PRIMARY KEY (`HR_SAL_GROUP_ID`) , RENAME TO `hr_sal_group_t` ;
            ALTER TABLE `tk_sal_group_s` RENAME TO `hr_sal_group_s` ;

            ALTER TABLE `hr_job_t` CHANGE COLUMN `TK_SAL_GROUP` `HR_SAL_GROUP` VARCHAR(10) CHARACTER SET 'utf8' COLLATE
            'utf8_bin' NULL DEFAULT NULL ;

            ALTER TABLE `tk_dept_earn_code_t` CHANGE COLUMN `tk_sal_group` `hr_sal_group` VARCHAR(10) CHARACTER SET
            'utf8' COLLATE 'utf8_bin' NOT NULL ;

            ALTER TABLE `tk_earn_code_t` CHANGE COLUMN `tk_earn_code_id` `hr_earn_code_id` INT(11) NOT NULL
            , DROP PRIMARY KEY
            , ADD PRIMARY KEY (`hr_earn_code_id`) , RENAME TO `hr_earn_code_t` ;
            ALTER TABLE `tk_earn_code_s` RENAME TO `hr_earn_code_s` ;

            ALTER TABLE `tk_earn_group_t` CHANGE COLUMN `tk_earn_group_id` `hr_earn_group_id` BIGINT(20) NOT NULL
            , DROP PRIMARY KEY
            , ADD PRIMARY KEY (`hr_earn_group_id`) , RENAME TO `hr_earn_group_t` ;
            ALTER TABLE `tk_earn_group_s` RENAME TO `hr_earn_group_s` ;

            ALTER TABLE `tk_earn_group_def_t` CHANGE COLUMN `tk_earn_group_id` `hr_earn_group_id` BIGINT(20) NULL
            DEFAULT NULL , CHANGE COLUMN `tk_earn_group_def_id` `hr_earn_group_def_id` BIGINT(20) NOT NULL
            , DROP PRIMARY KEY
            , ADD PRIMARY KEY (`hr_earn_group_def_id`) , RENAME TO `hr_earn_group_def_t` ;
            ALTER TABLE `tk_earn_group_def_s` RENAME TO `hr_earn_group_def_s` ;

            ALTER TABLE `tk_dept_earn_code_t` CHANGE COLUMN `tk_dept_earn_code_id` `hr_dept_earn_code_id` BIGINT(20) NOT
            NULL
            , DROP PRIMARY KEY
            , ADD PRIMARY KEY (`hr_dept_earn_code_id`) , RENAME TO `hr_dept_earn_code_t` ;
            ALTER TABLE `tk_dept_earn_code_s` RENAME TO `hr_dept_earn_code_s` ;

            ALTER TABLE `tk_principal_calendar_t` CHANGE COLUMN `calendar_group` `py_calendar_group` VARCHAR(45)
            CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL DEFAULT '' , RENAME TO `hr_principal_calendar_t` ;

            ALTER TABLE `tk_holiday_calendar_t` CHANGE COLUMN `HOLIDAY_CALENDAR_ID` `HR_HOLIDAY_CALENDAR_ID` BIGINT(20)
            NOT NULL
            , DROP PRIMARY KEY
            , ADD PRIMARY KEY (`HR_HOLIDAY_CALENDAR_ID`) , RENAME TO `hr_holiday_calendar_t` ;
            ALTER TABLE `tk_holiday_calendar_s` RENAME TO `hr_holiday_calendar_s` ;

            ALTER TABLE `tk_holiday_calendar_dates_t` CHANGE COLUMN `HOLIDAY_CALENDAR_DATES_ID`
            `HR_HOLIDAY_CALENDAR_DATES_ID` BIGINT(20) NOT NULL , CHANGE COLUMN `HOLIDAY_CALENDAR_ID`
            `HR_HOLIDAY_CALENDAR_ID` INT(11) NOT NULL
            , DROP PRIMARY KEY
            , ADD PRIMARY KEY (`HR_HOLIDAY_CALENDAR_DATES_ID`) , RENAME TO `hr_holiday_calendar_dates_t` ;
            ALTER TABLE `tk_holiday_calendar_dates_s` RENAME TO `hr_holiday_calendar_dates_s` ;

            ALTER TABLE `tk_py_calendar_t` CHANGE COLUMN `tk_py_calendar_id` `hr_py_calendar_id` BIGINT(20) NOT NULL ,
            CHANGE COLUMN `calendar_group` `py_calendar_group` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT
            NULL
            , DROP PRIMARY KEY
            , ADD PRIMARY KEY (`hr_py_calendar_id`) , RENAME TO `hr_py_calendar_t` ;
            ALTER TABLE `tk_py_calendar_s` RENAME TO `hr_py_calendar_s` ;

            ALTER TABLE `tk_py_calendar_entries_t` CHANGE COLUMN `tk_py_calendar_entry_id` `hr_py_calendar_entry_id`
            BIGINT(20) NOT NULL , CHANGE COLUMN `tk_py_calendar_id` `hr_py_calendar_id` BIGINT(20) NULL DEFAULT NULL ,
            CHANGE COLUMN `calendar_group` `py_calendar_group` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT
            NULL
            , DROP PRIMARY KEY
            , ADD PRIMARY KEY (`hr_py_calendar_entry_id`) , RENAME TO `hr_py_calendar_entries_t` ;
            ALTER TABLE `tk_py_calendar_entries_s` RENAME TO `hr_py_calendar_entries_s` ;

            ALTER TABLE `tk_batch_job_t` CHANGE COLUMN `TK_PAY_CALENDAR_ENTRY_ID` `HR_PY_CALENDAR_ENTRY_ID` BIGINT(20)
            NOT NULL ;
            ALTER TABLE `tk_batch_job_entry_t` CHANGE COLUMN `TK_PY_CALENDAR_ENTRY_ID` `HR_PY_CALENDAR_ENTRY_ID`
            BIGINT(20) NOT NULL ;

            ALTER TABLE `tk_roles_t` CHANGE COLUMN `tk_roles_id` `hr_roles_id` BIGINT(19) NOT NULL
            , DROP PRIMARY KEY
            , ADD PRIMARY KEY (`hr_roles_id`) , RENAME TO `hr_roles_t` ;
            ALTER TABLE `tk_roles_s` RENAME TO `hr_roles_s` ;

            ALTER TABLE `tk_roles_group_t` RENAME TO `hr_roles_group_t` ;

            ALTER TABLE `la_accruals_t` CHANGE COLUMN `LA_ACCRUALS_ID` `LM_ACCRUALS_ID` BIGINT(20) NOT NULL
            , DROP PRIMARY KEY
            , ADD PRIMARY KEY (`LM_ACCRUALS_ID`) , RENAME TO `lm_accruals_t` ;
            ALTER TABLE `la_accruals_s` RENAME TO `lm_accruals_s` ;

            ALTER TABLE `la_accrual_categories_t` CHANGE COLUMN `LA_ACCRUAL_CATEGORY_ID` `LM_ACCRUAL_CATEGORY_ID`
            BIGINT(20) NOT NULL
            , DROP PRIMARY KEY
            , ADD PRIMARY KEY (`LM_ACCRUAL_CATEGORY_ID`) , RENAME TO `lm_accrual_categories_t` ;
            ALTER TABLE `la_accrual_categories_s` RENAME TO `lm_accrual_categories_s` ;

            ALTER TABLE `tk_time_block_t` ADD COLUMN `principal_id` VARCHAR(40) NULL AFTER `VER_NBR` ;

            ALTER TABLE `tk_shift_differential_rl_t` CHANGE COLUMN `TK_SAL_GROUP` `HR_SAL_GROUP` VARCHAR(10) CHARACTER
            SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ;
            ALTER TABLE `tk_shift_differential_rl_t` CHANGE COLUMN `CALENDAR_GROUP` `PY_CALENDAR_GROUP` VARCHAR(30)
            CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ;

            /* SQL changes from Tom */
            ALTER TABLE `hr_earn_group_def_t` CHANGE COLUMN `hr_earn_group_id` `hr_earn_group_id` BIGINT(22) NOT NULL ,
            CHANGE COLUMN `earn_code` `earn_code` VARCHAR(9) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL , CHANGE
            COLUMN `OBJ_ID` `OBJ_ID` VARCHAR(38) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL DEFAULT '1', CHANGE COLUMN
            `VER_NBR` `VER_NBR` BIGINT(38) NOT NULL DEFAULT '1' , CHANGE COLUMN `hr_earn_group_def_id`
            `hr_earn_group_def_id` BIGINT(22) NOT NULL ;

            ALTER TABLE `hr_earn_group_t` CHANGE COLUMN `hr_earn_group_id` `hr_earn_group_id` BIGINT(38) NOT NULL ,
            CHANGE COLUMN `earn_group` `earn_group` VARCHAR(10) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ,
            CHANGE COLUMN `effdt` `effdt` DATE NOT NULL , CHANGE COLUMN `active` `active` VARCHAR(1) CHARACTER SET
            'utf8' COLLATE 'utf8_bin' NOT NULL , CHANGE COLUMN `OBJ_ID` `OBJ_ID` VARCHAR(38) CHARACTER SET 'utf8'
            COLLATE 'utf8_bin' NOT NULL DEFAULT '1', CHANGE COLUMN `VER_NBR` `VER_NBR` BIGINT(38) NOT NULL DEFAULT '1' ;

            ALTER TABLE `hr_sal_group_t` CHANGE COLUMN `HR_SAL_GROUP_ID` `HR_SAL_GROUP_ID` BIGINT(38) NOT NULL , CHANGE
            COLUMN `ACTIVE` `ACTIVE` VARCHAR(1) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL , CHANGE COLUMN
            `OBJ_ID` `OBJ_ID` VARCHAR(38) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL DEFAULT '1', CHANGE COLUMN
            `VER_NBR` `VER_NBR` BIGINT(38) NOT NULL DEFAULT '1' ;