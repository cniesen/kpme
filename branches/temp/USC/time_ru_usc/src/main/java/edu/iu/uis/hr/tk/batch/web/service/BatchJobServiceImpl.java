package edu.iu.uis.hr.tk.batch.web.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import edu.iu.uis.hr.tk.TKServiceLocator;
import edu.iu.uis.hr.tk.batch.TKBatchJobEntry;
import edu.iu.uis.hr.tk.batch.web.TKBatchJobForm;

public class BatchJobServiceImpl implements BatchJobService {
	private final String BATCH_JOB_SQL = "select * from TK_BATCH_JOB_ENTRIES where ";
	private final String BATCH_JOB_STATUS_UPDATE = "update TK_BATCH_JOB_ENTRIES SET JOB_STATUS = ? where ";
	private final String BATCH_IP_NET_UPDATE = "update TK_BATCH_JOB_ENTRIES SET IP_NET = ? where ";
	private final String BATCH_JOB_DELETE = "delete from TK_BATCH_JOB_ENTRIES where ";
	private final String BATCH_END_SQL = "rownum < 5000";

	@SuppressWarnings("unchecked")
	public List<TKBatchJobEntry> getBatchJobs(TKBatchJobForm frm){
		StringBuffer sb = new StringBuffer();
		sb.append(BATCH_JOB_SQL);
		List<String> vals = new ArrayList<String>();
		
		if(StringUtils.isNotBlank(frm.getTkJobId())){
			sb.append("tk_job_id = ? and ");
			vals.add(frm.getTkJobId());
		}
		if(StringUtils.isNotBlank(frm.getJobStatus())){
			sb.append("job_status = ? and ");
			vals.add(frm.getJobStatus());
		}
		if(StringUtils.isNotBlank(frm.getIpNumber())){
			sb.append("ip_net = ? and ");
			vals.add(frm.getIpNumber());
		}
		if(StringUtils.isNotBlank(frm.getDataId())){
			sb.append("data_id = ? and ");
			vals.add(frm.getDataId());
		}
		if(StringUtils.isNotBlank(frm.getTkBatchJobId())){
			sb.append("tk_batch_job_id = ? and ");
			vals.add(frm.getTkBatchJobId());
		}

		sb.append(BATCH_END_SQL);
		try{
			List<TKBatchJobEntry> lstBatchJobs = (List<TKBatchJobEntry>)TKServiceLocator.getTkJdbcTemplate().queryForObject(sb.toString(), vals.toArray(), new BatchJobRowMapper());
			return lstBatchJobs;
		}
		catch(Exception e){
			return new ArrayList<TKBatchJobEntry>();
		}
	}
	
	public void updateJobStatus(TKBatchJobForm frm, String newStatus){
		StringBuffer sb = new StringBuffer();
		sb.append(BATCH_JOB_STATUS_UPDATE);
		List<String> vals = new ArrayList<String>();
		vals.add(newStatus);
		if(StringUtils.isNotBlank(frm.getTkJobId())){
			sb.append("tk_job_id = ? and ");
			vals.add(frm.getTkJobId());
		}
		if(StringUtils.isNotBlank(frm.getJobStatus())){
			sb.append("job_status = ? and ");
			vals.add(frm.getJobStatus());
		}
		if(StringUtils.isNotBlank(frm.getIpNumber())){
			sb.append("ip_net = ? and ");
			vals.add(frm.getIpNumber());
		}
		if(StringUtils.isNotBlank(frm.getDataId())){
			sb.append("data_id = ? and ");
			vals.add(frm.getDataId());
		}
		if(StringUtils.isNotBlank(frm.getTkBatchJobId())){
			sb.append("tk_batch_job_id = ? and ");
			vals.add(frm.getTkBatchJobId());
		}
		TKServiceLocator.getTkJdbcTemplate().update(StringUtils.chomp(sb.toString(), "and "), vals.toArray());
	}

	public void updateIpNet(TKBatchJobForm frm, String newStatus){
		StringBuffer sb = new StringBuffer();
		sb.append(BATCH_IP_NET_UPDATE);
		List<String> vals = new ArrayList<String>();
		vals.add(newStatus);
		if(StringUtils.isNotBlank(frm.getTkJobId())){
			sb.append("tk_job_id = ? and ");
			vals.add(frm.getTkJobId());
		}
		if(StringUtils.isNotBlank(frm.getJobStatus())){
			sb.append("job_status = ? and ");
			vals.add(frm.getJobStatus());
		}
		if(StringUtils.isNotBlank(frm.getIpNumber())){
			sb.append("ip_net = ? and ");
			vals.add(frm.getIpNumber());
		}
		if(StringUtils.isNotBlank(frm.getDataId())){
			sb.append("data_id = ? and ");
			vals.add(frm.getDataId());
		}
		if(StringUtils.isNotBlank(frm.getTkBatchJobId())){
			sb.append("tk_batch_job_id = ? and ");
			vals.add(frm.getTkBatchJobId());
		}
		TKServiceLocator.getTkJdbcTemplate().update(StringUtils.chomp(sb.toString(), "and "), vals.toArray());
	}
	
	public void deleteJobEntries(TKBatchJobForm frm){
		StringBuffer sb = new StringBuffer();
		sb.append(BATCH_JOB_DELETE);
		List<String> vals = new ArrayList<String>();
		if(StringUtils.isNotBlank(frm.getTkJobId())){
			sb.append("tk_job_id = ? and ");
			vals.add(frm.getTkJobId());
		}
		if(StringUtils.isNotBlank(frm.getJobStatus())){
			sb.append("job_status = ? and ");
			vals.add(frm.getJobStatus());
		}
		if(StringUtils.isNotBlank(frm.getIpNumber())){
			sb.append("ip_net = ? and ");
			vals.add(frm.getIpNumber());
		}
		if(StringUtils.isNotBlank(frm.getDataId())){
			sb.append("data_id = ? and ");
			vals.add(frm.getDataId());
		}
		if(StringUtils.isNotBlank(frm.getTkBatchJobId())){
			sb.append("tk_batch_job_id = ? and ");
			vals.add(frm.getTkBatchJobId());
		}
		TKServiceLocator.getTkJdbcTemplate().update(StringUtils.chomp(sb.toString(), "and "),vals.toArray());
	}
	
	private class BatchJobRowMapper implements RowMapper{
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			List<TKBatchJobEntry> lstBatchJobs = new ArrayList<TKBatchJobEntry>();
			do{
				TKBatchJobEntry job = new TKBatchJobEntry();
				job.setTkJobId(rs.getString("tk_job_id"));
				job.setDataId(rs.getString("data_id"));
				job.setIpNet(rs.getString("ip_net"));
				job.setTotalTime(rs.getLong("total_time"));
				job.setTkBatchJobId(rs.getString("tk_batch_job_id"));
				job.setJobStatus(rs.getString("job_status"));
				job.setJobType(rs.getString("job_type"));
				job.setJobMessage(rs.getString("job_message"));
				job.setException(rs.getString("exception"));
				lstBatchJobs.add(job);
			} while(rs.next());
			return lstBatchJobs;
		}
	}
}
