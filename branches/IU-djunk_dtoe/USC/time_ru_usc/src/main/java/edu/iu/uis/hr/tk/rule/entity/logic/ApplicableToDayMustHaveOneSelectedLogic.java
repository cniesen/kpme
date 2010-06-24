package edu.iu.uis.hr.tk.rule.entity.logic;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.logic.AbstractLogic;
import edu.iu.uis.hr.entity.logic.MessageKeyConstants;
import edu.iu.uis.hr.entity.logic.OperationalLogic;
import edu.iu.uis.hr.tk.rule.entity.ShiftDifferentialRule;

public class ApplicableToDayMustHaveOneSelectedLogic extends AbstractLogic implements OperationalLogic {

    private static final Logger LOG = Logger.getLogger(ApplicableToDayMustHaveOneSelectedLogic.class);
    private final String APPLICABLE_TO_DAY = "Applicable To Day";

    public boolean isMatch() {
        return true;
    }

    public void execute(Entity entity) {
        LOG.info("Started ApplicableToDayMustHaveOneSelectedLogic execute()");
        if (!(((ShiftDifferentialRule)entity).isApplicableToSunday() || ((ShiftDifferentialRule)entity).isApplicableToMonday() || ((ShiftDifferentialRule)entity).isApplicableToTuesday() || ((ShiftDifferentialRule)entity).isApplicableToWednesday() || ((ShiftDifferentialRule)entity).isApplicableToThursday() || ((ShiftDifferentialRule)entity).isApplicableToFriday() || ((ShiftDifferentialRule)entity).isApplicableToSaturday())) {
            entity.getEntityErrors().add(new String[] { FieldNames.APPLICABLE_TO_SUNDAY, FieldNames.APPLICABLE_TO_MONDAY, FieldNames.APPLICABLE_TO_TUESDAY, FieldNames.APPLICABLE_TO_WEDNESDAY, FieldNames.APPLICABLE_TO_THURSDAY, FieldNames.APPLICABLE_TO_FRIDAY, FieldNames.APPLICABLE_TO_SATURDAY, }, getMessage(MessageKeyConstants.ERROR_REQUIRED_FIELD, new String[] { APPLICABLE_TO_DAY }));
        }
        LOG.info("Finished ApplicableToDayMustHaveOneSelectedLogic execute()");
    }

}