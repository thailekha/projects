package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import models.*;

public class Home extends Controller {

    public static void index() {
    	if (session.get("logged_in_userid") != null)
    	{
    		String userId = session.get("logged_in_userid");
    		User user = User.findById(Long.parseLong(userId));
    		//String name = user.firstName; 
    		if(user.friendships.size() > 0)
    		{   
    			if (user.inBox.size() > 0)
    			{
    				ArrayList<Message> messageBox = new ArrayList<Message>();
    				for(Message message : user.inBox)
    				{
    					for(Friendship friendship: user.friendships)
    					{
    						if(friendship.targetUser == message.from)
    						{
    							messageBox.add(message);
    						}
    					}
    				}
    				user.inBox = messageBox;
    				if (user.inBox.size() > 0)
    				{
    					String text1 = "Sender";
    					String text2 = "Message text";
    					render(user, text1, text2);
    				}
    				else
    				{
    					String text1 = "No messages";
        				render(user, text1);
    				}
    			}
    			else
    			{
    				String text1 = "No messages";
    				render(user, text1);
    			}
    		}
    		else
    		{
    			indexStart();
    		}
        }
    	else
    	{
    		Accounts.index();
    	}
    }
    
    public static void indexStart()
    {
    	String userId = session.get("logged_in_userid");
    	User user = User.findById(Long.parseLong(userId));
    	render(user);
    }
    
    public static void drop(Long id) 
    {
    	String userId = session.get("logged_in_userid");
    	User user = User.findById(Long.parseLong(userId));
    	User friend = User.findById(id);
    	user.unfriend(friend);
    	Logger.info("Dropping " + friend.email);
    	index();    	
    }
}