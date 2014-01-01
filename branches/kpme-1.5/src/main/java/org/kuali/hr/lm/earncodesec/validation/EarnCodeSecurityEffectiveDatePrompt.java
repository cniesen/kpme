/**
 * Copyright 2004-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.hr.lm.earncodesec.validation;


import org.kuali.hr.core.document.question.KpmeEffectiveDatePromptBase;
import org.kuali.hr.lm.earncodesec.EarnCodeSecurity;
import org.kuali.hr.time.service.base.TkServiceLocator;
import org.kuali.rice.krad.bo.PersistableBusinessObject;

public class EarnCodeSecurityEffectiveDatePrompt extends KpmeEffectiveDatePromptBase {
    @Override
    protected boolean futureEffectiveDateExists(PersistableBusinessObject pbo) {
        EarnCodeSecurity earnCodeSecurity = (EarnCodeSecurity)pbo;
        int numberOfNewer = TkServiceLocator.getEarnCodeSecurityService().getNewerEarnCodeSecurityCount(earnCodeSecurity.getEarnCode(), earnCodeSecurity.getEffectiveDate());
        return numberOfNewer > 0;
    }
}
