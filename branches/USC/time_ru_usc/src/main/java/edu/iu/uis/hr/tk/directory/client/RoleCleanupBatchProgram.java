package edu.iu.uis.hr.tk.directory.client;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.client.BatchProgram;
import edu.iu.uis.hr.directory.service.DirectoryService;
import edu.iu.uis.hr.tk.directory.service.RolesService;
import edu.iu.uis.hr.tk.directory.service.WebUserService;

public class RoleCleanupBatchProgram implements BatchProgram {
    private static final Logger LOG = Logger.getLogger(RoleCleanupBatchProgram.class);
    private RolesService rolesService;
    private WebUserService webUserService;
    private DirectoryService directoryService;

    public void execute() {

        List allAdministratorUsers = rolesService.getAllAdministratorUsers();
        for (Iterator iter = allAdministratorUsers.iterator(); iter.hasNext();) {
            String networkId = (String) iter.next();
            // TODO : test - use roman to test. 
            //if (!webUserService.isUserExistInEds(networkId)) {
            if (networkId.equals("starkr") || !webUserService.doesUserExist(networkId)) {
                LOG.info("remove " + networkId);
                rolesService.removeNetworkIdFromRoles(networkId);
            }
        }
    }

    private WebUserService getWebUserService() {
        return webUserService;
    }

    public void setWebUserService(WebUserService webUserService) {
        if (webUserService != null) {
            this.webUserService = webUserService;
        }
    }

    private RolesService getRolesService() {
        return rolesService;
    }

    public void setRolesService(RolesService rolesService) {
        if (rolesService != null) {
            this.rolesService = rolesService;
        }
    }

    public DirectoryService getDirectoryService() {
        return directoryService;
    }

    public void setDirectoryService(DirectoryService directoryService) {
        if (directoryService != null) {
            this.directoryService = directoryService;
        }
    }

}
