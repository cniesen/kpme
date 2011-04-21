package org.kuali.hr.time.roles;

import org.kuali.hr.time.department.Department;
import org.kuali.hr.time.workarea.WorkArea;
import org.kuali.kfs.coa.businessobject.Chart;
import org.kuali.rice.kim.bo.Person;
import org.kuali.rice.kim.service.KIMServiceLocator;
import org.kuali.rice.kns.bo.PersistableBusinessObjectBase;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedHashMap;

public class TkRole extends PersistableBusinessObjectBase {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Long tkRolesId;
	private String principalId;
	private String roleName;
	private String userPrincipalId;
	private Long workArea;
	private String department;
    private String chart;
	private Date effectiveDate;
	private Timestamp timestamp;
	private boolean active;
	private Long tkDeptId;
	private Long tkWorkAreaId;

    /**
     * These objects are used by Lookups to provide links on the maintenance
     * page. They are not necessarily going to be populated.
     */
	private Person person;
    private Department departmentObj;
    private WorkArea workAreaObj;
    private Chart chartObj;

    public Chart getChartObj() {
        return chartObj;
    }

    public void setChartObj(Chart chartObj) {
        this.chartObj = chartObj;
    }

    public Department getDepartmentObj() {
        return departmentObj;
    }

    public void setDepartmentObj(Department departmentObj) {
        this.departmentObj = departmentObj;
    }

    public WorkArea getWorkAreaObj() {
        return workAreaObj;
    }

    public void setWorkAreaObj(WorkArea workAreaObj) {
        this.workAreaObj = workAreaObj;
    }

    public Long getTkRolesId() {
		return tkRolesId;
	}
	public void setTkRolesId(Long tkRolesId) {
		this.tkRolesId = tkRolesId;
	}
	public String getPrincipalId() {
		return principalId;
	}
	public void setPrincipalId(String principalId) {
		this.principalId = principalId;
        setPerson(KIMServiceLocator.getPersonService().getPerson(this.principalId));
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getUserPrincipalId() {
		return userPrincipalId;
	}
	public void setUserPrincipalId(String userPrincipalId) {
		this.userPrincipalId = userPrincipalId;
	}
	public Long getWorkArea() {
		return workArea;
	}
	public void setWorkArea(Long workArea) {
		this.workArea = workArea;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	@SuppressWarnings("unchecked")
	@Override
	protected LinkedHashMap toStringMapper() {
		// TODO Auto-generated method stub
		return null;
	}

	public Long getTkDeptId() {
		return tkDeptId;
	}
	public void setTkDeptId(Long tkDeptId) {
		this.tkDeptId = tkDeptId;
	}
	public Long getTkWorkAreaId() {
		return tkWorkAreaId;
	}
	public void setTkWorkAreaId(Long tkWorkAreaId) {
		this.tkWorkAreaId = tkWorkAreaId;
	}

    public String getChart() {
        return chart;
    }

    public void setChart(String chart) {
        this.chart = chart;
    }

    /**
     * This method supports maintenance and lookup pages.
     */
    public String getUserName() {
        if (person == null) {
            person = KIMServiceLocator.getPersonService().getPerson(this.principalId);
        }

        return (person != null) ? person.getName() : "";
    }
}