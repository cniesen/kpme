package edu.iu.uis.hr.tk.log;

import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LoggingEvent;

import edu.iu.uis.hr.client.UserSession;
import edu.iu.uis.hr.directory.entity.User;
import edu.iu.uis.hr.tk.TKServiceLocator;

public class HreCustomLayout extends PatternLayout {

	@Override
    public String format(LoggingEvent event) {
	    String logEv =  super.format(event);
	    String[] logPcs = logEv.split("]");
	    String log = "";
	    if(logPcs.length == 2){
	    	log = logPcs[0] + "] " + getMessageHeading() + " Class: " + event.getLoggerName() + logPcs[1];
	    	return log;
	    }
	    return logEv;
    }
	private String getMessageHeading(){
		String msg = "";
		UserSession usrSess = TKServiceLocator.getWebUserService().getUserSession();
		if(usrSess != null){
			User user = usrSess.getUser();
			msg = "-UID[" + user.getNetworkId()+ "]";
		}
		else{
			msg = "-UID[UNKNOWN]";
		}
		return msg;
	}
}
