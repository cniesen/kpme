package edu.iu.uis.hr.tk.appcelerator;

import org.apache.commons.lang.StringUtils;
import org.appcelerator.annotation.Service;
import org.appcelerator.messaging.Message;

import edu.iu.uis.hr.directory.UserNotFoundException;
import edu.iu.uis.hr.tk.TKServiceLocator;
import edu.iu.uis.hr.tk.directory.entity.User;
import edu.iu.uis.hr.tk.web.ErrorFilter;

public class AppceleratorService { 
  
    @Service(request = "validation.request", response = "validation.response", version = "1.0")
    protected void idCardValidation (Message request, Message response)
        throws Exception
    {
        String cardId = request.getData().getString("cardid");
        if (StringUtils.isEmpty(cardId)){  
        	response.getData().put("message", "<font color=red>Card not read properly.  Please swipe your ID again. </font>");
        } else {
	        String userId = "";
	        try{
	        	userId = TKServiceLocator.getWebUserService().getNetworkIdByCardId(cardId);
	        	User user = (User) TKServiceLocator.getWebUserService().getUser(userId); 
	        	if (user != null){
	        		 response.getData().put("message",  "<br> ::  <strong> Retrieving timesheet for " + user.getName() + " </strong> ::");
	        		 if (TKServiceLocator.getAssignmentService().hasSynchronousAssignment(user.getUniversityId())) {
	        			 response.getData().put("validid", "true");
	        		 } else {
	        			 response.getData().put("validid", "false");
	        			 response.getData().put("message",  "<font color=red>" + user.getName() + " (" + user.getNetworkId()+") does not have valid synchronous time assignments.</font>");
	        		 }
	        	} 
	        } catch (UserNotFoundException e){
	        	response.getData().put("message", "<font color=red>The card ID ( "+cardId+" ) is not a valid identification card.</font>");
	        	response.getData().put("validid", "false");
	        } catch (RuntimeException e){
	        	if (StringUtils.contains(ErrorFilter.getString(e), "EdenUserNotFoundException")){
	        		response.getData().put("message", "<font color=red>The card ID ( "+cardId+" ) does not link to a valid time assignment.</font>");
	        		response.getData().put("validid", "false");
	        	} else {
		        	response.getData().put("message", "<font color=red>An error has occurred, please contact a system administrator.</font>");
		        	response.getData().put("validid", "false");
		        	response.getData().put("exception", "true");
		        	response.getData().put("stacktrace", ErrorFilter.getString(e));
	        	}
	        }catch (Exception e){
	        	response.getData().put("message", "An error has occurred, please contact a system administrator.");
	        	response.getData().put("validid", "false");
	        	response.getData().put("exception", "true");
	        	response.getData().put("stacktrace", ErrorFilter.getString(e));
	        }
        }
    }


}
