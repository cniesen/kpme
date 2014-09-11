package org.kuali.kpme.core.tkmdocument.authorization;

import org.apache.commons.lang.StringUtils;
import org.kuali.kpme.core.authorization.KPMEMaintenanceDocumentAuthorizerBase;
import org.kuali.rice.kew.api.document.DocumentStatus;
import org.kuali.rice.kim.api.KimConstants;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.document.authorization.DocumentAuthorizerBase;
import org.kuali.rice.kns.document.authorization.MaintenanceDocumentAuthorizer;
import org.kuali.rice.kns.document.authorization.MaintenanceDocumentAuthorizerBase;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.maintenance.MaintenanceViewAuthorizerBase;
import org.kuali.rice.krad.uif.view.View;
import org.kuali.rice.krad.uif.view.ViewModel;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.KRADUtils;

import java.util.Map;
import java.util.Set;

/**
 * Created by mlemons on 9/10/14.
 */

// MaintenanceDocumentAuthorizer
public class KhrEmployeeDocumentMaintenanceDocumentAuthorizer extends MaintenanceDocumentAuthorizerBase implements MaintenanceDocumentAuthorizer {

    @Override
    public boolean canCancel(Document document, Person user) {
        DocumentStatus status = document.getDocumentHeader().getWorkflowDocument().getStatus();

        String statusCode = status.getCode();
        if (StringUtils.equals(statusCode,"R"))
        {
            return false;
        }

        return super.canCancel(document, user);
    }
}
