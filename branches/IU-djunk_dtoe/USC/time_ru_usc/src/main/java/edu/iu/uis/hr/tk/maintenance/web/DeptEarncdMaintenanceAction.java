package edu.iu.uis.hr.tk.maintenance.web;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import edu.iu.uis.hr.tk.TKServiceLocator;
import edu.iu.uis.hr.tk.maintenance.DepartmentEarncdBean;

public class DeptEarncdMaintenanceAction extends DispatchAction {

	
	@Override
	protected String getMethodName(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String parameter) throws Exception {
		String methodName = super.getMethodName(mapping, form, request, response, parameter);
		return (StringUtils.isEmpty(methodName) ? "defaultAction" : methodName);
	}
	
	public ActionForward defaultAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//nothing
		return mapping.findForward("default");
	}
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward returnValue = super.execute(mapping, form, request, response);
		return returnValue;
	}
	
	private final static String INSERT_DEPT_EARNCD = "INSERT INTO tk.tk_dept_erncd_t (deptid, sal_admin_plan, location, erncd, employee, supervisor, payrollprocessor, active) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	
	public ActionForward addEarnCode(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DeptEarncdMaintenanceForm deForm = (DeptEarncdMaintenanceForm) form;
		try{
			//Service-ize this
			TKServiceLocator.getTkJdbcTemplate().update(INSERT_DEPT_EARNCD, new Object[] {
					deForm.getAddRow().getDeptid(),
					deForm.getAddRow().getSal_admin_plan(),
					deForm.getAddRow().getLocation(),
					deForm.getAddRow().getErncd(),
					(deForm.getAddRow().getEmployee() == Boolean.TRUE) ? 1 : 0,
					(deForm.getAddRow().getSupervisor() == Boolean.TRUE) ? 1 : 0,
					(deForm.getAddRow().getPayrollprocessor() == Boolean.TRUE) ? 1 : 0,
					(deForm.getAddRow().getActive() == Boolean.TRUE) ? 1 : 0
			});
			deForm.getMessages().add("Row Sucessfully Inserted!");
		} catch (Exception e){
			deForm.setSqlError(e.getMessage());
		}
		return mapping.findForward("default");
	}	
	
	public final static String GET_DEPT_EARNCD = "SELECT * FROM tk.tk_dept_erncd_t WHERE ERNCD like ?";
	
	public ActionForward earnCodeSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DeptEarncdMaintenanceForm deForm = (DeptEarncdMaintenanceForm) form;
		try{
			deForm.setEarnCode(StringUtils.replace(deForm.getEarnCode(), "*", "%"));
			//Service-ize this
			SqlRowSet rs = TKServiceLocator.getTkJdbcTemplate().queryForRowSet(GET_DEPT_EARNCD, new Object[] {
					deForm.getEarnCode()
			});
			DepartmentEarncdBean deBean = null;
			deForm.setRunSearch(Boolean.TRUE);
			while (rs.next()){
				deBean = new DepartmentEarncdBean();
				deBean.setDeptid(rs.getString("deptid"));
				deBean.setSal_admin_plan(rs.getString("sal_admin_plan"));
				deBean.setLocation(rs.getString("location"));
				deBean.setErncd(rs.getString("erncd"));
				deBean.setEmployee((rs.getInt("employee") == 1) ? Boolean.TRUE : Boolean.FALSE);
				deBean.setSupervisor((rs.getInt("supervisor") == 1) ? Boolean.TRUE : Boolean.FALSE);
				deBean.setPayrollprocessor((rs.getInt("payrollprocessor") == 1) ? Boolean.TRUE : Boolean.FALSE);
				deBean.setActive((rs.getInt("active") == 1) ? Boolean.TRUE : Boolean.FALSE);
				deForm.getSearchResults().add(deBean);
			}
		} catch (Exception e){
			deForm.setSqlError(e.getMessage());
		}		
		return mapping.findForward("default");
	}
	
	public ActionForward editDeptEarncdRow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		DeptEarncdMaintenanceForm deForm = (DeptEarncdMaintenanceForm) form;
		this.earnCodeSearch(mapping, deForm, request, response);
		
		return mapping.findForward("default");
	}
	
	public final static String SAVE_DEPT_EARNCD = "UPDATE tk.tk_dept_erncd_t SET DEPTID=?, SAL_ADMIN_PLAN=?, LOCATION=?, ERNCD=?, EMPLOYEE=?, SUPERVISOR=?, PAYROLLPROCESSOR=?, ACTIVE=? WHERE DEPTID=? AND SAL_ADMIN_PLAN=? AND LOCATION=? AND ERNCD=?";
	
	public ActionForward saveDeptEarncdRow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		DeptEarncdMaintenanceForm deForm = (DeptEarncdMaintenanceForm) form;
		this.earnCodeSearch(mapping, deForm, request, response);
		try{	
			List<String> keyList = Arrays.asList(StringUtils.split(deForm.getEditRow(), "-"));
			//Service-ize this
			TKServiceLocator.getTkJdbcTemplate().update(SAVE_DEPT_EARNCD, new Object[] {
					deForm.getEditRowBean().getDeptid(),
					deForm.getEditRowBean().getSal_admin_plan(),
					deForm.getEditRowBean().getLocation(),
					deForm.getEditRowBean().getErncd(),
					(deForm.getEditRowBean().getEmployee() == Boolean.TRUE) ? 1 : 0,
					(deForm.getEditRowBean().getSupervisor() == Boolean.TRUE) ? 1 : 0,
					(deForm.getEditRowBean().getPayrollprocessor() == Boolean.TRUE) ? 1 : 0,
					(deForm.getEditRowBean().getActive() == Boolean.TRUE) ? 1 : 0,
					keyList.get(0),
					keyList.get(1),
					keyList.get(2),
					keyList.get(3)		
			});
			deForm.setEditRow("");
			deForm.getMessages().add("Row Sucessfully Updated!");
		} catch (Exception e){
			deForm.setSqlError(e.getMessage());
		}
		deForm.setRunSearch(Boolean.TRUE);
		this.earnCodeSearch(mapping, deForm, request, response);
		return mapping.findForward("default");
	}	
	
}
