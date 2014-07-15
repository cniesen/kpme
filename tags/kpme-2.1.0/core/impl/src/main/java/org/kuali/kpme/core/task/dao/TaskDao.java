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
package org.kuali.kpme.core.task.dao;


import java.util.List;

import org.joda.time.LocalDate;
import org.kuali.kpme.core.task.TaskBo;

public interface TaskDao {
	public TaskBo getTask(String tkTaskId);
    public TaskBo getTask(Long task, LocalDate asOfDate);

    public void saveOrUpdate(TaskBo task);
    public void saveOrUpdate(List<TaskBo> tasks);
    public TaskBo getMaxTask(Long workArea);

    List<TaskBo> getTasks(Long task, String description, Long workArea, LocalDate fromEffdt, LocalDate toEffdt);
    public int getTaskCount(Long task);
}
