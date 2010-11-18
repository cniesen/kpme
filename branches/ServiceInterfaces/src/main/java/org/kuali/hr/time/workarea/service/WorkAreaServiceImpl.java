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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

public class WorkAreaServiceImpl implements WorkAreaService {

	@SuppressWarnings("unused")
	private static final Logger LOG = Logger
			.getLogger(WorkAreaService.class);

	 

	public WorkAreaServiceImpl() {
		
	}

	@Override
	public WorkArea getWorkArea(Long workAreaId, Date asOfDate) {
		return SpringContext.getBean(WorkAreaDao.class).getWorkArea(workAreaId, asOfDate);
	}

	@Override
	public void saveOrUpdate(WorkArea workArea) {
		SpringContext.getBean(WorkAreaDao.class).saveOrUpdate(workArea);
	}

	

	@Transactional
	public boolean addWorkAreas(List<WorkArea> workAreas)
			throws ServiceException {

		try {
			WorkAreaServiceRule workAreaServiceRule = new WorkAreaServiceRule();
			for (WorkArea workArea : workAreas) {
				if (!workAreaServiceRule.validateWorkAreaObject(workArea)) {
					throw new IllegalArgumentException(
							"invalid data for workarea");
				}
			}
			// save / update data

			for (WorkArea workArea : workAreas) {
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

			}

		} catch (Exception ex) {
			LOG.error(ex);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new ServiceException(ex);
		}
		return true;

	}

}
