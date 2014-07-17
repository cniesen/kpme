package org.kuali.kpme.edo.admin.web;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kpme.edo.base.web.EdoAction;
import org.kuali.kpme.edo.group.EdoGroupDefinition;
import org.kuali.kpme.edo.group.service.EdoGroupDefinitionService;
import org.kuali.kpme.edo.group.service.EdoGroupService;
import org.kuali.kpme.edo.service.EdoServiceLocator;
import org.kuali.kpme.edo.util.EdoConstants;
import org.kuali.kpme.edo.util.EdoContext;
import org.kuali.rice.kew.actionrequest.ActionRequestValue;
import org.kuali.rice.kew.service.KEWServiceLocator;
import org.kuali.rice.kim.api.group.Group;
import org.kuali.rice.kim.api.services.KimApiServiceLocator;
import org.kuali.rice.krad.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 * $HeadURL$
 * $Revision$
 * Created with IntelliJ IDEA.
 * User: bradleyt
 * Date: 12/6/12
 * Time: 8:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class EdoGenAdminAction extends EdoAction {
    private static final Logger LOG = Logger.getLogger(EdoGenAdminAction.class);

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        EdoGenAdminForm adminForm = (EdoGenAdminForm)form;
    	
        Collection<String> groupList = new TreeSet<String>();
        Collection<String> groupIds  = new TreeSet<String>();
        String userId = EdoContext.getPrincipalId();
        if (request.getParameterMap().containsKey("user")) {
            LOG.info("Getting user [" + request.getParameter("user") + "] principal ID");
            userId = KimApiServiceLocator.getIdentityService().getPrincipalByPrincipalName(request.getParameter("user")).getPrincipalId();
        }

        request.setAttribute("isMember", "false");

        if (request.getParameterMap().containsKey("sdocid")) {
            LOG.info("Found supplemental document ID on URL line: " + adminForm.getSuppDocId());
            adminForm.setSuppDocId(request.getParameter("sdocid"));

            List<ActionRequestValue> actionRequests = KEWServiceLocator.getActionRequestService().findAllActionRequestsByDocumentId(adminForm.getSuppDocId());

            if (CollectionUtils.isNotEmpty(actionRequests)) {
                LOG.info("Found action requests");
                for (ActionRequestValue req : actionRequests) {
                    String gid = req.getGroupId();
                    String grpname = req.getGroupName();
                    LOG.info("Found group: " + grpname);
                    groupList.add(grpname);
                    groupIds.add(gid);
                }

                for (String grp : groupIds) {
                    if (KimApiServiceLocator.getGroupService().isMemberOfGroup(userId,grp)) {
                        LOG.info("Checking principal ID: " + userId);
                        request.setAttribute("isMember", "true");
                    }
                }
            }
        }
        request.setAttribute("groupList", groupList);

        if (request.getParameterMap().containsKey("grpchk")) {
            LOG.info("Found group check flag on URL line.");
            String workflowId = request.getParameter("grpchk");

            EdoGroupDefinitionService grpDefSvc = EdoServiceLocator.getEdoGroupDefinitionService();
            EdoGroupService grpSvc = EdoServiceLocator.getEdoGroupService();
            List<EdoGroupDefinition> grpDefList = grpDefSvc.getEdoGroupDefinitionsByWorkflowId(workflowId);
            List<String> kimGrpList = new LinkedList<String>();

            List<String> depts = EdoServiceLocator.getEdoDossierService().getDistinctDossierDepartmentIDs();
            for (String dept : depts) {
                for (EdoGroupDefinition grpDef : grpDefList) {
                    if (grpDef.getKimTypeName().equalsIgnoreCase("department")) {
                        String grpName = grpSvc.buildGroupName(grpDef.getWorkflowLevel(), grpDef.getWorkflowType(), grpDef.getDossierType(), dept, workflowId);
                        LOG.info("Checking for " + grpName);
                        Group kimGrp = KimApiServiceLocator.getGroupService().getGroupByNamespaceCodeAndName(EdoConstants.EDO_NAME_SPACE, grpName);
                        if (ObjectUtils.isNull(kimGrp)) {
                            LOG.info("[" + grpName + "] not found.");
                             kimGrpList.add(grpName);
                        }
                    }
                }
            }
            List<String> schools = EdoServiceLocator.getEdoDossierService().getDistinctDossierSchoolIDs();
            for (String school : schools) {
                for (EdoGroupDefinition grpDef : grpDefList) {
                    if (grpDef.getKimTypeName().equalsIgnoreCase("school")) {
                        String grpName = grpSvc.buildGroupName(grpDef.getWorkflowLevel(), grpDef.getWorkflowType(), grpDef.getDossierType(), school, workflowId);
                        LOG.info("Checking for " + grpName);
                        Group kimGrp = KimApiServiceLocator.getGroupService().getGroupByNamespaceCodeAndName(EdoConstants.EDO_NAME_SPACE, grpName);
                        if (ObjectUtils.isNull(kimGrp)) {
                            LOG.info("[" + grpName + "] not found.");
                            kimGrpList.add(grpName);
                        }
                    }
                }
            }

            request.setAttribute("missingGroups", kimGrpList);
        }

        return super.execute(mapping, form, request, response);
    }


}
