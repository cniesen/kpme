package edu.iu.uis.hr.tk.entity.logic;

import java.util.Iterator;

import edu.iu.uis.hr.CheckboxList;
import edu.iu.uis.hr.directory.entity.EmailRecipient;
import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.logic.AbstractLogic;
import edu.iu.uis.hr.entity.logic.MessageKeyConstants;
import edu.iu.uis.hr.entity.logic.OperationalLogic;
import edu.iu.uis.hr.tk.entity.SupervisorNotification;

public class SupervisorMustHaveOneSelectedLogic extends AbstractLogic implements OperationalLogic {

    public boolean isMatch() {
        return true;
    }

    public void execute(Entity entity) {
        if (!(entity instanceof SupervisorNotification)) {
            throw new IllegalArgumentException("SupervisorMustHaveOneSelectedLogic's execute(Entity entity) method requires a non-null Entity of type SupervisorNotification");
        }
        // TODO : may need a new message for this error?
        CheckboxList supervisors = (CheckboxList) ((SupervisorNotification) entity).getSupervisors();
        for (Iterator iter = supervisors.iterator(); iter.hasNext();) {
            if (((EmailRecipient) iter.next()).isChecked()) {
                return;
            }
        }
         entity.getEntityErrors().add(new String[] { "supervisors" }, getMessage(MessageKeyConstants.ERROR_INVALID_VALUE_NOLOOKUP, new String[] { getLabel("supervisors") }));
    }

}
