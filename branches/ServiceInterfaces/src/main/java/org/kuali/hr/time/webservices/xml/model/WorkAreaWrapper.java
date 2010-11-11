package org.kuali.hr.time.webservices.xml.model;

import java.util.List;

import org.kuali.hr.time.workarea.WorkArea;

public class WorkAreaWrapper {
	List<WorkArea> workAreas ;

    public List<WorkArea> getWorkAreas() {
        return workAreas;
    }

    public void setWorkAreas(List<WorkArea> workAreas) {
        this.workAreas = workAreas;
    }
}
