#!/bin/sh


TK_TABLES="tk_assignment_s tk_assignment_t tk_clock_location_rl_s tk_clock_location_rl_t tk_clock_log_s tk_clock_log_t tk_dept_t tk_dept_s tk_time_block_t tk_work_area_s tk_work_area_t tk_grace_period_rl_t tk_grace_period_rl_s tk_dept_lunch_rl_t tk_dept_lunch_rl_s "



#mysql -B -h andover -u tk -p  -e "show tables like 'tk%'" tk
mysqldump -d -u tk -p -h andover tk $TK_TABLES > app_time_mysql.sql
