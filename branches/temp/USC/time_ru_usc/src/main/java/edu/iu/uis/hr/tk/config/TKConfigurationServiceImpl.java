package edu.iu.uis.hr.tk.config;

import org.apache.log4j.Logger;
import org.kuali.rice.ksb.messaging.MessageFetcher;
import org.kuali.rice.ksb.service.KSBServiceLocator;
import org.springframework.jdbc.core.JdbcTemplate;

import edu.iu.uis.hr.client.ApplicationInitializeListener;
import edu.iu.uis.hr.tk.TKServiceLocator;


public class TKConfigurationServiceImpl implements TKConfigurationService {
	private static Logger LOG = Logger.getLogger(ApplicationInitializeListener.class);
	public void setupMessageFetcher() throws Exception {
		LOG.info("Staring up MessageFetcher\n");
	    // execute the MessageFetcher to grab any messages that were being processed
	    // when the system shut down originally
	    MessageFetcher messageFetcher = new MessageFetcher((Integer) null);
	    KSBServiceLocator.getThreadPool().execute(messageFetcher);
	    LOG.info("Finished starting MessageFetcher\n");
    }
	
	public void verifyDataSources() throws Exception{
		LOG.info("Starting verifying datasources\n");
		final String tstSql = "select 1 from dual";
		int num = TKServiceLocator.getTkJdbcTemplate().queryForInt(tstSql);
		if (num != 1) {
			throw new RuntimeException("Did not receive correct response from tk datasource");
		}
		num = TKServiceLocator.getEnJdbcTemplate().queryForInt(tstSql);
		if (num != 1) {
			throw new RuntimeException("Did not receive correct response form workflow datasource");
		}
		num = new JdbcTemplate(TKServiceLocator.getDataSource("hrSudsDataSource")).queryForInt(tstSql);
		if (num != 1) {
			throw new RuntimeException("Did not receive correct response from SUDS datasource");
		}
		LOG.info("Finished verifying datasources\n");
	}

}
