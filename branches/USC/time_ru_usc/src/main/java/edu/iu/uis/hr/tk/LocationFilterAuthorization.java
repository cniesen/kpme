package edu.iu.uis.hr.tk;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.Filter;
import edu.iu.uis.hr.FilterAuthorization;
import edu.iu.uis.hr.Optionable;
import edu.iu.uis.hr.client.FilterApplicable;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.position.entity.Location;
import edu.iu.uis.hr.position.service.PositionService;
import edu.iu.uis.hr.tk.directory.client.RoleMaintenanceForm;
import edu.iu.uis.hr.tk.directory.service.RolesService;

public class LocationFilterAuthorization extends AbstractAuthorization implements FilterAuthorization {

    public Filter execute(FilterApplicable filterApplicable) {
        List locations = new ArrayList();
        
        if (((edu.iu.uis.hr.tk.directory.entity.User)getUser()).hasSystemRole()) {
            if (filterApplicable instanceof RoleMaintenanceForm) {
                return new Filter(FieldNames.LOCATION, getPositionService().getDropdownLocations());
            }
            locations = getRolesService().getRoleLocationCodes();//Gets all locations for which a system administrator role exists
        } else {    
        	locations = getUser().getSystemAdministratorLocations(); //Gets the location for which the user has the TK_SYSTEM_ADMINISTRATOR role
        }
        return new Filter(FieldNames.LOCATION, getPositionService().getLocations(locations));
    }

    public boolean execute(List filters, Object value) {
        if (!(value instanceof Location || value instanceof String) || filters == null || (Utility.hasValue(filters) && !(filters.get(0) instanceof Filter))) {
            throw new IllegalArgumentException("LocationFilterAuthorization's execute(FilterApplicable filterApplicable, Object value)() method expects an Object value of type Location or String and a non-null List of Filters");
        }
        boolean isAuthorizedByFilter = false;
        Filter filter = getRelevantFilter(FieldNames.LOCATION, filters);
        if (!Utility.hasValue(filter)) {
            isAuthorizedByFilter = true;
        } else {
            Iterator optionablesItr = filter.getOptionables().iterator();
            while (optionablesItr.hasNext()) {
                Optionable option = (Optionable) optionablesItr.next();
                if (value instanceof Location) {
                    isAuthorizedByFilter = isAuthorizedByFilter | value.equals(optionablesItr.next());
                } else {
                    isAuthorizedByFilter = isAuthorizedByFilter | value.equals(option.getValue());
                }
            }
        }
        return isAuthorizedByFilter;
    }

    protected Filter getRelevantFilter(String fieldName, List filters) {
        Iterator filtersItr = filters.iterator();
        while (filtersItr.hasNext()) {
            Filter filter = (Filter)filtersItr.next();
            if (fieldName.equals(filter.getFieldname())) {
                return filter;
            }
        }
        return null;
    }

    protected RolesService getRolesService() {
        return (RolesService)Context.getService(RolesService.class);
    }

	public PositionService getPositionService() {
		return (PositionService) Context.getService(PositionService.class);
	}

}
