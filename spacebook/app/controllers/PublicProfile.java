package controllers;

import play.*;

import play.mvc.*;

import java.util.*;

import models.*;

public class PublicProfile extends Controller {
    
    public static void visit(Long id)
    {
    	if(session.get("logged_in_userid") != null)
    	{
    		User user = User.findById(id);
    		Logger.info("Just visiting the page for " + user.firstName + " " + user.lastName);
    		render(user);
    	}
    	else
		{
    		Accounts.index();
		}
    }
    
    public static void sendMessage(Long id, String messageText, String subject)
    {
    	String userId = session.get("logged_in_userid");
    	User fromUser = User.findById(Long.parseLong(userId));
    	User toUser = User.findById(id);
    	
    	Logger.info("Message from user " + 
    	        fromUser.firstName + ' ' + fromUser.lastName +" to " +
    	        toUser.firstName + ' ' + toUser.lastName +": " +
    	        messageText + "(Subject: " + subject + ")");
    	
    	fromUser.sendMessage(toUser, messageText, subject);
    	
    	visit(id);
    }
}