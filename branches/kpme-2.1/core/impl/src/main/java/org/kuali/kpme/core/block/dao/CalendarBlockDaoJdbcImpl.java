/**
 * Copyright 2004-2015 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kpme.core.block.dao;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.kuali.kpme.core.api.assignment.Assignment;
import org.kuali.kpme.core.block.CalendarBlock;
import org.kuali.rice.core.framework.persistence.jdbc.dao.PlatformAwareDaoBaseJdbc;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CalendarBlockDaoJdbcImpl extends PlatformAwareDaoBaseJdbc implements CalendarBlockDao {

    private static final Log LOG = LogFactory.getLog(CalendarBlockDaoJdbcImpl.class);

    @Override
    public List<CalendarBlock> getAllCalendarBlocks() {
        List<CalendarBlock> calendarBlocks = new ArrayList<CalendarBlock>();
        PreparedStatementCreator psc = new PreparedStatementCreator(){

            @Override
            public PreparedStatement createPreparedStatement(Connection conn)
                    throws SQLException {
                String query = "SELECT begin_ts, end_ts, lm_leave_block_id as c_block_id, 'Leave' as c_block_type, document_id, job_number, obj_id, ver_nbr, principal_id, principal_id_modified as user_principal_id, timestamp, task, grp_key_cd, work_area, earn_code, " +
                        "'N' as lunch_deleted, null as overtime_pref, null as hours, leave_amount as amount " +
                        "FROM lm_leave_block_t " +
                        "UNION " +
                        "SELECT begin_ts, end_ts, tk_time_block_id as c_block_id, 'Time' as c_block_type, document_id, job_number, obj_id, ver_nbr, principal_id, user_principal_id, timestamp, task, grp_key_cd, work_area, earn_code, " +
                        "lunch_deleted, ovt_pref as overtime_pref, hours, amount " +
                        "FROM tk_time_block_t;";

                return conn.prepareStatement(query);
            }

        };
        calendarBlocks = this.getJdbcTemplate().query(psc, new CalendarBlockRowMapper());
        return calendarBlocks;
    }

    @Override
    public List<CalendarBlock> getActiveCalendarBlocksForDate(LocalDate asOfDate) {
        PreparedStatementCreator psc = new PreparedStatementCreator(){
            /**
             * TODO: For use, our effective dating strategy must be included in the query below.
             */
            @Override
            public PreparedStatement createPreparedStatement(Connection conn)
                    throws SQLException {
                String query = "SELECT begin_ts, end_ts, tk_time_block_id as c_block_id, 'Time' as c_block_type, document_id, job_number, obj_id, ver_nbr, principal_id, user_principal_id, timestamp, task, grp_key_cd, work_area, earn_code " +
                        "FROM tk_time_block_t";

                return conn.prepareStatement(query);
            }

        };

        List<CalendarBlock> calendarBlocks = this.getJdbcTemplate().query(psc, new CalendarBlockRowMapper());
        return calendarBlocks;
    }

    private class CalendarBlockRowMapper implements RowMapper<CalendarBlock> {

        @Override
        public CalendarBlock mapRow(ResultSet rs, int rowNum) throws SQLException {
            CalendarBlock cBlock = new CalendarBlock();
            cBlock.setBeginTimestamp(rs.getTimestamp("BEGIN_TS"));
            cBlock.setEndTimestamp(rs.getTimestamp("END_TS"));
            cBlock.setConcreteBlockId(rs.getString("C_BLOCK_ID"));
            cBlock.setConcreteBlockType(rs.getString("C_BLOCK_TYPE"));
            cBlock.setDocumentId(rs.getString("DOCUMENT_ID"));
            cBlock.setEarnCode(rs.getString("EARN_CODE"));
            cBlock.setJobNumber(rs.getLong("JOB_NUMBER"));
            cBlock.setObjectId(rs.getString("OBJ_ID"));
            cBlock.setVersionNumber(rs.getLong("VER_NBR"));
            cBlock.setPrincipalId(rs.getString("PRINCIPAL_ID"));
            cBlock.setUserPrincipalId(rs.getString("USER_PRINCIPAL_ID"));
            cBlock.setHours(rs.getBigDecimal("HOURS"));
            cBlock.setAmount(rs.getBigDecimal("AMOUNT"));
            cBlock.setOvertimePref(rs.getString("OVERTIME_PREF"));
            cBlock.setLunchDeleted(rs.getBoolean("LUNCH_DELETED"));
            cBlock.setTask(rs.getLong("TASK"));
            cBlock.setGroupKeyCode(rs.getString("GRP_KEY_CD"));
            cBlock.setWorkArea(rs.getLong("WORK_AREA"));
            cBlock.setTimestamp(rs.getTimestamp("TIMESTAMP"));
            return cBlock;
        }

    }


    @Override
    public DateTime getLatestEndTimestampForAssignment(Assignment assignment,String calendarBlockType) {

        PreparedStatementCreator timeBlockPSC = new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection conn)
                    throws SQLException {
                StringBuffer sql = new StringBuffer();
                sql.append("SELECT max(end_ts) ");
                sql.append("FROM tk_time_block_t ");
                sql.append("WHERE principal_id = ? AND job_number=? AND task=? AND work_area=? AND grp_key_cd=?");

                String query = sql.toString();

                return conn.prepareStatement(query);
            }
        };

        PreparedStatementCreator leaveBlockPSC = new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection conn)
                    throws SQLException {
                StringBuffer sql = new StringBuffer();
                sql.append("SELECT max(end_ts) ");
                sql.append("FROM lm_leave_block_t ");
                sql.append("WHERE principal_id = ? AND job_number=? AND task=? AND work_area=? AND grp_key_cd=?");

                String query = sql.toString();

                return conn.prepareStatement(query);
            }
        };

        try {
            PreparedStatement statement = null;
            if (StringUtils.equals(calendarBlockType, "Time")) {
                statement = timeBlockPSC.createPreparedStatement(this.getDataSource().getConnection());
            } else if (StringUtils.equals(calendarBlockType, "Leave")) {
                statement = leaveBlockPSC.createPreparedStatement(this.getDataSource().getConnection());
            }
            else {
                throw new IllegalArgumentException("calendarBlockType must be one of 'Time' or 'Leave'");
            }
            if(statement != null) {
                statement.setString(1, assignment.getPrincipalId());
                statement.setLong(2, assignment.getJobNumber());
                statement.setLong(3, assignment.getTask());
                statement.setLong(4, assignment.getWorkArea());
                statement.setString(5, assignment.getGroupKeyCode());
            }

            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                Timestamp maxDate = rs.getTimestamp("max(end_ts)");
                if(maxDate == null) {
                    return null;
                }
                else {
                    return new DateTime(maxDate.getTime());
                }
            }
        } catch (SQLException e) {
            LOG.warn("error creating or executing sql statement");
            LOG.warn(e);
            throw new RuntimeException();
        }
        return null;
    }

    @Override
    public DateTime getLatestEndTimestampForEarnCode(String earnCode, String calendarBlockType) {

        PreparedStatementCreator timeBlockPSC = new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection conn)
                    throws SQLException {
                StringBuffer sql = new StringBuffer();
                sql.append("SELECT max(end_ts) ");
                sql.append("FROM tk_time_block_t ");
                sql.append("WHERE earn_code = ?");

                String query = sql.toString();

                return conn.prepareStatement(query);
            }
        };

        PreparedStatementCreator leaveBlockPSC = new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection conn)
                    throws SQLException {
                StringBuffer sql = new StringBuffer();
                sql.append("SELECT max(end_ts) ");
                sql.append("FROM lm_leave_block_t ");
                sql.append("WHERE earn_code = ?");

                String query = sql.toString();

                return conn.prepareStatement(query);
            }
        };
        try {
            PreparedStatement statement = null;
            if (StringUtils.equals(calendarBlockType, "Time")) {
                statement = timeBlockPSC.createPreparedStatement(this.getDataSource().getConnection());
            } else if (StringUtils.equals(calendarBlockType, "Leave")) {
                statement = leaveBlockPSC.createPreparedStatement(this.getDataSource().getConnection());
            }
            else {
                throw new IllegalArgumentException("calendarBlockType must be one of 'Time' or 'Leave'");
            }
            if(statement != null) {
                statement.setString(1, earnCode);
            }

            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                Timestamp maxDate = rs.getTimestamp("max(end_ts)");
                if(maxDate == null) {
                    return null;
                }
                else {
                    return new DateTime(maxDate.getTime());
                }
            }
        } catch (SQLException e) {
            LOG.warn("error creating or executing sql statement");
            LOG.warn(e);
            throw new RuntimeException();
        }
        return null;
    }

}
