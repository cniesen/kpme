package edu.iu.uis.hr.tk.directory.dataaccess;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryFactory;

import edu.iu.uis.hr.dataaccess.AbstractDataAccessOjb;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.directory.entity.RoleAudit;
import edu.iu.uis.hr.tk.directory.entity.RoleAuditSearchCriteria;

public class RoleAuditDataAccessOjb extends AbstractDataAccessOjb implements RoleAuditDataAccess {

    public TypedPersistentMaintainedEntityList lookupRoleAudit(RoleAuditSearchCriteria searchCriteria) {
        Criteria lookupCriteria = new Criteria();
        getStandardLookupLogic(lookupCriteria, FieldNames.DEPARTMENT, searchCriteria.getDepartment());
        return getAllMaintainableValuesListByQuery(QueryFactory.newQuery(RoleAudit.class, lookupCriteria));
    }

}
