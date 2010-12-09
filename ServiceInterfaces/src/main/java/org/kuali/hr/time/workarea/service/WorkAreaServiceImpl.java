package org.kuali.hr.time.workarea.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import org.apache.log4j.Logger;
import org.kuali.hr.sys.context.SpringContext;
import org.kuali.hr.time.util.exceptions.ServiceException;
import org.kuali.hr.time.workarea.WorkArea;
import org.kuali.hr.time.workarea.dao.WorkAreaDao;
import org.kuali.hr.time.workarea.validation.WorkAreaServiceRule;
import org.kuali.rice.kns.service.KNSServiceLocator;

public class WorkAreaServiceImpl implements WorkAreaService {

	private static final Logger log = Logger.getLogger(WorkAreaService.class);

	public WorkAreaServiceImpl() {

	}

	@Override
	public WorkArea getWorkArea(Long workAreaId, Date asOfDate) {
		return SpringContext.getBean(WorkAreaDao.class).getWorkArea(workAreaId,
				asOfDate);
	}

	@Override
	public void saveOrUpdate(WorkArea workArea) {
		SpringContext.getBean(WorkAreaDao.class).saveOrUpdate(workArea);
	}

	public boolean addWorkAreas(List<WorkArea> workAreas)
			throws ServiceException {

		WorkAreaServiceRule workAreaServiceRule = new WorkAreaServiceRule();
		ServiceException serviceException = new ServiceException(
				"Error in WorkArea WebService");
		// save / update data
		for (WorkArea workArea : workAreas) {
			try {
				// validation
				if (!workAreaServiceRule.validateWorkAreaObject(workArea)) {
					throw new IllegalArgumentException(
							"invalid data for workarea");
				}
				if (workArea.getTkWorkAreaId() != null) {
					WorkArea oldWorkArea = KNSServiceLocator
							.getBusinessObjectService().findBySinglePrimaryKey(
									WorkArea.class, workArea.getTkWorkAreaId());
					oldWorkArea.setActive(Boolean.FALSE);
					oldWorkArea.setTimestamp(new Timestamp(Calendar
							.getInstance().getTimeInMillis()));
					KNSServiceLocator.getBusinessObjectService().save(
							oldWorkArea);
				}
				workArea.setTkWorkAreaId(null);
				workArea.setTimestamp(new Timestamp(Calendar.getInstance()
						.getTimeInMillis()));
				KNSServiceLocator.getBusinessObjectService().save(workArea);

			} catch (Exception ex) {
				log.error("Error with WorkArea Object:" + workArea, ex);
				serviceException.add(workArea, ex);
			}
		}
		if (serviceException.hasErrors()) {
			throw serviceException;
		}
		return true;
	}

}
