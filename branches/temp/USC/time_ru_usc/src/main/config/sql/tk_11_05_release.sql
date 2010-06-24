
--will need to update the route node with the 'new' interface manager group AFTER deploy and delete of the old one
--update EN_RTE_NODE_T set WRKGRP_ID = 4255892 WHERE WRKGRP_ID = 1392503

update EN_RULE_ATTRIB_T set MESSAGE_ENTITY_NM = 'TK' where RULE_ATTRIB_NM like 'Timesheet%'

GO


update EN_DOC_TYP_T set MESSAGE_ENTITY_NM = 'TK'  where DOC_TYP_NM like 'Time%'

GO


update EN_RULE_ATTRIB_T set MESSAGE_ENTITY_NM = 'TK' where RULE_ATTRIB_NM = 'SupervisorPayrollProcessorAttribute' 


GO


//Quartz tables
CREATE TABLE QRTZ_BLOB_TRIGGERS (
    TRIGGER_NAME    VARCHAR2(80) NOT NULL,
    TRIGGER_GROUP   VARCHAR2(80) NOT NULL,
    BLOB_DATA       BLOB NULL
    )
GO
CREATE TABLE QRTZ_CALENDARS (
    CALENDAR_NAME   VARCHAR2(80) NOT NULL,
    CALENDAR        BLOB NOT NULL
    )
GO
CREATE TABLE QRTZ_CRON_TRIGGERS (
    TRIGGER_NAME    VARCHAR2(80) NOT NULL,
    TRIGGER_GROUP   VARCHAR2(80) NOT NULL,
    CRON_EXPRESSION VARCHAR2(80) NOT NULL,
    TIME_ZONE_ID    VARCHAR2(80) NULL
    )
GO
CREATE TABLE QRTZ_FIRED_TRIGGERS (
    ENTRY_ID            VARCHAR2(95) NOT NULL,
    TRIGGER_NAME        VARCHAR2(80) NOT NULL,
    TRIGGER_GROUP       VARCHAR2(80) NOT NULL,
    IS_VOLATILE         VARCHAR2(1) NOT NULL,
    INSTANCE_NAME       VARCHAR2(80) NOT NULL,
    FIRED_TIME          NUMBER(13,0) NOT NULL,
    PRIORITY            NUMBER(13,0) NOT NULL,
    STATE               VARCHAR2(16) NOT NULL,
    JOB_NAME            VARCHAR2(80) NULL,
    JOB_GROUP           VARCHAR2(80) NULL,
    IS_STATEFUL         VARCHAR2(1) NULL,
    REQUESTS_RECOVERY   VARCHAR2(1) NULL
    )
GO
CREATE TABLE QRTZ_JOB_DETAILS (
    JOB_NAME            VARCHAR2(80) NOT NULL,
    JOB_GROUP           VARCHAR2(80) NOT NULL,
    DESCRIPTION         VARCHAR2(120) NULL,
    JOB_CLASS_NAME      VARCHAR2(128) NOT NULL,
    IS_DURABLE          VARCHAR2(1) NOT NULL,
    IS_VOLATILE         VARCHAR2(1) NOT NULL,
    IS_STATEFUL         VARCHAR2(1) NOT NULL,
    REQUESTS_RECOVERY   VARCHAR2(1) NOT NULL,
    JOB_DATA            BLOB NULL
    )
GO
CREATE TABLE QRTZ_JOB_LISTENERS (
    JOB_NAME        VARCHAR2(80) NOT NULL,
    JOB_GROUP       VARCHAR2(80) NOT NULL,
    JOB_LISTENER    VARCHAR2(80) NOT NULL
    )
GO
CREATE TABLE QRTZ_LOCKS (
    LOCK_NAME   VARCHAR2(40) NOT NULL
    )
GO
CREATE TABLE QRTZ_PAUSED_TRIGGER_GRPS (
    TRIGGER_GROUP   VARCHAR2(80) NOT NULL
    )
GO
CREATE TABLE QRTZ_SCHEDULER_STATE (
    INSTANCE_NAME       VARCHAR2(80) NOT NULL,
    LAST_CHECKIN_TIME   NUMBER(13,0) NOT NULL,
    CHECKIN_INTERVAL    NUMBER(13,0) NOT NULL
    )
GO
CREATE TABLE QRTZ_SIMPLE_TRIGGERS (
    TRIGGER_NAME    VARCHAR2(80) NOT NULL,
    TRIGGER_GROUP   VARCHAR2(80) NOT NULL,
    REPEAT_COUNT    NUMBER(7,0) NOT NULL,
    REPEAT_INTERVAL NUMBER(12,0) NOT NULL,
    TIMES_TRIGGERED NUMBER(7,0) NOT NULL
    )
GO
CREATE TABLE QRTZ_TRIGGERS (
    TRIGGER_NAME    VARCHAR2(80) NOT NULL,
    TRIGGER_GROUP   VARCHAR2(80) NOT NULL,
    JOB_NAME        VARCHAR2(80) NOT NULL,
    JOB_GROUP       VARCHAR2(80) NOT NULL,
    IS_VOLATILE     VARCHAR2(1) NOT NULL,
    DESCRIPTION     VARCHAR2(120) NULL,
    NEXT_FIRE_TIME  NUMBER(13,0) NULL,
    PREV_FIRE_TIME  NUMBER(13,0) NULL,
    PRIORITY        NUMBER(13,0) NULL,
    TRIGGER_STATE   VARCHAR2(16) NOT NULL,
    TRIGGER_TYPE    VARCHAR2(8) NOT NULL,
    START_TIME      NUMBER(13,0) NOT NULL,
    END_TIME        NUMBER(13,0) NULL,
    CALENDAR_NAME   VARCHAR2(80) NULL,
    MISFIRE_INSTR   NUMBER(2,0) NULL,
    JOB_DATA        BLOB NULL
    )
