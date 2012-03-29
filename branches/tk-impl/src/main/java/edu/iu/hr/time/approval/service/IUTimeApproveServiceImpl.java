package edu.iu.hr.time.approval.service;

import java.util.ArrayList;
import java.util.List;

import org.kuali.hr.time.approval.service.TimeApproveServiceImpl;
import org.kuali.hr.time.service.base.TkServiceLocator;
import org.kuali.hr.time.util.TKContext;
import org.kuali.rice.kew.routeheader.DocumentRouteHeaderValue;
import org.kuali.rice.kew.service.KEWServiceLocator;
import org.springframework.jdbc.support.rowset.SqlRowSet;


public class IUTimeApproveServiceImpl extends TimeApproveServiceImpl  {


	@Override
	protected List<String> getPrincipalIdsWithActiveAssignmentsForCalendarGroupByDeptAndWorkArea(
			String roleName, String department, String workArea,
			String payCalendarGroup, java.sql.Date effdt,
			java.sql.Date beginDate, java.sql.Date endDate) {
		
		String sql = null;
		
		if (department == null || department.isEmpty()) {
			return new ArrayList<String>();
		} else {

			List<String> principalIds = new ArrayList<String>();

			SqlRowSet rs = null;
			
			if (roleName == null) {
				 sql = "SELECT DISTINCT A0.PRINCIPAL_ID FROM TK_ASSIGNMENT_T A0, HR_ROLES_T R0, TK_WORK_AREA_T W0, HR_PRINCIPAL_CALENDAR_T P0  WHERE " 
					    + "((A0.EFFDT =  (SELECT MAX(EFFDT)  FROM TK_ASSIGNMENT_T  WHERE PRINCIPAL_ID = A0.PRINCIPAL_ID  AND EFFDT <= ? AND WORK_AREA = A0.WORK_AREA  AND TASK = A0.TASK AND JOB_NUMBER = A0.JOB_NUMBER) AND "
					    + "A0.TIMESTAMP =  (SELECT MAX(TIMESTAMP)  FROM TK_ASSIGNMENT_T  WHERE PRINCIPAL_ID = A0.PRINCIPAL_ID  AND EFFDT = A0.EFFDT AND WORK_AREA = A0.WORK_AREA AND TASK = A0.TASK AND JOB_NUMBER = A0.JOB_NUMBER) AND "
					    + "A0.ACTIVE = 'Y') OR (A0.ACTIVE = 'N'  AND A0.EFFDT >= ? AND A0.EFFDT <= ?)) AND "
					    + "R0.WORK_AREA = A0.WORK_AREA AND "
					    + "R0.PRINCIPAL_ID = ? AND "
					    + "R0.ROLE_NAME IN ('TK_APPROVER', 'TK_APPROVER_DELEGATE', 'TK_REVIEWER') AND "
						+ "R0.EFFDT = (SELECT MAX(EFFDT)  FROM HR_ROLES_T  WHERE ROLE_NAME = R0.ROLE_NAME AND PRINCIPAL_ID = R0.PRINCIPAL_ID AND EFFDT <= ? AND WORK_AREA = R0.WORK_AREA) AND "
						+ "R0.TIMESTAMP = (SELECT MAX(TIMESTAMP)  FROM HR_ROLES_T  WHERE ROLE_NAME = R0.ROLE_NAME AND PRINCIPAL_ID = R0.PRINCIPAL_ID AND EFFDT = R0.EFFDT AND WORK_AREA = R0.WORK_AREA) AND "
						+ "R0.ACTIVE = 'Y' AND "
						+ "W0.WORK_AREA = A0.WORK_AREA AND "
						+ "W0.DEPT = ? AND "
						+ "W0.EFFDT = (SELECT MAX(EFFDT) FROM TK_WORK_AREA_T WHERE EFFDT <= ? AND WORK_AREA = W0.WORK_AREA) AND "
						+ "W0.TIMESTAMP =  (SELECT MAX(TIMESTAMP)  FROM TK_WORK_AREA_T  WHERE WORK_AREA = W0.WORK_AREA  AND EFFDT = W0.EFFDT) AND "
						+ "W0.ACTIVE = 'Y' AND "
						+ "P0.PRINCIPAL_ID = A0.PRINCIPAL_ID AND "
						+ "P0.PY_CALENDAR_GROUP = ?";
				 
				 	int[] params = null;
				 	Object[] values = null;
				 	
					if (workArea != null) {
						sql += " AND A0.WORK_AREA = ? ";
						
						params = new int[] {java.sql.Types.DATE,
								java.sql.Types.DATE,
								java.sql.Types.DATE,
								java.sql.Types.VARCHAR, 
								java.sql.Types.DATE,
								java.sql.Types.VARCHAR,
								java.sql.Types.DATE,
								java.sql.Types.VARCHAR,
								java.sql.Types.INTEGER };
						
						values = new Object[] {effdt,  beginDate, endDate, TKContext.getUser().getTargetPrincipalId(), effdt, department, effdt, payCalendarGroup, workArea };
					}else {
						params = new int[] {java.sql.Types.DATE,
								java.sql.Types.DATE,
								java.sql.Types.DATE,
								java.sql.Types.VARCHAR, 
								java.sql.Types.DATE,
								java.sql.Types.VARCHAR,
								java.sql.Types.DATE,
								java.sql.Types.VARCHAR};
						
						values = new Object[] {effdt,  beginDate, endDate, TKContext.getUser().getTargetPrincipalId(), effdt, department, effdt, payCalendarGroup};
					}
					
					rs = TkServiceLocator.getTkJdbcTemplate().queryForRowSet(
							sql, values, params);
			}else {
				 sql = "SELECT DISTINCT A0.PRINCIPAL_ID FROM TK_ASSIGNMENT_T A0, HR_ROLES_T R0, TK_WORK_AREA_T W0, HR_PRINCIPAL_CALENDAR_T P0  WHERE " 
						 + "((A0.EFFDT =  (SELECT MAX(EFFDT)  FROM TK_ASSIGNMENT_T  WHERE PRINCIPAL_ID = A0.PRINCIPAL_ID  AND EFFDT <= ? AND WORK_AREA = A0.WORK_AREA  AND TASK = A0.TASK AND JOB_NUMBER = A0.JOB_NUMBER) AND "
						 + "A0.TIMESTAMP =  (SELECT MAX(TIMESTAMP)  FROM TK_ASSIGNMENT_T  WHERE PRINCIPAL_ID = A0.PRINCIPAL_ID  AND EFFDT = A0.EFFDT AND WORK_AREA = A0.WORK_AREA AND TASK = A0.TASK AND JOB_NUMBER = A0.JOB_NUMBER) AND "
						 + "A0.ACTIVE = 'Y') OR (A0.ACTIVE = 'N'  AND A0.EFFDT >= ? AND A0.EFFDT <= ?)) AND "
						 + "W0.WORK_AREA = A0.WORK_AREA AND "
						 + "W0.EFFDT = (SELECT MAX(EFFDT) FROM TK_WORK_AREA_T WHERE EFFDT <= ? AND WORK_AREA = W0.WORK_AREA) AND "
						 + "W0.TIMESTAMP =  (SELECT MAX(TIMESTAMP)  FROM TK_WORK_AREA_T  WHERE WORK_AREA = W0.WORK_AREA  AND EFFDT = W0.EFFDT) AND "
						 + "W0.ACTIVE = 'Y' AND "
						 + "W0.DEPT = ? AND "
						 + "W0.DEPT = R0.DEPT AND "
						 + "R0.PRINCIPAL_ID = ? AND "
						 + "R0.ROLE_NAME IN ('TK_PY_PROCESSOR') AND " 
						 + "R0.EFFDT = (SELECT MAX(EFFDT)  FROM HR_ROLES_T  WHERE ROLE_NAME = R0.ROLE_NAME AND PRINCIPAL_ID = R0.PRINCIPAL_ID AND EFFDT <= ? AND DEPT = R0.DEPT) AND "
						 + "R0.TIMESTAMP = (SELECT MAX(TIMESTAMP)  FROM HR_ROLES_T  WHERE ROLE_NAME = R0.ROLE_NAME AND PRINCIPAL_ID = R0.PRINCIPAL_ID AND EFFDT = R0.EFFDT AND DEPT = R0.DEPT) AND "
						 + "R0.ACTIVE = 'Y' AND "
						 + "P0.PRINCIPAL_ID = A0.PRINCIPAL_ID AND "
						 + "P0.PY_CALENDAR_GROUP = ?";
				 
					int[] params = null;
				 	Object[] values = null;
				 	
					if (workArea != null) {
						sql += " AND A0.WORK_AREA = ? ";
						
						params = new int[] {java.sql.Types.DATE,
								java.sql.Types.DATE,
								java.sql.Types.DATE,
								java.sql.Types.DATE,
								java.sql.Types.VARCHAR,
								java.sql.Types.VARCHAR,
								java.sql.Types.DATE,
								java.sql.Types.VARCHAR, 
								java.sql.Types.INTEGER};
						
						values= new Object[] { effdt, beginDate, endDate, effdt, department, TKContext.getUser().getTargetPrincipalId(), effdt, payCalendarGroup, workArea};
					} else {
						params = new int[] {java.sql.Types.DATE,
								java.sql.Types.DATE,
								java.sql.Types.DATE,
								java.sql.Types.DATE,
								java.sql.Types.VARCHAR,
								java.sql.Types.VARCHAR,
								java.sql.Types.DATE,
								java.sql.Types.VARCHAR};
						
						values= new Object[] { effdt, beginDate, endDate, effdt, department, TKContext.getUser().getTargetPrincipalId(), effdt, payCalendarGroup};
					}

					rs = TkServiceLocator.getTkJdbcTemplate()
							.queryForRowSet(
									sql, values, params);
			}
			
			while (rs.next()) {
				principalIds.add(rs.getString("principal_id"));
			}

			return principalIds;
		}
	}
}
