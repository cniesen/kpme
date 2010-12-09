package org.kuali.hr.time.paytype.service;

import java.sql.Date;
import java.util.List;

import javax.jws.WebService;

import org.kuali.hr.sys.context.SpringContext;
import org.kuali.hr.time.exceptions.TkException;
import org.kuali.hr.time.paytype.PayType;
import org.kuali.hr.time.paytype.dao.PayTypeDao;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @author djb - add webservices
 * 
 */
@WebService(endpointInterface = "org.kuali.hr.time.paytype.service.PayTypeService")
public class PayTypeServiceImpl implements PayTypeService {

	@Override
	public void saveOrUpdate(PayType payType) {
		SpringContext.getBean(PayTypeDao.class).saveOrUpdate(payType);
	}

	@Transactional
	public void saveOrUpdate(List<PayType> payTypeList) {
		SpringContext.getBean(PayTypeDao.class).saveOrUpdate(payTypeList);
	}

	@Override
	public PayType getPayType(String payType, Date effectiveDate) {
		PayType payTypeObj = null;
		payTypeObj = SpringContext.getBean(PayTypeDao.class).getPayType(
				payType, effectiveDate);
		return payTypeObj;
	}

	// djb 11.17.10 webService
	@Transactional
	public boolean addPayTypes(List<PayType> payTypes) throws TkException {
		// go through each PayType that is coming in
		// add PayType if it is NOT there.
		for (PayType newPayType : payTypes) {
			try {
				PayTypeDao payTypeDao = SpringContext.getBean(PayTypeDao.class);

				// check if we have a PayType with the same id already
				PayType oldPayType = payTypeDao
						.getPayType(newPayType.getPayType(), new Date(
								new java.util.Date().getTime()));
				// if no .. save it
				if (oldPayType == null) {
					KNSServiceLocator.getBusinessObjectDao().save(newPayType);
				} else {
					// else update it
					oldPayType.setPayType(newPayType.getPayType());
					oldPayType.setDescr(newPayType.getDescr());
					oldPayType.setRegEarnCode(newPayType.getRegEarnCode());
					oldPayType.setEffectiveDate(newPayType.getEffectiveDate());
					oldPayType.setTimestamp(newPayType.getTimestamp());
					oldPayType.setActive(newPayType.getActive());

					KNSServiceLocator.getBusinessObjectDao().save(oldPayType);
				}
				// any exceptions will be transformed into service exceptions
			} catch (Exception e) {
				TransactionAspectSupport.currentTransactionStatus()
						.setRollbackOnly();
				throw new TkException(e.getMessage());
			}
		}
		return true;

	}
}
