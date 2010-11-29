package org.kuali.hr.time.task.service;

import java.util.List;

import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.kuali.hr.time.task.Task;
import org.kuali.hr.time.task.validation.TaskServiceRule;
import org.kuali.hr.time.util.exceptions.ServiceException;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * 
 * @author Jigar
 *
 */

@WebService(endpointInterface = "org.kuali.hr.time.task.service.TaskService")
public class TaskServiceImpl implements TaskService {
	private static final Logger LOG = Logger.getLogger(TaskService.class);

	@Transactional
	public boolean addTasks(List<Task> tasks) throws ServiceException {
		try {
			TaskServiceRule taskServiceRule = new TaskServiceRule();
			// validating xml data
			for (Task task : tasks) {
				if (!taskServiceRule.validateTaskObject(task)) {
					throw new IllegalArgumentException("Invalid data for Task");
				}
			}
			// save /update
			for (Task task : tasks) {
				if (task.getTkTaskId() != null) {
					Task oldTask = KNSServiceLocator.getBusinessObjectService()
							.findBySinglePrimaryKey(Task.class,
									task.getTkTaskId());
					KNSServiceLocator.getBusinessObjectService().save(oldTask);
				}

				task.setTkTaskId(null);
				KNSServiceLocator.getBusinessObjectService().save(task);
			}

		} catch (Exception ex) {
			LOG.error(ex);
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			throw new ServiceException(ex);
		}
		return true;
	}

}
