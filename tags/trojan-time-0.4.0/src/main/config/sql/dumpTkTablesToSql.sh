#!/bin/sh


TK_TABLES=""
TK_TABLES=$TK_TABLES" hr_job_t hr_job_s"
TK_TABLES=$TK_TABLES" hr_paytype_t hr_paytype_s"
TK_TABLES=$TK_TABLES" hr_work_schedule_entry_t hr_work_schedule_entry_s"
TK_TABLES=$TK_TABLES" hr_work_schedule_t hr_work_schedule_s"

TK_TABLES=$TK_TABLES" tk_accrual_categories_t tk_accrual_categories_s"
TK_TABLES=$TK_TABLES" tk_accruals_t tk_accruals_s"
TK_TABLES=$TK_TABLES" tk_assign_acct_s tk_assign_acct_t"
TK_TABLES=$TK_TABLES" tk_assignment_t tk_assignment_s"
TK_TABLES=$TK_TABLES" tk_clock_location_rl_s tk_clock_location_rl_t"
TK_TABLES=$TK_TABLES" tk_clock_log_s tk_clock_log_t"
TK_TABLES=$TK_TABLES" tk_daily_overtime_rl_t tk_daily_overtime_rl_s"
TK_TABLES=$TK_TABLES" tk_dept_earn_code_t tk_dept_earn_code_s"
TK_TABLES=$TK_TABLES" tk_dept_lunch_rl_t tk_dept_lunch_rl_s"
TK_TABLES=$TK_TABLES" tk_dept_t "
TK_TABLES=$TK_TABLES" tk_earn_code_t tk_earn_code_s"
TK_TABLES=$TK_TABLES" tk_earn_group_def_t tk_earn_group_def_s"
TK_TABLES=$TK_TABLES" tk_earn_group_t tk_earn_group_s"
TK_TABLES=$TK_TABLES" tk_grace_period_rl_t tk_grace_period_rl_s"
TK_TABLES=$TK_TABLES" tk_holiday_calendar_dates_t tk_holiday_calendar_dates_s"
TK_TABLES=$TK_TABLES" tk_holiday_calendar_t tk_holiday_calendar_s"
TK_TABLES=$TK_TABLES" tk_py_calendar_t tk_py_calendar_s "
TK_TABLES=$TK_TABLES" tk_py_calendar_dates_t tk_py_calendar_dates_s"
TK_TABLES=$TK_TABLES" tk_sal_group_t tk_sal_group_s"
TK_TABLES=$TK_TABLES" tk_shift_differential_rl_t tk_shift_differential_rl_s"
TK_TABLES=$TK_TABLES" tk_system_lunch_rl_t tk_system_lunch_rl_s"
TK_TABLES=$TK_TABLES" tk_task_t tk_task_s"
TK_TABLES=$TK_TABLES" tk_time_block_hist_t tk_time_block_hist_s"
TK_TABLES=$TK_TABLES" tk_time_block_t "
TK_TABLES=$TK_TABLES" tk_time_collection_rl_t"
TK_TABLES=$TK_TABLES" tk_weekly_overtime_rl_t tk_weekly_overtime_rl_s"
TK_TABLES=$TK_TABLES" tk_work_area_s tk_work_area_t"

#mysql -B -h andover -u tk -p  -e "show tables like 'tk%'" tk
mysqldump -d -u tk -p -h andover tk $TK_TABLES > app_time_mysql.sql
