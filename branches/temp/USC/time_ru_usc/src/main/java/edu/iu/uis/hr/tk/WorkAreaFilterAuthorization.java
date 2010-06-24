package edu.iu.uis.hr.tk;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.Filter;
import edu.iu.uis.hr.Optionable;
import edu.iu.uis.hr.client.FilterApplicable;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.job.funding.entity.WorkArea;
import edu.iu.uis.hr.tk.job.funding.service.AssignmentService;

public class WorkAreaFilterAuthorization extends DepartmentFilterAuthorization {

    private AssignmentService assignmentService;
    
    public Filter execute(FilterApplicable filterApplicable) {
        return null;
    }

    public boolean execute(List filters, Object value) {
        if (!(value instanceof WorkArea || value instanceof BigDecimal)) {
            throw new IllegalArgumentException("WorkAreaFilterAuthorization's execute(FilterApplicable filterApplicable, Object value)() method expects an Object value of type WorkArea or BigDecimal");
        }
        boolean isAuthorizedByFilter = false;
        Filter filter = getRelevantFilter(FieldNames.WORK_AREA, filters);
        if (Utility.hasValue(filter)) {
            Iterator optionablesItr = filter.getOptionables().iterator();
            while (optionablesItr.hasNext()) {
                Optionable option = (Optionable)optionablesItr.next();
                if (value instanceof WorkArea) {
                    isAuthorizedByFilter = isAuthorizedByFilter | value.equals(optionablesItr.next());
                } else {
                    isAuthorizedByFilter = isAuthorizedByFilter | value.toString().equals(option.getValue());
                }
            }
        } else {
            Iterator workAreas = getWorkAreaObjectListFromObjectValue(value).iterator();
            while (workAreas.hasNext()) {
                isAuthorizedByFilter = isAuthorizedByFilter | super.execute(filters, ((WorkArea)workAreas.next()).getDepartment());
            }
        }
        return isAuthorizedByFilter;
    }

    private List getWorkAreaObjectListFromObjectValue(Object value) {
        if (!(value instanceof WorkArea || value instanceof BigDecimal)) {
            throw new IllegalArgumentException("WorkAreaFilterAuthorization's getWorkAreaObjectListFromObjectValue(Object value)() method expects an Object value of type WorkArea or BigDecimal");
        }
        List workAreas = new ArrayList();
        if (value instanceof WorkArea) {
            workAreas.add(value);
            return workAreas;
        } else {
            workAreas.add(value.toString());
            return getAssignmentService().getWorkAreas(workAreas, new Date());
        }
    }
    public AssignmentService getAssignmentService() {
        return (AssignmentService) Context.getService(AssignmentService.class);
    }

}
