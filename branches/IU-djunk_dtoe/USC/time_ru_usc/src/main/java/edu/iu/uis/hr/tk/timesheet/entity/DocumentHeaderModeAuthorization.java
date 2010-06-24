package edu.iu.uis.hr.tk.timesheet.entity;

import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.ModeAuthorization;
import edu.iu.uis.hr.tk.AbstractAuthorization;

public class DocumentHeaderModeAuthorization  extends AbstractAuthorization implements ModeAuthorization {
        
    public void execute(ModeApplicable modeApplicable) {
        if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(modeApplicable) || !(modeApplicable instanceof DocumentHeader)) {
            throw new IllegalArgumentException("DocumentHeaderModeAuthorization's execute(ModeApplicable modeApplicable) method requires a non-null ModeApplicable DocumentHeader object.");
        }
        //TODO : check authorization by routelog 
        modeApplicable.setMode(getModeAuthorizationFactory().getEditMode(modeApplicable));

        // User must not alter, or delete, headers. Only option is to create.
        DocumentHeader documentHeader = (DocumentHeader)modeApplicable;
    	if (edu.iu.uis.hr.entity.logic.Utility.hasValue(documentHeader.getDocumentId())) {
    		documentHeader.getMode().setEditable(false);
    	}
    }
}
