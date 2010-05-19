package edu.iu.uis.hr.tk.directory.dataaccess;

import edu.iu.uis.hr.dataaccess.DataAccess;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.directory.entity.RoleAuditSearchCriteria;

public interface RoleAuditDataAccess extends DataAccess {

    public TypedPersistentMaintainedEntityList lookupRoleAudit(RoleAuditSearchCriteria searchCriteria);

}
