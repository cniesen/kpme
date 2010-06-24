package edu.iu.uis.hr.tk.batch;

import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.SerializationUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.lob.OracleLobHandler;

import edu.iu.uis.hr.tk.TKServiceLocator;

public class TKBatchPollerThread extends Thread {

	
	private static final Logger LOG = Logger.getLogger(TKBatchPollerThread.class);
	private static final String GET_JOBS = "select TK_JOB_ID, SERIALIZED_RUNNABLE from TK_BATCH_JOB_ENTRIES where " +
	"IP_NET = ? and JOB_STATUS = 'S' and rownum < 100";

	
	private TKThreadPoolManager tkThreadPoolManager;
	private int secondsSleep;
	
	public TKBatchPollerThread(TKThreadPoolManager tkThreadPoolManager, int secondsSleep) {
		this.tkThreadPoolManager = tkThreadPoolManager;
		this.secondsSleep = secondsSleep;
	}
	
	@Override
	public void run() {
		LOG.info("Batch polling thread running");
		
		try {
			//give app time to start up...
			sleep(30000);
		} catch (InterruptedException e) {
			LOG.warn("Caught interrupt exception 1", e);
		} 
		
		while (true) {
			try {
				sleep(secondsSleep * 1000);
			} catch (InterruptedException e) {
				LOG.warn("Caught interrupt exception", e);
			}
			
			if (tkThreadPoolManager.getQueueSize() < 15) {
				List<TKBatchRunnable> runnables = getTKBatchRunnables();
				for (TKBatchRunnable batchRunnable : runnables) {
					tkThreadPoolManager.updateJobData(batchRunnable, Long.valueOf(0), "P", null);
					this.tkThreadPoolManager.executeRunnable(batchRunnable);
				}
			}
		}
	}

	
	@SuppressWarnings("unchecked")
	protected List<TKBatchRunnable> getTKBatchRunnables() {
		try {
			List<TKBatchRunnable> tkBatchRunnables =  TKServiceLocator.getTkJdbcTemplate().query(GET_JOBS, new Object[] {getIPNumber()}, new RowMapper() {
				public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
					byte[] objectBytes = new OracleLobHandler().getBlobAsBytes(rs, "SERIALIZED_RUNNABLE");
					return SerializationUtils.deserialize(objectBytes);
				}
			});
			
			return tkBatchRunnables;
			
		} catch (Exception e) {
			LOG.warn("Caught exception fetching jobs", e);
			return Collections.EMPTY_LIST;
		}
	}

	//move this method to a Util
	public static String getIPNumber() {
		try {
			return java.net.InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			return "unknown";
		}
	}
	
}
