package org.kuali.hr.time.webservices.xml.service;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Calendar;

import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryFactory;
import org.kuali.hr.job.Job;
import org.kuali.hr.time.accrual.AccrualCategory;
import org.kuali.hr.time.accrual.TimeOffAccrual;
import org.kuali.hr.time.assignment.Assignment;
import org.kuali.hr.time.assignment.AssignmentAccount;
import org.kuali.hr.time.assignment.validation.AssignmentRule;
import org.kuali.hr.time.salgroup.SalGroup;
import org.kuali.hr.time.util.TkConstants;
import org.kuali.hr.time.webservices.xml.model.AccrualCategoryWrapper;
import org.kuali.hr.time.webservices.xml.model.AssignmentAccountWrapper;
import org.kuali.hr.time.webservices.xml.model.AssignmentWrapper;
import org.kuali.hr.time.webservices.xml.model.JobWrapper;
import org.kuali.hr.time.webservices.xml.model.TimeOffAccrualWrapper;
import org.kuali.hr.time.webservices.xml.model.WorkAreaWrapper;
import org.kuali.hr.time.webservices.xml.model.XMLDoc;
import org.kuali.hr.time.webservices.xml.util.WebServiceUtil;
import org.kuali.hr.time.webservices.xml.validation.AssignmentAccountRule;
import org.kuali.hr.time.webservices.xml.validation.JobRule;
import org.kuali.hr.time.webservices.xml.validation.TimeOffAccrualRule;
import org.kuali.hr.time.webservices.xml.validation.WorkAreaRule;
import org.kuali.hr.time.workarea.WorkArea;
import org.kuali.rice.kns.service.KNSServiceLocator;

public class UploadXMLServiceImpl implements UploadXMLService {

	// set it to WorkArea.xsd location
	private final static String WORK_AREA_XSD = UploadXMLServiceImpl.class
			.getResource("/xsd/WorkArea.xsd").toString();
	private final static String ASSIGNMENT_ACCOUNT_XSD = UploadXMLServiceImpl.class
			.getResource("/xsd/AssignmentAccount.xsd").toString();
	private final static String ASSIGNMENT_XSD = UploadXMLServiceImpl.class
			.getResource("/xsd/Assignment.xsd").toString();
	private final static String TIME_OFF_ACCRUAL_XSD = UploadXMLServiceImpl.class
			.getResource("/xsd/TimeOffAccrual.xsd").toString();
	private final static String ACCRUAL_CATEGORY_XSD = UploadXMLServiceImpl.class
			.getResource("/xsd/AccuralCategory.xsd").toString();
	private final static String JOB_XSD = UploadXMLServiceImpl.class
			.getResource("/xsd/Job.xsd").toString();

	public UploadXMLServiceImpl() {

	}

	@Override
	public String uploadWorkArea(XMLDoc xmlDoc) {
		System.out.println("Inside uploadWorkArea");
		String result = TkConstants.SUCCESS_XML_CODE;
		FileOutputStream os = null;
		InputStream is = null;
		FileOutputStream fos = null;
		File tempFile = null;
		try {
			tempFile = File.createTempFile("workarea", ".xml");
			fos = new FileOutputStream(tempFile);
			// writing request data to File
			fos.write(xmlDoc.getData());
			// validate the incoming file against schema
			WebServiceUtil.validateXMLwithXSD(tempFile.getAbsolutePath(),
					WORK_AREA_XSD);
			// it works ! now lets parse it.
			is = new ByteArrayInputStream(xmlDoc.getData());

			WorkAreaWrapper workAreaWrapper = (WorkAreaWrapper) WebServiceUtil
					.parseXml(is, WorkAreaWrapper.class, WorkArea.class);

			// validating data
			WorkAreaRule workAreaRule = new WorkAreaRule();
			for (WorkArea workArea : workAreaWrapper.getWorkAreas()) {
				if (!workAreaRule.validateWorkAreaObject(workArea)) {
					throw new IllegalArgumentException(
							"invalid data for workarea");
				}
			}
			// save / update data

			for (WorkArea workArea : workAreaWrapper.getWorkAreas()) {
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

				// System.out.println("" + p1.getTkWorkAreaId());
			}

		} catch (Exception ex) {

			ex.printStackTrace();
			result = TkConstants.FAILURE_XML_CODE + " : " + ex.getMessage();
		} finally {
			tempFile.delete();
			close(fos);
			close(is);

		}
		return result;
	}

