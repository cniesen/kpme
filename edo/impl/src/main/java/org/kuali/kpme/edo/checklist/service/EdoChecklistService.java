package org.kuali.kpme.edo.checklist.service;

import java.util.List;

import org.joda.time.LocalDate;
import org.kuali.kpme.edo.api.checklist.EdoChecklist;

public interface EdoChecklistService {

    public EdoChecklist getChecklistByID(String edoChecklistID);
    
    public List<EdoChecklist> getChecklists(String groupKey, String organizationCode, String departmentID, LocalDate asOfDate);
}
