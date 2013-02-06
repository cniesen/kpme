/**
 * Copyright 2004-2013 The Kuali Foundation
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
package org.kuali.hr.time.calendar;

import org.kuali.hr.time.timeblock.TimeBlock;

import java.util.ArrayList;
import java.util.List;

public class TkCalendarDay extends CalendarDay{
	private List<TimeBlock> timeblocks = new ArrayList<TimeBlock>();
    private List<TimeBlockRenderer> blockRenderers = new ArrayList<TimeBlockRenderer>();
    private String dayNumberString;
    private Boolean gray;

	public List<TimeBlock> getTimeblocks() {
		return timeblocks;
	}

	public void setTimeblocks(List<TimeBlock> timeblocks) {
		this.timeblocks = timeblocks;
        for (TimeBlock tb : timeblocks) {
            blockRenderers.add(new TimeBlockRenderer(tb));
        }
	}

    public List<TimeBlockRenderer> getBlockRenderers() {
        return blockRenderers;
    }

    public String getDayNumberString() {
        return dayNumberString;
    }

    public void setDayNumberString(String dayNumberString) {
        this.dayNumberString = dayNumberString;
    }

	public Boolean getGray() {
		return gray;
	}

	public void setGray(Boolean gray) {
		this.gray = gray;
	}

}
