package edu.iu.uis.hr.tk.workflow;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.kuali.rice.kew.engine.RouteContext;
import org.kuali.rice.kew.routeheader.DocumentContent;
import org.kuali.rice.kew.rule.ResolvedQualifiedRole;
import org.kuali.rice.kew.rule.Role;
import org.kuali.rice.kew.rule.RoleAttribute;
import org.kuali.rice.kew.user.AuthenticationUserId;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.directory.dataaccess.AdsDataAccess;
import edu.iu.uis.hr.tk.TKServiceLocator;
import edu.iu.uis.hr.tk.directory.Utility;
import edu.iu.uis.hr.tk.job.entity.Job;
import edu.iu.uis.hr.tk.job.funding.entity.Assignment;
import edu.iu.uis.hr.tk.job.funding.service.AssignmentService;
import edu.iu.uis.hr.tk.timesheet.entity.TimesheetDocument;
import edu.iu.uis.hr.tk.timesheet.service.TimesheetService;

@SuppressWarnings("unchecked")
public class SupervisorPayrollProcessorRoleAttributeImpl implements RoleAttribute {

    @SuppressWarnings("unchecked")
	private static final List roleNames = new ArrayList();
    static {
        roleNames.add(new Role(SupervisorPayrollProcessorRoleAttribute.class, Utility.SUPERVISOR_ROLE, Utility.SUPERVISOR_ROLE_LABEL));
        roleNames.add(new Role(SupervisorPayrollProcessorRoleAttribute.class, Utility.PAYROLL_PROCESSOR_ROLE, Utility.PAYROLL_PROCESSOR_ROLE));
    }

    @SuppressWarnings("unchecked")
	public List getRoleNames() {
        return roleNames;
    }

    @SuppressWarnings("unchecked")
	public List getQualifiedRoleNames(String roleName, DocumentContent documentContent) {
        List qualifiedRoleNames = new ArrayList();
        List workareaIds = getWorkareaIds(documentContent.getRouteContext().getDocument().getRouteHeaderId());
        for (Iterator iterator = workareaIds.iterator(); iterator.hasNext();) {
            BigDecimal workareaId = (BigDecimal) iterator.next();
            // uses existing tk role names
            qualifiedRoleNames.add(Utility.getNestedRoleName(roleName, workareaId.toString()));
        }
        return qualifiedRoleNames;
    }

    @SuppressWarnings({ "unchecked", "deprecation" })
	public ResolvedQualifiedRole resolveQualifiedRole(RouteContext context, String roleName, String qualifiedRole)  {
        String workAreaId = Utility.getNestedRoleData(qualifiedRole);
        List roleRecipients = new ArrayList();
        List approvers = getApprovers(roleName, workAreaId);
        for (Iterator iterator = approvers.iterator(); iterator.hasNext();) {
            String networkId = (String) iterator.next();
            roleRecipients.add(new AuthenticationUserId(networkId));
        }
        // tk-550 add dept id, work area id to annotation - rpiercy
        AssignmentService assignmentService = (AssignmentService)Context.getService(AssignmentService.class);
        TimesheetDocument td = TKServiceLocator.getTimesheetService().getTimesheetDocument(Long.toString(context.getRouteHeader().getRouteHeaderId()));
        if(td!=null){
        	edu.iu.uis.hr.job.funding.entity.WorkArea workArea = assignmentService.getWorkArea(new BigDecimal(workAreaId), td.getDocumentHeader().getPayEndDate());
        	return new ResolvedQualifiedRole(roleName, roleRecipients, "Dept: " + workArea.getDepartment() + " , Work Area: " + workAreaId );
        }
        return null;
    }

    @SuppressWarnings("unchecked")
	public List getApprovers(String roleName, String workAreaId) {
        return getAdsDataAccess().getRoleNetworkIds(edu.iu.uis.hr.tk.directory.Utility.getNestedRoleName(roleName, workAreaId.toString()));
    }

    @SuppressWarnings("unchecked")
	private List getWorkareaIds(Long documentId) {
        List workareaIds = new ArrayList();
        TimesheetDocument timesheet = getTimesheetService().getTimesheetDocument(String.valueOf(documentId));
        for (Iterator iterator = timesheet.getJobs().iterator(); iterator.hasNext();) {
            Job job = (Job) iterator.next();
            for (Iterator jobIterator = job.getAssignments().iterator(); jobIterator.hasNext();) {
                Assignment assignment = (Assignment) jobIterator.next();
                BigDecimal workAreaId = assignment.getWorkArea();
                if (!workareaIds.contains(workAreaId)) {
                    workareaIds.add(workAreaId);
                }
            }
        }
        return workareaIds;
    }

    private TimesheetService getTimesheetService() {
        return Context.getTimesheetService();
    }

    private AdsDataAccess getAdsDataAccess() {
        return Context.getAdsDataAccess();
    }
}