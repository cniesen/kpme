package edu.iu.uis.hr.tk.config;

import java.io.FileInputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.kuali.rice.core.config.ConfigContext;
import org.kuali.rice.ksb.messaging.MessageFetcher;
import org.kuali.rice.ksb.service.KSBServiceLocator;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.DefaultResourceLoader;

import edu.iu.hrms.hredoc.infrastructure.HredocServiceLocator;
import edu.iu.uis.eden.IUServiceLocator;
import edu.iu.uis.hr.Context;

public class TKInitializingBean implements InitializingBean {
	private Logger LOG = Logger.getLogger(TKInitializingBean.class);
	
	public void afterPropertiesSet() {
		try{
			confirmAppNameAndEnvironment();
			setIUWorkflowPlugin();
			verifyADS();
			verifyGDS();
			configureLogging();
		}catch(Exception e){
			LOG.error("Problem found with configuration " +e);
			throw new RuntimeException(e);
		}
	}

	public void confirmAppNameAndEnvironment() throws Exception{
		String appName = ConfigContext.getCurrentContextConfig().getProperty("app.code");
		String environ = ConfigContext.getCurrentContextConfig().getProperty("environment");
		String url = ConfigContext.getCurrentContextConfig().getProperty("app.context.name");
		
		if(!StringUtils.equals(appName+"-"+environ, url)){
			throw new RuntimeException("Incorrect app.code or enivornment variable in application url - URL: " + url +" AppCode: " + appName+"-"+environ);
		}
	}
	 
	public void setupConfigProperties(ServletContextEvent sce) throws Exception{
		LOG.info("Started setting ConfigurationProperties\n");
		HredocServiceLocator.getInstance().setApplicationContext((ConfigurableApplicationContext)Context.getApplicationContext());
		LOG.info("Finished setting up ConfigurationProperties\n");
	}
	
	public void setIUWorkflowPlugin() throws Exception{
		LOG.info("Started IUWorkflowPlugin\n");
		IUServiceLocator.getInstance().overrideApplicationContext(Context.getApplicationContext());
		LOG.info("Finished IUWorkflowPlugin\n");
	}
	
	@SuppressWarnings("deprecation")
	public void verifyGDS() throws Exception {
		LOG.info("Started verifying GDS settings\n");
		String gdsSettingsLoc = ConfigContext.getRootConfig().getProperty("gds.settings.path");
		String gdsSecurityLoc = ConfigContext.getRootConfig().getProperty("gds.security.path");
		Properties settingsProperties = new Properties();
		Properties securityProperties = new Properties();
		settingsProperties.load(new FileInputStream(gdsSettingsLoc));
		securityProperties.load(new FileInputStream(gdsSecurityLoc));
		if (StringUtils.isEmpty(settingsProperties.getProperty("ldapUrl"))) {
			throw new RuntimeException("Error loading ldapUrl in GDS settings");
		}

		if (StringUtils.isBlank(securityProperties.getProperty("username"))) {
			throw new RuntimeException("Error loading username in GDS security");
		}

		if (StringUtils.isBlank(securityProperties.getProperty("password"))) {
			throw new RuntimeException("Error loading password in GDS security");
		}

		LOG.info("Finished verifying GDS settings\n");
	}
	
	@SuppressWarnings("deprecation")
	public void verifyADS() throws Exception {
		LOG.info("Started verifying ADS settings\n");
		String adsSecurityLoc = ConfigContext.getRootConfig().getProperty("ads.security.path");
		Properties securityProperties = new Properties();
		securityProperties.load(new FileInputStream(adsSecurityLoc));

		if (StringUtils.isBlank(securityProperties.getProperty("username"))) {
			throw new RuntimeException("Error loading username in ADS security");
		}

		if (StringUtils.isBlank(securityProperties.getProperty("password"))) {
			throw new RuntimeException("Error loading password in ADS security");
		}
		if (StringUtils.isBlank(securityProperties.getProperty("user"))) {
			throw new RuntimeException("Error loading user in ADS security");
		}
		LOG.info("Finished verifying ADS settings\n");

	}
	
	public void setupMessageFetcher() throws Exception {
		LOG.info("Staring up MessageFetcher\n");
	    // execute the MessageFetcher to grab any messages that were being processed
	    // when the system shut down originally
	    MessageFetcher messageFetcher = new MessageFetcher((Integer) null);
	    KSBServiceLocator.getThreadPool().execute(messageFetcher);
	    LOG.info("Finished starting MessageFetcher\n");
    }
	
	
	public void configureLogging() throws Exception{
		String log4jPath = ConfigContext.getCurrentContextConfig().getLog4jFileLocation();
		if (StringUtils.isNotEmpty(log4jPath)) {
			LOG.info("\n\n\n\n\nUsing log4j file " + log4jPath + " to configure logging.  Shuting down startup logger and starting application logger.\n\n\n\n\n\n");
			LogManager.shutdown();
			configureLog4j(log4jPath);
		}
	}
	
	private void configureLog4j(String filePath) {
		try {
			if (filePath.contains("classpath")) {
				DefaultResourceLoader drl = new DefaultResourceLoader();
				PropertyConfigurator.configure(drl.getResource(filePath).getURL());
			} else {
				PropertyConfigurator.configureAndWatch(filePath, 1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to configure logging properly.", e);
		}
	}
	
	
}
