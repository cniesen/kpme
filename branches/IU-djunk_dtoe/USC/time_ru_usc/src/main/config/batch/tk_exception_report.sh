#!/bin/ksh

# reference to brte library
. ${MYBRTE:-/opt/brte}/batch_modules $*
 
# stop on trouble 
set_error_class "004"
set_error_message "${SCRIPT_NAME} has problems."

# our SQRs are in this directory
export SRCDIR="${PROGRAMDIR}"

print_to_log "PSINSTANCE variable is set to $PSINSTANCE."
print_to_log "ORACLE_SID variable is set to $ORACLE_SID."
typeset SQCDIR=" -I${SRCDIR}/ -ZIF${SRCDIR}/sqr.ini -PRINTER:PD "
typeset SQRMFILE=" -M${SRCDIR}/mfile.dat "
typeset SQRPARMS="${SQRMFILE}${SQCDIR}"
typeset SQRUSERNAME=${TK_USER}
print_to_log "SQCDIR ${SQCDIR}"
print_to_log "SQRMFILE ${SQRMFILE}"
print_to_log "SQRPARMS ${SQRPARMS}"
do_command "export PSINSTANCE=${DATASERVER}"
do_command "export ORACLE_SID=${DATASERVER}"
do_command "export ORA_SERVERS=${DATASERVER}"
do_command "export ORA_SID=${DATASERVER}"
print_to_log "PSINSTANCE variable reset to $PSINSTANCE."
print_to_log "ORACLE_SID variable reset to $ORACLE_SID."
print_to_log "SQRUSERNAME ${SQRUSERNAME}"

function STEP10 {
   SQRSCRIPT="${SRCDIR}/clock_exception.sqr"
   print_to_log "SQRSCRIPT ${SQRSCRIPT}"
   do_sqr_file -u ${SQRUSERNAME} ${SQRSCRIPT} ${ORACLE_SID} ${SQRPARMS}
}

function STEP20 {
   SQRSCRIPT="${SRCDIR}/data_exception.sqr"
   print_to_log "SQRSCRIPT ${SQRSCRIPT}"
   do_sqr_file -u ${SQRUSERNAME} ${SQRSCRIPT} ${ORACLE_SID} ${SQRPARMS}
}

function STEP30 {
   SQRSCRIPT="${SRCDIR}/hours_exception.sqr"
   print_to_log "SQRSCRIPT ${SQRSCRIPT}"
   do_sqr_file -u ${SQRUSERNAME} ${SQRSCRIPT} ${ORACLE_SID} ${SQRPARMS}
}

function STEP40 {
   SQRSCRIPT="${SRCDIR}/lunch_exception.sqr"
   print_to_log "SQRSCRIPT ${SQRSCRIPT}"
   do_sqr_file -u ${SQRUSERNAME} ${SQRSCRIPT} ${ORACLE_SID} ${SQRPARMS}
}

function STEP50 {
   SQRSCRIPT="${SRCDIR}/time_exception.sqr"
   print_to_log "SQRSCRIPT ${SQRSCRIPT}"
   do_sqr_file -u ${SQRUSERNAME} ${SQRSCRIPT} ${ORACLE_SID} ${SQRPARMS}
}

# execute STEP# functions in order
executor

# this ends script gracefully (a return code of 0)
end_run 0

