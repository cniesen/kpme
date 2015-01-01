/**
 * Copyright 2004-2015 The Kuali Foundation
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
package org.kuali.kpme.tklm.time.clocklog;

import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.services.KimApiServiceLocator;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;

public class RemoteSwipeDevice extends PersistableBusinessObjectBase {

    private static final long serialVersionUID = 1L;

    private String ipAddress;
    private String deviceName;
    private String principalId;
    private String lastSeenOnline;
    private boolean active;

    private transient Person principal;

    public RemoteSwipeDevice() {

    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getLastSeenOnline() {
        return lastSeenOnline;
    }

    public void setLastSeenOnline(String lastSeenOnline) {
        this.lastSeenOnline = lastSeenOnline;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(String principalId) {
        this.principalId = principalId;
        this.setPrincipal(KimApiServiceLocator.getPersonService().getPerson(this.principalId));
    }

    public Person getPrincipal() {
        return principal;
    }

    public void setPrincipal(Person principal) {
        this.principal = principal;
    }
}