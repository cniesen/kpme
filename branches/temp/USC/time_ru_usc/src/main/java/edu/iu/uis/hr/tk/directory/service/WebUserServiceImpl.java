package edu.iu.uis.hr.tk.directory.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.ExceptionAdapter;
import edu.iu.uis.hr.directory.UserNotFoundException;
import edu.iu.uis.hr.directory.entity.User;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.job.funding.entity.WorkArea;
import edu.iu.uis.hr.position.entity.Department;
import edu.iu.uis.hr.tk.directory.entity.UserImpl;

public class WebUserServiceImpl extends edu.iu.uis.hr.directory.service.WebUserServiceImpl implements WebUserService {

    private static final Logger LOG = Logger.getLogger(WebUserServiceImpl.class);

    private static final String TK_USER = "tk-user";
    //private static final String TK_USER = "0001819903";

    //Can use this to manipulate roles for testing if needed
    public void testIt() {
      /*    
      List members = new ArrayList();
      members.add("aaneal");
      getAdsDataAccess().simpleAddNetworkIdsToRole(members, "ZTEST-TK_INTERFACE_MANAGER");
      
      members = new ArrayList();
      members.add("ZTEST-TK_INTERFACE_MANAGER");
      getAdsDataAccess().addGroup("ZTEST-HR_SUPER_USER", members);
      
      getAdsDataAccess().removeGroup("9595");    
      */      
    }

    public User getUser(HttpServletRequest request) {
        if (Utility.hasValue(request.getParameter(CARD_ID_PARAMETER))) {
            User user = getUser(getNetworkIdByCardId(request.getParameter(CARD_ID_PARAMETER)));
            user.setCardAuthenticated(true);
            return user;
        }
        return getUser(getNetworkId(request));
    }
    
    public User getUser(String networkId) {
        //testIt();
        User user = getDirectoryService().getUser(networkId);
        UserImpl tkUser = new UserImpl(user);
        return tkUser;
    }

    protected String getSystemUser(){
        return TK_USER;
    }
    
    public List getUserRoles(WorkArea workArea) {
        return edu.iu.uis.hr.Utility.getDefaultListValue();
    }

    public List getSupervisoryDelegationCandidates(WorkArea workArea) {
        return edu.iu.uis.hr.Utility.getDefaultListValue();
    }

    public List getDelegatableResponsibilities(Department department) {
        return edu.iu.uis.hr.Utility.getDefaultListValue();
    }

    public boolean doesUserExist(String networkId) {
        try {
            getDirectoryService().getUser(networkId, false);
        } catch (UserNotFoundException unfe) {
            return false;
        } catch (Exception e) {
            throw (ExceptionAdapter)e;
        }
        return true;
    }
}