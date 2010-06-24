package edu.iu.uis.hr.tk.department.dataaccess;

import java.util.Collection;
import java.util.Date;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryFactory;

import edu.iu.hrms.hredoc.cache.CacheResult;
import edu.iu.uis.hr.dataaccess.AbstractDataAccessOjb;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.job.funding.entity.WorkArea;
import edu.iu.uis.hr.tk.department.entity.DepartmentWorkAreaSearchCriteria;

public class DepartmentDataAccessOjb extends AbstractDataAccessOjb implements DepartmentDataAccess {
    
    private static String ACTIVE = "A";
    
    @CacheResult
    public Collection getDepartmentWorkAreas(String department){
        Criteria criteria = new Criteria();
        Date date = new Date();
        criteria = getCurrentRecordsCriteria(WorkArea.class, new Date(date.getYear(), date.getMonth(), date.getDate()));
        criteria.addEqualTo(FieldNames.DEPARTMENT, department);
        criteria.addEqualTo(FieldNames.ACTIVE, Boolean.TRUE);
        return getCollectionByQuery(QueryFactory.newQuery(WorkArea.class, criteria));
    }

    public TypedPersistentMaintainedEntityList lookupDepartmentWorkAreas(DepartmentWorkAreaSearchCriteria searchCriteria) {
        Criteria criteria = new Criteria();
        criteria = getCurrentRecordsCriteria(WorkArea.class, new Date());
        criteria.addEqualTo(FieldNames.DEPARTMENT, searchCriteria.getDepartment());
        if (searchCriteria.isActive()){
            criteria.addEqualTo(FieldNames.ACTIVE, ACTIVE);
         } 
        return getAllMaintainableValuesListByQuery(QueryFactory.newQuery(WorkArea.class, criteria));
    }
}