	@Override
	public String uploadAssignmentAccount(XMLDoc xmlDoc) {
		System.out.println("Inside uploadAssignmentAccount");
		String result = TkConstants.SUCCESS_XML_CODE;
		FileOutputStream os = null;
		InputStream is = null;
		FileOutputStream fos = null;
		File tempFile = null;

		try {
			tempFile = File.createTempFile("assignmentAccount", ".xml");
			fos = new FileOutputStream(tempFile);
			// writing request data to File
			fos.write(xmlDoc.getData());
			// validate the incoming file against schema
			WebServiceUtil.validateXMLwithXSD(tempFile.getAbsolutePath(),
					ASSIGNMENT_ACCOUNT_XSD);
			// it works ! now lets parse it.
			is = new ByteArrayInputStream(xmlDoc.getData());

			AssignmentAccountWrapper assignmentAccountWrapper = (AssignmentAccountWrapper) WebServiceUtil
					.parseXml(is, AssignmentAccountWrapper.class,
							AssignmentAccount.class);
			AssignmentAccountRule assignmentAccountRule = new AssignmentAccountRule();
			// validating all data
			for (AssignmentAccount assignmentAccount : assignmentAccountWrapper
					.getAssignmentAccounts()) {
				if (!assignmentAccountRule
						.validateAssignmentAccountObject(assignmentAccount)) {
					throw new IllegalArgumentException(
							"Invalid data for assignmentAccount");
				}
			}
			// saving all data
			for (AssignmentAccount assignmentAccount : assignmentAccountWrapper
					.getAssignmentAccounts()) {
				if (assignmentAccount.getTkAssignAcctId() != null) {
					AssignmentAccount oldAssignmentAccount = KNSServiceLocator
							.getBusinessObjectService().findBySinglePrimaryKey(
									AssignmentAccount.class,
									assignmentAccount.getTkAssignAcctId());
					oldAssignmentAccount.setActive(Boolean.FALSE);
					KNSServiceLocator.getBusinessObjectService().save(
							oldAssignmentAccount);
				}
				assignmentAccount.setTkAssignAcctId(null);
				KNSServiceLocator.getBusinessObjectService().save(
						assignmentAccount);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			result = TkConstants.FAILURE_XML_CODE + " : " + ex.getMessage();
		} finally {
			tempFile.delete();
			close(fos);
			close(is);

		}
		return result;
	}

	@Override
	public String uploadJob(XMLDoc xmlDoc) {
		System.out.println("Inside uploadJob");
		String result = TkConstants.SUCCESS_XML_CODE;
		FileOutputStream os = null;
		InputStream is = null;
		FileOutputStream fos = null;
		File tempFile = null;
		try {
			tempFile = File.createTempFile("job", ".xml");
			fos = new FileOutputStream(tempFile);
			// writing request data to File
			fos.write(xmlDoc.getData());
			// validate the incoming file against schema
			WebServiceUtil.validateXMLwithXSD(tempFile.getAbsolutePath(),
					JOB_XSD);
			// it works ! now lets parse it.
			is = new ByteArrayInputStream(xmlDoc.getData());

			JobWrapper jobWrapper = (JobWrapper) WebServiceUtil.parseXml(is,
					JobWrapper.class, Job.class);
			JobRule jobRule = new JobRule();
			// validating all Jobs
			for (Job job : jobWrapper.getJobs()) {
				if (!jobRule.validateJobObject(job)) {
					throw new IllegalArgumentException("invalid data for job");
				}
			}
			// save / update jobs
			for (Job job : jobWrapper.getJobs()) {
				if (job.getHrJobId() != null) {
					Job oldJob = KNSServiceLocator
							.getBusinessObjectService()
							.findBySinglePrimaryKey(Job.class, job.getHrJobId());
					oldJob.setActive(Boolean.FALSE);
					oldJob.setTimestamp(new Timestamp(Calendar.getInstance()
							.getTimeInMillis()));
					KNSServiceLocator.getBusinessObjectService().save(oldJob);
				}
				job.setHrJobId(null);
				job.setTimestamp(new Timestamp(Calendar.getInstance()
						.getTimeInMillis()));
				KNSServiceLocator.getBusinessObjectService().save(job);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			result = TkConstants.FAILURE_XML_CODE + " : " + ex.getMessage();
		} finally {
			tempFile.delete();
			close(fos);
			close(is);
		}
		return result;

	}

	@Override
	public String uploadTimeOffAccrual(XMLDoc xmlDoc) {
		System.out.println("Inside uploadTimeOfAccural");
		String result = TkConstants.SUCCESS_XML_CODE;
		FileOutputStream os = null;
		InputStream is = null;
		FileOutputStream fos = null;
		File tempFile = null;
		try {
			tempFile = File.createTempFile("timeOfAccural", ".xml");
			fos = new FileOutputStream(tempFile);
			// writing request data to File
			fos.write(xmlDoc.getData());
			// validate the incoming file against schema
			WebServiceUtil.validateXMLwithXSD(tempFile.getAbsolutePath(),
					TIME_OFF_ACCRUAL_XSD);
			// it works ! now lets parse it.
			is = new ByteArrayInputStream(xmlDoc.getData());

			TimeOffAccrualWrapper timeOfAccuralWrapper = (TimeOffAccrualWrapper) WebServiceUtil
					.parseXml(is, TimeOffAccrualWrapper.class,
							TimeOffAccrual.class);
			TimeOffAccrualRule timeOffAccrualRule = new TimeOffAccrualRule();
			// validate TimeOffArrcuals
			for (TimeOffAccrual timeOffAccrual : timeOfAccuralWrapper
					.getTimeOffAccruals()) {
				if (!timeOffAccrualRule
						.validateTimeOffAccrualObject(timeOffAccrual)) {
					throw new IllegalArgumentException(
							"Invalid data for timeOffAccrual");
				}
			}
			// save / update TimeOffAccrual
			for (TimeOffAccrual timeOffAccrual : timeOfAccuralWrapper
					.getTimeOffAccruals()) {
				timeOffAccrual.setLaAccrualId(null);
				KNSServiceLocator.getBusinessObjectService().save(
						timeOffAccrual);
			}

		} catch (Exception ex) {
			System.out.println("IOException : " + ex);
			ex.printStackTrace();
			result = TkConstants.FAILURE_XML_CODE + " : " + ex.getMessage();
		} finally {
			tempFile.delete();
			close(fos);
			close(is);
		}
		return result;
	}

	@Override
	public String uploadAssignment(XMLDoc xmlDoc) {
		System.out.println("Inside uploadAssignment");
		String result = TkConstants.SUCCESS_XML_CODE;
		FileOutputStream os = null;
		InputStream is = null;
		FileOutputStream fos = null;
		File tempFile = null;

		try {
			tempFile = File.createTempFile("assignment", ".xml");
			fos = new FileOutputStream(tempFile);
			// writing request data to File
			fos.write(xmlDoc.getData());
			// validate the incoming file against schema
			WebServiceUtil.validateXMLwithXSD(tempFile.getAbsolutePath(),
					ASSIGNMENT_XSD);
			// it works ! now lets parse it.
			is = new ByteArrayInputStream(xmlDoc.getData());

			AssignmentWrapper assignmentWrapper = (AssignmentWrapper) WebServiceUtil
					.parseXml(is, AssignmentWrapper.class, Assignment.class);
			org.kuali.hr.time.webservices.xml.validation.AssignmentRule assignmentRule = new org.kuali.hr.time.webservices.xml.validation.AssignmentRule();
			// validating xml data
			for (Assignment assignment : assignmentWrapper.getAssignments()) {
				if (!assignmentRule.validateAssignmentObject(assignment)) {
					throw new IllegalArgumentException(
							"Invalid data for Assignment");
				}
			}
			// save /update
			for (Assignment assignment : assignmentWrapper.getAssignments()) {
				if (assignment.getTkAssignmentId() != null) {
					Assignment oldAssignment = KNSServiceLocator
							.getBusinessObjectService().findBySinglePrimaryKey(
									Assignment.class,
									assignment.getTkAssignmentId());
					oldAssignment.setActive(Boolean.FALSE);
					oldAssignment.setTimestamp(new Timestamp(Calendar
							.getInstance().getTimeInMillis()));

					KNSServiceLocator.getBusinessObjectService().save(
							oldAssignment);
				}

				assignment.setTimestamp(new Timestamp(Calendar.getInstance()
						.getTimeInMillis()));
				assignment.setTkAssignmentId(null);
				KNSServiceLocator.getBusinessObjectService().save(assignment);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			result = TkConstants.FAILURE_XML_CODE + " : " + ex.getMessage();
		} finally {
			tempFile.delete();
			close(fos);
			close(is);
		}
		return result;
	}

	@Override
	public String uploadAccrualCategory(XMLDoc xmlDoc) {
		System.out.println("Inside uploadAccrualCategory");
		String result = TkConstants.SUCCESS_XML_CODE;
		FileOutputStream os = null;
		InputStream is = null;
		FileOutputStream fos = null;
		File tempFile = null;
		try {
			tempFile = File.createTempFile("accrualCategory", ".xml");
			fos = new FileOutputStream(tempFile);
			// writing request data to File
			fos.write(xmlDoc.getData());
			// validate the incoming file against schema
			WebServiceUtil.validateXMLwithXSD(tempFile.getAbsolutePath(),
					ACCRUAL_CATEGORY_XSD);
			// it works ! now lets parse it.
			is = new ByteArrayInputStream(xmlDoc.getData());

			AccrualCategoryWrapper accrualCategoryWrapper = (AccrualCategoryWrapper) WebServiceUtil
					.parseXml(is, AccrualCategoryWrapper.class,
							AccrualCategory.class);
			// save / update accrualCategory
			for (AccrualCategory accrualCategory : accrualCategoryWrapper
					.getAccrualCategories()) {
				if (accrualCategory.getLaAccrualCategoryId() != null) {
					AccrualCategory oldAccrualCategory = KNSServiceLocator
							.getBusinessObjectService().findBySinglePrimaryKey(
									AccrualCategory.class,
									accrualCategory.getLaAccrualCategoryId());
					oldAccrualCategory.setTimestamp(new Timestamp(Calendar
							.getInstance().getTimeInMillis()));
					oldAccrualCategory.setActive(Boolean.FALSE);
					KNSServiceLocator.getBusinessObjectService().save(
							oldAccrualCategory);
				}
				accrualCategory.setLaAccrualCategoryId(null);
				accrualCategory.setTimestamp(new Timestamp(Calendar
						.getInstance().getTimeInMillis()));
				KNSServiceLocator.getBusinessObjectService().save(
						accrualCategory);
			}

		} catch (Exception ex) {

			ex.printStackTrace();
			result = TkConstants.FAILURE_XML_CODE + " : " + ex.getMessage();
		} finally {
			tempFile.delete();
			close(fos);
			close(is);
		}
		return result;

	}

	public void flush(Flushable flushable) {
		if (flushable == null) {
			return;
		}
		try {
			flushable.flush();
		} catch (IOException e) {
			e.printStackTrace();
			// log the exception
		}
	}

	public void close(Closeable closable) {
		if (closable == null) {
			return;
		}
		try {
			closable.close();
		} catch (IOException e) {
			e.printStackTrace();
			// log the exception
		}
	}
}
