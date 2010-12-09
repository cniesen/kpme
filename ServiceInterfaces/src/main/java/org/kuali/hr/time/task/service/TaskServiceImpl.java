package org.kuali.hr.time.task.service;

import java.util.List;
import javax.jws.WebService;
import org.apache.log4j.Logger;
import org.kuali.hr.time.task.Task;
import org.kuali.hr.time.task.validation.TaskServiceRule;
import org.kuali.hr.time.util.exceptions.ServiceException;
import org.kuali.rice.kns.service.KNSServiceLocator;

/**
 * 
 * @author Jigar
 * 
 */

@WebService(endpointInterface = "org.kuali.hr.time.task.service.TaskService")
public class TaskServiceImpl implements TaskService {
	private static final Logger log = Logger.getLogger(TaskService.class);

	public boolean addTasks(List<Task> tasks) throws ServiceException {

		TaskServiceRule taskServiceRule = new TaskServiceRule();
		ServiceException serviceException = new ServiceException(
				"Error in Task WebService");
		// save /update
		for (Task task : tasks) {
			try {
				// validating
				if (!taskServiceRule.validateTaskObject(task)) {
					throw new IllegalArgumentException("Invalid data for Task");
				}

				if (task.getTkTaskId() != null) {
					Task oldTask = KNSServiceLocator.getBusinessObjectService()
							.findBySinglePrimaryKey(Task.class,
									task.getTkTaskId());
					KNSServiceLocator.getBusinessObjectService().save(oldTask);
				}

				task.setTkTaskId(null);
				KNSServiceLocator.getBusinessObjectService().save(task);

			} catch (Exception ex) {
				log.error("Error with Task Object:" + task, ex);
				serviceException.add(task, ex);
			}
		}
		if (serviceException.hasErrors()) {
			throw serviceException;
		}
		return true;
	}

}
