package edu.iu.uis.hr.tk;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import edu.iu.uis.hr.Filter;
import edu.iu.uis.hr.Optionable;
import edu.iu.uis.hr.client.FilterApplicable;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.position.entity.Department;

public class DepartmentFilterAuthorization extends LocationFilterAuthorization {

    public Filter execute(FilterApplicable filterApplicable) {
        if (((edu.iu.uis.hr.tk.directory.entity.User)getUser()).hasLocationRole()) {
            return super.execute(filterApplicable);
        }
        return new Filter(FieldNames.DEPARTMENT, getPositionService().getDepartments(getUser().getPayrollManagerDepartments(), new Date()));
    }

    public boolean execute(List filters, Object value) {    
	        if (!(value instanceof Department || value instanceof String)) {
	            throw new IllegalArgumentException("DepartmentFilterAuthorization's execute(FilterApplicable filterApplicable, Object value)() method expects an Object value of type Department or String");
	        }
	        boolean isAuthorizedByFilter = false;
	        Filter filter = getRelevantFilter(FieldNames.DEPARTMENT, filters);
	        if (Utility.hasValue(filter)) {
	            Iterator optionablesItr = filter.getOptionables().iterator();
	            while (optionablesItr.hasNext()) {
	                Optionable option = (Optionable) optionablesItr.next();
	                if (value instanceof Department) {
	                    isAuthorizedByFilter = isAuthorizedByFilter | value.equals(optionablesItr.next());
	                } else {
	                    isAuthorizedByFilter = isAuthorizedByFilter | value.equals(option.getValue());
	                }
	            }
	        } else {
	            Iterator departments = getDepartmentObjectListFromObjectValue(value).iterator();
	            while (departments.hasNext()) {
	                isAuthorizedByFilter = isAuthorizedByFilter | super.execute(filters, ((Department)departments.next()).getLocation());
	            }
	        }
	        return isAuthorizedByFilter;

    }

    private List getDepartmentObjectListFromObjectValue(Object value) {
        if (!(value instanceof Department || value instanceof String)) {
            throw new IllegalArgumentException("DepartmentFilterAuthorization's getDepartmentsFromObjectValue(Object value)() method expects an Object value of type Department or String");
        }
        List departments = new ArrayList();
        departments.add(value);
        if (value instanceof Department) {
            return departments;
        } else {
            return getPositionService().getDepartments(departments, new Date());
        }
    }

}
