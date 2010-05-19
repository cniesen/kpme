package edu.iu.uis.hr.tk.directory.service;

import java.util.List;

import edu.iu.uis.hr.job.funding.entity.WorkArea;
import edu.iu.uis.hr.position.entity.Department;

public interface WebUserService extends edu.iu.uis.hr.directory.service.WebUserService {

    public List getUserRoles(WorkArea workArea);

    public List getSupervisoryDelegationCandidates(WorkArea workArea);

    public List getDelegatableResponsibilities(Department department);

    public boolean doesUserExist(String networkId);

    //TODO:  remove once testing complete
    public void testIt();
}