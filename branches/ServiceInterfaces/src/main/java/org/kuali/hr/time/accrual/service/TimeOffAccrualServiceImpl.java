package org.kuali.hr.time.accrual.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.kuali.hr.sys.context.SpringContext;
import org.kuali.hr.time.accrual.TimeOffAccrual;
import org.kuali.hr.time.accrual.dao.TimeOffAccrualDao;
import org.kuali.hr.time.accrual.validation.TimeOffAccrualServiceRule;
import org.kuali.hr.time.util.exceptions.ServiceException;
import org.kuali.rice.kns.service.KNSServiceLocator;

/**
 * 
 * @author Jigar
 * 
 */
@WebService(endpointInterface = "org.kuali.hr.time.accrual.service.TimeOffAccrualService")
public class TimeOffAccrualServiceImpl implements TimeOffAccrualService {
	private static final Logger log = Logger
			.getLogger(TimeOffAccrualService.class);

	public boolean addTimeOffAccruals(List<TimeOffAccrual> timeOffAccruals)
			throws ServiceException {

		TimeOffAccrualServiceRule timeOffAccrualServiceRule = new TimeOffAccrualServiceRule();
		ServiceException serviceException = new ServiceException(
				"Error in TimeOffAccrual WebService");
		// save / update TimeOffAccrual
		for (TimeOffAccrual timeOffAccrual : timeOffAccruals) {
			try {
				// validation
				if (!timeOffAccrualServiceRule
						.validateTimeOffAccrualObject(timeOffAccrual)) {
					throw new IllegalArgumentException(
							"Invalid data for timeOffAccrual");
				}
				if (timeOffAccrual.getLaAccrualId() != null) {
					TimeOffAccrual oldTimeOffAccrual = KNSServiceLocator
							.getBusinessObjectService().findBySinglePrimaryKey(
									TimeOffAccrual.class,
									timeOffAccrual.getLaAccrualId());

					KNSServiceLocator.getBusinessObjectService().save(
							oldTimeOffAccrual);
				}

				timeOffAccrual.setLaAccrualId(null);
				KNSServiceLocator.getBusinessObjectService().save(
						timeOffAccrual);

			} catch (Exception ex) {
				log.error("Error with TimeOffAccrual Object:" + timeOffAccrual,
						ex);
				serviceException.add(timeOffAccrual, ex);

			}
		}
		if(serviceException.hasErrors()){
			throw serviceException;
		}
		return true;
	}
	
	@Override
	public List<TimeOffAccrual> getTimeOffAccruals(String principalId) {
		return SpringContext.getBean(TimeOffAccrualDao.class).getTimeOffAccruals(principalId);
	}
	
	@Override
	public List<Map<String, Object>> getTimeOffAccrualsCalc(String principalId) {
		
		List<Map<String, Object>> timeOffAccrualsCalc = new ArrayList<Map<String, Object>>();
		
		for(TimeOffAccrual timeOffAccrual : getTimeOffAccruals(principalId)) {
			Map<String, Object> output = new LinkedHashMap<String, Object>();
			output.put("accrualCategory", timeOffAccrual.getAccrualCategory());
			output.put("hoursAccrued", timeOffAccrual.getHoursAccrued());
			output.put("hoursTaken", timeOffAccrual.getHoursTaken());
			output.put("hoursAdjust", timeOffAccrual.getHoursAdjust());
			BigDecimal totalHours = timeOffAccrual.getHoursAccrued().subtract(timeOffAccrual.getHoursTaken()).add(timeOffAccrual.getHoursAdjust());
			output.put("totalHours", totalHours);
			output.put("effdt", timeOffAccrual.getEffectiveDate());
			
			timeOffAccrualsCalc.add(output);
		}
		
		return timeOffAccrualsCalc;
	}
}
