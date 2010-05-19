package edu.iu.uis.hr.tk.rule.entity.logic;

import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.logic.AbstractLogic;
import edu.iu.uis.hr.entity.logic.MessageKeyConstants;
import edu.iu.uis.hr.entity.logic.OperationalLogic;
import edu.iu.uis.hr.tk.rule.entity.TimeCollectionRule;

public class HoursDistributionAllowedLogic extends AbstractLogic implements OperationalLogic {

    public boolean isMatch() {
        return true;
    }

    public void execute(Entity entity) {
        if (!(entity instanceof TimeCollectionRule)) {
            throw new IllegalArgumentException("HoursDistributionAllowedLogic's execute(Entity entity) method requires a non-null Entity of type TimeCollectionRule");
        }
        // TODO : may need a new message for this error?
        if (!((TimeCollectionRule) entity).isClockUseRequired() && ((TimeCollectionRule) entity).isHoursDistributionAllowed()) {
            entity.getEntityErrors().add(new String[] { FieldNames.HOURS_DISTRIBUTION_ALLOWED }, getMessage(MessageKeyConstants.ERROR_INVALID_VALUE_NOLOOKUP, new String[] { getLabel(FieldNames.HOURS_DISTRIBUTION_ALLOWED) }));
        }
    }

}