GO
CREATE TABLE QRTZ_TRIGGER_LISTENERS (
    TRIGGER_NAME        VARCHAR2(80) NOT NULL,
    TRIGGER_GROUP       VARCHAR2(80) NOT NULL,
    TRIGGER_LISTENER    VARCHAR2(80) NOT NULL
    )
GO

INSERT INTO QRTZ_LOCKS(LOCK_NAME)
  VALUES('CALENDAR_ACCESS')
GO
INSERT INTO QRTZ_LOCKS(LOCK_NAME)
  VALUES('JOB_ACCESS')
GO
INSERT INTO QRTZ_LOCKS(LOCK_NAME)
  VALUES('MISFIRE_ACCESS')
GO
INSERT INTO QRTZ_LOCKS(LOCK_NAME)
  VALUES('STATE_ACCESS')
GO
INSERT INTO QRTZ_LOCKS(LOCK_NAME)
  VALUES('TRIGGER_ACCESS')
GO

CREATE INDEX QRTZ_SIMPLE_TRIGGERS_1 ON 
  QRTZ_SIMPLE_TRIGGERS(TRIGGER_NAME, TRIGGER_GROUP)
GO

//batch jobs related tables
create sequence tk_batch_job_seq minvalue 0 start with 1 increment by 1
GO
create sequence tk_batch_job_pk_seq minvalue 0 start with 1 increment by 1
GO
create table tk_batch_jobs
( tk_batch_job_id numeric(10) not null,
  batch_job_name varchar2(50) not null,
  total_jobs varchar2(50) not null,
  start_time timestamp,
  end_time timestamp,
  job_exception varchar2(50),
  CONSTRAINT tk_batch_pk PRIMARY KEY (tk_batch_job_id)
)
GO
CREATE TABLE TK.TK_BATCH_JOB_ENTRIES ( 
	TK_JOB_ID          	NUMBER(14,0) NOT NULL,
	DATA_ID            	VARCHAR2(50) NOT NULL,
	IP_NET             	VARCHAR2(50) NOT NULL,
	TOTAL_TIME         	NUMBER(10,0) NOT NULL,
	TK_BATCH_JOB_ID    	NUMBER(10,0) NOT NULL,
	JOB_STATUS         	VARCHAR2(2) NOT NULL,
	JOB_TYPE           	VARCHAR2(2) NOT NULL,
	JOB_MESSAGE        	VARCHAR2(4000) NULL,
	EXCEPTION          	VARCHAR2(4000) NULL,
	SERIALIZED_RUNNABLE	BLOB NULL 
)

GO
CREATE INDEX tk_batch_job_entries_1 ON tk_batch_job_entries (ip_net)
GO
CREATE INDEX tk_batch_job_entries_2 ON tk_batch_job_entries (job_status)
GO
CREATE INDEX tk_batch_job_entries_3 ON tk_batch_job_entries (tk_batch_job_id)
GO
CREATE INDEX tk_batch_job_entries_4 ON tk_batch_job_entries (ip_net, job_status)
GO

//EN  tables for asynchronous proccess
CREATE TABLE EN_MSG_PAYLOAD_T (
    MESSAGE_QUE_ID  NUMBER(14,0) NOT NULL,
    MESSAGE_PAYLOAD CLOB NOT NULL
    )
/
CREATE TABLE EN_MSG_QUE_T (
    MESSAGE_QUE_ID          NUMBER(14,0) NOT NULL,
    MESSAGE_QUE_DT          DATE NOT NULL,
    MESSAGE_EXP_DT          DATE NULL,
    MESSAGE_QUE_PRIO_NBR    NUMBER(8,0) NOT NULL,
    MESSAGE_QUE_STAT_CD     CHAR(1) NOT NULL,
    MESSAGE_QUE_RTRY_CNT    NUMBER(8,0) NOT NULL,
    MESSAGE_QUE_IP_NBR      VARCHAR2(2000) NOT NULL,
    MESSAGE_SERVICE_NM      VARCHAR2(255) NULL,
    MESSAGE_ENTITY_NM       VARCHAR2(10) NOT NULL,
    SERVICE_METHOD_NM       VARCHAR2(2000) NULL,
    VAL_ONE                 VARCHAR2(2000) NULL,
    VAL_TWO                 VARCHAR2(2000) NULL,
    DB_LOCK_VER_NBR         NUMBER(8,0) DEFAULT 0 NULL
    )


//FUNCTION TO BE ADDED TO "EN" SCHEMA ON GEN1PRD  (MODIFY THE RIGHT SCHEMA NAME)

DROP FUNCTION ENTEST.STRJOIN;

CREATE FUNCTION ENTEST.STRJOIN
(
    p_cursor sys_refcursor,
    p_del varchar2 := ','
) return varchar2
is
    l_value   varchar2(32767);
    l_result  varchar2(32767);
begin
    loop
        fetch p_cursor into l_value;
        exit when p_cursor%notfound;
        if l_result is not null then
            l_result := l_result || p_del;
        end if;
        l_result := l_result || l_value;
    end loop;
    return l_result;
end strjoin;

grant execute on STRJOIN to en_proxy;



create table tk_dept_erncd_t
( deptid varchar2(7) not null,
  location varchar2(2),  
  sal_admin_plan varchar2(4) not null,
  erncd varchar2(3) not null,
  employee varchar2(1),
  supervisor varchar2(1),
  payrollProcessor varchar2(1),
  active varchar2(1),
  CONSTRAINT tk_dept_erncd_pk PRIMARY KEY (deptid,location,sal_admin_plan,erncd)
)


