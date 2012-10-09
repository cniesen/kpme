delete from tk_clock_log_t where tk_clock_log_id = '5000';

/* clock log */
insert into tk_clock_log_t (TK_CLOCK_LOG_ID,PRINCIPAL_ID,JOB_NUMBER,WORK_AREA,TASK,TK_WORK_AREA_ID,TK_TASK_ID,CLOCK_TS,CLOCK_TS_TZ,CLOCK_ACTION,IP_ADDRESS,USER_PRINCIPAL_ID,TIMESTAMP,HR_JOB_ID,OBJ_ID, unapproved_ip) values ('5000','testUser',30,30,30,'1','1','2012-03-01 08:08:08','America/Indianapolis','CO','TEST','admin','2012-03-01 08:08:08','1','7EE387AB-26B0-B6A6-9C4C-5B5F687F0E97', 'Y');

