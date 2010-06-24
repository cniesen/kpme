//package edu.iu.uis.hr.tk;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.crypto.KeyGenerator;
//import javax.naming.NamingEnumeration;
//import javax.naming.directory.Attributes;
//import javax.naming.directory.DirContext;
//import javax.naming.directory.SearchControls;
//import javax.naming.directory.SearchResult;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
//import org.kuali.rice.core.config.ConfigContext;
//import org.kuali.rice.kew.service.KEWServiceLocator;
//import org.kuali.rice.kew.user.AuthenticationUserId;
//import org.kuali.rice.kew.workgroup.GroupNameId;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import edu.iu.hrms.hredoc.workflow.service.WorkgroupService;
//import edu.iu.uis.eden.IUServiceLocator;
//import edu.iu.uis.hr.Context;
//import edu.iu.uis.hr.Log;
//import edu.iu.uis.hr.tk.util.TkConstants;
//import edu.iu.uis.sit.util.directory.AdsConnectionFactory;
//import edu.iu.uis.sit.util.directory.AdsHelper;
//
//public class WorkflowWorkgroupUtility {
//
//	private static final Logger LOG_USER_IDS = Logger.getLogger("NotFoundUserIdsLog");
//	private static final Logger LOG_WORKGROUP_IDS = Logger.getLogger("EmptyWorkgroupsLog");
//	private static final Logger LOG_WORKGROUP_ADDED = Logger.getLogger("WorkgroupsAddedLog");
//	private static final Logger LOG = Logger.getLogger(WorkflowWorkgroupUtility.class);
//
//	private static List<String> GROUP_ROLES = new ArrayList<String>();
//	static {
//		GROUP_ROLES.add(edu.iu.uis.hr.tk.directory.Utility.VIEW_ONLY_ROLE);
//		GROUP_ROLES.add(edu.iu.uis.hr.tk.directory.Utility.REVIEWER_ROLE);
//		for (int i=1; i <= 9; i++){
//			GROUP_ROLES.add(edu.iu.uis.hr.tk.directory.Utility.SUPERVISOR_ROLE+"_"+i);
//		}
//		for (int i=1; i <= 9; i++){
//			GROUP_ROLES.add(edu.iu.uis.hr.tk.directory.Utility.PAYROLL_PROCESSOR_ROLE+"_"+i);
//		}
//		GROUP_ROLES.add(edu.iu.uis.hr.tk.directory.Utility.PAYROLL_MANAGER_ROLE+"_");
//		GROUP_ROLES.add(edu.iu.uis.hr.tk.directory.Utility.SYSTEM_ADMINISTRATOR_ROLE);
//		GROUP_ROLES.add(edu.iu.uis.hr.tk.directory.Utility.INTERFACE_MANAGER_ROLE);
//	}
//
//	public static String SPRING_BEANS = "classpath:SpringBeans.xml";
//
//	public static void setUp() {
//		ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext(SPRING_BEANS);
//		Context.setApplicationContext(appContext);
//		IUServiceLocator.getInstance().overrideApplicationContext(Context.getApplicationContext());
//	}
//
//	public static void configureLogging() throws Exception {
//		String log4jPath = ConfigContext.getCurrentContextConfig().getProperty(TkConstants.ConfigSettings.LOG_LOG4J_PROPERTIES);
//		if (StringUtils.isNotEmpty(log4jPath)) {
//			LogManager.shutdown();
//			Log.configureLog4j(log4jPath);
//		}
//
//	}
//
//	public static void main(String[] args) throws Exception {
//		KeyGenerator keygen = KeyGenerator.getInstance("DES");
//
//		setUp();
//		configureLogging();
//		long start = System.currentTimeMillis();
//		if (args.length > 0) {
//			generateWorkflowWorkgroupFromADS(StringUtils.trim(args[0]));
//		} else {
//		generateWorkflowWorkgroupFromADS();
//		}
//		long end = System.currentTimeMillis();
//		System.out.println("****The ADS Workgroup converstion completed in " + (end - start) + " ms ******");
//	}
//
//	
//	@SuppressWarnings("unchecked")
//	public static void generateWorkflowWorkgroupFromADS() throws Exception {
//		long start = System.currentTimeMillis();
//		BaseWorkgroup workgroup;
//		
//		DirContext ctx = AdsConnectionFactory.getDirContext("hreadspx", "finy07xu");
//        SearchControls ctls = new SearchControls();
//        ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);
//        
//        for (String groupRoleName : GROUP_ROLES) {
//	        List<String> groupNames = new ArrayList();
//	        String filter = "(&(objectCategory=CN=Group,CN=Schema,CN=Configuration,DC=ads,DC=iu,DC=edu)(cn=" + groupRoleName + "*))";
//	        try {
//				NamingEnumeration answer = ctx.search("dc=ads,dc=iu,dc=edu", filter, ctls);
//				Attributes groupAttributes;
//				while (answer.hasMoreElements()) {
//					SearchResult searchResult = (SearchResult) answer.next();
//					groupAttributes = searchResult.getAttributes();
//					groupNames.add(groupAttributes.get("cn").get().toString().toUpperCase());
//				}
//			} catch (Exception e) {
//				LOG.error("WorkflowWorkgroup Conversion error " + e);
//				throw new RuntimeException(e);
//			}
//
//			int count = 0;
//			for (String groupName : groupNames) {
//	//			 //this loop for test purposes
//	//			 if (count++ > 1500) {
//	//			   break;
//	//			 }
//				if (groupName != null) {
//					try {
//							if (!StringUtils.contains(groupName, "TK")){
//								LOG_USER_IDS.debug("NOT A TK ROLE: " + groupName);
//								LOG.error("NOT A TK ROLE: " + groupName);
//							}
//							workgroup = (BaseWorkgroup) KEWServiceLocator.getWorkgroupService().getBlankWorkgroup();
//							workgroup.setGroupNameId(new GroupNameId(groupName));
//							workgroup.setActiveInd(true);
//							workgroup.setDescription("TIME Workgroup for " + groupName);
//							workgroup.setWorkgroupType("W");
//							workgroup.setCurrentInd(true);
//							assignNetworkIdsToGroup(groupName, workgroup);
//							saveWorkgroup(workgroup);
//							if (workgroup.getWorkgroupMembers().size() <= 0) {
//								LOG_WORKGROUP_IDS.debug("Group Name: " + groupName + " is saved but has no members");
//							}
//							LOG_WORKGROUP_ADDED.debug(groupName + " is sucessfully saved.");
//							LOG.error(groupName + " is sucessfully saved.");
//						} catch (Exception e) {
//							throw new RuntimeException(e.getMessage(), e);
//						}
//				}
//			}
//        }//endFor GROUP_ROLES
//
//		long end = System.currentTimeMillis();
//		LOG_USER_IDS.warn("********** generateWorkflowWorkgroupFromADS " + (end - start) + " ms ***********");
//		LOG_WORKGROUP_IDS.warn("********** generateWorkflowWorkgroupFromADS " + (end - start) + " ms ***********");
//
//	}
//
//	@SuppressWarnings("unchecked")
//	public static void generateWorkflowWorkgroupFromADS(String groupNameToAdd) throws Exception {
//		long start = System.currentTimeMillis();
//		BaseWorkgroup workgroup;
//
//		DirContext ctx = AdsConnectionFactory.getDirContext("hreadspx", "finy07xu");
//		SearchControls ctls = new SearchControls();
//		ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);
//
//		Attributes groupAttributes = null;
//		String filter = "(&(objectCategory=CN=Group,CN=Schema,CN=Configuration,DC=ads,DC=iu,DC=edu)(cn=" + groupNameToAdd + "*))";
//		try {
//			NamingEnumeration answer = ctx.search("dc=ads,dc=iu,dc=edu", filter, ctls);
//			while (answer.hasMoreElements()) {
//				SearchResult searchResult = (SearchResult) answer.next();
//				groupAttributes = searchResult.getAttributes();
//				//groupNames.add(groupAttributes.get("cn").get().toString().toUpperCase());
//			}
//		} catch (Exception e) {
//			LOG.error("WorkflowWorkgroup Conversion error " + e);
//			throw new RuntimeException(e);
//		}
//
//		int count = 0;
//		if (StringUtils.equalsIgnoreCase(groupNameToAdd, groupAttributes.get("cn").get().toString().toUpperCase())) {
//			try {
//				if (!StringUtils.contains(groupNameToAdd, "TK")) {
//					LOG_USER_IDS.debug("NOT A TK ROLE: " + groupNameToAdd);
//					LOG.error("NOT A TK ROLE: " + groupNameToAdd);
//				}
//				workgroup = (BaseWorkgroup) KEWServiceLocator.getWorkgroupService().getBlankWorkgroup();
//				workgroup.setGroupNameId(new GroupNameId(groupNameToAdd));
//				workgroup.setActiveInd(true);
//				workgroup.setDescription("TIME Workgroup for " + groupNameToAdd);
//				workgroup.setWorkgroupType("W");
//				workgroup.setCurrentInd(true);
//				assignNetworkIdsToGroup(groupNameToAdd, workgroup);
//				saveWorkgroup(workgroup);
//				if (workgroup.getWorkgroupMembers().size() <= 0) {
//					LOG_WORKGROUP_IDS.debug("Group Name: " + groupNameToAdd + " is saved but has no members");
//				}
//				LOG_WORKGROUP_ADDED.debug(groupNameToAdd + " is sucessfully saved.");
//				LOG.error(groupNameToAdd + " is sucessfully saved.");
//			} catch (Exception e) {
//				throw new RuntimeException(e.getMessage(), e);
//			}
//		}
//
//		long end = System.currentTimeMillis();
//		LOG_USER_IDS.warn("********** generateWorkflowWorkgroupFromADS " + (end - start) + " ms ***********");
//		LOG_WORKGROUP_IDS.warn("********** generateWorkflowWorkgroupFromADS " + (end - start) + " ms ***********");
//		
//	}
//	
//	private static void assignNetworkIdsToGroup(String groupName, BaseWorkgroup workgroup) throws Exception {
//
//		List<String> networkIds = new AdsHelper("hreadspx", "finy07xu").getGroupUsers(groupName, AdsHelper.NO_GROUP_DEPTH);
//
//		for (String networkId : networkIds) {
//
//			if (!(workgroup instanceof BaseWorkgroup)) {
//				throw new IllegalArgumentException("Can only copy instances of BaseWorkgroups, given class was: " + workgroup.getClass().getName());
//			}
//
//			try {
//				WorkflowUser user = KEWServiceLocator.getUserService().getWorkflowUser(new AuthenticationUserId(networkId));
//				workgroup.getMembers().add(user);
//				BaseWorkgroupMember workgroupMember = new BaseWorkgroupMember();
//				workgroupMember.setMemberType("U");
//				workgroupMember.setWorkflowId(user.getWorkflowId());
//				workgroupMember.setWorkgroup(workgroup);
//				workgroupMember.setWorkgroupId(workgroup.getWorkgroupId());
//				workgroupMember.setWorkgroupVersionNumber(workgroup.getVersionNumber());
//
//				workgroup.getWorkgroupMembers().add(workgroupMember);
//
//			} catch (EdenUserNotFoundException e) {
//				// need to have log file created
//				LOG_USER_IDS.debug("Invalid User Not In Workgroup - Network Id: " + networkId + " | Group Name: " + groupName);
//			} catch (ClassCastException ce) {
//				throw new RuntimeException("User " + networkId + " can't cast to BaseWorkgroupMember from WorklowUser");
//			}
//		}
//	}
//
//	private static void saveWorkgroup(BaseWorkgroup workgroup) {
//		Workgroup savedWorkgroup = KEWServiceLocator.getWorkgroupService().getWorkgroup(workgroup.getGroupNameId());
//		if (savedWorkgroup == null) {
//		getWorkgroupService().save(workgroup);
//		} 
//	}
//
//	private static WorkgroupService getWorkgroupService() {
//		WorkgroupService workGroupClient = null;
//		try {
//			workGroupClient = KEWServiceLocator.getWorkgroupService();
//		} catch (Exception e) {
//			throw new RuntimeException("Can't get Workgroup service: " + e.getMessage(), e);
//		}
//		return workGroupClient;
//	}
//
//}
