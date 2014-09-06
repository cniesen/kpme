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
package org.kuali.kpme.tklm.time.clocklog;

import java.util.Date;
import java.util.Map;

public class RemoteClockLog {

    private Map<String, String> cardData;
    private String principalId;
    private String position;
    private Long clockLogTimestamp;
    private boolean fromQueue;
    // not from JSON
    private Date clockLog;

    public Map<String, String> getCardData() {
        return cardData;
    }

    public void setCardData(Map<String, String> cardData) {
        this.cardData = cardData;
    }

    public String getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(String principalId) {
        this.principalId = principalId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Long getClockLogTimestamp() {
        return clockLogTimestamp;
    }

    public void setClockLogTimestamp(Long clockLogTimestamp) {
        this.clockLogTimestamp = clockLogTimestamp;
    }

    public Date getClockLog() {
        return clockLog;
    }

    public void setClockLog(Date clockLog) {
        this.clockLog = clockLog;
    }

    public boolean isFromQueue() {
        return fromQueue;
    }

    public void setFromQueue(boolean fromQueue) {
        this.fromQueue = fromQueue;
    }

}