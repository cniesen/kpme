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