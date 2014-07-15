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
package org.kuali.kpme.core.service.module;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.kuali.kpme.core.bo.HrBusinessObject;
import org.kuali.kpme.core.util.HrConstants;
import org.kuali.rice.krad.service.impl.ModuleServiceBase;


public class KpmeModuleService extends ModuleServiceBase {

    @Override
    public List<List<String>> listAlternatePrimaryKeyFieldNames(Class businessObjectInterfaceClass) {
        List<List<String>> retList = new ArrayList<List<String>>();
        List<String> keyList = new ArrayList<String>();
        try {
            keyList.addAll((java.util.List<String>) businessObjectInterfaceClass.getDeclaredField("BUSINESS_KEYS").get(businessObjectInterfaceClass));
        } catch (NoSuchFieldException e) {
            LOG.warn(businessObjectInterfaceClass.getClass().getName() + " does not contain a BUSINESS_KEYS list");
        } catch (IllegalAccessException e) {
            LOG.warn(businessObjectInterfaceClass.getClass().getName() + " does not contain a BUSINESS_KEYS list");
        }

        if (HrBusinessObject.class.isAssignableFrom(businessObjectInterfaceClass)) {
            keyList.add(HrConstants.EFFECTIVE_DATE_FIELD);
        }
        if (CollectionUtils.isNotEmpty(keyList)) {
            retList.add(keyList);
            return retList;
        }
        return super.listAlternatePrimaryKeyFieldNames(businessObjectInterfaceClass);
    }
}
