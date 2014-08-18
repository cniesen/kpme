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
package org.kuali.kpme.tklm.time.timeblock;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.kuali.kpme.tklm.api.time.timeblock.TimeBlockHistoryDetailContract;
import org.kuali.kpme.tklm.time.timeblock.TimeBlockHistory;
import org.kuali.kpme.tklm.time.timehourdetail.TimeHourDetailBo;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

@Entity
@Table(name = "TK_TIME_BLOCK_HIST_DETAIL_T")
public class TimeBlockHistoryDetail extends TimeHourDetailBo implements TimeBlockHistoryDetailContract {

    private static final long serialVersionUID = 1L;

    @PortableSequenceGenerator(name = "TK_TIME_BLOCK_HIST_DETAIL_S")
    @GeneratedValue(generator = "TK_TIME_BLOCK_HIST_DETAIL_S")
    @Id
    @Column(name = "TK_TIME_BLOCK_HIST_DETAIL_ID", length = 60)
    private String tkTimeBlockHistoryDetailId;

    @Column(name = "TK_TIME_BLOCK_HIST_ID", length = 60)
    private String tkTimeBlockHistoryId;

    @ManyToOne(targetEntity = TimeBlockHistory.class, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "TK_TIME_BLOCK_HIST_ID", referencedColumnName = "TK_TIME_BLOCK_HIST_ID", insertable = false, updatable = false)
    private TimeBlockHistory timeBlockHistory;

    @Transient
    private transient Person principal;

    @Transient
    private transient Person userPrincipal;

    public String getTkTimeBlockHistoryDetailId() {
        return tkTimeBlockHistoryDetailId;
    }

    public void setTkTimeBlockHistoryDetailId(String tkTimeBlockHistoryDetailId) {
        this.tkTimeBlockHistoryDetailId = tkTimeBlockHistoryDetailId;
    }

    public String getTkTimeBlockHistoryId() {
        return tkTimeBlockHistoryId;
    }

    public void setTkTimeBlockHistoryId(String tkTimeBlockHistoryId) {
        this.tkTimeBlockHistoryId = tkTimeBlockHistoryId;
    }

    public TimeBlockHistoryDetail(TimeHourDetailBo thd) {
        this.setEarnCode(thd.getEarnCode());
        this.setAmount(thd.getAmount());
        this.setHours(thd.getHours());
    }

    protected TimeBlockHistoryDetail(TimeBlockHistoryDetail t) {
        this.tkTimeBlockHistoryDetailId = t.tkTimeBlockHistoryDetailId;
        this.tkTimeBlockHistoryId = t.tkTimeBlockHistoryId;
        this.setEarnCode(t.getEarnCode());
        this.setHours(t.getHours());
        this.setAmount(t.getAmount());
    }

    public TimeBlockHistoryDetail copy() {
        return new TimeBlockHistoryDetail(this);
    }

    public TimeBlockHistoryDetail() {
    }

    public TimeBlockHistory getTimeBlockHistory() {
        return timeBlockHistory;
    }

    public void setTimeBlockHistory(TimeBlockHistory timeBlockHistory) {
        this.timeBlockHistory = timeBlockHistory;
    }

    public Person getPrincipal() {
        return principal;
    }

    public void setPrincipal(Person principal) {
        this.principal = principal;
    }

    public Person getUserPrincipal() {
        return userPrincipal;
    }

    public void setUserPrincipal(Person userPrincipal) {
        this.userPrincipal = userPrincipal;
    }

    public Date getBeginDate() {
        return timeBlockHistory.getBeginDate();
    }
}
