package org.kuali.hr.time.paytype.dao;

import java.sql.Date;
import java.util.List;

import org.kuali.hr.time.exceptions.TkException;
import org.kuali.hr.time.paytype.PayType;

public interface PayTypeDao {

	public void saveOrUpdate(PayType payType);

	public void saveOrUpdate(List<PayType> payTypeList);

	public PayType getPayType(String payType, Date effectiveDate);

	public PayType getPayTypeByPrincipalId(String principalId)
			throws TkException;

}
